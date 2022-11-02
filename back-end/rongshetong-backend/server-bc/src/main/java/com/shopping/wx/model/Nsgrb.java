package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "nsgrb")
public class Nsgrb {
    @Id
    private String id;

    /**
     * 排名id
     */
    private String rankid;

    /**
     * 公司名称
     */
    private String companyname;

    /**
     * 纳税信用等级
     */
    private String nsxydj;

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
     * 获取排名id
     *
     * @return rankid - 排名id
     */
    public String getRankid() {
        return rankid;
    }

    /**
     * 设置排名id
     *
     * @param rankid 排名id
     */
    public void setRankid(String rankid) {
        this.rankid = rankid == null ? null : rankid.trim();
    }

    /**
     * 获取公司名称
     *
     * @return companyname - 公司名称
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * 设置公司名称
     *
     * @param companyname 公司名称
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    /**
     * 获取纳税额度
     *
     * @return nsxydj - 纳税信用等级
     */
    public String getNsxydj() {
        return nsxydj;
    }

    /**
     * 设置纳税信用等级
     *
     * @param nsxydj 纳税信用等级
     */
    public void setNsxydj(String nsxydj) {
        this.nsxydj = nsxydj == null ? null : nsxydj.trim();
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