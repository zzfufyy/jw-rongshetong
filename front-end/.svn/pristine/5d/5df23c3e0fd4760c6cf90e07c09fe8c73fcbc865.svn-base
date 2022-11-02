const $ = require('../utils/request_util')


const page = function (pagingParam) {
    return $.request({
        url: '/job-fair/page',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}
const loadEntityById = function (id) {
    return $.request({
        url: '/job-fair/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

module.exports = {
    page: page,
    loadEntityById: loadEntityById,
}