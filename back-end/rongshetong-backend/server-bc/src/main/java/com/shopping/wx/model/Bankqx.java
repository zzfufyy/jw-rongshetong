package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "bankqx")
public class Bankqx {
    @Id
    private Integer id;

    /**
     * 所属银行
     */
    private Integer bankid;

    /**
     * 所属网点
     */
    private String orgonid;

    /**
     * 微信oppid
     */
    private String oppenid;

    /**
     * 状态 0-删除 1-使用中
     */
    private Integer state;

    /**
     * 权限类别:1-金融办权限  2-银行权限
     */
    private String type;

    private String name;

    private String phone;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所属银行
     *
     * @return bankid - 所属银行
     */
    public Integer getBankid() {
        return bankid;
    }

    /**
     * 设置所属银行
     *
     * @param bankid 所属银行
     */
    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    /**
     * 获取所属网点
     *
     * @return orgonid - 所属网点
     */
    public String getOrgonid() {
        return orgonid;
    }

    /**
     * 设置所属网点
     *
     * @param orgonid 所属网点
     */
    public void setOrgonid(String orgonid) {
        this.orgonid = orgonid == null ? null : orgonid.trim();
    }

    /**
     * 获取微信oppid
     *
     * @return oppenid - 微信oppid
     */
    public String getOppenid() {
        return oppenid;
    }

    /**
     * 设置微信oppid
     *
     * @param oppenid 微信oppid
     */
    public void setOppenid(String oppenid) {
        this.oppenid = oppenid == null ? null : oppenid.trim();
    }

    /**
     * 获取状态 0-删除 1-使用中
     *
     * @return state - 状态 0-删除 1-使用中
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态 0-删除 1-使用中
     *
     * @param state 状态 0-删除 1-使用中
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取权限类别:1-金融办权限  2-银行权限
     *
     * @return type - 权限类别:1-金融办权限  2-银行权限
     */
    public String getType() {
        return type;
    }

    /**
     * 设置权限类别:1-金融办权限  2-银行权限
     *
     * @param type 权限类别:1-金融办权限  2-银行权限
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}