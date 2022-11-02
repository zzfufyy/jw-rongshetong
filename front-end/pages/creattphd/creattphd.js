// pages/creattphd/creattphd.js
const Loading = require('../../utils/loading_util');
const { Completer } = require('../../utils/function_util');
const creattphdEx = require('./creattphdEx');
const formInformationService = require('../../common/formInformationService');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        communityUuid: '',
        tablist: [
            { tabname: '我的创建', id: 0 },
            { tabname: '已下架', id: 1 },
        ],
        tabid: 0,
        currentTab: '0',
        currentId: '0',
        userOpenid: '',
        formInformationNormalList: [
            // { formUuid: '', formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, status: 1, },
            // { formUuid: '', formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, status: 0, },
            // { formUuid: '', formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, status: 1, },
            // { formUuid: '', formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, status: 0, },
        ],
        hiddenShare: true,

        formInformationUnpublishList: [
            // { formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, continueedit: true },
            // { formTitle: '标题', beginTime: '2022-05-30', completedCount: 0, continueedit: false },
        ],
    },
    /** BEGIN 页面事件 */
    // button - 发布 Noraml列表 
    bindtapPublishForNormal: async function(e) {
        let currentIndex = e.currentTarget.dataset.index;
        try {
            Loading.begin();
            await formInformationService.publish(this.data.formInformationNormalList[currentIndex].formUuid);
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
        this.setData({
            ['formInformationNormalList[' + currentIndex + '].status']: 1,
        });
    },
    // button - 下架
    bindtapUnpublish: async function(e) {
        let currentIndex = e.currentTarget.dataset.index;
        try {
            Loading.begin();
            await formInformationService.unpublish(this.data.formInformationNormalList[currentIndex].formUuid);
            // 重置加载页面内容
            await this._reloadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
    },
    // button - 上架 unpublish列表 
    async bindtapPublishForUnpublish(e) {
        let currentIndex = e.currentTarget.dataset.index;
        try {
            Loading.begin();
            await formInformationService.publish(this.data.formInformationUnpublishList[currentIndex].formUuid);
            // 重置加载页面内容
            await this._reloadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
    },
    // button - 继续编辑
    bindtapContinueEdit(e) {
        console.log(e)
        let currentIndex = e.currentTarget.dataset.index;
        let currentNormalList = this.data.formInformationNormalList;
        let currentFormUuid = currentNormalList[currentIndex].formUuid;
        let communityUuid = this.data.communityUuid;
        wx.navigateTo({
            url: `/pages/creattp/creattp?formUuid=${currentFormUuid}&communityUuid=${communityUuid}`,
        })
    },
    // button - 分享 - 二维码
    bindtapGoQrcode() {
        wx.navigateTo({
            url: '/pages/tpewm/tpewm',
        })
    },
    bindtapGoDataDetail: function(){
        console.log(e);
        console.log(this.data)
    },


    // button - 创建投票
    bindtapCreateVote() {
        wx.navigateTo({
            url: '/pages/creattp/creattp?communityUuid=' + this.data.communityUuid,
        })
    },
    // button - 点击分享
    bindtapShowShare() {
        let hiddenShare = !this.data.hiddenShare
        this.setData({
            hiddenShare: hiddenShare
        })
    },
    // button - 数据统计
    bindtapGoStatisticPublish: function(e) {
        console.log(e);
        console.log(this.data.formInformationNormalList);
        let formUuid = this.data.formInformationNormalList[e.currentTarget.dataset.index].formUuid;
        wx.navigateTo({
            url: `/pages/datdpage/datdpage?formUuid=${formUuid}`,
        })
    },
    bindtapGoStatisticUnpublish: function(e) {
        console.log(e);
        console.log(this.data.formInformationUnpublishList);
        let formUuid = this.data.formInformationUnpublishList[e.currentTarget.dataset.index].formUuid;
        wx.navigateTo({
            url: `/pages/datdpage/datdpage?formUuid=${formUuid}`,
        })
    },
    // tab事件
    bindtapChangeTab: function(e) {
        console.log(e)
        let id = e.currentTarget.dataset.id
        this.setData({
            tabid: id
        })
    },
    bindchangeSwitchTab: function (e) {
        // console.log(e) 
        var that = this;
        that.setData({
            tabid: e.detail.current,
            currentId: e.detail.current
        });
    },
    /** END 页面事件 */

    /** BEGIN 页面外部调用方法*/
    callReloadContent: async function() {
        await this._reloadContent();
    },
    /** END 页面外部调用方法 */

    /** BEGIN 页面内部方法 */
    _reloadContent: async function() {
        await this.clearAllContent();
        await this.loadAllContent();
    },
    /** END 页面内部方法 */
    
    /**
     * 生命周期函数--监听页面加载
     */
    communityUuidCompleter: new Completer(),
    onLoad: async function (options) {
        let that = this
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight,
                });
            }
        })
        this.communityUuidCompleter = new Completer();
        let communityUuid = options.communityUuid;
        this.setData({
            communityUuid: communityUuid,
        })
        this.communityUuidCompleter.resolve(communityUuid);

        await app.getOpenidReady();
        let userOpenid = app.getOpenid();
        this.setData({
            userOpenid: userOpenid,
        })
        // 加载数据
        try {
            Loading.begin();
            await this.clearAllContent();
            await this.loadAllContent();
        } catch (e) {
            console.error(e);
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
    onShow: async function () {
        // // 重读页面信息
        // try{
        //     Loading.begin();
        //     await this.clearAllContent();
        //     await this.loadAllContent();
        // }catch(e){
        //     console.error(e);
        // }finally{
        //     Loading.end();
        // }
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
    onShareAppMessage: function (res) {
        if (res.from === 'button') {
            // 来自页面内转发按钮
            title = "这个是页面自定义的分享事件~";
            // imageUrl='***.png';
        }
        if (res.from === 'menu') {
            title = "这个是页面右上角的分享事件~";
            // imageUrl='***.png';
        }
    },
    onShareTimeline(res) {
        console.log(res)
    },
    // 加载分页
    ...creattphdEx.createPageMethods(),
})