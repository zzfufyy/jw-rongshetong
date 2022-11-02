package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rwjd")
public class Rwjd {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 进度名
     */
    private String name;

    /**
     * 开始时间
     */
    private Date datetime;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 顺序编号
     */
    private Integer bh;

    /**
     * 进度是否结束,0-未结束,1-结束
     */
    private Integer isfinish;

    /**
     * 任务id
     */
    private String rwid;

    /**
     * 退回理由
     */
    private String thly;

    /**
     * 处理银行负责人oppenid
     */
    private String slroppenid;

    /**
     * 处理银行负责人姓名
     */
    private String slr;

    /**
     * 处理银行负责人电话
     */
    private String slphone;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取进度名
     *
     * @return name - 进度名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置进度名
     *
     * @param name 进度名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取开始时间
     *
     * @return datetime - 开始时间
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * 设置开始时间
     *
     * @param datetime 开始时间
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取顺序编号
     *
     * @return bh - 顺序编号
     */
    public Integer getBh() {
        return bh;
    }

    /**
     * 设置顺序编号
     *
     * @param bh 顺序编号
     */
    public void setBh(Integer bh) {
        this.bh = bh;
    }

    /**
     * 获取进度是否结束,0-未结束,1-结束
     *
     * @return isfinish - 进度是否结束,0-未结束,1-结束
     */
    public Integer getIsfinish() {
        return isfinish;
    }

    /**
     * 设置进度是否结束,0-未结束,1-结束
     *
     * @param isfinish 进度是否结束,0-未结束,1-结束
     */
    public void setIsfinish(Integer isfinish) {
        this.isfinish = isfinish;
    }

    /**
     * 获取任务id
     *
     * @return rwid - 任务id
     */
    public String getRwid() {
        return rwid;
    }

    /**
     * 设置任务id
     *
     * @param rwid 任务id
     */
    public void setRwid(String rwid) {
        this.rwid = rwid == null ? null : rwid.trim();
    }

    /**
     * 获取退回理由
     *
     * @return thly - 退回理由
     */
    public String getThly() {
        return thly;
    }

    /**
     * 设置退回理由
     *
     * @param thly 退回理由
     */
    public void setThly(String thly) {
        this.thly = thly == null ? null : thly.trim();
    }

    /**
     * 获取处理银行负责人oppenid
     *
     * @return slroppenid - 处理银行负责人oppenid
     */
    public String getSlroppenid() {
        return slroppenid;
    }

    /**
     * 设置处理银行负责人oppenid
     *
     * @param slroppenid 处理银行负责人oppenid
     */
    public void setSlroppenid(String slroppenid) {
        this.slroppenid = slroppenid == null ? null : slroppenid.trim();
    }

    /**
     * 获取处理银行负责人姓名
     *
     * @return slr - 处理银行负责人姓名
     */
    public String getSlr() {
        return slr;
    }

    /**
     * 设置处理银行负责人姓名
     *
     * @param slr 处理银行负责人姓名
     */
    public void setSlr(String slr) {
        this.slr = slr == null ? null : slr.trim();
    }

    /**
     * 获取处理银行负责人电话
     *
     * @return slphone - 处理银行负责人电话
     */
    public String getSlphone() {
        return slphone;
    }

    /**
     * 设置处理银行负责人电话
     *
     * @param slphone 处理银行负责人电话
     */
    public void setSlphone(String slphone) {
        this.slphone = slphone == null ? null : slphone.trim();
    }
}