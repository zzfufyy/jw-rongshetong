// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const userForFormService = require('../../common/userForFormService');
const string_util = require('../../utils/string_util');
const img_util = require('../../utils/img_util')
// 定义常量
const app = getApp();


const createPageMethods = () => ({

    _loadUserForFormList: async function (subjectUuid) {
        let queryCondition = {
            subjectUuid: subjectUuid,
            userOpenid: this.data.detailUserFormList[0].userOpenid,
        }
        let dataList = (await userForFormService.loadListByCondition(queryCondition)).data;
        console.log(dataList);
        return dataList;
    },

    // 填空题
    _loadUserCompletionData: async function (subjectUuid) {
        //  获取当前 数据详情 的填写用户openid
        let dataList = await this._loadUserForFormList(subjectUuid);
        return dataList;
    },
    // 选择题
    _loadUserChoiceSelectData: async function (subjectUuid) {
        //  获取当前 数据详情 的填写用户openid
        let queryCondition = {
            subjectUuid: subjectUuid,
            userOpenid: this.data.detailUserFormList[0].userOpenid,
        }
        let dataList = (await userForFormService.loadChoiceSelectListByCondition(queryCondition)).data;
        return dataList;
    },
    // 图片上传题
    _loadUserUploadImgData: async function (subjectUuid) {
        //  获取当前 数据详情 的填写用户openid
        let dataList = await this._loadUserForFormList(subjectUuid);
        dataList = dataList.map(v => {
            return {
                ...v,
                uploadImg: img_util.handleNormalPortraitPath(v.uploadImg),
            }
        })
        return dataList;
    },

    // 签名题
    _loadUserSignData: async function (subjectUuid) {
        //  获取当前 数据详情 的填写用户openid
        let dataList = await this._loadUserForFormList(subjectUuid);
        dataList = dataList.map(v => {
            return {
                ...v,
                uploadImg: img_util.handleNormalPortraitPath(v.uploadImg),
            }
        })
        return dataList;
    },
});

module.exports = {
    createPageMethods: createPageMethods,
}