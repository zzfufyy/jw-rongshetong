// pages/personjl/personjl.js
const {
    UserService
} = require('../../service/user_service');

const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const date_util = require('../../utils/date_util');
const img_util = require('../../utils/img_util');
const string_util = require('../../utils/string_util');
const $ = require('../../utils/request_util');
// 加载服务
const candidateForCategoryService = require('../../common/candidateForCategoryService');
const candidateForCommunityService = require('../../common/candidateForCommunityService');
const jobExperienceService = require('../../common/jobExperienceService');
const prompt_util = require('../../utils/prompt_util');
const uploadpath = require('../../utils/upload_util');
const app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        tximg: '',
        name: '',
        sex: '',
        id: "",
        // 年龄
        year: '',
        // 手机
        cellphone: '',
        // 工作年限  暂不做
        gznl: '',
        yxjobname: '',
        // 薪资范围
        ygz: '',
        sqname: [
            // { sqname: '东湖社区' },
            // { sqname: '定王台街道' },
            // { sqname: '湘湖街道' },
        ],
        gzjy: '填写工作经验，让HR快速看到你',
        qzjy: [{
            companyname: '长沙竟网科技有限公司',
            jobname: 'UI设计师',
            jobyear: '2022.03-至今',
            jobdsc: [{
                    jobjl: '1.根据设计要求完成建筑风格、外形等总体设计;'
                },
                {
                    jobjl: '2.提供各种建筑主体设计、户型设计、外墙设计、景 观设计等;'
                }
            ]
        }],
        boolean: false,
        jszj: '点击添加专业技能',
        selfpj: false,
        zwpj: '输入自我评价',
        sxjl: "请添加实习经历",
        zyjn: true,
        gerenzyjn: [{
            jobjl: 'java,.net,c++'
        }],
        eduExperiance: '填写毕业院校专业，工作匹配度更高',
        eduSchoolName: '',
        eduMajor: '',
        eduBackgroundName: '',
        scfile: '请上传简历附件',
        hidebox: true,
        file_Path: '',

        // 实习经历  list
				internshipList: [],
				
				jdthide:true,
				jdt:''
    },
    binderrorCandidatePortrait(e) {
        console.error(e);
        this.setData({
            tximg: Boolean(this.data.gender) ?
                CONSTANT.STATIC_IMG_URL.portrait_personal_female : CONSTANT.STATIC_IMG_URL.portrait_personal_male,
        })
    },
    //实习经历  添加
    gzjltj() {
        wx.navigateTo({
            url: '/pages/gzjl/gzjl',
        })
    },

    //点击上传简历
    scfile() {
        let that = this

        // onshow关闭
        app.globalData.isOnShow = false;
        wx.chooseMessageFile({
            count: 1,
            type: 'file',
            success: async res => {
                // tempFilePath可以作为img标签的src属性显示图片
                console.log(res)
                that.setData({
                    scfile: res.tempFiles[0].name,
                    filepath: res.tempFiles[0].path,
                    hidebox: false,
                    file_Path: res.tempFiles[0].path,
                })
                let id = this.data.id
                await $.upload({
                    url: '/user-candidate/uploadPortraitFile?id=' + id,
                    filePath: that.data.filepath,
                    formData: {},
                    name: 'file',
                    header: app.globalData.header,
                });
                await this._reloadResume();
            }
        })
    },
    async prewfile() {
        let that = this
        let file_Path = that.data.file_Path
       let pathflag=await uploadpath.isUploadPathLegals(file_Path);
       console.log(pathflag)
       if(pathflag==true){
          
       }else{
        file_Path=app.globalData.web_path+file_Path
       }
				that.setData({
					loadtit:'正在打开简历',
					jdthide:false
				})
        const downloadTask = wx.downloadFile({
            url: file_Path,
            success: function (res) {
                console.log(res)
                wx.openDocument({
                    filePath: res.tempFilePath,
                    success: function (res) {
                        console.log('打开文档成功')
                    }
                })
            },
            error: function (res) {
                console.log(res)
            }
				})
				downloadTask.onProgressUpdate((res) => {
					that.setData({
						jdt:res.progress
					})
					if(res.progress>='100'){
						that.setData({
							jdthide:true,
						})
					}
				})

    },
    //删除上传文件
    deletefile() {
        let that = this
        wx.showModal({
            title: '提示',
            content: '是否确认删除简历',
            success(res) {
                if (res.confirm) {
                    that.setData({
                        scfile: "请上传简历附件",
                        filepath: "",
                        hidebox: true
                    })
                    Loading.wrap(
                        () => UserService.saveRecruiteeInfo({
                            resume: "",
                        }),
                    )
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
        // that.setData({
        //     scfile: "请上传简历附件",
        //     filepath: "",
        //     hidebox: true
        // })
        // Loading.wrap(
        //     () => UserService.saveRecruiteeInfo({
        //         resume: "",
        //     }),
        // )
    },
    bindtapEditInternship(e) {
        wx.navigateTo({
            url: `/pages/gzjl/gzjl?jobExperienceUuid=${e.currentTarget.dataset.id}&jobType=${CONSTANT.JOB_TYPE.INTERNSHIP}`
        });
    },
    bindtapDeleteInternship: async function (e) {
        let res = await prompt_util.showModalPrompt('是否删除该实习经历');
        if (res.confirm) {
            await jobExperienceService.deleteById(e.currentTarget.dataset.id);
            // 删除数组元素
            let internshipList = this.data.internshipList;
            internshipList.splice(internshipList.findIndex(v => {
                return v.id = e.currentTarget.dataset.id
            }));
            this.setData({
                internshipList: internshipList
            })
            console.log(internshipList);
        } else {
            console.log("删除失败")
        }

    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let that = this;
        await app.getOpenidReady();
        // 加载全局 角色
        let role = await UserService.loadUserRole();
        if (role == CONSTANT.UserRole.Recruitee) {
            this.setData({
                isRecruitee: true,
            })
        } else if (role == CONSTANT.UserRole.Recruiter) {
            this.setData({
                isRecruitee: false,
            })
        }
        console.info(`Now Login User type: ${this.data.isRecruitee ? '求职者' : '招聘者'}`);
    },

    onShow: async function () {
        // onshow开关
        if (!app.globalData.isOnShow) {
            app.globalData.isOnShow = true;
            return;
        }
        await app.getAppInitReady();
        // 页面数据加载
        try {
            Loading.begin();
            await this._reloadData();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },

    onUnload: function (params) {
        console.debug('==================个人简历销毁==================');
    },

    _reloadResume: async function () {
        let that = this;
        let recruiteeInfo = await UserService.loadRcruiteeInfo();
        if (recruiteeInfo.resume == "" || recruiteeInfo.resume == null) {
            that.setData({
                id: recruiteeInfo.id,
                scfile: "请上传简历附件",
                filepath: recruiteeInfo.resume,
                hidebox: true,
                file_Path: "暂未上传简历",
            })
        } else {
            that.setData({
                id: recruiteeInfo.id,
                scfile: recruiteeInfo.realName + "的简历",
                filepath: recruiteeInfo.resume,
                hidebox: false,
                file_Path: recruiteeInfo.resume,
            })
        }
    },

    _reloadData: async function () {
        console.log('个人简历页面重载数据');
        var that = this;
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');
        // 加载求职者信息
        try {
            let recruiteeInfo = await UserService.loadRcruiteeInfo();
            if (recruiteeInfo.resume == "" || recruiteeInfo.resume == null) {
                that.setData({
                    id: recruiteeInfo.id,
                    scfile: "请上传简历附件",
                    filepath: recruiteeInfo.resume,
                    hidebox: true,
                    file_Path: "暂未上传简历",
                })
            } else {
                that.setData({
                    id: recruiteeInfo.id,
                    scfile: recruiteeInfo.realName + "的简历",
                    filepath: recruiteeInfo.resume,
                    hidebox: false,
                    file_Path: recruiteeInfo.resume,
                })
            }
            console.info(recruiteeInfo);
            this.setData({
                tximg: img_util.handlePersonnelPortraitPath(recruiteeInfo.portraitPath, recruiteeInfo.gender),
                gender: recruiteeInfo.gender,
                name: recruiteeInfo.realName,
                sex: CONSTANT.genderList[recruiteeInfo.gender],
                ygz: new CONSTANT.Salary(
                    recruiteeInfo.expectSalaryMin,
                    recruiteeInfo.expectSalaryMax,
                ).value,
                gznl: recruiteeInfo.ext1,
                zwpj: recruiteeInfo.introduction == '' ? '请添加个人简介' : recruiteeInfo.introduction,
                sxjl: (recruiteeInfo.internship == null || recruiteeInfo.internship == '') ? '请添加实习经历' : recruiteeInfo.internship,
                cellphone: recruiteeInfo.telephone,
                year: string_util.isNotEmpty(recruiteeInfo.birthday) ? date_util.getAgeByBirthday(recruiteeInfo.birthday) + '岁' : '',
                eduSchoolName: (string_util.isNotEmpty(recruiteeInfo.eduSchoolName) ? recruiteeInfo.eduSchoolName : ''),
                eduMajor: (string_util.isNotEmpty(recruiteeInfo.eduMajor) ? recruiteeInfo.eduMajor : ''),
                eduBackgroundName: (string_util.isNotEmpty(recruiteeInfo.eduBackgroundName) ? recruiteeInfo.eduBackgroundName : ''),
                eduBeginTime: (string_util.isNotEmpty(recruiteeInfo.eduBeginTime) ? date_util.dateToYYYYMMDD(recruiteeInfo.eduBeginTime) : ''),
                eduEndTime: (string_util.isNotEmpty(recruiteeInfo.eduEndTime) ? date_util.dateToYYYYMMDD(recruiteeInfo.eduEndTime) : ''),
            });

        } catch (e) {
            console.error(e);
        }
        // 加载 期望求职列表
        try {
            let expectCategoryListResult = await candidateForCategoryService.loadListByCandidateOpenid(openid);
            console.log(expectCategoryListResult);

            let yxjobnameList = [];
            expectCategoryListResult.data.forEach(r => {
                yxjobnameList.push({
                    categoryName: r.categoryName,
                });
            })
            this.setData({
                yxjobname: yxjobnameList,
            });
        } catch (e) {
            console.error(e);
        }
        // 加载 期望社区列表
        try {
            let expectCommunityListResult = await candidateForCommunityService.loadListByCandidateOpenid(openid);
            console.log(expectCommunityListResult);
            let sqnameList = [];
            expectCommunityListResult.data.forEach(r => {
                sqnameList.push({
                    sqname: r.communityName,
                });
            })
            this.setData({
                sqname: sqnameList,
            });
        } catch (e) {
            console.error(e);
        }
        // 加载  实习经历
        try {
            let jobExperienceData = (await jobExperienceService.listByCondition({
                candidateOpenid: openid,
                jobType: CONSTANT.JOB_TYPE.INTERNSHIP
            })).data;
            let internshipList = jobExperienceData.map(v => {
                return {
                    id: v.id,
                    candidateOpenid: v.candidateOpenid,
                    companyName: v.companyName,
                    jobName: v.jobName,
                    jobBeginTime: v.jobBeginTime,
                    jobEndTime: v.jobEndTime,
                    jobContent: v.jobContent,
                    jobType: v.jobType,
                }
            })
            this.setData({
                internshipList: internshipList,
            })
        } catch (e) {
            console.error(e)
        }

    },
})