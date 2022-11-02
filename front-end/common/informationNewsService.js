const $ = require('../utils/request_util')


const loadEntityById = function (id) {
    return $.request({
        url: '/information-news/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const updateByEntity = function (informationNews) {
    return $.request({
        url: '/information-news/modify',
        data: informationNews,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const insertByEntity = function (informationNews) {
    return $.request({
        url: '/information-news/add',
        data: informationNews,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const loadList = function () {
    return $.request({
        url: '/information-news/list',
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const loadListByCommunityUuid = function (communityUuid, num) {
    return $.request({
        url: '/information-news/listByCommunityUuid?communityUuid=' + communityUuid + '&num=' + num,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const pageByCondition = function (pagingParam) {
    return $.request({
        url: '/information-news/page',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}
const pageByArticleType = function (pagingParam) {
    return $.request({
        url: '/information-news/pageByArticleType',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const pageAllSocial = function (pagingParam) {
    return $.request({
        url: '/information-news/pageAllSocial',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const increaseCountView = function (id) {
    return $.request({
        url: '/information-news/increaseCountView?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

module.exports = {
    loadEntityById: loadEntityById,
    updateByEntity: updateByEntity,
    loadList: loadList,
    loadListByCommunityUuid: loadListByCommunityUuid,
    insertByEntity: insertByEntity,
    pageByCondition: pageByCondition,
    increaseCountView: increaseCountView,
    pageByArticleType: pageByArticleType,
    pageAllSocial: pageAllSocial,
}