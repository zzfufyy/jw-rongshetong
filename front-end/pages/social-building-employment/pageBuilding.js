// 加载 工具类
const Paging = require('../../utils/paging_util');
const CONSTANT = require('../../common/constant')
const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const communityBuildingService = require('../../common/communityBuildingService');
// 定义常量
const app = getApp();

const createPageMethods = () => ({
    // 分页状态
    state: {
        pageConfig: new Paging.PageConfig(8),
    },
    // 清空内容
    clearContentBuildingList: async function () {
        this.state.pageConfig.reset();
        // 重置数据内容
        this.setData({
            buildingList: []
        })
    },
    // 加载内容
    loadContentBuildingList: async function () {
        this.state.pageConfig.setNoMoreDataCallback();

        let districtName = this.data.districtName;
        let streetName = this.data.streetName
        // 初始化分页配置
        let pageConfig = this.state.pageConfig;

        // 构建查询条件 condition
        let condition = {
            districtName: districtName,
            streetName: streetName,
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
            pageInfo = await communityBuildingService.pageStreet(pagingParam);
            pageInfo = pageInfo.data;
        } catch (e) {
            console.error(e);
        } finally { }
        console.log(pageInfo);
        let dataList = pageConfig.handlePageInfo(pageInfo);
        console.log(dataList);

        let current = this.data.buildingList;
        let buildingList = dataList.map(v => ({
            buildingUuid: v.id,
            buildingName: v.buildingName,
            districtName: v.districtName,
            streetName: v.streetName,
            communityName: v.communityName,
            address: v.address,
            portraitPath: img_util.handleDefaultPortraitPath(v.portraitPath, CONSTANT.STATIC_IMG_URL.portrait_community_building),
        }));

        // 拼接数据
        this.setData({
            buildingList: current.concat(buildingList),
        });
    },

});

module.exports = {
    createPageMethods: createPageMethods,
}