const $ = require('../utils/request_util')


const loadEntityById = function (id) {
    return $.request({
        url: '/community-cell/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

const insertByEntity = function (communityCell) {
    return $.request({
        url: '/community-cell/add',
        data: communityCell,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};

const listByCommunityUuid = function (communityUuid) {
    return $.request({
        url: '/community-cell/listByCommunityUuid?communityUuid=' + communityUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
};


const listDTOByCommunityUuid = function (communityUuid) {
    return $.request({
        url: '/community-cell/listDTOByCommunityUuid?communityUuid=' + communityUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
};

const updateByRecorderOpenid = function (recorderOpenid, cellUuidList) {
    return $.request({
        url: '/community-cell/updateByRecorderOpenid?recorderOpenid=' + recorderOpenid,
        data: cellUuidList,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const loadEntityWithAssociatedById = function (id) {
    return $.request({
        url: '/community-cell/infoWithAssociated?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

const updateCascadeByEntity = function (entity) {
    return $.request({
        url: '/community-cell/modifyCascade',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}
const deleteCascadeById = function (id) {
    return $.request({
        url: '/community-cell/deleteCascade?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}


module.exports = {
    loadEntityById: loadEntityById,
    insertByEntity: insertByEntity,
    listByCommunityUuid: listByCommunityUuid,
    listDTOByCommunityUuid: listDTOByCommunityUuid,
    updateByRecorderOpenid: updateByRecorderOpenid,
    loadEntityWithAssociatedById: loadEntityWithAssociatedById,
    // 级联更新删除
    updateCascadeByEntity: updateCascadeByEntity,
    deleteCascadeById: deleteCascadeById,
}