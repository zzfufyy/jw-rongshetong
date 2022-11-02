// page/wglr/wglr.js
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util');
const date_util = require('../../utils/date_util');
const url_util = require('../../utils/url_util');
const validate_util = require('../../utils/validate_util');
const { Completer } = require('../../utils/function_util');
const string_util = require('../../utils/string_util');
const object_util = require('../../utils/object_util');

const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const { Salary } = require('../../common/constant');
// 加载服务

const communityInformationService = require('../../common/communityInformationService');
const communityCellService = require('../../common/communityCellService');
const userCommunityService = require('../../common/userCommunityService');

const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		communityName: '',
		cellList: [
			// { id: '', cellName: '东湖新寓', numBuilding: '8', numLayer: '31', numLayerFamily: '8', numRegistered: '1234', numUnregistered: '1200' },
			// { id: '', cellName: '东湖新寓', numBuilding: '8', numLayer: '31', numLayerFamily: '8', numRegistered: '1234', numUnregistered: '1200' },
		]
	},
	state: {
		communityUuidCompleter: new Completer(),
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		console.log(options.communityUuid);
		this.state.communityUuidCompleter = new Completer();
		let communityUuid = options.communityUuid;
		// 测试
		this.setData({
			communityUuid: communityUuid,
		});
		this.state.communityUuidCompleter.resolve(communityUuid);
	},
	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: function () {
		try {
			Loading.begin();
			this.loadContent();
		} catch (e) {
			console.error(e)
		} finally {
			Loading.end();
		}

	},
	async loadContent() {
		communityUuid = await this.state.communityUuidCompleter.promise;
		// 加载社区名字
		communityInformation = await communityInformationService.loadEntityById(communityUuid);
		this.setData({
			communityName: communityInformation.data.communityName
		});
		// 加载小区信息
		let cellList = await communityCellService.listDTOByCommunityUuid(communityUuid);
		cellList = cellList.data;
		this.setData({
			cellList: cellList
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