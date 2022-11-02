package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "dkqxinfo")
public class Dkqxinfo {
    @Id
    private String id;

    /**
     * 设置贷款期限
     */
    private String dkqx;

    /**
     * 是否上线, 1-上线 , 0-不上线
     */
    private String sfsx;

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
     * 获取设置贷款期限
     *
     * @return dkqx - 设置贷款期限
     */
    public String getDkqx() {
        return dkqx;
    }

    /**
     * 设置设置贷款期限
     *
     * @param dkqx 设置贷款期限
     */
    public void setDkqx(String dkqx) {
        this.dkqx = dkqx == null ? null : dkqx.trim();
    }

    /**
     * 获取是否上线, 1-上线 , 0-不上线
     *
     * @return sfsx - 是否上线, 1-上线 , 0-不上线
     */
    public String getSfsx() {
        return sfsx;
    }

    /**
     * 设置是否上线, 1-上线 , 0-不上线
     *
     * @param sfsx 是否上线, 1-上线 , 0-不上线
     */
    public void setSfsx(String sfsx) {
        this.sfsx = sfsx == null ? null : sfsx.trim();
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