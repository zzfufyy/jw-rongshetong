package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "candidate_for_category")
public class CandidateForCategory {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 求职者用户openid
     */
    @Column(name = "candidate_openid")
    private String candidateOpenid;

    /**
     * 标签类别id
     */
    @Column(name = "category_uuid")
    private String categoryUuid;

    /**
     * 标签类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

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
     * 获取求职者用户openid
     *
     * @return candidate_openid - 求职者用户openid
     */
    public String getCandidateOpenid() {
        return candidateOpenid;
    }

    /**
     * 设置求职者用户openid
     *
     * @param candidateOpenid 求职者用户openid
     */
    public void setCandidateOpenid(String candidateOpenid) {
        this.candidateOpenid = candidateOpenid == null ? null : candidateOpenid.trim();
    }

    /**
     * 获取标签类别id
     *
     * @return category_uuid - 标签类别id
     */
    public String getCategoryUuid() {
        return categoryUuid;
    }

    /**
     * 设置标签类别id
     *
     * @param categoryUuid 标签类别id
     */
    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid == null ? null : categoryUuid.trim();
    }

    /**
     * 获取标签类别名称
     *
     * @return category_name - 标签类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置标签类别名称
     *
     * @param categoryName 标签类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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