const $ = require('../utils/request_util')

const listByDefaultOrder = function (pagePathApply, communityUuid) {
    return $.request({
        url: '/function-tag/listByDefaultOrder?pagePathApply=' + pagePathApply + '&communityUuid=' + communityUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const listAll = function () {
    return $.request({
        url: '/function-tag/listAll',
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}


module.exports = {
    listAll: listAll,
    listByDefaultOrder: listByDefaultOrder
}