package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "recruit_record")
public class RecruitRecord {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 应聘人员姓名
     */
    @Column(name = "candidate_name")
    private String candidateName;

    /**
     * 应聘人手机
     */
    @Column(name = "candidate_telephone")
    private String candidateTelephone;

    /**
     * 应聘人邮箱
     */
    @Column(name = "candidate_email")
    private String candidateEmail;

    /**
     * 应聘人出生日期
     */
    @Column(name = "candidate_birthday")
    private Date candidateBirthday;

    /**
     * 招聘人员姓名
     */
    @Column(name = "recruiter_name")
    private String recruiterName;

    /**
     * 招聘公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 招聘人联系电话
     */
    @Column(name = "recruiter_telephone")
    private String recruiterTelephone;

    /**
     * 招聘人联系邮箱
     */
    @Column(name = "recruiter_email")
    private String recruiterEmail;

    /**
     * 招聘岗位名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 工作地址
     */
    @Column(name = "job_address")
    private String jobAddress;

    /**
     * 薪酬范围（根据发布薪酬生成）
     */
    @Column(name = "salary_scope")
    private String salaryScope;

    /**
     * 接收方是谁（0:求职者 1:招聘者）
     */
    @Column(name = "flag_who_receive")
    private Integer flagWhoReceive;

    /**
     * 该条信息是否已被接收方查看
     */
    @Column(name = "flag_view_receive")
    private Integer flagViewReceive;

    /**
     * 应聘流程(-1:不合适  0：待查看 1: 已查看 2: 待面试 3面试通过)
     */
    @Column(name = "flow_recruit")
    private Integer flowRecruit;

    /**
     * 应聘是否成功（-1：失败 0：无状态 1：成功）
     */
    @Column(name = "flag_result")
    private Integer flagResult;

    /**
     * 关联 - 求职者openid
     */
    @Column(name = "candidate_openid")
    private String candidateOpenid;

    /**
     * 关联 - 招聘者openid
     */
    @Column(name = "recruiter_openid")
    private String recruiterOpenid;

    /**
     * 关联 - 公司uuid
     */
    @Column(name = "company_uuid")
    private String companyUuid;

    /**
     * 关联 - 发布岗位uuid
     */
    @Column(name = "job_uuid")
    private String jobUuid;

    /**
     * 关联 - 岗位标签uuid
     */
    @Column(name = "category_uuid")
    private String categoryUuid;

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
     * 获取应聘人员姓名
     *
     * @return candidate_name - 应聘人员姓名
     */
    public String getCandidateName() {
        return candidateName;
    }

    /**
     * 设置应聘人员姓名
     *
     * @param candidateName 应聘人员姓名
     */
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName == null ? null : candidateName.trim();
    }

    /**
     * 获取应聘人手机
     *
     * @return candidate_telephone - 应聘人手机
     */
    public String getCandidateTelephone() {
        return candidateTelephone;
    }

    /**
     * 设置应聘人手机
     *
     * @param candidateTelephone 应聘人手机
     */
    public void setCandidateTelephone(String candidateTelephone) {
        this.candidateTelephone = candidateTelephone == null ? null : candidateTelephone.trim();
    }

    /**
     * 获取应聘人邮箱
     *
     * @return candidate_email - 应聘人邮箱
     */
    public String getCandidateEmail() {
        return candidateEmail;
    }

    /**
     * 设置应聘人邮箱
     *
     * @param candidateEmail 应聘人邮箱
     */
    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail == null ? null : candidateEmail.trim();
    }

    /**
     * 获取应聘人出生日期
     *
     * @return candidate_birthday - 应聘人出生日期
     */
    public Date getCandidateBirthday() {
        return candidateBirthday;
    }

    /**
     * 设置应聘人出生日期
     *
     * @param candidateBirthday 应聘人出生日期
     */
    public void setCandidateBirthday(Date candidateBirthday) {
        this.candidateBirthday = candidateBirthday;
    }

    /**
     * 获取招聘人员姓名
     *
     * @return recruiter_name - 招聘人员姓名
     */
    public String getRecruiterName() {
        return recruiterName;
    }

    /**
     * 设置招聘人员姓名
     *
     * @param recruiterName 招聘人员姓名
     */
    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName == null ? null : recruiterName.trim();
    }

    /**
     * 获取招聘公司名称
     *
     * @return company_name - 招聘公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置招聘公司名称
     *
     * @param companyName 招聘公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取招聘人联系电话
     *
     * @return recruiter_telephone - 招聘人联系电话
     */
    public String getRecruiterTelephone() {
        return recruiterTelephone;
    }

    /**
     * 设置招聘人联系电话
     *
     * @param recruiterTelephone 招聘人联系电话
     */
    public void setRecruiterTelephone(String recruiterTelephone) {
        this.recruiterTelephone = recruiterTelephone == null ? null : recruiterTelephone.trim();
    }

    /**
     * 获取招聘人联系邮箱
     *
     * @return recruiter_email - 招聘人联系邮箱
     */
    public String getRecruiterEmail() {
        return recruiterEmail;
    }

    /**
     * 设置招聘人联系邮箱
     *
     * @param recruiterEmail 招聘人联系邮箱
     */
    public void setRecruiterEmail(String recruiterEmail) {
        this.recruiterEmail = recruiterEmail == null ? null : recruiterEmail.trim();
    }

    /**
     * 获取招聘岗位名称
     *
     * @return job_name - 招聘岗位名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置招聘岗位名称
     *
     * @param jobName 招聘岗位名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 获取工作地址
     *
     * @return job_address - 工作地址
     */
    public String getJobAddress() {
        return jobAddress;
    }

    /**
     * 设置工作地址
     *
     * @param jobAddress 工作地址
     */
    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress == null ? null : jobAddress.trim();
    }

    /**
     * 获取薪酬范围（根据发布薪酬生成）
     *
     * @return salary_scope - 薪酬范围（根据发布薪酬生成）
     */
    public String getSalaryScope() {
        return salaryScope;
    }

    /**
     * 设置薪酬范围（根据发布薪酬生成）
     *
     * @param salaryScope 薪酬范围（根据发布薪酬生成）
     */
    public void setSalaryScope(String salaryScope) {
        this.salaryScope = salaryScope == null ? null : salaryScope.trim();
    }

    /**
     * 获取接收方是谁（0:求职者 1:招聘者）
     *
     * @return flag_who_receive - 接收方是谁（0:求职者 1:招聘者）
     */
    public Integer getFlagWhoReceive() {
        return flagWhoReceive;
    }

    /**
     * 设置接收方是谁（0:求职者 1:招聘者）
     *
     * @param flagWhoReceive 接收方是谁（0:求职者 1:招聘者）
     */
    public void setFlagWhoReceive(Integer flagWhoReceive) {
        this.flagWhoReceive = flagWhoReceive;
    }

    /**
     * 获取该条信息是否已被接收方查看
     *
     * @return flag_view_receive - 该条信息是否已被接收方查看
     */
    public Integer getFlagViewReceive() {
        return flagViewReceive;
    }

    /**
     * 设置该条信息是否已被接收方查看
     *
     * @param flagViewReceive 该条信息是否已被接收方查看
     */
    public void setFlagViewReceive(Integer flagViewReceive) {
        this.flagViewReceive = flagViewReceive;
    }

    /**
     * 获取应聘流程(-1:不合适  0：待查看 1: 沟通中 2: 结束)
     *
     * @return flow_recruit - 应聘流程(-1:不合适  0：待查看 1: 沟通中 2: 结束)
     */
    public Integer getFlowRecruit() {
        return flowRecruit;
    }

    /**
     * 设置应聘流程(-1:不合适  0：待查看 1: 沟通中 2: 结束)
     *
     * @param flowRecruit 应聘流程(-1:不合适  0：待查看 1: 沟通中 2: 结束)
     */
    public void setFlowRecruit(Integer flowRecruit) {
        this.flowRecruit = flowRecruit;
    }

    /**
     * 获取应聘是否成功（-1：失败 0：无状态 1：成功）
     *
     * @return flag_result - 应聘是否成功（-1：失败 0：无状态 1：成功）
     */
    public Integer getFlagResult() {
        return flagResult;
    }

    /**
     * 设置应聘是否成功（-1：失败 0：无状态 1：成功）
     *
     * @param flagResult 应聘是否成功（-1：失败 0：无状态 1：成功）
     */
    public void setFlagResult(Integer flagResult) {
        this.flagResult = flagResult;
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
     * 获取关联 - 招聘者openid
     *
     * @return recruiter_openid - 关联 - 招聘者openid
     */
    public String getRecruiterOpenid() {
        return recruiterOpenid;
    }

    /**
     * 设置关联 - 招聘者openid
     *
     * @param recruiterOpenid 关联 - 招聘者openid
     */
    public void setRecruiterOpenid(String recruiterOpenid) {
        this.recruiterOpenid = recruiterOpenid == null ? null : recruiterOpenid.trim();
    }

    /**
     * 获取关联 - 公司uuid
     *
     * @return company_uuid - 关联 - 公司uuid
     */
    public String getCompanyUuid() {
        return companyUuid;
    }

    /**
     * 设置关联 - 公司uuid
     *
     * @param companyUuid 关联 - 公司uuid
     */
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid == null ? null : companyUuid.trim();
    }

    /**
     * 获取关联 - 发布岗位uuid
     *
     * @return job_uuid - 关联 - 发布岗位uuid
     */
    public String getJobUuid() {
        return jobUuid;
    }

    /**
     * 设置关联 - 发布岗位uuid
     *
     * @param jobUuid 关联 - 发布岗位uuid
     */
    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid == null ? null : jobUuid.trim();
    }

    /**
     * 获取关联 - 岗位标签uuid
     *
     * @return category_uuid - 关联 - 岗位标签uuid
     */
    public String getCategoryUuid() {
        return categoryUuid;
    }

    /**
     * 设置关联 - 岗位标签uuid
     *
     * @param categoryUuid 关联 - 岗位标签uuid
     */
    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid == null ? null : categoryUuid.trim();
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