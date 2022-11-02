const img_util = require("../../utils/img_util");

Page({

    /**
     * 页面的初始数据
     */
    data: {
        backgroundImg: img_util.handleNormalPortraitPath("/images/img/social/social-cloud-recruitment-background.png"),
        titleBackgroundImg: img_util.handleNormalPortraitPath("/images/img/social/social-cloud-recruitment-title-background.svg"),
        tabList: [{
            imgSrc: "../../img/social-cloud-recruitment/company_recruitment_icon.svg",
            tagName: "企业云招聘",
            checked: true,
        },
        {
            imgSrc: "../../img/social-cloud-recruitment/street_recruitment_icon.svg",
            tagName: "街道云招聘",
            checked: false,
        },
        ],
        tabIndex: 0,
        ADList: [{
            title: "“中国创翼”创新创业",
            videoSrc: img_util.handleNormalPortraitPath("/images/video/ad_start_up_business.m4v"),
        }, {
            title: "“三高四新”技能培训",
            videoSrc: img_util.handleNormalPortraitPath("/images/video/ad_3high_4new.m4v"),
        }],
        companyList: [{
            companyUuid: "b8b06f3e-3ed2-11ed-b3bb-525401030264",
            companyName: "湖南惠农科技有限公司",
            videoSrc: img_util.handleNormalPortraitPath("/images/video/company1.mp4"),
        },
            // {
            //     companyUuid: "",
            //     companyName: "长沙市测试有限公司",
            //     videoSrc: img_util.handleNormalPortraitPath("/images/video/test.mp4"),
            // },
        ],
        streetList: [{
            streetName: "湘湖街道",
            companyList: [{
                companyUuid: "b8b06f3e-3ed2-11ed-b3bb-525401030264",
                companyName: "湖南惠农科技有限公司",
                videoSrc: img_util.handleNormalPortraitPath("/images/video/company1.mp4"),
            },
            ]
        },
        {
            streetName: "定王台街道",
            companyList: [
            ]
        }
        ]
    },
    /** 页面事件 */
    bindtapChangeTab: function (e) {
        let currentIndex = e.currentTarget.dataset.index;
        let currentTabList = this.data.tabList;
        console.log(currentIndex);
        console.info(currentTabList.findIndex(v => v.checked));
        if (currentIndex == currentTabList.findIndex(v => v.checked)) {
            return;
        }
        let changedTabIndex = 0;
        let changedTabList = currentTabList.map((v, i) => {
            v.checked = !v.checked;
            if (v.checked) {
                changedTabIndex = i;
            }
            return v;
        });
        this.setData({
            tabList: changedTabList,
            tabIndex: changedTabIndex,
        })
    },
    bindtapMoreCompanyInfo: function (e) {
        let companyUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/commessxq/commessxq?companyUuid=${companyUuid}`,
        });
    },
    /** 内部方法 */
    _setSystemInfo: function () {
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight,
        });
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.info(this.data.backgroundImg);
        this._setSystemInfo();
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

    }
})