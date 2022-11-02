// 加载 工具类
const Paging = require('../../utils/paging_util');
const CONSTANT = require('../../common/constant');

const string_util = require('../../utils/string_util');
const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const fairForCandidateService = require('../../common/fairForCandidateService');
const fairForCompanyService = require('../../common/fairForCompanyService');
// 定义常量
const app = getApp();

const createPageMethods = () => ({
    // 分页状态
    state: {
        jobListPageConfig: new Paging.PageConfig(5),
        companyListPageConfig: new Paging.PageConfig(5),
        candidateListPageConfig: new Paging.PageConfig(5),
    },
    // 没有更多页面了
    _noMoreData() {
        this.setData({
            noMoreData: true,
        });
    },
    reloadCandidateContent: async function () {
        this.state.candidateListPageConfig.reset();
        this.setData({
            candidateList: [],
        })
        await this._loadCandidateListContent();
    },
    reloadCompanyAndJobContent: async function () {
        this.state.jobListPageConfig.reset();
        this.state.companyListPageConfig.reset();
        this.setData({
            jobList: [],
            companyList: [],
        })
        await this._loadJobListContent();
        await this._loadCompanyListContent();
    },
    loadSingleContent: async function () {
        let tabIndex = this.data.tabList.findIndex(v => v.typeId == this.data.currentId);
        console.log(tabIndex);
        switch (tabIndex) {
            case 0: await this._loadJobListContent(); break;
            case 1: await this._loadCompanyListContent(); break;
            case 2: await this._loadCandidateListContent(); break;
            default: break;
        }
    },
    initAllContent: async function () {
        await this.clearAllContent();
        await this.loadAllContent();
    },

    loadAllContent: async function () {
        await this._loadJobListContent();
        await this._loadCompanyListContent();
        await this._loadCandidateListContent();
    },
    // 清空内容
    clearAllContent: async function () {
        this.state.candidateListPageConfig.reset();
        this.state.jobListPageConfig.reset();
        this.state.companyListPageConfig.reset();
        this.setData({
            jobList: [],
            companyList: [],
            candidateList: [],
        })
    },
    // 招聘岗位数据
    _loadJobListContent: async function () {
        let pageConfig = this.state.jobListPageConfig;

        // 回调 没有数据的处理方式
        pageConfig.setNoMoreDataCallback(this._noMoreData);
        // 构建查询条件 condition
        let condition = {
            fairUuid: this.data.fairUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await fairForCompanyService.pageJobDTO(pagingParam);
            pageInfo = pageInfo.data;
            this.setData({
                // 求职者数量
                ['tabList[0].num']: pageInfo.total,
            })
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let currentList = this.data.jobList;
        console.debug(currentList)
        let newList = dataList.map(v => ({
            companyUuid: v.companyUuid,
            companyName: v.companyName,
            jobUuid: v.jobUuid,
            jobName: v.jobName,
            jobSalarMin: v.jobSalarMin,
            jobSalarMax: v.jobSalarMax,
            jobSalaryScope: new CONSTANT.Salary(v.jobSalaryMin, v.jobSalaryMax).value,
            recruiterOpenid: v.recruiterOpenid,
            recruiterName: v.recruiterName,
            recruiterGender: v.recruiterGender,
            recruiterPortraitPath: img_util.handlePersonnelPortraitPath(v.recruiterPortraitPath, v.recruiterGender),
            // createTime: v.createTime,
        }));
        // 拼接数据
        this.setData({
            jobList: currentList.concat(newList),
        });
    },
    // 报名公司数据
    _loadCompanyListContent: async function () {
        let pageConfig = this.state.companyListPageConfig;

        // 回调 没有数据的处理方式
        pageConfig.setNoMoreDataCallback(this._noMoreData);
        // 构建查询条件 condition
        let condition = {
            fairUuid: this.data.fairUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await fairForCompanyService.pageDTO(pagingParam);
            pageInfo = pageInfo.data;
            this.setData({
                // 招聘企业数量
                ['tabList[1].num']: pageInfo.total,
            })
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let currentList = this.data.companyList;
        console.debug(currentList)
        let newList = dataList.map(v => ({
            companyUuid: v.companyUuid,
            companyName: v.companyName,
            countJob: v.countJob,
            recruiterOpenid: v.recruiterOpenid,
            recruiterName: v.recruiterName,
            companyPortraitPath: img_util.handlePersonnelPortraitPath(v.companyPortraitPath),
            // createTime: v.createTime,
        }));
        // 拼接数据
        this.setData({
            companyList: currentList.concat(newList),
        });
    },
    // 求职者数据
    _loadCandidateListContent: async function () {
        let pageConfig = this.state.candidateListPageConfig;

        // 回调 没有数据的处理方式
        pageConfig.setNoMoreDataCallback(this._noMoreData);
        // 构建查询条件 condition
        let condition = {
            fairUuid: this.data.fairUuid,
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await fairForCandidateService.pageDTO(pagingParam);
            pageInfo = pageInfo.data;
            this.setData({
                // 求职者数量
                ['tabList[2].num']: pageInfo.total,
            })
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let currentList = this.data.candidateList;
        console.debug(currentList)
        let newList = dataList.map(v => ({
            candidateOpenid: v.candidateOpenid,
            candidateName: v.candidateName,
            candidatePortraitPath: img_util.handlePersonnelPortraitPath(v.candidatePortraitPath, v.gender),
            gender: v.gender,
            sex: CONSTANT.genderList[v.gender],
            workingAge: !v.workingAge ? "" : v.workingAge + "年工作经验",
            age: string_util.isEmpty(v.birthday) ? "" : date_util.getAgeByBirthday(v.birthday) + "岁",
            birthday: v.birthday,
            expectSalaryScope: new CONSTANT.Salary(v.expectSalaryMin, v.expectSalaryMax).value,
            // expectCategoryUuid: v.expectCategoryUuid,
            expectCategoryName: string_util.isEmpty(v.expectCategoryName) ? [] : string_util.splitByComma(v.expectCategoryName),
            // expectCommunityUuid: v.expectCommunityUuid,
            // expectCommunityName: v.expectCommunityName,
            // communityUuid: v.communityUuid,
            // createTime: v.createTime,
        }));
        // 拼接数据
        this.setData({
            candidateList: currentList.concat(newList),
        });
    }

});

module.exports = {
    createPageMethods: createPageMethods,
}