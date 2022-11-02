package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "job_category")
public class JobCategory {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 标签类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 父id
     */
    private String pid;

    /**
     * 祖父id
     */
    private String ppid;

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
     * 关键字
     */
    private String keyword;

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
     * 获取父id
     *
     * @return pid - 父id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父id
     *
     * @param pid 父id
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取祖父id
     *
     * @return ppid - 祖父id
     */
    public String getPpid() {
        return ppid;
    }

    /**
     * 设置祖父id
     *
     * @param ppid 祖父id
     */
    public void setPpid(String ppid) {
        this.ppid = ppid == null ? null : ppid.trim();
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
     * 获取关键字
     *
     * @return keyword - 关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键字
     *
     * @param keyword 关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
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