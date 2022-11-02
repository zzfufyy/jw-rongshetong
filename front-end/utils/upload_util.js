const string_util = require('./string_util');

const app= getApp();

const isUploadPathLegal = function(path){
    return (string_util.isEmpty(path) || path.includes(app.globalData.web_path))? false: true;
}
const isUploadPathLegals = function(path){
  return path.includes(app.globalData.web_path)? true:false ;
}

module.exports = {
    isUploadPathLegal: isUploadPathLegal,
    isUploadPathLegals: isUploadPathLegals,
}