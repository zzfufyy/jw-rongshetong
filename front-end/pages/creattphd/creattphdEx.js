// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const formInformationService = require('../../common/formInformationService');
const string_util = require('../../utils/string_util');
// 定义常量
const app = getApp();


const createPageMethods = () => ({
    // 分页状态
    state: {
        normalPageConfig: new Paging.PageConfig(7),
        unpublishPageConfig: new Paging.PageConfig(7),

    },
    // 没有更多页面了
    _noMoreData() {
        this.setData({
            noMoreData: true,
        });
    },
    /** BEGIN 清空内容 */
    clearAllContent: async function () {
        this.clearNormalContent();
        this.clearUnpublishContent();
    },
    clearNormalContent: function () {
        this.state.normalPageConfig.reset();
        this.setData({
            formInformationNormalList: []
        })
    },
    clearUnpublishContent: function () {
        this.state.unpublishPageConfig.reset();
        this.setData({
            formInformationUnpublishList: []
        })
    },
    /** END 清空内容 */

    /** BEGIN 加载内容 */
    loadAllContent: async function(){
        await this.loadNormalContent();
        await this.loadUnpublishContent();
    },

    // 加载 Normal 内容
    loadNormalContent: async function () {
        let communityUuid = await this.communityUuidCompleter.promise;
        // 回调 没有数据的处理方式
        this.state.normalPageConfig.setNoMoreDataCallback(this._noMoreData);
        // 初始化分页配置
        let pageConfig = this.state.normalPageConfig;
        // 加載openid
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        // 构建查询条件 condition
        let condition = {
            userOpenid: openid,
            communityUuid: communityUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = (await formInformationService.pageNormal(pagingParam)).data;
            console.debug(pageInfo);
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.formInformationNormalList;
        let newList = dataList.filter(v=> string_util.isNotEmpty(v.formUuid)).map(v => ({
            formUuid: v.formUuid,
            formTitle: v.formTitle,
            beginTime: date_util.dateToYYYYMMDD(v.beginTime),
            completedCount: v.completedCount,
            status: v.status,
        }));
        // 拼接数据
        this.setData({
            formInformationNormalList: current.concat(newList),
        });
    },

    // 加载 下架 内容
    loadUnpublishContent: async function () {
        let communityUuid = await this.communityUuidCompleter.promise;
        // 回调 没有数据的处理方式
        this.state.unpublishPageConfig.setNoMoreDataCallback(this._noMoreData);
        // 初始化分页配置
        let pageConfig = this.state.unpublishPageConfig;
        // 加載openid
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        // 构建查询条件 condition
        let condition = {
            userOpenid: openid,
            communityUuid: communityUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = (await formInformationService.pageUnpublished(pagingParam)).data;
            console.debug(pageInfo);
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.formInformationUnpublishList;
        let newList = dataList.filter(v => string_util.isNotEmpty(v.formUuid)).map(v => ({
            formUuid: v.formUuid,
            formTitle: v.formTitle,
            beginTime: date_util.dateToYYYYMMDD(v.beginTime),
            completedCount: v.completedCount,
            status: v.status,
        }));

        // 拼接数据
        this.setData({
            formInformationUnpublishList: current.concat(newList),
        });
    },
    /** END 加载内容 */

});

module.exports = {
    createPageMethods: createPageMethods,
}