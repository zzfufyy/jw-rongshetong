const $ = require('../utils/request_util')

const app = getApp();
const loadList = function () {
    return $.request({
        url: '/community-information/list',
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const loadEntityById = function (id) {
    return $.request({
        url: '/community-information/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const updateByEntity = function (updateData) {
    return $.request({
        url: '/community-information/modify',
        data: updateData,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    });
}

const uploadPortrait = function (communityUuid, filePath) {
    return $.upload({
        url: '/community-information/uploadPortrait?id=' + communityUuid,
        filePath: filePath,
        formData: {},
        name: 'file',
        header: app.globalData.header,
    });
}

const listDistrictNameByCityName = function (cityName) {
    if(typeof(cityName) == 'undefined'){
        cityName = '';
    }
    return $.request({
        url: '/community-information/listDistrictNameByCityName?cityName=' + cityName,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const listStreetNameByDistrictName = function (districtName) {
    return $.request({
        url: '/community-information/listStreetNameByDistrictName?districtName=' + districtName,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const listByStreetName = function (streetName) {
    return $.request({
        url: '/community-information/listByStreetName?streetName=' + streetName,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}
module.exports = {
    loadList: loadList,
    loadEntityById: loadEntityById,
    updateByEntity: updateByEntity,
    uploadPortrait: uploadPortrait,
    listDistrictNameByCityName: listDistrictNameByCityName,
    listStreetNameByDistrictName: listStreetNameByDistrictName,
    listByStreetName: listByStreetName,

}