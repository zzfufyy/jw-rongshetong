package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "sdbsq")
public class Sdbsq {
    @Id
    private String id;

    /**
     * 企业名
     */
    private String qyname;

    /**
     * 申请人
     */
    private String sqr;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 贷款金额
     */
    private String dkje;

    /**
     * 贷款期限id
     */
    private String dkqxid;

    /**
     * 贷款利率id
     */
    private String dkllid;

    /**
     * 营业额
     */
    private String yye;

    /**
     * 纳税额
     */
    private String nshe;

    /**
     * 贷款银行id
     */
    private String dkbankid;

    /**
     * 贷款银行名
     */
    private String dkbankname;

    /**
     * 0-审核中 1-审核通过
     */
    private String status;

    /**
     * openid
     */
    private String yl1;

    private String yl2;

    /**
     * 主营业务
     */
    private String zyyw;

    /**
     * 贷款利率
     */
    private String dkll;

    /**
     * 贷款期限
     */
    private String dkqx;

    /**
     * 现有银行负债
     */
    private String xyyhfz;

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
     * 获取企业名
     *
     * @return qyname - 企业名
     */
    public String getQyname() {
        return qyname;
    }

    /**
     * 设置企业名
     *
     * @param qyname 企业名
     */
    public void setQyname(String qyname) {
        this.qyname = qyname == null ? null : qyname.trim();
    }

    /**
     * 获取申请人
     *
     * @return sqr - 申请人
     */
    public String getSqr() {
        return sqr;
    }

    /**
     * 设置申请人
     *
     * @param sqr 申请人
     */
    public void setSqr(String sqr) {
        this.sqr = sqr == null ? null : sqr.trim();
    }

    /**
     * 获取联系电话
     *
     * @return lxdh - 联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 设置联系电话
     *
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    /**
     * 获取贷款金额
     *
     * @return dkje - 贷款金额
     */
    public String getDkje() {
        return dkje;
    }

    /**
     * 设置贷款金额
     *
     * @param dkje 贷款金额
     */
    public void setDkje(String dkje) {
        this.dkje = dkje == null ? null : dkje.trim();
    }

    /**
     * 获取贷款期限id
     *
     * @return dkqxid - 贷款期限id
     */
    public String getDkqxid() {
        return dkqxid;
    }

    /**
     * 设置贷款期限id
     *
     * @param dkqxid 贷款期限id
     */
    public void setDkqxid(String dkqxid) {
        this.dkqxid = dkqxid == null ? null : dkqxid.trim();
    }

    /**
     * 获取贷款利率id
     *
     * @return dkllid - 贷款利率id
     */
    public String getDkllid() {
        return dkllid;
    }

    /**
     * 设置贷款利率id
     *
     * @param dkllid 贷款利率id
     */
    public void setDkllid(String dkllid) {
        this.dkllid = dkllid == null ? null : dkllid.trim();
    }

    /**
     * 获取营业额
     *
     * @return yye - 营业额
     */
    public String getYye() {
        return yye;
    }

    /**
     * 设置营业额
     *
     * @param yye 营业额
     */
    public void setYye(String yye) {
        this.yye = yye == null ? null : yye.trim();
    }

    /**
     * 获取纳税额
     *
     * @return nshe - 纳税额
     */
    public String getNshe() {
        return nshe;
    }

    /**
     * 设置纳税额
     *
     * @param nshe 纳税额
     */
    public void setNshe(String nshe) {
        this.nshe = nshe == null ? null : nshe.trim();
    }

    /**
     * 获取贷款银行id
     *
     * @return dkbankid - 贷款银行id
     */
    public String getDkbankid() {
        return dkbankid;
    }

    /**
     * 设置贷款银行id
     *
     * @param dkbankid 贷款银行id
     */
    public void setDkbankid(String dkbankid) {
        this.dkbankid = dkbankid == null ? null : dkbankid.trim();
    }

    /**
     * 获取贷款银行名
     *
     * @return dkbankname - 贷款银行名
     */
    public String getDkbankname() {
        return dkbankname;
    }

    /**
     * 设置贷款银行名
     *
     * @param dkbankname 贷款银行名
     */
    public void setDkbankname(String dkbankname) {
        this.dkbankname = dkbankname == null ? null : dkbankname.trim();
    }

    /**
     * 获取0-审核中 1-审核通过
     *
     * @return status - 0-审核中 1-审核通过
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0-审核中 1-审核通过
     *
     * @param status 0-审核中 1-审核通过
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取openid
     *
     * @return yl1 - openid
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置openid
     *
     * @param yl1 openid
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

    /**
     * 获取主营业务
     *
     * @return zyyw - 主营业务
     */
    public String getZyyw() {
        return zyyw;
    }

    /**
     * 设置主营业务
     *
     * @param zyyw 主营业务
     */
    public void setZyyw(String zyyw) {
        this.zyyw = zyyw == null ? null : zyyw.trim();
    }

    /**
     * 获取贷款利率
     *
     * @return dkll - 贷款利率
     */
    public String getDkll() {
        return dkll;
    }

    /**
     * 设置贷款利率
     *
     * @param dkll 贷款利率
     */
    public void setDkll(String dkll) {
        this.dkll = dkll == null ? null : dkll.trim();
    }

    /**
     * 获取贷款期限
     *
     * @return dkqx - 贷款期限
     */
    public String getDkqx() {
        return dkqx;
    }

    /**
     * 设置贷款期限
     *
     * @param dkqx 贷款期限
     */
    public void setDkqx(String dkqx) {
        this.dkqx = dkqx == null ? null : dkqx.trim();
    }

    /**
     * 获取现有银行负债
     *
     * @return xyyhfz - 现有银行负债
     */
    public String getXyyhfz() {
        return xyyhfz;
    }

    /**
     * 设置现有银行负债
     *
     * @param xyyhfz 现有银行负债
     */
    public void setXyyhfz(String xyyhfz) {
        this.xyyhfz = xyyhfz == null ? null : xyyhfz.trim();
    }
}