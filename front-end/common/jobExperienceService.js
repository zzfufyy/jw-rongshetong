const $ = require('../utils/request_util');

const loadEntityById = function (id) {
    return $.request({
        url: '/job-experience/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const insertByEntity = function (entity) {
    return $.request({
        url: '/job-experience/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const listByCondition = function (condition) {
    return $.request({
        url: '/job-experience/listByCondition',
        data: condition,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}

const deleteById = function (id) {
    return $.request({
        url: `/job-experience/deleteById?id=${id}`,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

module.exports = {
    loadEntityById: loadEntityById,
    insertByEntity: insertByEntity,
    listByCondition: listByCondition,
    deleteById: deleteById,
}