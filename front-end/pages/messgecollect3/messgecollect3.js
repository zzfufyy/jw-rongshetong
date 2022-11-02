// pages/messgecollect3/messgecollect3.js
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

        isGeologyDanger: constList.isGeologyDanger,
        geologyDangerType: constList.geologyDangerType,
        estimateStructureDanger: constList.estimateStructureDanger,
        structureDangerPart: constList.structureDangerPart,
        isSecurityAuthenticate: constList.isSecurityAuthenticate,
        securityAuthenticateResult: constList.securityAuthenticateResult,
        authenticateResultPart: constList.authenticateResultPart,
        isFireSafety: constList.isFireSafety,
        fireSafetyDanger: constList.fireSafetyDanger,
    },
    bindchangeisGeologyDanger(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isGeologyDanger: this.data.isGeologyDanger.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangegeologyDangerType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            geologyDangerType: this.data.geologyDangerType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeestimateStructureDanger(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            estimateStructureDanger: this.data.estimateStructureDanger.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangestructureDangerPart(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            structureDangerPart: this.data.structureDangerPart.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeisSecurityAuthenticate(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isSecurityAuthenticate: this.data.isSecurityAuthenticate.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangesecurityAuthenticateResult(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            securityAuthenticateResult: this.data.securityAuthenticateResult.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeauthenticateResultPart(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            authenticateResultPart: this.data.authenticateResultPart.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeisFireSafety(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isFireSafety: this.data.isFireSafety.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangefireSafetyDanger(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            fireSafetyDanger: this.data.fireSafetyDanger.map((v, i) => {
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
        let securityAuthenticateResult = constList.turnListToString(this.data.securityAuthenticateResult);
        let authenticateResultPart = constList.turnListToString(this.data.authenticateResultPart);
        if (securityAuthenticateResult.indexOf('C') != -1) {
            let arr = authenticateResultPart.split(";");
            arr = arr.map(v => {
                return 'C' + v;
            })
            authenticateResultPart = arr.join(";");
        } else if (securityAuthenticateResult.indexOf('D') != -1) {
            let arr = authenticateResultPart.split(";");
            arr = arr.map(v => {
                return 'D' + v;
            })
            authenticateResultPart = arr.join(";");
        }
        let updateData = {
            id: this.data.id,

            isGeologyDanger: constList.turnListToString(this.data.isGeologyDanger),
            geologyDangerType: constList.turnListToString(this.data.geologyDangerType),
            estimateStructureDanger: constList.turnListToString(this.data.estimateStructureDanger),
            structureDangerPart: constList.turnListToString(this.data.structureDangerPart),
            isSecurityAuthenticate: constList.turnListToString(this.data.isSecurityAuthenticate),
            securityAuthenticateResult: constList.turnListToString(this.data.securityAuthenticateResult),
            authenticateResultPart: authenticateResultPart,
            isFireSafety: constList.turnListToString(this.data.isFireSafety),
            fireSafetyDanger: constList.turnListToString(this.data.fireSafetyDanger),
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
            url: '/pages/messgecollect4/messgecollect4?id=' + this.data.id,
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