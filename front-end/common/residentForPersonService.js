const $ = require('../utils/request_util');
const { personTypeList } = require('./constant');

const insertByEntity = function (entity) {
    return $.request({
        url: '/resident-for-person/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const loadListByResidentUuid = function (residentUuid, personType) {
    // 单参数容错

    personType = typeof (personType) == "undefined" ? '' : personType;
    return $.request({
        url: '/resident-for-person/listByResidentUuid?residentUuid=' + residentUuid + '&personType=' + personType,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const deleteById = function (id) {
    return $.request({
        url: '/resident-for-person/delete?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const loadEntityById = function (id) {
    return $.request({
        url: '/resident-for-person/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}
const updateByEntity = function (entity) {
    return $.request({
        url: '/resident-for-person/modify',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

module.exports = {
    insertByEntity: insertByEntity,
    loadListByResidentUuid: loadListByResidentUuid,
    deleteById: deleteById,
    loadEntityById: loadEntityById,
    updateByEntity: updateByEntity,

}