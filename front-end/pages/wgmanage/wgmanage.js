// pages/wgmanage/wgmanage.js
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util');
const date_util = require('../../utils/date_util');
const url_util = require('../../utils/url_util');
const upload_util = require('../../utils/upload_util');

const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const { Salary } = require('../../common/constant');
// 加载服务
const communityInformationService = require('../../common/communityInformationService');

const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		communityUuid: '',

		communityName: '东湖社区',
		communityPhone: '0731-438953853',
		communityAddress: '湖南省长沙市芙蓉区晚报西街13号',
		communityPortraitPath: ''
	},
	bindinputCommunityName(e){
		console.log(e.detail.value);
		this.setData({communityName: e.detail.value})
	},
	bindinputCommunityPhone(e){
		console.log(e.detail.value);
		this.setData({communityPhone: e.detail.value})
	},
	bindinputCommunityAddress(e){
		console.log(e.detail.value);
		this.setData({communityAddress: e.detail.value})
	},
	
	//点击上传图片
	scimgbtn() {
		let that = this
		wx.chooseMedia({
			count: 1,
			mediaType: ['image'],
			sourceType: ['album', 'camera'],
			maxDuration: 30,
			camera: 'back',
			success(res) {
				console.log(res)
				that.setData({
					communityPortraitPath: res.tempFiles[0].tempFilePath
				})
			}
		})
	},
	//点击保存
	async tijsq() {
		let updateData = {
			id : this.data.communityUuid,
			communityName : this.data.communityName,
			phone: this.data.communityPhone,
			address: this.data.communityAddress,
		};
		try {
			Loading.begin();
			await communityInformationService.updateByEntity(updateData);
			if(upload_util.isUploadPathLegal(this.data.communityPortraitPath)){
				await communityInformationService.uploadPortrait(this.data.communityUuid, this.data.communityPortraitPath);
			}
			
		} catch (e) {
			console.error(e);
		}finally{
			Loading.end();
        }
        // 通知更新社区服务主页
        app.globalMonitors.communityInfoChangeMonitor.notifyAll();
		wx.navigateBack({
			delta: 1
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: async function (options) {
		console.log(options.communityUuid);
		let communityUuid = options.communityUuid;
		// 测试
		// communityUuid = 'e80cf86d-a8ea-11ec-98aa-5076afbf1539';
		this.setData({
			communityUuid: communityUuid,
		});

		try {
			Loading.begin();
			let communityData = await communityInformationService.loadEntityById(communityUuid)
			this.setData({
				communityName: communityData.data.communityName,
				communityPhone: communityData.data.phone,
				communityAddress: communityData.data.address,
				communityPortraitPath: url_util.isImageUrlInServer(communityData.data.portraitPath)?
					app.globalData.web_path + communityData.data.portraitPath: communityData.data.portraitPath,
				
			});
		} catch (e) {
			console.error(e);
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