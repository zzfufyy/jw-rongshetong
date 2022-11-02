// 加载 工具类
const Paging = require('../../utils/paging_util');
const img_util = require('../../utils/img_util');
// 加载服务类
const userForFormService = require('../../common/userForFormService');

// 定义常量
const app = getApp();

const createPageMethods = () => ({
    // 分页状态
    statePageSign: {
        pageConfigSignList: new Paging.PageConfig(4),
    },
    // 添加信息到题目
    _buildSignListInit: async function (subjectUuid) {
        let pageNum = 1;
        let data = await this._loadSignList(subjectUuid, pageNum);
        return {
            pageNum: pageNum,
            ...data,
        }
    },

    // 加载分页内容
    _loadSignList: async function (subjectUuid, pageNum) {
        // 初始化分页配置
        let pageConfig = this.statePageSign.pageConfigSignList;
        pageConfig.setNoMoreDataCallback();
        // 构建查询条件 condition
        console.info(`Begin to load signList which subjectUuid is ${subjectUuid}`);
        let condition = {
            subjectUuid: subjectUuid,
        }
        // let pagingParam = pageConfig.buildParam(pageNum, condition);
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = (await userForFormService.pageForSign(pagingParam)).data;
        } catch (e) {
            console.error(e);
        } finally { }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        return {
            ...pageInfo,
            dataList: dataList.map(v => {
                return {
                    ...v,
                    uploadImg: img_util.handleNormalPortraitPath(v.uploadImg)
                }
            }),
        }
        // dataList.map(v => ({
        //     id: v.id,

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