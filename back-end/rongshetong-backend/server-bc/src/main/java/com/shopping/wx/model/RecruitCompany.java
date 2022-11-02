package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "recruit_company")
public class RecruitCompany {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 公司名字
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 注册日期
     */
    @Column(name = "register_date")
    private Date registerDate;

    /**
     * 唯一社会信用识别码
     */
    @Column(name = "license_id")
    private String licenseId;

    /**
     * 营业执照图片路径
     */
    @Column(name = "license_photo_path")
    private String licensePhotoPath;

    /**
     * 公司简介
     */
    private String introduction;

    /**
     * 组织机构代码
     */
    private String tyshxydm;

    /**
     * 公司法人
     */
    @Column(name = "juridical_person")
    private String juridicalPerson;

    /**
     * 法人身份证
     */
    @Column(name = "juridical_idcard")
    private String juridicalIdcard;

    /**
     * 法人电话
     */
    @Column(name = "juridical_phone")
    private String juridicalPhone;

    /**
     * 公司联系人
     */
    @Column(name = "company_person")
    private String companyPerson;

    /**
     * 公司电话
     */
    private String phone;

    /**
     * 联系人身份证
     */
    @Column(name = "company_person_idcard")
    private String companyPersonIdcard;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 公司详细地址（楼层）
     */
    @Column(name = "address_detail")
    private String addressDetail;

    /**
     * 经度  默认
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 公司头像路径
     */
    @Column(name = "portrait_path")
    private String portraitPath;

    /**
     * 公司相册路径（分号分割）
     */
    @Column(name = "photo_path")
    private String photoPath;

    /**
     * 期望招聘岗位类别id（分号分割）
     */
    @Column(name = "expect_category_id")
    private String expectCategoryId;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 公司注册认证flag（0：未认证 1 已认证）
     */
    @Column(name = "flag_identification")
    private Integer flagIdentification;

    /**
     * 浏览量
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 关联 - 社区id
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 关联 - 楼栋uuid
     */
    @Column(name = "building_uuid")
    private String buildingUuid;

    /**
     * 关联 - 用户openid
     */
    @Column(name = "recruiter_openid")
    private String recruiterOpenid;

    /**
     * 本条状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 本条信息录入时间
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
     * 获取公司名字
     *
     * @return company_name - 公司名字
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名字
     *
     * @param companyName 公司名字
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取注册日期
     *
     * @return register_date - 注册日期
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册日期
     *
     * @param registerDate 注册日期
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取唯一社会信用识别码
     *
     * @return license_id - 唯一社会信用识别码
     */
    public String getLicenseId() {
        return licenseId;
    }

    /**
     * 设置唯一社会信用识别码
     *
     * @param licenseId 唯一社会信用识别码
     */
    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId == null ? null : licenseId.trim();
    }

    /**
     * 获取营业执照图片路径
     *
     * @return license_photo_path - 营业执照图片路径
     */
    public String getLicensePhotoPath() {
        return licensePhotoPath;
    }

    /**
     * 设置营业执照图片路径
     *
     * @param licensePhotoPath 营业执照图片路径
     */
    public void setLicensePhotoPath(String licensePhotoPath) {
        this.licensePhotoPath = licensePhotoPath == null ? null : licensePhotoPath.trim();
    }

    /**
     * 获取公司简介
     *
     * @return introduction - 公司简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置公司简介
     *
     * @param introduction 公司简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取组织机构代码
     *
     * @return tyshxydm - 组织机构代码
     */
    public String getTyshxydm() {
        return tyshxydm;
    }

    /**
     * 设置组织机构代码
     *
     * @param tyshxydm 组织机构代码
     */
    public void setTyshxydm(String tyshxydm) {
        this.tyshxydm = tyshxydm == null ? null : tyshxydm.trim();
    }

    /**
     * 获取公司法人
     *
     * @return juridical_person - 公司法人
     */
    public String getJuridicalPerson() {
        return juridicalPerson;
    }

    /**
     * 设置公司法人
     *
     * @param juridicalPerson 公司法人
     */
    public void setJuridicalPerson(String juridicalPerson) {
        this.juridicalPerson = juridicalPerson == null ? null : juridicalPerson.trim();
    }

    /**
     * 获取法人身份证
     *
     * @return juridical_idcard - 法人身份证
     */
    public String getJuridicalIdcard() {
        return juridicalIdcard;
    }

    /**
     * 设置法人身份证
     *
     * @param juridicalIdcard 法人身份证
     */
    public void setJuridicalIdcard(String juridicalIdcard) {
        this.juridicalIdcard = juridicalIdcard == null ? null : juridicalIdcard.trim();
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
     * 获取公司联系人
     *
     * @return company_person - 公司联系人
     */
    public String getCompanyPerson() {
        return companyPerson;
    }

    /**
     * 设置公司联系人
     *
     * @param companyPerson 公司联系人
     */
    public void setCompanyPerson(String companyPerson) {
        this.companyPerson = companyPerson == null ? null : companyPerson.trim();
    }

    /**
     * 获取公司电话
     *
     * @return phone - 公司电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置公司电话
     *
     * @param phone 公司电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取联系人身份证
     *
     * @return company_person_idcard - 联系人身份证
     */
    public String getCompanyPersonIdcard() {
        return companyPersonIdcard;
    }

    /**
     * 设置联系人身份证
     *
     * @param companyPersonIdcard 联系人身份证
     */
    public void setCompanyPersonIdcard(String companyPersonIdcard) {
        this.companyPersonIdcard = companyPersonIdcard == null ? null : companyPersonIdcard.trim();
    }

    /**
     * 获取公司地址
     *
     * @return address - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取公司详细地址（楼层）
     *
     * @return address_detail - 公司详细地址（楼层）
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * 设置公司详细地址（楼层）
     *
     * @param addressDetail 公司详细地址（楼层）
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    /**
     * 获取经度  默认
     *
     * @return lon - 经度  默认
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 设置经度  默认
     *
     * @param lon 经度  默认
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 获取纬度
     *
     * @return lat - 纬度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 设置纬度
     *
     * @param lat 纬度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 获取公司头像路径
     *
     * @return portrait_path - 公司头像路径
     */
    public String getPortraitPath() {
        return portraitPath;
    }

    /**
     * 设置公司头像路径
     *
     * @param portraitPath 公司头像路径
     */
    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath == null ? null : portraitPath.trim();
    }

    /**
     * 获取公司相册路径（分号分割）
     *
     * @return photo_path - 公司相册路径（分号分割）
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * 设置公司相册路径（分号分割）
     *
     * @param photoPath 公司相册路径（分号分割）
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath == null ? null : photoPath.trim();
    }

    /**
     * 获取期望招聘岗位类别id（分号分割）
     *
     * @return expect_category_id - 期望招聘岗位类别id（分号分割）
     */
    public String getExpectCategoryId() {
        return expectCategoryId;
    }

    /**
     * 设置期望招聘岗位类别id（分号分割）
     *
     * @param expectCategoryId 期望招聘岗位类别id（分号分割）
     */
    public void setExpectCategoryId(String expectCategoryId) {
        this.expectCategoryId = expectCategoryId == null ? null : expectCategoryId.trim();
    }

    /**
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取公司注册认证flag（0：未认证 1 已认证）
     *
     * @return flag_identification - 公司注册认证flag（0：未认证 1 已认证）
     */
    public Integer getFlagIdentification() {
        return flagIdentification;
    }

    /**
     * 设置公司注册认证flag（0：未认证 1 已认证）
     *
     * @param flagIdentification 公司注册认证flag（0：未认证 1 已认证）
     */
    public void setFlagIdentification(Integer flagIdentification) {
        this.flagIdentification = flagIdentification;
    }

    /**
     * 获取浏览量
     *
     * @return count_view - 浏览量
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置浏览量
     *
     * @param countView 浏览量
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    /**
     * 获取关联 - 社区id
     *
     * @return community_uuid - 关联 - 社区id
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 - 社区id
     *
     * @param communityUuid 关联 - 社区id
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
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
     * 获取关联 - 用户openid
     *
     * @return recruiter_openid - 关联 - 用户openid
     */
    public String getRecruiterOpenid() {
        return recruiterOpenid;
    }

    /**
     * 设置关联 - 用户openid
     *
     * @param recruiterOpenid 关联 - 用户openid
     */
    public void setRecruiterOpenid(String recruiterOpenid) {
        this.recruiterOpenid = recruiterOpenid == null ? null : recruiterOpenid.trim();
    }

    /**
     * 获取本条状态（0：正常 -1：失效）
     *
     * @return status - 本条状态（0：正常 -1：失效）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1：失效）
     *
     * @param status 本条状态（0：正常 -1：失效）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取本条信息录入时间
     *
     * @return create_time - 本条信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置本条信息录入时间
     *
     * @param createTime 本条信息录入时间
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