const { Salary } = require('../../common/constant');
const { UserService, user } = require('../../service/user_service');

const CONSTANT = require('../../common/constant');
const img_util = require('../../utils/img_util');

const loading = require('../../utils/loading_util');
const url_util = require('../../utils/url_util');
const string_util = require('../../utils/string_util');
const date_util = require('../../utils/date_util');

// 加载服务
const candidateForCategoryService = require('../../common/candidateForCategoryService');
const recruitRecordService = require('../../common/recruitRecordService');
const app = getApp();

const createRecruiteeMethods = () => ({
    loadRecruiteeInfo: async function () {
        let zw = "";
        loading.begin();
        let that = this
        try {
            let openid = wx.getStorageSync('openid')
            let recruiteeInfo = await UserService.loadRcruiteeInfo();
            // 期望职位列表
            let expectCatagoryListResult = await candidateForCategoryService.loadListByCandidateOpenid(openid);
            console.log(expectCatagoryListResult);
            let zwListSum = '';
            expectCatagoryListResult.data.forEach((v, i) => {
                if (i == 0) {
                    // TODO 问题float 超出隐藏了
                    zwListSum += (v.categoryName + '  ');// 可能显示不下  目前只写一个
                }
            })
            let loadRecordListPormise = recruitRecordService.listRecordPlusByCandidateOpenid(openid);
            let msyq = 0;
            let sumJobList = [];
            // {jobname:'清洁工',
            // jobmoney:'3000-3800',
            // companyname:'文和友餐饮有限公司',companytx:'/img/tx.png',jl:'1.5',phonenum:'13112345678'},
            await loadRecordListPormise.then(r => {
                console.log(r);
                r.data.forEach((v) => {
                    console.log(v);
                    let tempData = {
                        jobUuid: v.jobUuid,
                        jobname: v.jobName,
                        jobmoney: new Salary(v.jobSalaryMin, v.jobSalaryMax).value,
                        companyname: v.companyName,
                        companytx: app.globalData.web_path + v.portraitPath,
                        jl: (v.distance / 1000).toFixed(1),
                        phonenum: v.telephone,
                        flowRecruit: v.flowRecruit,
                    }
                    sumJobList.push(tempData);
                })
            }).catch(r => {
                console.error(r);
            });
            let joblist = [];
            let jobgtlist = [];
            let jobbhslist = [];
            // 面试结果计数
            let interviewResultCount = 0;
            sumJobList.forEach(v => {
                switch (v.flowRecruit) {
                    case CONSTANT.FLOW_RECRUIT.NORMAL:
                        joblist.push(v); break;
                    case CONSTANT.FLOW_RECRUIT.READ: // 沟通
                        jobgtlist.push(v); break;
                    case 2:
                        jobgtlist.push(v); msyq += 1; break;

                    case 3:
                        interviewResultCount ++;
                        break;
                    case CONSTANT.FLOW_RECRUIT.UNSUITABLE: // 不合适
                        interviewResultCount ++;
                        jobbhslist.push(v); break;
                }
            })
            // 已投递岗位数量
            let countData = await recruitRecordService.countByCandidateOpenid(openid);
            let countDeliveredJob = countData.data;
            // 设置数据
            that.setData({
                interviewResultCount: interviewResultCount,
                zw: zwListSum,
                ytdgw: countDeliveredJob,
                msyq: msyq,
                byll: recruiteeInfo.countView,
            });

            console.log(recruiteeInfo);

            this.setData({
                // 暂时只有一个数据
                personinfo: [{
                    tximg: img_util.handlePersonnelPortraitPath(recruiteeInfo.portraitPath, recruiteeInfo.gender),
                    name: recruiteeInfo.realName,
                    gender: recruiteeInfo.gender,
                    sex: CONSTANT.genderList[recruiteeInfo.gender],
                    year: string_util.isEmpty(recruiteeInfo.birthday) ? '' : date_util.getAgeByBirthday(recruiteeInfo.birthday) + '岁',
                    cellphone: recruiteeInfo.telephone,
                    jobname: that.data.zw,
                    money: new Salary(
                        recruiteeInfo.expectSalaryMin,
                        recruiteeInfo.expectSalaryMax,
                    ).value,
                    msgw: '0',
                    ytdgw: '127',
                    byll: recruiteeInfo.countView,
                }],
            });
            UserService.saveUserRole(CONSTANT.UserRole.Recruitee, true);

        } catch (e) {
            console.error(e);
        } finally {
            loading.end();
        }
    }
});


module.exports = {
    createRecruiteeMethods: createRecruiteeMethods,
}