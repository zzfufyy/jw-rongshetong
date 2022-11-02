// 加载常量
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
// 加载工具类
const $ = require('../../utils/request_util');
const Loading = require('../../utils/loading_util');
const {
    Completer
} = require('../../utils/function_util');
// 加载服务
const bcUserWxService = require("../../common/bcUserWxService");
const userCandidateService = require("../../common/userCandidateService");
const userRecruiterService = require("../../common/userRecruiterService");
const userCommunityService = require("../../common/userCommunityService");
const userStateService = require("../../common/userStateService");
const globalService = require("../../common/globalService");


const app = getApp();
Component({
    options: {
        multipleSlots: true // 在组件定义时的选项中启用多slot支持
    },
    /**
     * 组件的属性列表
     */
    properties: {},

    /** 组件数据 */
    data: {
        openid: '',
        sessionKey: '',
        token: '',

        hiddenPopup: true,
        isEverAuth: false,
    },

    /** 组件方法 */
    methods: {
        // 弹框 show hide
        showPopup() {
            this.setData({
                hiddenPopup: false,
            })
        },
        hidePopup: function () {
            this.setData({
                hiddenPopup: true,
            })
        },
        //  暴露方法接口  引导用户授权  */
        doAuthorize: async function (scene) {
            // await app.getAppInitReady();
            console.log(app.globalData.appId)
            var that = this;
            // 获取状态码
            try {
                let loginRes = await wx.login({
                    timeout: 5000, // 5秒login超时
                });
                let loginData = (await $.request({
                    url: '/wx/user/' + app.globalData.appId + '/login',
                    data: {
                        code: loginRes.code,
                        loginType: 'openid'
                    },
                    header: app.globalData.header,
                })).data;
                console.info(loginData);
                this.setData({
                    openid: loginData.openid,
                    sessionKey: loginData.sessionKey,
                    token: loginData.token
                })
                wx.setStorageSync('sessionKey', loginData.sessionKey);
                if (typeof (loginData.userWx) == 'undefined') {
                    // 用户未授权 popup授权框
                    that.setData({
                        isEverAuth: false,
                        hiddenPopup: false,
                    });

                    // 显示 授权页面 authCompleter在授权页面完成
                } else {
                    // 用户已授权
                    that.setData({
                        isEverAuth: true,
                        hiddenPopup: true,
                    });
                    let userState = await userStateService.loadEntityById(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_OPENID));
                    userState = userState.data;
                    if (typeof (userState) != 'undefined') {
                        globalService.setGlobalUserIdentity(GLOBAL_CONSTANT.userStateNameList[userState.userRole]);
                    }
                    this.authCompleter.resolve(true);
                }
            } catch (e) {
                console.error("authorize失败 程序出错")
                console.error(e);
                this.authCompleter.resolve(false);
            }
            return this.authCompleter.promise;
        },
        // 用户点击取消
        bindtapCancel: async function (e) {
            this.setData({
                hiddenPopup: true,
            })
            await this._saveUserInfoWithoutAuth();
            globalService.setGlobalUserIdentity(CONSTANT.UserRole.Vistor);
            this.authCompleter.resolve(false);
        },
        // 用户点击允许授权(后续还需要点击允许)
        bindtapGetUserProfile: async function (e) {
            const scene = this.data.scene;
            var that = this;
            wx.getUserProfile({
                desc: "获取你的昵称、头像", // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
                success: async (res) => {
                    // 如果用户同意
                    this.setData({
                        hiddenPopup: true,
                    });
                    try {
                        Loading.begin();
                        await that._saveUserInfoWithAuth(res.userInfo, res.iv, res.signature, res.encryptedData, res.rawData, scene);
                    } catch (e) {
                        console.error(e)
                    } finally {
                        Loading.end();
                    }
                    this.authCompleter.resolve(true);
                },
                fail: async (res) => {
                    this.setData({
                        hiddenPopup: true,
                    });
                    await this._saveUserInfoWithoutAuth();
                    console.info("用户没有给予授权： 以游客身份登录");
                    this.authCompleter.resolve(false);
                },
            });
        },
        // 未授权 保存到后台
        _saveUserInfoWithoutAuth: async function () {
            // 初始化bc_user_wx
            let openid = this.data.openid;
            let insertBcUserWxData = {
                openid: openid,
                appId: app.globalData.appId,
            }
            await Promise.allSettled([
                bcUserWxService.insertByEntity(insertBcUserWxData),
                // 求职者用户初始化
                userCandidateService.insertByEntity({
                    id: openid,
                }),
                // 招聘人用户初始化
                userRecruiterService.insertByEntity({
                    id: openid,
                }),
                // 用户社区表初始化
                userCommunityService.insertByEntity({
                    id: openid,
                }),
                // 用户状态表初始化
                userStateService.insertByEntity({
                    id: openid,
                    userRole: 0,
                }),
            ]);

        },
        // 根据授权 保存到后台
        _saveUserInfoWithAuth: async function (userInfo, iv, signature, encryptedData, rawData, scene) {
            const that = this;
            //保存用户信息 到bc_user
            var openid = wx.getStorageSync('openid');
            console.log(openid);
            let wxInfo = await $.request({
                url: '/wx/user/' + app.globalData.appId + '/info',
                data: {
                    sessionKey: that.data.sessionKey,
                    iv: iv,
                    signature: signature,
                    encryptedData: encryptedData,
                    rawData: rawData,
                    openid: openid
                },
                header: app.globalData.header,
            });
            wx.setStorageSync('user', wxInfo.data);
            // 求职者用户初始化
            let insertCandidateData = {
                id: openid,
                realName: userInfo.nickName,
                gender: userInfo.gender,
                portraitPath: userInfo.avatarUrl,
                communityUuid: that.data.communityUuid
            }
            await userCandidateService.insertByEntity(insertCandidateData);
            // 招聘人用户初始化
            let insertRecruiterData = {
                id: openid,
                realName: userInfo.nickName,
                portraitPath: userInfo.avatarUrl,
            }
            await userRecruiterService.insertByEntity(insertRecruiterData);
            // 社区用户初始化
            let insertUserCommunityData = {
                id: openid,
                realName: userInfo.nickName,
                portraitPath: userInfo.avatarUrl,
            }
            await userCommunityService.insertByEntity(insertUserCommunityData);
            let insertUserStateData = {
                id: openid,
            }
            await userStateService.insertByEntity(insertUserStateData);

        },

        _markUserEntrancePage: async function () {
            /**
             * 获取页面栈
             * 标记用户入口
             */
            await this.authCompleter.promise;
            let pages = getCurrentPages();
            let options = pages[0].options;
            if (pages.length == 1) {
                console.info(`标记当前入口页面${pages[0].route}`);
                userStateService.updateByEntity({
                    id: app.getOpenid(),
                    loginPage: pages[0].route,
                    loginPageParam: Object.entries(options).toString(),
                })
            }
        },

    },
    authCompleter: new Completer(),

    /** 组件生命周期 */
    lifetimes: {
        created: function () {
            this.authCompleter = new Completer();
        },
        attached: function () {

        },
        ready: async function () {
            // 标记入口
            await this._markUserEntrancePage();

        }
    },

    /** 页面生命周期 */
    pageLifetimes: {
        show: function () {},
        hide: function () {
            // 页面被隐藏
        },
        resize: function (size) {
            // 页面尺寸变化
        }
    }
})