// pages/mine/mine.js
const listener = require('./listener');

const { GlobalKey } = require("../../service/global_service");
const { UserService } = require("../../service/user_service");

const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');

const GLOBAL_CONSTANT = require('../../common/globalConstant')
const CONSTANT = require('../../common/constant');
const recruitee = require('./recruitee');
const recruiter = require('./recruiter');


const recruitCompanyService = require('../../common/recruitCompanyService');
const userRecruiterService = require('../../common/userRecruiterService');
const userStateService = require('../../common/userStateService');
const globalService = require('../../common/globalService');

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

    // 改变身份
    switchUserRole: async function () {
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');

        let isRecruitee = this.data.isRecruitee;
        let currentRole = (isRecruitee ?
            CONSTANT.UserRole.Recruitee : CONSTANT.UserRole.Recruiter
        );
        let targetRole = (isRecruitee ?
            CONSTANT.UserRole.Recruiter : CONSTANT.UserRole.Recruitee
        );
        let currentUserRoleName = CONSTANT.userRoleName.get(currentRole);
        let targetUserRoleName = CONSTANT.userRoleName.get(targetRole);

        console.log(targetRole)
        console.log(CONSTANT.UserRole.Recruiter);

        let shouldSwtich = false;
        let tipModal = await wx.showModal({
            title: '提示',
            content: '您当前是' + currentUserRoleName + '身份，点击[确认]可切换至' + targetUserRoleName + '身份',
        });
        shouldSwtich = tipModal.confirm ? true : false;

        if (shouldSwtich) {
            if (!isRecruitee) {

                // 全局设置用户变量
                await this.loadRecruiteeInfo();
                let updateUserState = {
                    id: openid,
                    userRole: 1,
                }
                await userStateService.updateByEntity(updateUserState);
                globalService.setGlobalUserIdentity(CONSTANT.UserRole.Recruitee)
            } else {
                // 切换到招聘者用户需要进行判断 如果招聘者没有企业  跳转到注册企业
                if (targetRole == CONSTANT.UserRole.Recruiter) {
                    try {
                        Loading.begin();
                        let userRecruiterData = await userRecruiterService.loadEntityById(openid);
                        console.log(userRecruiterData);
                        if (string_util.isEmpty(userRecruiterData.data.companyUuid)) {
                            wx.navigateTo({
                                url: '/pages/qyzc/qyzc',
                            });
                            return;
                        } else {
                            let recruitCompanyData = await recruitCompanyService.loadEntityById(userRecruiterData.data.companyUuid);
                            if (recruitCompanyData.data.flagIdentification != 1) {
                                // 还未认证通过
                                // wx.navigateTo({
                                // 	url: '/pages/waiteyz/waiteyz',
                                // });
                                // return;
                            }
                        }

                    } catch (e) {
                        console.error(e)
                    } finally {
                        Loading.end();
                    }
                }
                let updateUserState = {
                    id: openid,
                    userRole: 2,
                }
                await userStateService.updateByEntity(updateUserState);
                await this.loadRecruiterInfo();
                globalService.setGlobalUserIdentity(CONSTANT.UserRole.Recruiter)
            }
            // 通知用户角色监听器
            app.globalMonitors.userRoleChangeMonitor.notifyAll();

            this.setData({
                isRecruitee: !isRecruitee,
                nowsf: targetUserRoleName,
            });
        }
    },
    onUnload: function (params) {
        app.globalMonitors.userRoleChangeMonitor.removeListener(this.mineUserRoleChangeListener)
    },
    onLoad: async function (params) {
        app.globalMonitors.userRoleChangeMonitor.addListener(this.mineUserRoleChangeListener)
    },

    onShow: async function () {
        console.log('个人中心页面加载');
        let that = this;
        await app.getOpenidReady();
        // 加载全局 角色
        let role = await UserService.loadUserRole();
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
    ...listener.createListenerMethods(),
})