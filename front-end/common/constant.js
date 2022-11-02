const STATUS = {
    NORMAL: 0,
    DELETED: -1,
}
// 与后台mapper.xml保持一致 当为10000 时，显示 '未知距离'
const UNKNOWN_DISTANCE = 10000 * 1000;

const STATIC_IMG_URL = {
    portrait_company: '../../img/portrait/company_portrait.png',
    portrait_personal_male: '../../img/portrait/personal_portrait_male2x.svg',
    portrait_personal_female: '../../img/portrait/personal_portrait_female2x.svg',
    portrait_news: '../../img/portrait/news_portrait.svg',
    portrait_community: '../../img/portrait/community_portrait.svg',
    portrait_community_building: '../../img/social-building-empoyment/building_default.svg',
}
// 排序方法
const ORDER_TYPE = {
    DISTANCE: 0,
    CREATE_TIME: 1,
}

// 招聘流程
const FLOW_RECRUIT = {
    NORMAL: 0, // 未反馈 未沟通
    READ: 1, // 已查看
    INTERVIEW: 2, // 待面试
    THROUGH: 3, // 面试通过
    UNSUITABLE: -1,
}
// 招聘结果
const FLOW_RESULT = {
    NORMAL: 0, // 未反馈 未沟通
    SUCCESS: 1,
    FAIL: -1,

}

// 社区用户权限级别
const AUTHORIZATION_LEVEL = {
    NORMAL: 0,
    COMMUNITY_ADMIN: 1,
    COMMUNITY_RECORDER: 2,
    ALL_ADMIN: 3,
}



// 楼栋命名风格 0: 数字 1: 英文
const BUILDING_NAME_TYPE = [{
        id: 0,
        name: '阿拉伯数字',
    },
    {
        id: 1,
        name: '英文字母'
    }
]
// 社区  作用城市
const CURRENT_CITY = '长沙市';



// 年龄类：
class Age {
    constructor(min, max) {
        this.min = min;
        this.max = max;
        if (min <= 0 && max <= 0) {
            this.value = '年龄不限';
        } else if (min <= 0 && max > 0) {
            this.value = `${max}以下`;
        } else if (min > 0 && max <= 0) {
            this.value = `${min}以上`;
        } else {
            this.value = `${min} - ${max}`;
        }
    }
}
const ageList = [
    new Age(0, 0),
    new Age(20, 30),
    new Age(30, 40),
    new Age(40, 50),
    new Age(50, 60),
]

// 薪水类
class Salary {
    constructor(min, max) {
        this.min = min;
        this.max = max;
        if (min < 0 && max < 0) {
            this.value = '不限'
        } else if (min == 0 && max == 0) {
            this.value = '薪资面议';
        } else if (min == 0 && max > 0) {
            this.value = `${max}以下元/月`;
        } else if (min > 0 && max == 0) {
            this.value = `${min}以上元/月`;
        } else if (min > 0 && max > 0) {
            this.value = `${min}-${max}元/月`;
        }

    }
}
const salaryList = [
    new Salary(0, 0),
    new Salary(0, 3000),
    new Salary(3000, 5000),
    new Salary(5000, 8000),
    new Salary(8000, 10000),
    new Salary(10000, 0),
];



const genderList = [
    '男', '女',
]

const userRoleValue = {
    Vistor: 0,
    Recruitee: 1,
    Recruiter: 2,
}


const UserRole = {
    // 游客
    Vistor: 'vistor',
    // 求职者
    Recruitee: 'user',
    // 招聘者
    Recruiter: 'company',
}

const userRoleName = new Map([
    ['vistor', '游客'],
    ['user', '求职者'],
    ['company', '招聘方'],
]);

// 特殊人群选项
const personSpecialList = [
    '请选择',
    '高龄',
    '儿童',
    '军人',
    '特殊人群'
];

// 居住人类型选项
const personTypeList = [
    '居住人员', // 默认
    '从业人员',
];

const defaultLocation = {
    latitude: 0,
    longitude: 0,
}

// 信息采集 市辖区 权限区域
const INFO_COLLECT_PERMISSION_LIST = [
    '茶陵县'
];
const tempChalingList = [
    '6a0f6a5e-d0d5-11ec-8a81-525401030264',
    '6a1a3f57-d0d5-11ec-8a81-525401030264',
    '6a24e45f-d0d5-11ec-8a81-525401030264',
    '6a2ea551-d0d5-11ec-8a81-525401030264',
    '6a37c12a-d0d5-11ec-8a81-525401030264',
    '6a40b91f-d0d5-11ec-8a81-525401030264',
    '6a49b81b-d0d5-11ec-8a81-525401030264',
    '6a5381c2-d0d5-11ec-8a81-525401030264',
    '6a5e281c-d0d5-11ec-8a81-525401030264',
    '6a68371b-d0d5-11ec-8a81-525401030264',
    '6a723bc3-d0d5-11ec-8a81-525401030264',
    '6a7d12bb-d0d5-11ec-8a81-525401030264',
    '6a86ab26-d0d5-11ec-8a81-525401030264',
    '6a90caf2-d0d5-11ec-8a81-525401030264',
    '6a9aae5b-d0d5-11ec-8a81-525401030264',
    '6aa3fd91-d0d5-11ec-8a81-525401030264',
    '6aacafb7-d0d5-11ec-8a81-525401030264',
    '6ab79339-d0d5-11ec-8a81-525401030264',
    '6ac0d75c-d0d5-11ec-8a81-525401030264',
    '6acb2de6-d0d5-11ec-8a81-525401030264',
];

const TMP_IMG_PREFIX = 'http://tmp/';

// 题目类型
const FORM_SUBJECT_TYPE = {
    COMPLETION: 1,
    SINGLE_SELECT: 2,
    MULTIPLE_SELECT: 3,
    IMG_UPLOAD: 4,
    SIGN: 5,
}
// 表单组件类型
const FORM_COMPONENT_TYPE = {
    COMPLETION: 1,
    CHOICE_SELECT: 2,
    // 3是多选 并入2
    IMG_UPLOAD: 4,
    SIGN: 5,
}
// 社区类型
const COMMUNITY_TYPE = {
    COMMUNITY: 0,
    SCHOOL: 1,
}

//
const FUNCTION_TAG_LIST_SCHOOL = [
    'tagRecruitCompany',
    'tagRecruitCandidate',
    'tagRecruitFair',
    'tagPersonalCenterSchool',

]

// 面试进程
const INTERVIEW_FLOW = {
    INTERVIEW_FAIL: -1,
    UNCHECK: 0,
    CHECK: 1,
    WAIT_FOR_INTERVIEW: 2,
    INTERVIEW_SUCCESS: 3
}

// 工作类型
const JOB_TYPE = {
    FULL_TIME: 0,
    INTERNSHIP: 1,
}

// 公司认证情况
const COMPANY_IDENTIFICATION = {
    SUCCESS: 1,
    NORMAL: 0,
    FAIL: -1,
}

// 蓉社通 - 新闻分类
const SOCIAL_INFORMATION_NEWS_TYPE = {
    socialDynamicalNews: {
        id: 21,
        name: "动态",
    },
    socialSecurityNews: {
        id: 22,
        name: "社保",
    },
    socialEmploymentNews: {
        id: 23,
        name: "就业",
    },
    socialPersonnelNews: {
        id: 24,
        name: "人事",
    },
    socialMonitorNews: {
        id: 25,
        name: "监察",
    },
    socialAnnouncementNews: {
        id: 26,
        name: "公告",
    },
    socialPolicyNews: {
        id: 27,
        name: "相关政策",
    },
    socialIncubationNews: {
        id: 28,
        name: "创业孵化",
    },
    socialPersonnelExamNews: {
        id: 29,
        name: "人事考试",
    },
    socialTalentNews: {
        id: 30,
        name: "人才",
    },
}

// 芙蓉区人社局视频号
const SOCIAL_LIVE_ID = "sphYBYkHOHdkmQM";




module.exports = {
    // 常量
    STATUS: STATUS,
    FLOW_RECRUIT: FLOW_RECRUIT,
    FLOW_RESULT: FLOW_RESULT,
    AUTHORIZATION_LEVEL: AUTHORIZATION_LEVEL,
    BUILDING_NAME_TYPE: BUILDING_NAME_TYPE,
    CURRENT_CITY: CURRENT_CITY,
    UNKNOWN_DISTANCE: UNKNOWN_DISTANCE,
    STATIC_IMG_URL: STATIC_IMG_URL,
    ORDER_TYPE: ORDER_TYPE,
    TMP_IMG_PREFIX: TMP_IMG_PREFIX,
    COMMUNITY_TYPE: COMMUNITY_TYPE, // 社区类型 0 社区 1 学校
    FUNCTION_TAG_LIST_SCHOOL: FUNCTION_TAG_LIST_SCHOOL, // 学校功能列表
    INTERVIEW_FLOW: INTERVIEW_FLOW,
    JOB_TYPE: JOB_TYPE,
    COMPANY_IDENTIFICATION: COMPANY_IDENTIFICATION,
    // 类加载
    Age: Age,
    Salary: Salary,

    // 列表
    ageList: ageList,
    salaryList: salaryList,
    userRoleName: userRoleName,
    UserRole: UserRole,
    userRoleValue: userRoleValue,
    genderList: genderList,
    defaultLocation: defaultLocation,
    personSpecialList: personSpecialList,
    personTypeList: personTypeList,

    // 临时常量
    INFO_COLLECT_PERMISSION_LIST: INFO_COLLECT_PERMISSION_LIST,
    tempChalingList: tempChalingList,

    // 表单常量
    FORM_SUBJECT_TYPE: FORM_SUBJECT_TYPE,
    FORM_COMPONENT_TYPE: FORM_COMPONENT_TYPE,

    //
    SOCIAL_INFORMATION_NEWS_TYPE: SOCIAL_INFORMATION_NEWS_TYPE,

    // 蓉社通 - 视频直播好id
    SOCIAL_LIVE_ID:SOCIAL_LIVE_ID,
}