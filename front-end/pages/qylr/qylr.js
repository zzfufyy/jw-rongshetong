// pages/qylr/qylr.js
const $ = require('../../utils/request_util');
const string_util = require('../../utils/string_util');
const util = require('../../utils/util.js');
const recruitCompanyService = require('../../common/recruitCompanyService');
const communityInformationService = require('../../common/communityInformationService');
const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 * {name:'长沙竟网信息科技有限公司',sqname:'东湖社区',local:'长沙市岳麓区国际公寓A座1917',imgtx:'/img/tx.png'}]
	 */
	data: {
		jbxx:'企业基本信息',
		qyjs:'介绍一下企业',
		comxxlist:[],
		fjhide:true,
		scbtn:false,
		scfilepath:"",
		communityUuid:"",
	},
	//企业基本信息
	bindtapAddSingleCompany(){
		let that =this;
		wx.navigateTo({
			url: '/pages/sqcomjbxx/sqcomjbxx?communityUuid='+that.data.communityUuid,
		})
	},
	//企业介绍
	qyjstj(){
		wx.navigateTo({
			url: '/pages/comjslr/comjslr',
		})
	},
	//企业相册点击放大
	preimg1(e) {
		let zwpj = this.data.zwpj
		let arrimg = []
		for (let i = 0; i < zwpj.length; i++) {
			arrimg.push(zwpj[i].imgqy)
		}
		let oimg = e.currentTarget.dataset.src
		wx.previewImage({
			current: oimg, // 当前显示图片的http链接
			urls: arrimg // 需要预览的图片http链接列表
		})
	},
	//营业执照点击放大
	preimg(e) {
		let imgyyzz = this.data.imgyyzz
		let arrimg = []
		for (let i = 0; i < imgyyzz.length; i++) {
			arrimg.push(imgyyzz[i].img)
		}
		let oimg = e.currentTarget.dataset.src
		wx.previewImage({
			current: oimg, // 当前显示图片的http链接
			urls: arrimg // 需要预览的图片http链接列表
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
			url: '/import_excel/uploadPortrait?id=' +that.data.communityUuid,
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
	},
	//保存
	bc(){
		wx.redirectTo({
			url: '/pages/qylrcg/qylrcg',
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
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