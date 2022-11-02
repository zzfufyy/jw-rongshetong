// 加载 工具类
const Paging = require('../../utils/paging_util');
const CONSTANT = require('../../common/constant');

const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const jobFairService = require('../../common/jobFairService');
// 定义常量
const app = getApp();

const createPageMethods = () => ({
    // 分页状态
    state: {
        pageConfig: new Paging.PageConfig(5),
    },
    // 没有更多页面了
    _noMoreData() {
        this.setData({
            noMoreData: true,
        });
    },
    reloadContent: async function () {
        await this.clearContent();
        await this.loadContent();
    },
    // 清空内容
    clearContent: async function () {
        this.state.pageConfig.reset();
        this.setData({
            fairList: []
        })
    },
    loadContent: async function () {
        // 回调 没有数据的处理方式
        this.state.pageConfig.setNoMoreDataCallback(this._noMoreData);
        let pageConfig = this.state.pageConfig;
        // 构建查询条件 condition
        let condition = {
            fairTitle: this.data.searchText,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await jobFairService.page(pagingParam);
            pageInfo = pageInfo.data;
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let currentList = this.data.fairList;
        console.debug(currentList)
        let newList = dataList.map(v => ({
            fairUuid: v.id,
            fairTitle: v.fairTitle,
            fairContent: v.fairContent,
            fairBeginTime: date_util.dateToYYYYMMDD(v.fairBeginTime),
            fairPortraitPath: img_util.handleFairPortraitPath(v.fairPortraitPath),
        }));
        // 拼接数据
        this.setData({
            fairList: currentList.concat(newList),
        });

    }

});

module.exports = {
    createPageMethods: createPageMethods,
}