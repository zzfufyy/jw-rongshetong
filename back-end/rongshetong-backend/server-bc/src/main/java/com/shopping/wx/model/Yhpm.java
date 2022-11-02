package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "yhpm")
public class Yhpm {
    @Id
    private Integer id;

    @Column(name = "fbankName")
    private String fbankname;

    private Integer sxe;

    @Column(name = "priceCount")
    private Integer pricecount;

    @Column(name = "forderCount")
    private Integer fordercount;

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
     * @return fbankName
     */
    public String getFbankname() {
        return fbankname;
    }

    /**
     * @param fbankname
     */
    public void setFbankname(String fbankname) {
        this.fbankname = fbankname == null ? null : fbankname.trim();
    }

    /**
     * @return sxe
     */
    public Integer getSxe() {
        return sxe;
    }

    /**
     * @param sxe
     */
    public void setSxe(Integer sxe) {
        this.sxe = sxe;
    }

    /**
     * @return priceCount
     */
    public Integer getPricecount() {
        return pricecount;
    }

    /**
     * @param pricecount
     */
    public void setPricecount(Integer pricecount) {
        this.pricecount = pricecount;
    }

    /**
     * @return forderCount
     */
    public Integer getFordercount() {
        return fordercount;
    }

    /**
     * @param fordercount
     */
    public void setFordercount(Integer fordercount) {
        this.fordercount = fordercount;
    }
}