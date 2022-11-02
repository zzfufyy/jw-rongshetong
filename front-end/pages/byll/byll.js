// pages/byll/byll.js
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const { Salary } = require('../../common/constant');
// 加载服务
const recruitJobService = require('../../common/recruitJobService');
const viewRecordService = require('../../common/viewRecordService');

// 附加模块
const byllEx = require('./byllEx');

const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		pages: 1,
		joblist: [
			{ jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
			{ jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
			{ jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
		],
		comnum: 0,
	},
	//打电话
	callphone(e) {
		console.log(e.currentTarget.dataset.phonenum)
		let phonenum = e.currentTarget.dataset.phonenum
		wx.makePhoneCall({
			phoneNumber: phonenum //仅为示例，并非真实的电话号码
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		// this.loadViewRecord();
	},
	onShow: async function () {

		try {
			Loading.begin();

			// 需要openid 加載用戶參數
			await app.getOpenidReady();
			let openid = wx.getStorageSync('openid');
			// 每次进入前清空content
			await this.clearContent();
			await this.loadContent();
			// 加载数目
			let countData = await viewRecordService.countByCandidateOpenid(openid)
			this.setData({
				comnum: countData.data,
			})
		} catch (e) {
			console.error(e)
		} finally {
			Loading.end()
		}

	},
	/**
	 * 页面上拉触底事件的处理函数
	 */
	onReachBottom: async function () {
		try {
			Loading.begin();
			await this.loadContent();
		} catch (e) {
			console.error(e)
		} finally {
			Loading.end();
		}
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
	 * 用户点击右上角分享
	 */
	onShareAppMessage: function () {

	},
	// 页面分页内容相关
	...byllEx.createPageMethods(),
})