// pages/wantjob/wantjob.js
const CONSTANT = require('../../common/constant');
const Loading = require('../../utils/loading_util');
// 加载服务
const { UserService } = require('../../service/user_service');
const candidateForCategoryService = require('../../common/candidateForCategoryService');
const candidateForCommunityService = require('../../common/candidateForCommunityService');
const userCandidateService = require('../../common/userCandidateService');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        qwzw: '请输入期望职位',
        hiddenJobCategory: true,
        jobCategoryList: [],
        // 期望职位列表
        arrtag: [],

        txthide: false,
        arrhide: true,

        // 期望薪资
        salaryStringList: [],
        salaryList: [],
        index: '0',
        choosedCommunityName: '',
        choosedCommunityList: [],
        id: 0,
        oht: '',

    },
    //薪资选择
    bindPickerChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            index: e.detail.value
        })
    },

    // 全部保存事件
    bc: async function () {
        // 求职用户openid
        let candidateOpenid = this.data.candidateOpenid;
        let jobCategoryList = this.data.jobCategoryList.filter(v => v.checked);
        let currentSalary = this.data.salaryList[this.data.index];
        console.log(this.data);
        try {
            Loading.begin();
            // 构建 更新用户数据
            let updateCandidateData = {
                id: candidateOpenid,
                expectSalaryMin: currentSalary.min,
                expectSalaryMax: currentSalary.max,
            }
            await userCandidateService.updateByEntity(updateCandidateData);
            // 构建 插入 期望职位数据
            let insertExpectCategoryList = [];
            jobCategoryList.forEach(v => {
                insertExpectCategoryList.push({
                    candidateOpenid: candidateOpenid,
                    categoryUuid: v.categoryUuid,
                    categoryName: v.categoryName,
                })
            });
            await candidateForCategoryService.insertByEntityList(candidateOpenid, insertExpectCategoryList);
            // 插入期望社区
            if (this.data.choosedCommunityList.length != 0) {
                let insertExpectCommunityList = this.data.choosedCommunityList;
                insertExpectCommunityList = insertExpectCommunityList.map(v => {
                    v.candidateOpenid = candidateOpenid;
                    return v;
                })
                await candidateForCommunityService.insertByEntityList(candidateOpenid, insertExpectCommunityList)
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
        wx.navigateBack({
            delta: 0,
        });
    },
    /**BEGIN 组件事件 - 选择期望职位*/
    bindtapChooseJobCategory(e) {
        console.log(e);
        this.chooseJobCategoryComponent.showPopup();
    },
    submitChooseJobCategory(e) {
        let jobCategoryList = e.detail.value;

        this.setData({
            hiddenJobCategory: jobCategoryList.findIndex(v => { return v.checked == true }) == -1 ? true : false,
            jobCategoryList: jobCategoryList,
        })
    },
    /**END 组件事件 - 选择期望职位*/
    /**BEGIN 组件事件 - 选择社区*/
    bindtapChooseCommunity(e) {
        this.chooseCommunityComponent.showPopup();
    },
    submitChooseCommunity(e) {
        let currentChoosedCommunityList = e.detail.value;
        console.log(e);
        this.setData({
            choosedCommunityList: currentChoosedCommunityList,
            choosedCommunityName: currentChoosedCommunityList.length == 0 ? '' : currentChoosedCommunityList[0].communityName,
            hiddenCommunityDefaultText: currentChoosedCommunityList.length == 0 ? false : true,
        })
    },
    /**END 组件事件 - 选择社区*/
    onUnload: function () {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        // 初始化加载开始
        try {
            Loading.begin();
            await app.getOpenidReady();
            let openid = wx.getStorageSync('openid');
            this.setData({
                candidateOpenid: openid,
            });
            await this.loadContent();
        } catch (e) {
            console.error(e)
        } finally {
            Loading.end();
        }

        var that = this
        wx.getSystemInfo({
            success: (res) => {
                console.log(res)
                let windowHeight = res.windowHeight;
                let wht = windowHeight * 0.8 - 86
                let oht = wht;
                that.setData({
                    oht: oht
                })
            },
        });
    },

    onShow: function () {
    },
    onReady: async function () {
        // 加载社区组件
        this.chooseCommunityComponent = this.selectComponent("#choose-community");
        this.chooseJobCategoryComponent = this.selectComponent("#choose-job-category");
        // 加载 求职者工作意向
        await this.chooseJobCategoryComponent.initCandidateJobCategoryList();
        // 初始化 调用选择职位保存事件 同步页面数据
        this.chooseJobCategoryComponent.bindtapChoosedJobCategorySubmit();
    },

    // 加载用户
    loadContent: async function () {
        let openid = wx.getStorageSync('openid');

        // 加载求职者用户信息
        let recruiteeInfo = await UserService.loadRcruiteeInfo();
        // 加载求职者 - 薪资期望  及 薪资可选列表
        let salaryIndex = CONSTANT.salaryList.findIndex((r, i) => {
            return (r.min == recruiteeInfo.expectSalaryMin && r.max == recruiteeInfo.expectSalaryMax)
        });

        this.setData({
            salaryStringList: CONSTANT.salaryList.map(e => e.value),
            salaryList: CONSTANT.salaryList,
            index: salaryIndex,
        });

        // 加载 求职者  已选择的期望社区列表
        let candidateForCommunityResult = await candidateForCommunityService.loadListByCandidateOpenid(openid);
        let expectCommunityList = candidateForCommunityResult.data;
        console.log(expectCommunityList);
        this.setData({
            choosedCommunityList: expectCommunityList.map(v => {
                return {
                    communityUuid: v.communityUuid,
                    communityName: v.communityName,
                };
            })
        })
        if (this.data.choosedCommunityList.length > 0) {
            this.setData({
                hiddenCommunityDefaultText: true,
            })
        }
    },


})