const CONSTANT = require('../../../common/constant');

Component({
    options: {
        multipleSlots: true // 在组件定义时的选项中启用多slot支持
    },
    /**
     * 组件的属性列表
     */
    properties: {
        // 隐藏可编辑模式(true 为问卷模式)
        hiddenEditable: {
            type: Boolean,
            value: false,
        },
        subjectOrder: {
            type: Number,
            value: 1,
        }
    },

    /** 组件数据 */
    data: {
        id: '',
        formUuid: '',
        // 当前签名判断
        isSelected: false,
        hiddenBaseSetting: true,
        hiddenMoreSetting: true,
        /** BEGIN 组件数据 */
        componentType: CONSTANT.FORM_COMPONENT_TYPE.SIGN,
        // 填空回答
        subjectAnswer: '',
        // 题目标题
        subjectTitle: '',
        // 题目类型
        subjectType: CONSTANT.FORM_SUBJECT_TYPE.SIGN,
        // 是否必须
        isRequire: false,
        isRequireTemp: false,
        hiddenDefaultSign: false,
        /** END 组件数据 */
        uploadImg: '',
    },

    /** 组件方法 */
    methods: {
        /** BEGIN 外部调用方法 */
        getData() {
            return this.data;
        },
        buildData(newData) {
            console.log(newData);
            // 除了顺序 subjectOrder  
            this.setData({ ...newData, })
        },
        buildSign(tempFilePath) {
            this.setData({
                isSelected: false,
                hiddenDefaultSign: true,
                uploadImg: tempFilePath,
            })
            // console.log(tempFilePath);
        },
        /** END 外部调用方法 */  
        /** BEGIN 页面事件 */
        bindinputSujectTitle(e){
            this.setData({
                subjectTitle: e.detail.value,
            })
        },
        bindtapDrawSign(e) {
            console.log(this.data.hiddenEditable);
            if (!this.data.hiddenEditable) {
                return;
            }
            this.setData({
                isSelected: true,
            })
            wx.navigateTo({
                url: '/pages/commonSign/commonSign',
            })
        },
        /** END 页面事件 */
        
        /** BEGIN 基础设置 相关 */
        bandtapBaseSettingPopup() {
            let hiddenBaseSetting = !this.data.hiddenBaseSetting
            this.setData({
                hiddenBaseSetting: hiddenBaseSetting
            })
        },
        bindchangeIsRequire(e) {
            this.setData({
                isRequireTemp: e.detail.value
            })
        },
        bindtapBaseSettingCancel() {
            this.setData({
                hiddenBaseSetting: true
            })
        },
        bindtapBaseSettingConfirm() {
            this.setData({
                isRequire: this.data.isRequireTemp,
                hiddenBaseSetting: true
            })
        },
        /** END 基础设置 相关 */

        /** BEGIN 更多设置 相关 */
        bindtapMoreSettingPopup() {
            this.setData({
                hiddenMoreSetting: false
            })
        },
        bindtapMoreSettingCancel() {
            this.setData({
                hiddenMoreSetting: true
            })
        },
        bindtapCopy(e) {
            this.setData({
                hiddenMoreSetting: true,
            });
            let detail = {
                value: this.data,
            }
            this.triggerEvent('copy', detail);
        },
        bindtapMovePrev(e) {
            this.setData({
                hiddenMoreSetting: true,
            });
            let detail = {
                value: this.data,
            }
            this.triggerEvent('movePrev', detail);
        },
        bindtapMoveNext(e) {
            this.setData({
                hiddenMoreSetting: true,
            });
            let detail = {
                value: this.data,
            }
            this.triggerEvent('moveNext', detail);
        },
        bindtapDelete(e) {
            this.setData({
                hiddenMoreSetting: true,
            });
            let detail = {
                value: this.data,
            }
            this.triggerEvent('delete', detail);
        },
        /** END 更多设置 相关 */

    },

    /** 组件属性 */
    /** 组件生命周期 */
    lifetimes: {
        created: function () {
        },
        ready: async function () {

        }
    },
    /** 页面生命周期 */
    pageLifetimes: {
        show: function () {
        },
        hide: function () {
            // 页面被隐藏
        },
        resize: function (size) {
            // 页面尺寸变化
        }
    }
})