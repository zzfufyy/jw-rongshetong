package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "community_building")
public class CommunityBuilding {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 楼宇 -- 楼栋名称
     */
    @Column(name = "building_name")
    private String buildingName;

    /**
     * 社区名称
     */
    @Column(name = "community_name")
    private String communityName;

    /**
     * 街道名称
     */
    @Column(name = "street_name")
    private String streetName;

    /**
     * 区域名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 头像
     */
    @Column(name = "portrait_path")
    private String portraitPath;

    /**
     * 地址
     */
    private String address;

    /**
     * 关联 -- community_uuid
     */
    @Column(name = "community_uuid")
    private String communityUuid;

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
     * 获取楼宇 -- 楼栋名称
     *
     * @return building_name - 楼宇 -- 楼栋名称
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * 设置楼宇 -- 楼栋名称
     *
     * @param buildingName 楼宇 -- 楼栋名称
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    /**
     * 获取社区名称
     *
     * @return community_name - 社区名称
     */
    public String getCommunityName() {
        return communityName;
    }

    /**
     * 设置社区名称
     *
     * @param communityName 社区名称
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    /**
     * 获取街道名称
     *
     * @return street_name - 街道名称
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * 设置街道名称
     *
     * @param streetName 街道名称
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    /**
     * 获取区域名称
     *
     * @return district_name - 区域名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区域名称
     *
     * @param districtName 区域名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 获取头像
     *
     * @return portrait_path - 头像
     */
    public String getPortraitPath() {
        return portraitPath;
    }

    /**
     * 设置头像
     *
     * @param portraitPath 头像
     */
    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath == null ? null : portraitPath.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取关联 -- community_uuid
     *
     * @return community_uuid - 关联 -- community_uuid
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 -- community_uuid
     *
     * @param communityUuid 关联 -- community_uuid
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
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