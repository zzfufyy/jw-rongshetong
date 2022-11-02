package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_recruiter")
public class UserRecruiter {
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
     * 联系电话
     */
    private String telephone;

    /**
     * 性别 0:男 1:女
     */
    private Integer gender;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 招聘人职位
     */
    @Column(name = "job_position")
    private String jobPosition;

    /**
     * 招聘人头像路径
     */
    @Column(name = "portrait_path")
    private String portraitPath;

    /**
     * 浏览次数
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 关联 - recruit_company id
     */
    @Column(name = "company_uuid")
    private String companyUuid;

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
     * 获取联系电话
     *
     * @return telephone - 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     *
     * @param telephone 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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
     * 获取联系邮箱
     *
     * @return email - 联系邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置联系邮箱
     *
     * @param email 联系邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取招聘人职位
     *
     * @return job_position - 招聘人职位
     */
    public String getJobPosition() {
        return jobPosition;
    }

    /**
     * 设置招聘人职位
     *
     * @param jobPosition 招聘人职位
     */
    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition == null ? null : jobPosition.trim();
    }

    /**
     * 获取招聘人头像路径
     *
     * @return portrait_path - 招聘人头像路径
     */
    public String getPortraitPath() {
        return portraitPath;
    }

    /**
     * 设置招聘人头像路径
     *
     * @param portraitPath 招聘人头像路径
     */
    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath == null ? null : portraitPath.trim();
    }

    /**
     * 获取浏览次数
     *
     * @return count_view - 浏览次数
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置浏览次数
     *
     * @param countView 浏览次数
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    /**
     * 获取关联 - recruit_company id
     *
     * @return company_uuid - 关联 - recruit_company id
     */
    public String getCompanyUuid() {
        return companyUuid;
    }

    /**
     * 设置关联 - recruit_company id
     *
     * @param companyUuid 关联 - recruit_company id
     */
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid == null ? null : companyUuid.trim();
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
}