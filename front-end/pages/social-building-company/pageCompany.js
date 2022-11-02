// 加载 工具类
const Paging = require('../../utils/paging_util');
const string_util = require('../../utils/string_util');
// 加载服务类
const recruitCompanyService = require('../../common/recruitCompanyService');
// 定义常量
const app = getApp();
const createPageMethods = () => ({
    // 分页状态
    state: {
        pageConfig: new Paging.PageConfig(8),
    },
    // 清空内容
    clearContentCompanyList: async function () {
        this.state.pageConfig.reset();
        // 重置数据内容
        this.setData({
            companyList: []
        })
    },
    // 加载内容
    loadContentCompanyList: async function () {
        this.state.pageConfig.setNoMoreDataCallback();

        let buildingUuid = this.data.buildingUuid;
        let searchCompanyName = this.data.searchCompanyName;
        // 初始化分页配置
        let pageConfig = this.state.pageConfig;

        // 构建查询条件 condition
        let condition = {
            buildingUuid: buildingUuid,
            companyName: searchCompanyName,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        console.log(pagingParam)
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await recruitCompanyService.pageJobNameList(pagingParam);
            pageInfo = pageInfo.data;
        } catch (e) {
            console.error(e);
        } finally {}
        console.log(pageInfo);
        let dataList = pageConfig.handlePageInfo(pageInfo);
        console.log(dataList);

        let current = this.data.companyList;
        let companyList = dataList.map(v => ({
            companyUuid: v.companyUuid,
            companyName: v.companyName,
            address: v.address,
            addressDetail: v.addressDetail,
            jobNameList: string_util.isEmpty(v.jobNameList) ? '' : v.jobNameList,
        }));

        // 拼接数据
        this.setData({
            companyList: current.concat(companyList),
        });
    },

});

module.exports = {
    createPageMethods: createPageMethods,
}