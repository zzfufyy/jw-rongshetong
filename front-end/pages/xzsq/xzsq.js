// pages/xzsq/xzsq.js
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util')
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const sort_util = require('../../utils/sort_util');
// 加载服务
const communityInformationService = require('../../common/communityInformationService');
const string_util = require('../../utils/string_util');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        localchoose: false,
        streetchoose: true,
        communitychoose: true,
        showDistrictName: '',
        showStreetName: '',

        // 页面入参
        reserveDistrictNameList: [],

        districtList: [
            // {
            // 	districtName: '芙蓉区', streetNameList: [
            // 		{
            // 			streetName: '望岳街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		},
            // 		{
            // 			streetName: '桔子街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		},
            // 		{
            // 			streetName: '银盆岭街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		}
            // 	]
            // },
            // {
            // 	districtName: '岳麓区', streetNameList: [
            // 		{
            // 			streetName: '望岳街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		},
            // 		{
            // 			streetName: '桔子街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		},
            // 		{
            // 			streetName: '银盆岭街道', communityList: [
            // 				{ communityName: '社区一', }, { communityName: '社区er', }, { communityName: '社区3', }
            // 			]
            // 		}
            // 	]
            // },
        ],

    },
    lock: {},

    // 选择市辖区 -> 加载街道列表
    async bindtapChooseDistrict(e) {
        let currentDistrictName = e.currentTarget.dataset.name;
        let districtList = this.data.districtList;
        let currentStreetNameList = districtList.find(v => v.districtName == currentDistrictName).streetNameList;
        try {
            if (!this.lock.isLock) {
                this.lock.lock();
                if (e.currentTarget.dataset.name != this.data.showDistrictName) {
                    if (currentStreetNameList.length == 0) {
                        // 加载街道列表
                        let streetNameList = await communityInformationService.listStreetNameByDistrictName(currentDistrictName);
                        streetNameList = streetNameList.data;
                        districtList = districtList.map(v => {
                            if (v.districtName == currentDistrictName) {
                                streetNameList.forEach(r => {
                                    v.streetNameList.push({
                                        streetName: r,
                                        communityList: [],
                                    })
                                })
                            }
                            return v;
                        })
                        console.log(districtList);
                        this.setData({
                            districtList: districtList,
                            showDistrictName: e.currentTarget.dataset.name,
                        })
                    } else {
                        this.setData({

                            showDistrictName: e.currentTarget.dataset.name,
                        })
                    }

                } else {
                    this.setData({
                        showDistrictName: '',
                    })
                }
            }
        } catch (e) {
            console.error(e);
        } finally {
            this.lock.unLock();
        }

    },
    // 选择街道 加载社区列表
    async streetchoosetap(e) {
        let currentDistrictName = this.data.showDistrictName;
        let currentStreetName = e.currentTarget.dataset.name;
        let districtList = this.data.districtList;

        let currentStreetNameList = districtList.find(v => v.districtName == currentDistrictName).streetNameList;
        let currentCommunityList = currentStreetNameList.find(v => v.streetName == currentStreetName).communityList;
        try {
            if (!this.lock.isLock) {
                this.lock.lock();
                if (e.currentTarget.dataset.name != this.data.showStreetName) {
                    // 判断是否有数
                    if (currentCommunityList.length == 0) {
                        let communityList = await communityInformationService.listByStreetName(currentStreetName);
                        communityList = communityList.data;
                        // 加载社区列表
                        districtList = districtList.map(v => {
                            if (v.districtName == currentDistrictName) {
                                v.streetNameList = v.streetNameList.map(r => {
                                    if (r.streetName == currentStreetName) {
                                        communityList.forEach(d => {
                                            r.communityList.push({
                                                communityUuid: d.id,
                                                communityName: d.communityName,
                                            })
                                        })
                                    }
                                    return r;
                                });
                            }
                            return v;
                        })
                        this.setData({
                            districtList: districtList,
                            showStreetName: e.currentTarget.dataset.name,
                        })
                    } else {
                        this.setData({
                            showStreetName: e.currentTarget.dataset.name,
                        })
                    }
                } else {
                    this.setData({
                        showStreetName: '',
                    })
                }
            }
        } catch (e) {
            console.error(e);
        } finally {
            this.lock.unLock();
        }
    },



    catchtapSkipToSqfw(e) {
        let communityUuid = e.currentTarget.dataset.id;
        wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_COMMUNITYUUID, communityUuid)
        wx.navigateTo({
            url: "/pages/sqfw/sqfw?communityUuid=" + communityUuid,
        })

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let reserveDistrictNameList = options.reserveDistrictNameList;
        // reserveDistrictNameList = "麻阳县,岳麓区";
        this.setData({
            reserveDistrictNameList: string_util.splitByComma(reserveDistrictNameList)
        })
        this.lock = new Loading.Lock();
        try {
            Loading.begin(this.lock);
            await this.clearContent();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: async function () {
        // 清空  减少缓存
        let districtList = this.data.districtList;
        await this.clearContent();
        this.setData({
            districtList: districtList.map(v => {
                v.streetNameList = [];
                return v;
            })
        })

    },
    async clearContent() {
        this.setData({
            localchoose: false,
            streetchoose: true,
            communitychoose: true,
            showDistrictName: '',
            showStreetName: '',
        })
    },
    async loadContent() {
        // CONSTANT.CURRENT_CITY
        let districtNameList = await communityInformationService.listDistrictNameByCityName();
        districtNameList = districtNameList.data;
        districtNameList = districtNameList.filter(v => string_util.isNotEmpty(v));
        // 如有保留列表 保留
        let reserveDistrictNameList = this.data.reserveDistrictNameList;
        console.log(reserveDistrictNameList);
        if (reserveDistrictNameList.length != 0) {
            districtNameList = districtNameList.filter(v => {
                return reserveDistrictNameList.findIndex(r => r === v) !== -1
            })
        }
        let districtList = districtNameList.map(v => {
            return {
                districtName: v,
                streetNameList: [],
            }
        });
        districtList = sort_util.sortChangshaDistrict(districtList);
        // 加载 市辖区列表
        this.setData({
            districtList: districtList,
        })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})