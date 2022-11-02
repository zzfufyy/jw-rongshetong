/// 首页用于加载招聘者用户信息的模块const { Api } = require('../../common/api');

const { Api } = require('../../common/api');
const { UserService } = require('../../service/user_service');
const { CompanyService } = require('../../service/company_service');
// const { Age} = require('../../common/constant');
const date_util = require('../../utils/date_util');
const string_util = require('../../utils/string_util');
const url_util = require('../../utils/url_util');
const img_util = require('../../utils/img_util');
const format_util = require('../../utils/format_util');

const Loading = require('../../utils/loading_util');
const $ = require('../../utils/request_util');
const CONSTANT = require('../../common/constant');

const userCandidateService = require('../../common/userCandidateService');
const userStateService = require('../../common/userStateService');
const app = getApp();

const createRecruiterMethods = () => ({

    goToCompanyWaitPage: function () {
        this.setData({
            juesehide: true,
        })
        // wx.navigateTo({
        //     url: '/pages/waiteyz/waiteyz',
        // });
    },

    goToCompanyRegisterPage: function () {
        this.setData({
            juesehide: false,
        })
        wx.navigateTo({
            url: '/pages/qyzc/qyzc',
        });
    },

    _handleRecruiterSelected: async function () {
        try {
            Loading.begin();
            let openid = wx.getStorageSync('openid');
            let userInfo = await UserService.loadRecruiterInfo();
        
            console.log(userInfo)
            // 保存本次用户登录信息
            let updateUserState = {
                id: openid,
                userRole: 2,
            }
            await userStateService.updateByEntity(updateUserState);
            // 服务端有此人员数据
            if (userInfo != null) {
                console.info(`服务端有此招聘人员信息：${app.getOpenid()}`);

                let companyUuid = userInfo.companyUuid;
                this.setData({ companyUuid: companyUuid });
                // 该人员创建了公司
                if (companyUuid) {
                    let companyInfo = await CompanyService.loadRecruiterCompanyInfo(companyUuid);

                    if (!companyInfo) {
                        console.error(`未知的公司信息[${companyUuid}]`);
                        this.goToCompanyRegisterPage();
                    }

                    // 认证完成
                    if (companyInfo.flagIdentification) {
                        console.info(`公司[${companyInfo.id}]认证完成`);
                        UserService.saveUserRole(CONSTANT.UserRole.Recruiter);

                        this.setData({
                            juesehide: true,
                            ident: 'company',
                            companyUuid: companyInfo.id,
                        });
                    }
                    // 还在认证
                    else {
                        console.info(`公司[${companyInfo.id}]认证中`);
                       
                        this.goToCompanyWaitPage();
                        
                        
                    }
                }

                // 该人员还没有创建公司，跳转公司注册
                else {
                    console.info('此招聘人还没有公司');
                    this.goToCompanyRegisterPage();
                }
            }
            // 服务端没有此人数据
            else {
                console.info(`服务端没有此招聘人员信息：${app.getOpenid()}`);

                await this._commitRecruiterInfo();
                this.goToCompanyRegisterPage();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },

    _commitRecruiterInfo: async function () {
        Loading.begin();
        try {
            await $.request({
                url: Api.userRecruiterAdd,
                data: {
                    id: app.getOpenid(),
                },
                method: $.RequestMethod.POST,
                header: $.jsonHeader,
            });

            // Constant.saveUserRole(Constant.UserRole.Recruitee);
        } finally {
            Loading.end();
        }


        this.setData({
            infows: true
        })
    },

    // 加载求职用户应该看到的工作信息

    _loadCandidateList: async function () {

        console.info('Load index candidateList...');
        let pageConfig = this.state.pageConfig;
        let location = this.data.mapLocation == undefined ? this.data.location : this.data.mapLocation;

        let condition = {
            longitude: location.longitude,
            latitude: location.latitude,
        }

        let pagingParam = pageConfig.buildNextParam(condition);

        // 重置  maplocation
        this.setData({
            mapLocation: undefined,
        })
        if (!pagingParam) {
            return;
        }

        let pageInfo;
        try {
            Loading.begin();
            // 请求数据
            pageInfo = await userCandidateService.pagedByDistacne(pagingParam);
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }

        // pageConifg 保存当前的分页信息，并且取出 pageInfo 中的 list
        console.debug(pageInfo);
        let dataList = pageConfig.handlePageInfo(pageInfo);
        let current = this.data.candidateList;
        console.debug(dataList);
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
        this.setData({
            candidateList: current.concat(
                newList,
            ),
        });
    },


    async _resetCandidateList() {
        this.setData({
            candidateList: [],
        });
    }
});




module.exports = {
    createRecruiterMethods: createRecruiterMethods,
};

