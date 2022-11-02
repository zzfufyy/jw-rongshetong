package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notice")
public class Notice {
    @Id
    private String id;

    /**
     * 公告内容
     */
    private String notice;

    /**
     * 发布时间
     */
    private Date fbtime;

    /**
     * 相关人员
     */
    private String xgry;

    /**
     * 相关银行id
     */
    private String xgyhid;

    /**
     * 相关银行名
     */
    private String xgyhname;

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
     * 获取公告内容
     *
     * @return notice - 公告内容
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 设置公告内容
     *
     * @param notice 公告内容
     */
    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    /**
     * 获取发布时间
     *
     * @return fbtime - 发布时间
     */
    public Date getFbtime() {
        return fbtime;
    }

    /**
     * 设置发布时间
     *
     * @param fbtime 发布时间
     */
    public void setFbtime(Date fbtime) {
        this.fbtime = fbtime;
    }

    /**
     * 获取相关人员
     *
     * @return xgry - 相关人员
     */
    public String getXgry() {
        return xgry;
    }

    /**
     * 设置相关人员
     *
     * @param xgry 相关人员
     */
    public void setXgry(String xgry) {
        this.xgry = xgry == null ? null : xgry.trim();
    }

    /**
     * 获取相关银行id
     *
     * @return xgyhid - 相关银行id
     */
    public String getXgyhid() {
        return xgyhid;
    }

    /**
     * 设置相关银行id
     *
     * @param xgyhid 相关银行id
     */
    public void setXgyhid(String xgyhid) {
        this.xgyhid = xgyhid == null ? null : xgyhid.trim();
    }

    /**
     * 获取相关银行名
     *
     * @return xgyhname - 相关银行名
     */
    public String getXgyhname() {
        return xgyhname;
    }

    /**
     * 设置相关银行名
     *
     * @param xgyhname 相关银行名
     */
    public void setXgyhname(String xgyhname) {
        this.xgyhname = xgyhname == null ? null : xgyhname.trim();
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