const $ = require('../utils/request_util')

const insertByEntityList = function (residentUuid, list) {
    return $.request({
        url: '/resident-for-security-situation/addByEntityList?residentUuid=' + residentUuid,
        data: list,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};
const loadListByResidentUuid = function (residentUuid) {
    return $.request({
        url: '/resident-for-security-situation/listByResidentUuid?residentUuid=' + residentUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}
module.exports = {
    insertByEntityList: insertByEntityList,
    loadListByResidentUuid: loadListByResidentUuid,
}