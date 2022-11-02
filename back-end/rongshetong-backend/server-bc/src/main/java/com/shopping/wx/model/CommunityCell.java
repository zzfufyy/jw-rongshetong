package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "community_cell")
public class CommunityCell {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 社区名称
     */
    @Column(name = "cell_name")
    private String cellName;

    /**
     * 关联 - 社区uuid
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 楼栋命名格式 0: 数字命名(默认)  1: ABCD命名
     */
    @Column(name = "building_name_type")
    private Integer buildingNameType;

    /**
     * 小区总栋数
     */
    @Column(name = "num_building")
    private Integer numBuilding;

    /**
     * 该小区每栋层数
     */
    @Column(name = "num_layer")
    private Integer numLayer;

    /**
     * 该小区每层户数
     */
    @Column(name = "num_layer_family")
    private Integer numLayerFamily;

    /**
     * 录入员 openid
     */
    @Column(name = "recorder_openid")
    private String recorderOpenid;

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
     * 获取社区名称
     *
     * @return cell_name - 社区名称
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * 设置社区名称
     *
     * @param cellName 社区名称
     */
    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
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
     * 获取楼栋命名格式 0: 数字命名(默认)  1: ABCD命名
     *
     * @return building_name_type - 楼栋命名格式 0: 数字命名(默认)  1: ABCD命名
     */
    public Integer getBuildingNameType() {
        return buildingNameType;
    }

    /**
     * 设置楼栋命名格式 0: 数字命名(默认)  1: ABCD命名
     *
     * @param buildingNameType 楼栋命名格式 0: 数字命名(默认)  1: ABCD命名
     */
    public void setBuildingNameType(Integer buildingNameType) {
        this.buildingNameType = buildingNameType;
    }

    /**
     * 获取小区总栋数
     *
     * @return num_building - 小区总栋数
     */
    public Integer getNumBuilding() {
        return numBuilding;
    }

    /**
     * 设置小区总栋数
     *
     * @param numBuilding 小区总栋数
     */
    public void setNumBuilding(Integer numBuilding) {
        this.numBuilding = numBuilding;
    }

    /**
     * 获取该小区每栋层数
     *
     * @return num_layer - 该小区每栋层数
     */
    public Integer getNumLayer() {
        return numLayer;
    }

    /**
     * 设置该小区每栋层数
     *
     * @param numLayer 该小区每栋层数
     */
    public void setNumLayer(Integer numLayer) {
        this.numLayer = numLayer;
    }

    /**
     * 获取该小区每层户数
     *
     * @return num_layer_family - 该小区每层户数
     */
    public Integer getNumLayerFamily() {
        return numLayerFamily;
    }

    /**
     * 设置该小区每层户数
     *
     * @param numLayerFamily 该小区每层户数
     */
    public void setNumLayerFamily(Integer numLayerFamily) {
        this.numLayerFamily = numLayerFamily;
    }

    /**
     * 获取录入员 openid
     *
     * @return recorder_openid - 录入员 openid
     */
    public String getRecorderOpenid() {
        return recorderOpenid;
    }

    /**
     * 设置录入员 openid
     *
     * @param recorderOpenid 录入员 openid
     */
    public void setRecorderOpenid(String recorderOpenid) {
        this.recorderOpenid = recorderOpenid == null ? null : recorderOpenid.trim();
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