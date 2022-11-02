const $ = require('../utils/request_util')

const insertByEntity = function (entity) {
    return $.request({
        url: '/social-training-apply/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    });
};



module.exports = {
    insertByEntity: insertByEntity,
    
}