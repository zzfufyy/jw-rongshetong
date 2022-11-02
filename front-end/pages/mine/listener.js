// mine.js 监听器方法
const GLOBAL_CONSTANT = require('../../common/globalConstant');

const createListenerMethods = () => ({

    // 个人中心 用户角色变化 监听函数
    async mineUserRoleChangeListener() {
        let that = this;
        let userRole = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY)
        if (userRole == 'user') {
            wx.setNavigationBarTitle({
                title: that.pageConstant.navigateTitleCandidate,
            })
        } else if (userRole == 'company') {
            wx.setNavigationBarTitle({
                title: that.pageConstant.navigateTitleCompany,
            })
        }
    },


});

module.exports = {
    createListenerMethods: createListenerMethods,
}