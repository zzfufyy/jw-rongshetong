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
        pageConfig: new Paging.PageConfig(7),

    },
    // 没有更多页面了
    _noMoreData() {
        this.setData({
            noMoreData: true,
        });
    },
    // 清空内容
    clearContent: function () {
        this.state.pageConfig.reset();
        this.setData({
            formInformationList: []
        })
    },

    // 加载分页内容
    loadContent: async function () {
        let communityUuid = await this.communityUuidCompleter.promise;
        // 回调 没有数据的处理方式
        this.state.pageConfig.setNoMoreDataCallback(this._noMoreData);
        // 初始化分页配置
        let pageConfig = this.state.pageConfig;
        // 加載openid
        await app.getOpenidReady();
        // 构建查询条件 condition
        let condition = {
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
            pageInfo = (await formInformationService.pagePublished(pagingParam)).data;
            console.debug(pageInfo);
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.formInformationList;
        let newList = dataList.filter(v => string_util.isNotEmpty(v.formUuid)).map(v => ({
            formUuid: v.formUuid,
            formTitle: v.formTitle,
            beginTime: date_util.dateToYYYYMMDD(v.beginTime),
            endTime: string_util.isNotEmpty(v.endTime) ? date_util.dateToYYYYMMDD(v.endTime) : '无期限',
            completedCount: v.completedCount,
            status: v.status,
            isActive: date_util.isTodayAfter(v.endTime)
        }));
        // 拼接数据
        this.setData({
            formInformationList: current.concat(newList),
        });
    },
});

module.exports = {
    createPageMethods: createPageMethods,
}