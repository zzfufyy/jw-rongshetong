// pages/mesegsubmit/mesegsubmit.js
const { Completer } = require("../../utils/function_util");
const Loading = require('../../utils/loading_util');
const GLOBAL_CONSTANT = require('../../common/globalConstant');

const buildingResidentService = require('../../common/buildingResidentService');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        residentUuid: '',
        buidlingUuid: '',
        communityUuid: '',
    },
    state: {
        residentUuidCompleter: new Completer(),
    },
    bindtapReturnCommunityHomepage() {
        let firstStep = (wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY) == 'grid') ? 2 : 3;
        wx.navigateBack({
            delta: firstStep + 2,
        })
    },
    bindtapContinueRegister() {
        wx.navigateBack({
            delta: 2
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.state.residentUuidCompleter = new Completer();
        let residentUuid = options.residentUuid;
        this.state.residentUuidCompleter.resolve(residentUuid);
    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow: async function () {
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }

    },
    loadContent: async function () {
        let residentUuid = await this.state.residentUuidCompleter.promise;
        let residentData = await buildingResidentService.loadEntityById(residentUuid);
        residentData = residentData.data;
        this.setData({
            buidlingUuid: residentData.buidlingUuid,
            communityUuid: residentData.communityUuid,
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