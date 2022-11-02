package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qyrz")
public class Qyrz {
    @Id
    private String id;

    /**
     * 审核状态 -0--审核中  1---审核通过  2 -审核未通过
     */
    private String status;

    /**
     * 姓名
     */
    private String fname;

    /**
     * 身份证号
     */
    @Column(name = "fidCard")
    private String fidcard;

    /**
     * 提交时间
     */
    private Date fcreatetime;

    /**
     * 企业名称
     */
    private String qyname;

    /**
     * 社会信用统一代码
     */
    @Column(name = "fregistNum")
    private String fregistnum;

    /**
     * 所属市
     */
    private String fcity;

    /**
     * 所属区
     */
    private String farea;

    /**
     * 所属街道
     */
    private String fstreet;

    /**
     * 行业分类
     */
    private String hyfl;

    /**
     * 人员规模
     */
    private String rygm;

    /**
     * 注册资本
     */
    private String zczb;

    /**
     * 高企信息
     */
    private String gqxx;

    /**
     * 纳税等级
     */
    private String nsdj;

    /**
     * 纳税信用等级
     */
    private String nsxydj;

    /**
     * 营业收入
     */
    private String yysr;

    /**
     * 微信用户openid
     */
    private String wxopenid;

    private Date shsj;

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
     * 获取审核状态 -0--审核中  1---审核通过  2 -审核未通过
     *
     * @return status - 审核状态 -0--审核中  1---审核通过  2 -审核未通过
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置审核状态 -0--审核中  1---审核通过  2 -审核未通过
     *
     * @param status 审核状态 -0--审核中  1---审核通过  2 -审核未通过
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取姓名
     *
     * @return fname - 姓名
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置姓名
     *
     * @param fname 姓名
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取身份证号
     *
     * @return fidCard - 身份证号
     */
    public String getFidcard() {
        return fidcard;
    }

    /**
     * 设置身份证号
     *
     * @param fidcard 身份证号
     */
    public void setFidcard(String fidcard) {
        this.fidcard = fidcard == null ? null : fidcard.trim();
    }

    /**
     * 获取提交时间
     *
     * @return fcreatetime - 提交时间
     */
    public Date getFcreatetime() {
        return fcreatetime;
    }

    /**
     * 设置提交时间
     *
     * @param fcreatetime 提交时间
     */
    public void setFcreatetime(Date fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    /**
     * 获取企业名称
     *
     * @return qyname - 企业名称
     */
    public String getQyname() {
        return qyname;
    }

    /**
     * 设置企业名称
     *
     * @param qyname 企业名称
     */
    public void setQyname(String qyname) {
        this.qyname = qyname == null ? null : qyname.trim();
    }

    /**
     * 获取社会信用统一代码
     *
     * @return fregistNum - 社会信用统一代码
     */
    public String getFregistnum() {
        return fregistnum;
    }

    /**
     * 设置社会信用统一代码
     *
     * @param fregistnum 社会信用统一代码
     */
    public void setFregistnum(String fregistnum) {
        this.fregistnum = fregistnum == null ? null : fregistnum.trim();
    }

    /**
     * 获取所属市
     *
     * @return fcity - 所属市
     */
    public String getFcity() {
        return fcity;
    }

    /**
     * 设置所属市
     *
     * @param fcity 所属市
     */
    public void setFcity(String fcity) {
        this.fcity = fcity == null ? null : fcity.trim();
    }

    /**
     * 获取所属区
     *
     * @return farea - 所属区
     */
    public String getFarea() {
        return farea;
    }

    /**
     * 设置所属区
     *
     * @param farea 所属区
     */
    public void setFarea(String farea) {
        this.farea = farea == null ? null : farea.trim();
    }

    /**
     * 获取所属街道
     *
     * @return fstreet - 所属街道
     */
    public String getFstreet() {
        return fstreet;
    }

    /**
     * 设置所属街道
     *
     * @param fstreet 所属街道
     */
    public void setFstreet(String fstreet) {
        this.fstreet = fstreet == null ? null : fstreet.trim();
    }

    /**
     * 获取行业分类
     *
     * @return hyfl - 行业分类
     */
    public String getHyfl() {
        return hyfl;
    }

    /**
     * 设置行业分类
     *
     * @param hyfl 行业分类
     */
    public void setHyfl(String hyfl) {
        this.hyfl = hyfl == null ? null : hyfl.trim();
    }

    /**
     * 获取人员规模
     *
     * @return rygm - 人员规模
     */
    public String getRygm() {
        return rygm;
    }

    /**
     * 设置人员规模
     *
     * @param rygm 人员规模
     */
    public void setRygm(String rygm) {
        this.rygm = rygm == null ? null : rygm.trim();
    }

    /**
     * 获取注册资本
     *
     * @return zczb - 注册资本
     */
    public String getZczb() {
        return zczb;
    }

    /**
     * 设置注册资本
     *
     * @param zczb 注册资本
     */
    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
    }

    /**
     * 获取高企信息
     *
     * @return gqxx - 高企信息
     */
    public String getGqxx() {
        return gqxx;
    }

    /**
     * 设置高企信息
     *
     * @param gqxx 高企信息
     */
    public void setGqxx(String gqxx) {
        this.gqxx = gqxx == null ? null : gqxx.trim();
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
     * 获取纳税信用等级
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
     * 获取营业收入
     *
     * @return yysr - 营业收入
     */
    public String getYysr() {
        return yysr;
    }

    /**
     * 设置营业收入
     *
     * @param yysr 营业收入
     */
    public void setYysr(String yysr) {
        this.yysr = yysr == null ? null : yysr.trim();
    }

    /**
     * 获取微信用户openid
     *
     * @return wxopenid - 微信用户openid
     */
    public String getWxopenid() {
        return wxopenid;
    }

    /**
     * 设置微信用户openid
     *
     * @param wxopenid 微信用户openid
     */
    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    /**
     * @return shsj
     */
    public Date getShsj() {
        return shsj;
    }

    /**
     * @param shsj
     */
    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }
}