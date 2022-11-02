// pages/news/news.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const {
    Completer
} = require('../../utils/function_util');

const newsEx = require("./news-article-type-ex");
const string_util = require('../../utils/string_util');


const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        articleType: 0,
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
    bindscrolltolowerReachBottom: async function (e) {
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },

    /**
     * 内部方法
     */
    _setSystemInfo: function(){
        console.info(2222);
        let that = this;
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight,
        })
        
    },


    /**
     * 生命周期函数--监听页面加载
     */
    articleTypeCompleter: new Completer(),
    onLoad: async function (options) {
        this._setSystemInfo();
        // 初始化
        this.articleTypeCompleter = new Completer();
        if(string_util.isNotEmpty(options.navigationBarTitle)){
            wx.setNavigationBarTitle({
                title: options.navigationBarTitle,
              });
        }
        // 加载传入的社区id
        let articleType = options.articleType;
        this.setData({
            articleType: articleType,
        })
        console.info(`page onload by param articleType: ${articleType}`);
        this.articleTypeCompleter.resolve(articleType);
        try {
            Loading.begin();
            await this.clearContent();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
        console.info(this.data.winHeight);
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {},

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