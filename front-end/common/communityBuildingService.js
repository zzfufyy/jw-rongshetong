const $ = require('../utils/request_util')

const page = function (pagingParam) {
    return $.request({
        url: '/community-building/page',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}
const pageStreet = function (pagingParam) {
    return $.request({
        url: '/community-building/pageStreet',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

module.exports = {
    page: page,
    pageStreet: pageStreet,
}