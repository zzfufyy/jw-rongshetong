// pages/tpactive/tpactive.js
const Loading = require('../../utils/loading_util');
const {
    Completer
} = require('../../utils/function_util');
const tpactiveEx = require('./tpactiveEx');
const formInformationService = require('../../common/formInformationService');
const userForFormService = require('../../common/userForFormService');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        communityUuid: '',
        formInformationList: [
            // { formUuid: '', formTitle: '关于东湖社区的某项活动投票征集中', completedCount: '100',beginTime:'', endTime: '2022-05-30', status：1,isActive: true },

        ]
    },
    bindtapGoVote: async function (e) {
        // TODO 判断是否已经投票
        let formUuid = this.data.formInformationList[e.currentTarget.dataset.index].formUuid
        let count = (await userForFormService.countByCondition({
            userOpenid: app.getOpenid(),
            formUuid: formUuid
        })).data;
        if (count > 0) {
            wx.showToast({
                title: '您已投过票',
                icon: 'error',
                duration: 1200,
                mask: true,
            });
            return;
        }
        wx.navigateTo({
            url: `/pages/tp/tp?formUuid=${formUuid}`,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    communityUuidCompleter: new Completer(),
    onLoad: async function (options) {
        await app.getOpenidReady();
        this.communityUuidCompleter = new Completer();
        let communityUuid = options.communityUuid;
        console.info(`Now communityUuid is ${communityUuid}`);
        this.setData({
            communityUuid: communityUuid,
        })
        this.communityUuidCompleter.resolve(communityUuid);
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

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
    onShareAppMessage: function () {},
    ...tpactiveEx.createPageMethods()
})