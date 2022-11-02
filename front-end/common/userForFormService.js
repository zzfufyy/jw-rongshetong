const $ = require('../utils/request_util');

const insertByList = function (insertList) {
    return $.request({
        url: '/user-for-form/addList',
        data: insertList,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const page = function (pagingParam) {
    return $.request({
        url: '/user-for-form/page',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

const pageForUploadImg = function (pagingParam) {
    return $.request({
        url: '/user-for-form/pageForUploadImg',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

const pageForSign = function (pagingParam) {
    return $.request({
        url: '/user-for-form/pageForSign',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}


function pageDetailUserForm(pagingParam) {
    return $.request({
        url: '/user-for-form/pageDetailUserForm',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function loadListByCondition(condition) {
    return $.request({
        url: '/user-for-form/loadListByCondition',
        data: condition,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function loadChoiceSelectListByCondition(condition) {
    return $.request({
        url: '/user-for-form/loadChoiceSelectListByCondition',
        data: condition,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function countByCondition(condition) {
    return $.request({
        url: '/user-for-form/countByCondition',
        data: condition,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}




module.exports = {
    page: page,
    insertByList: insertByList,
    pageForUploadImg: pageForUploadImg,
    pageForSign: pageForSign,
    pageDetailUserForm: pageDetailUserForm,
    loadListByCondition: loadListByCondition,
    loadChoiceSelectListByCondition: loadChoiceSelectListByCondition,
    countByCondition: countByCondition,
}