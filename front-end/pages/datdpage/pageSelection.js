// 加载 工具类
const Paging = require('../../utils/paging_util');
const date_util = require('../../utils/date_util');
// 加载服务类
const userForFormService = require('../../common/userForFormService');
const string_util = require('../../utils/string_util');
const format_util = require('../../utils/format_util');
const formSubjectOptionService = require('../../common/formSubjectOptionService');
// 定义常量
const app = getApp();


const createPageMethods = () => ({

    _buildChoiceSelectInit: async function (subjectUuid) {
        let originDataList = await this._loadChoiceSelect(subjectUuid);
        console.log(originDataList);
        // 获取总 选择数量
        let totalOptionNum = 0;
        let totalUserNum = 0;
        originDataList.forEach(v => {
            totalOptionNum += v.countOptionNum;
            totalUserNum < v.countUserNum ? totalUserNum = v.countUserNum : totalUserNum;
        });
        return {
            dataList: originDataList.map(v => {
                return {
                    ...v,
                    countOptionNumRatio: totalOptionNum == 0 ? '-' : format_util.formatRatioFix2(v.countOptionNum / totalOptionNum),
                    totalUserNum: totalUserNum,
                }
            }),
        }
    },
    _loadChoiceSelect: async function (subjectUuid) {
        // 
        let choiceSelectData = (await formSubjectOptionService.listChoiceSelectBySubjectUuid(subjectUuid)).data;
        return choiceSelectData
    }
});

module.exports = {
    createPageMethods: createPageMethods,
}