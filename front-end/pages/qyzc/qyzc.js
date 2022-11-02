// pages/qyzc/qyzc.js
var QQMapWX = require('../../mapjs/qqmap-wx-jssdk.js');
// 加载工具类
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const Loading = require('../../utils/loading_util');
const object_util = require('../../utils/object_util');
const string_util = require('../../utils/string_util');
const sort_util = require('../../utils/sort_util');

// 加载服务
const jobCategoryService = require('../../common/jobCategoryService');
const companyForCategoryService = require('../../common/companyForCategoryService');
const recruitCompanyService = require('../../common/recruitCompanyService');
const communityInformationService = require('../../common/communityInformationService');
const globalService = require('../../common/globalService');

// 常量定义
const app = getApp();
const MAX_CHOOSE_CATEGORY_NUM = 5;
const PAGENAME = 'qyzc.js - ';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 招聘人
        recruiterTelephone: '',
        recruiterOpenid: '',
        // 企业名称
        companyUuid: '',
        companyName: '',
        companyAddress: '',
        longitude: 0.0,
        latitude: 0.0,
        // 分类选择
        array: ['IT', '文化传媒', '电子制造'], // categoryName
        categoryUuid_list: [],
        categoryUuid: '',
        index: 0,
        // 社区选择
        array1: ['社区1', '街道1', '街道2'], // communityName
        communityUuidList: [],
        communityUuid: '',
        index1: 0,
        positionData: '',
        yxz: 0,
        licenseId: '',
       
        jobCategoryList: [],
        
        datdid: '',
        companyPerson: '',
        recruiterName: '',
        recruiterTelephone: '',

        companyPerson: '',
        companyPhone: '',

        // 已选社区
        choosedCommunityList: [],

        oht: '',
    },
    /********** 页面输入事件 BEGIN **********/
    // 企业名字
    bindinputCompanyName(e) {
        this.setData({
            companyName: e.detail.value
        })
    },
    // 公司联系人姓名
    bindInputCompanyPerson(e) {
        this.setData({
            companyPerson: e.detail.value
        })
    },
    bindinputCompanyPhone(e) {
        this.setData({
            companyPhone: e.detail.value
        })
    },
    // 招聘人姓名
    bindtapRecruiterName(e) {
        this.setData({
            juridicalPerson: e.detail.value,
            recruiterName: e.detail.value,
        })
    },
    // 招聘人联系电话
    bindgetphonenumberRecruiter(e) {
        let that = this
        var sessionKey = wx.getStorageSync('sessionKey')
        wx.request({
            url: app.globalData.web_path + '/wx/user/' + app.globalData.appId + '/phoneNumberInfo',
            data: {
                sessionKey: sessionKey,
                iv: e.detail.iv,
                encryptedData: e.detail.encryptedData,
            },
            header: app.globalData.header,
            success: function (res) {
                that.setData({
                    recruiterTelephone: res.data.data.phoneNumber
                })
                wx.setStorageSync('phone', res.data.data.phoneNumber)
            },
            fail: function (res) {
            }
        })
    },
    // 更改定位
    bindtapChangeLocation(e) {
        let that = this
        wx.chooseLocation({
            type: 'gcj02',
            success(res) {
                console.log(PAGENAME + '公司地址选择:'); console.log(res);
                that.setData({
                    positionData: res.address + ' ' + res.name,
                    companyAddress: res.address + ' ' + res.name,
                    longitude: res.longitude,
                    latitude: res.latitude,
                })
            }
        })
    },
    // 统一社会信用代码
    bindinputLicenseId(e) {
        this.setData({
            licenseId: e.detail.value
        })
    },
    /********** 页面输入事件 END **********/


    /**BEGIN 组件事件 - 选择期望职位*/
    bindtapChooseJobCategory(e) {
        this.chooseJobCategoryComponent.showPopup();
    },
    submitChooseJobCategory(e) {
        this.setData({
            jobCategoryList: e.detail.value,
        })
    },
    // 招聘岗位意向去除
    catchtapCancelCategoryIcon(e) {
        let that = this;
        let currentCategoryUuid = e.currentTarget.dataset.id
        let jobCategoryList = that.data.jobCategoryList;
        jobCategoryList = jobCategoryList.map(v => {
            if (v.categoryUuid == currentCategoryUuid) {
                v.checked = false;
                v.display = 'none';
            }
            return v;
        })
        this.setData({
            jobCategoryList: jobCategoryList,
        })

    },
    /**END 组件事件 - 选择期望职位*/

    /** BEGIN 组件事件 - 选择社区*/
    bindtapChooseCommunity(e) {
        this.chooseCommunityComponent.showPopup();
    },
    submitChooseCommunity(e) {
        let currentChoosedCommunityList = e.detail.value;
        console.log(e);
        this.setData({
            choosedCommunityList: currentChoosedCommunityList,
        })
    },
    /** END 组件事件 - 选择社区*/

    /********** 企业注册提交审核 **********/
    //企业注册提交审核
    async bindtapSubmit() {
        let companyName = this.data.companyName;
        let juridicalPhone = this.data.recruiterTelephone;
        let juridicalPerson = this.data.juridicalPerson;
        // 企业名称检测
        if (this.data.choosedCommunityList.length == 0) {
            wx.showModal({
                title: '提示',
                content: '必须选择所属社区',
            })
            return;
        }
        if (string_util.isEmpty(companyName)) {
            wx.showModal({
                title: '提示',
                content: '必须填写企业名称',
            })
            return;
        }
        if (string_util.isEmpty(juridicalPhone)) {
            wx.showModal({
                title: '提示',
                content: '请获取当前招聘人联系电话',
            })
            return;
        }
        if (string_util.isEmpty(juridicalPerson)) {
            wx.showModal({
                title: '提示',
                content: '请填写招聘联系人',
            })
            return;
        }
        // 提交开始

        try {
            Loading.begin();

            let recruitCompany = {
                companyName: companyName,
                communityUuid: this.data.choosedCommunityList.length == 0 ? '' : this.data.choosedCommunityList[0].communityUuid,
                address: this.data.companyAddress,
                lon: this.data.longitude,
                lat: this.data.latitude,
                licenseId: this.data.licenseId,
                recruiterOpenid: this.data.recruiterOpenid,
                companyPerson: this.data.companyPerson,
                phone: this.data.companyPhone,
                juridicalPhone: juridicalPhone,
                juridicalPerson: juridicalPerson,
            }
            // 插入的同时已更新对应recruiter信息
            let companyUuid = await recruitCompanyService.insertByEntity(this.data.recruiterOpenid, recruitCompany)
            companyUuid = companyUuid.data;
            // 意向添加到 company_for_category
            if (this.data.jobCategoryList.findIndex(v => { return v.checked }) != -1) {
                let categoryList = [];
                console.log(this.data.jobCategoryList)
                this.data.jobCategoryList.forEach(v => {
                    if (v.checked == true) {
                        categoryList.push({
                            companyUuid: companyUuid,
                            categoryUuid: v.categoryUuid,
                            categoryName: v.categoryName,
                        })
                    }
                });
                console.log(categoryList);
                await companyForCategoryService.insertByEntityList(companyUuid, categoryList);
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end()
        }
        // 页面跳转
        wx.redirectTo({
            url: '/pages/fbjob/fbjob',
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {

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
        // 
        await app.getAppInitReady();
        let openid = wx.getStorageSync('openid');
        this.setData({
            recruiterOpenid: openid
        });
        // 根据全局获取默认社区
        let defaultCommunityUuid = globalService.getGlobalCommunityUuid();
        if(string_util.isNotEmpty(defaultCommunityUuid)){
            let defaultChoosedCommunity =  (await communityInformationService.loadEntityById(defaultCommunityUuid)).data;
            this.setData({
                choosedCommunityList: [{
                    communityUuid :defaultChoosedCommunity.id,
                    communityName :defaultChoosedCommunity.communityName,
                }]
            })
        }
        console.log(this.data.choosedCommunityList)
        


        var qqmapsdk = new QQMapWX({
            key: 'Z3WBZ-TX76I-CKXGO-5GWTU-CSSK3-7LBFO' // 必填
        });

        // 获取位置信息  默认位置
        wx.getLocation({
            type: 'gcj02',
            success(res) {
                console.log(res)
                const latitude = res.latitude
                const longitude = res.longitude
                wx.request({
                    url: 'https://apis.map.qq.com/ws/geocoder/v1/?location=' + latitude + ',' + longitude + '&key=Z3WBZ-TX76I-CKXGO-5GWTU-CSSK3-7LBFO&get_poi=0',
                    method: 'GET',
                    success(res) {
                        let tempData = res.data.result;
                        that.setData({
                            positionData: tempData.address + ' ' + tempData.formatted_addresses.recommend,
                            companyAddress: tempData.address + ' ' + tempData.formatted_addresses.recommend,
                            longitude: tempData.location.lng,
                            latitude: tempData.location.lat,
                        })
                    }
                })
            }
        });
    },

    /**
     * 组件应都写在onready 页面渲染交互完成
     */
    onReady: function () {
        this.chooseCommunityComponent = this.selectComponent("#choose-community");
        this.chooseJobCategoryComponent = this.selectComponent("#choose-job-category");
        // 初始化 调用选择职位保存事件 同步页面数据
        this.chooseJobCategoryComponent.bindtapChoosedJobCategorySubmit();
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

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