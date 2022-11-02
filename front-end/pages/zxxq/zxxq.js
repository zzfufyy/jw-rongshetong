// pages/zxxq/zxxq.js
const informationNewsService = require('../../common/informationNewsService');
const date_util = require('../../utils/date_util');
const img_util = require('../../utils/img_util');
const string_util = require('../../utils/string_util');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        richText: '<h1>Hello world!</h1>',
        // title:'春风送温暖 就业送真情',
        // ly:'岳麓区就业服务中心',
        // fbsj:'3月02日',
        // htmlSnip:'<p>日前，人力资源社会保障部联合工业和信息化部、民政部、交通运输部、国家乡村振兴局、全国总工会、共青团中央、全国妇联，在全国范围内部署开展2022年春风行动。此次行动以“春风送温暖 就业送真情”为主题，于1月21日至3月31日期间持续开展，集中为返乡返岗农民工、因疫情滞留的务工人员和脱贫人口、低收入人口等重点帮扶对象，以及有用工需求的各类用人单位提供就业服务，实现“留岗有关怀、就业有帮扶、用工有支持”，引导有序外出务工。	就业送真情”为主题，于1月21日至3月31日期间持续开展，集中为返乡返岗农民工、因疫情滞留的务工人员和脱贫人口、低收入人口等重点帮扶对象，以及有用工需求的各类用人单位提供就业服务，实现“留岗有关怀、就业有帮扶、用工有支持”，引导有序外出务工。活动期间，各地将引导有序外出务工，通过劳务协作和劳务品牌带动帮助劳动者就业增收，根据实际<p>',
        // times:'233'
        fileurl: '',
        fileList: [],

    },
    //分享按钮
    share() {

    },
    download:function (e) {
        let _that = this;
        let fileurl = e.currentTarget.dataset.url;
        console.log(fileurl);
        wx.showModal({
            title: '提示',
            content: '是否确认下载附件',
            success(res) {
                if (res.confirm) {
                    wx.downloadFile({
                        url: fileurl,
                        success: function (res) {
                            var filePath = res.tempFilePath;
                            console.log(res)
                            //页面显示加载动画
                            wx.openDocument({
                                filePath: filePath,
                                showMenu: true,
                                success: function (res) {
                                    _that.setData({
                                        // loadingHidden: true
                                    })
                                    console.log('打开文档成功')
                                }
                            })
                        }
                    })
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let that = this
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        // 获取newsUuid
        let newsUuid = this.options.newsUuid;
        // 测试
        console.log(newsUuid);

        await informationNewsService.increaseCountView(newsUuid);
        let newsData = await informationNewsService.loadEntityById(newsUuid);
        newsData = newsData.data
        that.setData({
            newUuid: newsData.id,
            title: newsData.articleTitle,
            ly: newsData.articleAuthor,
            fbsj: date_util.dateToCN(newsData.articleReleaseTime),
            htmlSnip: newsData.articleContent,
            times: newsData.countView,
        })
        let filePathList = string_util.splitBySemiColon(newsData.attachmentPath);
        let fileNameList = string_util.splitBySemiColon(newsData.ext1);
        let fileList = filePathList.map((v,i)=>{
            return {
                fileUrl: img_util.handleDefaultPortraitPath(v),
                fileName: fileNameList[i]? fileNameList[i]: "未知名称文件",
            }
        });
        this.setData({
            fileList: fileList,
        })
        wx.getSystemInfo({
            success: function (res) {
                console.log(res)
                let oht = res.windowHeight - 52 - 64 - 29 - 52 + 18;
                that.setData({
                    ht: oht
                })
            }
        })
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
    }
})