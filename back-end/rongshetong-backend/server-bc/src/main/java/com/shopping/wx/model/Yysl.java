package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "yysl")
public class Yysl {
    @Id
    private Integer id;

    private String yysl;

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
     * @return yysl
     */
    public String getYysl() {
        return yysl;
    }

    /**
     * @param yysl
     */
    public void setYysl(String yysl) {
        this.yysl = yysl == null ? null : yysl.trim();
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