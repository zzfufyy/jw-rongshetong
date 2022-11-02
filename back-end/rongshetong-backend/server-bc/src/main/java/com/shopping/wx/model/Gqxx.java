package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "gqxx")
public class Gqxx {
    @Id
    private Integer id;

    private String gqxx;

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
     * @return gqxx
     */
    public String getGqxx() {
        return gqxx;
    }

    /**
     * @param gqxx
     */
    public void setGqxx(String gqxx) {
        this.gqxx = gqxx == null ? null : gqxx.trim();
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