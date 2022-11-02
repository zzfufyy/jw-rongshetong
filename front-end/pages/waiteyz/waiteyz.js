// pages/waiteyz/waiteyz.js
const userRecruiterService = require('../../common/userRecruiterService');
const recruitCompanyService = require('../../common/recruitCompanyService');

const Loading = require('../../utils/loading_util');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        qyname: ""
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let openid = wx.getStorageSync('openid');
        console.log(openid)
        // let user =await userRecruiterService.loadEntityById("oIZm34m6OlkO9P6i-AjuzFfRQy8I");
        let user = await userRecruiterService.loadEntityById(openid);
        let user1 = user.data
        let company = await recruitCompanyService.loadEntityById(user1.companyUuid);
        console.log(company.data)
        this.setData({
            qyname: company.data.companyName + "企业信息认证中"
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
    onShareAppMessage: function () {

    }
})