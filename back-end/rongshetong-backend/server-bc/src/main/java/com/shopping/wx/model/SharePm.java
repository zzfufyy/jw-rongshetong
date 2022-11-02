package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "share_pm")
public class SharePm {
    /**
     * uuid
     */
    @Id
    private String id;

    /**
     * 分享人姓名
     */
    private String name;

    /**
     * 分享人openid
     */
    private String openid;

    /**
     * 成功分享次数
     */
    @Column(name = "sucess_sharenum")
    private Integer sucessSharenum;

    /**
     * 所属社区
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    private String ext1;

    private String ext2;

    private String ext3;

    private Integer zzid;

    /**
     * 获取uuid
     *
     * @return id - uuid
     */
    public String getId() {
        return id;
    }

    /**
     * 设置uuid
     *
     * @param id uuid
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取分享人姓名
     *
     * @return name - 分享人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分享人姓名
     *
     * @param name 分享人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取分享人openid
     *
     * @return openid - 分享人openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置分享人openid
     *
     * @param openid 分享人openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取成功分享次数
     *
     * @return sucess_sharenum - 成功分享次数
     */
    public Integer getSucessSharenum() {
        return sucessSharenum;
    }

    /**
     * 设置成功分享次数
     *
     * @param sucessSharenum 成功分享次数
     */
    public void setSucessSharenum(Integer sucessSharenum) {
        this.sucessSharenum = sucessSharenum;
    }

    /**
     * 获取所属社区
     *
     * @return community_uuid - 所属社区
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置所属社区
     *
     * @param communityUuid 所属社区
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
    }

    /**
     * @return ext1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * @return ext2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * @return ext3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    /**
     * @return zzid
     */
    public Integer getZzid() {
        return zzid;
    }

    /**
     * @param zzid
     */
    public void setZzid(Integer zzid) {
        this.zzid = zzid;
    }
}