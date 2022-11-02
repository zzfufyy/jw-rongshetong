package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "topimg")
public class Topimg {
    @Id
    private Integer id;

    /**
     * 视频名
     */
    private String imgurl;

    /**
     * 视频地址
     */
    private String videourl;

    /**
     * 所属银行
     */
    private String tplj;

    private String yl1;

    private String yl2;

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
     * 获取视频名
     *
     * @return imgurl - 视频名
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 设置视频名
     *
     * @param imgurl 视频名
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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
     * @return tplj - 所属银行
     */
    public String getTplj() {
        return tplj;
    }

    /**
     * 设置所属银行
     *
     * @param tplj 所属银行
     */
    public void setTplj(String tplj) {
        this.tplj = tplj == null ? null : tplj.trim();
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