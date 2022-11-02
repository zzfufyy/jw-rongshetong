const img_util = require("../../utils/img_util");
const prompt_util = require("../../utils/prompt_util");
const app = getApp();

Page({
    /**
     * 页面的初始数据
     */
    data: {
        bannerImgSrc: img_util.handleNormalPortraitPath("/images/img/social/social-security-service-banner.png"),
        infoText: '测试\nces',

        tabList1: [{
                imgSrc: "../../img/social-security-service/tab_security_search.svg",
                tagName: "社保查询",
            },
            {
                imgSrc: "../../img/social-security-service/tab_security_card.svg",
                tagName: "社保卡申领",
            },
            {
                imgSrc: "../../img/social-security-service/tab_pension_authentication.svg",
                tagName: "养老金领取资格认证",
            },
            {
                imgSrc: "../../img/social-security-service/tab_security_payment.svg",
                tagName: "灵活就业社保缴费",
            },
            {
                imgSrc: "../../img/social-security-service/tab_security_register.svg",
                tagName: "灵活就业参保登记",
            },
            {
                imgSrc: "../../img/social-security-service/tab_pension_insurance.svg",
                tagName: "养老保险关系转移",
            },
        ],
        tabList2: [{
                imgSrc: "../../img/social-security-service/tab2_1.svg",
                tagName: "单位人员异动",
            },
            {
                imgSrc: "../../img/social-security-service/tab2_2.svg",
                tagName: "单位险种补齐",
            },
            {
                imgSrc: "../../img/social-security-service/tab2_3.svg",
                tagName: "数字证书管理",
            },
            {
                imgSrc: "../../img/social-security-service/tab2_4.svg",
                tagName: "新单位参保登记",
            },
        ],
        tabList3: [{
                imgSrc: "../../img/social-security-service/tab3_1.svg",
                tagName: "保险金申领",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_2.svg",
                tagName: "补助金申领",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_3.svg",
                tagName: "技能补助",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_4.svg",
                tagName: "培训补助",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_5.svg",
                tagName: "生育补助",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_6.svg",
                tagName: "工伤查询",
            },
            {
                imgSrc: "../../img/social-security-service/tab3_7.svg",
                tagName: "劳动能力鉴定",
            },
        ]
    },


    bindtapTemp: function () {
        // wx.navigateTo({
        //   url: '/pages/zxxq/zxxq?newsUuid=1573152999346679810',
        // });
        prompt_util.showToastWaitForOpen();
        return;
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

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