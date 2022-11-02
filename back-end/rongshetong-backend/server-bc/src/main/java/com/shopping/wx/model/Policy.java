package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "policy")
public class Policy {
    @Id
    private String id;

    /**
     * 政策名
     */
    private String policyname;

    /**
     * 发布时间
     */
    private String fbtime;

    /**
     * 政策内容
     */
    private String policycontext;

    /**
     * 政策图片
     */
    private String policyimg;

    /**
     * 是否上线
     */
    private String yl1;

    /**
     * 点击量
     */
    private Integer yl2;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取政策名
     *
     * @return policyname - 政策名
     */
    public String getPolicyname() {
        return policyname;
    }

    /**
     * 设置政策名
     *
     * @param policyname 政策名
     */
    public void setPolicyname(String policyname) {
        this.policyname = policyname == null ? null : policyname.trim();
    }

    /**
     * 获取发布时间
     *
     * @return fbtime - 发布时间
     */
    public String getFbtime() {
        return fbtime;
    }

    /**
     * 设置发布时间
     *
     * @param fbtime 发布时间
     */
    public void setFbtime(String fbtime) {
        this.fbtime = fbtime == null ? null : fbtime.trim();
    }

    /**
     * 获取政策内容
     *
     * @return policycontext - 政策内容
     */
    public String getPolicycontext() {
        return policycontext;
    }

    /**
     * 设置政策内容
     *
     * @param policycontext 政策内容
     */
    public void setPolicycontext(String policycontext) {
        this.policycontext = policycontext == null ? null : policycontext.trim();
    }

    /**
     * 获取政策图片
     *
     * @return policyimg - 政策图片
     */
    public String getPolicyimg() {
        return policyimg;
    }

    /**
     * 设置政策图片
     *
     * @param policyimg 政策图片
     */
    public void setPolicyimg(String policyimg) {
        this.policyimg = policyimg == null ? null : policyimg.trim();
    }

    /**
     * 获取是否上线
     *
     * @return yl1 - 是否上线
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置是否上线
     *
     * @param yl1 是否上线
     */
    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    /**
     * 获取点击量
     *
     * @return yl2 - 点击量
     */
    public Integer getYl2() {
        return yl2;
    }

    /**
     * 设置点击量
     *
     * @param yl2 点击量
     */
    public void setYl2(Integer yl2) {
        this.yl2 = yl2;
    }
}