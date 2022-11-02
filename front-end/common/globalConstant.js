//  全局变量

// 全局用户身份
const GLOBAL_USER_IDENTITY = 'GLOBAL_USER_IDENTITY'; // 'user' 'company' 'vistor'
// 全局openid
const GLOBAL_OPENID = 'openid';
// 全局 communityUuid;
const GLOBAL_COMMUNITYUUID = 'GLOBAL_COMMUNITYUUID';

const GLOBAL_INFROMATION_COLLECT_SUBMIT = 'GLOBAL_INFROMATION_COLLECT_SUBMIT';

// 全局  社区服务 从网格录入进 还是 社区管理进
const GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY = 'GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY';

// 根据user_state 表 userRole 输出当前角色识别
const userStateNameList = [
    'vistor',
    'user',
    'company',

]



// 全局监听
const LISTENABLE_SEND_RESUME = 'LISTENABLE_SEND_RESUME';


module.exports = {
    GLOBAL_USER_IDENTITY: GLOBAL_USER_IDENTITY,
    GLOBAL_OPENID: GLOBAL_OPENID,
    GLOBAL_COMMUNITYUUID: GLOBAL_COMMUNITYUUID,

    GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY: GLOBAL_FLAG_ENTRY_GRID_OR_COMMUNITY,

    GLOBAL_INFROMATION_COLLECT_SUBMIT: GLOBAL_INFROMATION_COLLECT_SUBMIT,
    // 全局监听器
    LISTENABLE_SEND_RESUME: LISTENABLE_SEND_RESUME,
    userStateNameList: userStateNameList,
}