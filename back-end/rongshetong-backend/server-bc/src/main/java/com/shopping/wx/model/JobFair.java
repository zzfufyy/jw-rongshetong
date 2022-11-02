package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "job_fair")
public class JobFair {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 招聘会文章标题
     */
    @Column(name = "fair_title")
    private String fairTitle;

    /**
     * 招聘会banner图片
     */
    @Column(name = "fair_portrait_path")
    private String fairPortraitPath;

    /**
     * 招聘会地址
     */
    @Column(name = "fair_address")
    private String fairAddress;

    /**
     * 招聘会主办方
     */
    @Column(name = "fair_host")
    private String fairHost;

    /**
     * 招聘会承办方
     */
    @Column(name = "fair_organizer")
    private String fairOrganizer;

    /**
     * 招聘会发布时间
     */
    @Column(name = "fair_release_time")
    private Date fairReleaseTime;

    /**
     * 招聘会开始时间
     */
    @Column(name = "fair_begin_time")
    private Date fairBeginTime;

    /**
     * 招聘会结束时间
     */
    @Column(name = "fair_end_time")
    private Date fairEndTime;

    /**
     * 链接地址
     */
    @Column(name = "link_path")
    private String linkPath;

    /**
     * 链接参数
     */
    @Column(name = "link_path_param")
    private String linkPathParam;

    /**
     * 图片链接地址
     */
    @Column(name = "link_photo_path")
    private String linkPhotoPath;

    /**
     * 浏览量
     */
    @Column(name = "count_view")
    private Integer countView;

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
     * 招聘会文章内容（富文本）
     */
    @Column(name = "fair_content")
    private String fairContent;

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
     * 获取招聘会文章标题
     *
     * @return fair_title - 招聘会文章标题
     */
    public String getFairTitle() {
        return fairTitle;
    }

    /**
     * 设置招聘会文章标题
     *
     * @param fairTitle 招聘会文章标题
     */
    public void setFairTitle(String fairTitle) {
        this.fairTitle = fairTitle == null ? null : fairTitle.trim();
    }

    /**
     * 获取招聘会banner图片
     *
     * @return fair_portrait_path - 招聘会banner图片
     */
    public String getFairPortraitPath() {
        return fairPortraitPath;
    }

    /**
     * 设置招聘会banner图片
     *
     * @param fairPortraitPath 招聘会banner图片
     */
    public void setFairPortraitPath(String fairPortraitPath) {
        this.fairPortraitPath = fairPortraitPath == null ? null : fairPortraitPath.trim();
    }

    /**
     * 获取招聘会地址
     *
     * @return fair_address - 招聘会地址
     */
    public String getFairAddress() {
        return fairAddress;
    }

    /**
     * 设置招聘会地址
     *
     * @param fairAddress 招聘会地址
     */
    public void setFairAddress(String fairAddress) {
        this.fairAddress = fairAddress == null ? null : fairAddress.trim();
    }

    /**
     * 获取招聘会主办方
     *
     * @return fair_host - 招聘会主办方
     */
    public String getFairHost() {
        return fairHost;
    }

    /**
     * 设置招聘会主办方
     *
     * @param fairHost 招聘会主办方
     */
    public void setFairHost(String fairHost) {
        this.fairHost = fairHost == null ? null : fairHost.trim();
    }

    /**
     * 获取招聘会承办方
     *
     * @return fair_organizer - 招聘会承办方
     */
    public String getFairOrganizer() {
        return fairOrganizer;
    }

    /**
     * 设置招聘会承办方
     *
     * @param fairOrganizer 招聘会承办方
     */
    public void setFairOrganizer(String fairOrganizer) {
        this.fairOrganizer = fairOrganizer == null ? null : fairOrganizer.trim();
    }

    /**
     * 获取招聘会发布时间
     *
     * @return fair_release_time - 招聘会发布时间
     */
    public Date getFairReleaseTime() {
        return fairReleaseTime;
    }

    /**
     * 设置招聘会发布时间
     *
     * @param fairReleaseTime 招聘会发布时间
     */
    public void setFairReleaseTime(Date fairReleaseTime) {
        this.fairReleaseTime = fairReleaseTime;
    }

    /**
     * 获取招聘会开始时间
     *
     * @return fair_begin_time - 招聘会开始时间
     */
    public Date getFairBeginTime() {
        return fairBeginTime;
    }

    /**
     * 设置招聘会开始时间
     *
     * @param fairBeginTime 招聘会开始时间
     */
    public void setFairBeginTime(Date fairBeginTime) {
        this.fairBeginTime = fairBeginTime;
    }

    /**
     * 获取招聘会结束时间
     *
     * @return fair_end_time - 招聘会结束时间
     */
    public Date getFairEndTime() {
        return fairEndTime;
    }

    /**
     * 设置招聘会结束时间
     *
     * @param fairEndTime 招聘会结束时间
     */
    public void setFairEndTime(Date fairEndTime) {
        this.fairEndTime = fairEndTime;
    }

    /**
     * 获取链接地址
     *
     * @return link_path - 链接地址
     */
    public String getLinkPath() {
        return linkPath;
    }

    /**
     * 设置链接地址
     *
     * @param linkPath 链接地址
     */
    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath == null ? null : linkPath.trim();
    }

    /**
     * 获取链接参数
     *
     * @return link_path_param - 链接参数
     */
    public String getLinkPathParam() {
        return linkPathParam;
    }

    /**
     * 设置链接参数
     *
     * @param linkPathParam 链接参数
     */
    public void setLinkPathParam(String linkPathParam) {
        this.linkPathParam = linkPathParam == null ? null : linkPathParam.trim();
    }

    /**
     * 获取图片链接地址
     *
     * @return link_photo_path - 图片链接地址
     */
    public String getLinkPhotoPath() {
        return linkPhotoPath;
    }

    /**
     * 设置图片链接地址
     *
     * @param linkPhotoPath 图片链接地址
     */
    public void setLinkPhotoPath(String linkPhotoPath) {
        this.linkPhotoPath = linkPhotoPath == null ? null : linkPhotoPath.trim();
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

    /**
     * 获取招聘会文章内容（富文本）
     *
     * @return fair_content - 招聘会文章内容（富文本）
     */
    public String getFairContent() {
        return fairContent;
    }

    /**
     * 设置招聘会文章内容（富文本）
     *
     * @param fairContent 招聘会文章内容（富文本）
     */
    public void setFairContent(String fairContent) {
        this.fairContent = fairContent == null ? null : fairContent.trim();
    }
}