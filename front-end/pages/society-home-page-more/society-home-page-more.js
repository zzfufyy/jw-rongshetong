const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');

const date_util = require('../../utils/date_util');
const img_util = require('../../utils/img_util');
const prompt_util = require('../../utils/prompt_util');
const string_util = require('../../utils/string_util');
const Loading = require('../../utils/loading_util');
const {
    Completer
} = require('../../utils/function_util');


const userRecruiterService = require('../../common/userRecruiterService');
const recruitCompanyService = require('../../common/recruitCompanyService');
const iUserRoleService = require('../../common/iUserRoleService');
const globalService = require('../../common/globalService');
const {
    appendHeadSlash
} = require('../../utils/url_util');


const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        openid: '',
        hiddenBindCompanyInfo: true,
        hiddenChooseIdentity: true,
        flagIdentification: false,


        pageTabList: [{
            imgSrc: "../../img/society-bureau-home-page/tab_company_employment.svg",
            tagName: "企业招聘",
            tagId: "tagRecruitCompany",
            pagePathLink: "/pages/comzp/comzp"
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_job_empolyment.svg",
            tagName: "求职就业",
            tagId: "tagRecruitCandidate",
            pagePathLink: "/pages/qzjy/qzjy"
        },
        // {
        //     imgSrc: "../../img/society-bureau-home-page/tab_business_incubation.svg",
        //     tagName: "创业孵化",
        //     tagId: "tagBusinessIncubation",
        //     pagePathLink: "/pages/news-article-type/news-article-type",
        // },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_relevant_policy.svg",
            tagName: "相关政策",
            tagId: "tagRelevantPolicy",
            pagePathLink: "/pages/social-relevant-policy/social-relevant-policy",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_training_service.svg",
            tagName: "培训服务",
            tagId: "tagTrainingService",
            pagePathLink: "/pages/social-training-service/social-training-service",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_social_service.svg",
            tagName: "社保服务",
            tagId: "tagSecurityService",
            pagePathLink: "/pages/social-security-service/social-security-service",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_cloud_live.svg",
            tagName: "云直播",
            tagId: "tagCloudLive",
            pagePathLink: "",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_cloud_recruitment.svg",
            tagName: "云招聘",
            tagId: "tagCloudRecruitment",
            pagePathLink: "/pages/social-cloud-recruitment/social-cloud-recruitment",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_personnel_exam.svg",
            tagName: "人事考试",
            tagId: "tagPersonnelExam",
            pagePathLink: "/pages/news-article-type/news-article-type",
        },
        // {
        //     imgSrc: "../../img/society-bureau-home-page/tab_job_fair.svg",
        //     tagName: "招聘会",
        //     tagId: "tagJobFair",
        //     pagePathLink: "/pages/zphlist_copy/zphlist_copy",
        // },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_employment_help.svg",
            tagName: "就业帮扶",
            tagId: "tagEmploymentHelp",
            pagePathLink: "/pages/xzsq/xzsq?reserveDistrictNameList=麻阳县",
        },
        {
            imgSrc: "../../img/society-bureau-home-page/tab_labor_supervision.svg",
            tagName: "劳动监察",
            tagId: "tagLaborSupervision",
            pagePathLink: "",
        }

        ],
    },


    bindtapGoLink: async function (e) {
        let id = e.currentTarget.dataset.id;
        let linkUrl = this.data.pageTabList.find(v => {
            return id == v.tagId;
        }).pagePathLink;
        console.info(`Choose the functionTag: ${id}`);
        switch (id) {
            // 企业招聘  求职就业
            case 'tagRecruitCompany':
                // 如果企业未授权通过则
                // 尝试转换身份
                let switchRoleToRecruiterRs = await iUserRoleService.trySwitchToRecruiterPure();
                if (!switchRoleToRecruiterRs) {
                    return;
                }
                let userRecruiterInfo = (await userRecruiterService.loadEntityById(this.data.openid)).data;
                let companyUuid = userRecruiterInfo.companyUuid;
                if (string_util.isEmpty(companyUuid)) {
                    prompt_util.showModalPrompt('请您先点击个人中心注册公司');
                    return;
                }
                let recruitCompanyInfo = (await recruitCompanyService.loadEntityById(companyUuid)).data;
                switch (recruitCompanyInfo.flagIdentification) {
                    case CONSTANT.COMPANY_IDENTIFICATION.NORMAL:
                        prompt_util.showModalPrompt('您的公司信息正在审核中，请耐心等待');
                        return;
                        break;
                    case CONSTANT.COMPANY_IDENTIFICATION.FAIL:
                        prompt_util.showModalPrompt('您的公司信息未通过审核，请联系管理员处理');
                        return;
                        break;
                    case CONSTANT.COMPANY_IDENTIFICATION.SUCCESS:
                        break;
                    default:
                        break;
                }
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagRecruitCandidate':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagBusinessIncubation':
                linkUrl += ('?articleType=' + CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialIncubationNews.id)
                break;
            case 'tagPersonnelExam':
                linkUrl += `?articleType=${CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialPersonnelExamNews.id}&navigationBarTitle=人事考试`;
                break;
            case 'tagRelevantPolicy':
                linkUrl += ('?articleType=' + CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialPolicyNews.id);
                break;
            case 'tagTrainingService':
                break;
            case 'tagSecurityService':
                break;
            // 云直播 处理
            case 'tagCloudLive':
                console.info("begin open cloud live account");
                wx.getChannelsLiveInfo({
                    finderUserName: CONSTANT.SOCIAL_LIVE_ID,
                    success: res => {
                        console.info(res);
                    },
                    fail: res => {
                        console.info(res);
                    }
                });
                wx.openChannelsLive({
                    finderUserName: CONSTANT.SOCIAL_LIVE_ID,
                    success: res => {
                        console.info(res);
                    },
                    fail: res => {
                        console.info(res);
                    }
                })
                return;
                break;
            case 'tagCloudRecruitment':
                break;
            case 'tagPersonalCenter':
                // 重置 身份切换完成器
                this.setData({
                    hiddenChooseIdentity: false,
                })
                this.identitySwitchCompleter = new Completer();
                await this.identitySwitchCompleter.promise;
                // 如果是招聘者  修改路由到
                if (wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY) == CONSTANT.UserRole.Recruiter) {
                    // 判断是否有公司
                    let userRecruiterInfo = (await userRecruiterService.loadEntityById(this.data.openid)).data;
                    console.info(userRecruiterInfo);
                    let companyUuid = userRecruiterInfo.companyUuid;
                    if (string_util.isEmpty(companyUuid)) {
                        console.debug(`User Openid: ${this.data.openid} hasn't register Company`);
                        let tryBindCompanyRes = await prompt_util.showModalPrompt("是否尝试绑定后台已有公司")
                        if (tryBindCompanyRes.confirm) {
                            console.log("点击确定");
                            // 弹窗获取电话号码授权
                            this.bindCompanyCompleter = new Completer();
                            this.setData({
                                hiddenBindCompanyInfo: false
                            })
                            let bindRes = await this.bindCompanyCompleter.promise;
                            if (!bindRes) {
                                linkUrl = '/pages/qyzc/qyzc';
                                console.debug(`用户绑定后台公司失败， 前往企业注册页面: ${linkUrl}`);
                            }
                        } else {
                            // 企业注册
                            linkUrl = '/pages/qyzc/qyzc';
                            console.debug(`用户取消绑定后台公司信息， 前往企业注册页面: ${linkUrl}`);
                        }
                    } else {
                        console.debug(`User Openid: ${this.data.openid} has CompanyUuid: ${companyUuid}`);
                        let recruitCompanyInfo = (await recruitCompanyService.loadEntityById(companyUuid)).data;
                        // 已认证
                        if (recruitCompanyInfo.flagIdentification == 1) {
                            // 不修改路由
                        } else {
                            linkUrl = '/pages/waiteyz/waiteyz';
                        }
                    }
                }
                break;
            case 'tagLaborSupervision':
                prompt_util.showToastWaitForOpen();
                return;
                break;
            default:
                break;
        }
        console.info(`page navigateTo the page: ${linkUrl}`);
        wx.navigateTo({
            url: linkUrl,
        })
    },
    passbtn() {
        this.setData({
            hiddenChooseIdentity: true,
            // juesehide:false
        })
    },
    bindtapChooseCandidate: async function () {
        await iUserRoleService.turnToCandidate();
        this.setData({
            hiddenChooseIdentity: true,
        })
        this.identitySwitchCompleter.resolve();
    },
    bindtapChooseRecruiter: async function () {
        await iUserRoleService.turnToRecruiter();
        this.setData({
            hiddenChooseIdentity: true,
        })
        this.identitySwitchCompleter.resolve();

    },
    bindgetphonenumberRecruiter: async function (e) {
        console.log(e);
        if (e.detail.encryptedData) {
            // 用户同意授权
            let sessionKey = wx.getStorageSync('sessionKey')
            let openid = wx.getStorageSync('openid')
            let res = await wx.request({
                url: app.globalData.web_path + '/wx/user/' + app.globalData.appId + '/phoneNumberInfo',
                data: {
                    sessionKey: sessionKey,
                    iv: e.detail.iv,
                    encryptedData: e.detail.encryptedData,
                },
                header: app.globalData.header,
                success: res => {
                    console.log(res);
                    this.setData({
                        telephone: res.data.data.phoneNumber
                    })
                    wx.setStorageSync('phone', res.data.data.phoneNumber)
                }
            })
            console.log(res);

        } else {
            wx.showToast({
                title: '用户取消授权',
                icon: 'error',
                duration: 1200,
                mask: true,
            });
            return;
        }
    },
    bindtapSubmitBindCompany: async function () {
        if (string_util.isEmpty(this.data.telephone)) {
            wx.showToast({
                title: '请先授权电话',
                icon: 'error',
                duration: 1200,
                mask: true,
            });
        } else {
            // 尝试绑定电话并回填
            let recruitCompanyInfo = (await recruitCompanyService.infoByRecruiterPhone(this.data.telephone)).data;
            if (recruitCompanyInfo) {
                console.log(recruitCompanyInfo);
                console.log(this.data.openid);
                // 获取到绑定公司  进行回填
                await userRecruiterService.updateByEntity({
                    id: this.data.openid,
                    companyUuid: recruitCompanyInfo.id,
                });
                console.log('绑定成功');
                this.setData({
                    hiddenBindCompanyInfo: true,
                });
                this.bindCompanyCompleter.resolve(true);
            } else {
                this.setData({
                    hiddenBindCompanyInfo: true,
                });
                setTimeout(() => wx.showToast({
                    title: '没有绑定公司',
                    icon: 'error',
                    duration: 1200,
                    mask: true,
                }), 1300);

                this.bindCompanyCompleter.resolve(false);
            }
            this.bindCompanyCompleter.resolve(false);
        }
    },

    bindtapSkipBindCompany: async function () {
        // 关闭弹窗
        this.setData({
            hiddenBindCompanyInfo: true,
        })
        this.bindCompanyCompleter.resolve(false);
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let openid = app.getOpenid();
        this.setData({
            openid: openid,
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

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

    }
})