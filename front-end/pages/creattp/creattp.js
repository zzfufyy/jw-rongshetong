// pages/creattp/creattp.js
const CONSTANT = require('../../common/constant');

const string_util = require('../../utils/string_util');
const object_util = require('../../utils/object_util');
const prompt_util = require('../../utils/prompt_util');
const { Completer } = require('../../utils/function_util');
const Loading = require('../../utils/loading_util');

const formInformationService = require('../../common/formInformationService');
const formSubjectService = require('../../common/formSubjectService');
const formSubjectOptionService = require('../../common/formSubjectOptionService');
const loading_util = require('../../utils/loading_util');

const COMPONENT_ID_PREFIX = '#option-component-'
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        pageMode: 0, // 页面模式: 0: 创建模式 1: 编辑模式


        formUuid: '',
        effectCommunityUuid: '',
        userOpenid: '',
        formTitle: '',
        formIntroduction: '',
        endTime: '',
        subjectList: [
            {
                componentType: CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT,
                subjectType: CONSTANT.FORM_SUBJECT_TYPE.SINGLE_SELECT,
            },
            // {
            //     componentType: CONSTANT.FORM_COMPONENT_TYPE.COMPLETION,
            //     subjectType: CONSTANT.FORM_SUBJECT_TYPE.COMPLETION,
            // }, 
            // {
            //     componentType: CONSTANT.FORM_COMPONENT_TYPE.IMG_UPLOAD,
            //     subjectType: CONSTANT.FORM_SUBJECT_TYPE.IMG_UPLOAD,
            // }, 
            // {
            //     componentType: CONSTANT.FORM_COMPONENT_TYPE.SIGN,
            //     subjectType: CONSTANT.FORM_SUBJECT_TYPE.SIGN,
            // },
        ],

        showAdvancedSetting: false,

    },
    /** BEGIN 页面事件 */
    bindinputFormTitle(e) {
        this.setData({
            formTitle: e.detail.value,
        })
    },
    bindinputFormIntroduction(e) {
        this.setData({
            formIntroduction: e.detail.value,
        })
    },
    bindchangeEndTime: function (e) {
        this.setData({
            endTime: e.detail.value
        })
    },
    bindchangeShowAdvancedSetting(e) {
        this.setData({
            showAdvancedSetting: e.detail.value,
        })
    },
    bindtapAddSubject(e) {
        wx.navigateTo({
            url: '/pages/subjectAdd/subjectAdd',
        })
    },

    async bindtapSubmit() {
        // 获取所有组件数据
        this._getAllSubjectComponentData();
        /** 进行表单检查 */
        let currentSubjectList = object_util.copyObject(this.data.subjectList);
        // 检查表单标题
        if (string_util.isEmpty(this.data.formTitle)) {
            await prompt_util.showModalPrompt('请填写投票活动标题');
            return;
        }
        // 检查题目标题
        let returnFlag = false;
        for (let i = 0; i < currentSubjectList.length; i++) {
            if (string_util.isEmpty(currentSubjectList[i].subjectTitle)) {
                await prompt_util.showModalPrompt(`请填写第${i + 1}题目标题`);
                returnFlag = true;
                break;
            }
        }
        if (returnFlag) {
            return;
        }
        // 检查选项
        for (let i = 0; i < currentSubjectList.length; i++) {
            if (currentSubjectList[i].componentType == CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT) {
                if (currentSubjectList[i].optionList.findIndex(v => { return string_util.isEmpty(v.optionTitle) }) != -1) {
                    await prompt_util.showModalPrompt(`请完善第${i + 1}题目的选项`);
                    returnFlag = true;
                    break;
                }
            }
        }
        if (returnFlag) {
            return;
        }
        try {
            Loading.begin();
            // 提交 form信息 返回表单id
            let formData = {
                userOpenid: this.data.userOpenid,
                id: this.data.formUuid,
                formTitle: this.data.formTitle,
                formIntroduction: this.data.formIntroduction,
                endTime: this.data.endTime,
                effectCommunityUuid: this.data.effectCommunityUuid,
            }
            console.log(formData);
            let formUuid = (await formInformationService.insertByEntity(formData)).data;
            console.log(formUuid);
            // 构建题目列表 执行插入
            let insertSubjectList = currentSubjectList.map(v => {
                return {
                    subjectOrder: v.subjectOrder,
                    subjectTitle: v.subjectTitle,
                    subjectImg: v.hasOwnProperty('subjectImg') ? v.subjectImg : '',
                    componentType: v.componentType,
                    subjectType: v.subjectType,
                    rowCount: v.hasOwnProperty('rowCount') ? v.rowCount : 1,
                    uploadImgCount: v.hasOwnProperty('uploadImgCount') ? v.uploadImgCount : 1,
                    isRequire: Number(v.isRequire),
                    formUuid: formUuid,
                }
            })

            // 构建选项列表 执行插入
            let subjectUuidMap = (await formSubjectService.insertByList(insertSubjectList)).data;
            subjectUuidMap = new Map(Object.entries(subjectUuidMap));
            let subjectWithOptionList = currentSubjectList.filter(v => {
                return (v.componentType == CONSTANT.FORM_COMPONENT_TYPE.CHOICE_SELECT) ? true : false;
            })
            console.log(subjectWithOptionList);
            let insertSubjectOptionList = [];
            subjectWithOptionList.forEach((v) => {
                let subjectUuid = subjectUuidMap.get(String(v.subjectOrder))
                let tmpList = v.optionList.map((r, index) => {
                    return {
                        optionOrder: index,
                        optionTitle: r.optionTitle,
                        formUuid: formUuid,
                        subjectUuid: subjectUuid,
                    }
                })
                insertSubjectOptionList = insertSubjectOptionList.concat(tmpList);
            })
            await (formSubjectOptionService.insertByList(insertSubjectOptionList));
            // 调用 pages/creattphd/creattphd.js 页面重构方法
            // P.S. 页面路由更改需注意
            console.warn("Mainly Depend on page routing!: pages/creattp/creattp.js - bindtapSubmit");
            let pages = getCurrentPages();
            let prevPage = pages[pages.length - 2];
            await prevPage.callReloadContent();
            // save success
            await wx.showToast({
                icon: 'success',
                title: '保存成功',
                duration: 1500
            });
            setTimeout(() => {
                wx.navigateBack({
                    delta: 0,
                });
            }, 1700);
        } catch (e) {
            prompt_util.showToastProgramErrorPormpt();
            console.error(e);
        } finally {
            Loading.end();
        }

    },
    /** BEGIN 选择框组件 绑定交互事件 */
    formComponentCopy(e) {
        this._getAllSubjectComponentData();
        let currentIndex = e.currentTarget.dataset.index;
        let currentSubjectList = object_util.copyObject(this.data.subjectList);
        currentSubjectList.splice(currentIndex, 0, object_util.copyObject(this.data.subjectList[currentIndex]));
        this.setData({
            subjectList: currentSubjectList,
        });
        this._setAllSubjectComponentData();
    },
    formComponentMovePrev(e) {
        this._getAllSubjectComponentData();
        let currentIndex = e.currentTarget.dataset.index;
        let currentSubjectList = object_util.copyObject(this.data.subjectList);
        currentSubjectList.splice(currentIndex - 1, 0, object_util.copyObject(this.data.subjectList[currentIndex]));
        currentSubjectList.splice(currentIndex + 1, 1);
        this.setData({
            subjectList: currentSubjectList,
        });
        this._setAllSubjectComponentData();
    },
    formComponentMoveNext(e) {
        this._getAllSubjectComponentData();
        let currentIndex = e.currentTarget.dataset.index;
        let currentSubjectList = object_util.copyObject(this.data.subjectList);
        currentSubjectList.splice(currentIndex + 2, 0, object_util.copyObject(this.data.subjectList[currentIndex]));
        currentSubjectList.splice(currentIndex, 1);
        // currentSubjectList.splice(currentIndex, 1);
        this.setData({
            subjectList: currentSubjectList,
        });
        this._setAllSubjectComponentData();
    },
    formComponentDelete(e) {
        this._getAllSubjectComponentData();
        let currentIndex = e.currentTarget.dataset.index;
        let currentSubjectList = object_utils.copyObject(this.data.subjectList);
        currentSubjectList.splice(currentIndex, 1);
        this.setData({
            subjectList: currentSubjectList,
        });
        this._setAllSubjectComponentData();
    },
    /** END 选择框组件 绑定交互事件 */

    /** BEGIN 内部方法 */
    // 页面与组件 传、赋值  构造 触发事件双向绑定效果
    _getAllSubjectComponentData() {
        let currentSubjectList = this.data.subjectList;
        this.setData({
            subjectList: currentSubjectList.map((v, index) => {
                return this.selectComponent(COMPONENT_ID_PREFIX + v.componentType + '-' + index).getData()
            }),
        })
    },
    _setAllSubjectComponentData() {
        let currentSubjectList = this.data.subjectList;
        currentSubjectList.forEach((v, index) => {
            this.selectComponent(COMPONENT_ID_PREFIX + v.componentType + '-' + index).buildData({ ...v, subjectOrder: index });
        });
    },
    /** END 内部方法 */

    /** BEGIN 外部暴露方法 */
    callAddSubject(componentType, subjectType) {
        this.setData({
            subjectList: this.data.subjectList.concat({
                componentType: componentType,
                subjectType: subjectType,
            }),
        })
    },
    /** END 外部暴露方法 */


    /**
     * 生命周期函数--监听页面加载
     */
    communityUuidCompleter: new Completer(),
    formUuidCompleter: new Completer(),
    onLoad: async function (options) {
        console.log(options);
        this.formUuidCompleter = new Completer
        this.communityUuidCompleter = new Completer();
        await app.getOpenidReady();
        let openid = app.getOpenid();
        let communityUuid = options.communityUuid;
        this.communityUuidCompleter.resolve(communityUuid);
        this.setData({
            userOpenid: openid,
            effectCommunityUuid: communityUuid,
        });
        // 如果是编辑模式
        if (string_util.isNotEmpty(options.formUuid)) {
            let formUuid = options.formUuid;
            this.formUuidCompleter.resolve(formUuid);
            this.setData({
                formUuid: formUuid,
                pageMode: 1,
            });
            try {
                Loading.begin();
                await this._loadContent();
            } catch (e) {
                console.error(e);
            } finally {
                Loading.end();
            }
            console.log(this.data);

        } else {
            this.setData({
                pageMode: 0,
            })
        }
    },

    async _loadContent() {
        await this.formUuidCompleter.promise;
        // 加载 form信息
        let formInformation = (await formInformationService.loadEntityById(this.data.formUuid)).data;
        this.setData({
            formTitle: formInformation.formTitle,
            formIntroduction: formInformation.formIntroduction,
            endTime: formInformation.endTime,
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