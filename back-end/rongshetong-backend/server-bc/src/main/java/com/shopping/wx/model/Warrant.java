package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "warrant")
public class Warrant {
    @Id
    private String id;

    /**
     * 担保名
     */
    private String warrantname;

    /**
     * 担保费率
     */
    private String warrantfl;

    /**
     * 担保期限
     */
    private String warrantqx;

    /**
     * 授信额度
     */
    private String edu;

    /**
     * 内容
     */
    private String warrantcontext;

    private String yl1;

    private String yl2;

    private String yl3;

    /**
     * 是否上线
     */
    private String sfsx;

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
     * 获取担保名
     *
     * @return warrantname - 担保名
     */
    public String getWarrantname() {
        return warrantname;
    }

    /**
     * 设置担保名
     *
     * @param warrantname 担保名
     */
    public void setWarrantname(String warrantname) {
        this.warrantname = warrantname == null ? null : warrantname.trim();
    }

    /**
     * 获取担保费率
     *
     * @return warrantfl - 担保费率
     */
    public String getWarrantfl() {
        return warrantfl;
    }

    /**
     * 设置担保费率
     *
     * @param warrantfl 担保费率
     */
    public void setWarrantfl(String warrantfl) {
        this.warrantfl = warrantfl == null ? null : warrantfl.trim();
    }

    /**
     * 获取担保期限
     *
     * @return warrantqx - 担保期限
     */
    public String getWarrantqx() {
        return warrantqx;
    }

    /**
     * 设置担保期限
     *
     * @param warrantqx 担保期限
     */
    public void setWarrantqx(String warrantqx) {
        this.warrantqx = warrantqx == null ? null : warrantqx.trim();
    }

    /**
     * 获取授信额度
     *
     * @return edu - 授信额度
     */
    public String getEdu() {
        return edu;
    }

    /**
     * 设置授信额度
     *
     * @param edu 授信额度
     */
    public void setEdu(String edu) {
        this.edu = edu == null ? null : edu.trim();
    }

    /**
     * 获取内容
     *
     * @return warrantcontext - 内容
     */
    public String getWarrantcontext() {
        return warrantcontext;
    }

    /**
     * 设置内容
     *
     * @param warrantcontext 内容
     */
    public void setWarrantcontext(String warrantcontext) {
        this.warrantcontext = warrantcontext == null ? null : warrantcontext.trim();
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

    /**
     * @return yl3
     */
    public String getYl3() {
        return yl3;
    }

    /**
     * @param yl3
     */
    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }

    /**
     * 获取是否上线
     *
     * @return sfsx - 是否上线
     */
    public String getSfsx() {
        return sfsx;
    }

    /**
     * 设置是否上线
     *
     * @param sfsx 是否上线
     */
    public void setSfsx(String sfsx) {
        this.sfsx = sfsx == null ? null : sfsx.trim();
    }
}