const GLOBAL_CONSTANT = require('./globalConstant');
const CONSTANT = require('./constant');

const {
    Completer
} = require('../utils/function_util');
const string_util = require('../utils/string_util');

const userStateService = require('./userStateService');
const userCandidateService = require('./userCandidateService');
const userRecruiterService = require('./userRecruiterService');
const app = getApp();

const getUserRole = async function () {
    await app.getOpenidReady();
    let openid = wx.getStorageSync('openid');
    let userRole = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
    let userStateData;
    if (string_util.isEmpty(userRole)) {
        // 全局获取不到尝试从数据库获取
        userStateData = (await userStateService.loadEntityById(openid)).data;
        switch (userStateData.userRole) {
            case 0:
                userRole = CONSTANT.UserRole.Vistor;
                break;
            case 1:
                userRole = CONSTANT.UserRole.Recruitee;
                break;
            case 2:
                userRole = CONSTANT.UserRole.Recruiter;
                break;
            default:
                break;
        }
    }
    if (!userRole) {
        console.error(userStateData);
        console.error(`error: can not get UserRole, where userRole is: ${userRole}`);
    }
    console.info("Now user Identity: " + userRole);
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, userRole);
    return userRole;
}

// 尝试切换至求职者
const trySwitchToCandidate = async function () {
    let switchCompleter = new Completer();
    await app.getOpenidReady();
    let openid = app.getOpenid();
    let userRole = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
    try {
        if (userRole == CONSTANT.UserRole.Recruitee) {
            ;
        } else {
            let wxModal = wx.showModal({
                title: '提示',
                content: '您当前身份不是求职者，需切换为求职者身份',
            });
            let modalRs = await wxModal.then(async res => {
                if (res.confirm) {
                    console.debug(`用户 确认切换至 求职者身份openid:${openid}`)
                    // 保存本次用户登录信息
                    let updateUserState = {
                        id: openid,
                        userRole: 1,
                    }
                    await userStateService.updateByEntity(updateUserState);
                    // 保存登录角色信息
                    wx.showToast({
                        title: '身份切换成功',
                        duration: 1300,
                    })
                    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, CONSTANT.UserRole.Recruitee);
                    return true;
                } else if (res.cancel) {
                    console.debug('用户 取消切换至求职者身份')
                    switchCompleter.resolve(false);
                    return false;
                }
            });
            // 取消切换身份
            if (!modalRs) {
                return switchCompleter.promise;
            }
        }
        // 检查求职者是否有电话
        let userCandidateData = (await userCandidateService.loadEntityById(openid)).data;
        if (string_util.isEmpty(userCandidateData.telephone)) {
            wx.showModal({
                title: '提示',
                content: '请先填写联系方式',
                success: res => {
                    if (res.confirm) {
                        wx.navigateTo({
                            url: '/pages/jlxg/jlxg',
                        })
                    }
                }
            })
            switchCompleter.resolve(false);
        } else {
            switchCompleter.resolve(true);
        }

    } catch (e) {
        switchCompleter.resolve(false);
        console.error("error: 切换身份至求职者 - 程序出错");
    }
    return switchCompleter.promise;
};

// 尝试切换至 招聘者
const trySwitchToRecruiter = async function () {
    let switchCompleter = new Completer();
    await app.getOpenidReady();
    let openid = app.getOpenid();
    let userRole = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
    try {
        if (userRole == CONSTANT.UserRole.Recruiter) {
            console.debug(`用户 已经是招聘者身份openid:${openid}`)
        } else {
            let wxModal = wx.showModal({
                title: '提示',
                content: '您当前身份不是招聘者，需切换为招聘者身份',
            });
            let modalRs = await wxModal.then(async res => {
                if (res.confirm) {
                    console.debug(`用户 确认切换至 招聘者身份openid:${openid}`)
                    // 保存本次用户登录信息
                    let updateUserState = {
                        id: openid,
                        userRole: 2,
                    }
                    await userStateService.updateByEntity(updateUserState);
                    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, CONSTANT.UserRole.Recruiter);
                    // 保存登录角色信息
                    wx.showToast({
                        icon: 'success',
                        title: '身份切换成功',
                        duration: 1300,
                    })
                    return true;
                } else {
                    console.debug('用户 取消切换至招聘者身份')
                    wx.showToast({
                        icon: 'error',
                        title: '用户取消',
                        duration: 1300,
                    })
                    switchCompleter.resolve(false);
                    return false;
                }
            });
            // 取消切换身份
            if (!modalRs) {
                return switchCompleter.promise;
            }
        }
        // 判断用户是否有公司 且 是否通过审核
        let userPlusData = (await userRecruiterService.loadEntityPlusById(openid)).data;
        console.debug(userPlusData);
        // 没有公司id 跳转到注册公司
        if (string_util.isEmpty(userPlusData.companyUuid)) {
            switchCompleter.resolve(false);
            wx.showModal({
                title: '提示',
                content: '请先注册公司',
                success: res => {
                    if (res.confirm) {
                        wx.navigateTo({
                            url: '/pages/qyzc/qyzc',
                        });
                    }
                }
            })
        }
        // 没有审核通过 跳转到等待验证
        if (userPlusData.flagIdentification == 0) {
            switchCompleter.resolve(false);
            wx.navigateTo({
                url: '/pages/waiteyz/waiteyz',
            });
        } else {
            switchCompleter.resolve(true);
        }
    } catch (e) {
        switchCompleter.resolve(false);
        console.error("error: 切换身份至招聘者 - 程序出错");
    }
    return switchCompleter.promise;
};

const trySwitchToRecruiterPure = async function () {
    let switchCompleter = new Completer();
    await app.getOpenidReady();
    let openid = app.getOpenid();
    let userRole = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
    try {
        if (userRole == CONSTANT.UserRole.Recruiter) {
            console.debug(`用户 已经是招聘者身份openid:${openid}`)
            switchCompleter.resolve(true);
            return switchCompleter.promise;
        } else {
            let wxModal = wx.showModal({
                title: '提示',
                content: '您当前身份不是招聘者，需切换为招聘者身份',
            });
            let modalRs = await wxModal.then(async res => {
                if (res.confirm) {
                    console.debug(`用户 确认切换至 招聘者身份openid:${openid}`)
                    // 保存本次用户登录信息
                    let updateUserState = {
                        id: openid,
                        userRole: 2,
                    }
                    await userStateService.updateByEntity(updateUserState);
                    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, CONSTANT.UserRole.Recruiter);
                    // 保存登录角色信息
                    wx.showToast({
                        icon: 'success',
                        title: '身份切换成功',
                        duration: 1300,
                    })
                    switchCompleter.resolve(true);
                } else {
                    console.debug('用户 取消切换至招聘者身份')
                    wx.showToast({
                        icon: 'error',
                        title: '用户取消',
                        duration: 1300,
                    })
                    switchCompleter.resolve(false);
                }
            });
        }
    } catch (e) {
        switchCompleter.resolve(false);
        console.error("error: 切换身份至招聘者 - 程序出错");
    }
    return switchCompleter.promise;
};


// 直接切换到求职者身份
const turnToCandidate = async function () {
    await app.getOpenidReady();
    let openid = app.getOpenid();
    let updateUserState = {
        id: openid,
        userRole: CONSTANT.userRoleValue.Recruitee,
    }
    await userStateService.updateByEntity(updateUserState);
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, CONSTANT.UserRole.Recruitee);
}

// 直接切换到招聘者身份
const turnToRecruiter = async function () {
    await app.getOpenidReady();
    let openid = app.getOpenid();
    let updateUserState = {
        id: openid,
        userRole: CONSTANT.userRoleValue.Recruiter,
    }
    await userStateService.updateByEntity(updateUserState);
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, CONSTANT.UserRole.Recruiter);
}




module.exports = {
    trySwitchToCandidate: trySwitchToCandidate,
    trySwitchToRecruiter: trySwitchToRecruiter,
    trySwitchToRecruiterPure: trySwitchToRecruiterPure,
    getUserRole: getUserRole,
    turnToCandidate: turnToCandidate,
    turnToRecruiter: turnToRecruiter
}