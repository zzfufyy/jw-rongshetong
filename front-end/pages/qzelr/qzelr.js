// pages/qzelr/qzelr.js
const $ = require('../../utils/request_util');
const string_util = require('../../utils/string_util');
const util = require('../../utils/util.js');
const recruitCompanyService = require('../../common/recruitCompanyService');
const communityInformationService = require('../../common/communityInformationService');
const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		fjhide:true,
		scbtn:false,
		qzxxadd:'',
		zwpjadd:'',
		qyjs:'请添加求职信息',
		zwpj:'请添加自我评价',
		comxxlist:[{name:'张三',sex:'女',age:'21',imgtx:'/img/tx.png',work:'5',ellp:'12345678901'}],
		yxjobname:[{categoryName:'保洁工作',ygz:'3000-5000元/月',sqname:'东湖社区',},],
		communityUuid:"",
	},
	// 求职者基本信息
	gzjltj(){
		let that = this;
		wx.navigateTo({
			url: '/pages/qzzxxlr/qzzxxlr?communityUuid='+that.data.communityUuid,
		})
	},
	// 求职信息
	qyjstj(){
		wx.navigateTo({
			url: '/pages/wantjob/wantjob',
		})
	},
	//文件上传
	sqwj(){
    let that = this
    wx.chooseMessageFile({
      count: 1,
      type: 'file',
      success (res) {
        // tempFilePath可以作为img标签的src属性显示图片
        console.log(res)
        that.setData({
					scfile:res.tempFiles[0].name,
					scfilepath:res.tempFiles[0].path,
					scbtn:false,
					fjhide:false
        })
      }
    })
	},
	//删除按钮
	scbtn(){
		let that = this
		wx.showModal({
			title: '提示',
			content: '确认删除',
			success (res) {
				if (res.confirm) {
					console.log('用户点击确定')
					that.setData({
						scbtn:true
					})
				} else if (res.cancel) {
					console.log('用户点击取消')
				}
			}
		})
		
	},
	//确定录入
	qdlrbtn(){
		let that = this;
		let uuid = util.wxuuid();
		let uploadPromise = $.upload({
			url: '/import_excel/uploadQzzPortrait?id=' +that.data.communityUuid,
			filePath: that.data.scfilepath,
			formData: {},
			name: 'file',
			header: app.globalData.header,
		});
		wx.showToast({
			title: '成功',
			icon: 'success',
			duration: 2000
		})
		wx.navigateTo({
			url: '/pages/sqgl/sqgl?c',
		})
	},
	//自我评价
	zwpj(){
		let that =this;
		wx.navigateTo({
			url: '/pages/selfpj/selfpj?communityUuid='+that.data.communityUuid,
		})
	},
	//
	bc(){
		wx.redirectTo({
			url: '/pages/qzzlrcg/qzzlrcg',
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		console.log(options)
		this.setData({
			communityUuid:options.communityUuid
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