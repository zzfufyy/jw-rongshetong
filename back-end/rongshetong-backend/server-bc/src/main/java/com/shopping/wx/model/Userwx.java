package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bc_user_wx")
public class Userwx {
    /**
     * uuid主键
     */
    @Id
    private Long id;

    /**
     * 加入日期
     */
    private Date addtime;

    private String deletestatus;

    @Column(name = "REVISION")
    private Integer revision;

    /**
     * 小程序id
     */
    @Column(name = "app_id")
    private String appId;

    @Column(name = "bc_user_id")
    private String bcUserId;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1：男 0：女
     */
    private String gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市省
     */
    private String city;

    /**
     * 城市市
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像路径
     */
    private String avatarurl;

    /**
     * 微信绑定电话
     */
    private String unionid;

    private String ext1;

    private String ext2;

    private String ext3;

    /**
     * 获取uuid主键
     *
     * @return id - uuid主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置uuid主键
     *
     * @param id uuid主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取加入日期
     *
     * @return addtime - 加入日期
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置加入日期
     *
     * @param addtime 加入日期
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return deletestatus
     */
    public String getDeletestatus() {
        return deletestatus;
    }

    /**
     * @param deletestatus
     */
    public void setDeletestatus(String deletestatus) {
        this.deletestatus = deletestatus == null ? null : deletestatus.trim();
    }

    /**
     * @return REVISION
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * @param revision
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
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
     * @return bc_user_id
     */
    public String getBcUserId() {
        return bcUserId;
    }

    /**
     * @param bcUserId
     */
    public void setBcUserId(String bcUserId) {
        this.bcUserId = bcUserId == null ? null : bcUserId.trim();
    }

    /**
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取性别 1：男 0：女
     *
     * @return gender - 性别 1：男 0：女
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别 1：男 0：女
     *
     * @param gender 性别 1：男 0：女
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * 获取城市省
     *
     * @return city - 城市省
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市省
     *
     * @param city 城市省
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取城市市
     *
     * @return province - 城市市
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置城市市
     *
     * @param province 城市市
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取头像路径
     *
     * @return avatarurl - 头像路径
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * 设置头像路径
     *
     * @param avatarurl 头像路径
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    /**
     * 获取微信绑定电话
     *
     * @return unionid - 微信绑定电话
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置微信绑定电话
     *
     * @param unionid 微信绑定电话
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
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
}