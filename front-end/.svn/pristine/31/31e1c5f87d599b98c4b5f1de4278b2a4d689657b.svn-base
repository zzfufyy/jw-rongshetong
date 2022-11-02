const CONSTANT = require('../../../common/constant');
const object_util = require('../../../utils/object_util');
const app = getApp();
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
            value: 1,
            type: Number,
        }
    },

    /** 组件数据 */
    data: {
        id: '',
        formUuid: '',

        hiddenBaseSetting: true,
        hiddenMoreSetting: true,

        /** BEGIN 组件数据 */
        uploadImgCountIndex: 0,
        uploadImgCountList: [1, 2, 3, 4, 5, 6, 7, 8],
        // 组件类型
        componentType: CONSTANT.FORM_COMPONENT_TYPE.IMG_UPLOAD,
        // 填空回答
        uploadImgList: [
            // {uploadImg: "/img/gbimg.png"},
            // {uploadImg: "/img/gbimg.png"},
            // {uploadImg: "/img/gbimg.png"},
        ],
        uploadImgCount: 1,
        // 题目标题
        subjectTitle: '',
        // 题目类型
        subjectType: CONSTANT.FORM_SUBJECT_TYPE.IMG_UPLOAD,
        // 是否必须
        isRequire: false,
        isRequireTemp: false,
        /** END 组件数据 */
    },

    /** 组件方法 */
    methods: {
        // 点击事件
        bindtapUploadImg(e) {
            if(!this.data.hiddenEditable){
                return;
            }
            let $this = this;
            console.log($this.data);
            wx.chooseMedia({
                count: 1, // 先设置上传一个
                mediaType: ['image'],
                sourceType: ['album', 'camera'],
                maxDuration: 30,
                camera: 'back',
                success: res => {
                    let tempFiles = res.tempFiles;
                    let uploadImgList = object_util.copyObject($this.data.uploadImgList);
                    uploadImgList.push({ uploadImg: tempFiles[0].tempFilePath });
                    $this.setData({
                        uploadImgList: uploadImgList,
                    })
                }
            });
        },
        bindinputSujectTitle(e){
            this.setData({
                subjectTitle: e.detail.value,
            })
        },
        bindchangeUploadImgCount(e) {
            this.setData({
                uploadImgCountIndex: e.detail.value,
            })
        },
        
        // 暴露方法
        getData() {
            return this.data;
        },
        buildData(newData) {
            // 除了顺序 subjectOrder  
            this.setData({
                ...newData,
            })
        },
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
                uploadImgCount: this.data.uploadImgCount[this.data.uploadImgCountIndex],
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
            let $this = this;
            wx.getSystemInfo({
                success: res => {
                    // 页面一排容纳 3个图片
                    $this.setData({
                        // container margin padding  img margin
                        imgWidth: (res.windowWidth - 40 - 32 - 25) / 3,
                    })
                }
            });
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