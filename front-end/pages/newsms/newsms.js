// pages/news/news.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const { Completer } = require('../../utils/function_util');

const newsEx = require('./newsmsEx');
const string_util = require('../../utils/string_util');


const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        communityUuid: '',
        communityType: CONSTANT.COMMUNITY_TYPE.COMMUNITY, // 默认社区分页模式
        newsList: [
            // { newsUuid:'',articleTitle: '春风送温暖 就业送真情', articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知', articleAuthor: '岳麓区就业服务中心', articleReleaseTime: '3月02日', articlePortraitPath: 'http://frqryb.oss-accelerate.aliyuncs.com/headphoto/16297916583243346b1330ea-9a71-4ea4-85d0-02c7556c3b40.jpeg' },
            // { articleTitle: '春风送温暖 就业送真情', articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知', articleAuthor: '岳麓区就业服务中心', articleReleaseTime: '3月02日', articlePortraitPath: 'http://frqryb.oss-accelerate.aliyuncs.com/headphoto/16297916583243346b1330ea-9a71-4ea4-85d0-02c7556c3b40.jpeg' },
        ]
    },

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
    /**
     * 生命周期函数--监听页面加载
     */
    communityUuidCompleter: new Completer(),
    communityTypeCompleter: new Completer(),
    onLoad: async function (options) {
        // 初始化
        this.communityUuidCompleter = new Completer();
        this.communityTypeCompleter = new Completer();
        // 加载传入的社区id
        let communityUuid = options.communityUuid;
        this.setData({
            communityUuid: communityUuid,
        })
        console.info(`page onload by param communityUuid: ${communityUuid}`);
        this.communityUuidCompleter.resolve(communityUuid);
        // 加载传入的社区类型
        let communityType = string_util.isNotEmpty(options.communityType) ? options.communityType : 0;
        this.setData({
            communityType: communityType,
        })
        this.communityTypeCompleter.resolve(communityType);
        console.info(`page onload by param communityType: ${communityType}`);
        try {
            Loading.begin();
            await this.clearContent();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }

    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: async function () {
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
    // 加载分页
    ...newsEx.createPageMethods(),
})