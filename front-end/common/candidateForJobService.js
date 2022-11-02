const $ = require('../utils/request_util')

const insertFlagCall = function (candidateOpenid, jobUuid ){
    return $.request({
        url: '/cand-job/add?candidateOpenid=' + candidateOpenid +'&jobUuid=' + jobUuid,
        data: {},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    });
};


module.exports = {
    insertFlagCall: insertFlagCall,
}