package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "zczb")
public class Zczb {
    @Id
    private Integer id;

    private String zczb;

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
     * @return zczb
     */
    public String getZczb() {
        return zczb;
    }

    /**
     * @param zczb
     */
    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
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