package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "streets")
public class Streets {
    @Id
    private Integer id;

    private String jdname;

    private String jddz;

    private String jdjd;

    private String jdwd;

    private String yl1;

    private String yl2;

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
     * @return jdname
     */
    public String getJdname() {
        return jdname;
    }

    /**
     * @param jdname
     */
    public void setJdname(String jdname) {
        this.jdname = jdname == null ? null : jdname.trim();
    }

    /**
     * @return jddz
     */
    public String getJddz() {
        return jddz;
    }

    /**
     * @param jddz
     */
    public void setJddz(String jddz) {
        this.jddz = jddz == null ? null : jddz.trim();
    }

    /**
     * @return jdjd
     */
    public String getJdjd() {
        return jdjd;
    }

    /**
     * @param jdjd
     */
    public void setJdjd(String jdjd) {
        this.jdjd = jdjd == null ? null : jdjd.trim();
    }

    /**
     * @return jdwd
     */
    public String getJdwd() {
        return jdwd;
    }

    /**
     * @param jdwd
     */
    public void setJdwd(String jdwd) {
        this.jdwd = jdwd == null ? null : jdwd.trim();
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