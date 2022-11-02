package com.shopping.wx.model;

import javax.persistence.*;

@Entity
@Table(name = "organ")
public class Organ {


    public Organ(){}

    public Organ(String bankid, String organname) {
        this.bankid = bankid;
        this.organname = organname;
    }

    @Id
    private String id;

    /**
     * 所属银行id
     */
    private String bankid;

    /**
     * 名称
     */
    private String organname;

    /**
     * atm或者营业厅 0-ATM,1-营业厅
     */
    private String type;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String lxdh;

    private String yl1;

    private String yl2;

    /**
     * 经度
     */
    private String jd;

    /**
     * 纬度
     */
    private String wd;

    /**
     * 银行类型
     */
    private String ttype;

    /**
     * 联系人
     */
    @Column(name = "flinkMan")
    private String flinkman;

    /**
     * 银行简介
     */
    private String fdesc;

    /**
     * 贷款额
     */
    private String dkye;

    /**
     * 成交户数
     */
    private String cjhs;

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
     * 获取所属银行id
     *
     * @return bankid - 所属银行id
     */
    public String getBankid() {
        return bankid;
    }

    /**
     * 设置所属银行id
     *
     * @param bankid 所属银行id
     */
    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    /**
     * 获取名称
     *
     * @return organname - 名称
     */
    public String getOrganname() {
        return organname;
    }

    /**
     * 设置名称
     *
     * @param organname 名称
     */
    public void setOrganname(String organname) {
        this.organname = organname == null ? null : organname.trim();
    }

    /**
     * 获取atm或者营业厅 0-ATM,1-营业厅
     *
     * @return type - atm或者营业厅 0-ATM,1-营业厅
     */
    public String getType() {
        return type;
    }

    /**
     * 设置atm或者营业厅 0-ATM,1-营业厅
     *
     * @param type atm或者营业厅 0-ATM,1-营业厅
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 获取经度
     *
     * @return jd - 经度
     */
    public String getJd() {
        return jd;
    }

    /**
     * 设置经度
     *
     * @param jd 经度
     */
    public void setJd(String jd) {
        this.jd = jd == null ? null : jd.trim();
    }

    /**
     * 获取纬度
     *
     * @return wd - 纬度
     */
    public String getWd() {
        return wd;
    }

    /**
     * 设置纬度
     *
     * @param wd 纬度
     */
    public void setWd(String wd) {
        this.wd = wd == null ? null : wd.trim();
    }

    /**
     * 获取银行类型
     *
     * @return ttype - 银行类型
     */
    public String getTtype() {
        return ttype;
    }

    /**
     * 设置银行类型
     *
     * @param ttype 银行类型
     */
    public void setTtype(String ttype) {
        this.ttype = ttype == null ? null : ttype.trim();
    }

    /**
     * 获取联系人
     *
     * @return flinkMan - 联系人
     */
    public String getFlinkman() {
        return flinkman;
    }

    /**
     * 设置联系人
     *
     * @param flinkman 联系人
     */
    public void setFlinkman(String flinkman) {
        this.flinkman = flinkman == null ? null : flinkman.trim();
    }

    /**
     * 获取银行简介
     *
     * @return fdesc - 银行简介
     */
    public String getFdesc() {
        return fdesc;
    }

    /**
     * 设置银行简介
     *
     * @param fdesc 银行简介
     */
    public void setFdesc(String fdesc) {
        this.fdesc = fdesc == null ? null : fdesc.trim();
    }

    /**
     * 获取贷款额
     *
     * @return dkye - 贷款额
     */
    public String getDkye() {
        return dkye;
    }

    /**
     * 设置贷款额
     *
     * @param dkye 贷款额
     */
    public void setDkye(String dkye) {
        this.dkye = dkye == null ? null : dkye.trim();
    }

    /**
     * 获取成交户数
     *
     * @return cjhs - 成交户数
     */
    public String getCjhs() {
        return cjhs;
    }

    /**
     * 设置成交户数
     *
     * @param cjhs 成交户数
     */
    public void setCjhs(String cjhs) {
        this.cjhs = cjhs == null ? null : cjhs.trim();
    }
}