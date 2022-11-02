const string_util = require("../../utils/string_util");
const prompt_util = require("../../utils/prompt_util");
const socialTrainingApplyService = require("../../common/socialTrainingApplyService");
const Loading = require("../../utils/loading_util");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {

        userOpenid: "",
        userName: "",
        userPhone: "",
        userMark: "",
        trainingType: "",
        trainingTypeList: [
            "信息传输、软件和信息技术服务类",
            "医药制造类",
            "机械制造基础加工类",
            "金属制品制造类",
            "专用设备制造类",
            "汽车制造类",
            "电气机械和器材制造类",
            "计算机、通信和其他电子设备制造类",
            "运输设备和通用工程机械操作类",
            "生产辅助类",
            "技术辅助服务类",
            "批发与零售服务类",
            "交通运输、仓储和邮政业服务类",
            "住宿和餐饮服务类",
            "租赁和商务服务",
            "居民服务类",
            "修理及制作服务类",
            "纺织品、服装和皮革、毛皮制品加工制作类",
            "木材加工、家具与木制品制作类",
            "采矿类",
            "建筑施工类",
            "农业生产类",
            "林业生产类",
            "畜牧业生产类",
            "渔业生产类",
            "农、林、牧、渔业生产辅助类",
            "农副产品加工类",
            "食品、饮料生产加工类",
        ]
    },
    /** 页面事件 */
    bindchangeTrainingType: function (e) {
        console.log(e);
        let index = e.detail.value;
        let trainingType = this.data.trainingTypeList.find((v, i) => i == index);
        this.setData({
            trainingType: trainingType,
        })
    },
    bindinputUserName: function (e) {
        this.setData({
            userName: e.detail.value,
        })
    },
    bindinputUserPhone: function (e) {
        this.setData({
            userPhone: e.detail.value,
        })
    },
    bindinputUserMark: function (e) {
        this.setData({
            userMark: e.detail.value,
        })
    },
    bindtapSubmit: async function () {
        if (string_util.isEmpty(this.data.trainingType)) {
            prompt_util.showToastErrorCustom("请选择培训类型");
            return;
        }
        if (string_util.isEmpty(this.data.userName)) {
            prompt_util.showToastErrorCustom("请填写姓名");
            return;
        }
        if (string_util.isEmpty(this.data.userPhone)) {
            prompt_util.showToastErrorCustom("请填写电话");
            return;
        }
        try {
            Loading.begin();
            let entity = {
                userOpenid: this.data.userOpenid,
                trainingType: this.data.trainingType,
                userName: this.data.userName,
                userPhone: this.data.userPhone,
                userMark: string_util.isEmpty(this.data.userMark)? "":this.data.userMark,
            }
            await socialTrainingApplyService.insertByEntity(entity);

        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
        prompt_util.saveSuccessPrompt(()=>{
            wx.navigateBack({
                delta: 1,
            });
        })
        
    },
    /** 内部方法 */
    _setSystemInfo: function () {
        this.setData({
            winHeight: wx.getSystemInfoSync().windowHeight,
            submitMarginTop: wx.getSystemInfoSync().windowHeight - 64,
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        this._setSystemInfo();
        await app.getOpenidReady();
        let userOpenid = app.getOpenid();
        this.setData({
            userOpenid: userOpenid,
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