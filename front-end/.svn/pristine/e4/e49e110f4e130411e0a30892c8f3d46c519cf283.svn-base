// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const userForFormService = require('../../common/userForFormService');
const string_util = require('../../utils/string_util');
// 定义常量
const app = getApp();


const createPageMethods = () => ({
    // 分页状态
    statePageCompletion: {
        pageConfigCompletionList: new Paging.PageConfig(1),
    },

    // 添加信息到题目
    _buildCompletionList: async function (subjectUuid, pageNum) {
        let data = await this._loadCompletionList(subjectUuid, pageNum);
        return {
            ...data,
        }
    },

    // 加载分页内容
    _loadCompletionList: async function (subjectUuid, pageNum) {

        // 初始化分页配置
        let pageConfig = this.statePageCompletion.pageConfigCompletionList;
        pageConfig.setNoMoreDataCallback();

        // 构建查询条件 condition
        console.info(`Begin to load completionList which subjectUuid is ${subjectUuid}`);
        let condition = {
            subjectUuid: subjectUuid,
        }
        // let pagingParam = pageConfig.buildParam(pageNum, condition);
        let pagingParam;
        if (typeof (pageNum) == 'undefined') {
            pagingParam = pageConfig.buildNextParam(condition);
            if (!pagingParam) {
                return;
            }
        } else {
            pagingParam = pageConfig.buildParam(pageNum,condition);
            if (!pagingParam) {
                return;
            }
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = (await userForFormService.page(pagingParam)).data;
            console.debug(pageInfo);
        } catch (e) {
            console.error(e);
        } finally { }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        return {
            ...pageInfo,
            dataList: dataList,
        }
        // dataList.map(v => ({
        //     userForFormUuid: v.id,

        //     formUuid: v.formUuid,
        //     subjectUuid: v.id,
        //     subjectTitle: v.subjectTitle,
        //     componentType: v.componentType, 
        //     subjectType: v.subjectType, 
        // }));
    },
});

module.exports = {
    createPageMethods: createPageMethods,
}