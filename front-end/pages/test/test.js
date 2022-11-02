// pages/test/test.js
// 加载 常量
const CONSTANT = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
let $ = require('../../utils/request_util');
// 加载 工具
const date_util = require('../../utils/date_util');
const img_util = require('../../utils/img_util');
const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');
const url_util = require('../../utils/url_util');
const { Completer } = require('../../utils/function_util');
const prompt_util = require('../../utils/prompt_util');
// 加载 服务
const userCommunityService = require("../../common/userCommunityService");
const functionTagService = require("../../common/functionTagService");
const userForFunctionTagService = require("../../common/userForFunctionTagService");

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isDataNew: true,
        functionTagList: [],
        authorizationLevel: '',
        communityUuid: '',
        userForFunctionTagId: '',
        openid: '',
        pagePathApply: '',
        list: [
            // {id: '1',name: '中央球机',iconimg:'/img/more.png',},
            { id: '', tagId: 'sygl', tagName: '诉源治理', pagePathLink: '/pages/syzl/syzl', backgroudColor: '#EEF7FE', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },
            { id: '', tagId: 'zphui', tagName: '招聘会', pagePathLink: '/pages/zphlist/zphlist', backgroudColor: '#EEF7FE', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },
            { id: '', tagId: 'tagCandidateRecruit', tagName: '求职就业', pagePathLink: '/pages/comzp/comzp', backgroudColor: '#FEFAEE', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },
            { id: '', tagId: 'tagCompanyRecruit', tagName: '企业招聘', pagePathLink: '/pages/qzjy/qzjy', backgroudColor: '#EEF7FE', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },

            { id: '', tagId: 'tagCellInput', tagName: '网格录入', pagePathLink: '/pages/wglr/wglr', backgroudColor: '#F3EEFC', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },
            { id: '', tagId: 'tagCommunityManagement', tagName: '社区管理', pagePathLink: '/pages/sqgl/sqgl', backgroudColor: '#EEFBFE', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },
            { id: '', tagId: 'tagRongEDai', tagName: '蓉e贷', pagePathLink: '', backgroudColor: '#F5FFE8', logoPathSmall: '/img/more.png', target: 'miniProgram', appId: 'wx96d6e30c9f677d52', iconimg: '/img/more.png', },
            { id: '', tagId: 'tagNewsAnnouncement', tagName: '新闻公告', pagePathLink: '/pages/newsms/newsms', backgroudColor: '#FFF6F6', logoPathSmall: '/img/more.png', iconimg: '/img/more.png', },

            { id: '', tagId: 'tagInformationCollect', tagName: '自建房信息采集', pagePathLink: '/pages/messgecollect/messgecollect', backgroudColor: '#EEF7FE', logoPathSmall: '/img/more.png', tp: '-13px', iconimg: '/img/more.png', },

        ],

        isSelect: { id: '2', name: '4号地', iconimg: '/img/more.png', },
        dialogShow: false,
        buttons: [{ text: '取消' }, { text: '确定' }],
        showTopTips: false,
        inputText: '',
        showkelong: false,
        kelong: { top: 0, name: '', id: '2', name: '4号地', iconimg: '/img/more.png', },
        replace: {
            name: '',
        },
    },
    /**
     * 生命周期函数--监听页面加载
     */
    bindtapGoLink: function (e) {
        console.log(this.data);

        let id = e.currentTarget.dataset.id;
        let linkUrl = this.data.functionTagList.find(v => {
            return id == v.tagId;
        }).pagePathLink;
        console.info(`Usre Choose the functionTag is :${id}`);
        switch (id) {
            // 企业招聘  求职就业
            case 'tagRecruitCompany':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagRecruitCandidate':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagNewsAnnouncement':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagHouseInformationCollect':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            case 'tagVoteActivity':
                linkUrl += ('?communityUuid=' + this.data.communityUuid);
                break;
            // 网格录入
            case 'tagGridManage':
                wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY, 'grid');
                if (this.data.authorizationLevel == CONSTANT.AUTHORIZATION_LEVEL.COMMUNITY_ADMIN
                    || this.data.authorizationLevel == CONSTANT.AUTHORIZATION_LEVEL.COMMUNITY_RECORDER) {
                    linkUrl += ('?communityUuid=' + this.data.communityUuid);
                } else {
                    wx.showModal({
                        title: '您没有网格录入的权限',
                        icon: 'error',
                    })
                    return;
                }
                break;
            // 社区管理
            case 'tagCommunityManage':
                wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY, 'community');
                if (this.data.authorizationLevel == CONSTANT.AUTHORIZATION_LEVEL.COMMUNITY_ADMIN) {
                    linkUrl += ('?communityUuid=' + this.data.communityUuid);
                } else {
                    wx.showModal({
                        title: '您没有社区管理的权限',
                        icon: 'error',
                    })
                    return;
                }
                break;
            default:
                break;
        }
        console.info(`Navigator linkUrl is ${linkUrl}`);
        wx.navigateTo({
            url: linkUrl,
        })
    },
    // 提交 保存
    bindtapSubmit: async function () {
        let currentFunctionTagList = this.data.functionTagList;
        if (this.data.isDataNew) {
            // 插入
            let insertData = {
                userOpenid: this.data.openid,
                pagePathApply: url_util.appendHeadSlash(this.data.pagePathApply),
                functionTagId: currentFunctionTagList.map(v => {
                    return v.id;
                }).join(";"),
            }
            try {
                Loading.begin();
                await userForFunctionTagService.insertByEntity(insertData);
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end();
            }

        } else {
            // 更新
            let updateData = {
                id: this.data.userForFunctionTagId,
                functionTagId: currentFunctionTagList.map(v => {
                    return v.id;
                }).join(";"),
            }
            try {
                Loading.begin();
                await userForFunctionTagService.updateByEntity(updateData);
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end();
            }
        }
        app.globalMonitors.functionTagChangeMonitor.notifyAll();
        wx.navigateBack({
            delta: 0,
        })
    },
    onLoad: async function (options) {
        await app.getOpenidReady();
        let openid = app.getOpenid();
        this.setData({ openid: openid })
        let communityUuid = options.communityUuid;
        console.info(`Now communityUuid is ${communityUuid}`);
        this.setData({ communityUuid: communityUuid });

        try {
            Loading.begin();
            let pages = getCurrentPages();
            let pagePathApply = pages[pages.length - 2].route;
            // 测试
            // pagePathApply = 'pages/sqfw/sqfw';
            this.setData({ pagePathApply: pagePathApply });
            // 加载  读取所有功能列表
            let functionTagData = await functionTagService.listByDefaultOrder(this.data.pagePathApply, this.data.communityUuid);
            functionTagData = functionTagData.data;
            let originFunctionTagList = functionTagData.map((v) => {
                v.iconimg = '/img/more.png';
                return v;
            });

            // 加载用户选择
            let userForFunctionTagData = await userForFunctionTagService.loadEntityByUserAndPath(this.data.openid, this.data.pagePathApply);
            userForFunctionTagData = userForFunctionTagData.data;
            console.log(userForFunctionTagData);
            this.setData({
                isDataNew: (!userForFunctionTagData) ? true : false,
                userForFunctionTagId: (!userForFunctionTagData) ? '' : userForFunctionTagData.id,
            })
            if (!this.data.isDataNew) {
                let idList = string_util.splitBySemiColon(userForFunctionTagData.functionTagId);
                console.log(idList);
                this.setData({
                    functionTagList: idList.map(v => {
                        return originFunctionTagList.find(r => {
                            return v == r.id;
                        })
                    })
                })
            } else {
                this.setData({
                    functionTagList: originFunctionTagList,
                })
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end()
        }

    },

    dragStart: function (e) {
        var that = this
        var kelong = that.data.kelong
        var i = e.currentTarget.dataset.index
        kelong.tagName = this.data.functionTagList[i].tagName
        var query = wx.createSelectorQuery();
        //选择id
        query.select('.list').boundingClientRect(function (rect) {
            kelong.top = e.changedTouches[0].clientY - rect.top
            console.log("dragStart", kelong.top)
            that.setData({
                kelong: kelong,
                showkelong: true
            })
        }).exec();
    },
    dragMove: function (e) {
        var that = this
        var query = wx.createSelectorQuery();
        var kelong = that.data.kelong
        query.select('.list').boundingClientRect(function (rect) {
            kelong.top = e.changedTouches[0].clientY - rect.top
            if (kelong.top < 60) {
                kelong.top = 60
            } else if (kelong.top > rect.height - 40) {
                kelong.top = rect.height - 40
            }
            that.setData({
                kelong: kelong,
            })
        }).exec();
    },
    dragEnd: function (e) {
        var that = this
        var i = e.currentTarget.dataset.index
        var query = wx.createSelectorQuery();
        var kelong = that.data.kelong
        // var listnum = that.data.list.length
        var functionTagList = that.data.functionTagList
        query.select('.list').boundingClientRect(function (rect) {
            kelong.top = e.changedTouches[0].clientY - rect.top
            var target = parseInt(kelong.top / 48) - 1
            var replace = that.data.replace
            // console.log("dragEnd",target,i)
            if (target >= 0) {
                replace = functionTagList.splice(i, 1);
                functionTagList.splice(target, 0, replace[0]);
                // console.log("dragEnd",target,i,replace,list)
            }
            that.setData({
                functionTagList: functionTagList,
                showkelong: false
            })
        }).exec();
        console.log(functionTagList)
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