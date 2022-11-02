const {
    Completer
} = require('../utils/function_util')

const authorizeRecord = async function () {
    let authCompleter = new Completer();
    let settingInfo = await wx.getSetting({
        withSubscriptions: true,
    });
    if (!settingInfo.authSetting['scope.record']) {
        let recordAuthorize = wx.authorize({
            scope: 'scope.record',
        });
        await recordAuthorize.then(v => {
            console.log('用户授权 录音');
            authCompleter.resolve(true);
        }).catch(v => {
            console.log('用户未授权 录音');
            authCompleter.resolve(false);
        })
    } else {
        authCompleter.resolve(true);
    }
    return authCompleter.promise;
}

const authorizeCamera = async function () {
    let authCompleter = new Completer();
    let settingInfo = await wx.getSetting({
        withSubscriptions: true,
    });
    if (!settingInfo.authSetting['scope.camera']) {
        let cameraAuthorize = wx.authorize({
            scope: 'scope.camera',
        });
        await cameraAuthorize.then(v => {
            console.log('用户授权 视频');
            authCompleter.resolve(true);
        }).catch(v => {
            console.log('用户未授权 视频');
            authCompleter.resolve(false);
        })
    } else {
        authCompleter.resolve(true);
    }
    return authCompleter.promise;
}

const isCanVideoInterview = async function () {
    let authSuccessCompleter = new Completer();
    let isAuthRecord = await authorizeRecord();
    let isAuthCamera = await authorizeCamera();
    if (!isAuthRecord || !isAuthCamera) {
        wx.showModal({
            title: '提示',
            content: '您尚未授权录音或摄像头功能，请授权后使用本功能',
            success: function (res) {
                if (res.confirm) {
                    wx.openSetting({
                        success(res2) {
                            console.log(res2);
                            if (res2.authSetting["scope.camera"] && res2.authSetting["scope.record"]) {
                                authSuccessCompleter.resolve(true);
                            } else {
                                authSuccessCompleter.resolve(false);
                            }
                        },
                        fail(res) {
                            console.error(res);
                            authSuccessCompleter.resolve(false);
                        }
                    })
                } else {
                    authSuccessCompleter.resolve(false);
                    console.log("cancel");
                }
            }
        })
    } else {
        authSuccessCompleter.resolve(true);
    }
    return await authSuccessCompleter.promise;
}

module.exports = {
    authorizeRecord: authorizeRecord,
    authorizeCamera: authorizeCamera,
    isCanVideoInterview: isCanVideoInterview,
}