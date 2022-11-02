// pages/mine/mine.js
const { UserService } = require("../../service/user_service");
const CONSTANT = require('../../common/constant');
const recruitee = require('./recruitee');
const recruiter = require('./recruiter');
const iUserRoleService = require('../../common/iUserRoleService');

const app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        personinfo: [{
            // gender: 0,
            // tximg: '',
            // name: '',
            // sex: '',
            // year: '23',
            // cellphone: ' ',
            // jobname: '保洁人员',
            // money: '',
            // msgw: '0',
            // ytdgw: '127',
            // byll: '',
        }],
        ytdgw: "",
        msyq: "",
        byll: '',
        zw: "",
        msgw: 0,
        interviewResultCount: 0,
        companyinfo: [{
            // gender: 0,
            // tximg: '/img/tx.png',
            // name: '张三',
            // // sex:'女',
            // // year:'23',
            // companyname: '长沙竟网信息科技有限公司',
            // jobname: '保洁人员',
            // money: '3000-5000',
            // msgw: '0',
            // ytdgw: '127',
            // byll: '55',
        }],

        // 默认身份为求职方
        // TODO: 使用加载条
        isRecruitee: true,
        // 默认求职者
        identity: CONSTANT.UserRole.Recruitee,
    },
    pageConstant: {
        navigateTitleCandidate: '个人中心',
        navigateTitleCompany: '企业中心',
    },
    // 错误头像处理
    binderrorCandidatePortrait(e) {
        console.error(e);
        let img = 'personinfo[' + e.currentTarget.dataset.index + '].tximg';
        this.setData({
            [img]: Boolean(this.data.personinfo[e.currentTarget.dataset.index].gender) ?
                CONSTANT.STATIC_IMG_URL.portrait_personal_female : CONSTANT.STATIC_IMG_URL.portrait_personal_male,
        })
    },
    binderrorRecruiterPortrait(e) {
        console.error(e);
        let img = 'companyinfo[' + e.currentTarget.dataset.index + '].tximg';
        this.setData({
            [img]: Boolean(this.data.companyinfo[e.currentTarget.dataset.index].gender) ?
                CONSTANT.STATIC_IMG_URL.portrait_personal_female : CONSTANT.STATIC_IMG_URL.portrait_personal_male,
        })
    },

    _clearUserRole() {
        return UserService.clearUserRole();
    },
    onUnload: function (params) {
    },
    onLoad: async function (params) {
      
    },

    onShow: async function () {
        console.log('个人中心页面加载');
        
        let that = this;
        await app.getOpenidReady();
        // 加载全局 角色
        let role = await iUserRoleService.getUserRole();
        if (role == CONSTANT.UserRole.Recruitee) {
            // 标题修改
            wx.setNavigationBarTitle({
                title: that.pageConstant.navigateTitleCandidate,
            });
            this.setData({
                isRecruitee: true,
                nowsf: CONSTANT.userRoleName.get(role),
            })
        } else if (role == CONSTANT.UserRole.Recruiter) {
            wx.setNavigationBarTitle({
                title: that.pageConstant.navigateTitleCompany,
            });
            this.setData({
                isRecruitee: false,
                nowsf: CONSTANT.userRoleName.get(role),
            })
        }
        // 根据角色加载数据
        if (this.data.isRecruitee) {
            await this.loadRecruiteeInfo();
        } else {
            await this.loadRecruiterInfo();
        }
    },

    ...recruiter.createRecruiterMethods(),
    ...recruitee.createRecruiteeMethods(),
})