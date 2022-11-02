// pages/mesegsubmit/mesegsubmit.js
const CONSTANT = require('../../common/constant')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        optionWidth: 0,
        optionList: [
            {
                iconSrc: '/img/icon/btn_single_select.png',
                iconTitle: '单选题',
                componentType: CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.SINGLE_SELECT,
            }, {
                iconSrc: '/img/icon/btn_multiple_select.png',
                iconTitle: '多选题',
                componentType: CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.MULTIPLE_SELECT,
            }, {
                iconSrc: '/img/icon/btn_completion.png',
                iconTitle: '填空题',
                componentType: CONSTANT.FORM_COMPONENT_TYPE.COMPLETION,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.COMPLETION,
            }, {
                iconSrc: '/img/icon/btn_upload.png',
                iconTitle: '图片上传',
                componentType: CONSTANT.FORM_COMPONENT_TYPE.IMG_UPLOAD,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.IMG_UPLOAD,
            }, {
                iconSrc: '/img/icon/btn_sign.png',
                iconTitle: '签名',
                componentType: CONSTANT.FORM_COMPONENT_TYPE.SIGN,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.SIGN,
            }
        ]
    },
    bindtapChooseType(e) {
        console.log(e);
        let pages = getCurrentPages();
        // 获取上一个页面
        let prevPage = pages[pages.length - 2]
        let currentIndex = e.currentTarget.dataset.index;
        let currentItem = this.data.optionList[currentIndex];
        // 调用上一个页面暴露方法  添加题目
        prevPage.callAddSubject(currentItem.componentType, currentItem.subjectType);
        wx.navigateBack({
            delta: 0,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {


        // 获取屏幕方块宽度

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