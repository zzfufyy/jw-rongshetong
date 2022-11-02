package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "resident_for_person")
public class ResidentForPerson {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 个人姓名
     */
    @Column(name = "person_name")
    private String personName;

    /**
     * 个人身份证
     */
    @Column(name = "person_idcard")
    private String personIdcard;

    /**
     * 个人电话号码
     */
    @Column(name = "person_phone")
    private String personPhone;

    /**
     * 个人相信地址
     */
    @Column(name = "person_address")
    private String personAddress;

    /**
     * 性别 0:男 1:女
     */
    @Column(name = "person_gender")
    private Integer personGender;

    /**
     * 出生日期
     */
    @Column(name = "person_birthday")
    private Date personBirthday;

    /**
     * 省份名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 市区名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区域名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 党员(单位)
     */
    @Column(name = "party_institution")
    private String partyInstitution;

    /**
     * 特殊人群 0： 无 1: 高龄 2: 儿童  3： 军人  4: 特殊人群
     */
    @Column(name = "person_special")
    private Integer personSpecial;

    /**
     * 备注
     */
    private String remark;

    /**
     * 人员性质： 0: 居住人  1: 从业人员
     */
    @Column(name = "person_type")
    private Integer personType;

    /**
     * 关联 -- resident_uuid
     */
    @Column(name = "resident_uuid")
    private String residentUuid;

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
     * 获取个人姓名
     *
     * @return person_name - 个人姓名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置个人姓名
     *
     * @param personName 个人姓名
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    /**
     * 获取个人身份证
     *
     * @return person_idcard - 个人身份证
     */
    public String getPersonIdcard() {
        return personIdcard;
    }

    /**
     * 设置个人身份证
     *
     * @param personIdcard 个人身份证
     */
    public void setPersonIdcard(String personIdcard) {
        this.personIdcard = personIdcard == null ? null : personIdcard.trim();
    }

    /**
     * 获取个人电话号码
     *
     * @return person_phone - 个人电话号码
     */
    public String getPersonPhone() {
        return personPhone;
    }

    /**
     * 设置个人电话号码
     *
     * @param personPhone 个人电话号码
     */
    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone == null ? null : personPhone.trim();
    }

    /**
     * 获取个人相信地址
     *
     * @return person_address - 个人相信地址
     */
    public String getPersonAddress() {
        return personAddress;
    }

    /**
     * 设置个人相信地址
     *
     * @param personAddress 个人相信地址
     */
    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress == null ? null : personAddress.trim();
    }

    /**
     * 获取性别 0:男 1:女
     *
     * @return person_gender - 性别 0:男 1:女
     */
    public Integer getPersonGender() {
        return personGender;
    }

    /**
     * 设置性别 0:男 1:女
     *
     * @param personGender 性别 0:男 1:女
     */
    public void setPersonGender(Integer personGender) {
        this.personGender = personGender;
    }

    /**
     * 获取出生日期
     *
     * @return person_birthday - 出生日期
     */
    public Date getPersonBirthday() {
        return personBirthday;
    }

    /**
     * 设置出生日期
     *
     * @param personBirthday 出生日期
     */
    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    /**
     * 获取省份名称
     *
     * @return province_name - 省份名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省份名称
     *
     * @param provinceName 省份名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取市区名称
     *
     * @return city_name - 市区名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置市区名称
     *
     * @param cityName 市区名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取区域名称
     *
     * @return district_name - 区域名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区域名称
     *
     * @param districtName 区域名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 获取党员(单位)
     *
     * @return party_institution - 党员(单位)
     */
    public String getPartyInstitution() {
        return partyInstitution;
    }

    /**
     * 设置党员(单位)
     *
     * @param partyInstitution 党员(单位)
     */
    public void setPartyInstitution(String partyInstitution) {
        this.partyInstitution = partyInstitution == null ? null : partyInstitution.trim();
    }

    /**
     * 获取特殊人群 0： 无 1: 高龄 2: 儿童  3： 军人  4: 特殊人群
     *
     * @return person_special - 特殊人群 0： 无 1: 高龄 2: 儿童  3： 军人  4: 特殊人群
     */
    public Integer getPersonSpecial() {
        return personSpecial;
    }

    /**
     * 设置特殊人群 0： 无 1: 高龄 2: 儿童  3： 军人  4: 特殊人群
     *
     * @param personSpecial 特殊人群 0： 无 1: 高龄 2: 儿童  3： 军人  4: 特殊人群
     */
    public void setPersonSpecial(Integer personSpecial) {
        this.personSpecial = personSpecial;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取人员性质： 0: 居住人  1: 从业人员
     *
     * @return person_type - 人员性质： 0: 居住人  1: 从业人员
     */
    public Integer getPersonType() {
        return personType;
    }

    /**
     * 设置人员性质： 0: 居住人  1: 从业人员
     *
     * @param personType 人员性质： 0: 居住人  1: 从业人员
     */
    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    /**
     * 获取关联 -- resident_uuid
     *
     * @return resident_uuid - 关联 -- resident_uuid
     */
    public String getResidentUuid() {
        return residentUuid;
    }

    /**
     * 设置关联 -- resident_uuid
     *
     * @param residentUuid 关联 -- resident_uuid
     */
    public void setResidentUuid(String residentUuid) {
        this.residentUuid = residentUuid == null ? null : residentUuid.trim();
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