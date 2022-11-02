package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dkts")
public class Dkts {
    @Id
    private Integer id;

    private String dkid;

    /**
     * 投诉内容
     */
    private String tsnr;

    /**
     * 联系人
     */
    private String lxr;

    /**
     * 联系电话
     */
    private String lxdh;

    private String dkbankid;

    /**
     * 未受理:0,受理:1 完成:2
     */
    private String yl1;

    private String yl2;

    private Date tstime;

    private Date sltime;

    private Date wctime;

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
     * @return dkid
     */
    public String getDkid() {
        return dkid;
    }

    /**
     * @param dkid
     */
    public void setDkid(String dkid) {
        this.dkid = dkid == null ? null : dkid.trim();
    }

    /**
     * 获取投诉内容
     *
     * @return tsnr - 投诉内容
     */
    public String getTsnr() {
        return tsnr;
    }

    /**
     * 设置投诉内容
     *
     * @param tsnr 投诉内容
     */
    public void setTsnr(String tsnr) {
        this.tsnr = tsnr == null ? null : tsnr.trim();
    }

    /**
     * 获取联系人
     *
     * @return lxr - 联系人
     */
    public String getLxr() {
        return lxr;
    }

    /**
     * 设置联系人
     *
     * @param lxr 联系人
     */
    public void setLxr(String lxr) {
        this.lxr = lxr == null ? null : lxr.trim();
    }

    /**
     * 获取联系电话
     *
     * @return lxdh - 联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 设置联系电话
     *
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    /**
     * @return dkbankid
     */
    public String getDkbankid() {
        return dkbankid;
    }

    /**
     * @param dkbankid
     */
    public void setDkbankid(String dkbankid) {
        this.dkbankid = dkbankid == null ? null : dkbankid.trim();
    }

    /**
     * 获取未受理:0,受理:1 完成:2
     *
     * @return yl1 - 未受理:0,受理:1 完成:2
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置未受理:0,受理:1 完成:2
     *
     * @param yl1 未受理:0,受理:1 完成:2
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

    /**
     * @return tstime
     */
    public Date getTstime() {
        return tstime;
    }

    /**
     * @param tstime
     */
    public void setTstime(Date tstime) {
        this.tstime = tstime;
    }

    /**
     * @return sltime
     */
    public Date getSltime() {
        return sltime;
    }

    /**
     * @param sltime
     */
    public void setSltime(Date sltime) {
        this.sltime = sltime;
    }

    /**
     * @return wctime
     */
    public Date getWctime() {
        return wctime;
    }

    /**
     * @param wctime
     */
    public void setWctime(Date wctime) {
        this.wctime = wctime;
    }
}