const GLOBAL_CONSTANT = require('../common/globalConstant');
/**
 * 提供  操作  全局变量方法的接口
 */
// 全局 openid
const getGlobalOpenid = function () {
    return wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_OPENID);
}
const setGlobalOpenid = function (openid) {
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_OPENID, openid)
}

// 全局社区id
const getGlobalCommunityUuid = function () {
    return wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_COMMUNITYUUID);
}
const setGlobalCommunityUuid = function (communityUuid) {
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_COMMUNITYUUID, communityUuid);
}

// 全局用户身份
const getGlobalUserIdentity = function () {
    return wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
}
const setGlobalUserIdentity = function (userIdentity) {
    wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY, userIdentity)
}



module.exports = {

    getGlobalOpenid: getGlobalOpenid,
    setGlobalOpenid: setGlobalOpenid,

    getGlobalCommunityUuid: getGlobalCommunityUuid,
    setGlobalCommunityUuid: setGlobalCommunityUuid,

    getGlobalUserIdentity: getGlobalUserIdentity,
    setGlobalUserIdentity: setGlobalUserIdentity,
}
