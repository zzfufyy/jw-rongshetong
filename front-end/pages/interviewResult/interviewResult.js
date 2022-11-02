// pages/msinvite/msinvite.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const img_util = require('../../utils/img_util');
const recruitRecordService = require('../../common/recruitRecordService');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        pages: 1,
        currentTabIndex: 0,
        candidateOpenid: '',
        sectionTabList: [{
            id: 0,
            tagName: '面试通过',
            checked: true,
            num: 0,
        }, {
            id: 1,
            tagName: '面试未通过',
            checked: false,
            num: 0,
        }],
        interviewSuccessTotal: 0,

        interviewSuccessList: [
            // {
            //     jobName: '清洁工',
            //     salaryScope: '3000-3800',
            //     companyName: '文和友餐饮有限公司',
            //     portraitPath: '/img/tx.png',
            //     distance: '1.5',
            //     juridicalPhone: '13112345678',
            //     juridicalPerson: '张三'
            // },
        ],
        interviewFailList: [
            // {
            //     jobName: '清洁工',
            //     salaryScope: '3000-3800',
            //     companyName: '文和友餐饮有限公司',
            //     portraitPath: '/img/tx.png',
            //     distance: '1.5',
            //     juridicalPhone: '13112345678',
            //     juridicalPerson: '张三'
            // }, 
        ],

    },
    //打电话
    catchtapCallphone(e) {
        wx.makePhoneCall({
            phoneNumber: e.currentTarget.dataset.phone //仅为示例，并非真实的电话号码
        })
    },
    catchtapSwitchTab: function (e) {
        console.log(e);
        let changeIndex = e.currentTarget.dataset.index;
        if (changeIndex == this.data.currentTabIndex) {
            return;
        }
        this.setData({
            currentTabIndex: changeIndex,
        })
    },
    // 滚动切换标签样式 
    bindchangeSwitchTab: function (e) {
        let changeTabIndex;
        let sectionTabList = this.data.sectionTabList.map((v, index) => {
            v.checked = !v.checked;
            if (v.checked) {
                changeTabIndex = index;
            }
            return v;
        });
        this.setData({
            sectionTabList: sectionTabList,
            currentTabIndex: changeTabIndex,
        })
    },

    /** 内部方法 begin  */
    /** 内部方法 end  */

    //沟通中不合适点击事件

    _setPageSystemInfo: async function () {
        try {
            let sysInfo = await wx.getSystemInfo();
            this.setData({
                winWidth: sysInfo.windowWidth,
                winHeight: sysInfo.windowHeight,
            });
        } catch (e) {
            console.error(e);
        }
        return true;
    },
    _loadInterviewSuccessList: async function () {
        // 默认加载面试通过列表
        let successListData = (await recruitRecordService.listRecordForCandidatePlus({
            candidateOpenid: this.data.candidateOpenid,
            flowRecruit: CONSTANT.INTERVIEW_FLOW.INTERVIEW_SUCCESS,
        })).data;
        let interviewSuccessList = successListData.map(v => {
            return {
                jobName: v.recruitJobName,
                salaryScope: new CONSTANT.Salary(v.recruitJobSalaryMin, v.recruitJobSalaryMax).value,
                companyName: v.recruitCompanyName,
                portraitPath: img_util.handleCompanyPortraitPath(v.recruitPortraitPath),
                distance: '1.5',
                juridicalPerson: v.recruitJuridicalPerson,
                juridicalPhone: v.recruitJuridicalPhone,

            }
        });
        let successTabIndex = 0;
        this.setData({
            interviewSuccessList: interviewSuccessList,
            interviewSuccessTotal: interviewSuccessList.length,
            [`sectionTabList[${successTabIndex}].num`]: interviewSuccessList.length,
        })
    },

    _loadInterviewFailList: async function () {
        // 默认加载面试通过列表
        let failListData = (await recruitRecordService.listRecordForCandidatePlus({
            candidateOpenid: this.data.candidateOpenid,
            flowRecruit: CONSTANT.INTERVIEW_FLOW.INTERVIEW_FAIL,
        })).data;
        let interviewFailList = failListData.map(v => {
            return {
                jobName: v.recruitJobName,
                salaryScope: new CONSTANT.Salary(v.recruitJobSalaryMin, v.recruitJobSalaryMax).value,
                companyName: v.recruitCompanyName,
                portraitPath: img_util.handleCompanyPortraitPath(v.recruitPortraitPath),
                distance: '1.5',
                juridicalPerson: v.recruitJuridicalPerson,
                juridicalPhone: v.recruitJuridicalPhone,

            }
        });
        let failTabIndex = 1;

        this.setData({
            interviewFailList: interviewFailList,
            [`sectionTabList[${failTabIndex}].num`]: interviewFailList.length,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        await this._setPageSystemInfo();
        await app.getOpenidReady();
        let openid = app.getOpenid();
        // 测试
        // openid = 'oIZm34my11VAlNvr9EHY1pf1LEPs';
        this.setData({
            candidateOpenid: openid,
        });
        try {
            await Promise.all([
                this._loadInterviewSuccessList(),
                this._loadInterviewFailList(),
            ])
        } catch (e) {
            console.error(e)
        } finally {

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