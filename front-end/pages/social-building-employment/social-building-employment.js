const pageBuilding = require("./pageBuilding");
const Loading = require("../../utils/loading_util");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        buildingList: [],
        choosedStreetList: [],
        districtName: '',
        streetName: '',
    },

    /** 页面事件 */
    bindscrolltolowerBuildingList: async function (e) {
        await this._loadContent();
    },
    bindtapGoLinkBuilding: function (e) {
        console.log(e);
        let buildingUuid = e.currentTarget.dataset.id;
        let buildingName = e.currentTarget.dataset.name;
        console.log(buildingName);
        wx.navigateTo({
            url: `/pages/social-building-company/social-building-company?buildingUuid=${buildingUuid}&buildingName=${buildingName}`,
        });
    },

    /** 组件方法 */
    bindtapChooseStreet(e) {
        this.chooseStreetComponent.showPopup();
    },
    submitChooseStreet: async function (e) {
        let choosedStreetList = e.detail.value;
        if (choosedStreetList.length === 0) {
            return;
        }
        this.setData({
            choosedStreetList: choosedStreetList,
        });
        if (choosedStreetList.length != 0) {
            this.setData({
                districtName: choosedStreetList[0].districtName,
                streetName: choosedStreetList[0].streetName,
            })
        }
        await this._reloadContent();
        console.info(this.data.choosedStreetList)
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
            await this.loadContentBuildingList();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },
    _reloadContent: async function () {
        try {
            Loading.begin();
            await this.clearContentBuildingList();
            await this.loadContentBuildingList();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {

        this._setSystemInfo();
        await this._reloadContent();
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () { },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: async function () { },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: async function () {
        // 加载社区组件
        this.chooseStreetComponent = this.selectComponent("#choose-street");
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
    onPullDownRefresh: function () { },

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
    ...pageBuilding.createPageMethods(),
})