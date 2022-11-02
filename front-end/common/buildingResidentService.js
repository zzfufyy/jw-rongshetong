const $ = require('../utils/request_util')

const loadListByBuildingUuid = function (buildingUuid) {
    return $.request({
        url: '/building-resident/loadListByBuildingUuid?buildingUuid=' + buildingUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}
const loadEntityById = function (id) {
    return $.request({
        url: '/building-resident/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

const loadEntityWithAssociatedById = function (id) {
    return $.request({
        url: '/building-resident/infoWithAssociated?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}


const updateByEntity = function (buildingResident) {
    return $.request({
        url: '/building-resident/modify',
        data: buildingResident,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};


const uploadPortrait = function (id, filePath) {
    return $.upload({
        url: '/building-resident/uploadPortrait?id=' + id,
        filePath: filePath,
        formData: {},
        name: 'file',
        header: $.jsonHeader,
    });
}


module.exports = {
    loadListByBuildingUuid: loadListByBuildingUuid,
    loadEntityById: loadEntityById,
    loadEntityWithAssociatedById: loadEntityWithAssociatedById,
    updateByEntity: updateByEntity,
    uploadPortrait: uploadPortrait,
}