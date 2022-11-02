// 加载常量
const CONSTANT = require('../../common/constant');
// 加载工具类
const sort_util = require('../../utils/sort_util');
const object_util = require('../../utils/object_util');
const Loading = require('../../utils/loading_util');
// 加载服务
const communityInformationService = require("../../common/communityInformationService");
const app = getApp();
Component({
    options: {
        multipleSlots: true // 在组件定义时的选项中启用多slot支持
    },
    /**
     * 组件的属性列表
     */
    properties: {
        // 默认选择社区为1
        max_choosed_number: {
            type: Number,
            value: 1,
        },
        choosedStreetList: {
            type: Array,
            value: [],
        }
    },

    /** 组件数据 */
    data: {
        flag: true,
        oht: "",
        hiddenPopup: true,
        districtList: [],
        // choosedCommunityList: [
        //      {communityUuid: "5850cc9a-a39f-2736-b318-c61deaea3b20", communityName: "茶园坡社区"}
        // ],
    },
    /**
     * 
     * 绑定使用页面事件： submit
     * 通过 e.detail.value获取
     * 选定社区列表
     */

    /** 组件方法 */
    methods: {
        //隐藏弹框
        hidePopup: function () {
            this.setData({
                hiddenPopup: true,
            })
        },
        //展示弹框
        showPopup() {
            this.setData({
                hiddenPopup: false,
            })
        },
        // 点击遮罩层
        bindtapMaskLayer: function () {
            this.setData({
                hiddenPopup: true,
            })
        },
        // 保存提交事件 - 绑定页面事件
        bindtapChooseStreetSubmit() {
            this.setData({
                hiddenPopup: true,
            });
            let detail = {
                value: this.data.choosedStreetList,
            }
            this.triggerEvent('submit', detail, {})
        },
        // 点击选择市辖区
        bindtapChooseDistrict: async function (e) {
            // 获取item项的id，和数组的下标值
            let currentDistrictName = e.target.dataset.name;
            let currentDistrictList = this.data.districtList;
            this.setData({
                districtList: currentDistrictList.map(v => {
                    v.checked = (v.districtName == currentDistrictName) ? true : false
                    return v;
                })
            })
            // // 先清空
            this.setData({
                streetList: [],
                communityList: [],
            })
            // 后加载对应的街道
            try {
                if (!this.lock.isLock) {
                    Loading.begin(this.lock);
                    await this._loadStreetList(currentDistrictName);
                }
            } catch (e) {
                console.error(e)
            } finally {
                Loading.end(this.lock)
            }
        },
        // 街道选择
        bindtapChooseStreet: async function (e) {
            let currentIndex = e.target.dataset.index;
            let currentStreetList = this.data.streetList;
            let choosedStreetList = this.data.choosedStreetList;
            let currentStreetItem = this.data.streetList[currentIndex];
            let currentDistrictName = this.data.districtList.find(v=> v.checked).districtName;
            console.info(this.data.districtList)
            if (currentStreetItem.checked) {
                return;
            }
            if (choosedStreetList.length >= this.data.max_choosed_number) {
                wx.showModal({
                    title: `仅能选择${this.data.max_choosed_number}个街道`,
                })
                return;
            }
            choosedStreetList.push({
                districtName: currentDistrictName,
                streetName: currentStreetItem.streetName,
            });
            this.setData({
                choosedStreetList: choosedStreetList,
            })
            this.setData({
                streetList: currentStreetList.map(v => {
                    v.checked = choosedStreetList.findIndex(r => v.streetName == r.streetName) == -1? false: true;
                    return v;
                }),
            });
        },
        bindtapCancelChooseStreet: function (e) {
            let index = e.currentTarget.dataset.index;
            let currentChoosedStreetList = this.data.choosedStreetList;
            let cancelItem = currentChoosedStreetList[index];
            currentChoosedStreetList.splice(index, 1);
            let streetList = this.data.streetList;
            streetList = streetList.map(v =>{
                if(v.streetName == cancelItem.streetName){
                    v.checked  = false;
                }
                return v;
            })
            this.setData({
                streetList : streetList,
                choosedStreetList: currentChoosedStreetList,
            });

        },
        /** 内部方法 */
        async _loadStreetList(districtName) {
            let streetNameList = (await communityInformationService.listStreetNameByDistrictName(districtName)).data;
            let choosedStreetList = this.data.choosedStreetList;
            console.info(choosedStreetList);
            let streetList = streetNameList.map(v => {
                let checked = choosedStreetList.findIndex(r => v == r.streetName) == -1 ? false : true;
                return {
                    streetName: v,
                    checked: checked,
                }
            });
            this.setData({
                streetList: streetList,
            });
        },
    },

    /** 组件属性 */
    lock: new Loading.Lock(),
    /** 组件生命周期 */
    lifetimes: {
        created: function () {
            this.lock = new Loading.Lock();
        },
        ready: async function () {
            // 加载 市辖区列表
            let districtNameList = await communityInformationService.listDistrictNameByCityName(CONSTANT.CURRENT_CITY);
            districtNameList = districtNameList.data;
            let districtList = districtNameList.map(v => {
                return {
                    districtName: v,
                    checked: false,
                }
            })
            districtList = sort_util.sortChangshaDistrict(districtList);
            this.setData({
                districtList: districtList,
            })

        }
    },



    /** 页面生命周期 */
    pageLifetimes: {
        show: function () {
            let that = this;
            wx.getSystemInfo({
                success: (res) => {
                    console.log(res)
                    let windowHeight = res.windowHeight;
                    let wht = windowHeight * 0.8 - 86
                    let oht = wht;
                    that.setData({
                        oht: oht
                    })
                },
            });
        },
        hide: function () {
            // 页面被隐藏
        },
        resize: function (size) {
            // 页面尺寸变化
        }
    }
})