package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "rygm")
public class Rygm {
    @Id
    private Integer id;

    private String rygm;

    private String yl1;

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
     * @return rygm
     */
    public String getRygm() {
        return rygm;
    }

    /**
     * @param rygm
     */
    public void setRygm(String rygm) {
        this.rygm = rygm == null ? null : rygm.trim();
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
}