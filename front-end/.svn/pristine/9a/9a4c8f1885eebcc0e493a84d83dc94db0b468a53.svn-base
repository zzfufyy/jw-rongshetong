// pages/msinvite/msinvite.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const img_util = require('../../utils/img_util');
const recruitRecordService = require('../../common/recruitRecordService');
const string_util = require('../../utils/string_util');
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
        interviewInvitationTotal: 0,

        interviewInvitationList: [
            // {
            //     id: '',
            //     jobUuid: '',
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
    catchtapUnsuitable: async function (e) {
        let id = e.currentTarget.dataset.id;
        if (string_util.isEmpty(id)) {
            return;
        }
        let modalRs = await wx.showModal({
            title: '提示',
            content: '是否确定放弃此条面试邀请',
            showCancel: true,
        });
        if (modalRs.confirm) {
            try {
                Loading.begin();
                let updateData = {
                    id: id,
                    flowRecruit: CONSTANT.FLOW_RECRUIT.UNSUITABLE,
                    flagResult: CONSTANT.FLOW_RESULT.FAIL,
                }
                await recruitRecordService.updateByEntity(updateData);
                // 更新页面
                let interviewInvitationList = this.data.interviewInvitationList;
                let index = interviewInvitationList.findIndex(v => {
                    return v.id = id;
                })
                interviewInvitationList.splice(index, 1);
                this.setData({
                    interviewInvitationList: interviewInvitationList,
                    interviewInvitationTotal: this.data.interviewInvitationTotal - 1,
                })
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end();
            }
        } else {
            return;
        }
    },

    /** 内部方法 begin  */
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
    _loadInterviewInvitationList: async function () {
        // 默认加载面试通过列表
        let successListData = (await recruitRecordService.listRecordForCandidatePlus({
            candidateOpenid: this.data.candidateOpenid,
            flowRecruit: CONSTANT.INTERVIEW_FLOW.WAIT_FOR_INTERVIEW,
        })).data;
        let interviewInvitationList = successListData.map(v => {
            return {
                id: v.id,
                jobUuid: v.jobUuid,
                jobName: v.recruitJobName,
                salaryScope: new CONSTANT.Salary(v.recruitJobSalaryMin, v.recruitJobSalaryMax).value,
                companyName: v.recruitCompanyName,
                portraitPath: img_util.handleCompanyPortraitPath(v.recruitPortraitPath),
                distance: '1.5',
                juridicalPerson: v.recruitJuridicalPerson,
                juridicalPhone: v.recruitJuridicalPhone,
            }
        });
        this.setData({
            interviewInvitationList: interviewInvitationList,
            interviewInvitationTotal: interviewInvitationList.length,
        })
    },
    /** 内部方法 end  */
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
            Loading.begin();
            await this._loadInterviewInvitationList();
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