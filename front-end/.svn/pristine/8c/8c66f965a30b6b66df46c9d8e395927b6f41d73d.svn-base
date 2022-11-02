const $ = require('../utils/request_util');

const insertByEntity = function(entity){
    return $.request({
        url: '/bc-user-wx/add',
        data: entity,
        method: $.RequestMethod.POST,
        header: $.jsonHeader,
    })
}


module.exports = {
    insertByEntity: insertByEntity,
}