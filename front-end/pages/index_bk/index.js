// index.js
const {
    GlobalKey
} = require('../../service/global_service');

// 求职者模块
const recruitee = require('./recruitee');
// 招聘者模块
const recruiter = require('./recruiter');
// 监听器模块
const listener = require('./listener');
// 内容加载模块
const content = require('./content');
// 加载常量
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
// 加载工具
const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');
const prompt_util = require('../../utils/prompt_util');
const {
    Completer
} = require('../../utils/function_util');
// 加载服务
const recruitCompanyService = require('../../common/recruitCompanyService');
const userCandidateService = require('../../common/userCandidateService');
const jobCategoryService = require('../../common/jobCategoryService');
const recruitJobService = require('../../common/recruitJobService');
const recruitRecordService = require('../../common/recruitRecordService');
const candidateForJobService = require('../../common/candidateForJobService');
const userStateService = require('../../common/userStateService');
const iUserRoleService = require('../../common/iUserRoleService');
const globalService = require('../../common/globalService');

// 获取应用实例
const app = getApp();
Page({
    data: {
        //

        flagOnLoad: true,

        candidateTelephone: "",
        isll: false,
        ...recruitee.createRecruiteeInfo(),
        backgroundimg1: [{
                imgsrc: app.globalData.web_path + '/images/img/zphimg.png',
                url: '/pages/zpzhuanc/zpzhuanc'
            },
            {
                imgsrc: '/img/indexbg.png',
                url: ''
            }, {
                imgsrc: '/img/index_banner2.png',
                url: ''
            },
        ],
        backgroundimg: [{
                imgsrc: app.globalData.web_path + '/images/img/zphimg.png',
                url: '/pages/zpzhuanc/zpzhuanc'
            },
            {
                imgsrc: '/img/indexbg.png',
                url: ''
            }, {
                imgsrc: '/img/index_banner2.png',
                url: ''
            },
        ],
        indicatorDots: true,
        vertical: false,
        autoplay: false,
        interval: 2000,
        duration: 500,
        indicatorColor: 'rgba(0, 0, 0, 0.3)',
        activeColor: '#fff',
        circular: true,
        topnav: [{
                name: '投递记录',
                imsrc: '/img/tdjl.png',
                url: ''
            },
            {
                name: '个人简历',
                imsrc: '/img/grjl.png',
                url: ''
            },
            {
                name: '邀约面试',
                imsrc: '/img/yyms.png',
                url: ''
            },
            {
                name: '修改定位',
                imsrc: '/img/xgdw.png',
                url: ''
            },
        ],
        //判断用户是求职者还是企业  user/company
        ident: 'user',
        nowsf: "",
        animated: false,
        candidateList: [
            // {
            //   jobname: '服务员/保洁', usertag: [{ tagbq: '女' }, { tagbq: '29岁' }, { tagbq: '3000-5000/月' }],
            //   name: '张三', tximg: '/img/tx.png', hxtime: '10分钟前', sqname: '天源社区', companyjuli: '1.2',
            // },
            // {
            //   jobname: '服务员/保洁', usertag: [{ tagbq: '女' }, { tagbq: '29岁' }, { tagbq: '3000-5000/月' }],
            //   name: '李四', tximg: '/img/tx.png', hxtime: '10分钟前', sqname: '天源社区', companyjuli: '1.2',
            // },
            // {
            //   jobname: '服务员/保洁', usertag: [{ tagbq: '女' }, { tagbq: '29岁' }, { tagbq: '3000-5000/月' }],
            //   name: '王二', tximg: '/img/tx.png', hxtime: '10分钟前', sqname: '天源社区', companyjuli: '1.2',
            // },

        ],
        scene: "",
        nianl: [{
            nal: '30-40岁'
        }, {
            nal: '40-50岁'
        }, {
            nal: '50-60岁'
        }, ],
        num2: '',

        xzyq: [{
            xz: '3K-5K'
        }, {
            xz: '5K-8K'
        }, {
            xz: '8K-10K'
        }, ],
        ht: '',

        // 用户授权
        isUserAuthorized: false, // 用户是否授权
        // 授权框隐藏
        hideUserInfoAuth: true,
        // 角色选择
        juesehide: true,
        // 实名认证
        smhide: true,
        // 求职者完善信息
        infows: true,
        sqrphone: "",
        companyUuid: '',
        //企业登录
        qydl: true,
        //提示用户需要获取手机号
        sjh: true,

        isShow: false,
        arrshow: true,

        // joblist: [
        //   { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        //   { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        //   { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        // ],
        recruiterOpenid: "",
        companyUuid: "",
        categoryUuid: "",
    },
    flagRestartCompleter: new Completer(),
    //社区服务
    sqfw() {
        // wx.showToast({
        // 	title: '暂未开放',
        // 	icon:'none'
        // })
        wx.navigateTo({
            url: '/pages/xzsq/xzsq',
        })
    },
    //打电话
    async callphone(e) {
        console.log(e)
        let job = e.currentTarget.dataset.item
        let phonenum = e.currentTarget.dataset.phonenum;
        let switchRole = await iUserRoleService.trySwitchToCandidate();
        if (!switchRole) {
            return;
        }
        console.log(e.currentTarget)
        if (string_util.isEmpty(phonenum)) {
            prompt_util.companyNoPhoneToastPrompt();
            return;
        }
        // 生成 打过电话记录
        let candidateOpenid = wx.getStorageSync('openid');
        try {
            Loading.begin()
            await candidateForJobService.insertFlagCall(candidateOpenid, job.jobUuid);
            // 这里不需要更新页面数据， makephone 再进来会触发 onShow() 事件
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
        wx.makePhoneCall({
            phoneNumber: phonenum //仅为示例，并非真实的电话号码
        })
    },
    //投递简历
    async catchtapSendResume(e) {
        let that = this;
        let currentItem = e.currentTarget.dataset.item
        let candidateOpenid = wx.getStorageSync('openid');

        let switchRole = await iUserRoleService.trySwitchToCandidate();
        if (!switchRole) {
            return;
        }
        if (currentItem.flagApply) {
            wx.showToast({
                title: '您已投递',
                duration: 1500,
            })
            return;
        }

        // 询问确定
        let confirmModalRs = await wx.showModal({
            title: '提示',
            content: '是否确定投递该岗位',
            showCancel: true,
            cancelText: '取消',
            confirmText: '确定',
        });
        if (!confirmModalRs.confirm) {
            return;
        }
        let index = e.currentTarget.dataset.index;
        //发送简历消息
        recruitee.createRecruiteeMethods().sendjianlixx(currentItem.recruiterOpenid, currentItem.jobname, candidateOpenid)
        // 生成简历记录
        let insertData = {
            flagWhoReceive: 1,
            flowRecruit: 0,
            candidateOpenid: candidateOpenid,
            recruiterOpenid: currentItem.recruiterOpenid,
            companyUuid: currentItem.companyUuid,
            jobUuid: currentItem.jobUuid,
            categoryUuid: currentItem.categoryUuid,
        }
        try {
            Loading.begin();
            await recruitRecordService.insertByEntity(insertData);
            // 投递简历 次数 ++
            await recruitJobService.increaseCountApply(currentItem.jobUuid);
            // 优化用户体验 这里修改页面数据
            this.setData({
                ['jobInfoList[' + index + '].flagApply']: true,
                ['jobInfoList[' + index + '].flagApplyText']: '已投递',
            })
            wx.showToast({
                title: '投递成功',
                duration: 2000,
            })
        } catch (e) {
            console.error(e);
            wx.showToast({
                title: '投递失败',
                duration: 2000,
            })
        } finally {
            Loading.end();
        }
    },
    // 点击跳转到岗位详情
    async bindtapCandidateJobInfo(e) {
        let recruitJobUuid = this.data.jobInfoList[e.currentTarget.dataset.index].jobUuid;
        let distance = this.data.jobInfoList[e.currentTarget.dataset.index].jl;
        wx.navigateTo({
            url: '/pages/zwxq/zwxq?recruitJobUuid=' + recruitJobUuid + '&distance=' + distance,
        })
    },
    // 点击跳转到求职者详情
    async bindtapGoCandidateDetail(e) {
        console.log(e.currentTarget.dataset.index);
        let currentIndex = e.currentTarget.dataset.index;
        let currentCandidateOpenid = this.data.candidateList[currentIndex].candidateOpenid;
        wx.navigateTo({
            url: '/pages/loojl/lookjl?candidateOpenid=' + currentCandidateOpenid,
        })
    },

    //打电话
    catchtapCandidateCallPhone: async function (e) {
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        if (!switchRole) {
            return;
        }
        let currentPhone = e.currentTarget.dataset.phone;
        wx.makePhoneCall({
            phoneNumber: currentPhone,
            fail: (res) => {
                console.info('User cancel call the phone', res)
            }
        })
    },
    //求职者搜索函数
    searchjob() {
        wx.navigateTo({
            url: '/pages/searchpage/searchpage',
        })
    },
    //企业搜索函数
    searchjob1() {
        wx.navigateTo({
            url: '/pages/qysearch/qysearch',
        })
    },
    //添加意向
    btnExpectJob() {
        wx.navigateTo({
            url: '/pages/wantjob/wantjob',
        })
    },
    //＋发布招聘
    btnPostJob() {
        wx.navigateTo({
            url: '/pages/fbjob/fbjob',
        })
    },
    // 错误 公司头像
    binderrorCompanyPortrait(e) {
        console.error(e);
        let img = 'jobInfoList[' + e.currentTarget.dataset.index + '].companytx';
        this.setData({
            [img]: CONSTANT.STATIC_IMG_URL.portrait_company,
        })
    },
    // 错误 求职者头像
    binderrorCandidatePortrait(e) {
        console.error(e);
        let img = 'candidateList[' + e.currentTarget.dataset.index + '].tximg';
        this.setData({
            [img]: Boolean(this.data.candidateList[e.currentTarget.dataset.index].gender) ?
                CONSTANT.STATIC_IMG_URL.portrait_personal_female : CONSTANT.STATIC_IMG_URL.portrait_personal_male,
        })
    },

    //完善信息
    wsxnhide() {
        let infows = this.data.infows
        this.setData({
            infows: true
        })
    },
    //提示用户需要获取手机号*确认*
    setPhone(openid, phone) {
        var that = this;
        wx.request({
            url: app.globalData.web_path + '/gywm/setWxuserandCompPhone',
            data: {
                openid: openid,
                phone: phone,
            },
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
            },
            fail: function (res) {}
        })
    },
    getPhoneNumber(e) {
        let that = this
        var sessionKey = wx.getStorageSync('sessionKey')
        var openid = wx.getStorageSync('openid')
        console.log(openid)
        console.log(e)
        console.log(sessionKey)
        wx.request({
            url: app.globalData.web_path + '/wx/user/' + app.globalData.appId + '/phoneNumberInfo',
            data: {
                sessionKey: sessionKey,
                iv: e.detail.iv,
                encryptedData: e.detail.encryptedData,
            },
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
                console.log(res.data.data.phoneNumber)
                that.setData({
                    sqrphone: res.data.data.phoneNumber
                })
                that.setPhone(openid, res.data.data.phoneNumber);
                wx.setStorageSync('phone', res.data.data.phoneNumber)
            },
            fail: function (res) {}
        })
    },
    //提示用户需要获取手机号*忽略*
    hulue() {
        this.setData({
            hideUserInfoAuth: true,
            // juesehide:false
        })
    },

    //实名认证跳过
    passbtn() {
        this.setData({
            smhide: true,
            // juesehide:false
        })
    },
    //求职者完善信息跳过
    passbtnwsxx() {
        this.setData({
            infows: true,
            // juesehide:false
        })
    },




    // 年龄选择
    nlxz(e) {
        let that = this;
        let index = e.currentTarget.dataset.index2;
        that.setData({
            num2: index
        })
    },


    // 求职者完善信息end
    //企业登录
    qydltijsq() {
        let qydl = this.data.qydl
        this.setData({
            qydl: true
        })
    },
    // 选择求职者
    bindtapChooseCandidate: async function () {
        await this.handleChooseCandidate();
        wx.requestSubscribeMessage({
            //zOhHPg2OJCVaFetsGxWkqItK8OY-K6XnkgQKgViRa8A
            tmplIds: ['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU'],
            success(res) {
                console.log(res)
                if (res['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU'] == 'accept') {
                    console.log(111)

                }
            }
        })
    },


     handleChooseCandidate: async function() {
        globalService.setGlobalUserIdentity(CONSTANT.UserRole.Recruitee)
        this.setData({
            ident: 'user',
            nowsf: '求职者',
        })
        await this._handleRecruiteeSelected();
        // 重新加载内容
        await this.reloadContent();
    },


    //招聘方
    bindtapChooseRecruiter: async function () {
        await this.handleChooseRecruiter();
        wx.requestSubscribeMessage({
            //zOhHPg2OJCVaFetsGxWkqItK8OY-K6XnkgQKgViRa8A
            tmplIds: ['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU'],
            success(res) {
                console.log(res)
                if (res['zCyKj9S-dZ24qVDK-UXkf2-sZ8dBYyLXHOsZiT9jOUU'] == 'accept') {
                    console.log(111)

                }
            }
        })

    },
    async handleChooseRecruiter() {
        globalService.setGlobalUserIdentity(CONSTANT.UserRole.Recruiter)
        this.setData({
            ident: 'company',
            nowsf: '招聘方',
        })
        await this._handleRecruiterSelected();
        // 重新加载内容
        await this.reloadContent();
    },

    onUnload() {
        // 销毁监听器
        app.globalMonitors.sendResumeMonitor.removeListener(this.indexSendResumeListener);
        app.globalMonitors.userRoleChangeMonitor.removeListener(this.indexUserRoleChangeListener);
        app.globalMonitors.userLocationChangeMonitor.removeListener(this.indexUserLocationChangeListener);
        app.globalMonitors.candidateTelephoneChangeMonitor.remove(this.indexCandidateTelephoneListener);
    },
    addshare(openid) {
        var that = this;
        wx.request({
            url: app.globalData.web_path + '/share/insert',
            data: {
                openid: openid,
            },
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
            },
            fail: function (res) {}
        })
    },
    async onLoad(options) {
        console.log(options)
        let that = this;
        if (options.openid) {
            console.log(111)
            that.addshare(options.openid)
        }
        // 等待app初始化完成
        await app.getAppInitReady();
        // 简历状态监听
        app.globalMonitors.sendResumeMonitor.addListener(this.indexSendResumeListener);
        // 用户角色监听
        app.globalMonitors.userRoleChangeMonitor.addListener(this.indexUserRoleChangeListener);
        // 用户位置监听
        app.globalMonitors.userLocationChangeMonitor.addListener(this.indexUserLocationChangeListener);
        // 求职者是否 有电话监听
        app.globalMonitors.candidateTelephoneChangeMonitor.addListener(this.indexCandidateTelephoneListener);
        wx.request({
            url: app.globalData.web_path + '/banner_for_page/searchBanner',
            data: {},
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
                let images = res.data.bannerForPageList;
                let backgroundimg1 = [];
                let backgroundimg = [];
                for (let i = 0; i < images.length; i++) {
                    const element = images[i];
                    var image = {
                        imgsrc: images[i].bannerImgPath,
                        url: images[i].pagePath
                    }
                    backgroundimg1.push(image)
                    backgroundimg.push(image)
                }
                that.setData({
                    backgroundimg1: backgroundimg1,
                    backgroundimg: backgroundimg
                })
            },
            fail: function (res) {}
        })

        // let that = this
        //自定义头部方法
        this.setData({
            navH: app.globalData.navHeight,
            margtop: app.globalData.navHeight
        });
        this.data.wantjob[0].checked = true;
        this.setData({
            wantjob: this.data.wantjob,
        })
        //设置scroll-view的高度
        console.log(app.globalData.arrij.screenHeight)

        that.setData({
            ht: app.globalData.arrij.screenHeight - 119
        })
        // 加载完善信息 的工作选择列表
        let jobCategoryListResult = await jobCategoryService.loadList();
        console.log(jobCategoryListResult)
        let wantjobList = jobCategoryListResult.data.map(r => {
            return {
                job: r.categoryName,
                id: r.id,
                checked: false,
            };
        })
        wantjobList.splice(4, wantjobList.length - 5);
        this.setData({
            wantjob: wantjobList,
        })
        await this.init()
    },

    async onShow() {

    },

    async init() {
        // 全局
        let that = this;
        this.setData({
            ident: globalService.getGlobalUserIdentity(), 
        });
        await app.getOpenidReady();
        // 授权
        this.authorize = this.selectComponent("#authorize");
        let authorizeRs = await this.authorize.doAuthorize();
        if (!authorizeRs) {
            prompt_util.authorizeNamePortraitFailPrompt();
        }

        let openid = wx.getStorageSync('openid');
        let userStateData = await userStateService.loadEntityById(openid);

        userStateData = userStateData.data;
        console.log(userStateData);
        if (!userStateData || !userStateData.userRole) {
            this.setData({
                juesehide: false,
            });
        } else if (userStateData.userRole == 1) {
            await this.handleChooseCandidate();
        } else if (userStateData.userRole == 2) {
            await this.handleChooseRecruiter();
        } else {
            this.setData({
                juesehide: false,
            });
        }
    },
    // 修改定位
    async changeLocation(e) {
        let that = this
        let wxChooseLocation = wx.chooseLocation({
            type: 'gcj02',
        });
        wxChooseLocation.then(async res => {
            that.setData({
                mapLocation: {
                    longitude: res.longitude,
                    latitude: res.latitude,
                },
            });
            if (that.data.ident == 'user') {
                let updateData = {
                    id: that.data.openid,
                    lon: res.longitude,
                    lat: res.latitude,
                    address: res.address + res.name,
                }
                try {
                    Loading.begin();
                    await userCandidateService.updateByEntity(updateData);
                } catch (e) {
                    console.error(e);
                } finally {
                    Loading.end();
                }
            } else if (that.data.ident == 'company') {
                let updateData = {
                    id: that.data.companyUuid,
                    lon: res.longitude,
                    lat: res.latitude,
                    address: res.address + res.name,
                    addressDetail: '', // 清空详细地址（门牌号等）
                }
                try {
                    Loading.begin();
                    await recruitCompanyService.updateRecruitCompany(updateData);
                } catch (e) {
                    console.error(e);
                } finally {
                    Loading.end();
                }
            }
            app.globalMonitors.userLocationChangeMonitor.notifyAll();
        }).catch(e => {
            console.info('用户取消  修改定位')
        })
    },

    onReachBottom: async function () {
        this.loadContent();
    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function (res) {
        let openid = wx.getStorageSync('openid');
        console.log(res)
        var that = this;
        //如果用户是点击按钮进行分享的
        if (res.from == 'button') {
            return {
                title: "111", //分享出去的标题
                // imageUrl: "/img/indexbg.png", //分享时显示的图片
                path: "pages/index/index?openid=" + openid //别人点击链接进来的页面及传递的参数
            }
        } else {
            return {
                title: "分享-社区就业小程序", //分享出去的标题
                imageUrl: app.globalData.web_path + '/images/img/zphimg.png', //分享时显示的图片
                // desc: '分享页面的内容',
                path: "pages/index/index?openid=" + openid //别人点击链接进来的页面及传递的参数
            }
        }
    },
    ...recruitee.createRecruiteeMethods(),
    ...recruiter.createRecruiterMethods(),
    ...content.createContentMethods(),
    ...listener.createListenerMethods(),

})