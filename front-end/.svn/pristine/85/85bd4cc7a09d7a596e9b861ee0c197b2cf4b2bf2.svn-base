// pages/ptjuzhu/ptjuzhu.js
const CONSTANT = require('../../common/constant');
const Loading = require('../../utils/loading_util');
const { Completer } = require('../../utils/function_util');
const string_util = require('../../utils/string_util');
const date_util = require('../../utils/date_util');


// 加载服务
const buildingResidentService = require('../../common/buildingResidentService');
const residentForPersonService = require('../../common/residentForPersonService');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {

        residentUuid: '',

        // 居住人数
        residentNumber: 0,
        genderList: CONSTANT.genderList,

        residentList: [
            // { personName: '张六', personGender: 0, personBirthday: '', personAge: 29, personIdcard: '431223199809071234', personPhone: '12345678901', provinceName: '湖南省', cityName: '长沙市', districtName: '岳麓区', id: '' },
        ],
        rs: '',
        ctx: null,
        width: null,
        height: null,
        drawCount: 0,
        drawState: "init",
        showCanvas: false,
        img: '',
    },
    state: {
        residentUuidCompleter: new Completer(),
    },
    bindinputResidentNumber(e) {
        console.log(e.detail.value);
        this.setData({
            residentNumber: e.detail.value
        })
    },
    // 添加居住人员
    bindtapAddResident(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?residentUuid=' + this.data.residentUuid + '&personType=' + 0,
        })
    },
    // 修改居住人员信息
    bindtapModifyResident(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?personUuid=' + e.currentTarget.dataset.id,
        })
    },
    // 删除居住人员信息
    async catchtapDeleteResident(e) {
        console.log(e);
        let deleteId = e.currentTarget.dataset.id;
        let currentResidentList = this.data.residentList;
        let deleteOperation = residentForPersonService.deleteById(deleteId);
        await deleteOperation.then(v => {
            wx.showToast({
                icon: 'success',
                title: '删除成功',
            });
            this.setData({
                residentList: currentResidentList.filter(r => { return r.id != deleteId }),
            });
        }).catch(e => {
            wx.showToast({
                icon: 'error',
                title: '删除失败',
            });
            console.error(e);
        })
    },
    bindtapSign() {
        wx.navigateTo({
            url: '/pages/dzqmpage/dzqmpage?url=ptjuzhu&residentUuid=' + this.data.residentUuid,
        })

    },
    
    // 提交
    async bindtapSubmit(e) {
        // 更新从业人数 居住人数
        let updateData ={
            id: this.data.residentUuid,
            residentNumber: this.data.residentNumber,
        }
        try {
            Loading.begin();
            await buildingResidentService.updateByEntity(updateData)    
        } catch (e) {
            console.error(e);
        }finally{
            Loading.end();
        }
        wx.redirectTo({
            url: '/pages/mesegsubmit/mesegsubmit?residentUuid=' + this.data.residentUuid,
        })

    },

   
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.state.residentUuidCompleter = new Completer();
        let residentUuid = options.residentUuid;
        // 测试id
        // residentUuid = '801fb106-6792-4770-8f34-7f8950ca61cd';
        this.state.residentUuidCompleter.resolve(residentUuid);
    },
    onShow: async function () {
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },

    loadContent: async function () {
        let residentUuid = await this.state.residentUuidCompleter.promise;
        this.setData({
            residentUuid: residentUuid,
        })
        // 加载 从业人数、居住人数,
        let buildingResidentData = await buildingResidentService.loadEntityById(residentUuid);
        buildingResidentData = buildingResidentData.data;
        this.setData({
            residentNumber: buildingResidentData.residentNumber,
            signImgPath: string_util.isEmpty(buildingResidentData.signImgPath) ?
                '' : app.globalData.web_path + buildingResidentData.signImgPath,
            hiddenSign: string_util.isEmpty(buildingResidentData.signImgPath) ? true : false,
        });
        console.log(this.data.hiddenSign);
        // 加载 从业人员、居住人员 信息
        let residentList = await residentForPersonService.loadListByResidentUuid(residentUuid, 0);
        residentList = residentList.data;
        this.setData({
            residentList: residentList.map(v => {
                v.personAge = date_util.getAgeByBirthday(v.personBirthday);
                return v;
            }),
        })
    },
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

    initCanvas() {
        let {
            width,
            height
        } = this.data
        width = wx.getSystemInfoSync().windowWidth
        height = wx.getSystemInfoSync().windowHeight
        console.log(wx.getSystemInfoSync())
        this.data.ctx = wx.createCanvasContext('canvas')
        this.setData({
            width,
            height
        })
        this.clearCanvas()
    },
    clearCanvas() {
        this.data.drawCount = 0
        this.data.drawState = "ing"
        this.data.ctx.setTextBaseline('top')
        this.data.ctx.setTextAlign('center')
        this.data.ctx.setFontSize(20)
        this.data.ctx.setFillStyle('#616165');
        this.data.ctx.fillText("请灰色区域内完成签名", this.data.width / 2, 30)
        this.data.ctx.draw(false)
    },
    catchtouchstart(e) {
        if (this.data.drawCount == 0) {
            this.data.ctx.draw(false)
        }
        this.data.drawCount++
        this.data.ctx.moveTo(e.changedTouches[0].clientX, e.changedTouches[0].clientY)
    },
    catchtouchmove(e) {
        if (this.data.drawState == "stop") return
        this.data.drawState = "ing"
        if (e.touches.length > 1) {
            return
        }
        this.data.ctx.setStrokeStyle('#000000');
        this.data.ctx.setLineWidth(3);
        this.data.ctx.setShadow(0, 0, 0.5, '#000000')
        this.data.ctx.setLineCap('round');
        this.data.ctx.setLineJoin('round');
        this.data.ctx.lineTo(e.changedTouches[0].clientX, e.changedTouches[0].clientY)
        this.data.ctx.stroke()
        this.data.ctx.draw(true)
        this.data.ctx.moveTo(e.changedTouches[0].clientX, e.changedTouches[0].clientY)
    },
    canvasToImg() {
        let showCanvas = !this.data.showCanvas
        let that = this
        if (this.data.drawState == "init") return
        this.data.drawState = "stop"
        wx.canvasToTempFilePath({
            canvasId: 'canvas',
            success: res => {
                console.log(res.tempFilePath)
                // ...
                // 上传服务器
                // wx.navigateTo({
                url: '/pages/ptjuzhu/ptjuzhu?src=' + res.tempFilePath,
                    // })
                    that.setData({
                        img: res.tempFilePath,
                        showCanvas: showCanvas
                    })
            }
        })
    },
})