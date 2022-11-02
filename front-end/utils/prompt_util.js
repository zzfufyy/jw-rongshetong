function saveSuccessPrompt(fn) {
    wx.showToast({
        title: '保存成功',
        icon: 'success',
        duration: 1000,
    })
    setTimeout(fn, 1100);
}

function saveFailPrompt() {
    return wx.showToast({
        title: '保存失败',
        icon: 'error',
        duration: 1000,
    })
}

function loadingSuccessPrompt() {
    return wx.showToast({
        title: '加载成功',
        icon: 'success',
        duration: 1500,
    })
}

function loadingFailPrompt() {
    return wx.showToast({
        title: '加载失败',
        icon: 'error',
        duration: 1500,
    })
}

function companyNoPhoneToastPrompt() {
    return wx.showToast({
        title: '公司暂未留电话',
        icon: 'error',
        duration: 1500,
    })
}

function authorizeNamePortraitFailPrompt() {
    console.info("用户未授权 昵称头像。使用游客身份!");
}

function showModalPrompt(content) {
    return wx.showModal({
        title: '提示',
        content: content,
    })
}

function showToastProgramErrorPormpt() {
    return wx.showToast({
        icon: 'error',
        title: '程序错误',
    })
}

function showToastErrorCustom(text) {
    return wx.showToast({
        icon: "error",
        title: text,
        duration: 1400,
    })
}

function showToastWaitForOpen() {
    return wx.showToast({
        icon: 'error',
        title: '暂未开放',
        duration: 1400,
    })
}


// function asyncShowToastPrompt(param) {
//     let toastParam = {
//         icon: param.icon ? param.icon : 'none',
//         title: param.title ? param.title : '',
//         duration: param.duration ? param.duration : 1200,
//     }
//     let toastRes = wx.showToast({
//         mask: true,
//         ...toastParam,
//     });
//     setTimeout(() => {
//         console.log('执行完毕')
//         return toastRes;
//     }, 5000);


// }

module.exports = {
    saveSuccessPrompt: saveSuccessPrompt,
    saveFailPrompt: saveFailPrompt,
    loadingSuccessPrompt: loadingSuccessPrompt,
    loadingFailPrompt: loadingFailPrompt,
    authorizeNamePortraitFailPrompt: authorizeNamePortraitFailPrompt,
    companyNoPhoneToastPrompt: companyNoPhoneToastPrompt,
    showModalPrompt: showModalPrompt,
    showToastProgramErrorPormpt: showToastProgramErrorPormpt,

    showToastWaitForOpen: showToastWaitForOpen,
    showToastErrorCustom: showToastErrorCustom,
    // asyncShowToastPrompt: asyncShowToastPrompt,
}