package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "dkzp")
public class Dkzp {
    @Id
    private Integer id;

    private String oldbkid;

    private String newbkid;

    private String oldorganid;

    private String neworganid;

    private String zpsj;

    private String oldbkname;

    private String newbkname;

    private String oldorganname;

    private String neworganname;

    /**
     * 任务id
     */
    private String yl1;

    private String yl2;

    private String yl3;

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
     * @return oldbkid
     */
    public String getOldbkid() {
        return oldbkid;
    }

    /**
     * @param oldbkid
     */
    public void setOldbkid(String oldbkid) {
        this.oldbkid = oldbkid == null ? null : oldbkid.trim();
    }

    /**
     * @return newbkid
     */
    public String getNewbkid() {
        return newbkid;
    }

    /**
     * @param newbkid
     */
    public void setNewbkid(String newbkid) {
        this.newbkid = newbkid == null ? null : newbkid.trim();
    }

    /**
     * @return oldorganid
     */
    public String getOldorganid() {
        return oldorganid;
    }

    /**
     * @param oldorganid
     */
    public void setOldorganid(String oldorganid) {
        this.oldorganid = oldorganid == null ? null : oldorganid.trim();
    }

    /**
     * @return neworganid
     */
    public String getNeworganid() {
        return neworganid;
    }

    /**
     * @param neworganid
     */
    public void setNeworganid(String neworganid) {
        this.neworganid = neworganid == null ? null : neworganid.trim();
    }

    /**
     * @return zpsj
     */
    public String getZpsj() {
        return zpsj;
    }

    /**
     * @param zpsj
     */
    public void setZpsj(String zpsj) {
        this.zpsj = zpsj == null ? null : zpsj.trim();
    }

    /**
     * @return oldbkname
     */
    public String getOldbkname() {
        return oldbkname;
    }

    /**
     * @param oldbkname
     */
    public void setOldbkname(String oldbkname) {
        this.oldbkname = oldbkname == null ? null : oldbkname.trim();
    }

    /**
     * @return newbkname
     */
    public String getNewbkname() {
        return newbkname;
    }

    /**
     * @param newbkname
     */
    public void setNewbkname(String newbkname) {
        this.newbkname = newbkname == null ? null : newbkname.trim();
    }

    /**
     * @return oldorganname
     */
    public String getOldorganname() {
        return oldorganname;
    }

    /**
     * @param oldorganname
     */
    public void setOldorganname(String oldorganname) {
        this.oldorganname = oldorganname == null ? null : oldorganname.trim();
    }

    /**
     * @return neworganname
     */
    public String getNeworganname() {
        return neworganname;
    }

    /**
     * @param neworganname
     */
    public void setNeworganname(String neworganname) {
        this.neworganname = neworganname == null ? null : neworganname.trim();
    }

    /**
     * 获取任务id
     *
     * @return yl1 - 任务id
     */
    public String getYl1() {
        return yl1;
    }

    /**
     * 设置任务id
     *
     * @param yl1 任务id
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