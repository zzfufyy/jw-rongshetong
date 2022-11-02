// pages/schoolHomePage/schoolHomePage.js
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

const pageNews = require('./pageNews');

const app = getApp();

Page({
    /**
     * 页面的初始数据
     */
    data: {
        openid: "",
        communityUuid: "",
        hiddenBindCompanyInfo: true,
        hiddenChooseIdentity: true,
        flagIdentification: false,
        // 云直播- 视频号二维码
        isOpenQrCodeCloudLive: false,
        qrcodeCloudLive: img_util.handleNormalPortraitPath('/images/img/social/qrcode_cloud_live.jpg'),
        bannerImgList: [
            {
                imgSrc: img_util.handleNormalPortraitPath('/images/img/social/social-bureau-home-page-banner1.png'),
                newsUuid: '1574938780834869250',
            },
            {
                imgSrc: img_util.handleNormalPortraitPath('/images/img/social/social-bureau-home-page-banner2.png'),
                newsUuid: '1574936460667531266',
            },
            {
                imgSrc: img_util.handleNormalPortraitPath('/images/img/social/social-bureau-home-page-banner3.png'),
                newsUuid: '1565951501420359681',
            },
            {
                imgSrc: img_util.handleNormalPortraitPath('/images/img/social/social-bureau-home-page-banner4.png'),
                newsUuid: '1565952706536173570',
            },
            {
                imgSrc: img_util.handleNormalPortraitPath('/images/img/social/social-bureau-home-page-banner5.png'),
                newsUuid: '1565954179890311170',
            }
        ],
        tabList: [{
                imgSrc: "../../img/society-bureau-home-page/tab_company_employment.svg",
                tabName: "企业招聘",
                tabId: "tagRecruitCompany",
                pagePathLink: "/pages/comzp/comzp"
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_job_empolyment.svg",
                tabName: "求职就业",
                tabId: "tagRecruitCandidate",
                pagePathLink: "/pages/qzjy/qzjy"
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_cloud_live.svg",
                tabName: "云直播",
                tabId: "tagCloudLive",
                pagePathLink: "",
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_cloud_recruitment.svg",
                tabName: "云招聘",
                tabId: "tagCloudRecruitment",
                pagePathLink: "/pages/social-cloud-recruitment/social-cloud-recruitment",
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_training_service.svg",
                tabName: "培训服务",
                tabId: "tagTrainingService",
                pagePathLink: "/pages/social-training-service/social-training-service",
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_social_service.svg",
                tabName: "社保服务",
                tabId: "tagSecurityService",
                pagePathLink: "/pages/social-security-service/social-security-service",
            },
            {
                imgSrc: "../../img/society-bureau-home-page/tab_relevant_policy.svg",
                tabName: "相关政策",
                tabId: "tagRelevantPolicy",
                pagePathLink: "/pages/social-relevant-policy/social-relevant-policy",
            },

            {
                imgSrc: "../../img/society-bureau-home-page/tab_more.svg",
                tabName: "更多",
                tabId: "tagMore",
                pagePathLink: "/pages/society-home-page-more/society-home-page-more",
            },
            // {
            //     imgSrc: "../../img/society-bureau-home-page/tab_business_incubation.svg",
            //     tabName: "创业孵化",
            //     tabId: "tagBusinessIncubation",
            //     pagePathLink: "/pages/news-article-type/news-article-type",
            // },
            // {
            //     imgSrc: "../../img/society-bureau-home-page/tab_personnel_exam.svg",
            //     tabName: "人事考试",
            //     tabId: "tagPersonnelExam",
            //     pagePathLink: "/pages/news-article-type/news-article-type",
            // },
            // {
            //     imgSrc: "../../img/society-bureau-home-page/tab_personnel_center.svg",
            //     tabName: "个人中心",
            //     tabId: "tagPersonalCenter",
            //     pagePathLink: "/pages/personalCenter/personalCenter",
            // },
        ],
        bigTabList: [{
            imgSrc: "../../img/society-bureau-home-page/big_tab_flex_labor.svg",
            tabName: "零工市场",
            tagNameColor: "#FF6464 ",
            tabIntroduction: "时间/地点自由",
            tabId: "bigTabFlexLabor",
            pagePathLink: "/pages/searchpage/searchpage",
        }, {
            imgSrc: "../../img/society-bureau-home-page/big_tab_building_employment.svg",
            tabName: "楼宇就业",
            tagNameColor: "#4042E2",
            tabIntroduction: "推荐附近好工作",
            tabId: "bigTabBuildingEmployment",
            pagePathLink: "/pages/social-building-employment/social-building-employment",

        }, {
            imgSrc: "../../img/society-bureau-home-page/big_tab_annoucement.svg",
            tabName: "公示公告",
            tagNameColor: "#4042E2",
            tabIntroduction: "最新平台公告",
            tabId: "bigTabAnnouncement",
            pagePathLink: "/pages/news-article-type/news-article-type",
        }, {
            imgSrc: "../../img/society-bureau-home-page/big_tab_business_incubation.svg",
            tabName: "创业孵化",
            tagNameColor: "#FF6464",
            tabIntroduction: "政府指导",
            tabId: "bigTabBusinessIncubation",
            pagePathLink: "/pages/news-article-type/news-article-type",

        }],
        newsTabList: [{
                text: "动态",
                tabName: "socialDynamicalNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialDynamicalNews.id,
                checked: true,
            },
            {
                text: "社保",
                tabName: "socialSecurityNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialSecurityNews.id,
                checked: false,
            },
            {
                text: "就业",
                tabName: "socialEmploymentNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialEmploymentNews.id,
                checked: false,
            },
            {
                text: "人事",
                tabName: "socialPersonnelNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialPersonnelNews.id,
                checked: false,
            },
            {
                text: "监察",
                tabName: "socialMonitorNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialMonitorNews.id,
                checked: false,
            },
            {
                text: "公告",
                tabName: "socialAnnouncementNews",
                articleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialAnnouncementNews.id,
                checked: false,
            },
        ],
        newsList: [
            // {

            //     articleTitle: "文章标题1",
            //     day: "20",
            //     yearMonth: "2022-08",
            // },           
        ],
        newsListArticleType: CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialDynamicalNews.id,
    },


    identitySwitchCompleter: new Completer(), // 身份切换完成器
    communityUuidCompleter: new Completer(), // 社区uuid 获取完成器
    bindCompanyCompleter: new Completer(), // 绑定公司完成器
    onLoad: async function (options) {
        this.communityUuidCompleter = new Completer();
        await app.getAppInitReady();
        let communityUuid = "a27c1460-2b56-11ed-b3bb-525401030264";
        // 设置全局社区uuid
        globalService.setGlobalCommunityUuid(communityUuid);
        this.setData({
            openid: app.getOpenid(),
            communityUuid: communityUuid,
        })
        this.communityUuidCompleter.resolve(communityUuid);
        // 用户授权
        this.authorize = this.selectComponent("#authorize");
        let authorizeRs = await this.authorize.doAuthorize();
        if (!authorizeRs) {
            prompt_util.authorizeNamePortraitFailPrompt();
        }
        let userRole = await iUserRoleService.getUserRole();
        console.info(`Now user identity: ${userRole}`);

        try {
            Loading.begin();
            await this.clearContentNewsList();
            await this.loadContentNewsList();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }

    },
    bindtapFlexibleLabor: function () {
        wx.navigateTo({
            url: '/pages/searchpage/searchpage',
        })
    },
    bindtapBuildingEmployment: function () {
        wx.navigateTo({
            url: '/pages/social-building-employment/social-building-employment',
        })
    },

    bindtapChooseNewsTab: async function (e) {
        let index = e.currentTarget.dataset.index;
        let currentIndex = this.data.newsTabList.findIndex(v => {
            return v.checked == true;
        })
        if (index == currentIndex) {
            return;
        }
        let newsListArticleType = this.data.newsListArticleType;
        let changedList = this.data.newsTabList.map((v, itemIndex) => {
            if (itemIndex == index) {
                v.checked = true;
                newsListArticleType = v.articleType;
            } else {
                v.checked = false;
            }
            return v;
        })
        console.info(newsListArticleType);
        this.setData({
            newsTabList: changedList,
            newsListArticleType: newsListArticleType,
        })
        this.clearContentNewsList();
        try {
            // Loading.begin();
            await this.loadContentNewsList();
        } catch (e) {
            console.error(e);
        } finally {
            // Loading.end();
        }
        console.log(this.data.newsTabList);

    },
    /** begin 页面事件 */
    bindtapCloseQrcode: function () {
        this.setData({
            isOpenQrCodeCloudLive: false,
        })
    },
    bindtapGoLinkBanner: function (e) {
        let newsUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '/pages/zxxq/zxxq?newsUuid=' + newsUuid,
        })
    },
    bindtapGoNewsDetail: function (e) {
        let newsUuid = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '/pages/zxxq/zxxq?newsUuid=' + newsUuid,
        })
    },
    bindtapGoLinkBigTab: function (e) {
        let id = e.currentTarget.dataset.id;
        console.log(id);
        let linkUrl = this.data.bigTabList.find(v => {
            return id == v.tabId;
        }).pagePathLink;
        console.info(`Choose the tab: ${id}`);
        switch (id) {
            case "bigTabFlexLabor":
                break;
            case "bigTabBuildingEmployment":
                break;
            case "bigTabAnnouncement":
                linkUrl += `?articleType=${CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialAnnouncementNews.id}&navigationBarTitle=公示公告`;
                break;
            case "bigTabBusinessIncubation":
                linkUrl += `?articleType=${CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialIncubationNews.id}&navigationBarTitle=创业孵化`;
                break;
        }
        wx.navigateTo({
            url: linkUrl,
        });
    },
    bindtapGoLink: async function (e) {
        let id = e.currentTarget.dataset.id;
        let linkUrl = this.data.tabList.find(v => {
            return id == v.tabId;
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
                linkUrl += ('?articleType=' + CONSTANT.SOCIAL_INFORMATION_NEWS_TYPE.socialPersonnelExamNews.id);
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
            default:
                break;
        }
        console.info(`page navigateTo the page: ${linkUrl}`);

        wx.navigateTo({
            url: linkUrl,
        })
    },
    bindscrolltolowerMoreNews: async function () {
        try {
            Loading.begin();
            await this.loadContentNewsList();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
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
    /** end 页面事件 */

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

    },

    ...pageNews.createPageMethods(),
})