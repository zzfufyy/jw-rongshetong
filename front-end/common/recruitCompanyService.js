const $ = require('../utils/request_util')


const loadEntityById = function (id) {
    return $.request({
        url: '/recruit-company/info?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader
    })
}

const updateRecruitCompany = function (recruitCompany) {
    return $.request({
        url: '/recruit-company/modify',
        data: recruitCompany,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}

const insertByEntity = function (openid, recruitCompany) {
    return $.request({
        url: '/recruit-company/add?openid=' + openid,
        data: recruitCompany,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};

const pagedByCondition = function (pagingParam) {
    return $.request({
        url: '/recruit-company/pagedByCondition',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}


const countByCommunityUuid = function (communityUuid) {
    return $.request({
        url: '/recruit-company/countByCommunityUuid?communityUuid=' + communityUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
}

const infoWithAssociated = function (id) {
    return $.request({
        url: '/recruit-company/infoWithAssociated?id=' + id,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}

const infoByRecruiterPhone = function (telephone) {
    return $.request({
        url: `/recruit-company/infoByRecruiterPhone?telephone=${telephone}`,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })

}

const pageJobNameList = function(pagingParam){
    return $.request({
        url: '/recruit-company/pageJobNameList',
        data: pagingParam,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
}




module.exports = {
    loadEntityById: loadEntityById,
    updateRecruitCompany: updateRecruitCompany,
    insertByEntity: insertByEntity,

    pagedByCondition: pagedByCondition,
    countByCommunityUuid: countByCommunityUuid,
    infoWithAssociated: infoWithAssociated,

    infoByRecruiterPhone: infoByRecruiterPhone,

    pageJobNameList:pageJobNameList,
}