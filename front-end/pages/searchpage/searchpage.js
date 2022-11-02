// pages/searchpage/searchpage.js
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const { Salary } = require('../../common/constant');
// 加载工具
const $ = require('../../utils/request_util');
const listener = require('./listener');
const string_util = require('../../utils/string_util');
const Loading = require('../../utils/loading_util');
const prompt_util = require('../../utils/prompt_util');
// 加载服务
const recruitJobService = require('../../common/recruitJobService');
const recruitRecordService = require('../../common/recruitRecordService');
const userCandidateService = require('../../common/userCandidateService');
const iUserRoleService = require('../../common/iUserRoleService');

// 附加模块
const searchpageEx = require('./searchpageEx');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {

        hiddenJobList: false,
        flagChangeToCompanyList: false,

        // 求职用户 信息
        userOpenid: '',
        location: {},

        // 弹窗
        sxkz: true,
        joblist: [
            // { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
            // { jobname: '清洁工', jobmoney: '3000-3800', companyname: '文和友餐饮有限公司', companytx: '/img/tx.png', jl: '1.5', phonenum: '13112345678' },
        ],
        // 月薪
        yxyq: [
            // { yxmoney: '不限', id: 0, checked: true }, { yxmoney: '2千以下', id: 1 }, { yxmoney: '2千-3千', id: 2 }, { yxmoney: '3千-4千', id: 3 }, { yxmoney: '4千-5千', id: 4 }, { yxmoney: '5千-7千', id: 5 }, { yxmoney: '7千-1万', id: 6 }, { yxmoney: '1万-1.5万', id: 7 }
        ],
        currentYxyq: {},
        index: '',


        candidateOpenid: '',
        candidateTelephone: '',
        //搜索条件

        searchText: '',
        jobSalaryMin: -1,
        jobSalaryMax: -1,
        searchType: 0,
        // 距离
        nyxz: [
            { nl: '不限', id: 0, checked: true }, { nl: '距离最近', id: 1 }
        ],
        searchTypeList: [
            { name: '职位', checked: true }, { name: '企业', checked: true }
        ],
        oid: 0,
        hidesx: true,
        companyList: [
            // { companyUuid:'', companyName: '长沙竟网信息科技有限公司', companyPortraitPath: '', recruiterName: '张经理', distance: '1.5', phone: '13112345678' },
            // { companyUuid:'', companyName: '长沙竟网信息科技有限公司', companyPortraitPath: '', recruiterName: '张经理', distance: '1.5', phone: '13112345678' },
        ],
    },
    // 搜索栏输入
    bindinputSearchCategory(e) {
        this.setData({
            searchText: e.detail.value,
        })
    },
    // 搜索栏回车
    bindconfirmSearchCategory(e) {
        if ((this.data.searchTypeList[0].checked && this.data.searchTypeList[1].checked)
            || (!this.data.searchTypeList[0].checked && !this.data.searchTypeList[1].checked)) {
            this.setData({ flagChangeToCompanyList: false });
        }

        this.setData({
            searchText: e.detail.value,
        })
        try {
            Loading.begin();
            this.clearContent();
            this.loadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end()
        }
    },


    // 筛选事件
    sx() {
        let hidesx = !this.data.hidesx
        this.setData({
            hidesx: hidesx
        })
    },
    //月薪
    bindtapChooseSalary(e) {
        let id = e.currentTarget.dataset.id
        let yxyq_list = this.data.yxyq.map((v, i) => {
            v.checked = (i == id) ? true : false;
            return v;
        })
        this.setData({
            yxyq: yxyq_list
        })
    },
    //	点击跳转到对应公司
    bindtapChooseCompany(e) {
        let currentIndex = e.currentTarget.dataset.index;
        let currentCompany = this.data.companyList[currentIndex];
        let companyUuid = currentCompany.companyUuid;
        wx.navigateTo({
            url: '/pages/commessxq/commessxq?companyUuid=' + companyUuid,
        })
    },
    // 打电话 公司
    async catchtapCallCompany(e) {
        console.log(e);
        let currentPhone = e.currentTarget.dataset.phone;
        let switchRole = await iUserRoleService.trySwitchToCandidate();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }

        wx.makePhoneCall({
            phoneNumber: currentPhone,
        })
    },
    //投递简历
    async catchtapSendResume(e) {
        let that = this;
        let currentItem = e.currentTarget.dataset.item
        let candidateOpenid = wx.getStorageSync('openid');

        let switchRole = await iUserRoleService.trySwitchToCandidate();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
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
        let index = e.currentTarget.dataset.index;
        // 生成简历记录
        console.log(currentItem);
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
            });
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
    //距离
    bindtapChooseDistance(e) {

        let id = e.currentTarget.dataset.id
        console.log(id)
        for (let i = 0; i < this.data.nyxz.length; i++) {
            if (this.data.nyxz[i].id == id) {
                this.setData({
                    oid: id
                })
            }
        }

    },
    //类型
    bindtapChooseType(e) {
        let index = e.currentTarget.dataset.index;
        let currentSearchTypeList = this.data.searchTypeList;
        this.setData({
            searchTypeList: currentSearchTypeList.map((v, i) => {
                if (i == index) {
                    v.checked = !v.checked;
                }
                return v;
            })
        })
    },
    //打电话
    async callphone(e) {
        console.log(e);
        let switchRole = await iUserRoleService.trySwitchToCandidate();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
        let phonenum = e.currentTarget.dataset.phonenum
        if(string_util.isEmpty(phonenum)){
            prompt_util.companyNoPhoneToastPrompt();
            return;
        }
        wx.makePhoneCall({
            phoneNumber: phonenum, //仅为示例，并非真实的电话号码
        })
    },
    //清空
    clear() {
        console.log(this.data.yxyq)
        let yxyqList = this.data.yxyq.map((v) => {
            v.checked = false;
            return v;
        })
        console.log(yxyqList);
        for (let q = 0; q < this.data.nyxz.length; q++) {
            this.setData({
                oid: 100
            })
        }
        let currentSearchTypeList = this.data.searchTypeList;
        this.setData({
            searchTypeList: currentSearchTypeList.map(v => {
                v.checked = true;
                return v;
            })
        })
        // 重置职位选择
        this.setData({
            yxyq: yxyqList
        })
    },
    //确定
    async bindtapSearchSubmit() {
        if ((this.data.searchTypeList[0].checked && this.data.searchTypeList[1].checked)
            || (!this.data.searchTypeList[0].checked && !this.data.searchTypeList[1].checked)) {
            this.setData({ flagChangeToCompanyList: false });
        }
        // 获取薪资范围参数
        let currentYxyq = this.data.yxyq.find(v => {
            return v.checked;
        })
        this.setData({
            currentYxyq: currentYxyq,

        })
        if (currentYxyq == undefined || currentYxyq.id == 0) {
            await this.clearContent();
            await this.loadContent();

        } else {
            this.setData({
                jobSalaryMin: currentYxyq.min,
                jobSalaryMax: currentYxyq.max,
            })
            await this.clearContent();
            await this.loadContent();
        }

        let hidesx = !this.data.hidesx
        this.setData({
            hidesx: hidesx
        })
    },
    // 点击跳转到对应岗位
    async bindtapChooseJob(e) {
        let jobData = this.data.joblist[e.currentTarget.dataset.index]
        let recruitJobUuid = jobData.jobUuid;
        // TODO 生成浏览记录  +  浏览量+1
        try {
            Loading.begin();
        } catch (e) {
            console.log(e);
        } finally {
            Loading.end();
            wx.navigateTo({
                url: "/pages/zwxq/zwxq?recruitJobUuid=" + jobData.jobUuid,
            })
        }

    },
    // 错误公司头像 替换
    binderrorCompanyPortrait(e) {
        console.error(e);
        let img = 'joblist[' + e.currentTarget.dataset.index + '].companytx';
        this.setData({
            [img]: CONSTANT.STATIC_IMG_URL.portrait_company,
        })
    },
    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        app.globalMonitors.sendResumeMonitor.removeListener(this.sendResumeListener);
        app.globalMonitors.candidateTelephoneChangeMonitor.removeListener(this.candidateTelephoneListener);
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        app.globalMonitors.sendResumeMonitor.addListener(this.sendResumeListener);
        app.globalMonitors.candidateTelephoneChangeMonitor.addListener(this.candidateTelephoneListener);

        let pages = getCurrentPages();
        let prevPage = pages[pages.length - 2];
        console.log(prevPage);
        // 蓉社通主页
        if(prevPage.route ==  "pages/society-bureau-home-page/society-bureau-home-page"){
            wx.setNavigationBarTitle({
                title: '零工市场',
                
            });
        }
        this.lock = new Loading.Lock();

        let that = this;
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight,
                });
            }
        })
        // 获取用户openid
        await app.getOpenidReady();
        let openid = wx.getStorageSync('openid');

        try {
            let userCandidate = await userCandidateService.loadEntityById(openid)
            userCandidate = userCandidate.data;
            this.setData({
                candidateOpenid: openid,
                candidateTelephone: userCandidate.telephone
            })
        } catch (e) {
            console.error(e)
        }
        // 搜索条件 - 加载薪资
        let yxyq_list = [];
        let salaryList = CONSTANT.salaryList;
        // 特殊 - 不限薪资
        let unlimitSalary = new Salary(-1, -1);
        yxyq_list = salaryList.map((v, i) => {
            return {
                min: v.min,
                max: v.max,
                yxmoney: v.value,
                id: i + 1, // 预留第一位为默认 薪资不限
                checked: false,
            }
        });
        yxyq_list.unshift({
            min: unlimitSalary.min,
            max: unlimitSalary.max,
            yxmoney: unlimitSalary.value,
            id: 0,
            checked: true,
        })
        this.setData({
            yxyq: yxyq_list,
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
    onShow: async function () {
        try {
            Loading.begin();
            // 需要openid 加載用戶參數
            await app.getOpenidReady();
            // 每次进入前清空content
            await this.clearContent();
            await this.loadContent();

        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }
    },
    /**
         * 页面上拉触底事件的处理函数
         */
    onReachBottom: async function () {
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }

    },
    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },



    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },



    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },

    // 页面分页内容相关
    ...searchpageEx.createPageMethods(),
    // 监听器方法相关
    ...listener.createListenerMethods(),
})