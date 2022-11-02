// pages/comjbxx/comjbxx.js
const $ = require('../../utils/request_util');

const CONSTANT = require('../../common/constant');

const string_util = require('../../utils/string_util');
const img_util = require('../../utils/img_util');

const userRecruiterService = require('../../common/userRecruiterService');
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
        communityUuidList: [],
        communityUuid: '',
        choosedCommunityList: [],
        qyname: '',
        tyshxydm: '',
    },


     /** 页面绑定事件 begin */
    binderrorCompanyPortrait(e) {
        console.error(e);
        this.setData({
            imgsrc: CONSTANT.STATIC_IMG_URL.portrait_company,
        })
    },
    // 社区选择
    bindPickerChange1(e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            index1: e.detail.value,
            communityUuid: this.data.communityUuidList[e.detail.value],
        })
        console.log(this.data)
    },
    // 更改定位
    changedw(e) {
        let that = this
        wx.chooseLocation({
            type: 'gcj02',
            success(res) {
                // console.log(PAGENAME + '公司地址选择:'); console.log(res);
                that.setData({
                    positionData: res.address + ' ' + res.name,
                    // companyAddress: res.address + ' ' + res.name,
                    longitude: res.longitude,
                    latitude: res.latitude,
                })
            }
        })
    },
    // 统一社会信用代码
    bindinputLicenseId(e) {
        this.setData({
            tyshxydm: e.detail.value
        })
    },
    // 企业名称
    bindinputCompanyName(e) {
        this.setData({
            qyname: e.detail.value
        })
    },
    //联系电话
    bindinputCellphone(e) {
        this.setData({
            cellphone: e.detail.value
        })
    },
    //点击企业标志
    bindtapUploadCompanyPortrait() {
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
    // 保存
     bindtapSave: async function() {
        let that = this;
        // 如果图片路径不包含 web_path 或者为空 则不执行上传
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
                .then((r) => {
                    console.log(r)
                })
                .catch((r) => console.error(r));
        }
        let submitData = {
            id: this.data.companyUuid,
            companyName: this.data.qyname,
            licenseId: this.data.tyshxydm,
            communityUuid: this.data.communityUuid,
            // juridicalPerson: this.data.name,
            juridicalPhone: this.data.cellphone
        }
        console.log(submitData);
        let submitPromise = recruitCompanyService.updateRecruitCompany(submitData);
        await submitPromise.then((r) => {
            console.log(r);
        }).catch(r => console.error(r));
        wx.navigateBack({
            delta: 1,
        })
    },
    bindinputCellphone(e) {
        this.setData({
            cellphone: e.detail.value
        })
    },
    /** 页面绑定事件 end */

    /** BEGIN 组件事件 - 选择社区*/
    bindtapChooseCommunity(e) {
        this.chooseCommunityComponent.showPopup();
    },
    submitChooseCommunity(e) {
        let currentChoosedCommunityList = e.detail.value;
        this.setData({
            choosedCommunityList: currentChoosedCommunityList,
            choosedCommunityName: currentChoosedCommunityList.length == 0 ? '' : currentChoosedCommunityList[0].communityName,
        })
    },
    /** END 组件事件 - 选择社区*/
    
    /** 内部方法 begin*/
    _loadPageContent: async function () {
        // 加载用户信息
        let userRecruiterData = (await userRecruiterService.loadEntityById(this.data.openid)).data;
        this.setData({
            companyUuid: userRecruiterData.companyUuid
        });
        let companyData = (await recruitCompanyService.loadEntityById(this.data.companyUuid)).data;
        let portraitPath =
            string_util.isEmpty(companyData.portraitPath) ? '' : app.globalData.web_path + companyData.portraitPath;
        this.setData({
            imgsrc: img_util.handleCompanyPortraitPath(portraitPath),
            name: companyData.juridicalPerson,
            cellphone: companyData.juridicalPhone,
            qyname: companyData.companyName,
            tyshxydm: companyData.licenseId,
        });
        let communityData = (await communityInformationService.loadEntityById(companyData.communityUuid)).data;
        this.setData({
            choosedCommunityList: [{
                communityUuid: communityData.id,
                communityName: communityData.communityName,
            }]
        })
    },
    /** 内部方法 end */
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        await app.getAppInitReady();
        this.setData({
            openid: app.getOpenid(),
        })
        await this._loadPageContent();
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
        this.chooseCommunityComponent = this.selectComponent("#choose-community");
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