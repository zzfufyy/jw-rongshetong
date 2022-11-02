// pages/zpzhuanc/zpzhuanc.js
// 加载 常量
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
// 加载 工具
const string_util = require('../../utils/string_util');
const img_util = require('../../utils/img_util');
const date_util = require('../../utils/date_util');
const Loading = require('../../utils/loading_util');
const { Completer } = require('../../utils/function_util')
// 加载 服务
const jobFairService = require('../../common/jobFairService');
const iUserRoleService = require('../../common/iUserRoleService');
const userCandidateService = require('../../common/userCandidateService');
const fairForCandidateService = require('../../common/fairForCandidateService');
const fairForCompanyService = require('../../common/fairForCompanyService');
const userRecruiterService = require('../../common/userRecruiterService');
// 附加模块
const zpzhuancEx = require('../../pages/zpzhuanc/zpzhuancEx');
const prompt_util = require('../../utils/prompt_util');
// 获取应用实例
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 全局数据
        openid: '',

        // 招聘会 id (传入)
        fairUuid: '',
        fairPortraitPath: '/img/gxbyzph.png',
        fairTitle: '',
        fairContent: '',
        fairHost: '',
        fairAddress: '',
        fairBeginTime: '',
        fairEndTime: '',
        // 小图片链接
        linkPath: '',
        linkPathParam: '',
        linkPhotoPath: '',


        // 内容是否显示全部
        isContentFold: false,

        isCompanySign: false,
        isCandidateSign: false,
        companySignText: '企业报名',
        candidateSignText: '求职者报名',


        currentId: '0',
        currentTab: '0',
        tabList: [
            { name: '招聘岗位', typeId: '0', num: 0, checked: true },
            { name: '招聘企业', typeId: '1', num: 0, checked: false },
            { name: '求职者', typeId: '2', num: 0, checked: false },
        ],
        jobList: [
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companylxr: '张经理', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        ],
        companyList: [
            // { comname: '长沙竟网信息科技有限公司', companytx: '/img/tx.png', companylxr: '张经理', zzjob: '5' },
            // { comname: '长沙竟网信息科技有限公司', companytx: '/img/tx.png', companylxr: '张经理', zzjob: '1', },
        ],
        candidateList: [
            // {
            //     pertx: '/img/tx.png', pername: '张三', age: '30', sex: '男', workyear: '3', wantmoy: '5000-8000',
            //     wantjob: [{ jobname: '产品经理' }, { jobname: '仓储管理员' }]
            // },
            // {
            //     pertx: '/img/tx.png', pername: '李四', age: '30', sex: '女', workyear: '3', wantmoy: '5000-8000',
            //     wantjob: [{ jobname: '产品经理' }, { jobname: '仓储管理员' }]
            // },
        ],
        pagess: 1,
    },
    lock: new Loading.Lock(),
    fairUuidCompleter: new Completer,
    //查看全部
    /** 页面事件 begin */
    bindtapFoldFairContent() {
        let isContentFold = !this.data.isContentFold
        this.setData({
            isContentFold: isContentFold
        })
    },
    //点击头部导航的点击事件
    catchtapSwitchTab: function (e) {
        let id = e.currentTarget.id;
        if (id) {
            this.setData({
                currentId: id,
                currentTab: id,
            })
        }
    },
    // 滚动切换标签样式 
    switchTab: function (e) {
        // console.log(e) 
        var that = this;
        that.setData({
            currentTab: e.detail.current,
            currentId: e.detail.current
        });
    },
    // 点击跳转到对应岗位
    async bindtapChooseJob(e) {
        let jobData = this.data.jobList[e.currentTarget.dataset.index]
        wx.navigateTo({
            url: "/pages/zwxq/zwxq?recruitJobUuid=" + jobData.jobUuid,
        })

    },
    //	点击跳转到对应公司
    bindtapChooseCompany(e) {
        let companyData = this.data.companyList[e.currentTarget.dataset.index]
        console.log(companyData);
        wx.navigateTo({
            url: '/pages/commessxq/commessxq?companyUuid=' + companyData.companyUuid,
        })
    },
    bindtapChooseCandidate(e) {
        let candidateData = this.data.candidateList[e.currentTarget.dataset.index]
        wx.navigateTo({
            url: '/pages/loojl/lookjl?candidateOpenid=' + candidateData.candidateOpenid,
        })
    },
    //企业报名
    async bindtapCompanySign() {
        // 已报名
        if (this.data.isCompanySign) {
            return;
        }
        // 判断 是否已经切换身份
        let switchRs = await iUserRoleService.trySwitchToRecruiter();
        if (!switchRs) {
            return;
        }
        let isSign = await this._loadIsSign(CONSTANT.UserRole.Recruiter);
        if (isSign) {
            this.setData({
                isCompanySign: true,
            })
        } else {
            console.log("未报名 报名去")
            // 未报名 - 走报名流程
            // 判断是否有公司  没公司跳转至注册公司
            let recruiterData = await userRecruiterService.loadEntityById(this.data.openid)
            recruiterData = recruiterData.data;
            if (string_util.isEmpty(recruiterData.companyUuid)) {
                // 跳转注册企业流程
                wx.showModal({
                    title: "请先注册企业",
                    success: res => {
                        if (res.confirm) {
                            // 跳转 企业注册
                            wx.navigateTo({
                                url: '/pages/qyzc/qyzc',
                            });
                        } else {
                            return;
                        }
                    }
                })

            } else {
                let insertData = {
                    fairUuid: this.data.fairUuid,
                    recruiterOpenid: this.data.openid,
                    companyUuid: recruiterData.companyUuid,
                }
                try {
                    Loading.begin();
                    await fairForCompanyService.insertByEntity(insertData)
                    await this.reloadCompanyAndJobContent();
                } catch (e) {
                    console.error(e)
                } finally {
                    Loading.end();
                }

                wx.showToast({
                    icon: 'success',
                    title: '报名成功',
                    duration: 1500,
                })
                this.setData({
                    isCompanySign: true,
                })
            }
        }

    },
    // 求职者报名
    async bindtapCandidateSign() {
        if (this.data.isCandidateSign) {
            return
        }
        // 判断 是否已经切换身份
        let switchRs = await iUserRoleService.trySwitchToCandidate();
        // 切换失败直接返回
        if (!switchRs) {
            return;
        }
        let isSign = await this._loadIsSign(CONSTANT.UserRole.Recruitee);
        if (isSign) {
            // 已报名
            this.setData({
                isCandidateSign: true,
            })
        } else {
            console.log("未报名 报名去")
            // 未报名 - 走报名流程
            // 判断是否有电话  没电话跳转至注册
            let candidateData = await userCandidateService.loadEntityById(this.data.openid)
            candidateData = candidateData.data;
            if (string_util.isEmpty(candidateData.telephone)) {
                wx.showModal({
                    title: "请完善个人信息，填写您的联系方式",
                    success: res => {
                        if (res.confirm) {
                            // 跳转 个人简历修改填写电话
                            wx.navigateTo({
                                url: '/pages/jlxg/jlxg',
                            })
                        } else {
                            return;
                        }
                    }
                })
            } else {
                let insertData = {
                    fairUuid: this.data.fairUuid,
                    candidateOpenid: this.data.openid,
                }
                try {
                    Loading.begin();
                    await fairForCandidateService.insertByEntity(insertData)
                    await this.reloadCandidateContent();
                } catch (e) {
                    console.error(e)
                } finally {
                    Loading.end();
                }
                wx.showToast({
                    icon: 'success',
                    title: '报名成功',
                    duration: 1500,
                })

                this.setData({
                    isCandidateSign: true,
                })
            }
        }

    },
    async reachBottom() {
        try {
            Loading.begin();
            await this.loadSingleContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }

    },
    bindtapGoFairHomePage: function(){
        wx.redirectTo({
          url: `${this.data.linkPath}?${this.data.linkPathParam}`,
        })
    },
    /** 页面事件 end */

    /** 内部方法 - begin */
    // 加载 - 招聘会信息
    _loadJobFairContent: async function () {
        let fairUuid = await this.fairUuidCompleter.promise;
        let jobFairData = await jobFairService.loadEntityById(fairUuid);
        jobFairData = jobFairData.data;
        let address1 = string_util.isEmpty(jobFairData.fairAddress) ? "暂未确定活动地址,等待后续更新" : jobFairData.fairAddress;

        this.setData({
            fairPortraitPath: img_util.handleFairPortraitPath(jobFairData.fairPortraitPath),
            fairTitle: jobFairData.fairTitle,
            fairContent: jobFairData.fairContent,
            fairHost: jobFairData.fairHost,
            fairAddress: address1,
            fairBeginTime: date_util.dateToYYYYMMDD(jobFairData.fairBeginTime),
            fairEndTime: date_util.dateToYYYYMMDD(jobFairData.fairEndTime),

            // 小图片链接
            linkPath: jobFairData.linkPath, 
            linkPathParam: jobFairData.linkPathParam, 
            linkPhotoPath: jobFairData.linkPhotoPath, 

        })
    },

    // 是否已经报名
    async _loadIsSign(userRole) {
        let isSignCompleter = new Completer();
        switch (userRole) {
            case CONSTANT.UserRole.Vistor:
                isSignCompleter.resolve(false);
                break;
            case CONSTANT.UserRole.Recruitee:
                let isCandidateSign = await fairForCandidateService.isSign(this.data.fairUuid, this.data.openid);
                isCandidateSign = isCandidateSign.data;
                this.setData({
                    isCompanySign: false,
                    isCandidateSign: isCandidateSign,
                })
                isSignCompleter.resolve(isCandidateSign);
                break;
            case CONSTANT.UserRole.Recruiter:
                let isCompanySign = await fairForCompanyService.isSign(this.data.fairUuid, this.data.openid);
                isCompanySign = isCompanySign.data;
                this.setData({
                    isCompanySign: isCompanySign,
                    isCandidateSign: false,
                })
                isSignCompleter.resolve(isCompanySign);
                break;
            default:
                isSignCompleter.resolve(false);
                break;
        }
        return isSignCompleter.promise;
    },
    /** 内部方法 - end */
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        // 页面样式调整
        let that = this
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight,
                });
            }
        })
        // 加载 fairUuid
        this.fairUuidCompleter = new Completer();
        let fairUuid = options.fairUuid;
        this.fairUuidCompleter.resolve(fairUuid);
        this.setData({
            fairUuid: fairUuid,
        })
        // 加载openid
        await app.getOpenidReady();
        let openid = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_OPENID);
        this.setData({
            openid: openid,
        });
        this.authorize = this.selectComponent("#authorize");
        // 等待授权完成
        let authorizeRs = await this.authorize.doAuthorize();
        if (!authorizeRs) {
            prompt_util.authorizeNamePortraitFailPrompt();
        }

        try {
            Loading.begin();
            // 加载 招聘会信息
            await this._loadJobFairContent();
            let userRole = await iUserRoleService.getUserRole();
            // 获取当前用户身份 展示是否已经报名
            await this._loadIsSign(userRole);
            // 加载分页信息
            await this.initAllContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end()
        }
    },


    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: async function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

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
    onShareAppMessage: function () {

    },
    // 页面分页内容相关
    ...zpzhuancEx.createPageMethods()
})