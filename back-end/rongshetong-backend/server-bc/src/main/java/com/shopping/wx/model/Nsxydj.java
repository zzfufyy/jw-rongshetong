package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "nsdj")
public class Nsxydj {
    @Id
    private Integer id;

    private String nsdj;

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
     * @return nsdj
     */
    public String getNsdj() {
        return nsdj;
    }

    /**
     * @param nsdj
     */
    public void setNsdj(String nsdj) {
        this.nsdj = nsdj == null ? null : nsdj.trim();
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