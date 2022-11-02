package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bc_user_wx")
public class UserWxInfo {
    @Id
    private Long id;

    private Date addtime;

    private String deletestatus;

    @Column(name = "REVISION")
    private Integer revision;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "bc_user_id")
    private String bcUserId;

    private String openid;

    private String nickname;

    private String gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarurl;

    private String unionid;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
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
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
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
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return avatarurl
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * @param avatarurl
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    /**
     * @return unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * @param unionid
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}