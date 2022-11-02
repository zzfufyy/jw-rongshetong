// 处理url映射问题
const string_util = require('./string_util');
const isImageUrlInServer = function (url) {
    if (typeof (url) === "undefined" || url === null || url.length == 0) {
        return false;
    } else {
        return url.substr(0, 1) == '/' ? true : false
    }
}

const appendHeadSlash = function (str) {
    if (string_util.isEmpty(str)) {
        return str;
    }
    if (str.substr(0, 1) != '/') {
        return '/' + str;
    } else {
        return str;
    }
}

module.exports = {
    isImageUrlInServer: isImageUrlInServer,
    appendHeadSlash: appendHeadSlash,
}