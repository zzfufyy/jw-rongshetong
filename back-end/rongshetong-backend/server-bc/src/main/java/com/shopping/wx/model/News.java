package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news")
public class News {
    @Id
    private Integer id;

    /**
     * 新闻标题
     */
    private String xwtitle;

    /**
     * 新闻描述
     */
    private String xwdesc;

    /**
     * 新闻内容
     */
    private String xwcontext;

    /**
     * 发布时间
     */
    private Date fbtime;

    /**
     * 创建人
     */
    private String cjr;

    /**
     * 新闻主要图片
     */
    private String xwtp;

    /**
     * 浏览量
     */
    private String lll;

    /**
     * 新闻来源
     */
    private String ly;

    private String yl1;

    private String yl2;

    private String yl3;

    /**
     * 状态:0-删除1-使用中
     */
    private Integer state;

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
     * 获取新闻标题
     *
     * @return xwtitle - 新闻标题
     */
    public String getXwtitle() {
        return xwtitle;
    }

    /**
     * 设置新闻标题
     *
     * @param xwtitle 新闻标题
     */
    public void setXwtitle(String xwtitle) {
        this.xwtitle = xwtitle == null ? null : xwtitle.trim();
    }

    /**
     * 获取新闻描述
     *
     * @return xwdesc - 新闻描述
     */
    public String getXwdesc() {
        return xwdesc;
    }

    /**
     * 设置新闻描述
     *
     * @param xwdesc 新闻描述
     */
    public void setXwdesc(String xwdesc) {
        this.xwdesc = xwdesc == null ? null : xwdesc.trim();
    }

    /**
     * 获取新闻内容
     *
     * @return xwcontext - 新闻内容
     */
    public String getXwcontext() {
        return xwcontext;
    }

    /**
     * 设置新闻内容
     *
     * @param xwcontext 新闻内容
     */
    public void setXwcontext(String xwcontext) {
        this.xwcontext = xwcontext == null ? null : xwcontext.trim();
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
     * 获取创建人
     *
     * @return cjr - 创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 设置创建人
     *
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    /**
     * 获取新闻主要图片
     *
     * @return xwtp - 新闻主要图片
     */
    public String getXwtp() {
        return xwtp;
    }

    /**
     * 设置新闻主要图片
     *
     * @param xwtp 新闻主要图片
     */
    public void setXwtp(String xwtp) {
        this.xwtp = xwtp == null ? null : xwtp.trim();
    }

    /**
     * 获取浏览量
     *
     * @return lll - 浏览量
     */
    public String getLll() {
        return lll;
    }

    /**
     * 设置浏览量
     *
     * @param lll 浏览量
     */
    public void setLll(String lll) {
        this.lll = lll == null ? null : lll.trim();
    }

    /**
     * 获取新闻来源
     *
     * @return ly - 新闻来源
     */
    public String getLy() {
        return ly;
    }

    /**
     * 设置新闻来源
     *
     * @param ly 新闻来源
     */
    public void setLy(String ly) {
        this.ly = ly == null ? null : ly.trim();
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

    /**
     * 获取状态:0-删除1-使用中
     *
     * @return state - 状态:0-删除1-使用中
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态:0-删除1-使用中
     *
     * @param state 状态:0-删除1-使用中
     */
    public void setState(Integer state) {
        this.state = state;
    }
}