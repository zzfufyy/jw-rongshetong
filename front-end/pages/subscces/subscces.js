// pages/subscces/subscces.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		title:'表单名称显示',
	},

	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {

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
	onShareAppMessage: function (res) {
		if (res.from === 'button') {
			// 来自页面内转发按钮
			title = "这个是页面自定义的分享事件~";
			// imageUrl='***.png';
		}
		if (res.from === 'menu') {
				title = "这个是页面右上角的分享事件~";
				// imageUrl='***.png';
		}
	},
	//分享朋友圈
	onShareTimeline(res) {
    console.log(res)
  }
})