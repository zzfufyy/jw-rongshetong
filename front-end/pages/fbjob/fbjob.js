// pages/fbjob/fbjob.js
const CONSTANT = require('../../common/constant');
const string_util = require('../../utils/string_util');
const object_util = require('../../utils/object_util');

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
        recruiterOpenid: '',
        recruiterName: '',
        recruiterTelephone: '',
        // 是否已认证
        flagIdentification: 0,
        companyUuid: '',
        companyName: '',
        companyAddress: '',
        tzym: "",
        jobList: [{
            array: CONSTANT.salaryList.map((val) => {
                return val.value
            }),
            // salay范围index
            index: 0,
            jobName: '',
            jobSalaryMin: CONSTANT.salaryList[0].min,
            jobSalaryMax: CONSTANT.salaryList[0].max,
            jobBeginTime: null,
            jobEndTime: null,
            jobIntroduction: '',
            jobRequire:"",
            jobRequireMajor: '',
            jobRequireSkill: '',
            jobBasicWorkfare: '',
            recruitingNumber: '',
            pindex: dataJobNum,
            ppindex: 0,
            sbpp: "",
        }, ],
        dyxz: "",
    },
    lock: {},
    /** BEGIN 页面绑定事件 */
    bindchangeJobBeginTime: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            [`jobList[${e.target.dataset.index}].jobBeginTime`]: e.detail.value
        })
    },
    bindchangeJobEndTime: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            [`jobList[${e.target.dataset.index}].jobEndTime`]: e.detail.value
        })
    },
    // 继续添加
    bindtapContinueAdd(e) {
        let that = this
        dataJobNum++;
        let newitem = {
            array: CONSTANT.salaryList.map((val) => {
                return val.value
            }),
            // salay范围index
            index: 0,
            jobName: '',
            jobSalaryMin: CONSTANT.salaryList[0].min,
            jobSalaryMax: CONSTANT.salaryList[0].max,
            jobIntroduction: '',
            recruitingNumber: '',
            pindex: dataJobNum,
            ppindex: 0,
            sbpp: "",
        }
        this.setData({
            jobList: that.data.jobList.concat(newitem),
        })
        console.log(this.data.jobList)
    },

    // 岗位名称
    bindinputJobName: function (e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].jobName`]: e.detail.value
        })

    },
    // 薪资范围
    bindchangeChoooseSalary(e) {
        let index = e.detail.value;
        this.setData({
            [`jobList[${e.target.dataset.index}].jobSalaryMin`]: CONSTANT.salaryList[index].min,
            [`jobList[${e.target.dataset.index}].jobSalaryMax`]: CONSTANT.salaryList[index].max,
            [`jobList[${e.target.dataset.index}].index`]: index,
            color: "#000"
        })
    },
    // 招聘人数
    bindinputRecruitingNumber(e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].recruitingNumber`]: e.detail.value
        })
    },
    // 岗位介绍
    bindinputJobIntroduction: function (e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].jobIntroduction`]: e.detail.value
        })
    },
    // 专业要求
    bindinputJobRequireMajor: function (e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].jobRequireMajor`]: e.detail.value
        })
    },
    // 技能要求
    bindinputJobRequireSkill: function (e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].jobRequire`]: e.detail.value
        })
    },
    // 工作福利
    bindinputJobBasicWorkfare: function (e) {
        this.setData({
            [`jobList[${e.target.dataset.index}].jobBasicWorkfare`]: e.detail.value
        })
    },

    // 删除
    bindtapDeleteIcon: function (e) {
        // debugger;
        let that = this
        let index = e.target.dataset.index //数组下标
        console.log(index);
        console.log(this.data.jobList);
        let arrayLength = this.data.jobList.length //数组长度
        let currentDatallList = object_util.copyObject(this.data.jobList);
        console.log(currentDatallList)
        wx.showModal({
            title: '提示',
            content: '是否确认删除',
            success(res) {
                if (res.confirm) {

                    if (arrayLength > 1) {
                        currentDatallList.splice(index, 1);
                        that.setData({
                            jobList: currentDatallList,
                        })
                        console.log(that.data.jobList);
                    } else {
                        wx.showModal({
                            title: '提示',
                            content: '需发布至少一个职位',
                            success(res) {
                                if (res.confirm) {
                                    console.log('用户点击确定')
                                } else if (res.cancel) {
                                    console.log('用户点击取消')
                                }
                            }
                        })
                    }

                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })

    },
    /** END 页面绑定事件 */
    // 确认发布
    bindtapSubmit: async function () {
        let that = this;
        // 遍历检查名称
        let isEmptyJobName, isEmptyRecruitingNumber, isEmptyJobIntroduction = false;
        this.data.jobList.forEach((val) => {
            isEmptyJobName = string_util.isEmpty(val.jobName) ? true : false;
            isEmptyRecruitingNumber = string_util.isEmpty(val.recruitingNumber) ? true : false;
            isEmptyJobIntroduction = string_util.isEmpty(val.jobIntroduction) ? true : false;
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
        try {

            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                this.data.jobList.forEach(async (val, index) => {
                    let submitData = {
                        jobName: val.jobName,
                        categoryName: val.jobName,
                        jobIntroduction: val.jobIntroduction,
                        jobRequireMajor: val.jobRequireMajor,
                        jobRequire: val.jobRequire,
                        jobBasicWorkfare: val.jobBasicWorkfare,
                        jobSalaryMin: val.jobSalaryMin,
                        jobSalaryMax: val.jobSalaryMax,
                        jobBeginTime: val.jobBeginTime,
                        jobEndTime: val.jobEndTime,
                        jobAddress: this.data.companyAddress,
                        recruitingNumber: val.recruitingNumber,
                        companyUuid: this.data.companyUuid,
                        companyName: this.data.companyName,
                        recruiterOpenid: this.data.recruiterOpenid,
                        recruiterName: this.data.recruiterName,
                        recruiterTelephone: this.data.recruiterTelephone,
                    }
                    console.debug(`提交发布职位 ${index}:`, submitData);
                    await recruitJobService.insertByEntity(submitData)
                });
            } else {
                return;
            }
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end(this.lock);
        }


        let flagIdentification = this.data.flagIdentification;
        console.log(PAGENAME + '企业验证状态: ' + flagIdentification);
        wx.showToast({
            title: '发布成功',
            icon: 'success',
            duration: 2000,
            success: function () {
                // 跳转 企业认证中
                console.log(PAGENAME + '发布成功 -> 页面跳转');
                if (flagIdentification == 0) {
                    if (that.data.tzym == "jxgl") {
                        wx.navigateBack({
                            changed: true
                        });
                    } else {
                        wx.redirectTo({
                            url: '/pages/waiteyz/waiteyz',
                        })
                    }

                } else if (flagIdentification == 1) {
                    if (that.data.tzym == "jxgl") {
                        wx.navigateBack({
                            changed: true
                        });
                    } else {
                        let tabPagePath = '/pages/mine/mine';
                        let pages = getCurrentPages();
                        let prevPage = pages[pages.length - 2]; // 路由-1
                        if (prevPage.route == tabPagePath) {
                            wx.switchTab({
                                url: tabPagePath,
                            })
                        } else {
                            wx.navigateBack({
                                delta: 1
                            });
                        }

                    }

                }
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        // 初始化loading锁
        this.lock = new Loading.Lock();
        // 页面加载开始
        let tzym = options.tzym
        this.setData({
            tzym: tzym
        })
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        this.setData({
            recruiterOpenid: openid,
        })
        console.debug('招聘人id:' + openid)
        // 加载 招聘人信息 招聘公司信息
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                let recruiterData = await userRecruiterService.loadEntityById(openid);
                recruiterData = recruiterData.data;
                this.setData({
                    companyUuid: recruiterData.companyUuid,
                    recruiterName: recruiterData.realName,
                });
                let companyData = await recruitCompanyService.loadEntityById(this.data.companyUuid);
                companyData = companyData.data;
                this.setData({
                    companyName: companyData.companyName,
                    flagIdentification: companyData.flagIdentification,
                    recruiterTelephone: companyData.juridicalPhone
                })
            } else {
                return;
            }

        } catch (e) {
            console.error(e)
        } finally {
            // 加载完毕
            Loading.end(this.lock);
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