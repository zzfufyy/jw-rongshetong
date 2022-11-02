// page/sqgl/sqgl.js
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util');
const img_util = require('../../utils/img_util');
const { Completer } = require('../../utils/function_util');

// 加载服务

const communityInformationService = require('../../common/communityInformationService');

const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		communityUuid: '',

		communityName: '',//sqname:'东湖社区',
		communityAddress: '', //localtt:'湖南省长沙市芙蓉区晚报西街13号',
		communityPortraitPath: '',
		ext1:"",
	},
	state: {
		communityUuidCompleter: new Completer(),
	},

	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: async function (options) {
		this.state.communityUuidCompleter = new Completer();
		let communityUuid = options.communityUuid;
		console.log(communityUuid);
		// 测试
		// communityUuid = 'e80cf86d-a8ea-11ec-98aa-5076afbf1539';
		this.setData({
			communityUuid: communityUuid,
		})
		this.state.communityUuidCompleter.resolve(communityUuid);

	},
	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: async function () {
		try {
			Loading.begin();
			await this.loadContent()
		} catch (e) {
			console.error(e)
		} finally {
			Loading.end();
		}
	},

	loadContent: async function () {
		let communityUuid = await this.state.communityUuidCompleter.promise;
		console.log(communityUuid)
		let communityData = await communityInformationService.loadEntityById(communityUuid);
		communityData = communityData.data;
		console.log(communityData);
		this.setData({
			communityName: communityData.communityName,
			communityAddress: communityData.address,
			communityPortraitPath: img_util.handleCommunityPortraitPath(communityData.portraitPath),
			ext1:communityData.ext1
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

	}
})