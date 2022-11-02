package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "banner_for_page")
public class BannerForPage {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 图片标识名称
     */
    @Column(name = "banner_name")
    private String bannerName;

    /**
     * 图片路径
     */
    @Column(name = "banner_img_path")
    private String bannerImgPath;

    /**
     * 页面路径（如：/page/index/index）
     */
    @Column(name = "page_path")
    private String pagePath;

    /**
     * 用户角色（0: 所有角色  1: 求职者 2: 招聘者）
     */
    @Column(name = "user_role")
    private Integer userRole;

    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 是否对本页面所有社区/用户有效（0: 否 1: 是）
     */
    @Column(name = "is_global")
    private Integer isGlobal;

    /**
     * 优先级 高-大
     */
    private Integer priority;

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
     * 获取图片标识名称
     *
     * @return banner_name - 图片标识名称
     */
    public String getBannerName() {
        return bannerName;
    }

    /**
     * 设置图片标识名称
     *
     * @param bannerName 图片标识名称
     */
    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    /**
     * 获取图片路径
     *
     * @return banner_img_path - 图片路径
     */
    public String getBannerImgPath() {
        return bannerImgPath;
    }

    /**
     * 设置图片路径
     *
     * @param bannerImgPath 图片路径
     */
    public void setBannerImgPath(String bannerImgPath) {
        this.bannerImgPath = bannerImgPath == null ? null : bannerImgPath.trim();
    }

    /**
     * 获取页面路径（如：/page/index/index）
     *
     * @return page_path - 页面路径（如：/page/index/index）
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * 设置页面路径（如：/page/index/index）
     *
     * @param pagePath 页面路径（如：/page/index/index）
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath == null ? null : pagePath.trim();
    }

    /**
     * 获取用户角色（0: 所有角色  1: 求职者 2: 招聘者）
     *
     * @return user_role - 用户角色（0: 所有角色  1: 求职者 2: 招聘者）
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置用户角色（0: 所有角色  1: 求职者 2: 招聘者）
     *
     * @param userRole 用户角色（0: 所有角色  1: 求职者 2: 招聘者）
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * @return community_uuid
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * @param communityUuid
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
    }

    /**
     * 获取是否对本页面所有社区/用户有效（0: 否 1: 是）
     *
     * @return is_global - 是否对本页面所有社区/用户有效（0: 否 1: 是）
     */
    public Integer getIsGlobal() {
        return isGlobal;
    }

    /**
     * 设置是否对本页面所有社区/用户有效（0: 否 1: 是）
     *
     * @param isGlobal 是否对本页面所有社区/用户有效（0: 否 1: 是）
     */
    public void setIsGlobal(Integer isGlobal) {
        this.isGlobal = isGlobal;
    }

    /**
     * 获取优先级 高-大
     *
     * @return priority - 优先级 高-大
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级 高-大
     *
     * @param priority 优先级 高-大
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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