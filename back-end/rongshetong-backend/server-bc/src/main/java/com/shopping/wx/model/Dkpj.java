package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dkpj")
public class Dkpj {
    @Id
    private Integer id;

    private String dkid;

    /**
     * 评分
     */
    private String pf;

    /**
     * 评价意见
     */
    private String pjyj;

    private String dkbankid;

    /**
     * 未受理:0,受理:1 完成:2
     */
    private String yl1;

    private String yl2;

    private Date pjtime;

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
     * 获取评分
     *
     * @return pf - 评分
     */
    public String getPf() {
        return pf;
    }

    /**
     * 设置评分
     *
     * @param pf 评分
     */
    public void setPf(String pf) {
        this.pf = pf == null ? null : pf.trim();
    }

    /**
     * 获取评价意见
     *
     * @return pjyj - 评价意见
     */
    public String getPjyj() {
        return pjyj;
    }

    /**
     * 设置评价意见
     *
     * @param pjyj 评价意见
     */
    public void setPjyj(String pjyj) {
        this.pjyj = pjyj == null ? null : pjyj.trim();
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
     * @return pjtime
     */
    public Date getPjtime() {
        return pjtime;
    }

    /**
     * @param pjtime
     */
    public void setPjtime(Date pjtime) {
        this.pjtime = pjtime;
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