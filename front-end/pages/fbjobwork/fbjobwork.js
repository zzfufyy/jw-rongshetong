// pages/fbjob/fbjob.js
const CONSTANT = require('../../common/constant');
var string_util = require('../../utils/string_util');
const $ = require('../../utils/request_util');

const userRecruiterService = require('../../common/userRecruiterService');
const recruitCompanyService = require('../../common/recruitCompanyService');
const recruitJobService = require('../../common/recruitJobService');

const Loading = require('../../utils/loading_util');
const app = getApp();

const PAGENAME = 'fbjob.js - ';
let dataJobNum = 0;
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		communityUuid:"",
		recruiterOpenid: '',
		// 是否已认证
		flagIdentification: 0,
		companyUuid: '',
		companyName: '',
		companyAddress: '',
		recruiterTelephone:"",
		datall:[
			{array:CONSTANT.salaryList.map((val) => { return val.value }),index:0,
			jobName: '',
			jobSalaryMin: CONSTANT.salaryList[0].min,
				jobSalaryMax: CONSTANT.salaryList[0].max,
				jobIntroduction: '',
				recruitingNumber: '',
				array1:['社区1','街道1','街道2'],	index1:0,
			array2:['IT1','文化传媒1','电子制造1'],index2:0,
			array3:['经验1','经验12','经验13'],index3:0,
			array4:['年龄1','年龄12','年龄13'],index4:0,
			pindex: dataJobNum,
			ppindex:0, sbpp:"",}
		],
		dyxz:"",
	},
	//继续添加
	jxtj(e){
		let that = this
		let odatall = that.data.datall
		dataJobNum++;
		let newitem = {
			array:CONSTANT.salaryList.map((val) => { return val.value }),
			jobName: '',
			jobSalaryMin: CONSTANT.salaryList[0].min,
				jobSalaryMax: CONSTANT.salaryList[0].max,
				jobIntroduction: '',
				recruitingNumber: '',
			index:0,	array1:['社区1','街道1','街道2'],	index1:0,
			array2:['IT1','文化传媒1','电子制造1'],index2:0,
			array3:['经验1','经验12','经验13'],index3:0,
			array4:['年龄1','年龄12','年龄13'],index4:0,
			pindex: dataJobNum,
			ppindex:0,sbpp:"",
		}
		this.setData({
			datall: that.data.datall.concat(newitem),
		})
	},
	// 薪资范围选择
	bindPickerChange(e){
		let thisDatall = this.data.datall;
		let pindex = e.target.dataset.index; //数组下标
		let index = e.detail.value;
		thisDatall[pindex].jobSalaryMin = CONSTANT.salaryList[index].min;
		thisDatall[pindex].jobSalaryMax = CONSTANT.salaryList[index].max;
		thisDatall[pindex].index = index;
		console.log('picker发送选择改变，携带值为', e.detail.value)
		// console.log(ppindex)
		this.setData({
			datall: thisDatall,
			color: "#000"
		})
		console.log(this.data.datall[pindex].array[index]);
	},

		// 招聘人数
		bindinputRecruitingNumber(e) {
			let pindex = e.target.dataset.index; //数组下标
			let thisDatall = this.data.datall;
	
			thisDatall[pindex].recruitingNumber = e.detail.value  //赋值
			this.setData({
				datall: thisDatall
			})
		},
	// 学历要求
	bindPickerChange2(e){
    let ppindex = 'datall['+e.target.dataset.index+'].index2';
		console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
			// i:e.detail.value,
			[ppindex]:e.detail.value,
			color2:"#000"
		})
	},
	// 经验要求
	bindPickerChange3(e){
    let ppindex = 'datall['+e.target.dataset.index+'].index3';
		console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
			// i:e.detail.value,
			[ppindex]:e.detail.value,
			color3:"#000"
		})
	},
	// 年龄要求
	bindPickerChange4(e){
    let ppindex = 'datall['+e.target.dataset.index+'].index4';
		console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
			// i:e.detail.value,
			[ppindex]:e.detail.value,
			color4:"#000"
		})
	},
	// 招聘人姓名
	namephone(e) {
		let pindex = e.target.dataset.index; //数组下标
		let thisDatall = this.data.datall;

		thisDatall[pindex].recruitingNumber = e.detail.value  //赋值
		this.setData({
			datall: thisDatall
		})
	},
	// 招聘联系电话
	bindinputRecruitingNumber(e) {
		let pindex = e.target.dataset.index; //数组下标
		let thisDatall = this.data.datall;

		thisDatall[pindex].recruitingNumber = e.detail.value  //赋值
		this.setData({
			datall: thisDatall
		})
	},
  /**获取岗位名称**/
	setInputValue2: function (e) {
		let pindex = e.target.dataset.index; //数组下标
		let thisDatall = this.data.datall;
		thisDatall[pindex].jobName = e.detail.value  //赋值
		this.setData({
			datall: thisDatall
		})
		// console.log(this.data.datall[pindex].jobName);
	},
	/**岗位需求**/
	setInputValue3: function (e) {
		let pindex = e.target.dataset.index; //数组下标
		let thisDatall = this.data.datall;
		thisDatall[pindex].jobIntroduction = e.detail.value  //赋值
		this.setData({
			datall: thisDatall
		})
	},

  /****删除*/
  deletePrice: function (e) {
    let that = this
    let index = e.target.dataset.index //数组下标
    let arrayLength = that.data.datall.length //数组长度
    let newArray = []
    if (arrayLength > 1) {
      //数组长度>1 才能删除
      for (let i = 0; i < arrayLength; i++) {
        if (i !== index) {
          newArray.push(that.data.datall[i])
        }
      }
      that.setData({
        datall: newArray
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '必须设置一个设备',
        success (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }
	},
	// 确认发布
	async qrfb(){
		let that = this;
		// 遍历检查名称
		let isEmptyJobName, isEmptyRecruitingNumber, isEmptyJobIntroduction = false;
		this.data.datall.forEach((val) => {
			string_util.isEmpty(val.jobName) ? isEmptyJobName = true : false;
			string_util.isEmpty(val.recruitingNumber) ? isEmptyRecruitingNumber = true : false;
			string_util.isEmpty(val.jobIntroduction) ? isEmptyJobIntroduction = true : false;
		})
		if (isEmptyJobName) {
			wx.showModal({
				title: '提示',
				content: '请填写岗位名称',
			})
			return;
		}
		if (isEmptyRecruitingNumber) {
			wx.showModal({
				title: '提示',
				content: '请正确填写招聘人数',
			})
			return;
		}
		if (isEmptyJobIntroduction) {
			wx.showModal({
				title: '提示',
				content: '请填写岗位需求',
			})
			return;
		}
		// 提交数据
		Loading.begin();
		this.data.datall.forEach(async (val) => {
			let submitData = {
				jobName: val.jobName,
				categoryName: val.jobName,
				jobIntroduction: val.jobIntroduction,
				jobSalaryMin: val.jobSalaryMin,
				jobSalaryMax: val.jobSalaryMax,
				jobAddress: this.data.companyAddress,
				recruitingNumber: val.recruitingNumber,
				recruiterTelephone:this.data.recruiterTelephone,
				companyUuid: this.data.companyUuid,
				companyName: this.data.companyName,
				recruiterOpenid: "",
			}
			console.log(PAGENAME + '提交数据:'); console.log(submitData);
			let submitPromise = recruitJobService.insertByEntity(submitData)
			await submitPromise.then(r => {
				console.log(r);
				wx.showToast({
					title: '添加成功',
					icon: 'success',
					duration: 2000
				})
				wx.navigateTo({
					// url: '/pages/qylr/qylr',
					url:'/pages/qylr/qylr?communityUuid='+that.data.communityUuid
				})
			}).catch(r => {
				console.error(r);
			});
		});
		Loading.end();

	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad:async function (options) {
		console.log(options)
		Loading.begin();
		let that = this;
		await app.getOpenidReady();
		that.setData({
			companyUuid: options.companyUuid,
			communityUuid:options.communityUuid
		})
		console.log(  "招聘人企业id:" + options.companyUuid);

		let loadCompanyPromise = recruitCompanyService.loadEntityById(options.companyUuid);
		console.log(loadCompanyPromise)
		await loadCompanyPromise.then(r => {
			console.log(r);
			that.setData({
				companyName: r.data.companyName,
				flagIdentification: r.data.flagIdentification,
				recruiterTelephone:r.data.juridicalPhone
			})
		}).catch(r => {
			console.error(r);
		})
		// 加载完毕
		Loading.end();
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