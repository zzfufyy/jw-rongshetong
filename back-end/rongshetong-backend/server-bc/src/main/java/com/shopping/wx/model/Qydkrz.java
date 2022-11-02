package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qydkrz")
public class Qydkrz {
    @Id
    private String id;

    /**
     * 法人
     */
    private String sqr;

    /**
     * 身份证
     */
    private String sfzh;

    /**
     * 企业注册所在地
     */
    private String qyzcszd;

    /**
     * 企业名称
     */
    private String qyname;

    /**
     * 身份证正面照片
     */
    private String sfzfimg;

    /**
     * 身份证反面照片
     */
    private String sfzzimg;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 企业id
     */
    private String qyid;

    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 审核时间
     */
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
     * 获取法人
     *
     * @return sqr - 法人
     */
    public String getSqr() {
        return sqr;
    }

    /**
     * 设置法人
     *
     * @param sqr 法人
     */
    public void setSqr(String sqr) {
        this.sqr = sqr == null ? null : sqr.trim();
    }

    /**
     * 获取身份证
     *
     * @return sfzh - 身份证
     */
    public String getSfzh() {
        return sfzh;
    }

    /**
     * 设置身份证
     *
     * @param sfzh 身份证
     */
    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    /**
     * 获取企业注册所在地
     *
     * @return qyzcszd - 企业注册所在地
     */
    public String getQyzcszd() {
        return qyzcszd;
    }

    /**
     * 设置企业注册所在地
     *
     * @param qyzcszd 企业注册所在地
     */
    public void setQyzcszd(String qyzcszd) {
        this.qyzcszd = qyzcszd == null ? null : qyzcszd.trim();
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
     * 获取身份证正面照片
     *
     * @return sfzfimg - 身份证正面照片
     */
    public String getSfzfimg() {
        return sfzfimg;
    }

    /**
     * 设置身份证正面照片
     *
     * @param sfzfimg 身份证正面照片
     */
    public void setSfzfimg(String sfzfimg) {
        this.sfzfimg = sfzfimg == null ? null : sfzfimg.trim();
    }

    /**
     * 获取身份证反面照片
     *
     * @return sfzzimg - 身份证反面照片
     */
    public String getSfzzimg() {
        return sfzzimg;
    }

    /**
     * 设置身份证反面照片
     *
     * @param sfzzimg 身份证反面照片
     */
    public void setSfzzimg(String sfzzimg) {
        this.sfzzimg = sfzzimg == null ? null : sfzzimg.trim();
    }

    /**
     * 获取审核状态
     *
     * @return status - 审核状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置审核状态
     *
     * @param status 审核状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 获取创建时间
     *
     * @return cjsj - 创建时间
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * 设置创建时间
     *
     * @param cjsj 创建时间
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    /**
     * 获取审核时间
     *
     * @return shsj - 审核时间
     */
    public Date getShsj() {
        return shsj;
    }

    /**
     * 设置审核时间
     *
     * @param shsj 审核时间
     */
    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }
}