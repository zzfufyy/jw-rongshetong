// pages/loojl/lookjl.js
const $ = require('../../utils/request_util');

const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');
const CONSTANT = require('../../common/constant');
const date_util = require('../../utils/date_util');
const img_util = require('../../utils/img_util');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const { UserService } = require('../../service/user_service');
const { CompanyService } = require('../../service/company_service');
const userRecruiterService = require('../../common/userRecruiterService');
const recruitJobService = require('../../common/recruitJobService');
const userCandidateService = require('../../common/userCandidateService');
const candidateForCommunityService = require('../../common/candidateForCommunityService');
const candidateForCategoryService = require('../../common/candidateForCategoryService');
const recruitRecordService = require('../../common/recruitRecordService');
const userStateService = require('../../common/userStateService');
const iUserRoleService = require('../../common/iUserRoleService');
const iAuthorizeService = require('../../common/iAuthorizeService');
const uploadpath = require('../../utils/upload_util');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        countView: 0,
        portraitPath: '',
        name: '',
        gender: '',
        age: '',
        gznl: '',
        expectJobName: '',
        salaryScope: '',
        expectCommunityName: ' ',
        introduction: '',
        ident: "",
        // portraitPath: '/img/tx.png',
        // name: '张三',
        // gender: '女',
        // age: '24',
        // gznl: '10分钟前活跃',
        // expectJobName: '保洁工作',
        // salaryScope: '3000-5000',
        // expectCommunityName: '天源社区 ',
        // introduction: 
        // '接受部长分配的服务工作，向客人提供优质服务。负责开餐前的准备工作。爱护餐厅设施设备，并对其实施保养、清洁。搞好营业前后的卫生工作，保持餐厅环境整洁，确保餐具，部件等清洁完好。',
        isinvite: false,
        compangjob: [
            {
                // job: '服务员/保洁', companyjuli: '1.2', companylocal: '长沙县泉塘街道新长海广场', sq: '天源社区', juli: '1.2',
                // tagxb: '女', tagnl: '29', taggz: '3000-5000', persontx: '/img/tx.png', personname: '张三 '
            },
        ],
        callname: "",
        candidateOpenid: "",
        cellphne: '',
        companyUuid: "",
        invitejob: [
            // {jobname:'店长',jobmoney:'6000-7000元',id:0},
            // {jobname:'店长',jobmoney:'6000-7000元',id:1},
            // {jobname:'店长',jobmoney:'6000-7000元',id:2},
            // {jobname:'店长',jobmoney:'6000-7000元',id:3},
            // {jobname:'店长',jobmoney:'6000-7000元',id:4},
        ],
        choosejob: true,
        categoryUuid: "",
        eduBackgroundName: '',
        eduSchoolName: '',
        eduMajor: '',
        file_Path:'',
				curriculumVitae:'',
				jdthide:true,
				jdt:''
    },
    //预览简历
    async prewfile(){
        let that = this
        let file_Path = that.data.file_Path
        let pathflag=await uploadpath.isUploadPathLegals(file_Path);
       console.log(pathflag)
       if(pathflag==true){
          
       }else{
        file_Path=app.globalData.web_path+file_Path
       }
				console.log(file_Path)
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
						error:function (res) {
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
    // 下载简历
    async uploadFile(){
				let that = this
				that.setData({
					loadtit:'正在下载简历',
					jdthide:false
        })
        let file_Path=that.data.file_Path;
        let pathflag=await uploadpath.isUploadPathLegals(file_Path);
        console.log(pathflag)
        if(pathflag==true){
           
        }else{
         file_Path=app.globalData.web_path+file_Path
        }
        const downloadTask = wx.downloadFile({
            url:file_Path, //仅为示例，并非真实的资源
            success (res) {
                console.log(res)
              // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
              if (res.statusCode === 200) {
                wx.openDocument({
                    filePath: res.tempFilePath,
                    showMenu: true,
                    success: function (res) {
                      console.log('打开文档成功')
                    }
                })
              }
            }
				});
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
    goToCompanyWaitPage: function () {
        this.setData({
            juesehide: false,
        })
        wx.navigateTo({
            url: '/pages/waiteyz/waiteyz',
        });
    },

    goToCompanyRegisterPage: function () {
        this.setData({
            juesehide: false,
        })
        wx.navigateTo({
            url: '/pages/qyzc/qyzc',
        });
    },
    async handleChooseRecruiter() {
        globalService.setGlobalUserIdentity(CONSTANT.UserRole.Recruiter);
        this.setData({
            ident: 'company',
        })

    },
    // 错误头像处理
    binderrorCandidatePortrait(e) {
        console.error(e);
        this.setData({
            portraitPath: Boolean(this.data.gender) ? CONSTANT.STATIC_IMG_URL.portrait_personal_female : CONSTANT.STATIC_IMG_URL.portrait_personal_male,
        })
    },
    // 打电话
    callnum: async function (e) {
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
        if (wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY) == 'user') {
            wx.showModal({
                title: '请切换至招聘者身份后操作',
            })
            return;
        }
        let that = this
        let phonenum = e.currentTarget.dataset.cellphne
        if (phonenum != "") {
            wx.makePhoneCall({
                phoneNumber: phonenum, //仅为示例，并非真实的电话号码
            })
        } else {
            wx.showToast({
                title: '此用户暂未填写电话',
                icon: 'none'
            })
        }

    },
    //邀请面试
    yqms: async function (e) {
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
        // 获取当前用户的角色
        if (wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY) == 'user') {
            wx.showModal({
                title: '请切换至招聘者身份后操作',
            })
            return;
        }
        let choosejob = !this.data.choosejob
        this.setData({
            choosejob: choosejob
        })

    },
    msxx: async function (e) {
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
        // 是否能继续视频面试（权限判断）
        let isContinue = await iAuthorizeService.isCanVideoInterview();
        if (!isContinue) {
            return;
        }
        let that = this;
        wx.showModal({
            title: '提示',
            content: '是否确认进行视频面试',
            success(res) {
                if (res.confirm) {
                    let openid = wx.getStorageSync('openid')
                    wx.setEnable1v1Chat({
                        enable: true,
                        success: (e) => {
                            wx.join1v1Chat({
                                caller: {
                                    nickname: that.data.callname,
                                    openid: openid,
                                },
                                listener: {
                                    nickname: that.data.name,
                                    openid: that.data.candidateOpenid,
                                },
                            });
                        },
                        fail: (e) => {
                            console.error(e)
                        }
                    })
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    },
    /**个人介绍**/
    setInputValue3: function (e) {
        console.log(e)
    },
    //点击选择职位
    jobnamebox(e) {
        console.log(e)
        let id = e.currentTarget.dataset.id.id
        this.setData({
            id: id
        })
    },
    //取消按钮
    qxbtn() {
        let choosejob = !this.data.choosejob
        this.setData({
            choosejob: choosejob
        })

    },
    //新增岗位
    addbtn() {
        wx.navigateTo({
            url: '/pages/fbjob/fbjob?tzym=jxgl',
        })
    },
    //发送面试邀请消息
    sendmianshixx: function (candidateOpenid, jobid, recruiterOpenid, companyUuid) {
        //发送简历消息
        wx.requestSubscribeMessage({
            //zOhHPg2OJCVaFetsGxWkqItK8OY-K6XnkgQKgViRa8A
            tmplIds: ['9uBKXPZrMPWqX-ny9BiW4z7FrL5-W8jpYzA7j3ChsM0'],
            success(res) {
                console.log(res)
                if (res['9uBKXPZrMPWqX-ny9BiW4z7FrL5-W8jpYzA7j3ChsM0'] == 'accept') {
                    console.log(111)
                    wx.request({
                        url: app.globalData.web_path + '/fczc/searchtoken',
                        header: app.globalData.header,
                        method: "GET",
                        data: {
                            grant_type: "client_credential",
                        },
                        success: function (data) {
                            console.log(data.data.sj.access_token)
                            wx.request({
                                url: app.globalData.web_path + '/fczc/sendmsyqinfos',
                                header: {
                                    Token: "",
                                    Cookie: "",
                                    "dataType": "json",
                                    // 'content-type' : 'application/x-www-form-urlencoded'
                                },
                                method: "get",
                                data: {
                                    "access_token": data.data.sj.access_token,
                                    "touser": candidateOpenid,
                                    "template_id": "9uBKXPZrMPWqX-ny9BiW4z7FrL5-W8jpYzA7j3ChsM0",
                                    "lang": "zh_CN",
                                    "zhiwei": jobid,
                                    "companyname": companyUuid,
                                    //   "qzz":that.data.sqr,
                                    "openid": recruiterOpenid,
                                    "data": {

                                    }
                                },
                                success: function (data) {
                                    console.log(data)
                                    wx.showToast({
                                        title: '邀请成功',
                                        icon: 'success',
                                        duration: 2000
                                    })
                                }
                            })
                        }
                    })
                }
            }
        })
    },
    //
    async tijsq1(e) {
        let that = this;

        console.log(e)
        wx.request({
            url: app.globalData.web_path + '/cand-job/searchmsyq',
            data: {
                candidateOpenid: that.data.candidateOpenid,
                jobUuid: that.data.id,
            },
            header: app.globalData.header,
            success: async function (res) {
                console.log(res)
                if (res.data.obj > 0) {
                    wx.showToast({
                        icon: 'none',
                        title: `您已发送过面试邀请`,
                        duration: 2000,
                    })
                    return;
                } else {

                    let insertData = {
                        flagWhoReceive: 0,
                        flowRecruit: 2, // 待查看
                        candidateOpenid: that.data.candidateOpenid,
                        recruiterOpenid: wx.getStorageSync('openid'),
                        companyUuid: that.data.companyUuid,
                        jobUuid: that.data.id,
                        categoryUuid: that.data.categoryUuid,
                    }
                    let insertPromise = recruitRecordService.insertByEntity(insertData);
                    await insertPromise
                        .then(r => console.log(r))
                        .catch(r => console.error(r));
                    // 生成投递记录
                    await recruitJobService.increaseCountApply(that.data.id);
                    that.sendmianshixx(that.data.candidateOpenid, that.data.id, wx.getStorageSync('openid'), that.data.companyUuid)
                }
            },
            fail: function (res) {
            }
        })



        let choosejob = !this.data.choosejob
        this.setData({
            choosejob: choosejob
        })

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let openid = wx.getStorageSync('openid');
        let userStateData = await userStateService.loadEntityById(openid);
        userStateData = userStateData.data;

        this.setData({
            inlth: this.data.invitejob.length
        })

        let candidateOpenid = options.candidateOpenid;
        await app.getOpenidReady();
        let recruiterOpenid = app.getOpenid();
        try {
            Loading.begin()
            // 加载数据
            let [
                userCandidateData,
                userRecruiterData,
                communityListData,
                categoryListData,
            ] = await Promise.allSettled([
                userCandidateService.loadEntityById(candidateOpenid),
                userCandidateService.loadEntityById(recruiterOpenid),
                candidateForCommunityService.loadListByCandidateOpenid(candidateOpenid),
                candidateForCategoryService.loadListByCandidateOpenid(candidateOpenid),
            ]);
            userCandidateData = userCandidateData.value.data;
            userRecruiterData = userRecruiterData.value.data;
            communityListData = communityListData.value.data;
            categoryListData = categoryListData.value.data;
            // 增加浏览量
            await userCandidateService.increaseCountView(candidateOpenid);

            let expectCommunityName = communityListData.map(v => {
                return v.communityName;
            }).join(",");

            let expectJobName = categoryListData.map(v => {
                return v.categoryName;
            }).join(",");
            this.setData({
                candidateOpenid: candidateOpenid,
                gender: userCandidateData.gender,
                countView: userCandidateData.countView,
                portraitPath: img_util.handlePersonnelPortraitPath(userCandidateData.portraitPath, userCandidateData.gender),
                name: userCandidateData.realName,
                callname: userRecruiterData.realName,
                gender: CONSTANT.genderList[userCandidateData.gender],
                age: string_util.isEmpty(userCandidateData.birthday) ? '' : date_util.getAgeByBirthday(userCandidateData.birthday) + '岁',
                gznl: '', // TODO 10分钟前活跃
                expectJobName: expectJobName,
                curriculumVitae:userCandidateData.resume,
                file_Path:userCandidateData.resume,
                salaryScope: new CONSTANT.Salary(userCandidateData.expectSalaryMin, userCandidateData.expectSalaryMax).value,
                expectCommunityName: expectCommunityName,
                introduction: userCandidateData.introduction,
                cellphne: userCandidateData.telephone,

                // 教育信息
                eduBackgroundName: string_util.isEmpty(userCandidateData.eduBackgroundName) ? "" : userCandidateData.eduBackgroundName,
                eduSchoolName: string_util.isEmpty(userCandidateData.eduSchoolName) ? "" : userCandidateData.eduSchoolName,
                eduMajor: string_util.isEmpty(userCandidateData.eduMajor) ? "" : userCandidateData.eduMajor,

            })
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: async function () {
        let that = this;
        let openid = wx.getStorageSync('openid');
        console.log(openid)
        let jobs = [];
        let userRecruiter = await userRecruiterService.loadEntityById(openid);
        console.log(userRecruiter.data.companyUuid)
        let companyJob = await recruitJobService.loadListByCompanyUuid(userRecruiter.data.companyUuid);
        console.log(companyJob.data)

        for (var i = 0; i < companyJob.data.length; i++) {
            let money = new CONSTANT.Salary(companyJob.data[i].jobSalaryMin, companyJob.data[i].jobSalaryMax)
            var job = { jobname: companyJob.data[i].jobName, jobmoney: money.value, id: companyJob.data[i].id }
            jobs.push(job)
        }
        console.log(jobs)
        that.setData({
            invitejob: jobs,
            companyUuid: userRecruiter.data.companyUuid
        })
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function (res) {
        if (res.from === 'button') {
            // 来自页面内转发按钮
            title = "这个是页面自定义的分享事件~";
            // imageUrl='***.png';
        }
        if (res.from === 'menu') {
            title = "这个是页面右上角的分享事件~";
            // imageUrl='***.png';
        }
    },
    onShareTimeline(res) {
        console.log(res)
    }
})