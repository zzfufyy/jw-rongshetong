/// 首页用于保存求职者用户信息的模块
const { UserService } = require('../../service/user_service');
const recruitJobService = require('../../common/recruitJobService');

const CONSTANT = require('../../common/constant');
const Loading = require('../../utils/loading_util');


const string_util = require('../../utils/string_util');
const url_util = require('../../utils/url_util');
const img_util = require('../../utils/img_util');

const userCandidateService = require('../../common/userCandidateService');
const candidateForCategoryService = require('../../common/candidateForCategoryService');
const userStateService = require('../../common/userStateService');


const app = getApp();

// 招聘人信息
const createRecruiteeData = () => ({
    // 注册信息
    realName: '',
    identityCard: '',
    gender: 0,
    // salaryList index
    salary: 0,
    expectCatagoryId: [],
    expectSalaeryMin: 0,
    expectSalaeryMax: 0,
    genderList: CONSTANT.genderList,
    salaryList: CONSTANT.salaryList,
    // 期望职业
    wantjob: [
        { job: '保洁', id: 1, }, { job: '服务员', id: 2, }, { job: '保安', id: 3 }, { job: '保姆', id: 4 },
    ],
    // 数据信息
    jobInfoList: [],
    newListss: [],
});




// TODO: 输入校验
const createRecruiteeMethods = () => ({

    // 实名认证 - 真实姓名
    handleRealName: function (e) {
        var value = e.detail.value;
        this.setData({
            realName: value,
        });
    },
    // 实名认证 -  身份证
    handleIdentityCard: function (e) {
        var value = e.detail.value;
        this.setData({
            identityCard: value,
        });
    },
    //发送简历消息
    sendjianlixx:function(recruiterOpenid,jobname,candidateOpenid){
         //发送简历消息
         wx.requestSubscribeMessage({
            //zOhHPg2OJCVaFetsGxWkqItK8OY-K6XnkgQKgViRa8A
            tmplIds: ['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU'],
            success (res) { 
              console.log(res)
              if(res['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU']=='accept'){
                  console.log(111)
                wx.request({
                    url: app.globalData.web_path +'/fczc/searchtoken',
                    header: app.globalData.header,
                    method: "GET",
                    data:{
                      grant_type:"client_credential",
                    },
                    success: function (data) {
                        console.log(data.data.sj.access_token)
                        wx.request({
                            url:  app.globalData.web_path +'/fczc/sendtdjlinfos',
                            header : {
                              Token : "",
                              Cookie : "",
                              "dataType": "json", 
                              // 'content-type' : 'application/x-www-form-urlencoded'
                          },
                            method: "get",
                            data:{
                              "access_token":data.data.sj.access_token,
                              "touser": recruiterOpenid,
                              "template_id":"zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU",
                              "lang":"zh_CN",
                              "zhiwei":jobname,
                            //   "qzz":that.data.sqr,
                              "openid":candidateOpenid,
                              "data":{
                                  
                              }
                            },
                            success: function (data) {
                              console.log(data)
                            }
                    })
                    }
                })
              }
            }
        })
    },
 


    //实名认证 - 提交
    async submitRealUser(e) {

        if (string_util.isEmpty(this.data.realName)) {
            wx.showModal({
                title: '提示',
                content: '输入姓名不能为空',
            });
            return;
        }
        if (string_util.isEmpty(this.data.identityCard)) {
            wx.showModal({
                title: '提示',
                content: '输入身份证号码不能为空',
            });
            return;
        }
        if (string_util.isEmpty(this.data.sqrphone)) {
            wx.showModal({
                title: '提示',
                content: '手机号码不能为空',
            });
            return;
        }
        let updateData = {
            id: wx.getStorageSync('openid'),
            realName: this.data.realName,
            identityCard: this.data.identityCard,
            telephone: this.data.sqrphone,
        }
        try {
            Loading.begin();
            await userCandidateService.updateByEntity(updateData);
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
        // 跳入完善信息
        this.setData({
            smhide: true,
            infows: false
        })
    },
    // 完善信息 - 选择 期望工作 仅能选3个
    handleSelectCategory(e) {
        let id = e.currentTarget.dataset.id;
        let wantjobList = this.data.wantjob;
        let currentObj = wantjobList.find(r => {
            return r.id == id;
        })
        // 如果大于3 就提示
        if (currentObj.checked == false) {
            let num = 0;
            wantjobList.forEach(v => {
                if (v.checked == true) {
                    num = num + 1;
                }
            })
            if (num == 3) {
                wx.showModal({
                    title: '提示',
                    content: '不能选择超过3个想从事的工作',
                })
                return;
            } else {
                wantjobList = wantjobList.map(v => {
                    if (v.id == e.currentTarget.dataset.id) {
                        v.checked = true;
                    }
                    return v;
                })
            }
        } else {
            wantjobList = wantjobList.map(v => {
                if (v.id == id) {
                    v.checked = false;
                }
                return v;
            })
        }
        this.setData({
            wantjob: wantjobList,
            msg: "id:" + id
        })
    },

    // 完善信息 - 选择性别
    handleSelectGender(e) {
        let that = this;
        let index = e.currentTarget.dataset.index1;

        that.setData({
            gender: index
        })
    },

    // 完善信息- 选择薪资
    handleSelectSalary(e) {
        let that = this;
        let index = e.currentTarget.dataset.index3;
        console.debug(`性别设置为 [${index}]: ${CONSTANT.salaryList[index].value}`);
        that.setData({
            salary: index
        })
    },
    // 完善信息 - 提交
    submitRecruiteeInfo: async function () {
        let recruiteeInfo = this._getRecruiteeInfo();
        let openid = wx.getStorageSync('openid');
        Loading.begin();

        try {
            let updateData = {
                id: openid,
                gender: this.data.gender,
                expectSalaryMin: CONSTANT.salaryList[this.data.salary].min,
                expectSalaryMax: CONSTANT.salaryList[this.data.salary].max,
            }
            await userCandidateService.updateByEntity(updateData);
            let wantjobList = [];
            this.data.wantjob.forEach(v => {
                if (v.checked == true) {
                    wantjobList.push({
                        candidateOpenid: openid,
                        categoryUuid: v.id,
                        categoryName: v.job
                    });
                }
            });

            await candidateForCategoryService.insertByEntityList(openid, wantjobList)

            UserService.saveUserRole(CONSTANT.UserRole.Recruitee);
            this.setData({
                ident: 'user'
            });
        } finally {
            Loading.end();
        }


        this.setData({
            infows: true
        });
    },
    // 从 data 中获取 提交给服务端的 信息
    _getRecruiteeInfo() {
        let data = this.data;
        let salary = CONSTANT.salaryList[data.salary];
        return {
            // id 即是 open id
            id: wx.getStorageSync('openid'),
            realName: data.realName,
            identityCard: data.identityCard,
            gender: data.gender,
            expectSalaryMin: salary.min,
            expectSalaryMax: salary.max,
        }
    },



    // 角色选择时，用户点击选择求职者
    _handleRecruiteeSelected: async function () {
        try {
            Loading.begin();
            // 获取bc_user_wx 表信息
            let openid = wx.getStorageSync('openid');
            let userCandidate = await userCandidateService.loadEntityById(openid);
            // 保存本次用户登录信息
            let updateUserState = {
                id: openid,
                userRole: 1,
            }
            await userStateService.updateByEntity(updateUserState);
            console.log(userCandidate)
            if (string_util.isEmpty(userCandidate.data.identityCard)) {
                // 还未实名认证   
                this.setData({
                    juesehide: true, // 角色选择隐藏
                    smhide: false,   // 进入实名认证
                    ident: 'user',
                    openid: app.getOpenid(),
                });
            } else {
                // 已经实名认证 
                this.setData({
                    juesehide: true,
                    smhide: true,
                    ident: 'user',
                    openid: app.getOpenid(),
                });
            }
            // 保存登录角色信息
            UserService.saveUserRole(CONSTANT.UserRole.Recruitee);
        }
        catch (e) {
            console.error(e);
        }
        finally {
            Loading.end();
        }
    },
    // 加载求职用户应该看到的工作信息
    _loadJobList: async function () {
        let that = this;
        console.info('加载 首页 工作列表');
        let pageConfig = this.state.pageConfig;

        console.log(pageConfig);

        let location = this.data.mapLocation == undefined ? this.data.location : this.data.mapLocation;
        let pagingParam = pageConfig.buildNextParam({
            longitude: location.longitude,
            latitude: location.latitude,
        });
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
            // pageInfo = await recruitJobService.pagedByDistance(pagingParam);
            console.log(wx.getStorageSync('openid'))
            let tempCandidateOpenid = wx.getStorageSync('openid');
            pageInfo = await recruitJobService.pagedByDistanceWithCandidateOpenid(tempCandidateOpenid, pagingParam);

        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }

        // pageConifg 保存当前的分页信息，并且取出 pageInfo 中的 list
        let dataList = pageConfig.handlePageInfo(pageInfo);
        console.log(dataList)
        // 当前的列表数据， 用于之后的列表拼接
        let current = this.data.jobInfoList;

        // 将服务端的数据映射成页面展示数据
        let newList = dataList.map(jobInfo => ({
            jobUuid: jobInfo.jobId,
            jobname: jobInfo.jobName,
            jobmoney: new CONSTANT.Salary(
                jobInfo.jobSalaryMin,
                jobInfo.jobSalaryMax,
            ).value,
            categoryUuid: jobInfo.categoryUuid,
            companyUuid: jobInfo.companyUuid,
            companyname: jobInfo.companyName,
            // companytx: jobInfo.portraitPath,
            companytx: img_util.handleCompanyPortraitPath(jobInfo.portraitPath),
            juridicalPerson: jobInfo.juridicalPerson,
            jl: jobInfo.distance == CONSTANT.UNKNOWN_DISTANCE ? '未知距离' : (jobInfo.distance / 1000).toFixed(1) + '公里',
            phonenum: jobInfo.telephone,
            flagApply: Boolean(jobInfo.flagApply),
            flagApplyText: Boolean(jobInfo.flagApply) ? '已投递' : '投递简历',
            flagCall: Boolean(jobInfo.flagCall),
            flagCallText: '电话沟通',
            recruiterOpenid: jobInfo.recruiterOpenid,
        }));
        console.log(newList);
        // 刷新页面
        this.setData({
            newListss: newList,
            jobInfoList: current.concat(
                newList,
            ),
        })
        console.log(this.data.jobInfoList)
    },

    async _resetJobInfoList() {
        this.setData({
            jobInfoList: [],
        });
    }
});

module.exports = {
    createRecruiteeMethods: createRecruiteeMethods,
    createRecruiteeInfo: createRecruiteeData,
};

