// pages/messgecollect4/messgecollect4.js
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

        managementMeasure: constList.managementMeasure,
        projectMeasure: constList.projectMeasure,
        illegalMeasure: constList.illegalMeasure,
        createTime: '',
        collectTeam: '',
        hiddenSign:true,
        hiddenpcr:true
    },
    bindtapSign() {
        wx.navigateTo({
            url: '/pages/dzqmpage/dzqmpage?url=ptjuzhu&residentUuid=' + this.data.residentUuid,
        })

    },
    bindtappcrSign() {
        wx.navigateTo({
            url: '/pages/dzqmpage/dzqmpage?url=ptjuzhu&residentUuid=' + this.data.residentUuid,
        })

    },
    bindchangemanagementMeasure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            managementMeasure: this.data.managementMeasure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeprojectMeasure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            projectMeasure: this.data.projectMeasure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeillegalMeasure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            illegalMeasure: this.data.illegalMeasure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangecreateTime: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            createTime: e.detail.value
        })
    },
    bindinputcollectTeam(e) {
        this.setData({
            collectTeam: e.detail.value,
        })
    },
    prev() {
        wx.navigateBack({
            delta: 0,
        })
    },
    async submit() {
        let that = this;
     
        wx.showModal({
            title: '提示',
            content: '是否确认提交',
            async success(res) {
                if (res.confirm) {
                    let updateData = {
                        id: that.data.id,
                        managementMeasure: constList.turnListToString(that.data.managementMeasure),
                        projectMeasure: constList.turnListToString(that.data.projectMeasure),
                        illegalMeasure: constList.turnListToString(that.data.illegalMeasure),
                        createTime: that.data.createTime,
                        collectTeam: that.data.collectTeam,
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
                    wx.redirectTo({
                        url: '/pages/modicompit/modicompit',
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
    onLoad: function (options) {

        let id = options.id;
        // id = '27fdf7a1-5471-41ef-a746-b9dc13a9310d';
        this.setData({
            id: id,
        })
        //获取系统当前日期年月日
        let riqi = new Date();
        let time = new Date();
        //年
        var Y = riqi.getFullYear();
        //月
        var M = (riqi.getMonth() + 1 < 10 ? '0' + (riqi.getMonth() + 1) : riqi.getMonth() + 1);
        //日
        var D = riqi.getDate() < 10 ? '0' + riqi.getDate() : riqi.getDate();
        //时
        var h = time.getHours() < 10 ? '0' + time.getHours() : time.getHours();;
        //分
        var m = time.getMinutes() < 10 ? '0' + time.getMinutes() : time.getMinutes();;
        //秒
        // var s = time.getSeconds() < 10 ? '0' + time.getSeconds() : time.getSeconds();;
        // 日期
        var data = Y + "-" + M + "-" + D
        var t = h + ":" + m;
        // 赋值
        console.log(data)
        this.setData({
            createTime: data,
            time: t
        });
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