// pages/tp/tp.js
const CONSTANT = require('../../common/constant');

const $ = require('../../utils/request_util');
const { Completer } = require('../../utils/function_util');
const Loading = require('../../utils/loading_util');
const object_util = require('../../utils/object_util');
const string_util = require('../../utils/string_util');

const formInformationService = require('../../common/formInformationService');
const formSubjectService = require('../../common/formSubjectService');
const formSubjectOptionService = require('../../common/formSubjectOptionService');
const userForFormService = require('../../common/userForFormService');

const COMPONENT_ID_PREFIX = '#option-component-'
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userOpenid: '',
        formUuid: '',

        formTitle: '关于东湖社区的某项活动投票征集中',
        formIntroduction: '说明详情',
        subjectList: [
        ],
    },
    /** BEGIN 页面事件函数 */
    // 提交表单事件处理
    async bindtapSubmitForm(e) {
        // 同步组件数据
        this._getAllSubjectComponentData();

        // 检查是否必选已填
        console.log(this.data.subjectList);
        let currentSubjectList = this.data.subjectList;
        // 构建 图片上传题
        let uploadImgSubjectList = new Array();
        currentSubjectList.filter(v => {
            return v.componentType == CONSTANT.FORM_COMPONENT_TYPE.IMG_UPLOAD;
        }).forEach(v => {
            v.uploadImgList.forEach(r => {
                uploadImgSubjectList.push({
                    componentType: v.componentType,
                    subjectType: v.subjectType,
                    userOpenid: this.data.userOpenid,
                    subjectAnwser: '',
                    uploadImg: r.uploadImg,
                    optionUuid: '',
                    subjectUuid: v.id,
                    formUuid: this.data.formUuid,
                })
            })
        })
        // 构建 签名题
        currentSubjectList.filter(v => {
            return v.componentType == CONSTANT.FORM_COMPONENT_TYPE.SIGN;
        }).forEach(v => {
            if (string_util.isEmpty(v.uploadImg)) {
                return;
            }
            uploadImgSubjectList.push({
                componentType: v.componentType,
                subjectType: v.subjectType,
                userOpenid: this.data.userOpenid,
                subjectAnwser: '',
                uploadImg: v.uploadImg,
                optionUuid: '',
                subjectUuid: v.id,
                formUuid: this.data.formUuid,
            })
        })
        console.log(uploadImgSubjectList);
        // 循环上传图片题目 文件和 formdata
        uploadImgSubjectList.forEach(async v => {
            await $.upload({
                url: '/user-for-form/addUploadImgSubject',
                filePath: v.uploadImg,
                name: 'file',
                head: $.ContentType.MULTIPART,
                formData: v,
            })
        })
        // 遍历选择题 
        let selectSubjectList = new Array();
        currentSubjectList.filter(v => {
            return v.componentType == CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT;
        }).forEach(v => {
            v.optionList.filter(t => {
                return t.checked;
            }).forEach(r => {
                selectSubjectList.push({
                    componentType: v.componentType,
                    subjectType: v.subjectType,
                    userOpenid: this.data.userOpenid,
                    subjectAnwser: '',
                    uploadImg: '',
                    optionUuid: r.id,
                    subjectUuid: v.id,
                    formUuid: this.data.formUuid,
                })
            })
        })
        // 填空题
        let completionSubjectList = new Array();
        currentSubjectList.filter(v => {
            return v.componentType == CONSTANT.FORM_COMPONENT_TYPE.COMPLETION;
        }).forEach(v => {
            console.log(v);
            completionSubjectList.push({
                componentType: v.componentType,
                subjectType: v.subjectType,
                userOpenid: this.data.userOpenid,
                subjectAnswer: v.subjectAnswer,
                uploadImg: '',
                optionUuid: '',
                subjectUuid: v.id,
                formUuid: this.data.formUuid,
            })
        })
        await userForFormService.insertByList([...selectSubjectList, ...completionSubjectList]);
        wx.showToast({
            icon: 'success',
            title: '提交成功',
            duration: 1500,
        })
        setTimeout(
            () => wx.navigateBack({
                delta: 1,
            }), 1700
        )
    },
    /** END 页面事件函数 */


    /** BEGIN 外部调用方法 */
    // 签名
    callGetCommonSign: function (tempFilePath) {
        this._getAllSubjectComponentData();
        this._setAllSubjectComponentData();
        let choosedIndex = this.data.subjectList.findIndex(v => { return v.isSelected == true })
        console.log(choosedIndex);
        this.selectComponent(COMPONENT_ID_PREFIX + CONSTANT.FORM_COMPONENT_TYPE.SIGN + '-' + choosedIndex).buildSign(tempFilePath);
    },
    /** END 外部调用方法 */

    /** BEGIN 内部方法 */
    // 页面与组件 传、赋值  构造 触发事件双向绑定效果
    _getAllSubjectComponentData() {
        let currentSubjectList = this.data.subjectList;
        this.setData({
            subjectList: currentSubjectList.map((v, index) => {
                return this.selectComponent(COMPONENT_ID_PREFIX + v.componentType + '-' + index).getData()
            }),
        })
        console.log(this.data.subjectList);
    },
    _setAllSubjectComponentData() {
        let currentSubjectList = this.data.subjectList;
        currentSubjectList.map((v, index) => {
            console.log(v);
            console.log(index);
            this.selectComponent(COMPONENT_ID_PREFIX + v.componentType + '-' + index).buildData(v);
        });
    },
    /** END 内部方法 */
    /**
     * 生命周期函数--监听页面加载
     */
    formUuidCompleter: new Completer(),
    onLoad: async function (options) {
        // 初始化
        this.formUuidCompleter = new Completer();
        let formUuid = options.formUuid;
        this.formUuidCompleter.resolve(formUuid);
        await app.getOpenidReady();
        let userOpenid = app.getOpenid();
        this.setData({
            userOpenid: userOpenid,
            formUuid: formUuid,
        });
        // 加载表单内容
        await this.loadContent();

    },

    async loadContent() {
        await this.formUuidCompleter.promise;
        // 加载 form信息
        let formInformation = (await formInformationService.loadEntityById(this.data.formUuid)).data;
        this.setData({
            formTitle: formInformation.formTitle,
            formIntroduction: formInformation.formIntroduction,
        });
        // 加载题目信息
        let subjectList = (await formSubjectService.loadEntityListByFormUuid(this.data.formUuid)).data;
        // 加载option信息
        let selectSubjectUuidList = [];
        subjectList.forEach(v => {
            if (v.componentType == CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT) {
                selectSubjectUuidList.push(v.id)
            }
        })
        console.log(selectSubjectUuidList)
        if (selectSubjectUuidList.length > 0) {
            let allOptionList = (await formSubjectOptionService.listBySubjectUuidList(selectSubjectUuidList)).data;
            console.log(allOptionList);
            subjectList = subjectList.map(v => {
                if (selectSubjectUuidList.some(a => { return a == v.id })) {
                    v.optionList = allOptionList.filter(r => {
                        return r.subjectUuid == v.id
                    })
                }
                return v;
            })
        }
        console.log(subjectList);

        this.setData({
            subjectList: subjectList,
        });
        this._setAllSubjectComponentData();
        this._getAllSubjectComponentData();
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
        // 将所有签名被选择状态置为false
        let signComponentIndexList = this.data.subjectList.map((v, index) => {
            return (v.componentType == CONSTANT.FORM_COMPONENT_TYPE.SIGN) ? index : -1
        }).filter(r => {
            return r != -1
        });
        signComponentIndexList.forEach(v => {
            this.selectComponent(COMPONENT_ID_PREFIX + CONSTANT.FORM_COMPONENT_TYPE.SIGN + '-' + v).setData({
                isSelected: false,
            })
        })
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