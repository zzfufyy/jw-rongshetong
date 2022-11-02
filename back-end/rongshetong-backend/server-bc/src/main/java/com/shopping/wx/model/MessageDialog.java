package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "message_dialog")
public class MessageDialog {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 关联 - 招聘记录id
     */
    @Column(name = "record_uuid")
    private String recordUuid;

    /**
     * 关联 - 用户openid
     */
    @Column(name = "user_openid")
    private String userOpenid;

    /**
     * 用户角色（0: 求职者 1：招聘者）
     */
    @Column(name = "user_role")
    private Integer userRole;

    /**
     * 消息内容
     */
    @Column(name = "msg_body")
    private String msgBody;

    /**
     * 是否已读(0：未读 1：已读)
     */
    @Column(name = "flag_read")
    private Integer flagRead;

    /**
     * 本条信息状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 本条信息录入时间
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
     * 获取关联 - 招聘记录id
     *
     * @return record_uuid - 关联 - 招聘记录id
     */
    public String getRecordUuid() {
        return recordUuid;
    }

    /**
     * 设置关联 - 招聘记录id
     *
     * @param recordUuid 关联 - 招聘记录id
     */
    public void setRecordUuid(String recordUuid) {
        this.recordUuid = recordUuid == null ? null : recordUuid.trim();
    }

    /**
     * 获取关联 - 用户openid
     *
     * @return user_openid - 关联 - 用户openid
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置关联 - 用户openid
     *
     * @param userOpenid 关联 - 用户openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    /**
     * 获取用户角色（0: 求职者 1：招聘者）
     *
     * @return user_role - 用户角色（0: 求职者 1：招聘者）
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置用户角色（0: 求职者 1：招聘者）
     *
     * @param userRole 用户角色（0: 求职者 1：招聘者）
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * 获取消息内容
     *
     * @return msg_body - 消息内容
     */
    public String getMsgBody() {
        return msgBody;
    }

    /**
     * 设置消息内容
     *
     * @param msgBody 消息内容
     */
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody == null ? null : msgBody.trim();
    }

    /**
     * 获取是否已读(0：未读 1：已读)
     *
     * @return flag_read - 是否已读(0：未读 1：已读)
     */
    public Integer getFlagRead() {
        return flagRead;
    }

    /**
     * 设置是否已读(0：未读 1：已读)
     *
     * @param flagRead 是否已读(0：未读 1：已读)
     */
    public void setFlagRead(Integer flagRead) {
        this.flagRead = flagRead;
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
     * 获取本条信息录入时间
     *
     * @return create_time - 本条信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置本条信息录入时间
     *
     * @param createTime 本条信息录入时间
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