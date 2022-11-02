// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
const string_util = require('../../utils/string_util');

// 加载服务类
const userForFormService = require('../../common/userForFormService');

// 定义常量
const app = getApp();


const createPageMethods = () => ({
    // 分页状态
    stateDetailUserFormList: {
        pageConfigDetailUserFormList: new Paging.PageConfig(1), 
    },
    // 清空内容
    _clearDetailUserFormList: function () {
        this.stateDetailUserFormList.pageConfigDetailUserFormList.reset();
        this.setData({
            detailUserFormList: []
        })
    },

    // 加载分页内容
    _loadDetailUserFormList: async function (pageNum) {

        // 初始化分页配置
        let pageConfig = this.stateDetailUserFormList.pageConfigDetailUserFormList;
        pageConfig.setNoMoreDataCallback();
        // 构建查询条件 condition
        let condition = {
            formUuid: this.data.formUuid,
        }
        
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
            pageInfo = (await userForFormService.pageDetailUserForm(pagingParam)).data;
        } catch (e) {
            console.error(e);
        } finally {
        }
        // 去除list
        let {list, ...resPageInfo} = pageInfo;
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.detailUserFormList;
        let newList = dataList.map(v => ({
            formUuid: v.formUuid,
            userOpenid: v.userOpenid,
            submitTime: date_util.dateToYYYYMMDD(v.submitTime),
            userName: string_util.isEmpty(v.userName)? '未知用户': v.userName,
            ...resPageInfo,
        }));

        

        // 拼接数据
        this.setData({
            detailUserFormList: newList,
        });
    },
});

module.exports = {
    createPageMethods: createPageMethods,
}