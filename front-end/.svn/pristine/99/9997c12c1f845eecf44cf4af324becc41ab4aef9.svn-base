// pages/qzzxxlr/qzzxxlr.js
const $ = require('../../utils/request_util');
const string_util = require('../../utils/string_util');
const util = require('../../utils/util.js');
const userCandidateService = require('../../common/userCandidateService');
// const communityInformationService = require('../../common/communityInformationService');
const { Completer } = require('../../utils/function_util');

const recruitCompanyService = require('../../common/recruitCompanyService');
const communityInformationService = require('../../common/communityInformationService');
const app = getApp();
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		array:['社区一','社区二'],
		index:0,
		array1:['女','男'],
		index1:0,
		date: '1990-01-01',
		array3:['1年','2年','3年','4年','5年','6年',],
		index3:0,
		zwpj:"",
		communityUuidList: [],
		communityUuid: '',
		communityName: '',
		gzsc:"",
		name:"",
		lxdh:"",
		mingz:"",
		maxgz:"",
	
	},
	state: {
		communityUuidCompleter: new Completer(),
},
	bindinputName(e){
		this.setData({
			name: e.detail.value,
		})
	},
	lxdh(e){
		this.setData({
			lxdh: e.detail.value,
		})
	},
	mingz(e){
		this.setData({
			mingz: e.detail.value,
		})
	},
	maxgz(e){
		this.setData({
			maxgz: e.detail.value,
		})
	},
	// 社区选择
	bindPickerChange(e) {
		console.log('picker发送选择改变，携带值为', e.detail.value)
		this.setData({
			index: e.detail.value,
			communityUuid: this.data.communityUuidList[e.detail.value],
		})
	},
	// 性别
	bindPickerChange1(e) {
		console.log('picker发送选择改变，携带值为', e.detail.value)
		this.setData({
			index1: e.detail.value,
		})
	},
	zwpj(e) {
		this.setData({
			zwpj: e.detail.value,
		});
	},
	gzsc(e){
		this.setData({
			gzsc: e.detail.value,
		});
	},
	//出生年份
	bindDateChange2(e){
		console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
	},
	// 性别
	bindPickerChange3(e) {
		console.log('picker发送选择改变，携带值为', e.detail.value)
		this.setData({
			index3: e.detail.value,
		})
	},
	//点击上传头像
	xgtx() {
		let imgsrc = this.data.imgsrc
		let that = this
		wx.chooseMedia({
			count: 1,
			mediaType: ['image'],
			sourceType: ['album', 'camera'],
			camera: ['back', 'front'],
			success(res) {
				console.log(res.tempFiles[0].tempFilePath);
				that.setData({
					imgsrc: res.tempFiles[0].tempFilePath,
					none: 0
				})
			}
		})
	},
	async	bc(){
		let that = this;
		let uuid = util.wxuuid()
		// 如果图片路径不包含 web_path 或者为空 则不执行上传
	
		let insertData = {
			id:uuid,
			realName:this.data.name,
			gender:this.data.index,
			birthday:this.data.date,
			telephone:this.data.lxdh,
			workingAge: this.data.gzsc,
			communityUuid: this.data.communityUuid,
			introduction:this.data.zwpj,
			expectSalaryMin:this.data.mingz,
			expectSalaryMax:this.data.maxgz,
			ext2:"sqll",
			ext3:this.data.communityName,
		}
		console.log(insertData);
		let submitPromise = userCandidateService.insertByEntity(insertData);
		await submitPromise.then((r) => {
			console.log(r);
		}).catch(r => console.error(r));
		wx.showToast({
			title: '添加成功',
			icon: 'success',
			duration: 2000
		})
		wx.redirectTo({
			url: '/pages/qzelr/qzelr',
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: async function (options) {
		this.state.communityUuidCompleter = new Completer();
		let communityUuid = options.communityUuid;
		console.log(communityUuid)
		this.state.communityUuidCompleter.resolve(communityUuid)
		let currentCommunityUuid = await this.state.communityUuidCompleter.promise;

		let communityData = await communityInformationService.loadEntityById(currentCommunityUuid)
		communityData = communityData.data;
		console.log(communityData)
		this.setData({
				communityUuid: communityData.id,
				communityName: communityData.communityName,
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