const img_util = require("../../utils/img_util");
const communityInformationService = require("../../common/communityInformationService");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        districtName: "芙蓉区",
        streetName: "定王台街道",
        companyList: [{
                companyUuid: "475e63c7-c085-11ec-a9e3-e86a648eddb5",
                companyName: "长沙市竟网信息科技公司",
                videoSrc: img_util.handleNormalPortraitPath("/images/video/test.mp4"),
            },
            {
                companyUuid: "",
                companyName: "长沙市测试有限公司",
                videoSrc: img_util.handleNormalPortraitPath("/images/video/test.mp4"),
            },
        ]
    },
    /** 页面事件 */
    bindtapGoLinkCompanyInfo: function(e){
        let companyUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/commessxq/commessxq?companyUuid=${companyUuid}`,
        });
    },
    bindchangeChooseStreet: function (e) {
        let index = e.detail.value;
        this.setData({
            streetName: this.data.streetNameList[index],
        })
    },

    /** 内部方法 */
    _setSystemInfo: function () {
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight,
        });
    },
    _loadContent: async function(){
        let streetNameList = (await communityInformationService.listStreetNameByDistrictName(this.data.districtName)).data;
        console.info(streetNameList);
        this.setData({
            streetNameList: streetNameList
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        this._setSystemInfo();
        let districtName = "芙蓉区";
        this.setData({
            districtName: districtName,
        });
        await this._loadContent();
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {
        
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