const $ = require('../utils/request_util')


const insertByEntity = function (insertData) {
    return $.request({
        url: '/user-community/add',
        data: insertData,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}


const loadEntityById = function (id) {
    return $.request({
        url: '/user-community/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const updateByEntity = function (updateData) {
    return $.request({
        url: '/user-community/modify',
        data: updateData,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    });
}

const loadRecorderListByCommunityUuid = function (communityUuid) {
    return $.request({
        url: '/user-community/loadRecorderListByCommunityUuid?communityUuid=' + communityUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}


module.exports = {
    insertByEntity: insertByEntity,
    loadEntityById: loadEntityById,
    updateByEntity: updateByEntity,
    loadRecorderListByCommunityUuid: loadRecorderListByCommunityUuid
}