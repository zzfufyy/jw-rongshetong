package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "videoinfo")
public class Videoinfo {
    @Id
    private String id;

    /**
     * 视频名
     */
    private String videoname;

    /**
     * 视频地址
     */
    private String videourl;

    /**
     * 所属银行
     */
    private String bankid;

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
     * 获取视频名
     *
     * @return videoname - 视频名
     */
    public String getVideoname() {
        return videoname;
    }

    /**
     * 设置视频名
     *
     * @param videoname 视频名
     */
    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }

    /**
     * 获取视频地址
     *
     * @return videourl - 视频地址
     */
    public String getVideourl() {
        return videourl;
    }

    /**
     * 设置视频地址
     *
     * @param videourl 视频地址
     */
    public void setVideourl(String videourl) {
        this.videourl = videourl == null ? null : videourl.trim();
    }

    /**
     * 获取所属银行
     *
     * @return bankid - 所属银行
     */
    public String getBankid() {
        return bankid;
    }

    /**
     * 设置所属银行
     *
     * @param bankid 所属银行
     */
    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
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