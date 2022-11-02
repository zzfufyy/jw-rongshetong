// pages/djmesge/djmesge.js
// 加载工具
const CONSTANT = require('../../common/constant');
const IDCard = require('../../common/IDCard.js');
const { Completer } = require('../../utils/function_util.js');
const Loading = require('../../utils/loading_util');
const string_util = require('../../utils/string_util');

// 加载服务
const buildingResidentService = require('../../common/buildingResidentService');
const residentForPersonService = require('../../common/residentForPersonService');
const date_util = require('../../utils/date_util');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 状态
        personUuid: '',
        // 关联项加载
        residentUuid: '',
        buildingUuid: '',
        cellUuid: '',
        communityUuid: '',

        personType: 0,


        personName: '',
        personIdcard: '',
        personPhone: '',

        provinceName: '',
        cityName: '',
        districtName: '',

        personAddress: '',

        personGender: 0,
        personAge: 0, // 只是显示 年龄  存的是出生日期
        personBirthday: '',

        partyInstitution: '',
        personSpecial: 0,
        remark: '',


        genderList: CONSTANT.genderList,
        personSpecialList: CONSTANT.personSpecialList,



        cor1: '',



    },
    state: {
        residentUuidCompleter: new Completer(),
        personTypeCompleter: new Completer(),
        personUuidCompleter: new Completer(),
    },
    // 姓名
    bindtapPersonName(e) {
        this.setData({
            personName: e.detail.value
        })
    },
    // 解析身份证
    bindtapPersonIdcard(e) {
        let idcard = e.detail.value
        this.setData({
            personIdcard: idcard
        })
        if (idcard.length == 18) {
            this.setData({
                personBirthday: IDCard.IdCard(e.detail.value, 1),
                personAge: IDCard.IdCard(e.detail.value, 3),
                personGender: IDCard.IdCard(e.detail.value, 2)
            })
        } else {
            this.setData({
                personBirthday: '',
                personAge: 0,
                personGender: 0,
            })
        }
    },
    // 电话号码
    bindtapPersonPhone(e) {
        this.setData({
            personPhone: e.detail.value
        })
    },

    // 户籍地
    bindchangeRegion: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            provinceName: e.detail.value[0],
            cityName: e.detail.value[1],
            districtName: e.detail.value[2],
            cor1: '#333'
        })
    },
    // 详细地址
    bindtapPersonAddress(e) {
        this.setData({
            personAddress: e.detail.value,
        });
    },
    // 党员（单位）
    bindinputPartyInstitution(e) {
        this.setData({
            partyInstitution: e.detail.value,
        });
    },
    //特殊人群
    bindPickerChange: function (e) {
        this.setData({
            personSpecial: e.detail.value,
            cor2: '#333'
        })
    },
    //备注
    bindinputRemark(e) {
        this.setData({
            remark: e.detail.value,
        })
    },
    //保存
    async bindtapSubmit() {
        if (string_util.isEmpty(this.data.personName)) {
            wx.showToast({
                icon: 'error',
                title: '请输入姓名',
            })
        }
        let insertData = {
            residentUuid: this.data.residentUuid,
            buildingUuid: this.data.buildingUuid,
            cellUuid: this.data.cellUuid,
            communityUuid: this.data.communityUuid,
            personType: this.data.personType,
            personName: this.data.personName,
            personIdcard: this.data.personIdcard,
            personPhone: this.data.personPhone,
            provinceName: this.data.provinceName,
            cityName: this.data.cityName,
            districtName: this.data.districtName,
            personAddress: this.data.personAddress,
            personGender: this.data.personGender,
            personBirthday: this.data.personBirthday,
            partyInstitution: this.data.partyInstitution,
            personSpecial: this.data.personSpecial,
            remark: this.data.remark,
        }
        if (string_util.isEmpty(this.data.personUuid)) {
            // 如果没有 personUuid 则判断为插入
            await residentForPersonService.insertByEntity(insertData);
        } else {
            // 如果不为空 执行更新操作
            let updateData = {
                ...insertData,
                id: this.data.personUuid
            }
            await residentForPersonService.updateByEntity(updateData);
        }
        wx.navigateBack({
            delta: 1
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // 人员类型
        let personType = options.personType;
        let residentUuid = options.residentUuid;
        let personUuid = options.personUuid;
        console.log(personUuid);
        this.state.residentUuidCompleter = new Completer();
        this.state.personTypeCompleter = new Completer();
        this.state.personUuidCompleter = new Completer();
        // 测试id
        // residentUuid = '801fb106-6792-4770-8f34-7f8950ca61cd';
        // 测试personType
        // personType = 1;
        this.state.residentUuidCompleter.resolve(residentUuid);
        this.state.personTypeCompleter.resolve(personType);
        this.state.personUuidCompleter.resolve(personUuid);
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: async function () {
        try {
            Loading.begin();
            await this.loadContent();
        } catch (e) {
            console.error(e);
        } finally {
            Loading.end();
        }
    },
    // 加载页面
    loadContent: async function () {
        let residentUuid = await this.state.residentUuidCompleter.promise;
        let personType = await this.state.personTypeCompleter.promise;
        let personUuid = await this.state.personUuidCompleter.promise;
        console.log(personUuid);
        if (residentUuid != undefined && personType != undefined) {
            // 新增
            let buildingResidentData = await buildingResidentService.loadEntityById(residentUuid);
            buildingResidentData = buildingResidentData.data;
            this.setData({
                residentUuid: residentUuid,
                buildingUuid: buildingResidentData.buildingUuid,
                cellUuid: buildingResidentData.cellUuid,
                communityUuid: buildingResidentData.cellUuid,
                personType: personType,
            });
        }

        // 如果是修改 加载数据
        if (!string_util.isEmpty(personUuid)) {
            this.setData({
                personUuid: personUuid,
            });
            let personData = await residentForPersonService.loadEntityById(personUuid);
            personData = personData.data;
            this.setData({
                residentUuid: personData.residentUuid,
                buildingUuid: personData.buildingUuid,
                cellUuid: personData.cellUuid,
                communityUuid: personData.communityUuid,
                personType: personData.personType,
                personName: personData.personName,
                personIdcard: personData.personIdcard,
                personPhone: personData.personPhone,
                provinceName: personData.provinceName,
                cityName: personData.cityName,
                districtName: personData.districtName,
                personAddress: personData.personAddress,
                personGender: personData.personGender,
                personBirthday: personData.personBirthday,
                personAge: date_util.getAgeByBirthday(personData.personBirthday),
                partyInstitution: personData.partyInstitution,
                personSpecial: personData.personSpecial,
                remark: personData.remark,
            })
        }

        // 设置标题
        if (this.data.personType == 1) {
            wx.setNavigationBarTitle({
                title: '从业人员信息',
            })
        } else {
            wx.setNavigationBarTitle({
                title: '居住人员信息',
            })
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

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