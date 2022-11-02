package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_candidate")
public class UserCandidate {
    /**
     * openid主键
     */
    @Id
    private String id;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证
     */
    @Column(name = "identity_card")
    private String identityCard;

    /**
     * 性别 0:男 1:女
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 工龄
     */
    @Column(name = "working_age")
    private Integer workingAge;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 婚姻状态：0:未婚 1:已婚
     */
    @Column(name = "marraige_state")
    private Integer marraigeState;

    /**
     * 政治面貌：0:群众 1:团员 2:党员
     */
    @Column(name = "political_state")
    private Integer politicalState;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 个人状态: 0: 待业 1: 在职 2: 困难户等
     */
    @Column(name = "personnel_state")
    private Integer personnelState;

    /**
     * 个人照片路径
     */
    @Column(name = "portrait_path")
    private String portraitPath;

    /**
     * 期望薪资（最小）
     */
    @Column(name = "expect_salary_min")
    private Integer expectSalaryMin;

    /**
     * 期望薪资（最大）
     */
    @Column(name = "expect_salary_max")
    private Integer expectSalaryMax;

    /**
     * 期望职业（标签id  分号分割匹配）
     */
    @Column(name = "expect_category_id")
    private String expectCategoryId;

    /**
     * 期望工作社区id
     */
    @Column(name = "expect_community_id")
    private String expectCommunityId;

    /**
     * 学校名称
     */
    @Column(name = "edu_school_name")
    private String eduSchoolName;

    /**
     * 学历
     */
    @Column(name = "edu_background_name")
    private String eduBackgroundName;

    /**
     * 专业名称
     */
    @Column(name = "edu_major")
    private String eduMajor;

    /**
     * 教育开始时间
     */
    @Column(name = "edu_begin_time")
    private Date eduBeginTime;

    /**
     * 教育结束时间
     */
    @Column(name = "edu_end_time")
    private Date eduEndTime;

    /**
     * 是否实名认证（0：否 1：是）
     */
    @Column(name = "flag_identification")
    private Integer flagIdentification;

    /**
     * 被查看次数
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 关联 - 用户所在社区 id
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 本条状态（0：正常 -1：失效）
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
     * 实习经历
     */
    private String internship;

    /**
     * 简历
     */
    private String resume;

    /**
     * 获取openid主键
     *
     * @return id - openid主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置openid主键
     *
     * @param id openid主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取身份证
     *
     * @return identity_card - 身份证
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 设置身份证
     *
     * @param identityCard 身份证
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    /**
     * 获取性别 0:男 1:女
     *
     * @return gender - 性别 0:男 1:女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别 0:男 1:女
     *
     * @param gender 性别 0:男 1:女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取手机号码
     *
     * @return telephone - 手机号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置手机号码
     *
     * @param telephone 手机号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 获取工龄
     *
     * @return working_age - 工龄
     */
    public Integer getWorkingAge() {
        return workingAge;
    }

    /**
     * 设置工龄
     *
     * @param workingAge 工龄
     */
    public void setWorkingAge(Integer workingAge) {
        this.workingAge = workingAge;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取家庭住址
     *
     * @return address - 家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置家庭住址
     *
     * @param address 家庭住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取经度
     *
     * @return lon - 经度
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 设置经度
     *
     * @param lon 经度
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
     * 获取婚姻状态：0:未婚 1:已婚
     *
     * @return marraige_state - 婚姻状态：0:未婚 1:已婚
     */
    public Integer getMarraigeState() {
        return marraigeState;
    }

    /**
     * 设置婚姻状态：0:未婚 1:已婚
     *
     * @param marraigeState 婚姻状态：0:未婚 1:已婚
     */
    public void setMarraigeState(Integer marraigeState) {
        this.marraigeState = marraigeState;
    }

    /**
     * 获取政治面貌：0:群众 1:团员 2:党员
     *
     * @return political_state - 政治面貌：0:群众 1:团员 2:党员
     */
    public Integer getPoliticalState() {
        return politicalState;
    }

    /**
     * 设置政治面貌：0:群众 1:团员 2:党员
     *
     * @param politicalState 政治面貌：0:群众 1:团员 2:党员
     */
    public void setPoliticalState(Integer politicalState) {
        this.politicalState = politicalState;
    }

    /**
     * 获取个人简介
     *
     * @return introduction - 个人简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置个人简介
     *
     * @param introduction 个人简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取个人状态: 0: 待业 1: 在职 2: 困难户等
     *
     * @return personnel_state - 个人状态: 0: 待业 1: 在职 2: 困难户等
     */
    public Integer getPersonnelState() {
        return personnelState;
    }

    /**
     * 设置个人状态: 0: 待业 1: 在职 2: 困难户等
     *
     * @param personnelState 个人状态: 0: 待业 1: 在职 2: 困难户等
     */
    public void setPersonnelState(Integer personnelState) {
        this.personnelState = personnelState;
    }

    /**
     * 获取个人照片路径
     *
     * @return portrait_path - 个人照片路径
     */
    public String getPortraitPath() {
        return portraitPath;
    }

    /**
     * 设置个人照片路径
     *
     * @param portraitPath 个人照片路径
     */
    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath == null ? null : portraitPath.trim();
    }

    /**
     * 获取期望薪资（最小）
     *
     * @return expect_salary_min - 期望薪资（最小）
     */
    public Integer getExpectSalaryMin() {
        return expectSalaryMin;
    }

    /**
     * 设置期望薪资（最小）
     *
     * @param expectSalaryMin 期望薪资（最小）
     */
    public void setExpectSalaryMin(Integer expectSalaryMin) {
        this.expectSalaryMin = expectSalaryMin;
    }

    /**
     * 获取期望薪资（最大）
     *
     * @return expect_salary_max - 期望薪资（最大）
     */
    public Integer getExpectSalaryMax() {
        return expectSalaryMax;
    }

    /**
     * 设置期望薪资（最大）
     *
     * @param expectSalaryMax 期望薪资（最大）
     */
    public void setExpectSalaryMax(Integer expectSalaryMax) {
        this.expectSalaryMax = expectSalaryMax;
    }

    /**
     * 获取期望职业（标签id  分号分割匹配）
     *
     * @return expect_category_id - 期望职业（标签id  分号分割匹配）
     */
    public String getExpectCategoryId() {
        return expectCategoryId;
    }

    /**
     * 设置期望职业（标签id  分号分割匹配）
     *
     * @param expectCategoryId 期望职业（标签id  分号分割匹配）
     */
    public void setExpectCategoryId(String expectCategoryId) {
        this.expectCategoryId = expectCategoryId == null ? null : expectCategoryId.trim();
    }

    /**
     * 获取期望工作社区id
     *
     * @return expect_community_id - 期望工作社区id
     */
    public String getExpectCommunityId() {
        return expectCommunityId;
    }

    /**
     * 设置期望工作社区id
     *
     * @param expectCommunityId 期望工作社区id
     */
    public void setExpectCommunityId(String expectCommunityId) {
        this.expectCommunityId = expectCommunityId == null ? null : expectCommunityId.trim();
    }

    /**
     * 获取学校名称
     *
     * @return edu_school_name - 学校名称
     */
    public String getEduSchoolName() {
        return eduSchoolName;
    }

    /**
     * 设置学校名称
     *
     * @param eduSchoolName 学校名称
     */
    public void setEduSchoolName(String eduSchoolName) {
        this.eduSchoolName = eduSchoolName == null ? null : eduSchoolName.trim();
    }

    /**
     * 获取学历
     *
     * @return edu_background_name - 学历
     */
    public String getEduBackgroundName() {
        return eduBackgroundName;
    }

    /**
     * 设置学历
     *
     * @param eduBackgroundName 学历
     */
    public void setEduBackgroundName(String eduBackgroundName) {
        this.eduBackgroundName = eduBackgroundName == null ? null : eduBackgroundName.trim();
    }

    /**
     * 获取专业名称
     *
     * @return edu_major - 专业名称
     */
    public String getEduMajor() {
        return eduMajor;
    }

    /**
     * 设置专业名称
     *
     * @param eduMajor 专业名称
     */
    public void setEduMajor(String eduMajor) {
        this.eduMajor = eduMajor == null ? null : eduMajor.trim();
    }

    /**
     * 获取教育开始时间
     *
     * @return edu_begin_time - 教育开始时间
     */
    public Date getEduBeginTime() {
        return eduBeginTime;
    }

    /**
     * 设置教育开始时间
     *
     * @param eduBeginTime 教育开始时间
     */
    public void setEduBeginTime(Date eduBeginTime) {
        this.eduBeginTime = eduBeginTime;
    }

    /**
     * 获取教育结束时间
     *
     * @return edu_end_time - 教育结束时间
     */
    public Date getEduEndTime() {
        return eduEndTime;
    }

    /**
     * 设置教育结束时间
     *
     * @param eduEndTime 教育结束时间
     */
    public void setEduEndTime(Date eduEndTime) {
        this.eduEndTime = eduEndTime;
    }

    /**
     * 获取是否实名认证（0：否 1：是）
     *
     * @return flag_identification - 是否实名认证（0：否 1：是）
     */
    public Integer getFlagIdentification() {
        return flagIdentification;
    }

    /**
     * 设置是否实名认证（0：否 1：是）
     *
     * @param flagIdentification 是否实名认证（0：否 1：是）
     */
    public void setFlagIdentification(Integer flagIdentification) {
        this.flagIdentification = flagIdentification;
    }

    /**
     * 获取被查看次数
     *
     * @return count_view - 被查看次数
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置被查看次数
     *
     * @param countView 被查看次数
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    /**
     * 获取关联 - 用户所在社区 id
     *
     * @return community_uuid - 关联 - 用户所在社区 id
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 - 用户所在社区 id
     *
     * @param communityUuid 关联 - 用户所在社区 id
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
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

    /**
     * 获取实习经历
     *
     * @return internship - 实习经历
     */
    public String getInternship() {
        return internship;
    }

    /**
     * 设置实习经历
     *
     * @param internship 实习经历
     */
    public void setInternship(String internship) {
        this.internship = internship == null ? null : internship.trim();
    }

    /**
     * 获取简历
     *
     * @return resume - 简历
     */
    public String getResume() {
        return resume;
    }

    /**
     * 设置简历
     *
     * @param resume 简历
     */
    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }
}