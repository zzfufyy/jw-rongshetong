const $ = require('../utils/request_util')

// 根据实体类 list插入
const insertByEntityList = function (companyUuid, list) {
    return $.request({
        url: '/company-for-category/add?companyUuid=' + companyUuid,
        data: list,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};

const loadListByCandidateOpenid = function (candidateOpenid){
    return $.request({
        url: '/candidate-for-community/listByCandidateOpenid?candidateOpenid=' + candidateOpenid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}


module.exports = {
    insertByEntityList: insertByEntityList,
    loadListByCandidateOpenid: loadListByCandidateOpenid,
}