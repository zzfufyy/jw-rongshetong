// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const formSubjectService = require('../../common/formSubjectService');
const string_util = require('../../utils/string_util');
// 定义常量
const app = getApp();


const createPageMethods = () => ({
    // 分页状态
    stateEx: {
        pageConfigSubjectList: new Paging.PageConfig(4), 
    },
    // 清空内容
    _clearSubjectList: function () {
        this.stateEx.pageConfigSubjectList.reset();
        this.setData({
            formSubjectList: []
        })
    },

    // 加载分页内容
    _loadSubjectList: async function () {

        // 初始化分页配置
        let pageConfig = this.stateEx.pageConfigSubjectList;
        pageConfig.setNoMoreDataCallback();
        // 构建查询条件 condition
        let condition = {
            formUuid: this.data.formUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        // let pagingParam = pageConfig.buildParam(5, condition);
        if (!pagingParam) {
            return;
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = (await formSubjectService.page(pagingParam)).data;
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.formSubjectList;
        let newList = dataList.map(v => ({
            formUuid: v.formUuid,
            subjectUuid: v.id,
            subjectTitle: v.subjectTitle,
            componentType: v.componentType,
            subjectType: v.subjectType,
            isLoad: false,
        }));
        // 拼接数据
        this.setData({
            formSubjectList: current.concat(newList),
        });
    },
});

module.exports = {
    createPageMethods: createPageMethods,
}