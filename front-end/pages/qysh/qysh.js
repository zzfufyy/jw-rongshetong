// pages/qysh/qysh.js
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        section: [],
        currentId: '0',
        currentTab: '0',
        yhid: "",
        communityUuid: "",

        joblistyfb: [
            { comname: '长沙竟网信息科技有限公司', sssq: '东湖社区', comlocal: '长沙市岳麓区奥克斯中央公馆', tidm: '1161050056377320XW', cellp: '12345678901', xjid: '0' },
            { comname: '长沙竟网信息科技有限公司', sssq: '东湖社区', comlocal: '长沙市岳麓区奥克斯中央公馆', tidm: '1161050056377320XW', cellp: '12345678901', xjid: '1' }
        ],
        joblistyxj: [
            { comname: '长沙竟网信息科技有限公司', sssq: '东湖社区', comlocal: '长沙市岳麓区奥克斯中央公馆', tidm: '1161050056377320XW', cellp: '12345678901', sjid: '2', istg: true },
            { comname: '长沙竟网信息科技有限公司', sssq: '东湖社区', comlocal: '长沙市岳麓区奥克斯中央公馆', tidm: '1161050056377320XW', cellp: '12345678901', sjid: '3', istg: false },
        ],
        pagess: 1
    },
    //点击头部导航的点击事件
    handleTap: function (e) {
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
    //审核通过
    editjob(e) {
        console.log(e)
        let that = this;
        let id = e.currentTarget.dataset.id
        wx.showModal({
            title: '提示',
            content: '确认审核通过',
            success(res) {
                if (res.confirm) {
                    that.shtg(id);

                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    },
    //审核不通过
    xiajia(e) {
        console.log(e)
        let that = this;
        let id = e.currentTarget.dataset.id
        wx.showModal({
            title: '提示',
            content: '确认审核不通过?',
            success(res) {
                if (res.confirm) {
                    console.log('用户点击确定')
                    that.shwtg(id);
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    //审核通过
    shtg(id) {
        let that = this;
        wx.request({
            url: app.globalData.web_path + '/recruit-company/shtg',
            data: {
                id: id
            },
            header: app.globalData.header,
            success: function (res) {
                that.searchcomwsh(that)
                that.searchcomysh(that)
                // that.openAlert(scene);
            },
            fail: function (res) {
            }
        })
    },
    //审核未通过
    shwtg(id) {
        let that = this;
        wx.request({
            url: app.globalData.web_path + '/recruit-company/shbtg',
            data: {
                id: id
            },
            header: app.globalData.header,
            success: function (res) {
                that.searchcomwsh(that)
                that.searchcomysh(that)
                // that.openAlert(scene);
            },
            fail: function (res) {
            }
        })
    },
    //查询待审核和已审核的公司
    searchcomwsh(that) {
        //未审核
        wx.request({
            url: app.globalData.web_path + '/recruit-company/searchbyflag',
            data: {
                pagess: that.data.pagess,
                communityUuid: that.data.communityUuid
            },
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
                let dshlist = res.data.obj;
                let complist = [];
                for (let i = 0; i < dshlist.length; i++) {
                    let comp = { comname: dshlist[i].companyName, sssq: dshlist[i].communityUuid, comlocal: dshlist[i].address, tidm: dshlist[i].licenseId, cellp: dshlist[i].juridicalPhone, id: dshlist[i].id }
                    complist.push(comp)
                }
                that.setData({
                    joblistyfb: complist
                })
                // that.openAlert(scene);
            },
            fail: function (res) {
            }
        })

    },
    searchcomysh(that) {
        //已审核
        console.log(that.data.communityUuid)
        wx.request({
            url: app.globalData.web_path + '/recruit-company/searchtgrz',
            data: {
                pagess: that.data.pagess,
                communityUuid: that.data.communityUuid
            },
            header: app.globalData.header,
            success: function (res) {
                console.log(res)
                let dshlist = res.data.obj;
                let complist = [];
                let istg = true;
                for (let i = 0; i < dshlist.length; i++) {
                    if (dshlist[i].flagIdentification == 1) {
                        istg = true
                    } else {
                        istg = false
                    }
                    let comp = { istg: istg, comname: dshlist[i].companyName, sssq: dshlist[i].communityUuid, comlocal: dshlist[i].address, tidm: dshlist[i].licenseId, cellp: dshlist[i].juridicalPhone, id: dshlist[i].id }
                    complist.push(comp)
                }
                that.setData({
                    joblistyxj: complist
                })
                // that.openAlert(scene);
            },
            fail: function (res) {
            }
        })
    },
    onLoad: function (options) {
        console.log(options)
        let that = this
        that.setData({
            communityUuid: options.communityUuid
        });
        that.searchcomwsh(that)
        that.searchcomysh(that)
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    winWidth: res.windowWidth,
                    winHeight: res.windowHeight,
                });
            }
        })

        let fblg = that.data.joblistyfb.length
        let xjlg = that.data.joblistyxj.length
        let section1 = [
            { name: '未审核', typeId: '0', num: fblg },
            { name: '已审核', typeId: '1', num: xjlg },
        ]
        that.setData({
            section: section1,
            communityUuid: options.communityUuid
        });
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
    onReachBottom: function (e) {
        console.log(e)
        let pagesss = that.data.pagess;
        pagesss += 1
        that.setData({
            pagess: pagesss
        })
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})