package com.shopping.wx.model;

import javax.persistence.*;

@Entity
@Table(name = "bankinfo")
public class Bankinfo {
    @Id
    private String id;

    /**
     * 银行名
     */
    private String bankname;

    /**
     * 银行编号
     */
    private String bankuid;

    /**
     * 银行logo
     */
    private String bankimg;

    /**
     * 银行logo2
     */
    private String bankimg2;

    /**
     * 银行logo3
     */
    private String bankimg3;

    /**
     * 银行logo4
     */
    private String bankimg4;

    /**
     * 银行简介
     */
    private String bankcontext;

    /**
     * 放款额
     */
    private Integer fke;

    /**
     * 成交数量
     */
    private Integer cjnum;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 地址
     */
    private String adress;

    /**
     * 融资企业数
     */
    private Integer rzqys;

    /**
     * 上班时间
     */
    private String sbtime;

    /**
     * 预留字段
     */
    private String yl1;

    private String yl2;

    /**
     * atm或者营业厅
是否在用
     */
    private String type;

    /**
     * 银行背景颜色
     */
    private String bgcolor;

    /**
     * 优先条件
     */
    private Integer yxtjid;

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
     * 获取银行名
     *
     * @return bankname - 银行名
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 设置银行名
     *
     * @param bankname 银行名
     */
    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    /**
     * 获取银行编号
     *
     * @return bankuid - 银行编号
     */
    public String getBankuid() {
        return bankuid;
    }

    /**
     * 设置银行编号
     *
     * @param bankuid 银行编号
     */
    public void setBankuid(String bankuid) {
        this.bankuid = bankuid == null ? null : bankuid.trim();
    }

    /**
     * 获取银行logo
     *
     * @return bankimg - 银行logo
     */
    public String getBankimg() {
        return bankimg;
    }

    /**
     * 设置银行logo
     *
     * @param bankimg 银行logo
     */
    public void setBankimg(String bankimg) {
        this.bankimg = bankimg == null ? null : bankimg.trim();
    }

    /**
     * 获取银行logo2
     *
     * @return bankimg2 - 银行logo2
     */
    public String getBankimg2() {
        return bankimg2;
    }

    /**
     * 设置银行logo2
     *
     * @param bankimg2 银行logo2
     */
    public void setBankimg2(String bankimg2) {
        this.bankimg2 = bankimg2 == null ? null : bankimg2.trim();
    }

    /**
     * 获取银行logo3
     *
     * @return bankimg3 - 银行logo3
     */
    public String getBankimg3() {
        return bankimg3;
    }

    /**
     * 设置银行logo3
     *
     * @param bankimg3 银行logo3
     */
    public void setBankimg3(String bankimg3) {
        this.bankimg3 = bankimg3 == null ? null : bankimg3.trim();
    }

    /**
     * 获取银行logo4
     *
     * @return bankimg4 - 银行logo4
     */
    public String getBankimg4() {
        return bankimg4;
    }

    /**
     * 设置银行logo4
     *
     * @param bankimg4 银行logo4
     */
    public void setBankimg4(String bankimg4) {
        this.bankimg4 = bankimg4 == null ? null : bankimg4.trim();
    }

    /**
     * 获取银行简介
     *
     * @return bankcontext - 银行简介
     */
    public String getBankcontext() {
        return bankcontext;
    }

    /**
     * 设置银行简介
     *
     * @param bankcontext 银行简介
     */
    public void setBankcontext(String bankcontext) {
        this.bankcontext = bankcontext == null ? null : bankcontext.trim();
    }

    /**
     * 获取放款额
     *
     * @return fke - 放款额
     */
    public Integer getFke() {
        return fke;
    }

    /**
     * 设置放款额
     *
     * @param fke 放款额
     */
    public void setFke(Integer fke) {
        this.fke = fke;
    }

    /**
     * 获取成交数量
     *
     * @return cjnum - 成交数量
     */
    public Integer getCjnum() {
        return cjnum;
    }

    /**
     * 设置成交数量
     *
     * @param cjnum 成交数量
     */
    public void setCjnum(Integer cjnum) {
        this.cjnum = cjnum;
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
     * 获取地址
     *
     * @return adress - 地址
     */
    public String getAdress() {
        return adress;
    }

    /**
     * 设置地址
     *
     * @param adress 地址
     */
    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    /**
     * 获取融资企业数
     *
     * @return rzqys - 融资企业数
     */
    public Integer getRzqys() {
        return rzqys;
    }

    /**
     * 设置融资企业数
     *
     * @param rzqys 融资企业数
     */
    public void setRzqys(Integer rzqys) {
        this.rzqys = rzqys;
    }

    /**
     * 获取上班时间
     *
     * @return sbtime - 上班时间
     */
    public String getSbtime() {
        return sbtime;
    }

    /**
     * 设置上班时间
     *
     * @param sbtime 上班时间
     */
    public void setSbtime(String sbtime) {
        this.sbtime = sbtime == null ? null : sbtime.trim();
    }

    /**
     * 获取预留字段
     *
     * @return yl1 - 预留字段
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置预留字段
     *
     * @param yl1 预留字段
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
     * 获取atm或者营业厅
是否在用
     *
     * @return type - atm或者营业厅
是否在用
     */
    public String getType() {
        return type;
    }

    /**
     * 设置atm或者营业厅
是否在用
     *
     * @param type atm或者营业厅
是否在用
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取银行背景颜色
     *
     * @return bgcolor - 银行背景颜色
     */
    public String getBgcolor() {
        return bgcolor;
    }

    /**
     * 设置银行背景颜色
     *
     * @param bgcolor 银行背景颜色
     */
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor == null ? null : bgcolor.trim();
    }

    /**
     * 获取优先条件
     *
     * @return yxtjid - 优先条件
     */
    public Integer getYxtjid() {
        return yxtjid;
    }

    /**
     * 设置优先条件
     *
     * @param yxtjid 优先条件
     */
    public void setYxtjid(Integer yxtjid) {
        this.yxtjid = yxtjid;
    }
}