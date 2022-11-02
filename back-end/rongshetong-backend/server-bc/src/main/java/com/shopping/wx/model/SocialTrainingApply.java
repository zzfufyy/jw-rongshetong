package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "social_training_apply")
public class SocialTrainingApply {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 用户openid
     */
    @Column(name = "user_openid")
    private String userOpenid;

    /**
     * 培训类型
     */
    @Column(name = "training_type")
    private String trainingType;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 电话
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 备注
     */
    @Column(name = "user_mark")
    private String userMark;

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
     * 获取用户openid
     *
     * @return user_openid - 用户openid
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置用户openid
     *
     * @param userOpenid 用户openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    /**
     * 获取培训类型
     *
     * @return training_type - 培训类型
     */
    public String getTrainingType() {
        return trainingType;
    }

    /**
     * 设置培训类型
     *
     * @param trainingType 培训类型
     */
    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType == null ? null : trainingType.trim();
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取电话
     *
     * @return user_phone - 电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置电话
     *
     * @param userPhone 电话
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取备注
     *
     * @return user_mark - 备注
     */
    public String getUserMark() {
        return userMark;
    }

    /**
     * 设置备注
     *
     * @param userMark 备注
     */
    public void setUserMark(String userMark) {
        this.userMark = userMark == null ? null : userMark.trim();
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