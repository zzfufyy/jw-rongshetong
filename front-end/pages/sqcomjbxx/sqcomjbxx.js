// pages/sqcomjbxx/sqcomjbxx.js
const $ = require('../../utils/request_util');
const string_util = require('../../utils/string_util');
const util = require('../../utils/util.js');
const { Completer } = require('../../utils/function_util');

const recruitCompanyService = require('../../common/recruitCompanyService');
const communityInformationService = require('../../common/communityInformationService');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        companyUuid: '',
        imgsrc: '',
        name: '',
        cellphone: '',
        array1: ['社区1', '街道1', '街道2'],
        index1: 0,
        positionData: '',
        // communityUuidList: [],
        communityUuid: '',
        communityName: '',
        qyname: '',
        tyshxydm: '',
        gslxr: '',
    },
    state: {
        communityUuidCompleter: new Completer(),
    },
    // // 社区选择
    // bindPickerChange1(e) {
    //     console.log('picker发送选择改变，携带值为', e.detail.value)
    //     this.setData({
    //         index1: e.detail.value,
    //         communityUuid: this.data.communityUuidList[e.detail.value],
    //     })
    //     console.log(this.data)
    // },
    // 统一社会信用代码
    bindinputLicenseId(e) {
        this.setData({
            tyshxydm: e.detail.value
        })
    },
    lxrname(e) {
        this.setData({
            gslxr: e.detail.value
        })
    },
    // 企业名称
    bindinputCompanyName(e) {
        this.setData({
            qyname: e.detail.value
        })
    },
    bindinputIntroduction(e) {
        this.setData({
            introduction: e.detail.value,
        })
    },
    //联系电话
    lxdhip(e) {
        this.setData({
            cellphone: e.detail.value
        })
    },
    //点击企业标志
    xgtx() {
        let imgsrc = this.data.imgsrc
        let that = this
        wx.chooseMedia({
            count: 1,
            mediaType: ['image'],
            sourceType: ['album', 'camera'],
            camera: ['back', 'front'],
            success(res) {
                console.log(res.tempFiles[0].tempFilePath);
                that.setData({
                    imgsrc: res.tempFiles[0].tempFilePath,
                    none: 0
                })
            }
        })
    },
    //地址
    bindinputCompanylocal(e) {
        this.setData({
            qylocal: e.detail.value
        })
    },
    async bc() {
        let that = this;
        let uuid = util.wxuuid()
        // 如果图片路径不包含 web_path 或者为空 则不执行上传
        console.log(uuid)
        let submitData = {
            companyName: this.data.qyname,
            licenseId: this.data.tyshxydm,
            communityUuid: this.data.communityUuid,
            address: this.data.qylocal,
            juridicalPerson: this.data.gslxr,
            phone: this.data.cellphone,
            juridicalPhone: this.data.cellphone,
            introduction: this.data.introduction,
            flagIdentification: 1,
            ext2: "sqll"
        }
        console.log(submitData);
        let submitPromise = recruitCompanyService.insertByEntity(uuid, submitData);
        await submitPromise.then((r) => {
            console.log(r);
            that.setData({
                companyUuid: r.data
            })
        }).catch(r => console.error(r));
        let flagUpload = true;
        if (string_util.isEmpty(that.data.imgsrc) || that.data.imgsrc.includes(app.globalData.web_path)) {
            flagUpload = false;
        }
        if (flagUpload) {
            let uploadPromise = $.upload({
                url: '/recruit-company/uploadPortrait?id=' + that.data.companyUuid,
                filePath: that.data.imgsrc,
                formData: {},
                name: 'file',
                header: app.globalData.header,
            });
            await uploadPromise
                .then((r) => { console.log(r) })
                .catch((r) => console.error(r));
        }
        wx.showToast({
            title: '添加成功',
            icon: 'success',
            duration: 2000
        })
        wx.navigateTo({
            // url: '/pages/qylr/qylr',
            url: '/pages/fbjobwork/fbjobwork?companyUuid=' + that.data.companyUuid + '&communityUuid=' + that.data.communityUuid
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        this.state.communityUuidCompleter = new Completer();
        let communityUuid = options.communityUuid;
        this.state.communityUuidCompleter.resolve(communityUuid)
        let currentCommunityUuid = await this.state.communityUuidCompleter.promise;

        let communityData = await communityInformationService.loadEntityById(currentCommunityUuid)
        communityData = communityData.data;
        this.setData({
            communityUuid: communityData.id,
            communityName: communityData.communityName,
        })

        // 加载
        // let loadCommunityListPromise = communityInformationService.loadList();
        // await loadCommunityListPromise.then(r => {
        //     let communityNameList = []; let communityUuidList = [];
        //     r.data.forEach((item) => {
        //         communityNameList.push(item.communityName);
        //         communityUuidList.push(item.id);
        //     });
        //     that.setData({
        //         array1: communityNameList,
        //         communityUuidList: communityUuidList,
        //         communityUuid: options.communityUuid,

        //     })

        // }).catch(r => {
        //     console.error(r);
        // })
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