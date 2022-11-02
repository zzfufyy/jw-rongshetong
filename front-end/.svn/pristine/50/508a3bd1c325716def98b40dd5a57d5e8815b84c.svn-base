const $ = require('../utils/request_util')

function insertByList(list) {
    return $.request({
        url: '/form-subject-option/addList',
        data: list,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function listBySubjectUuidList(list) {
    return $.request({
        url: '/form-subject-option/listBySubjectUuidList',
        data: list,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}

function listChoiceSelectBySubjectUuid(subjectUuid){
    return $.request({
        url: '/form-subject-option/listChoiceSelectBySubjectUuid?subjectUuid='+ subjectUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}


module.exports = {
    insertByList: insertByList,
    listBySubjectUuidList: listBySubjectUuidList,
    listChoiceSelectBySubjectUuid: listChoiceSelectBySubjectUuid,
}