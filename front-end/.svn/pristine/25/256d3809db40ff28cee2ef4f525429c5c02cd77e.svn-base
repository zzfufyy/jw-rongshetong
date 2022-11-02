// pages/messgecollect1/messgecollect1.js
const constList = require('../../common/constMessgecollect');
const Loading = require('../../utils/loading_util');

const informationCollectSelfBuildingService = require('../../common/informationCollectSelfBuildingService')

Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '',
	prew(){
		wx.navigateBack({
			delta: 1,
			success: (res) => {},
			fail: (res) => {},
			complete: (res) => {},
		})
	},
	next(){
		wx.navigateTo({
			url: '/pages/messgecollect2/messgecollect2',
		})
	},
        designMode: constList.designMode,
        constructionTeam: constList.constructionTeam,
        structureType: constList.structureType,
        wallMaterial: constList.wallMaterial,
        floorType: constList.floorType,
        roofType: constList.roofType,
        aseismaticStructure: constList.aseismaticStructure,
        isExpansion: constList.isExpansion,
        expansionNumber: '',
        expansionYear: '',
        expansionContent: constList.expansionContent,
        isChangeStructure: constList.isChangeStructure,
    },
    bindchangedesignMode(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            designMode: this.data.designMode.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeconstructionTeam(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            constructionTeam: this.data.constructionTeam.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangestructureType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            structureType: this.data.structureType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangewallMaterial(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            wallMaterial: this.data.wallMaterial.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangefloorType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            floorType: this.data.floorType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeroofType(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            roofType: this.data.roofType.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeaseismaticStructure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            aseismaticStructure: this.data.aseismaticStructure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeisExpansion(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isExpansion: this.data.isExpansion.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindinputexpansionNumber(e) {
        this.setData({ expansionNumber: e.detail.value })
    },
    bindinputexpansionYear(e) {
        this.setData({ expansionYear: e.detail.value })
    },
    bindchangeexpansionContent(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            expansionContent: this.data.expansionContent.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },
    bindchangeisChangeStructure(e) {
        let checkedIndex = e.detail.value;
        this.setData({
            isChangeStructure: this.data.isChangeStructure.map((v, i) => {
                if (checkedIndex.indexOf(i + "") != -1) {
                    v.checked = true;
                } else {
                    v.checked = false;
                }
                return v;
            })
        })
    },

    async next() {
        let updateData = {
            id: this.data.id,
            designMode: constList.turnListToString(this.data.designMode),
            constructionTeam: constList.turnListToString(this.data.constructionTeam),
            structureType: constList.turnListToString(this.data.structureType),
            wallMaterial: constList.turnListToString(this.data.wallMaterial),
            floorType: constList.turnListToString(this.data.floorType),
            roofType: constList.turnListToString(this.data.roofType),
            aseismaticStructure: constList.turnListToString(this.data.aseismaticStructure),
            isExpansion: constList.turnListToString(this.data.isExpansion),
            expansionNumber: this.data.expansionNumber,
            expansionYear: this.data.expansionYear,
            expansionContent: constList.turnListToString(this.data.expansionContent),
            isChangeStructure: constList.turnListToString(this.data.isChangeStructure),
        }
        console.log(updateData);
        let submitRs ='';
        try {
            Loading.begin();
            submitRs = await informationCollectSelfBuildingService.updateByEntity(updateData);
        } catch (e) {
            console.error(e);
        }finally{
            Loading.end()
        }
        console.log(submitRs.data);

        wx.navigateTo({
            url: "/pages/messgecollect2/messgecollect2?id="+this.data.id,
        })
    },
    prev(){
        wx.navigateBack({
            delta:0,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let id = options.id;
        // id= '27fdf7a1-5471-41ef-a746-b9dc13a9310d';
        this.setData({
            id: id,
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