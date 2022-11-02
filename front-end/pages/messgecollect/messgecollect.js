// pages/messgecollect/messgecollect.js
const constList = require('../../common/constMessgecollect');
const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');
const GLOBAL_CONSTANT = require('../../common/globalConstant');

const informationCollectSelfBuildingService = require('../../common/informationCollectSelfBuildingService')
const communityInformationService = require('../../common/communityInformationService');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isInputDisabled: false,

        id: '',
        topPreffixText: '茶陵县',
        topDefaultSuffixText: '城乡居民建房安全隐患排查整治信息采集表',



        villiage: '',
        community: '',
        villiageGroup: '',
        road: '',
        houseNo: '',
        houseArea: constList.houseArea,
        housePropertyRight: constList.housePropertyRight,
        householderName: '',
        householderIdcard: '',
        houseLivingNumber: '',
        houseUsage: constList.houseUsage,
        isBusiness: constList.isBusiness,
        businessScope: constList.businessScope,
        isAuthenticateReport: constList.isAuthenticateReport,
        houseLandProperty: constList.houseLandProperty,
        countryLandUsage: constList.countryLandUsage,
        collectiveLandUsage: constList.collectiveLandUsage,
        houseMainLayer: '',
        housePartLayer: '',
        houseAcerage: '',
        houseHeight: '',
        houseYear: '',
    },

    bindinputVilliage(e) {
        this.setData({ villiage: e.detail.value })
    },
    bindinputCommunity(e) {
        this.setData({ community: e.detail.value })
    },
    bindinputVilliageGroup(e) {
        this.setData({ villiageGroup: e.detail.value })
    },
    bindinputRoad(e) {
        this.setData({ road: e.detail.value })
    },
    bindinputHouseNo(e) {
        this.setData({ houseNo: e.detail.value })
    },
    // 多选
    bindchangeHouseArea(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            houseArea: this.data.houseArea.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    // 单选
    bindchangeHousePropertyRight(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            housePropertyRight: this.data.housePropertyRight.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindinputHouseholderName(e) {
        this.setData({ householderName: e.detail.value })
    },
    bindinputHouseholderIdcard(e) {
        this.setData({ householderIdcard: e.detail.value })
    },
    bindinputHouseLivingNumber(e) {
        this.setData({ houseLivingNumber: e.detail.value })
    },
    bindchangeHouseUsage(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            houseUsage: this.data.houseUsage.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeIsBusiness(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isBusiness: this.data.isBusiness.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeBusinessScope(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            businessScope: this.data.businessScope.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeIsAuthenticateReport(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isAuthenticateReport: this.data.isAuthenticateReport.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeHouseLandProperty(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            houseLandProperty: this.data.houseLandProperty.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeCountryLandUsage(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            countryLandUsage: this.data.countryLandUsage.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeCollectiveLandUsage(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            collectiveLandUsage: this.data.collectiveLandUsage.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },

    bindinputhouseMainLayer(e) {
        this.setData({ houseMainLayer: e.detail.value })
    },
    bindinputhousePartLayer(e) {
        this.setData({ housePartLayer: e.detail.value })
    },
    bindinputhouseHeight(e) {
        this.setData({ houseHeight: e.detail.value })
    },
    bindinputhouseAcerage(e) {
        this.setData({ houseAcerage: e.detail.value })
    },
    bindinputhouseYear(e) {
        this.setData({ houseYear: e.detail.value })
    },
    async next() {
        let submitData = {
            villiage: this.data.villiage,
            community: this.data.community,
            villiageGroup: this.data.villiageGroup,
            road: this.data.road,
            houseNo: this.data.houseNo,
            houseArea: constList.turnListToString(this.data.houseArea),
            housePropertyRight: constList.turnListToString(this.data.housePropertyRight),
            householderName: this.data.householderName,
            householderIdcard: this.data.householderIdcard,
            houseLivingNumber: this.data.houseLivingNumber,
            houseUsage: constList.turnListToString(this.data.houseUsage),
            isBusiness: constList.turnListToString(this.data.isBusiness),
            businessScope: constList.turnListToString(this.data.businessScope),
            isAuthenticateReport: constList.turnListToString(this.data.isAuthenticateReport),
            houseLandProperty: constList.turnListToString(this.data.houseLandProperty),
            countryLandUsage: constList.turnListToString(this.data.countryLandUsage),
            collectiveLandUsage: constList.turnListToString(this.data.collectiveLandUsage),
            houseMainLayer: this.data.houseMainLayer,
            housePartLayer: this.data.housePartLayer,
            houseAcerage: this.data.houseAcerage,
            houseHeight: this.data.houseHeight,
            houseYear: this.data.houseYear,
        }

        let submitRs = '';

        if (string_util.isEmpty(this.data.id)) {
            try {
                Loading.begin();
                submitRs = await informationCollectSelfBuildingService.insertByEntity(submitData);
                this.setData({
                    id: submitRs.data
                })
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end()
            }

        } else {
            let updateData = {
                id: this.data.id,
                ...submitData,
            }
            submitRs = await informationCollectSelfBuildingService.updateByEntity(updateData);
        }
        console.log(submitRs);



        wx.navigateTo({
            url: "/pages/messgecollect1/messgecollect1?id=" + this.data.id,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: async function (options) {
        let communityUuid = options.communityUuid;
        console.log(communityUuid);
        if (string_util.isEmpty(communityUuid)) {
            this.setData({ isInputDisabled: false })
        } else {
            // 加载社区信息
            this.setData({ isInputDisabled: true })
            let communityData = await communityInformationService.loadEntityById(communityUuid);
            communityData = communityData.data;
            this.setData({ topPreffixText: communityData.districtName });
            
            console.log(communityData);
            let streetName = communityData.streetName;
            if (/(乡|镇)$/.test(streetName)) {
                streetName = streetName.substr(0, streetName.length - 1);
            } else if (/(街道)$/.test(streetName)) {
                streetName = streetName.substr(0, streetName.length - 2);
            }

            let communityName = communityData.communityName;
            if (/(村)$/.test(communityName)) {
                communityName = communityName.substr(0, communityName.length - 1);
            } else if (/(社区)$/.test(communityName)) {
                communityName = communityName.substr(0, communityName.length - 2);
            }

            this.setData({
                villiage: streetName,
                community: communityName,
            })
        }
        console.log(this.data);
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
        let flag = wx.getStorageSync(GLOBAL_CONSTANT.GLOBAL_INFROMATION_COLLECT_SUBMIT);
        if (flag) {
            this.setData({
                id: '',
                // villiage: '',
                // community: '',
                villiageGroup: '',
                road: '',
                houseNo: '',
                houseArea: constList.houseArea,
                housePropertyRight: constList.housePropertyRight,
                householderName: '',
                householderIdcard: '',
                houseLivingNumber: '',
                houseUsage: constList.houseUsage,
                isBusiness: constList.isBusiness,
                businessScope: constList.businessScope,
                isAuthenticateReport: constList.isAuthenticateReport,
                houseLandProperty: constList.houseLandProperty,
                countryLandUsage: constList.countryLandUsage,
                collectiveLandUsage: constList.collectiveLandUsage,
                houseMainLayer: '',
                housePartLayer: '',
                houseAcerage: '',
                houseHeight: '',
                houseYear: '',
            })
            wx.pageScrollTo({
                scrollTop: 0,
                duration: 800
            })
            wx.setStorageSync(GLOBAL_CONSTANT.GLOBAL_INFROMATION_COLLECT_SUBMIT, false);
        }

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
    onPageScroll: function (e) {

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