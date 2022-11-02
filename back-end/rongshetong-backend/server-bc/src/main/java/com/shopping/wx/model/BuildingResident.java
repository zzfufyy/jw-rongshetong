package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "building_resident")
public class BuildingResident {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 第几层
     */
    @Column(name = "which_layer")
    private Integer whichLayer;

    /**
     * 某层第几户
     */
    @Column(name = "which_layer_family")
    private Integer whichLayerFamily;

    /**
     * 居住人姓名
     */
    @Column(name = "resident_name")
    private String residentName;

    /**
     * 居住人电话号码
     */
    @Column(name = "resident_phone")
    private String residentPhone;

    /**
     * 详细地址
     */
    @Column(name = "resident_address")
    private String residentAddress;

    /**
     * 居住人数
     */
    @Column(name = "resident_number")
    private Integer residentNumber;

    /**
     * 从业人数
     */
    @Column(name = "employee_number")
    private Integer employeeNumber;

    /**
     * 房屋面积
     */
    @Column(name = "house_space")
    private Integer houseSpace;

    /**
     * 房屋 几室
     */
    @Column(name = "house_chamber")
    private Integer houseChamber;

    /**
     * 房屋 几厅
     */
    @Column(name = "house_hall")
    private Integer houseHall;

    /**
     * 电动车牌号
     */
    @Column(name = "electrocar_number")
    private String electrocarNumber;

    /**
     * 房屋用途是否选择了公司
     */
    @Column(name = "flag_company")
    private Integer flagCompany;

    /**
     * 企业名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 工商登记号码
     */
    @Column(name = "business_register_id")
    private String businessRegisterId;

    /**
     * 经营许可证号
     */
    @Column(name = "license_permission_id")
    private String licensePermissionId;

    /**
     * 法人
     */
    @Column(name = "juridical_person")
    private String juridicalPerson;

    /**
     * 法人电话
     */
    @Column(name = "juridical_phone")
    private String juridicalPhone;

    /**
     * 其他事项
     */
    @Column(name = "company_remark")
    private String companyRemark;

    /**
     * 签名图片路径
     */
    @Column(name = "sign_img_path")
    private String signImgPath;

    /**
     * 调查人姓名
     */
    @Column(name = "surveyor_name")
    private String surveyorName;

    /**
     * 关联 - 楼栋uuid
     */
    @Column(name = "building_uuid")
    private String buildingUuid;

    /**
     * 关联 - 小区uuid
     */
    @Column(name = "cell_uuid")
    private String cellUuid;

    /**
     * 关联 - 社区uuid
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 本条信息状态（0：未登记 1：已登记）
     */
    @Column(name = "flag_register")
    private Integer flagRegister;

    /**
     * 信息录入时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 本条信息状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 信息录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

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
     * 获取第几层
     *
     * @return which_layer - 第几层
     */
    public Integer getWhichLayer() {
        return whichLayer;
    }

    /**
     * 设置第几层
     *
     * @param whichLayer 第几层
     */
    public void setWhichLayer(Integer whichLayer) {
        this.whichLayer = whichLayer;
    }

    /**
     * 获取某层第几户
     *
     * @return which_layer_family - 某层第几户
     */
    public Integer getWhichLayerFamily() {
        return whichLayerFamily;
    }

    /**
     * 设置某层第几户
     *
     * @param whichLayerFamily 某层第几户
     */
    public void setWhichLayerFamily(Integer whichLayerFamily) {
        this.whichLayerFamily = whichLayerFamily;
    }

    /**
     * 获取居住人姓名
     *
     * @return resident_name - 居住人姓名
     */
    public String getResidentName() {
        return residentName;
    }

    /**
     * 设置居住人姓名
     *
     * @param residentName 居住人姓名
     */
    public void setResidentName(String residentName) {
        this.residentName = residentName == null ? null : residentName.trim();
    }

    /**
     * 获取居住人电话号码
     *
     * @return resident_phone - 居住人电话号码
     */
    public String getResidentPhone() {
        return residentPhone;
    }

    /**
     * 设置居住人电话号码
     *
     * @param residentPhone 居住人电话号码
     */
    public void setResidentPhone(String residentPhone) {
        this.residentPhone = residentPhone == null ? null : residentPhone.trim();
    }

    /**
     * 获取详细地址
     *
     * @return resident_address - 详细地址
     */
    public String getResidentAddress() {
        return residentAddress;
    }

    /**
     * 设置详细地址
     *
     * @param residentAddress 详细地址
     */
    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress == null ? null : residentAddress.trim();
    }

    /**
     * 获取居住人数
     *
     * @return resident_number - 居住人数
     */
    public Integer getResidentNumber() {
        return residentNumber;
    }

    /**
     * 设置居住人数
     *
     * @param residentNumber 居住人数
     */
    public void setResidentNumber(Integer residentNumber) {
        this.residentNumber = residentNumber;
    }

    /**
     * 获取从业人数
     *
     * @return employee_number - 从业人数
     */
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * 设置从业人数
     *
     * @param employeeNumber 从业人数
     */
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * 获取房屋面积
     *
     * @return house_space - 房屋面积
     */
    public Integer getHouseSpace() {
        return houseSpace;
    }

    /**
     * 设置房屋面积
     *
     * @param houseSpace 房屋面积
     */
    public void setHouseSpace(Integer houseSpace) {
        this.houseSpace = houseSpace;
    }

    /**
     * 获取房屋 几室
     *
     * @return house_chamber - 房屋 几室
     */
    public Integer getHouseChamber() {
        return houseChamber;
    }

    /**
     * 设置房屋 几室
     *
     * @param houseChamber 房屋 几室
     */
    public void setHouseChamber(Integer houseChamber) {
        this.houseChamber = houseChamber;
    }

    /**
     * 获取房屋 几厅
     *
     * @return house_hall - 房屋 几厅
     */
    public Integer getHouseHall() {
        return houseHall;
    }

    /**
     * 设置房屋 几厅
     *
     * @param houseHall 房屋 几厅
     */
    public void setHouseHall(Integer houseHall) {
        this.houseHall = houseHall;
    }

    /**
     * 获取电动车牌号
     *
     * @return electrocar_number - 电动车牌号
     */
    public String getElectrocarNumber() {
        return electrocarNumber;
    }

    /**
     * 设置电动车牌号
     *
     * @param electrocarNumber 电动车牌号
     */
    public void setElectrocarNumber(String electrocarNumber) {
        this.electrocarNumber = electrocarNumber == null ? null : electrocarNumber.trim();
    }

    /**
     * 获取房屋用途是否选择了公司
     *
     * @return flag_company - 房屋用途是否选择了公司
     */
    public Integer getFlagCompany() {
        return flagCompany;
    }

    /**
     * 设置房屋用途是否选择了公司
     *
     * @param flagCompany 房屋用途是否选择了公司
     */
    public void setFlagCompany(Integer flagCompany) {
        this.flagCompany = flagCompany;
    }

    /**
     * 获取企业名称
     *
     * @return company_name - 企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置企业名称
     *
     * @param companyName 企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取工商登记号码
     *
     * @return business_register_id - 工商登记号码
     */
    public String getBusinessRegisterId() {
        return businessRegisterId;
    }

    /**
     * 设置工商登记号码
     *
     * @param businessRegisterId 工商登记号码
     */
    public void setBusinessRegisterId(String businessRegisterId) {
        this.businessRegisterId = businessRegisterId == null ? null : businessRegisterId.trim();
    }

    /**
     * 获取经营许可证号
     *
     * @return license_permission_id - 经营许可证号
     */
    public String getLicensePermissionId() {
        return licensePermissionId;
    }

    /**
     * 设置经营许可证号
     *
     * @param licensePermissionId 经营许可证号
     */
    public void setLicensePermissionId(String licensePermissionId) {
        this.licensePermissionId = licensePermissionId == null ? null : licensePermissionId.trim();
    }

    /**
     * 获取法人
     *
     * @return juridical_person - 法人
     */
    public String getJuridicalPerson() {
        return juridicalPerson;
    }

    /**
     * 设置法人
     *
     * @param juridicalPerson 法人
     */
    public void setJuridicalPerson(String juridicalPerson) {
        this.juridicalPerson = juridicalPerson == null ? null : juridicalPerson.trim();
    }

    /**
     * 获取法人电话
     *
     * @return juridical_phone - 法人电话
     */
    public String getJuridicalPhone() {
        return juridicalPhone;
    }

    /**
     * 设置法人电话
     *
     * @param juridicalPhone 法人电话
     */
    public void setJuridicalPhone(String juridicalPhone) {
        this.juridicalPhone = juridicalPhone == null ? null : juridicalPhone.trim();
    }

    /**
     * 获取其他事项
     *
     * @return company_remark - 其他事项
     */
    public String getCompanyRemark() {
        return companyRemark;
    }

    /**
     * 设置其他事项
     *
     * @param companyRemark 其他事项
     */
    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark == null ? null : companyRemark.trim();
    }

    /**
     * 获取签名图片路径
     *
     * @return sign_img_path - 签名图片路径
     */
    public String getSignImgPath() {
        return signImgPath;
    }

    /**
     * 设置签名图片路径
     *
     * @param signImgPath 签名图片路径
     */
    public void setSignImgPath(String signImgPath) {
        this.signImgPath = signImgPath == null ? null : signImgPath.trim();
    }

    /**
     * 获取调查人姓名
     *
     * @return surveyor_name - 调查人姓名
     */
    public String getSurveyorName() {
        return surveyorName;
    }

    /**
     * 设置调查人姓名
     *
     * @param surveyorName 调查人姓名
     */
    public void setSurveyorName(String surveyorName) {
        this.surveyorName = surveyorName == null ? null : surveyorName.trim();
    }

    /**
     * 获取关联 - 楼栋uuid
     *
     * @return building_uuid - 关联 - 楼栋uuid
     */
    public String getBuildingUuid() {
        return buildingUuid;
    }

    /**
     * 设置关联 - 楼栋uuid
     *
     * @param buildingUuid 关联 - 楼栋uuid
     */
    public void setBuildingUuid(String buildingUuid) {
        this.buildingUuid = buildingUuid == null ? null : buildingUuid.trim();
    }

    /**
     * 获取关联 - 小区uuid
     *
     * @return cell_uuid - 关联 - 小区uuid
     */
    public String getCellUuid() {
        return cellUuid;
    }

    /**
     * 设置关联 - 小区uuid
     *
     * @param cellUuid 关联 - 小区uuid
     */
    public void setCellUuid(String cellUuid) {
        this.cellUuid = cellUuid == null ? null : cellUuid.trim();
    }

    /**
     * 获取关联 - 社区uuid
     *
     * @return community_uuid - 关联 - 社区uuid
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 - 社区uuid
     *
     * @param communityUuid 关联 - 社区uuid
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
    }

    /**
     * 获取本条信息状态（0：未登记 1：已登记）
     *
     * @return flag_register - 本条信息状态（0：未登记 1：已登记）
     */
    public Integer getFlagRegister() {
        return flagRegister;
    }

    /**
     * 设置本条信息状态（0：未登记 1：已登记）
     *
     * @param flagRegister 本条信息状态（0：未登记 1：已登记）
     */
    public void setFlagRegister(Integer flagRegister) {
        this.flagRegister = flagRegister;
    }

    /**
     * 获取信息录入时间
     *
     * @return register_time - 信息录入时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置信息录入时间
     *
     * @param registerTime 信息录入时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取本条信息状态（0：正常 -1：失效）
     *
     * @return status - 本条信息状态（0：正常 -1：失效）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条信息状态（0：正常 -1：失效）
     *
     * @param status 本条信息状态（0：正常 -1：失效）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取信息录入时间
     *
     * @return create_time - 信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置信息录入时间
     *
     * @param createTime 信息录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取扩展字段1
     *
     * @return ext1 - 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 获取扩展字段2
     *
     * @return ext2 - 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 获取扩展字段3
     *
     * @return ext3 - 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}