// pages/messgecollect2/messgecollect2.js
const constList = require('../../common/constMessgecollect');
const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');
const informationCollectSelfBuildingService = require('../../common/informationCollectSelfBuildingService')

Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '',

        isProcedure: constList.isProcedure,
        procedureType: constList.procedureType,
        isIllegal: constList.isIllegal,
        illegalType: constList.illegalType,
    },
    bindchangeisProcedure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isProcedure: this.data.isProcedure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeprocedureType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            procedureType: this.data.procedureType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeisIllegal(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isIllegal: this.data.isIllegal.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeillegalType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            illegalType: this.data.illegalType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },

    prev() {
        wx.navigateBack({
            delta: 0,
        })
    },
    async next() {
        let updateData = {
            id: this.data.id,
            isProcedure: constList.turnListToString(this.data.isProcedure),
            procedureType: constList.turnListToString(this.data.procedureType),
            isIllegal: constList.turnListToString(this.data.isIllegal),
            illegalType: constList.turnListToString(this.data.illegalType),
        }
        console.log(updateData);
        let submitRs = '';
        try {
            Loading.begin();
            submitRs = await informationCollectSelfBuildingService.updateByEntity(updateData);
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end()
        }
        console.log(submitRs.data);
        wx.navigateTo({
            url: '/pages/messgecollect3/messgecollect3?id=' + this.data.id,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let id = options.id;
        // id = '27fdf7a1-5471-41ef-a746-b9dc13a9310d';
        this.setData({
            id: id,
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