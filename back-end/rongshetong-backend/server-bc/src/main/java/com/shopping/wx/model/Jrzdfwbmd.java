package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "jrzdfwbmd")
public class Jrzdfwbmd {
    @Id
    private String id;

    /**
     * 企业id
     */
    private String qyid;

    /**
     * 企业名
     */
    private String qyname;

    /**
     * 企业类型
     */
    private String qyfl;

    /**
     * 营业执照
     */
    private String yyzz;

    /**
     * 纳税证明或银行流水
     */
    private String nszm;

    /**
     * 无重大责任事故承诺书
     */
    private String sgcrs;

    /**
     * 无不良信用记录(信用中国截图)
     */
    private String wblxyjl;

    /**
     * 状态 （0-待审核 ，1-审核通过，2-审核不通过）
     */
    private String status;

    private String yl1;

    private String yl2;

    private String yl3;

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
     * 获取企业id
     *
     * @return qyid - 企业id
     */
    public String getQyid() {
        return qyid;
    }

    /**
     * 设置企业id
     *
     * @param qyid 企业id
     */
    public void setQyid(String qyid) {
        this.qyid = qyid == null ? null : qyid.trim();
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
     * 获取企业类型
     *
     * @return qyfl - 企业类型
     */
    public String getQyfl() {
        return qyfl;
    }

    /**
     * 设置企业类型
     *
     * @param qyfl 企业类型
     */
    public void setQyfl(String qyfl) {
        this.qyfl = qyfl == null ? null : qyfl.trim();
    }

    /**
     * 获取营业执照
     *
     * @return yyzz - 营业执照
     */
    public String getYyzz() {
        return yyzz;
    }

    /**
     * 设置营业执照
     *
     * @param yyzz 营业执照
     */
    public void setYyzz(String yyzz) {
        this.yyzz = yyzz == null ? null : yyzz.trim();
    }

    /**
     * 获取纳税证明或银行流水
     *
     * @return nszm - 纳税证明或银行流水
     */
    public String getNszm() {
        return nszm;
    }

    /**
     * 设置纳税证明或银行流水
     *
     * @param nszm 纳税证明或银行流水
     */
    public void setNszm(String nszm) {
        this.nszm = nszm == null ? null : nszm.trim();
    }

    /**
     * 获取无重大责任事故承诺书
     *
     * @return sgcrs - 无重大责任事故承诺书
     */
    public String getSgcrs() {
        return sgcrs;
    }

    /**
     * 设置无重大责任事故承诺书
     *
     * @param sgcrs 无重大责任事故承诺书
     */
    public void setSgcrs(String sgcrs) {
        this.sgcrs = sgcrs == null ? null : sgcrs.trim();
    }

    /**
     * 获取无不良信用记录(信用中国截图)
     *
     * @return wblxyjl - 无不良信用记录(信用中国截图)
     */
    public String getWblxyjl() {
        return wblxyjl;
    }

    /**
     * 设置无不良信用记录(信用中国截图)
     *
     * @param wblxyjl 无不良信用记录(信用中国截图)
     */
    public void setWblxyjl(String wblxyjl) {
        this.wblxyjl = wblxyjl == null ? null : wblxyjl.trim();
    }

    /**
     * 获取状态 （0-待审核 ，1-审核通过，2-审核不通过）
     *
     * @return status - 状态 （0-待审核 ，1-审核通过，2-审核不通过）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 （0-待审核 ，1-审核通过，2-审核不通过）
     *
     * @param status 状态 （0-待审核 ，1-审核通过，2-审核不通过）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    /**
     * @return yl3
     */
    public String getYl3() {
        return yl3;
    }

    /**
     * @param yl3
     */
    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }
}