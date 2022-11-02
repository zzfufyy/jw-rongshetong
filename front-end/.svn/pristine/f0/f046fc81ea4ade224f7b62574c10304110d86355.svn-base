const $ = require('../utils/request_util')


const loadList = function (categoryName) {
    return $.request({
        url: '/job-category/list',
        data: {
            categoryName: categoryName,
        },
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    })
}
const loadListByNumber = function (number, categoryName) {
    return $.request({
        url: '/job-category/listByNumber?number=' + number + '&categoryName=' + categoryName,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}


const insertByEntity = function (submitData) {
    return $.request({
        url: '/job-category/add',
        data: submitData,
        method: $.RequestMethod.POST,
        header: $.jsonHeader
    });
}



module.exports = {
    loadList: loadList,
    insertByEntity: insertByEntity,
    loadListByNumber: loadListByNumber,
}