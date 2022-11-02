package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_community")
public class UserCommunity {
    /**
     * openid主键
     */
    @Id
    private String id;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 关联 - 用户小区 id
     */
    @Column(name = "cell_uuid")
    private String cellUuid;

    /**
     * 关联 - 用户所在社区 id
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 授权级别 0: 未授权用户 1.社区管理员用户 2.社区录入员用户 3.超级用户？
     */
    @Column(name = "authorization_level")
    private Integer authorizationLevel;

    /**
     * 授权级别认证是否通过
     */
    @Column(name = "authorization_identification")
    private Integer authorizationIdentification;

    /**
     * 本条状态（0：正常 -1：失效）
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
     * 获取openid主键
     *
     * @return id - openid主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置openid主键
     *
     * @param id openid主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取关联 - 用户小区 id
     *
     * @return cell_uuid - 关联 - 用户小区 id
     */
    public String getCellUuid() {
        return cellUuid;
    }

    /**
     * 设置关联 - 用户小区 id
     *
     * @param cellUuid 关联 - 用户小区 id
     */
    public void setCellUuid(String cellUuid) {
        this.cellUuid = cellUuid == null ? null : cellUuid.trim();
    }

    /**
     * 获取关联 - 用户所在社区 id
     *
     * @return community_uuid - 关联 - 用户所在社区 id
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置关联 - 用户所在社区 id
     *
     * @param communityUuid 关联 - 用户所在社区 id
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
    }

    /**
     * 获取授权级别 0: 未授权用户 1.社区管理员用户 2.社区录入员用户 3.超级用户？
     *
     * @return authorization_level - 授权级别 0: 未授权用户 1.社区管理员用户 2.社区录入员用户 3.超级用户？
     */
    public Integer getAuthorizationLevel() {
        return authorizationLevel;
    }

    /**
     * 设置授权级别 0: 未授权用户 1.社区管理员用户 2.社区录入员用户 3.超级用户？
     *
     * @param authorizationLevel 授权级别 0: 未授权用户 1.社区管理员用户 2.社区录入员用户 3.超级用户？
     */
    public void setAuthorizationLevel(Integer authorizationLevel) {
        this.authorizationLevel = authorizationLevel;
    }

    /**
     * 获取授权级别认证是否通过
     *
     * @return authorization_identification - 授权级别认证是否通过
     */
    public Integer getAuthorizationIdentification() {
        return authorizationIdentification;
    }

    /**
     * 设置授权级别认证是否通过
     *
     * @param authorizationIdentification 授权级别认证是否通过
     */
    public void setAuthorizationIdentification(Integer authorizationIdentification) {
        this.authorizationIdentification = authorizationIdentification;
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