const CONSTANT = require("../../common/constant");
const Loading = require("../../utils/loading_util");

const pageNews = require("./pageNews");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tabList: [{
            text: "人才",
            tagName: "socialTalentNews",
            articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialTalentNews.id,
            checked: false,
        }, {
            text: "社保",
            tagName: "socialSecurityNews",
            articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialSecurityNews.id,
            checked: true,
        }, {
            text: "就业",
            tagName: "socialEmploymentNews",
            articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialEmploymentNews.id,
            checked: false,
        }, {
            text: "人事",
            tagName: "socialPersonnelNews",
            articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialPersonnelNews.id,
            checked: false,
        }, {
            text: "监察",
            tagName: "socialMonitorNews",
            articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialMonitorNews.id,
            checked: false,
        }
        ],

        // 查询类型
        newsListArticleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialSecurityNews.id,
        newsList: [
            // {
            //     newsUuid: '',
            //     articleTitle: '春风送温暖 就业送真情',
            //     articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知',
            //     articleReleaseTime: '3月02日',
            //     countView: 123,
            // },
            // {
            //     newsUuid: '',
            //     articleTitle: '春风送温暖 就业送真情',
            //     articleIntroduction: '【湖南省】关于印发《湖南省“十四五”金融业发展规划》的通知湖南省地方金融监督管理局关于印发《湖南省“十四五”金融业发展规划》的通知',
            //     articleReleaseTime: '3月02日',
            //     countView: 0,
            // },
        ]
    },

    /**
     * 页面事件
     */
    bindtapChooseTab: async function (e) {
        let currentIndex = e.currentTarget.dataset.index;
        if (currentIndex === this.data.tabList.findIndex(v => {
            return v.checked === true;
        })) {
            return;
        }

        let currentTabList = this.data.tabList.map((v, i) => {
            v.checked = currentIndex == i ? true : false;
            return v;
        });
        this.setData({
            tabList: currentTabList,
        });
        let currentItem = currentTabList.find(v => v.checked);
        // news content switch
        this.setData({
            newsListArticleType: currentItem.articleType,
        });
        // reload~

        await this._reloadContent();

    },
    bindscrolltolowerLoadMore: async function () {
        await this._loadContent();
    },
    bindtapGoLinkNewsDetail: function (e) {
        let newsUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '/pages/zxxq/zxxq?newsUuid=' + newsUuid,
        })
    },
    /**
     * 内部方法
     */
    _setSystemInfo: function () {
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight - 86,
        });
    },

    _loadContent: async function () {
        try {
            Loading.begin();
            await this.loadContentNewsList();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },
    _reloadContent: async function () {
        try {
            Loading.begin();
            await this.clearContentNewsList();
            await this.loadContentNewsList();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        this._setSystemInfo();
        await this._loadContent();
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

    ...pageNews.createPageMethods(),
})