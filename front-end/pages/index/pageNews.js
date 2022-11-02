// 加载 工具类
const Paging = require('../../utils/paging_util');
const CONSTANT = require('../../common/constant')
const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const informationNewsService = require('../../common/informationNewsService');
// 定义常量
const app = getApp();

const createPageMethods = () => ({
    // 分页状态
    state: {
        pageConfig: new Paging.PageConfig(5),
    },
    // 清空内容
    clearContentNewsList: async function () {
        this.state.pageConfig.reset();
        // 重置数据内容
        this.setData({
            newsList: []
        })
    },
    // 加载内容
    loadContentNewsList: async function () {
        this.state.pageConfig.setNoMoreDataCallback();

        let articleType = this.data.newsListArticleType;
        // 初始化分页配置
        let pageConfig = this.state.pageConfig;

        // 构建查询条件 condition
        let condition = {
            articleType: articleType,
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
            if(articleType == 21){
                pageInfo = await informationNewsService.pageAllSocial(pagingParam);
            }else{
                pageInfo = await informationNewsService.pageByArticleType(pagingParam);
            }
            
            pageInfo = pageInfo.data;
        } catch (e) {
            console.error(e);
        } finally {}
        console.log(pageInfo);
        let dataList = pageConfig.handlePageInfo(pageInfo);
        console.log(dataList);
        // 拼接数据示例
        // joblist: [
        // 	{ jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        let current = this.data.newsList;
        let newList = dataList.map(v => ({
            newsUuid: v.id,
            articleTitle: v.articleTitle,
            articleIntroduction: v.articleIntroduction,
            articleAuthor: v.articleAuthor,
            articleReleaseTime: date_util.dateToCN(v.articleReleaseTime),
            yearMonth: date_util.getYearMonth(v.articleReleaseTime),
            day: date_util.getDay(v.articleReleaseTime),
            articlePortraitPath: img_util.handleArticlePortraitPath(v.articlePortraitPath),
        }));

        // 拼接数据
        this.setData({
            newsList: current.concat(newList),
        });
    },

});

module.exports = {
    createPageMethods: createPageMethods,
}