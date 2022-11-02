// pages/gzjl/gzjl.js
const string_util = require('../../utils/string_util');
const prompt_util = require('../../utils/prompt_util');
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');

const jobExperienceService = require('../../common/jobExperienceService');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 默认全职
        jobType: CONSTANT.JOB_TYPE.FULL_TIME,

        jobExperienceUuid: '',
        candidateOpenid: '',
        companyName: '',
        jobName: '',
        jobBeginTime: '',
        jobEndTime: '',
        jobContent: '',

    },
    /** 页面绑定事件 begin */
    bindinputCompanyName: function (e) {
        this.setData({
            companyName: e.detail.value,
        })
    },
    bindinputJobName: function (e) {
        this.setData({
            jobName: e.detail.value,
        })
    },
    bindchangeJobBeginTime: function (e) {
        this.setData({
            jobBeginTime: e.detail.value
        })
    },
    bindchangeJobEndTime: function (e) {
        this.setData({
            jobEndTime: e.detail.value
        })
    },
    bindinputJobContent: function (e) {
        this.setData({
            jobContent: e.detail.value,
        })
    },
    bindtapSave: async function () {
        // 检测
        if (string_util.isEmpty(this.data.companyName)) {
            prompt_util.showModalPrompt("请填写公司名称");
            return;
        }
        if (string_util.isEmpty(this.data.jobName)) {
            prompt_util.showModalPrompt("请填写职位名称");
            return;
        }
        if (string_util.isEmpty(this.data.jobBeginTime)) {
            prompt_util.showModalPrompt("请选择开始时间");
            return;
        }
        if (string_util.isEmpty(this.data.jobEndTime)) {
            prompt_util.showModalPrompt("请选择结束时间");
            return;
        }
        if (string_util.isEmpty(this.data.jobContent)) {
            prompt_util.showModalPrompt("请填写工作内容");
            return;
        }
        if (string_util.isEmpty(this.data.jobExperienceUuid)) {
            // 插入
            await jobExperienceService.insertByEntity({
                candidateOpenid: this.data.candidateOpenid,
                companyName: this.data.companyName,
                jobName: this.data.jobName,
                jobBeginTime: this.data.jobBeginTime,
                jobEndTime: this.data.jobEndTime,
                jobContent: this.data.jobContent,
                jobType: this.data.jobType,
            })
        } else {
            // 更新
        }

        wx.navigateBack({
            delta: 1
        });
    },
    /** 页面绑定事件 end */

    /** 内部方法 begin */
    _loadContent: async function () {
        let jobExperienceData = (await jobExperienceService.loadEntityById(this.data.jobExperienceUuid)).data;
        this.setData({
            candidateOpenid: jobExperienceData.candidateOpenid,
            companyName: jobExperienceData.companyName,
            jobName: jobExperienceData.jobName,
            jobBeginTime: jobExperienceData.jobBeginTime,
            jobEndTime: jobExperienceData.jobEndTime,
            jobContent: jobExperienceData.jobContent,
            jobType: jobExperienceData.jobType,
        })
    },

    /** 内部方法 end */

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        await app.getOpenidReady();
        let candidateOpenid = app.getOpenid();
        this.setData({
            candidateOpenid: candidateOpenid,
        });
        // 工作经历类型  全职 实习.
        if (options.jobType) {
            this.setData({
                jobType: options.jobType,
            })
        }
        console.log(this.data.jobType)

        if (options.jobExperienceUuid) {
            this.setData({
                jobExperienceUuid: options.jobExperienceUuid,
            })
            try {
                Loading.begin();
                await this._loadContent();
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end();
            }
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