package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_for_form")
public class UserForForm {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 题目类型 1、填空题 2 选择题 4.图片上传 5签名
     */
    @Column(name = "component_type")
    private Integer componentType;

    /**
     * 题目类型 1、填空题 2、单选题 3、多选题 4.图片上传 5.签名
     */
    @Column(name = "subject_type")
    private Integer subjectType;

    /**
     * 填表人 - openid
     */
    @Column(name = "user_openid")
    private String userOpenid;

    /**
     * 题目作答(填空题用)
     */
    @Column(name = "subject_answer")
    private String subjectAnswer;

    /**
     * 题目上传图片
     */
    @Column(name = "upload_img")
    private String uploadImg;

    /**
     * 关联选项 - uuid
     */
    @Column(name = "option_uuid")
    private String optionUuid;

    /**
     * 关联题目 - uuid
     */
    @Column(name = "subject_uuid")
    private String subjectUuid;

    /**
     * 关联表单 - uuid
     */
    @Column(name = "form_uuid")
    private String formUuid;

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
     * 获取题目类型 1、填空题 2 选择题 4.图片上传 5签名
     *
     * @return component_type - 题目类型 1、填空题 2 选择题 4.图片上传 5签名
     */
    public Integer getComponentType() {
        return componentType;
    }

    /**
     * 设置题目类型 1、填空题 2 选择题 4.图片上传 5签名
     *
     * @param componentType 题目类型 1、填空题 2 选择题 4.图片上传 5签名
     */
    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    /**
     * 获取题目类型 1、填空题 2、单选题 3、多选题 4.图片上传 5.签名
     *
     * @return subject_type - 题目类型 1、填空题 2、单选题 3、多选题 4.图片上传 5.签名
     */
    public Integer getSubjectType() {
        return subjectType;
    }

    /**
     * 设置题目类型 1、填空题 2、单选题 3、多选题 4.图片上传 5.签名
     *
     * @param subjectType 题目类型 1、填空题 2、单选题 3、多选题 4.图片上传 5.签名
     */
    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * 获取填表人 - openid
     *
     * @return user_openid - 填表人 - openid
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置填表人 - openid
     *
     * @param userOpenid 填表人 - openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    /**
     * 获取题目作答(填空题用)
     *
     * @return subject_answer - 题目作答(填空题用)
     */
    public String getSubjectAnswer() {
        return subjectAnswer;
    }

    /**
     * 设置题目作答(填空题用)
     *
     * @param subjectAnswer 题目作答(填空题用)
     */
    public void setSubjectAnswer(String subjectAnswer) {
        this.subjectAnswer = subjectAnswer == null ? null : subjectAnswer.trim();
    }

    /**
     * 获取题目上传图片
     *
     * @return upload_img - 题目上传图片
     */
    public String getUploadImg() {
        return uploadImg;
    }

    /**
     * 设置题目上传图片
     *
     * @param uploadImg 题目上传图片
     */
    public void setUploadImg(String uploadImg) {
        this.uploadImg = uploadImg == null ? null : uploadImg.trim();
    }

    /**
     * 获取关联选项 - uuid
     *
     * @return option_uuid - 关联选项 - uuid
     */
    public String getOptionUuid() {
        return optionUuid;
    }

    /**
     * 设置关联选项 - uuid
     *
     * @param optionUuid 关联选项 - uuid
     */
    public void setOptionUuid(String optionUuid) {
        this.optionUuid = optionUuid == null ? null : optionUuid.trim();
    }

    /**
     * 获取关联题目 - uuid
     *
     * @return subject_uuid - 关联题目 - uuid
     */
    public String getSubjectUuid() {
        return subjectUuid;
    }

    /**
     * 设置关联题目 - uuid
     *
     * @param subjectUuid 关联题目 - uuid
     */
    public void setSubjectUuid(String subjectUuid) {
        this.subjectUuid = subjectUuid == null ? null : subjectUuid.trim();
    }

    /**
     * 获取关联表单 - uuid
     *
     * @return form_uuid - 关联表单 - uuid
     */
    public String getFormUuid() {
        return formUuid;
    }

    /**
     * 设置关联表单 - uuid
     *
     * @param formUuid 关联表单 - uuid
     */
    public void setFormUuid(String formUuid) {
        this.formUuid = formUuid == null ? null : formUuid.trim();
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