const $ = require('../utils/request_util')

function insertByEntity(entity) {
    return $.request({
        url: '/form-information/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function loadEntityById(id) {
    return $.request({
        url: '/form-information/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

function pageNormal(pagingParam) {
    return $.request({
        url: '/form-information/pageNormal',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function pageUnpublished(pagingParam) {
    return $.request({
        url: '/form-information/pageUnpublished',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function pagePublished(pagingParam) {
    return $.request({
        url: '/form-information/pagePublished',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}



function publish(id) {
    return $.request({
        url: '/form-information/publish?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

function unpublish(id) {
    return $.request({
        url: '/form-information/unpublish?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

function loadInfoWithFilled(id) {
    return $.request({
        url: '/form-information/infoWithFilled?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

module.exports = {
    loadEntityById: loadEntityById,
    insertByEntity: insertByEntity,
    pageNormal: pageNormal,
    pageUnpublished: pageUnpublished,
    pagePublished: pagePublished,
    publish: publish,
    unpublish: unpublish,
    loadInfoWithFilled: loadInfoWithFilled,
}