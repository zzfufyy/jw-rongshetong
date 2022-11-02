package com.shopping.wx.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    public Product(String floanUse){
        this.floanUse = floanUse;
    }

    public Product(){}

    public Product(
            String id,
            String bankid,
            String productname,
            String nianhualv,
            String qixian,
            String edu
    ) {
        this.id = id;
        this.bankid = bankid;
        this.productname = productname;
        this.nianhualv = nianhualv;
        this.qixian = qixian;
        this.edu = edu;
    }

    @Id
    private String id;

    /**
     * 产品名
     */
    private String productname;

    /**
     * 金融办所属银行id
     */
    private String bankid;

    /**
     * 年化率
     */
    private String nianhualv;

    /**
     * 期限
     */
    private String qixian;

    /**
     * 额度
     */
    private String edu;

    /**
     * 产品描述
     */
    private String productcontext;

    /**
     * 所属银行名称
     */
    private String yl1;

    /**
     * 还款方式
     */
    private String yl2;

    /**
     * 是否上线
     */
    private String sfsx;

    /**
     * 贷款用途
     */
    @Column(name = "floan_use")
    private String floanUse;

    /**
     * 贷款类型
     */
    private String ftype;

    private String productcpts;

    private String productzrtj;

    private String sqzl;

    /**
     * 年利率最小值
     */
    @Column(name = "air_min")
    private Double airMin;

    /**
     * 年利率最大值
     */
    @Column(name = "air_max")
    private Double airMax;

    /**
     * 额度最小值
     */
    @Column(name = "loan_limit_min")
    private Integer loanLimitMin;

    /**
     * 额度最大值
     */
    @Column(name = "loan_limit_max")
    private Integer loanLimitMax;

    /**
     * 年限最小值
     */
    @Column(name = "time_limit_min")
    private Integer timeLimitMin;

    /**
     * 年限最大值
     */
    @Column(name = "time_limit_max")
    private Integer timeLimitMax;

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
     * 获取产品名
     *
     * @return productname - 产品名
     */
    public String getProductname() {
        return productname;
    }

    /**
     * 设置产品名
     *
     * @param productname 产品名
     */
    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    /**
     * 获取金融办所属银行id
     *
     * @return bankid - 金融办所属银行id
     */
    public String getBankid() {
        return bankid;
    }

    /**
     * 设置金融办所属银行id
     *
     * @param bankid 金融办所属银行id
     */
    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    /**
     * 获取年化率
     *
     * @return nianhualv - 年化率
     */
    public String getNianhualv() {
        return nianhualv;
    }

    /**
     * 设置年化率
     *
     * @param nianhualv 年化率
     */
    public void setNianhualv(String nianhualv) {
        this.nianhualv = nianhualv == null ? null : nianhualv.trim();
    }

    /**
     * 获取期限
     *
     * @return qixian - 期限
     */
    public String getQixian() {
        return qixian;
    }

    /**
     * 设置期限
     *
     * @param qixian 期限
     */
    public void setQixian(String qixian) {
        this.qixian = qixian == null ? null : qixian.trim();
    }

    /**
     * 获取额度
     *
     * @return edu - 额度
     */
    public String getEdu() {
        return edu;
    }

    /**
     * 设置额度
     *
     * @param edu 额度
     */
    public void setEdu(String edu) {
        this.edu = edu == null ? null : edu.trim();
    }

    /**
     * 获取产品描述
     *
     * @return productcontext - 产品描述
     */
    public String getProductcontext() {
        return productcontext;
    }

    /**
     * 设置产品描述
     *
     * @param productcontext 产品描述
     */
    public void setProductcontext(String productcontext) {
        this.productcontext = productcontext == null ? null : productcontext.trim();
    }

    /**
     * 获取所属银行名称
     *
     * @return yl1 - 所属银行名称
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置所属银行名称
     *
     * @param yl1 所属银行名称
     */
    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    /**
     * 获取还款方式
     *
     * @return yl2 - 还款方式
     */
    public String getYl2() {
        return yl2;
    }

    /**
     * 设置还款方式
     *
     * @param yl2 还款方式
     */
    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }

    /**
     * 获取是否上线
     *
     * @return sfsx - 是否上线
     */
    public String getSfsx() {
        return sfsx;
    }

    /**
     * 设置是否上线
     *
     * @param sfsx 是否上线
     */
    public void setSfsx(String sfsx) {
        this.sfsx = sfsx == null ? null : sfsx.trim();
    }

    /**
     * 获取贷款用途
     *
     * @return floan_use - 贷款用途
     */
    public String getFloanUse() {
        return floanUse;
    }

    /**
     * 设置贷款用途
     *
     * @param floanUse 贷款用途
     */
    public void setFloanUse(String floanUse) {
        this.floanUse = floanUse == null ? null : floanUse.trim();
    }

    /**
     * 获取贷款类型
     *
     * @return ftype - 贷款类型
     */
    public String getFtype() {
        return ftype;
    }

    /**
     * 设置贷款类型
     *
     * @param ftype 贷款类型
     */
    public void setFtype(String ftype) {
        this.ftype = ftype == null ? null : ftype.trim();
    }

    /**
     * @return productcpts
     */
    public String getProductcpts() {
        return productcpts;
    }

    /**
     * @param productcpts
     */
    public void setProductcpts(String productcpts) {
        this.productcpts = productcpts == null ? null : productcpts.trim();
    }

    /**
     * @return productzrtj
     */
    public String getProductzrtj() {
        return productzrtj;
    }

    /**
     * @param productzrtj
     */
    public void setProductzrtj(String productzrtj) {
        this.productzrtj = productzrtj == null ? null : productzrtj.trim();
    }

    /**
     * @return sqzl
     */
    public String getSqzl() {
        return sqzl;
    }

    /**
     * @param sqzl
     */
    public void setSqzl(String sqzl) {
        this.sqzl = sqzl == null ? null : sqzl.trim();
    }

    /**
     * 获取年利率最小值
     *
     * @return air_min - 年利率最小值
     */
    public Double getAirMin() {
        return airMin;
    }

    /**
     * 设置年利率最小值
     *
     * @param airMin 年利率最小值
     */
    public void setAirMin(Double airMin) {
        this.airMin = airMin;
    }

    /**
     * 获取年利率最大值
     *
     * @return air_max - 年利率最大值
     */
    public Double getAirMax() {
        return airMax;
    }

    /**
     * 设置年利率最大值
     *
     * @param airMax 年利率最大值
     */
    public void setAirMax(Double airMax) {
        this.airMax = airMax;
    }

    /**
     * 获取额度最小值
     *
     * @return loan_limit_min - 额度最小值
     */
    public Integer getLoanLimitMin() {
        return loanLimitMin;
    }

    /**
     * 设置额度最小值
     *
     * @param loanLimitMin 额度最小值
     */
    public void setLoanLimitMin(Integer loanLimitMin) {
        this.loanLimitMin = loanLimitMin;
    }

    /**
     * 获取额度最大值
     *
     * @return loan_limit_max - 额度最大值
     */
    public Integer getLoanLimitMax() {
        return loanLimitMax;
    }

    /**
     * 设置额度最大值
     *
     * @param loanLimitMax 额度最大值
     */
    public void setLoanLimitMax(Integer loanLimitMax) {
        this.loanLimitMax = loanLimitMax;
    }

    /**
     * 获取年限最小值
     *
     * @return time_limit_min - 年限最小值
     */
    public Integer getTimeLimitMin() {
        return timeLimitMin;
    }

    /**
     * 设置年限最小值
     *
     * @param timeLimitMin 年限最小值
     */
    public void setTimeLimitMin(Integer timeLimitMin) {
        this.timeLimitMin = timeLimitMin;
    }

    /**
     * 获取年限最大值
     *
     * @return time_limit_max - 年限最大值
     */
    public Integer getTimeLimitMax() {
        return timeLimitMax;
    }

    /**
     * 设置年限最大值
     *
     * @param timeLimitMax 年限最大值
     */
    public void setTimeLimitMax(Integer timeLimitMax) {
        this.timeLimitMax = timeLimitMax;
    }
}