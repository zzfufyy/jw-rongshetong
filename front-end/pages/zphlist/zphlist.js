// pages/zphlist/zphlist.js
const Loading = require('../../utils/loading_util');
const prompt_util = require('../../utils/prompt_util');
// 加载附加模块
const zphlistEx = require('./zphlistEx');
const app = getApp();

Page({
    /**
     * 页面的初始数据
     */
    data: {
        searchTextTemp: '',
        searchText: '',
        fairList: [
            // {
            //     fairUuid: '',
            //     fairTitle: '',
            //     fairContent: '',
            //     fairBeginTime: '',
            //     fairPortraitPath: '',
            // },
            // { fairPortraitPath: '/img/gxbyzph.png', fairTitle: '2022年长沙市“高校毕业生”就业服务季专场招聘会', fairContent: '为深入贯彻党的十九届六中全会，经济工作会议和省弟十二次党代会精神，认真落实政府工作报告部署要求认真落实政府工作报告部署要求', fairBeginTime: '2022-05 20 00:00:00' },
            // { fairPortraitPath: '/img/gxbyzph2.png', fairTitle: '2022年长沙市“高校毕业生”就业服务季专场招聘会', fairContent: '为深入贯彻党的十九届六中全会，经济工作会议和省弟十二次党代会精神，认真落实政府工作报告部署要求认真落实政府工作报告部署要求', fairBeginTime: '2022-05 20 00:00:00' }
        ]
    },
    lock: new Loading.Lock(),
    bindtapLinkToFair(e) {
        let currentIndex = e.currentTarget.dataset.index;
        let fairUuid = this.data.fairList[currentIndex].fairUuid
        console.debug(fairUuid);
        wx.navigateTo({
            url: '/pages/zpzhuanc/zpzhuanc?fairUuid=' + fairUuid,
        })
    },
    bindinputSearchFair(e){
        this.setData({
            searchTextTemp: e.detail.value,
        });
    },
    async bindconfirmSearchFair(e){
        this.setData({
            searchText: this.data.searchTextTemp,
        })
        await this.reloadContent();
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        this.lock = new Loading.Lock();
        await app.getOpenidReady();
        try {
            if(!this.lock.isLock){
                Loading.begin(this.lock);
                await this.reloadContent();
            }
        } catch (e) {
            console.error(e);
            prompt_util.loadingFailPrompt();            
        } finally{
            Loading.end(this.lock);
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
    onShareAppMessage: function () {

    },
    // 加载分页插件
    ...zphlistEx.createPageMethods(),
})