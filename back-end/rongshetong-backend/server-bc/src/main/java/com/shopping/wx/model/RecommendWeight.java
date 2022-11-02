package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "recommend_weight")
public class RecommendWeight {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 求职者用户_id
     */
    @Column(name = "candidate_openid")
    private String candidateOpenid;

    /**
     * 岗位标签_id
     */
    @Column(name = "category_uuid")
    private String categoryUuid;

    /**
     * 浏览次数
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 本条状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 本条录入时间
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
     * 获取求职者用户_id
     *
     * @return candidate_openid - 求职者用户_id
     */
    public String getCandidateOpenid() {
        return candidateOpenid;
    }

    /**
     * 设置求职者用户_id
     *
     * @param candidateOpenid 求职者用户_id
     */
    public void setCandidateOpenid(String candidateOpenid) {
        this.candidateOpenid = candidateOpenid == null ? null : candidateOpenid.trim();
    }

    /**
     * 获取岗位标签_id
     *
     * @return category_uuid - 岗位标签_id
     */
    public String getCategoryUuid() {
        return categoryUuid;
    }

    /**
     * 设置岗位标签_id
     *
     * @param categoryUuid 岗位标签_id
     */
    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid == null ? null : categoryUuid.trim();
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
     * 获取本条录入时间
     *
     * @return create_time - 本条录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置本条录入时间
     *
     * @param createTime 本条录入时间
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