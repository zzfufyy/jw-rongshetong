// pages/comzp/comzp.js
const Loading = require('../../utils/loading_util');
const CONSTANT = require('../../common/constant');
const { Salary } = require('../../common/constant');
const GLOBAL_CONSTANT = require('../../common/globalConstant');
const { Completer } = require('../../utils/function_util');
const { UserService } = require('../../service/user_service');
const { CompanyService } = require('../../service/company_service');
// 加载服务
const viewRecordService = require('../../common/viewRecordService');
const recruitCompanyService = require('../../common/recruitCompanyService');
const userCandidateService = require('../../common/userCandidateService');
const userStateService = require('../../common/userStateService');
const iUserRoleService = require('../../common/iUserRoleService');
// // 附加模块
const comzpEx = require('./comzpEx');


const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        vedio_data: [
            // { bac: 'linear-gradient(180deg, #7CCCFB 0%, #1BA2F6 100%)', xdname: '春风行动线上专区', url: '', id: 0, img: '/img/cfxd.png' },
            // { bac: 'linear-gradient(180deg, #FFDC69 0%, #FFAC30 100%)', xdname: '高校毕业生专场', url: '', id: 1, img: '/img/gxzc.png' },
            // { bac: 'linear-gradient(180deg, #FFDC69 0%, #FFAC30 100%)', xdname: '高校毕业生专场', url: '', id: 1, img: '/img/gxzc.png' }
        ],
        currentId: '0',
        currentTab: '0',
        searchText: '',
        jobSalaryMin: -1,
        jobSalaryMax: -1,
        // 排序方式
        orderTypeList: [
            { tagName: '最新', checked: true, type: CONSTANT.ORDER_TYPE.CREATE_TIME },
            { tagName: '附近', checked: false, type: CONSTANT.ORDER_TYPE.DISTANCE },
        ],
        salaryList: CONSTANT.salaryList,
        scopeList: [{ name: '全部社区', checked: true, }, { name: '当前社区', checked: false, }],
        hideSearchBar: true,

        // 距离
        distanceList: [
            { tagName: '不限', id: 0, checked: true }, { tagName: '距离最近', id: 1 }
        ],
        oid: 0,
        userIdentity: '',
        canhgeid: 0,
        ident: "",
        candidateList: [
            // {
            //     jobName: '服务员/保洁',
            //     userTag: [{ tagbq: '女' }, { tagbq: '29岁' }, { tagbq: '3000-5000/月' }],
            //     realName: '张三',
            //     portraitPath: '/img/tx.png',
            //     // hxtime: '10分钟前',
            //     communityName: '天源社区',
            //     distance: '1.2',
            //     phone: '12345678901'
            // },
        ],
    },
    lock: {},
    stateCompleter: {
        userIdentityCompleter: new Completer(),
        communityUuidCompleter: new Completer(),
    },
    //定位
    async changedw(e) {
        let that = this;
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
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
            if (that.data.ident == 1) {
                let updateData = {
                    id: app.getOpenid(),
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
            } else if (that.data.ident == 2) {
                let updateData = {
                    id: app.getOpenid(),
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
            // app.globalMonitors.userLocationChangeMonitor.notifyAll();
        }).catch(e => {
            console.info('用户取消  修改定位')
        })


    },

    //在招职业最新附近筛选事件
    async bindtapChangeOrderType(e) {
        let currentType = e.currentTarget.dataset.type;
        let currentOrderTypeList = this.data.orderTypeList;
        let currentOrderType = currentOrderTypeList.find(v => { return v.type == currentType });
        if (currentOrderType.checked) {
            return;
        } else {
            currentOrderTypeList = currentOrderTypeList.map(v => {
                v.checked = !v.checked;
                return v;
            })
        }
        this.setData({
            orderTypeList: currentOrderTypeList,
        })
        // 重新加载
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }

    },
    // 搜索事件
    bindinputSearchCategory(e) {
        this.setData({
            searchText: e.detail.value,
        });
    },
    // 搜索栏回车
    async bindconfirmSearchCategory(e) {
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }
    },
    // 筛选事件
    bindtapShowSearchBar() {
        this.setData({
            hideSearchBar: !this.data.hideSearchBar,
        })
    },
    //打电话
    catchtapCallPhone: async function (e) {
        let switchRole = await iUserRoleService.trySwitchToRecruiter();
        console.log(wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY));
        if (!switchRole) {
            return;
        }
        let currentPhone = e.currentTarget.dataset.phone;
        wx.makePhoneCall({
            phoneNumber: currentPhone,
        })
    },

    //重置事件
    async resetSearchContent() {
        this.setData({
            searchText: '',
            jobSalaryMin: -1,
            jobSalaryMax: -1,
            salaryList: this.data.salaryList.map(v => {
                v.checked = false;
                return v;
            })
        });
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }
    },

    // 选择薪资条件
    catchtapChooseSalary(e) {
        let currentIndex = e.currentTarget.dataset.index;
        let salaryList = this.data.salaryList;
        this.setData({
            salaryList: salaryList.map((v, i) => {
                v.checked = i == currentIndex ? true : false;
                return v;
            }),
        })
    },
    catchtapChooseScope(e) {
        let currentIndex = e.currentTarget.dataset.index;
        let scopeList = this.data.scopeList;
        this.setData({
            scopeList: scopeList.map((v, i) => {
                v.checked = i == currentIndex ? true : false;
                return v;
            }),
        })
    },

    //清空
    catchtapSearchBarClear() {
        this.setData({
            jobSalaryMin: -1,
            jobSalaryMax: -1,
            salaryList: this.data.salaryList.map(v => {
                v.checked = false;
                return v;
            }),
            scopeList: this.data.scopeList.map((v, i) => {
                v.checked = i == 0 ? true : false;
                return v;
            }),
        });
    },
    //确定
    async catchtapSearchBarConfirm() {
        let salaryList = this.data.salaryList;
        let currentIndex = salaryList.findIndex(v => {
            return v.checked;
        })
        if (currentIndex != -1) {
            let currentSalary = salaryList[currentIndex];
            this.setData({
                jobSalaryMin: currentSalary.min,
                jobSalaryMax: currentSalary.max,
            })
        } else {
            this.setData({
                jobSalaryMin: -1,
                jobSalaryMax: -1,
            })
        }
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }
        this.setData({ hideSearchBar: true })
    },
    // 点击跳转到对应求职者
    async bindtapChooseCandidate(e) {
        console.log(e.currentTarget.dataset.index);
        let currentIndex = e.currentTarget.dataset.index;
        let currentCandidateOpenid = this.data.candidateList[currentIndex].candidateOpenid;
        wx.navigateTo({
            url: '/pages/loojl/lookjl?candidateOpenid=' + currentCandidateOpenid,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        // 初始化 loading锁
        let openid = wx.getStorageSync('openid');
        this.setData({
            ident: wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY),
        });
        this.lock = new Loading.Lock();

        // 搜索栏 月薪要求  添加薪资不限
        let salaryList = this.data.salaryList;
        salaryList.unshift(new Salary(-1, -1))
        this.setData({
            salaryList: salaryList.map(v => {
                v.checked = false;
                return v;
            }),
        })
        // 完成器初始化
        this.stateCompleter.userIdentityCompleter = new Completer();
        this.stateCompleter.communityUuidCompleter = new Completer();
        // 获取授权级别  用户角色

        let userIdentity = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_USER_IDENTITY);
        let communityUuid = options.communityUuid;
        // 测试
        this.setData({
            userIdentity: userIdentity,
            communityUuid: communityUuid,
        })
        this.stateCompleter.userIdentityCompleter.resolve(userIdentity);
        this.stateCompleter.communityUuidCompleter.resolve(communityUuid);

        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.clearContent();
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
        }
        let that = this
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight - 133,
                });
            }
        })
    },


    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: async function () {
        try {
            if (!this.lock.isLock) {
                Loading.begin(this.lock);
                await this.loadContent();
            }
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end(this.lock);
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
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    ...comzpEx.createPageMethods(),
})