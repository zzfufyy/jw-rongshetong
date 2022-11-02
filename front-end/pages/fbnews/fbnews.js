const Loading = require('../../utils/loading_util');
const date_util = require('../../utils/date_util');
const url_util = require('../../utils/url_util');
const { Completer } = require('../../utils/function_util');
const CONSTANT = require('../../common/constant');
const communityInformationService = require('../../common/communityInformationService');
const informationNewsService = require('../../common/informationNewsService');
const userCandidateService = require('../../common/userCandidateService');
const $ = require('../../utils/request_util');
const string_util = require('../../utils/string_util');
const util = require('../../utils/util.js');
const app = getApp();
Page({
    data: {
        formats: {},
        readOnly: false,
        placeholder: '请输入...',
        editorHeight: 200,
        keyboardHeight: 0,
        isIOS: false,
        array: ['请选择类型', '新闻', '活动'],
        index: 0,
        titlecont: '',
        scfile: '点击上传文件',
        communityUuid: "",
        newTilte: "",
        filepath: "",
        newIntroduction: "",
    },
    // 发布类型*
    bindPickerChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            index: e.detail.value
        })
    },
    newTilte: function (e) {

        this.setData({
            newTilte: e.detail.value
        })
    },
    newIntroduction: function (e) {
        this.setData({
            newIntroduction: e.detail.value
        })
    },
    //点击上传图片
    scimgbtn() {
        let that = this
        wx.chooseMedia({
            count: 1,
            mediaType: ['image'],
            sourceType: ['album', 'camera'],
            maxDuration: 30,
            camera: 'back',
            success(res) {
                console.log(res)
                that.setData({
                    imgurl: res.tempFiles[0].tempFilePath
                })
            }
        })
    },
    //点击上传文件
    scfile() {
        let that = this
        wx.chooseMessageFile({
            count: 1,
            type: 'file',
            success(res) {
                // tempFilePath可以作为img标签的src属性显示图片
                console.log(res)
                that.setData({
                    scfile: res.tempFiles[0].name,
                    filepath: res.tempFiles[0].path,
                })
            }
        })
    },
    deletefile(){
        let that = this
        that.setData({
            scfile: "点击上传文件",
            filepath: "",
        }) 
    },
    readOnlyChange() {
        this.setData({
            readOnly: !this.data.readOnly
        })
    },
    //输入完毕失去焦点
    losecon(e) {
        let titlecont = e.detail.html
        console.log(titlecont)
        this.setData({
            titlecont: titlecont
        })
    },
    // 保存
    async tijsq() {
        let that = this;
        let uuid = util.wxuuid()
        let openid = wx.getStorageSync('openid')
        console.log(openid)
        let user = userCandidateService.loadEntityById(openid);
        console.log(user);
        // return;
        if (string_util.isEmpty(this.data.newTilte)) {
            wx.showModal({
                title: "请输入新闻标题"
            })
            return;
        }
        if (string_util.isEmpty(this.data.newIntroduction)) {
            wx.showModal({
                title: "请输入新闻简介"
            })
            return;
        }

        let informationNews = {
            id: uuid,
            articleTitle: that.data.newTilte,
            articleType: that.data.index,
            articleIntroduction: that.data.newIntroduction,
            articleAuthor: that.data.sqname,
            userId: openid,
            articleReleaseTime: new Date(),
            // staffName:that.data.newTilte,
            communityUuid: that.data.communityUuid,
            // articleAuthor:that.data.newTilte,
            articleContent: that.data.titlecont
        }
        informationNewsService.insertByEntity(informationNews)
        let flagUpload = true;
        if (string_util.isEmpty(that.data.imgurl) || that.data.imgurl.includes(app.globalData.web_path)) {
            flagUpload = false;
        }
        if (flagUpload) {
            let uploadPromise = $.upload({
                url: '/information-news/uploadPortrait?id=' + uuid,
                filePath: that.data.imgurl,
                formData: {},
                name: 'file',
                header: app.globalData.header,
            });
            await uploadPromise
                .then((r) => { console.log(r) })
                .catch((r) => console.error(r));
        }

        let flagUpload1 = true;
        if (string_util.isEmpty(that.data.filepath) || that.data.filepath.includes(app.globalData.web_path)) {
            flagUpload1 = false;
        }
        if (flagUpload1) {
            console.log(that.data.scfile)
            let uploadPromise = $.upload({
                url: '/information-news/uploadPortraitFile?id=' + uuid + '&ext1=' + that.data.scfile,
                filePath: that.data.filepath,
                formData: {},
                name: 'file',
                header: app.globalData.header,
            });
            await uploadPromise
                .then((r) => { console.log(r) })
                .catch((r) => console.error(r));
        }
        wx.showLoading({
            title: '发布中',
            mask:true,
            // duration: 2000,
            success(){
                setTimeout(function () {
                    wx.hideLoading()
                    wx.navigateTo({
                        url: '/pages/wztjcg/wztjcg?communityUuid=' + that.data.communityUuid + '&newsUuid=' + uuid,
                    })
                }, 2000)
            }
        })
        // wx.showToast({
        //     title: '发布成功',
        //     icon: 'success',
        //     duration: 2000
        // })
        // wx.navigateTo({
        //     url: '/pages/wztjcg/wztjcg?communityUuid=' + that.data.communityUuid + '&newsUuid=' + uuid,
        // })
    },
    bindready(){
        this.setData({
            keyboardHeight:0,
        })
    },
    editorfocus(e){
        let keyboardHeight = 0
        // console.log(e)
        this.setData({
            keyboardHeight:136,
        })
        let that = this
        wx.onKeyboardHeightChange(res => {
            if (res.height === keyboardHeight) return
            const duration = res.height > 0 ? res.duration * 1000 : 0
            keyboardHeight = res.height
            console.log(keyboardHeight)
            that.setData({
                keyboardHeight:res.height -190
            })
            setTimeout(() => {
                wx.pageScrollTo({
                    // scrollTop:805,
                    success() {
                        that.updatePosition(keyboardHeight)
                        that.editorCtx.scrollIntoView()
                    }
                })
            }, duration)

        })
    },
    onBindBlur(){

        this.setData({
            keyboardHeight:0,
        })
    },
    blur(){
        this.editorCtx.blur()
    },
    async onLoad(options) {
        console.log(options)
        let communityData = await communityInformationService.loadEntityById(options.communityUuid);
        communityData = communityData.data;
        console.log(communityData);
        this.setData({
            sqname: communityData.communityName,
            communityUuid: options.communityUuid,
        })
        const platform = wx.getSystemInfoSync().platform
        const isIOS = platform === 'ios'
        this.setData({ isIOS })
        const that = this
        this.updatePosition(0)
        // let keyboardHeight = 0
        // wx.onKeyboardHeightChange(res => {
        //     if (res.height === keyboardHeight) return
        //     const duration = res.height > 0 ? res.duration * 1000 : 0
        //     keyboardHeight = res.height
        //     setTimeout(() => {
        //         wx.pageScrollTo({
        //             // scrollTop: 0,
        //             success() {
        //                 that.updatePosition(keyboardHeight)
        //                 that.editorCtx.scrollIntoView()
        //             }
        //         })
        //     }, duration)

        // })
    },
    updatePosition(keyboardHeight) {
        const toolbarHeight = 50
        const { windowHeight, platform } = wx.getSystemInfoSync()
        let editorHeight = keyboardHeight > 0 ? (windowHeight - keyboardHeight - toolbarHeight) : windowHeight
        this.setData({ editorHeight, keyboardHeight })
    },
    calNavigationBarAndStatusBar() {
        const systemInfo = wx.getSystemInfoSync()
        const { statusBarHeight, platform } = systemInfo
        const isIOS = platform === 'ios'
        const navigationBarHeight = isIOS ? 44 : 48
        return statusBarHeight + navigationBarHeight
    },
    onEditorReady() {
        const that = this
        wx.createSelectorQuery().select('#editor').context(function (res) {
            that.editorCtx = res.context
        }).exec()
    },
    blur() {
        this.editorCtx.blur()
    },
    format(e) {
        let { name, value } = e.target.dataset
        if (!name) return
        // console.log('format', name, value)
        this.editorCtx.format(name, value)

    },
    onStatusChange(e) {
        const formats = e.detail
        this.setData({ formats })
    },
    insertDivider() {
        this.editorCtx.insertDivider({
            success: function () {
                console.log('insert divider success')
            }
        })
    },
    clear() {
        this.editorCtx.clear({
            success: function (res) {
                console.log("clear success")
            }
        })
    },
    removeFormat() {
        this.editorCtx.removeFormat()
    },
    insertDate() {
        const date = new Date()
        const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
        this.editorCtx.insertText({
            text: formatDate
        })
    },
    insertImage() {
        const that = this
        wx.chooseImage({
            count: 1,
            success: function (res) {
                that.editorCtx.insertImage({
                    src: res.tempFilePaths[0],
                    data: {
                        id: 'abcd',
                        role: 'god'
                    },
                    width: '80%',
                    success: function () {
                        console.log('insert image success')
                    }
                })
            }
        })
    }
})
