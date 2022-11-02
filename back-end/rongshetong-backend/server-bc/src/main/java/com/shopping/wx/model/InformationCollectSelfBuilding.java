package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "information_collect_self_building")
public class InformationCollectSelfBuilding {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 乡
     */
    private String villiage;

    /**
     * 村
     */
    private String community;

    /**
     * 组
     */
    @Column(name = "villiage_group")
    private String villiageGroup;

    /**
     * 路
     */
    private String road;

    /**
     * 号
     */
    @Column(name = "house_no")
    private String houseNo;

    /**
     * 所在区域
     */
    @Column(name = "house_area")
    private String houseArea;

    /**
     * 产权类型
     */
    @Column(name = "house_property_right")
    private String housePropertyRight;

    /**
     * 户主姓名
     */
    @Column(name = "householder_name")
    private String householderName;

    /**
     * 身份证
     */
    @Column(name = "householder_idcard")
    private String householderIdcard;

    /**
     * 居住人口数
     */
    @Column(name = "house_living_number")
    private String houseLivingNumber;

    /**
     * 房屋用途
     */
    @Column(name = "house_usage")
    private String houseUsage;

    /**
     * 是否用作经营用途
     */
    @Column(name = "is_business")
    private String isBusiness;

    /**
     * 经营业态种类
     */
    @Column(name = "business_scope")
    private String businessScope;

    /**
     * 是否出具专业鉴定报告
     */
    @Column(name = "is_authenticate_report")
    private String isAuthenticateReport;

    /**
     * 土地性质
     */
    @Column(name = "house_land_property")
    private String houseLandProperty;

    /**
     * 国有土地用途
     */
    @Column(name = "country_land_usage")
    private String countryLandUsage;

    /**
     * 集体土地用途
     */
    @Column(name = "collective_land_usage")
    private String collectiveLandUsage;

    /**
     * 房屋主体层数
     */
    @Column(name = "house_main_layer")
    private String houseMainLayer;

    /**
     * 房屋局部层数
     */
    @Column(name = "house_part_layer")
    private String housePartLayer;

    /**
     * 房屋建筑面积
     */
    @Column(name = "house_acerage")
    private String houseAcerage;

    /**
     * 房屋高度
     */
    @Column(name = "house_height")
    private String houseHeight;

    /**
     * 房屋建成时间（年）
     */
    @Column(name = "house_year")
    private String houseYear;

    /**
     * 设计方式
     */
    @Column(name = "design_mode")
    private String designMode;

    /**
     * 施工队伍
     */
    @Column(name = "construction_team")
    private String constructionTeam;

    /**
     * 结构类型
     */
    @Column(name = "structure_type")
    private String structureType;

    /**
     * 墙体材料
     */
    @Column(name = "wall_material")
    private String wallMaterial;

    /**
     * 楼板类型
     */
    @Column(name = "floor_type")
    private String floorType;

    /**
     * 屋盖类型
     */
    @Column(name = "roof_type")
    private String roofType;

    /**
     * 抗震构造
     */
    @Column(name = "aseismatic_structure")
    private String aseismaticStructure;

    /**
     * 是否扩建
     */
    @Column(name = "is_expansion")
    private String isExpansion;

    /**
     * 改扩建次数
     */
    @Column(name = "expansion_number")
    private String expansionNumber;

    /**
     * 改造时间
     */
    @Column(name = "expansion_year")
    private String expansionYear;

    /**
     * 改扩建内容
     */
    @Column(name = "expansion_content")
    private String expansionContent;

    /**
     * 是否改变主体结构
     */
    @Column(name = "is_change_structure")
    private String isChangeStructure;

    /**
     * 是否行政登记、备案
     */
    @Column(name = "is_procedure")
    private String isProcedure;

    /**
     * 行政登记、审批备案类型
     */
    @Column(name = "procedure_type")
    private String procedureType;

    /**
     * 是否违法建设经营
     */
    @Column(name = "is_illegal")
    private String isIllegal;

    /**
     * 违法建设经营情况
     */
    @Column(name = "illegal_type")
    private String illegalType;

    /**
     * 是否存在地质灾害隐患
     */
    @Column(name = "is_geology_danger")
    private String isGeologyDanger;

    /**
     * 地质灾害隐患类型
     */
    @Column(name = "geology_danger_type")
    private String geologyDangerType;

    /**
     * 房屋结构安全隐患初判
     */
    @Column(name = "estimate_structure_danger")
    private String estimateStructureDanger;

    /**
     * 房屋结构安全隐患部位
     */
    @Column(name = "structure_danger_part")
    private String structureDangerPart;

    /**
     * 是否安全鉴定
     */
    @Column(name = "is_security_authenticate")
    private String isSecurityAuthenticate;

    /**
     * 鉴定结论
     */
    @Column(name = "security_authenticate_result")
    private String securityAuthenticateResult;

    /**
     * 鉴定结论为CD级隐患部位
     */
    @Column(name = "authenticate_result_part")
    private String authenticateResultPart;

    /**
     * 是否存在消防安全
     */
    @Column(name = "is_fire_safety")
    private String isFireSafety;

    /**
     * 消防安全隐患
     */
    @Column(name = "fire_safety_danger")
    private String fireSafetyDanger;

    /**
     * 对存在质量及消防安全隐患管理措施
     */
    @Column(name = "management_measure")
    private String managementMeasure;

    /**
     * 经鉴定后为C、D级危房工程措施
     */
    @Column(name = "project_measure")
    private String projectMeasure;

    /**
     * 违法建设查出
     */
    @Column(name = "illegal_measure")
    private String illegalMeasure;

    /**
     * 调查时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 采集小组
     */
    @Column(name = "collect_team")
    private String collectTeam;

    /**
     * 获取uuid主键
     *
     * @return id - uuid主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置uuid主键
     *
     * @param id uuid主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取乡
     *
     * @return villiage - 乡
     */
    public String getVilliage() {
        return villiage;
    }

    /**
     * 设置乡
     *
     * @param villiage 乡
     */
    public void setVilliage(String villiage) {
        this.villiage = villiage == null ? null : villiage.trim();
    }

    /**
     * 获取村
     *
     * @return community - 村
     */
    public String getCommunity() {
        return community;
    }

    /**
     * 设置村
     *
     * @param community 村
     */
    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    /**
     * 获取组
     *
     * @return villiage_group - 组
     */
    public String getVilliageGroup() {
        return villiageGroup;
    }

    /**
     * 设置组
     *
     * @param villiageGroup 组
     */
    public void setVilliageGroup(String villiageGroup) {
        this.villiageGroup = villiageGroup == null ? null : villiageGroup.trim();
    }

    /**
     * 获取路
     *
     * @return road - 路
     */
    public String getRoad() {
        return road;
    }

    /**
     * 设置路
     *
     * @param road 路
     */
    public void setRoad(String road) {
        this.road = road == null ? null : road.trim();
    }

    /**
     * 获取号
     *
     * @return house_no - 号
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * 设置号
     *
     * @param houseNo 号
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    /**
     * 获取所在区域
     *
     * @return house_area - 所在区域
     */
    public String getHouseArea() {
        return houseArea;
    }

    /**
     * 设置所在区域
     *
     * @param houseArea 所在区域
     */
    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea == null ? null : houseArea.trim();
    }

    /**
     * 获取产权类型
     *
     * @return house_property_right - 产权类型
     */
    public String getHousePropertyRight() {
        return housePropertyRight;
    }

    /**
     * 设置产权类型
     *
     * @param housePropertyRight 产权类型
     */
    public void setHousePropertyRight(String housePropertyRight) {
        this.housePropertyRight = housePropertyRight == null ? null : housePropertyRight.trim();
    }

    /**
     * 获取户主姓名
     *
     * @return householder_name - 户主姓名
     */
    public String getHouseholderName() {
        return householderName;
    }

    /**
     * 设置户主姓名
     *
     * @param householderName 户主姓名
     */
    public void setHouseholderName(String householderName) {
        this.householderName = householderName == null ? null : householderName.trim();
    }

    /**
     * 获取身份证
     *
     * @return householder_idcard - 身份证
     */
    public String getHouseholderIdcard() {
        return householderIdcard;
    }

    /**
     * 设置身份证
     *
     * @param householderIdcard 身份证
     */
    public void setHouseholderIdcard(String householderIdcard) {
        this.householderIdcard = householderIdcard == null ? null : householderIdcard.trim();
    }

    /**
     * 获取居住人口数
     *
     * @return house_living_number - 居住人口数
     */
    public String getHouseLivingNumber() {
        return houseLivingNumber;
    }

    /**
     * 设置居住人口数
     *
     * @param houseLivingNumber 居住人口数
     */
    public void setHouseLivingNumber(String houseLivingNumber) {
        this.houseLivingNumber = houseLivingNumber == null ? null : houseLivingNumber.trim();
    }

    /**
     * 获取房屋用途
     *
     * @return house_usage - 房屋用途
     */
    public String getHouseUsage() {
        return houseUsage;
    }

    /**
     * 设置房屋用途
     *
     * @param houseUsage 房屋用途
     */
    public void setHouseUsage(String houseUsage) {
        this.houseUsage = houseUsage == null ? null : houseUsage.trim();
    }

    /**
     * 获取是否用作经营用途
     *
     * @return is_business - 是否用作经营用途
     */
    public String getIsBusiness() {
        return isBusiness;
    }

    /**
     * 设置是否用作经营用途
     *
     * @param isBusiness 是否用作经营用途
     */
    public void setIsBusiness(String isBusiness) {
        this.isBusiness = isBusiness == null ? null : isBusiness.trim();
    }

    /**
     * 获取经营业态种类
     *
     * @return business_scope - 经营业态种类
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * 设置经营业态种类
     *
     * @param businessScope 经营业态种类
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    /**
     * 获取是否出具专业鉴定报告
     *
     * @return is_authenticate_report - 是否出具专业鉴定报告
     */
    public String getIsAuthenticateReport() {
        return isAuthenticateReport;
    }

    /**
     * 设置是否出具专业鉴定报告
     *
     * @param isAuthenticateReport 是否出具专业鉴定报告
     */
    public void setIsAuthenticateReport(String isAuthenticateReport) {
        this.isAuthenticateReport = isAuthenticateReport == null ? null : isAuthenticateReport.trim();
    }

    /**
     * 获取土地性质
     *
     * @return house_land_property - 土地性质
     */
    public String getHouseLandProperty() {
        return houseLandProperty;
    }

    /**
     * 设置土地性质
     *
     * @param houseLandProperty 土地性质
     */
    public void setHouseLandProperty(String houseLandProperty) {
        this.houseLandProperty = houseLandProperty == null ? null : houseLandProperty.trim();
    }

    /**
     * 获取国有土地用途
     *
     * @return country_land_usage - 国有土地用途
     */
    public String getCountryLandUsage() {
        return countryLandUsage;
    }

    /**
     * 设置国有土地用途
     *
     * @param countryLandUsage 国有土地用途
     */
    public void setCountryLandUsage(String countryLandUsage) {
        this.countryLandUsage = countryLandUsage == null ? null : countryLandUsage.trim();
    }

    /**
     * 获取集体土地用途
     *
     * @return collective_land_usage - 集体土地用途
     */
    public String getCollectiveLandUsage() {
        return collectiveLandUsage;
    }

    /**
     * 设置集体土地用途
     *
     * @param collectiveLandUsage 集体土地用途
     */
    public void setCollectiveLandUsage(String collectiveLandUsage) {
        this.collectiveLandUsage = collectiveLandUsage == null ? null : collectiveLandUsage.trim();
    }

    /**
     * 获取房屋主体层数
     *
     * @return house_main_layer - 房屋主体层数
     */
    public String getHouseMainLayer() {
        return houseMainLayer;
    }

    /**
     * 设置房屋主体层数
     *
     * @param houseMainLayer 房屋主体层数
     */
    public void setHouseMainLayer(String houseMainLayer) {
        this.houseMainLayer = houseMainLayer == null ? null : houseMainLayer.trim();
    }

    /**
     * 获取房屋局部层数
     *
     * @return house_part_layer - 房屋局部层数
     */
    public String getHousePartLayer() {
        return housePartLayer;
    }

    /**
     * 设置房屋局部层数
     *
     * @param housePartLayer 房屋局部层数
     */
    public void setHousePartLayer(String housePartLayer) {
        this.housePartLayer = housePartLayer == null ? null : housePartLayer.trim();
    }

    /**
     * 获取房屋建筑面积
     *
     * @return house_acerage - 房屋建筑面积
     */
    public String getHouseAcerage() {
        return houseAcerage;
    }

    /**
     * 设置房屋建筑面积
     *
     * @param houseAcerage 房屋建筑面积
     */
    public void setHouseAcerage(String houseAcerage) {
        this.houseAcerage = houseAcerage == null ? null : houseAcerage.trim();
    }

    /**
     * 获取房屋高度
     *
     * @return house_height - 房屋高度
     */
    public String getHouseHeight() {
        return houseHeight;
    }

    /**
     * 设置房屋高度
     *
     * @param houseHeight 房屋高度
     */
    public void setHouseHeight(String houseHeight) {
        this.houseHeight = houseHeight == null ? null : houseHeight.trim();
    }

    /**
     * 获取房屋建成时间（年）
     *
     * @return house_year - 房屋建成时间（年）
     */
    public String getHouseYear() {
        return houseYear;
    }

    /**
     * 设置房屋建成时间（年）
     *
     * @param houseYear 房屋建成时间（年）
     */
    public void setHouseYear(String houseYear) {
        this.houseYear = houseYear == null ? null : houseYear.trim();
    }

    /**
     * 获取设计方式
     *
     * @return design_mode - 设计方式
     */
    public String getDesignMode() {
        return designMode;
    }

    /**
     * 设置设计方式
     *
     * @param designMode 设计方式
     */
    public void setDesignMode(String designMode) {
        this.designMode = designMode == null ? null : designMode.trim();
    }

    /**
     * 获取施工队伍
     *
     * @return construction_team - 施工队伍
     */
    public String getConstructionTeam() {
        return constructionTeam;
    }

    /**
     * 设置施工队伍
     *
     * @param constructionTeam 施工队伍
     */
    public void setConstructionTeam(String constructionTeam) {
        this.constructionTeam = constructionTeam == null ? null : constructionTeam.trim();
    }

    /**
     * 获取结构类型
     *
     * @return structure_type - 结构类型
     */
    public String getStructureType() {
        return structureType;
    }

    /**
     * 设置结构类型
     *
     * @param structureType 结构类型
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType == null ? null : structureType.trim();
    }

    /**
     * 获取墙体材料
     *
     * @return wall_material - 墙体材料
     */
    public String getWallMaterial() {
        return wallMaterial;
    }

    /**
     * 设置墙体材料
     *
     * @param wallMaterial 墙体材料
     */
    public void setWallMaterial(String wallMaterial) {
        this.wallMaterial = wallMaterial == null ? null : wallMaterial.trim();
    }

    /**
     * 获取楼板类型
     *
     * @return floor_type - 楼板类型
     */
    public String getFloorType() {
        return floorType;
    }

    /**
     * 设置楼板类型
     *
     * @param floorType 楼板类型
     */
    public void setFloorType(String floorType) {
        this.floorType = floorType == null ? null : floorType.trim();
    }

    /**
     * 获取屋盖类型
     *
     * @return roof_type - 屋盖类型
     */
    public String getRoofType() {
        return roofType;
    }

    /**
     * 设置屋盖类型
     *
     * @param roofType 屋盖类型
     */
    public void setRoofType(String roofType) {
        this.roofType = roofType == null ? null : roofType.trim();
    }

    /**
     * 获取抗震构造
     *
     * @return aseismatic_structure - 抗震构造
     */
    public String getAseismaticStructure() {
        return aseismaticStructure;
    }

    /**
     * 设置抗震构造
     *
     * @param aseismaticStructure 抗震构造
     */
    public void setAseismaticStructure(String aseismaticStructure) {
        this.aseismaticStructure = aseismaticStructure == null ? null : aseismaticStructure.trim();
    }

    /**
     * 获取是否扩建
     *
     * @return is_expansion - 是否扩建
     */
    public String getIsExpansion() {
        return isExpansion;
    }

    /**
     * 设置是否扩建
     *
     * @param isExpansion 是否扩建
     */
    public void setIsExpansion(String isExpansion) {
        this.isExpansion = isExpansion == null ? null : isExpansion.trim();
    }

    /**
     * 获取改扩建次数
     *
     * @return expansion_number - 改扩建次数
     */
    public String getExpansionNumber() {
        return expansionNumber;
    }

    /**
     * 设置改扩建次数
     *
     * @param expansionNumber 改扩建次数
     */
    public void setExpansionNumber(String expansionNumber) {
        this.expansionNumber = expansionNumber == null ? null : expansionNumber.trim();
    }

    /**
     * 获取改造时间
     *
     * @return expansion_year - 改造时间
     */
    public String getExpansionYear() {
        return expansionYear;
    }

    /**
     * 设置改造时间
     *
     * @param expansionYear 改造时间
     */
    public void setExpansionYear(String expansionYear) {
        this.expansionYear = expansionYear == null ? null : expansionYear.trim();
    }

    /**
     * 获取改扩建内容
     *
     * @return expansion_content - 改扩建内容
     */
    public String getExpansionContent() {
        return expansionContent;
    }

    /**
     * 设置改扩建内容
     *
     * @param expansionContent 改扩建内容
     */
    public void setExpansionContent(String expansionContent) {
        this.expansionContent = expansionContent == null ? null : expansionContent.trim();
    }

    /**
     * 获取是否改变主体结构
     *
     * @return is_change_structure - 是否改变主体结构
     */
    public String getIsChangeStructure() {
        return isChangeStructure;
    }

    /**
     * 设置是否改变主体结构
     *
     * @param isChangeStructure 是否改变主体结构
     */
    public void setIsChangeStructure(String isChangeStructure) {
        this.isChangeStructure = isChangeStructure == null ? null : isChangeStructure.trim();
    }

    /**
     * 获取是否行政登记、备案
     *
     * @return is_procedure - 是否行政登记、备案
     */
    public String getIsProcedure() {
        return isProcedure;
    }

    /**
     * 设置是否行政登记、备案
     *
     * @param isProcedure 是否行政登记、备案
     */
    public void setIsProcedure(String isProcedure) {
        this.isProcedure = isProcedure == null ? null : isProcedure.trim();
    }

    /**
     * 获取行政登记、审批备案类型
     *
     * @return procedure_type - 行政登记、审批备案类型
     */
    public String getProcedureType() {
        return procedureType;
    }

    /**
     * 设置行政登记、审批备案类型
     *
     * @param procedureType 行政登记、审批备案类型
     */
    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType == null ? null : procedureType.trim();
    }

    /**
     * 获取是否违法建设经营
     *
     * @return is_illegal - 是否违法建设经营
     */
    public String getIsIllegal() {
        return isIllegal;
    }

    /**
     * 设置是否违法建设经营
     *
     * @param isIllegal 是否违法建设经营
     */
    public void setIsIllegal(String isIllegal) {
        this.isIllegal = isIllegal == null ? null : isIllegal.trim();
    }

    /**
     * 获取违法建设经营情况
     *
     * @return illegal_type - 违法建设经营情况
     */
    public String getIllegalType() {
        return illegalType;
    }

    /**
     * 设置违法建设经营情况
     *
     * @param illegalType 违法建设经营情况
     */
    public void setIllegalType(String illegalType) {
        this.illegalType = illegalType == null ? null : illegalType.trim();
    }

    /**
     * 获取是否存在地质灾害隐患
     *
     * @return is_geology_danger - 是否存在地质灾害隐患
     */
    public String getIsGeologyDanger() {
        return isGeologyDanger;
    }

    /**
     * 设置是否存在地质灾害隐患
     *
     * @param isGeologyDanger 是否存在地质灾害隐患
     */
    public void setIsGeologyDanger(String isGeologyDanger) {
        this.isGeologyDanger = isGeologyDanger == null ? null : isGeologyDanger.trim();
    }

    /**
     * 获取地质灾害隐患类型
     *
     * @return geology_danger_type - 地质灾害隐患类型
     */
    public String getGeologyDangerType() {
        return geologyDangerType;
    }

    /**
     * 设置地质灾害隐患类型
     *
     * @param geologyDangerType 地质灾害隐患类型
     */
    public void setGeologyDangerType(String geologyDangerType) {
        this.geologyDangerType = geologyDangerType == null ? null : geologyDangerType.trim();
    }

    /**
     * 获取房屋结构安全隐患初判
     *
     * @return estimate_structure_danger - 房屋结构安全隐患初判
     */
    public String getEstimateStructureDanger() {
        return estimateStructureDanger;
    }

    /**
     * 设置房屋结构安全隐患初判
     *
     * @param estimateStructureDanger 房屋结构安全隐患初判
     */
    public void setEstimateStructureDanger(String estimateStructureDanger) {
        this.estimateStructureDanger = estimateStructureDanger == null ? null : estimateStructureDanger.trim();
    }

    /**
     * 获取房屋结构安全隐患部位
     *
     * @return structure_danger_part - 房屋结构安全隐患部位
     */
    public String getStructureDangerPart() {
        return structureDangerPart;
    }

    /**
     * 设置房屋结构安全隐患部位
     *
     * @param structureDangerPart 房屋结构安全隐患部位
     */
    public void setStructureDangerPart(String structureDangerPart) {
        this.structureDangerPart = structureDangerPart == null ? null : structureDangerPart.trim();
    }

    /**
     * 获取是否安全鉴定
     *
     * @return is_security_authenticate - 是否安全鉴定
     */
    public String getIsSecurityAuthenticate() {
        return isSecurityAuthenticate;
    }

    /**
     * 设置是否安全鉴定
     *
     * @param isSecurityAuthenticate 是否安全鉴定
     */
    public void setIsSecurityAuthenticate(String isSecurityAuthenticate) {
        this.isSecurityAuthenticate = isSecurityAuthenticate == null ? null : isSecurityAuthenticate.trim();
    }

    /**
     * 获取鉴定结论
     *
     * @return security_authenticate_result - 鉴定结论
     */
    public String getSecurityAuthenticateResult() {
        return securityAuthenticateResult;
    }

    /**
     * 设置鉴定结论
     *
     * @param securityAuthenticateResult 鉴定结论
     */
    public void setSecurityAuthenticateResult(String securityAuthenticateResult) {
        this.securityAuthenticateResult = securityAuthenticateResult == null ? null : securityAuthenticateResult.trim();
    }

    /**
     * 获取鉴定结论为CD级隐患部位
     *
     * @return authenticate_result_part - 鉴定结论为CD级隐患部位
     */
    public String getAuthenticateResultPart() {
        return authenticateResultPart;
    }

    /**
     * 设置鉴定结论为CD级隐患部位
     *
     * @param authenticateResultPart 鉴定结论为CD级隐患部位
     */
    public void setAuthenticateResultPart(String authenticateResultPart) {
        this.authenticateResultPart = authenticateResultPart == null ? null : authenticateResultPart.trim();
    }

    /**
     * 获取是否存在消防安全
     *
     * @return is_fire_safety - 是否存在消防安全
     */
    public String getIsFireSafety() {
        return isFireSafety;
    }

    /**
     * 设置是否存在消防安全
     *
     * @param isFireSafety 是否存在消防安全
     */
    public void setIsFireSafety(String isFireSafety) {
        this.isFireSafety = isFireSafety == null ? null : isFireSafety.trim();
    }

    /**
     * 获取消防安全隐患
     *
     * @return fire_safety_danger - 消防安全隐患
     */
    public String getFireSafetyDanger() {
        return fireSafetyDanger;
    }

    /**
     * 设置消防安全隐患
     *
     * @param fireSafetyDanger 消防安全隐患
     */
    public void setFireSafetyDanger(String fireSafetyDanger) {
        this.fireSafetyDanger = fireSafetyDanger == null ? null : fireSafetyDanger.trim();
    }

    /**
     * 获取对存在质量及消防安全隐患管理措施
     *
     * @return management_measure - 对存在质量及消防安全隐患管理措施
     */
    public String getManagementMeasure() {
        return managementMeasure;
    }

    /**
     * 设置对存在质量及消防安全隐患管理措施
     *
     * @param managementMeasure 对存在质量及消防安全隐患管理措施
     */
    public void setManagementMeasure(String managementMeasure) {
        this.managementMeasure = managementMeasure == null ? null : managementMeasure.trim();
    }

    /**
     * 获取经鉴定后为C、D级危房工程措施
     *
     * @return project_measure - 经鉴定后为C、D级危房工程措施
     */
    public String getProjectMeasure() {
        return projectMeasure;
    }

    /**
     * 设置经鉴定后为C、D级危房工程措施
     *
     * @param projectMeasure 经鉴定后为C、D级危房工程措施
     */
    public void setProjectMeasure(String projectMeasure) {
        this.projectMeasure = projectMeasure == null ? null : projectMeasure.trim();
    }

    /**
     * 获取违法建设查出
     *
     * @return illegal_measure - 违法建设查出
     */
    public String getIllegalMeasure() {
        return illegalMeasure;
    }

    /**
     * 设置违法建设查出
     *
     * @param illegalMeasure 违法建设查出
     */
    public void setIllegalMeasure(String illegalMeasure) {
        this.illegalMeasure = illegalMeasure == null ? null : illegalMeasure.trim();
    }

    /**
     * 获取调查时间
     *
     * @return create_time - 调查时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置调查时间
     *
     * @param createTime 调查时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 获取采集小组
     *
     * @return collect_team - 采集小组
     */
    public String getCollectTeam() {
        return collectTeam;
    }

    /**
     * 设置采集小组
     *
     * @param collectTeam 采集小组
     */
    public void setCollectTeam(String collectTeam) {
        this.collectTeam = collectTeam == null ? null : collectTeam.trim();
    }
}