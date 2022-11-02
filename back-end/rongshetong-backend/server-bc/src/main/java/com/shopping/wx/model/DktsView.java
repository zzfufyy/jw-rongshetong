package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dktsview")
public class DktsView {
    private Integer id;

    /**
     * 投诉内容
     */
    private String tsnr;

    /**
     * 联系人
     */
    private String lxr;

    /**
     * 联系电话
     */
    private String tslxdh;

    /**
     * 未受理:0,受理:1 完成:2
     */
    private String tsstatus;

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
     * 是否有抵押物
     */
    private String sfdyw;

    /**
     * 贷款产品id
     */
    private String dyw;

    /**
     * 贷款用途id
     */
    private String dkytid;

    /**
     * 还款方式id
     */
    private String hkfsid;

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
     * 0-已申报，1-已通过 2-已受理 3-已放款 -1-已退回
     */
    private String status;

    /**
     * 微信openid
     */
    private String yl1;

    private String yl2;

    /**
     * 纳税等级
     */
    private String nsdj;

    /**
     * 贷款用途
     */
    private String dkyt;

    /**
     * 贷款期限
     */
    private String dkqx;

    /**
     * 还款方式
     */
    private String hkfs;

    /**
     * 贷款申请时间
     */
    private Date dksj;

    /**
     * 银行网点id
     */
    private String yhwdid;

    /**
     * 银行网点
     */
    private String yhwd;

    /**
     * 转派时间
     */
    private Date zpsj;

    private String yxtjid;

    /**
     * 优先条件
     */
    private String yxtjname;

    private Date tstime;

    private Date sltime;

    private Date wctime;

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
     * 获取投诉内容
     *
     * @return tsnr - 投诉内容
     */
    public String getTsnr() {
        return tsnr;
    }

    /**
     * 设置投诉内容
     *
     * @param tsnr 投诉内容
     */
    public void setTsnr(String tsnr) {
        this.tsnr = tsnr == null ? null : tsnr.trim();
    }

    /**
     * 获取联系人
     *
     * @return lxr - 联系人
     */
    public String getLxr() {
        return lxr;
    }

    /**
     * 设置联系人
     *
     * @param lxr 联系人
     */
    public void setLxr(String lxr) {
        this.lxr = lxr == null ? null : lxr.trim();
    }

    /**
     * 获取联系电话
     *
     * @return tslxdh - 联系电话
     */
    public String getTslxdh() {
        return tslxdh;
    }

    /**
     * 设置联系电话
     *
     * @param tslxdh 联系电话
     */
    public void setTslxdh(String tslxdh) {
        this.tslxdh = tslxdh == null ? null : tslxdh.trim();
    }

    /**
     * 获取未受理:0,受理:1 完成:2
     *
     * @return tsstatus - 未受理:0,受理:1 完成:2
     */
    public String getTsstatus() {
        return tsstatus;
    }

    /**
     * 设置未受理:0,受理:1 完成:2
     *
     * @param tsstatus 未受理:0,受理:1 完成:2
     */
    public void setTsstatus(String tsstatus) {
        this.tsstatus = tsstatus == null ? null : tsstatus.trim();
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
     * 获取是否有抵押物
     *
     * @return sfdyw - 是否有抵押物
     */
    public String getSfdyw() {
        return sfdyw;
    }

    /**
     * 设置是否有抵押物
     *
     * @param sfdyw 是否有抵押物
     */
    public void setSfdyw(String sfdyw) {
        this.sfdyw = sfdyw == null ? null : sfdyw.trim();
    }

    /**
     * 获取贷款产品id
     *
     * @return dyw - 贷款产品id
     */
    public String getDyw() {
        return dyw;
    }

    /**
     * 设置贷款产品id
     *
     * @param dyw 贷款产品id
     */
    public void setDyw(String dyw) {
        this.dyw = dyw == null ? null : dyw.trim();
    }

    /**
     * 获取贷款用途id
     *
     * @return dkytid - 贷款用途id
     */
    public String getDkytid() {
        return dkytid;
    }

    /**
     * 设置贷款用途id
     *
     * @param dkytid 贷款用途id
     */
    public void setDkytid(String dkytid) {
        this.dkytid = dkytid == null ? null : dkytid.trim();
    }

    /**
     * 获取还款方式id
     *
     * @return hkfsid - 还款方式id
     */
    public String getHkfsid() {
        return hkfsid;
    }

    /**
     * 设置还款方式id
     *
     * @param hkfsid 还款方式id
     */
    public void setHkfsid(String hkfsid) {
        this.hkfsid = hkfsid == null ? null : hkfsid.trim();
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
     * 获取0-已申报，1-已通过 2-已受理 3-已放款 -1-已退回
     *
     * @return status - 0-已申报，1-已通过 2-已受理 3-已放款 -1-已退回
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0-已申报，1-已通过 2-已受理 3-已放款 -1-已退回
     *
     * @param status 0-已申报，1-已通过 2-已受理 3-已放款 -1-已退回
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取微信openid
     *
     * @return yl1 - 微信openid
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置微信openid
     *
     * @param yl1 微信openid
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
     * 获取纳税等级
     *
     * @return nsdj - 纳税等级
     */
    public String getNsdj() {
        return nsdj;
    }

    /**
     * 设置纳税等级
     *
     * @param nsdj 纳税等级
     */
    public void setNsdj(String nsdj) {
        this.nsdj = nsdj == null ? null : nsdj.trim();
    }

    /**
     * 获取贷款用途
     *
     * @return dkyt - 贷款用途
     */
    public String getDkyt() {
        return dkyt;
    }

    /**
     * 设置贷款用途
     *
     * @param dkyt 贷款用途
     */
    public void setDkyt(String dkyt) {
        this.dkyt = dkyt == null ? null : dkyt.trim();
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
     * 获取还款方式
     *
     * @return hkfs - 还款方式
     */
    public String getHkfs() {
        return hkfs;
    }

    /**
     * 设置还款方式
     *
     * @param hkfs 还款方式
     */
    public void setHkfs(String hkfs) {
        this.hkfs = hkfs == null ? null : hkfs.trim();
    }

    /**
     * 获取贷款申请时间
     *
     * @return dksj - 贷款申请时间
     */
    public Date getDksj() {
        return dksj;
    }

    /**
     * 设置贷款申请时间
     *
     * @param dksj 贷款申请时间
     */
    public void setDksj(Date dksj) {
        this.dksj = dksj;
    }

    /**
     * 获取银行网点id
     *
     * @return yhwdid - 银行网点id
     */
    public String getYhwdid() {
        return yhwdid;
    }

    /**
     * 设置银行网点id
     *
     * @param yhwdid 银行网点id
     */
    public void setYhwdid(String yhwdid) {
        this.yhwdid = yhwdid == null ? null : yhwdid.trim();
    }

    /**
     * 获取银行网点
     *
     * @return yhwd - 银行网点
     */
    public String getYhwd() {
        return yhwd;
    }

    /**
     * 设置银行网点
     *
     * @param yhwd 银行网点
     */
    public void setYhwd(String yhwd) {
        this.yhwd = yhwd == null ? null : yhwd.trim();
    }

    /**
     * 获取转派时间
     *
     * @return zpsj - 转派时间
     */
    public Date getZpsj() {
        return zpsj;
    }

    /**
     * 设置转派时间
     *
     * @param zpsj 转派时间
     */
    public void setZpsj(Date zpsj) {
        this.zpsj = zpsj;
    }

    /**
     * @return yxtjid
     */
    public String getYxtjid() {
        return yxtjid;
    }

    /**
     * @param yxtjid
     */
    public void setYxtjid(String yxtjid) {
        this.yxtjid = yxtjid == null ? null : yxtjid.trim();
    }

    /**
     * 获取优先条件
     *
     * @return yxtjname - 优先条件
     */
    public String getYxtjname() {
        return yxtjname;
    }

    /**
     * 设置优先条件
     *
     * @param yxtjname 优先条件
     */
    public void setYxtjname(String yxtjname) {
        this.yxtjname = yxtjname == null ? null : yxtjname.trim();
    }

    /**
     * @return tstime
     */
    public Date getTstime() {
        return tstime;
    }

    /**
     * @param tstime
     */
    public void setTstime(Date tstime) {
        this.tstime = tstime;
    }

    /**
     * @return sltime
     */
    public Date getSltime() {
        return sltime;
    }

    /**
     * @param sltime
     */
    public void setSltime(Date sltime) {
        this.sltime = sltime;
    }

    /**
     * @return wctime
     */
    public Date getWctime() {
        return wctime;
    }

    /**
     * @param wctime
     */
    public void setWctime(Date wctime) {
        this.wctime = wctime;
    }
}