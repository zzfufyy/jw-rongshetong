
const $ = require('../utils/request_util');

const insertByEntity = function (entity) {
    return $.request({
        url: '/information-collect-self-building/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const updateByEntity = function (entity) {
    return $.request({
        url: '/information-collect-self-building/modify',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}



module.exports = {
    insertByEntity: insertByEntity,
    updateByEntity: updateByEntity,
}