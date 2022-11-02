package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "form_subject_option")
public class FormSubjectOption {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 顺序(从小到大)
     */
    @Column(name = "option_order")
    private Integer optionOrder;

    /**
     * 题目标题
     */
    @Column(name = "option_title")
    private String optionTitle;

    /**
     * 选项图片路径
     */
    @Column(name = "option_img")
    private String optionImg;

    /**
     * 关联表单 - uuid
     */
    @Column(name = "form_uuid")
    private String formUuid;

    /**
     * 关联题目 - uuid
     */
    @Column(name = "subject_uuid")
    private String subjectUuid;

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
     * 获取顺序(从小到大)
     *
     * @return option_order - 顺序(从小到大)
     */
    public Integer getOptionOrder() {
        return optionOrder;
    }

    /**
     * 设置顺序(从小到大)
     *
     * @param optionOrder 顺序(从小到大)
     */
    public void setOptionOrder(Integer optionOrder) {
        this.optionOrder = optionOrder;
    }

    /**
     * 获取题目标题
     *
     * @return option_title - 题目标题
     */
    public String getOptionTitle() {
        return optionTitle;
    }

    /**
     * 设置题目标题
     *
     * @param optionTitle 题目标题
     */
    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle == null ? null : optionTitle.trim();
    }

    /**
     * 获取选项图片路径
     *
     * @return option_img - 选项图片路径
     */
    public String getOptionImg() {
        return optionImg;
    }

    /**
     * 设置选项图片路径
     *
     * @param optionImg 选项图片路径
     */
    public void setOptionImg(String optionImg) {
        this.optionImg = optionImg == null ? null : optionImg.trim();
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