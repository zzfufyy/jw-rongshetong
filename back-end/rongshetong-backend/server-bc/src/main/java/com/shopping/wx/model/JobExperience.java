package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "job_experience")
public class JobExperience {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 职位名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 工作入职时间
     */
    @Column(name = "job_begin_time")
    private String jobBeginTime;

    /**
     * 工作离职时间
     */
    @Column(name = "job_end_time")
    private String jobEndTime;

    /**
     * 工作内容
     */
    @Column(name = "job_content")
    private String jobContent;

    /**
     * 关联 - 求职者openid
     */
    @Column(name = "candidate_openid")
    private String candidateOpenid;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 0： 全职 1: 实习
     */
    @Column(name = "job_type")
    private Integer jobType;

    /**
     * 本条状态（0：正常 -1: 不显示）
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
     * 获取职位名称
     *
     * @return job_name - 职位名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置职位名称
     *
     * @param jobName 职位名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 获取工作入职时间
     *
     * @return job_begin_time - 工作入职时间
     */
    public String getJobBeginTime() {
        return jobBeginTime;
    }

    /**
     * 设置工作入职时间
     *
     * @param jobBeginTime 工作入职时间
     */
    public void setJobBeginTime(String jobBeginTime) {
        this.jobBeginTime = jobBeginTime == null ? null : jobBeginTime.trim();
    }

    /**
     * 获取工作离职时间
     *
     * @return job_end_time - 工作离职时间
     */
    public String getJobEndTime() {
        return jobEndTime;
    }

    /**
     * 设置工作离职时间
     *
     * @param jobEndTime 工作离职时间
     */
    public void setJobEndTime(String jobEndTime) {
        this.jobEndTime = jobEndTime == null ? null : jobEndTime.trim();
    }

    /**
     * 获取工作内容
     *
     * @return job_content - 工作内容
     */
    public String getJobContent() {
        return jobContent;
    }

    /**
     * 设置工作内容
     *
     * @param jobContent 工作内容
     */
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent == null ? null : jobContent.trim();
    }

    /**
     * 获取关联 - 求职者openid
     *
     * @return candidate_openid - 关联 - 求职者openid
     */
    public String getCandidateOpenid() {
        return candidateOpenid;
    }

    /**
     * 设置关联 - 求职者openid
     *
     * @param candidateOpenid 关联 - 求职者openid
     */
    public void setCandidateOpenid(String candidateOpenid) {
        this.candidateOpenid = candidateOpenid == null ? null : candidateOpenid.trim();
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
     * 获取0： 全职 1: 实习
     *
     * @return job_type - 0： 全职 1: 实习
     */
    public Integer getJobType() {
        return jobType;
    }

    /**
     * 设置0： 全职 1: 实习
     *
     * @param jobType 0： 全职 1: 实习
     */
    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    /**
     * 获取本条状态（0：正常 -1: 不显示）
     *
     * @return status - 本条状态（0：正常 -1: 不显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1: 不显示）
     *
     * @param status 本条状态（0：正常 -1: 不显示）
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