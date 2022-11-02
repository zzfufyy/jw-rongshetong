// pages/dzqmpage/dzqmpage.js
/** 调用页面必须实现 callGetCommonSign(tempFilePath) 方法 */
Page({

    /**
     * 页面的初始数据
     */
    data: {
        ctx: null,
        width: null,
        height: null,
        drawCount: 0,
        drawState: "init",
        url: ''
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
        let pages = getCurrentPages();
        let prevPage = pages[pages.length - 2];
        console.log(prevPage);
        wx.canvasToTempFilePath({
            canvasId: 'canvas',
            success: res => {
                prevPage.callGetCommonSign(res.tempFilePath)
                wx.navigateBack({
                    delta:0,
                })
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function () {
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
        this.initCanvas()
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