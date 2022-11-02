// pages/ptjuzhu/ptjuzhu.js

const CONSTANT = require('../../common/constant');
const Loading = require('../../utils/loading_util');
const { Completer } = require('../../utils/function_util');
const string_util = require('../../utils/string_util');
const date_util = require('../../utils/date_util');


// 加载服务
const buildingResidentService = require('../../common/buildingResidentService');
const residentForPersonService = require('../../common/residentForPersonService');

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 签名是否显示
        hiddenSign: true,


        residentUuid: '',
        buildingUuid: '',
        cellUuid: '',
        communityUuid: '',
        
        // 从业人数
        employeeNumber: 0,
        // 居住人数
        residentNumber: 0,
        genderList: CONSTANT.genderList,
        employeeList: [
            // { personName: '张五', personGender: 0, personBirthday: '', personAge: 29, personIdcard: '431223199809071234', personPhone: '12345678901', provinceName: '湖南省', cityName: '长沙市', districtName: '岳麓区', id: '' },
        ],
        cy: '',
        residentList: [
            // { personName: '张六', personGender: 0, personBirthday: '', personAge: 29, personIdcard: '431223199809071234', personPhone: '12345678901', provinceName: '湖南省', cityName: '长沙市', districtName: '岳麓区', id: '' },
        ],
        rs: '',



    },
    state: {
        residentUuidCompleter: new Completer(),
    },
    bindtapSign() {
        wx.navigateTo({
            url: '/pages/dzqmpage/dzqmpage?url=comjuzhu&residentUuid=' + this.data.residentUuid,
        })

    },
    // 
    bindinputEmployeeNumber(e) {
        console.log(e.detail.value);
        this.setData({
            employeeNumber: e.detail.value
        })
    },
    bindinputResidentNumber(e) {
        console.log(e.detail.value);
        this.setData({
            residentNumber: e.detail.value
        })
    },

    // 添加从业人员
    bindtapAddEmployee(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?residentUuid=' + this.data.residentUuid + '&personType=' + 1,
        })
    },
    // 修改从业人员信息
    bindtapModifyEmployee(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?personUuid=' + e.currentTarget.dataset.id,
        })
    },

    // 删除从业人员信息
    async catchtapDeleteEmployee(e) {
        console.log(e);
        let deleteId = e.currentTarget.dataset.id;
        let currentEmployeeList = this.data.employeeList;
        let deleteOperation = residentForPersonService.deleteById(deleteId);
        await deleteOperation.then(v => {
            wx.showToast({
                icon: 'success',
                title: '删除成功',
            });
            this.setData({
                employeeList: currentEmployeeList.filter(r => { return r.id != deleteId }),
            });
        }).catch(e => {
            wx.showToast({
                icon: 'error',
                title: '删除失败',
            });
            console.error(e);
        })
    },

    // 添加居住人员
    bindtapAddResident(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?residentUuid=' + this.data.residentUuid + '&personType=' + 0,
        })
    },
    // 修改居住人员信息
    bindtapModifyResident(e) {
        wx.navigateTo({
            url: '/pages/djmesge/djmesge?personUuid=' + e.currentTarget.dataset.id,
        })
    },

    // 删除居住人员信息
    async catchtapDeleteResident(e) {
        console.log(e);
        let deleteId = e.currentTarget.dataset.id;
        let currentResidentList = this.data.residentList;
        let deleteOperation = residentForPersonService.deleteById(deleteId);
        await deleteOperation.then(v => {
            wx.showToast({
                icon: 'success',
                title: '删除成功',
            });
            this.setData({
                residentList: currentResidentList.filter(r => { return r.id != deleteId }),
            });
        }).catch(e => {
            wx.showToast({
                icon: 'error',
                title: '删除失败',
            });
            console.error(e);
        })
    },

   
    // 提交
    async bindtapSubmit(e) {
        // 更新从业人数 居住人数
        let updateData ={
            id: this.data.residentUuid,
            employeeNumber: this.data.employeeNumber,
            residentNumber: this.data.residentNumber,
        }
        try {
            Loading.begin();
            await buildingResidentService.updateByEntity(updateData)    
        } catch (e) {
            console.error(e);
        }finally{
            Loading.end();
        }
        wx.redirectTo({
            url: '/pages/mesegsubmit/mesegsubmit?residentUuid=' + this.data.residentUuid,
        })

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.state.residentUuidCompleter = new Completer();
        let residentUuid = options.residentUuid;
        // 测试id
        // residentUuid = '801fb106-6792-4770-8f34-7f8950ca61cd';
        this.state.residentUuidCompleter.resolve(residentUuid);
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

    loadContent: async function () {
        let residentUuid = await this.state.residentUuidCompleter.promise;
        this.setData({
            residentUuid: residentUuid,
        })
        // 加载 从业人数、居住人数,
        let buildingResidentData = await buildingResidentService.loadEntityById(residentUuid);
        buildingResidentData = buildingResidentData.data;
        this.setData({
            employeeNumber: buildingResidentData.employeeNumber,
            residentNumber: buildingResidentData.residentNumber,
            signImgPath: string_util.isEmpty(buildingResidentData.signImgPath) ?
                '' : app.globalData.web_path + buildingResidentData.signImgPath,
            hiddenSign: string_util.isEmpty(buildingResidentData.signImgPath) ? true : false,
        });
        console.log(this.data.hiddenSign);
        // 加载 从业人员、居住人员 信息
        let personList = await residentForPersonService.loadListByResidentUuid(residentUuid);
        personList = personList.data;
        let employeeList = personList.filter(v => {
            return v.personType == 1;
        })
        let residentList = personList.filter(v => {
            return v.personType == 0;
        })
        this.setData({
            employeeList: employeeList.map(v => {
                v.personAge = date_util.getAgeByBirthday(v.personBirthday);
                return v;
            }),
            residentList: residentList.map(v => {
                v.personAge = date_util.getAgeByBirthday(v.personBirthday);
                return v;
            }),
        })
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