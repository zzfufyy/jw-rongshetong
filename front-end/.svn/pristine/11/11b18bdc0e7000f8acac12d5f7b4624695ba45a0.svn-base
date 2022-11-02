// pages/news/news.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');

const newsEx = require('./newsEx');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 数据加载 锁
        searchTextTemp: '',
        searchText: '',
        newsList: [
            // { newsUuid:'',articleTitle: '春风送温暖 就业送真情', articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知', articleAuthor: '岳麓区就业服务中心', articleReleaseTime: '3月02日', articlePortraitPath: 'http://frqryb.oss-accelerate.aliyuncs.com/headphoto/16297916583243346b1330ea-9a71-4ea4-85d0-02c7556c3b40.jpeg' },
            // { articleTitle: '春风送温暖 就业送真情', articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知', articleAuthor: '岳麓区就业服务中心', articleReleaseTime: '3月02日', articlePortraitPath: 'http://frqryb.oss-accelerate.aliyuncs.com/headphoto/16297916583243346b1330ea-9a71-4ea4-85d0-02c7556c3b40.jpeg' },
        ]
    },
    // loading锁
    lock: {},

    bintapNewsDetail(e) {
        console.log(e);
        let newsUuid = e.currentTarget.id;
        wx.navigateTo({
            url: '/pages/zxxq/zxxq?newsUuid=' + newsUuid,
        })
    },
    binderrorArticlePortraitPath(e) {
        console.error(e);
        let img = 'newsList[' + e.currentTarget.dataset.index + '].articlePortraitPath';
        this.setData({
            [img]: CONSTANT.STATIC_IMG_URL.portrait_news,
        })
    },
    // 搜索栏 输入
    bindinputSearchNews(e) {
        this.setData({
            searchTextTemp: e.detail.value,
        })

    },
    // 搜索栏 回车
    async bindconfirmSearchNews(e) {
        this.setData({
            searchText: this.data.searchTextTemp,
        })
        await this.reloadContent();
    },
    // 搜索栏 取消
    async bindtapCancelSearch(e) {
        this.setData({
            searchTextTemp: '',
            searchText: '',
        })
        await this.reloadContent();
    },
    async reloadContent() {
        if (!this.lock.isLock) {
            try {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            } catch (e) {
                console.error(e)
            } finally {
                Loading.end(this.lock);
            }
        }
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        // 初始化loading锁
        this.lock = new Loading.Lock();

        let that = this;
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight,
                });
            }
        })
        await app.getOpenidReady();
        await this.reloadContent();

    },
    bindscrolltolowerReachBottom: async function () {
        if (!this.lock.isLock) {
            try {
                Loading.begin(this.lock);
                await this.loadContent();
            } catch (e) {
                console.error(e)
            } finally {
                Loading.end(this.lock);
            }
        }
    },

    /**
         * 页面上拉触底事件的处理函数
         */
    onReachBottom: async function () {

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
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    // 加载分页
    ...newsEx.createPageMethods(),
})