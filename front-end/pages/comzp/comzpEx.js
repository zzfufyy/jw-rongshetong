// 加载 工具类
const Paging = require('../../utils/paging_util');
const CONSTANT = require('../../common/constant');

const string_util = require('../../utils/string_util');
const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
const format_util = require('../../utils/format_util');

// 加载服务类
const userRecruiterService = require('../../common/userRecruiterService');
const userCandidateService = require('../../common/userCandidateService');
const recruitCompanyService = require('../../common/recruitCompanyService');


// 定义常量
const app = getApp();


const createPageMethods = () => ({
    // 分页状态
    state: {
        pageConfig: new Paging.PageConfig(10),
        location: CONSTANT.defaultLocation,
    },
    // 没有更多页面了
    _noMoreData() {
        this.setData({
            noMoreData: true,
        });
    },
    _loadLocation: async function (openid, userIndentity) {
        // debugger
        let location = {
            longitude: 0,
            latitude: 0,
        };
        let companyUuid = '';
        // 全局为 求职者
        if (userIndentity === "user") {
            try {
                let userCandidateData = await userCandidateService.loadEntityById(openid);
                userCandidateData = userCandidateData.data;
                location = {
                    longitude: userCandidateData.lon,
                    latitude: userCandidateData.lat,
                }
            } catch (e) {
                console.error(e)
            }
            // 全局为 招聘者
        } else if (userIndentity === "company") {
            try {

                let userRecruiterData = await userRecruiterService.loadEntityById(openid);
                let userRecruiterInfo = userRecruiterData.data;
                if (!string_util.isEmpty(userRecruiterInfo.companyUuid)) {
                    let companyData = await recruitCompanyService.loadEntityById(userRecruiterInfo.companyUuid);
                    companyData = companyData.data;
                    companyUuid = companyData.id;
                    location = {
                        longitude: companyData.lon,
                        latitude: companyData.lat,
                    }
                } else {
                    location = CONSTANT.defaultLocation;
                }
                this.setData({
                    location: location,
                })
            } catch (e) {
                console.error(e)
            }
            this.setData({
                companyUuid: companyUuid,
                location: location,
            })
        } else {
            location = CONSTANT.defaultLocation;
            this.setData({
                location: location,
            })
        }
        return location;
    },
    // 清空内容
    clearContent: async function () {
        this.state.pageConfig.reset();
        // 重置数据内容
        this.setData({
            candidateList: []
        })
    },

    // 加载内容
    loadContent: async function () {
        // 加载页面 排序参数
        let orderType = this.data.orderTypeList.find(v => { return v.checked }).type;


        // 加載openid
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        let userIndentity = await this.stateCompleter.userIdentityCompleter.promise;
        let communityUuid = await this.stateCompleter.communityUuidCompleter.promise;
        // 加載位置 location
        let location = await this._loadLocation(openid, userIndentity);

        // 回调 没有数据的处理方式
        this.state.pageConfig.setNoMoreDataCallback(this._noMoreData);

        // 初始化分页配置
        let pageConfig = this.state.pageConfig;
        let isChooseAllCommunity = this.data.scopeList[0].checked;
        // 构建查询条件 condition
        let condition = {
            orderType: orderType,
            communityUuid: isChooseAllCommunity ? '' : communityUuid,
            categoryName: this.data.searchText,
            jobSalaryMin: this.data.jobSalaryMin,
            jobSalaryMax: this.data.jobSalaryMax,
            ...location
        }
        let pagingParam = pageConfig.buildNextParam(condition);
        if (!pagingParam) {
            return;
        }
        // 获取分页内容
        let pageInfo;
        // 请求数据
        try {
            pageInfo = await userCandidateService.pagedByCondition(pagingParam)
            pageInfo = pageInfo.data;
            console.debug(pageInfo);
        } catch (e) {
            console.error(e);
        } finally {
        }
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.candidateList;
      
        let newList = dataList.map(r => ({
            candidateOpenid: r.candidateOpenid,
            realName: r.realName,
            jobName: string_util.isEmpty(r.categoryName) ? '暂无' : r.categoryName.replaceAll(',', '/'),
            genderText: CONSTANT.genderList[r.gender],
            ageText: string_util.isEmpty(r.birthday) ? '年龄未知' : date_util.getAgeByBirthday(r.birthday) + '岁' ,
            salaryText: new CONSTANT.Salary(r.expectSalaryMin, r.expectSalaryMax).value ,
            gender: r.gender,
            portraitPath: img_util.handlePersonnelPortraitPath(r.portraitPath, r.gender),
            communityName: string_util.isEmpty(r.communityName) ? '' : r.communityName,
            distance: format_util.formatDistance(r.distance),
            phone: r.phone,
            eduBackgroundName: string_util.isNotEmpty(r.eduBackgroundName) ? r.eduBackgroundName : '',
            eduSchoolName: string_util.isNotEmpty(r.eduSchoolName) ? r.eduSchoolName : '',
            eduMajor: string_util.isNotEmpty(r.eduMajor) ? r.eduMajor : '',
        }));
        console.log(newList)
        // 拼接数据
        this.setData({
            candidateList: current.concat(newList),
        });
    },

});

module.exports = {
    createPageMethods: createPageMethods,
}