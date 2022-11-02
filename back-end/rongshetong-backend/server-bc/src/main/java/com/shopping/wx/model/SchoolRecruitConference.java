package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "school_recruit_conference")
public class SchoolRecruitConference {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 宣讲会名称
     */
    @Column(name = "conference_name")
    private String conferenceName;

    /**
     * 宣讲会类型： 0：校内 , 1:校外
     */
    @Column(name = "conference_type")
    private Integer conferenceType;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司类型: 0:国有企业，1:私营企业 2:股份制企业 3:外商投资企业
     */
    @Column(name = "company_type")
    private Integer companyType;

    /**
     * 公司主营业务
     */
    @Column(name = "company_major")
    private String companyMajor;

    /**
     * 公司规模
     */
    @Column(name = "company_scale")
    private String companyScale;

    /**
     * 公司所在城市
     */
    @Column(name = "company_city")
    private String companyCity;

    /**
     * 公司地点
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 公司简介
     */
    @Column(name = "company_introduction")
    private String companyIntroduction;

    /**
     * 招聘简章
     */
    @Column(name = "recruit_introduction")
    private String recruitIntroduction;

    /**
     * 招聘专业
     */
    @Column(name = "recruit_major")
    private String recruitMajor;

    /**
     * 宣讲会开始时间
     */
    @Column(name = "conference_begin_time")
    private Date conferenceBeginTime;

    /**
     * 宣讲会地点
     */
    @Column(name = "conference_address")
    private String conferenceAddress;

    /**
     * 本条信息状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 点击数
     */
    @Column(name = "count_view")
    private Integer countView;

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
     * 获取宣讲会名称
     *
     * @return conference_name - 宣讲会名称
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * 设置宣讲会名称
     *
     * @param conferenceName 宣讲会名称
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName == null ? null : conferenceName.trim();
    }

    /**
     * 获取宣讲会类型： 0：校内 , 1:校外
     *
     * @return conference_type - 宣讲会类型： 0：校内 , 1:校外
     */
    public Integer getConferenceType() {
        return conferenceType;
    }

    /**
     * 设置宣讲会类型： 0：校内 , 1:校外
     *
     * @param conferenceType 宣讲会类型： 0：校内 , 1:校外
     */
    public void setConferenceType(Integer conferenceType) {
        this.conferenceType = conferenceType;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取公司类型: 0:国有企业，1:私营企业 2:股份制企业 3:外商投资企业
     *
     * @return company_type - 公司类型: 0:国有企业，1:私营企业 2:股份制企业 3:外商投资企业
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * 设置公司类型: 0:国有企业，1:私营企业 2:股份制企业 3:外商投资企业
     *
     * @param companyType 公司类型: 0:国有企业，1:私营企业 2:股份制企业 3:外商投资企业
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取公司主营业务
     *
     * @return company_major - 公司主营业务
     */
    public String getCompanyMajor() {
        return companyMajor;
    }

    /**
     * 设置公司主营业务
     *
     * @param companyMajor 公司主营业务
     */
    public void setCompanyMajor(String companyMajor) {
        this.companyMajor = companyMajor == null ? null : companyMajor.trim();
    }

    /**
     * 获取公司规模
     *
     * @return company_scale - 公司规模
     */
    public String getCompanyScale() {
        return companyScale;
    }

    /**
     * 设置公司规模
     *
     * @param companyScale 公司规模
     */
    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale == null ? null : companyScale.trim();
    }

    /**
     * 获取公司所在城市
     *
     * @return company_city - 公司所在城市
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * 设置公司所在城市
     *
     * @param companyCity 公司所在城市
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity == null ? null : companyCity.trim();
    }

    /**
     * 获取公司地点
     *
     * @return company_address - 公司地点
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地点
     *
     * @param companyAddress 公司地点
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * 获取公司简介
     *
     * @return company_introduction - 公司简介
     */
    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    /**
     * 设置公司简介
     *
     * @param companyIntroduction 公司简介
     */
    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction == null ? null : companyIntroduction.trim();
    }

    /**
     * 获取招聘简章
     *
     * @return recruit_introduction - 招聘简章
     */
    public String getRecruitIntroduction() {
        return recruitIntroduction;
    }

    /**
     * 设置招聘简章
     *
     * @param recruitIntroduction 招聘简章
     */
    public void setRecruitIntroduction(String recruitIntroduction) {
        this.recruitIntroduction = recruitIntroduction == null ? null : recruitIntroduction.trim();
    }

    /**
     * 获取招聘专业
     *
     * @return recruit_major - 招聘专业
     */
    public String getRecruitMajor() {
        return recruitMajor;
    }

    /**
     * 设置招聘专业
     *
     * @param recruitMajor 招聘专业
     */
    public void setRecruitMajor(String recruitMajor) {
        this.recruitMajor = recruitMajor == null ? null : recruitMajor.trim();
    }

    /**
     * 获取宣讲会开始时间
     *
     * @return conference_begin_time - 宣讲会开始时间
     */
    public Date getConferenceBeginTime() {
        return conferenceBeginTime;
    }

    /**
     * 设置宣讲会开始时间
     *
     * @param conferenceBeginTime 宣讲会开始时间
     */
    public void setConferenceBeginTime(Date conferenceBeginTime) {
        this.conferenceBeginTime = conferenceBeginTime;
    }

    /**
     * 获取宣讲会地点
     *
     * @return conference_address - 宣讲会地点
     */
    public String getConferenceAddress() {
        return conferenceAddress;
    }

    /**
     * 设置宣讲会地点
     *
     * @param conferenceAddress 宣讲会地点
     */
    public void setConferenceAddress(String conferenceAddress) {
        this.conferenceAddress = conferenceAddress == null ? null : conferenceAddress.trim();
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
     * 获取点击数
     *
     * @return count_view - 点击数
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置点击数
     *
     * @param countView 点击数
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
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