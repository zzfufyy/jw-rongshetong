const $ = require('../utils/request_util')


const insertByEntity = function (entity) {
    return $.request({
        url: '/fair-for-candidate/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}
const isSign = function (fairUuid, candidateOpenid) {
    return $.request({
        url: '/fair-for-candidate/isSign?fairUuid=' + fairUuid + '&candidateOpenid=' + candidateOpenid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}
const pageDTO = function (pagingParam) {
    return $.request({
        url: '/fair-for-candidate/pageDTO',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

module.exports = {
    insertByEntity: insertByEntity,
    isSign: isSign,
    pageDTO: pageDTO,
}