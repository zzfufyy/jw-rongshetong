const Loading = require("../../utils/loading_util");

const {
    Completer
} = require("../../utils/function_util");
const pageCompany = require("./pageCompany");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        searchCompanyName: '',
        buildingUuid: '',
        companyList: [
            // {
            // companyName: "竟网科技有限公司",
            // address: "长沙市芙蓉区",
            // addressDetail: "国际公寓A座1917",
            // jobNameList:'',
            // }
        ],
    },
    /** 页面事件 */
    bindinputSearchCompanyName: function (e) {
        console.log(e.detail.value);
        this.setData({
            searchCompanyName: e.detail.value,
        })
    },
    bindconfirmSearchCompanyName: async function () {
        console.log(this.data.searchCompanyName);
        await this._loadContent();
    },
    bindscrolltolowerCompanyList: async function () {
        await this._loadContent();
    },
    bindtapGoLinkCompany: function (e) {
        let companyUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/commessxq/commessxq?companyUuid=${companyUuid}`,
        });
    },

    /** 内部方法 */
    _setSystemInfo: function () {
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight - 60,
        });
    },

    _loadContent: async function () {
        try {
            Loading.begin();
            await this.loadContentCompanyList();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end()
        }
        console.log(this.data.companyList);
    },



    /**
     * 生命周期函数--监听页面加载
     */
    buildingUuidCompleter: new Completer(),
    onLoad: async function (options) {
        this.buildingUuidCompleter = new Completer();
        this._setSystemInfo();
        let buildingUuid = options.buildingUuid;
        let buildingName = options.buildingName;
        console.info('buildingName', buildingName);
        this.setData({
            buildingUuid: buildingUuid,
            buildingName: buildingName,
        });
        this.buildingUuidCompleter.resolve(buildingUuid);
        wx.setNavigationBarTitle({
            title: buildingName,
        });
        await this.clearContentCompanyList();
        await this.loadContentCompanyList();
        // 加载信息


    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: async function () {},
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: async function () {

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
    onPullDownRefresh: function () {},

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    ...pageCompany.createPageMethods(),
})