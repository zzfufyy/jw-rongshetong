// pages/tpewm/tpewm.js
const img_util = require("../../utils/img_util");
const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		id:'',
		ewmimg:'http://jw.csjing.com:8080/jeecg-boot/sys/common/static/temp/ewmimg_1653393350316.png',
		syewm:'/img/syewm.png',
		img: img_util.handleNormalPortraitPath("/images/img/bigimg.jpg"),
		wechat: "/img/pyq.png",
		xiazai: "/img/wxhy.png",
		share:"/img/share.png",
		maskHidden: false,
		name: "张先生",
		hr: "招聘专员",
		comname: "问卷一招聘专员招聘专员招聘专员招聘专员招聘专员招聘专员招聘专员招聘专员",
		// showshare:false,
		portraitPath:'/img/xhsq.png',
		imagePath:'',
	},
	//将canvas转换为图片保存到本地，然后将图片路径传给image图片的src
	createNewImg: function () {
		var that = this;
		var context = wx.createCanvasContext('mycanvas');
		//设置背景图
		// var path = "/img/bigimg.jpg";
		var path = "/img/whitebg.png";
		context.drawImage(path, 0, 0, 375, 734);

		// // 设置白色背景图
		// var pathwhite = "/img/whitebg.png";
		// context.drawImage(pathwhite, 19, 421, 337, 218);
		//设置小程序码的图片
		// var path5 = "/img/syewm.png";
		var path5=wx.getStorageSync('ewm');
		context.drawImage(path5, 42, 63,183,183);

		 //绘制工作名字
		 context.setFontSize(14);
		 context.setFillStyle('#333');
		 context.setTextAlign('left');
		 console.log(that.data.comname.length)
		 let str;
		 if(that.data.comname.length>=13){
			 str = that.data.comname.substring(0,13) + '...'
		 }else{
			str = that.data.comname
		 }
		 context.fillText(str, 39, 300);
		 context.stroke();

		context.draw();

		//将生成好的图片保存到本地
		setTimeout(function () {
			wx.canvasToTempFilePath({
				canvasId: 'mycanvas',
				success: function (res) {
					var tempFilePath = res.tempFilePath;
					console.log(res)
					that.setData({
						imagePath: tempFilePath,
					});
				},
				fail: function (res) {
					console.log(res);
				}
			});
		}, 2000);
	},
	//点击保存到相册
	baocun: function () {
			var that = this
			console.log(that.data.imagePath)

			wx.saveImageToPhotosAlbum({
			filePath: that.data.imagePath,
			success(res) {
					wx.showModal({
					content: '保存成功',
					showCancel: false,
					confirmText: '确定',
					confirmColor: '#333',
					success: function (res) {
							if (res.confirm) {
								console.log('用户点击确定');
								/* 该隐藏的隐藏 */
								// that.setData({
								// 		maskHidden: false
								// })
							}
					}, fail: function (res) {
							console.log(11111)
					}
					})
			}
			})
	},
  getQrCodes(that){
		wx.request({
				url: app.globalData.web_path +'/fczc/searchtoken',
				header: app.globalData.header,
				method: "GET",
				data:{
					grant_type:"client_credential",
				},
				success: function (data) {
					
					var accessToken =data.data.sj.access_token
					let id=that.data.id;
					console.log(id)
					wx.request({
						// url:'https://api.weixin.qq.com/wxa/getwxacode?access_token=' + accessToken, // a
						// url:'https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=' + accessToken, // b
						url: app.globalData.web_path +'/fczc/gettpQRCode', // c
						method: "GET",
						data: {
							'access_token':accessToken,
							'sceneStr': id, // 携参数openid
							// 'width': '430'
						},
						// responseType: 'arraybuffer',
						success: function (res) {
							console.log(res)  // 二维码
							that.setData({

								ewmimg:app.globalData.web_path +'/images/ewm/'+res.data.qrcodeurl
							})
							let ewm = app.globalData.web_path +'/images/ewm/'+res.data.qrcodeurl;
							wx.getImageInfo({
								src: ewm,//服务器返回的图片地址
								success: function (res) {
									//res.path是网络图片的本地地址
									// 背景图
									console.log(res)
									wx.setStorageSync('ewm', res.path);
								},
								fail:function (res) {
										console.log(res)
								}
						});
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
		that.getQrCodes(that);
		this.createNewImg()
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