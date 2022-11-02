const $ = require('../utils/request_util')


const insertByEntity = function (entity) {
    return $.request({
        url: '/fair-for-company/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}
const isSign = function (fairUuid, recruiterOpenid) {
    return $.request({
        url: '/fair-for-company/isSign?fairUuid=' + fairUuid + '&recruiterOpenid=' + recruiterOpenid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const pageDTO = function (pagingParam) {
    return $.request({
        url: '/fair-for-company/pageDTO',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const pageJobDTO = function (pagingParam) {
    return $.request({
        url: '/fair-for-company/pageJobDTO',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

module.exports = {
    insertByEntity: insertByEntity,
    isSign: isSign,
    pageDTO: pageDTO,
    pageJobDTO: pageJobDTO,
}