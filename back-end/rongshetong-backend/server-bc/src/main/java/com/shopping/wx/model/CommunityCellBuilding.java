package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "community_cell_building")
public class CommunityCellBuilding {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 楼栋排序
     */
    @Column(name = "building_order")
    private Integer buildingOrder;

    /**
     * 楼栋名称
     */
    @Column(name = "building_name")
    private String buildingName;

    /**
     * 该楼栋层数
     */
    @Column(name = "num_layer")
    private Integer numLayer;

    /**
     * 每层户数
     */
    @Column(name = "num_layer_family")
    private Integer numLayerFamily;

    /**
     * 关联 - 小区uuid
     */
    @Column(name = "cell_uuid")
    private String cellUuid;

    /**
     * 关联 - 社区uuid
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
     * 获取楼栋排序
     *
     * @return building_order - 楼栋排序
     */
    public Integer getBuildingOrder() {
        return buildingOrder;
    }

    /**
     * 设置楼栋排序
     *
     * @param buildingOrder 楼栋排序
     */
    public void setBuildingOrder(Integer buildingOrder) {
        this.buildingOrder = buildingOrder;
    }

    /**
     * 获取楼栋名称
     *
     * @return building_name - 楼栋名称
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * 设置楼栋名称
     *
     * @param buildingName 楼栋名称
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    /**
     * 获取该楼栋层数
     *
     * @return num_layer - 该楼栋层数
     */
    public Integer getNumLayer() {
        return numLayer;
    }

    /**
     * 设置该楼栋层数
     *
     * @param numLayer 该楼栋层数
     */
    public void setNumLayer(Integer numLayer) {
        this.numLayer = numLayer;
    }

    /**
     * 获取每层户数
     *
     * @return num_layer_family - 每层户数
     */
    public Integer getNumLayerFamily() {
        return numLayerFamily;
    }

    /**
     * 设置每层户数
     *
     * @param numLayerFamily 每层户数
     */
    public void setNumLayerFamily(Integer numLayerFamily) {
        this.numLayerFamily = numLayerFamily;
    }

    /**
     * 获取关联 - 小区uuid
     *
     * @return cell_uuid - 关联 - 小区uuid
     */
    public String getCellUuid() {
        return cellUuid;
    }

    /**
     * 设置关联 - 小区uuid
     *
     * @param cellUuid 关联 - 小区uuid
     */
    public void setCellUuid(String cellUuid) {
        this.cellUuid = cellUuid == null ? null : cellUuid.trim();
    }

    /**
     * 获取关联 - 社区uuid
     *
     * @return community_uuid - 关联 - 社区uuid
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 - 社区uuid
     *
     * @param communityUuid 关联 - 社区uuid
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