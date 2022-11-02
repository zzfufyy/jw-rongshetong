const turnListToString = function (list) {
    let tmplist = list.filter(v => {
        return v.checked;
    });
    tmplist = tmplist.map(v => {
        return v.name;
    });
    return tmplist.join(";");
};

const houseArea = [
    { name: '城镇建成区', checked: false },
    { name: '城乡结合部', checked: false },
    { name: '城中村', checked: false },
    { name: '拆迁安置区', checked: false },
    { name: '建制镇', checked: false },
    { name: '集镇', checked: false },
    { name: '学校周边', checked: false },
    { name: '医院周边', checked: false },
    { name: '批发市场周边', checked: false },
    { name: '景区及周边', checked: false },
    { name: '其他', checked: false },
];
const housePropertyRight = [
    { name: '产权人', checked: false },
    { name: '实际拥有人', checked: false },
];

const houseUsage = [
    { name: '自有住宅', checked: false },
    { name: '小产权房', checked: false },
];
const isBusiness = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const businessScope = [
    { name: '餐饮饭店', checked: false },
    { name: '民宿宾馆', checked: false },
    { name: '批发零售', checked: false },
    { name: '休闲娱乐', checked: false },
    { name: '教育设施', checked: false },
    { name: '医疗卫生', checked: false },
    { name: '养老服务', checked: false },
    { name: '生产加工', checked: false },
    { name: '仓储物流', checked: false },
    { name: '出租居住', checked: false },
    { name: '其他', checked: false },
];
const isAuthenticateReport = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const houseLandProperty = [
    { name: '国有土地', checked: false },
    { name: '集体土地', checked: false },
];
const countryLandUsage = [
    { name: '居住用地', checked: false },
    { name: '商住用地', checked: false },
    { name: '商业用地', checked: false },
    { name: '国有林场、种场、渔场等用地', checked: false },
];
const collectiveLandUsage = [
    { name: '宅基地', checked: false },
    { name: '集体建设用地', checked: false },
];

// --------------------------第二页
const designMode = [
    { name: '有专业设计', checked: false },
    { name: '采用标准化图集 ', checked: false },
    { name: '无专业设计、未采用标准图集', checked: false },
];
const constructionTeam = [
    { name: '自行建造', checked: false },
    { name: '建筑工匠建造', checked: false },
    { name: '有资质的施工队伍建造', checked: false },
];
const structureType = [
    { name: '砌体结构', checked: false },
    { name: '底部框架-抗震墙上部砌体结构', checked: false },
    { name: '钢筋混凝土结构', checked: false },
    { name: '钢结构', checked: false },
    { name: '木（竹）结构', checked: false },
    { name: '土木/石木结构', checked: false },
    { name: '混杂结构', checked: false },
    { name: '其他', checked: false },
];
const wallMaterial = [
    { name: '红砖', checked: false },
    { name: '水泥砌块', checked: false },
    { name: '石块', checked: false },
    { name: '其他', checked: false },
];
const floorType = [
    { name: '木楼板', checked: false },
    { name: '预制板', checked: false },
    { name: '现浇板', checked: false },
];
const roofType = [
    { name: '木屋盖', checked: false },
    { name: '预制板', checked: false },
    { name: '现浇板', checked: false },
];
const aseismaticStructure = [
    { name: '基础地圈梁', checked: false },
    { name: '构造柱', checked: false },
    { name: '圈梁', checked: false },
];
const isExpansion = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];

const expansionContent = [
    { name: '楼顶加层', checked: false },
    { name: '周边扩建（建筑外扩）', checked: false },
    { name: '楼内建夹层', checked: false },
    { name: '室内加隔墙', checked: false },
    { name: '楼体抬高', checked: false },
    { name: '扩大门窗', checked: false },
    { name: '加建地下室', checked: false },
    { name: '减柱减隔墙', checked: false },
    { name: '其他', checked: false },
];
const isChangeStructure = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];


//-------------------  第三页
const isProcedure = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const procedureType = [
    { name: '建设用地规划许可证', checked: false },
    { name: '国有土地使用权证', checked: false },
    { name: '集体建设用地使用权证', checked: false },
    { name: '竣工验收手续', checked: false },
    { name: '消防竣工验收手续', checked: false },
    { name: '施工许可证', checked: false },
    { name: '宅基地批准书', checked: false },
    { name: '乡村建设规划许可证', checked: false },
    { name: '不动产登记证', checked: false },
    { name: '公共聚集场所营业前消防行政许可', checked: false },
    { name: '市场主体登记（营业执照）', checked: false },
    { name: '经营许可证', checked: false },

];
const isIllegal = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const illegalType = [
    { name: '违法占地', checked: false },
    { name: '违法建设', checked: false },
    { name: '违法改扩建', checked: false },
    { name: '违规经营', checked: false },
];

// ----------------第四页
const isGeologyDanger = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const geologyDangerType = [
    { name: '切坡建房', checked: false },
    { name: '滑坡泥石流', checked: false },
    { name: '地下采空区', checked: false },
    { name: '洪涝', checked: false },
    { name: '山体崩塌', checked: false },
    { name: '地面沉降', checked: false },
    { name: '其他', checked: false },
];
const estimateStructureDanger = [
    { name: '基本安全', checked: false },
    { name: '存在安全隐患', checked: false },
];
const structureDangerPart = [
    { name: '墙体', checked: false },
    { name: '梁柱', checked: false },
    { name: '地基', checked: false },
    { name: '屋面', checked: false },
    { name: '楼板', checked: false },
    { name: '其他', checked: false },
];
const isSecurityAuthenticate = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const securityAuthenticateResult = [
    { name: 'A级', checked: false },
    { name: 'B级', checked: false },
    { name: 'C级', checked: false },
    { name: 'D级', checked: false },
];
const authenticateResultPart = [
    { name: '墙体', checked: false },
    { name: '梁柱', checked: false },
    { name: '地基', checked: false },
    { name: '屋面', checked: false },
    { name: '楼板', checked: false },
    { name: '其他', checked: false },
];
const isFireSafety = [
    { name: '是', checked: false },
    { name: '否', checked: false },
];
const fireSafetyDanger = [
    { name: '消防车道严重堵塞', checked: false },
    { name: '疏散通道严重堵塞', checked: false },
    { name: '消防设施和器材缺失或破坏', checked: false },
    { name: '道路狭窄消防车无法通行', checked: false },
    { name: '发生过电路短路', checked: false },
    { name: '电路敷设不规范', checked: false },
    { name: '易燃材料', checked: false },
    { name: '疏散楼梯', checked: false },
    { name: '消火栓', checked: false },
    { name: '喷淋', checked: false },
    { name: '灭火器', checked: false },
    { name: '应急照明', checked: false },
    { name: '烟感其他', checked: false },
];

// -------------- 第五页
const managementMeasure = [
    { name: '停止经营', checked: false },
    { name: '封存警示', checked: false },
    { name: '人员搬离', checked: false },
    { name: '其他', checked: false },
];
const projectMeasure = [
    { name: '拆除', checked: false },
    { name: '重建', checked: false },
    { name: '维修加固', checked: false },
    { name: '其他', checked: false },
];
const illegalMeasure = [
    { name: '拆除', checked: false },
    { name: '行政处罚', checked: false },
    { name: '其他', checked: false },
];



module.exports = {
    turnListToString: turnListToString,
    // 第一页
    houseArea: houseArea,
    housePropertyRight: housePropertyRight,
    houseUsage: houseUsage,
    isBusiness: isBusiness,
    businessScope: businessScope,
    isAuthenticateReport: isAuthenticateReport,
    houseLandProperty: houseLandProperty,
    countryLandUsage: countryLandUsage,
    collectiveLandUsage: collectiveLandUsage,

    // 第二页
    designMode: designMode,
    constructionTeam: constructionTeam,
    structureType: structureType,
    wallMaterial: wallMaterial,
    floorType: floorType,
    roofType: roofType,
    aseismaticStructure: aseismaticStructure,
    isExpansion: isExpansion,
    expansionContent: expansionContent,
    isChangeStructure: isChangeStructure,

    // 第三页
    isProcedure: isProcedure,
    procedureType: procedureType,
    isIllegal: isIllegal,
    illegalType: illegalType,

    // 第四页
    isGeologyDanger: isGeologyDanger,
    geologyDangerType: geologyDangerType,
    estimateStructureDanger: estimateStructureDanger,
    structureDangerPart: structureDangerPart,
    isSecurityAuthenticate: isSecurityAuthenticate,
    securityAuthenticateResult: securityAuthenticateResult,
    authenticateResultPart: authenticateResultPart,
    isFireSafety: isFireSafety,
    fireSafetyDanger: fireSafetyDanger,

    // 第五页
    managementMeasure: managementMeasure,
    projectMeasure: projectMeasure,
    illegalMeasure: illegalMeasure,
}