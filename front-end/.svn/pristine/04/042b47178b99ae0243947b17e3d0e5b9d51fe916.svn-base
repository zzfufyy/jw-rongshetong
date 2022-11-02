// pages/qylr/qylr.js
Page({

	/**
	 * 页面的初始数据
	 * {name:'长沙竟网信息科技有限公司',sqname:'东湖社区',local:'长沙市岳麓区国际公寓A座1917',imgtx:'/img/tx.png'}]
	 */
	data: {
		sqname:'东湖社区',
		localtion:'长沙市岳麓区国际公寓A座1917',
		yxjobname:'团队已拥有从业至少十年以上的项目经理',
		tximg:'/img/tx.png',
		name:'长沙竟网信息科技有限公司',
		qzjy:[
			{
				companyname:'UI设计师',jobmoney:'7000-8000元',rsnum:'1',jiny:'经验不限',xlyq:'学历不限',
				jobyear:'年龄不限',
				jobdsc:[
					{jobjl:'1.根据设计要求完成建筑风格、外形等总体设计;'},
					{jobjl:'2.提供各种建筑主体设计、户型设计、外墙设计、景 观设计等;'}
				]
			},
			{
				companyname:'java',jobmoney:'7000-8000元',rsnum:'1',jiny:'经验不限',xlyq:'学历不限',
				jobyear:'年龄不限',
				jobdsc:[
					{jobjl:'1.根据设计要求完成建筑风格、外形等总体设计;'},
					{jobjl:'2.提供各种建筑主体设计、户型设计、外墙设计、景 观设计等;'}
				]
			}
		],
		zwpj:[{imgqy:'/img/wglr.png'},{imgqy:'/img/tx.png'},{imgqy:'/img/wgimg.png'},],
		imgyyzz:[{img:'/img/wglr.png'}]
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
	zwxq(){
		wx.navigateTo({
			url: '/pages/zwxq/zwxq',
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		let that = this;
		wx.getSystemInfo({
			success: function (res) {
				let imgwd = (res.windowWidth -40 -30)/3;
				let bight = imgwd * 3 +30
				that.setData({
					imgwd:imgwd,
					bight:imgwd
				})
			}
		});
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