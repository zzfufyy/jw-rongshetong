
const CONSTANT = require('../common/constant');

function formatRoomNum(whichLayer, whichLayerFamily) {
    let tempWhichLayer = whichLayer + "";
    tempWhichLayer = (tempWhichLayer.length == 1) ? "0" + tempWhichLayer : tempWhichLayer;
    let tempWhichLayerFamily = whichLayerFamily + "";
    tempWhichLayerFamily = (tempWhichLayerFamily.length == 1) ? "0" + tempWhichLayerFamily : tempWhichLayerFamily;
    return tempWhichLayer + tempWhichLayerFamily;
}

function formatDistance(distance) {
    return distance == CONSTANT.UNKNOWN_DISTANCE ? '未知距离' : (distance / 1000).toFixed(1) + '公里';
}

function formatRatioFix2(originNum) {
    return originNum == 0 ? '0%' : (originNum * 100).toFixed(2) + '%';
}

module.exports = {
    formatRoomNum: formatRoomNum,
    formatDistance: formatDistance,
    formatRatioFix2: formatRatioFix2,
}