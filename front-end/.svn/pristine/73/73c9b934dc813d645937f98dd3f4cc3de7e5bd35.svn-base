const $ = require('../utils/request_util')

const loadList = function( ){
    return $.request({
        url:'/dim-security-situation/list',
        data:{},
        method: $.RequestMethod.GET,
        header: $.jsonHeader,
    })
}


module.exports = {
    loadList: loadList,
}