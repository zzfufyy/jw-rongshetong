package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "function_tag")
public class FunctionTag {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 标签-标识
     */
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 标签-名称
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 作用页面路径
     */
    @Column(name = "page_path_apply")
    private String pagePathApply;

    /**
     * 连接页面路径
     */
    @Column(name = "page_path_link")
    private String pagePathLink;

    /**
     * 背景颜色
     */
    @Column(name = "background_color")
    private String backgroundColor;

    /**
     * logo（小）路径
     */
    @Column(name = "logo_path_small")
    private String logoPathSmall;

    /**
     * logo（大）路径
     */
    @Column(name = "logo_path_big")
    private String logoPathBig;

    /**
     * 跳转方式 默认 0： navigateto跳转
     */
    @Column(name = "link_method")
    private Integer linkMethod;

    /**
     * 跳转目标
     */
    private String target;

    /**
     * 小程序id
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 默认优先级
     */
    private Integer priority;

    /**
     * 是否为定制化功能 默认 0： 非定制化 1: 定制化
     */
    @Column(name = "is_custom")
    private Integer isCustom;

    /**
     * 定制化影响社区id,分号分割
     */
    @Column(name = "effect_community_uuid")
    private String effectCommunityUuid;

    /**
     * 本条信息状态（0：正常 -1：失效）
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
     * 获取标签-标识
     *
     * @return tag_id - 标签-标识
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 设置标签-标识
     *
     * @param tagId 标签-标识
     */
    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    /**
     * 获取标签-名称
     *
     * @return tag_name - 标签-名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签-名称
     *
     * @param tagName 标签-名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取作用页面路径
     *
     * @return page_path_apply - 作用页面路径
     */
    public String getPagePathApply() {
        return pagePathApply;
    }

    /**
     * 设置作用页面路径
     *
     * @param pagePathApply 作用页面路径
     */
    public void setPagePathApply(String pagePathApply) {
        this.pagePathApply = pagePathApply == null ? null : pagePathApply.trim();
    }

    /**
     * 获取连接页面路径
     *
     * @return page_path_link - 连接页面路径
     */
    public String getPagePathLink() {
        return pagePathLink;
    }

    /**
     * 设置连接页面路径
     *
     * @param pagePathLink 连接页面路径
     */
    public void setPagePathLink(String pagePathLink) {
        this.pagePathLink = pagePathLink == null ? null : pagePathLink.trim();
    }

    /**
     * 获取背景颜色
     *
     * @return background_color - 背景颜色
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor 背景颜色
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor == null ? null : backgroundColor.trim();
    }

    /**
     * 获取logo（小）路径
     *
     * @return logo_path_small - logo（小）路径
     */
    public String getLogoPathSmall() {
        return logoPathSmall;
    }

    /**
     * 设置logo（小）路径
     *
     * @param logoPathSmall logo（小）路径
     */
    public void setLogoPathSmall(String logoPathSmall) {
        this.logoPathSmall = logoPathSmall == null ? null : logoPathSmall.trim();
    }

    /**
     * 获取logo（大）路径
     *
     * @return logo_path_big - logo（大）路径
     */
    public String getLogoPathBig() {
        return logoPathBig;
    }

    /**
     * 设置logo（大）路径
     *
     * @param logoPathBig logo（大）路径
     */
    public void setLogoPathBig(String logoPathBig) {
        this.logoPathBig = logoPathBig == null ? null : logoPathBig.trim();
    }

    /**
     * 获取跳转方式 默认 0： navigateto跳转
     *
     * @return link_method - 跳转方式 默认 0： navigateto跳转
     */
    public Integer getLinkMethod() {
        return linkMethod;
    }

    /**
     * 设置跳转方式 默认 0： navigateto跳转
     *
     * @param linkMethod 跳转方式 默认 0： navigateto跳转
     */
    public void setLinkMethod(Integer linkMethod) {
        this.linkMethod = linkMethod;
    }

    /**
     * 获取跳转目标
     *
     * @return target - 跳转目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置跳转目标
     *
     * @param target 跳转目标
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 获取小程序id
     *
     * @return app_id - 小程序id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置小程序id
     *
     * @param appId 小程序id
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取默认优先级
     *
     * @return priority - 默认优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置默认优先级
     *
     * @param priority 默认优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取是否为定制化功能 默认 0： 非定制化 1: 定制化
     *
     * @return is_custom - 是否为定制化功能 默认 0： 非定制化 1: 定制化
     */
    public Integer getIsCustom() {
        return isCustom;
    }

    /**
     * 设置是否为定制化功能 默认 0： 非定制化 1: 定制化
     *
     * @param isCustom 是否为定制化功能 默认 0： 非定制化 1: 定制化
     */
    public void setIsCustom(Integer isCustom) {
        this.isCustom = isCustom;
    }

    /**
     * 获取定制化影响社区id,分号分割
     *
     * @return effect_community_uuid - 定制化影响社区id,分号分割
     */
    public String getEffectCommunityUuid() {
        return effectCommunityUuid;
    }

    /**
     * 设置定制化影响社区id,分号分割
     *
     * @param effectCommunityUuid 定制化影响社区id,分号分割
     */
    public void setEffectCommunityUuid(String effectCommunityUuid) {
        this.effectCommunityUuid = effectCommunityUuid == null ? null : effectCommunityUuid.trim();
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