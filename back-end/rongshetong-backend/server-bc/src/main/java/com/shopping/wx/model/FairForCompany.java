package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fair_for_company")
public class FairForCompany {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 关联招聘会 - uuid
     */
    @Column(name = "fair_uuid")
    private String fairUuid;

    /**
     * 关联公司 - uuid
     */
    @Column(name = "company_uuid")
    private String companyUuid;

    /**
     * 关联招聘人 - openid
     */
    @Column(name = "recruiter_openid")
    private String recruiterOpenid;

    /**
     * 记录状态（0：正常 -1：过期不显示）
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
     * 获取uuid 主键
     *
     * @return id - uuid 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置uuid 主键
     *
     * @param id uuid 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取关联招聘会 - uuid
     *
     * @return fair_uuid - 关联招聘会 - uuid
     */
    public String getFairUuid() {
        return fairUuid;
    }

    /**
     * 设置关联招聘会 - uuid
     *
     * @param fairUuid 关联招聘会 - uuid
     */
    public void setFairUuid(String fairUuid) {
        this.fairUuid = fairUuid == null ? null : fairUuid.trim();
    }

    /**
     * 获取关联公司 - uuid
     *
     * @return company_uuid - 关联公司 - uuid
     */
    public String getCompanyUuid() {
        return companyUuid;
    }

    /**
     * 设置关联公司 - uuid
     *
     * @param companyUuid 关联公司 - uuid
     */
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid == null ? null : companyUuid.trim();
    }

    /**
     * 获取关联招聘人 - openid
     *
     * @return recruiter_openid - 关联招聘人 - openid
     */
    public String getRecruiterOpenid() {
        return recruiterOpenid;
    }

    /**
     * 设置关联招聘人 - openid
     *
     * @param recruiterOpenid 关联招聘人 - openid
     */
    public void setRecruiterOpenid(String recruiterOpenid) {
        this.recruiterOpenid = recruiterOpenid == null ? null : recruiterOpenid.trim();
    }

    /**
     * 获取记录状态（0：正常 -1：过期不显示）
     *
     * @return status - 记录状态（0：正常 -1：过期不显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置记录状态（0：正常 -1：过期不显示）
     *
     * @param status 记录状态（0：正常 -1：过期不显示）
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