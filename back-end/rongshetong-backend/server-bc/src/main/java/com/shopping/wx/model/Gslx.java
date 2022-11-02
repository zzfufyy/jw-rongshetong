package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "gslx")
public class Gslx {
    @Id
    private String id;

    /**
     * 类型
     */
    private String gslx;

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
     * 获取类型
     *
     * @return gslx - 类型
     */
    public String getGslx() {
        return gslx;
    }

    /**
     * 设置类型
     *
     * @param gslx 类型
     */
    public void setGslx(String gslx) {
        this.gslx = gslx == null ? null : gslx.trim();
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