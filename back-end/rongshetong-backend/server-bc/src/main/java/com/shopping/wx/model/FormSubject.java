package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "form_subject")
public class FormSubject {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 顺序(从小到大)
     */
    @Column(name = "subject_order")
    private Integer subjectOrder;

    /**
     * 题目标题
     */
    @Column(name = "subject_title")
    private String subjectTitle;

    /**
     * 题目图片路径
     */
    @Column(name = "subject_img")
    private String subjectImg;

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
     * 填空题 - 行数
     */
    @Column(name = "row_count")
    private Integer rowCount;

    /**
     * 图片上传题 - 最大上传数
     */
    @Column(name = "upload_img_count")
    private Integer uploadImgCount;

    /**
     * 是否 必选必填
     */
    @Column(name = "is_require")
    private Integer isRequire;

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
     * 后台专用定义
     */
    private String options;

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
     * 获取顺序(从小到大)
     *
     * @return subject_order - 顺序(从小到大)
     */
    public Integer getSubjectOrder() {
        return subjectOrder;
    }

    /**
     * 设置顺序(从小到大)
     *
     * @param subjectOrder 顺序(从小到大)
     */
    public void setSubjectOrder(Integer subjectOrder) {
        this.subjectOrder = subjectOrder;
    }

    /**
     * 获取题目标题
     *
     * @return subject_title - 题目标题
     */
    public String getSubjectTitle() {
        return subjectTitle;
    }

    /**
     * 设置题目标题
     *
     * @param subjectTitle 题目标题
     */
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle == null ? null : subjectTitle.trim();
    }

    /**
     * 获取题目图片路径
     *
     * @return subject_img - 题目图片路径
     */
    public String getSubjectImg() {
        return subjectImg;
    }

    /**
     * 设置题目图片路径
     *
     * @param subjectImg 题目图片路径
     */
    public void setSubjectImg(String subjectImg) {
        this.subjectImg = subjectImg == null ? null : subjectImg.trim();
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
     * 获取填空题 - 行数
     *
     * @return row_count - 填空题 - 行数
     */
    public Integer getRowCount() {
        return rowCount;
    }

    /**
     * 设置填空题 - 行数
     *
     * @param rowCount 填空题 - 行数
     */
    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * 获取图片上传题 - 最大上传数
     *
     * @return upload_img_count - 图片上传题 - 最大上传数
     */
    public Integer getUploadImgCount() {
        return uploadImgCount;
    }

    /**
     * 设置图片上传题 - 最大上传数
     *
     * @param uploadImgCount 图片上传题 - 最大上传数
     */
    public void setUploadImgCount(Integer uploadImgCount) {
        this.uploadImgCount = uploadImgCount;
    }

    /**
     * 获取是否 必选必填
     *
     * @return is_require - 是否 必选必填
     */
    public Integer getIsRequire() {
        return isRequire;
    }

    /**
     * 设置是否 必选必填
     *
     * @param isRequire 是否 必选必填
     */
    public void setIsRequire(Integer isRequire) {
        this.isRequire = isRequire;
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

    /**
     * 获取后台专用定义
     *
     * @return options - 后台专用定义
     */
    public String getOptions() {
        return options;
    }

    /**
     * 设置后台专用定义
     *
     * @param options 后台专用定义
     */
    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }
}