const $ = require('../utils/request_util')


const insertByEntity = function (insertData) {
    return $.request({
        url: '/user-state/add',
        data: insertData,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const loadEntityById = function (id) {
    return $.request({
        url: '/user-state/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const updateByEntity = function (entity) {
    return $.request({
        url: '/user-state/modify',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

module.exports = {
    insertByEntity: insertByEntity,
    loadEntityById: loadEntityById,
    updateByEntity: updateByEntity,

}