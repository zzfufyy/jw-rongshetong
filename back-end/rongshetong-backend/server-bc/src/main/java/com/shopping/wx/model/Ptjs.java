package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "ptjs")
public class Ptjs {
    @Id
    private String id;

    /**
     * 平台名字
     */
    private String ptname;

    /**
     * 平台介绍
     */
    private String ptcongtext;

    /**
     * 政府平台
     */
    private String zfptjs;

    /**
     * 公共服务
     */
    private String ggfw;

    /**
     * 利企惠银
     */
    private String lqhg;

    private String yl1;

    private String yl2;

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
     * 获取平台名字
     *
     * @return ptname - 平台名字
     */
    public String getPtname() {
        return ptname;
    }

    /**
     * 设置平台名字
     *
     * @param ptname 平台名字
     */
    public void setPtname(String ptname) {
        this.ptname = ptname == null ? null : ptname.trim();
    }

    /**
     * 获取平台介绍
     *
     * @return ptcongtext - 平台介绍
     */
    public String getPtcongtext() {
        return ptcongtext;
    }

    /**
     * 设置平台介绍
     *
     * @param ptcongtext 平台介绍
     */
    public void setPtcongtext(String ptcongtext) {
        this.ptcongtext = ptcongtext == null ? null : ptcongtext.trim();
    }

    /**
     * 获取政府平台
     *
     * @return zfptjs - 政府平台
     */
    public String getZfptjs() {
        return zfptjs;
    }

    /**
     * 设置政府平台
     *
     * @param zfptjs 政府平台
     */
    public void setZfptjs(String zfptjs) {
        this.zfptjs = zfptjs == null ? null : zfptjs.trim();
    }

    /**
     * 获取公共服务
     *
     * @return ggfw - 公共服务
     */
    public String getGgfw() {
        return ggfw;
    }

    /**
     * 设置公共服务
     *
     * @param ggfw 公共服务
     */
    public void setGgfw(String ggfw) {
        this.ggfw = ggfw == null ? null : ggfw.trim();
    }

    /**
     * 获取利企惠银
     *
     * @return lqhg - 利企惠银
     */
    public String getLqhg() {
        return lqhg;
    }

    /**
     * 设置利企惠银
     *
     * @param lqhg 利企惠银
     */
    public void setLqhg(String lqhg) {
        this.lqhg = lqhg == null ? null : lqhg.trim();
    }

    /**
     * @return yl1
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * @param yl1
     */
    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    /**
     * @return yl2
     */
    public String getYl2() {
        return yl2;
    }

    /**
     * @param yl2
     */
    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }
}