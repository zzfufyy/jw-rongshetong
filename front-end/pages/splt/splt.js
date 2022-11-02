// pages/splt/splt.js
const { btoa } =  require('../../common/base64.js')
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ewm:"",
    ext1:""
  },

  statechange(e) {
    console.log('live-player code:', e.detail.code)
  },
  error(e) {
    console.error('live-player error:', e.detail.errMsg)
  },
  bindPlay() {
    let that = this;
    wx.request({
      url: app.globalData.web_path +'/fczc/searchtoken',
      header: app.globalData.header,
      method: "GET",
      data:{
        grant_type:"client_credential",
      },
      success: function (data) {
        console.log()
        var accessToken =data.data.sj.access_token
        let ext1=that.data.ext1;
        wx.request({
          // url:'https://api.weixin.qq.com/wxa/getwxacode?access_token=' + accessToken, // a
          // url:'https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=' + accessToken, // b
          url: app.globalData.web_path +'/fczc/getQRCode', // c
          method: "GET",
          data: {
            'access_token':accessToken,
            'sceneStr': 'ext1='+ext1, // 携参数openid
            // 'width': '430'
          },
          // responseType: 'arraybuffer',
          success: function (res) {
            console.log(res)  // 二维码
            that.setData({
              ewm:app.globalData.web_path +'/images/ewm/'+res.data.qrcodeurl
            })
           
          },
          fail: function (res) {
            console.log('fail')
          }
        })
      }
    })
   
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    that.setData({
      ext1:options.ext1
    })

    wx.request({
      url: app.globalData.web_path +'/fczc/searchtoken',
      header: app.globalData.header,
      method: "GET",
      data:{
        grant_type:"client_credential",
      },
      success: function (data) {
        
        var accessToken =data.data.sj.access_token
        let ext1=that.data.ext1;
        console.log(ext1)
        wx.request({
          // url:'https://api.weixin.qq.com/wxa/getwxacode?access_token=' + accessToken, // a
          // url:'https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=' + accessToken, // b
          url: app.globalData.web_path +'/fczc/getQRCode', // c
          method: "GET",
          data: {
            'access_token':accessToken,
            'sceneStr': ext1, // 携参数openid
            // 'width': '430'
          },
          // responseType: 'arraybuffer',
          success: function (res) {
            console.log(res)  // 二维码
            that.setData({
              ewm:app.globalData.web_path +'/images/ewm/'+res.data.qrcodeurl
            })
           
          },
          fail: function (res) {
            console.log('fail')
          }
        })
      }
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