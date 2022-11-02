const $ = require('../utils/request_util')

const loadEntityByUserAndPath = function (userOpenid, pagePathApply) {
    return $.request({
        url: '/user-for-function-tag/infoByUserAndPath?userOpenid=' + userOpenid + '&pagePathApply=' + pagePathApply,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const insertByEntity = function (entity) {
    return $.request({
        url: '/user-for-function-tag/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const updateByEntity = function (entity) {
    return $.request({
        url: '/user-for-function-tag/modify',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

module.exports = {
    loadEntityByUserAndPath: loadEntityByUserAndPath,
    insertByEntity: insertByEntity,
    updateByEntity: updateByEntity,
}