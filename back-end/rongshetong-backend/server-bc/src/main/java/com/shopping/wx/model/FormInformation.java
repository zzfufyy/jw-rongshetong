package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "form_information")
public class FormInformation {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 表单名称
     */
    @Column(name = "form_title")
    private String formTitle;

    /**
     * 表单说明
     */
    @Column(name = "form_introduction")
    private String formIntroduction;

    /**
     * 0： 创建状态 1:激活状态 2:结束状态
     */
    @Column(name = "form_status")
    private Integer formStatus;

    /**
     * 0： 非全局，局限于社区 1: 全局
     */
    @Column(name = "form_scope")
    private Integer formScope;

    /**
     * 表单开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 表单结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 二维码路径
     */
    @Column(name = "qrcode_img")
    private String qrcodeImg;

    /**
     * 优先级（从大到小）
     */
    private Integer priority;

    /**
     * 关联 - 影响社区id
     */
    @Column(name = "effect_community_uuid")
    private String effectCommunityUuid;

    /**
     * 关联创建人 - openid
     */
    @Column(name = "user_openid")
    private String userOpenid;

    /**
     * 本条状态（0：正常 -1：不显示）
     */
    private Integer status;

    /**
     * 创建时间
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
     * 获取表单名称
     *
     * @return form_title - 表单名称
     */
    public String getFormTitle() {
        return formTitle;
    }

    /**
     * 设置表单名称
     *
     * @param formTitle 表单名称
     */
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle == null ? null : formTitle.trim();
    }

    /**
     * 获取表单说明
     *
     * @return form_introduction - 表单说明
     */
    public String getFormIntroduction() {
        return formIntroduction;
    }

    /**
     * 设置表单说明
     *
     * @param formIntroduction 表单说明
     */
    public void setFormIntroduction(String formIntroduction) {
        this.formIntroduction = formIntroduction == null ? null : formIntroduction.trim();
    }

    /**
     * 获取0： 创建状态 1:激活状态 2:结束状态
     *
     * @return form_status - 0： 创建状态 1:激活状态 2:结束状态
     */
    public Integer getFormStatus() {
        return formStatus;
    }

    /**
     * 设置0： 创建状态 1:激活状态 2:结束状态
     *
     * @param formStatus 0： 创建状态 1:激活状态 2:结束状态
     */
    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
    }

    /**
     * 获取0： 非全局，局限于社区 1: 全局
     *
     * @return form_scope - 0： 非全局，局限于社区 1: 全局
     */
    public Integer getFormScope() {
        return formScope;
    }

    /**
     * 设置0： 非全局，局限于社区 1: 全局
     *
     * @param formScope 0： 非全局，局限于社区 1: 全局
     */
    public void setFormScope(Integer formScope) {
        this.formScope = formScope;
    }

    /**
     * 获取表单开始时间
     *
     * @return begin_time - 表单开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置表单开始时间
     *
     * @param beginTime 表单开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取表单结束时间
     *
     * @return end_time - 表单结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置表单结束时间
     *
     * @param endTime 表单结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取二维码路径
     *
     * @return qrcode_img - 二维码路径
     */
    public String getQrcodeImg() {
        return qrcodeImg;
    }

    /**
     * 设置二维码路径
     *
     * @param qrcodeImg 二维码路径
     */
    public void setQrcodeImg(String qrcodeImg) {
        this.qrcodeImg = qrcodeImg == null ? null : qrcodeImg.trim();
    }

    /**
     * 获取优先级（从大到小）
     *
     * @return priority - 优先级（从大到小）
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级（从大到小）
     *
     * @param priority 优先级（从大到小）
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取关联 - 影响社区id
     *
     * @return effect_community_uuid - 关联 - 影响社区id
     */
    public String getEffectCommunityUuid() {
        return effectCommunityUuid;
    }

    /**
     * 设置关联 - 影响社区id
     *
     * @param effectCommunityUuid 关联 - 影响社区id
     */
    public void setEffectCommunityUuid(String effectCommunityUuid) {
        this.effectCommunityUuid = effectCommunityUuid == null ? null : effectCommunityUuid.trim();
    }

    /**
     * 获取关联创建人 - openid
     *
     * @return user_openid - 关联创建人 - openid
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置关联创建人 - openid
     *
     * @param userOpenid 关联创建人 - openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    /**
     * 获取本条状态（0：正常 -1：不显示）
     *
     * @return status - 本条状态（0：正常 -1：不显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1：不显示）
     *
     * @param status 本条状态（0：正常 -1：不显示）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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