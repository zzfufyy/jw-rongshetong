package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "about")
public class About {
    @Id
    private Integer id;

    /**
     * 产品名称
     */
    private String productname;

    /**
     * 描述
     */
    private String context;

    /**
     * 当前版本
     */
    private String edition;

    /**
     * 个人id
     */
    private String personid;

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
     * 获取产品名称
     *
     * @return productname - 产品名称
     */
    public String getProductname() {
        return productname;
    }

    /**
     * 设置产品名称
     *
     * @param productname 产品名称
     */
    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    /**
     * 获取描述
     *
     * @return context - 描述
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置描述
     *
     * @param context 描述
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    /**
     * 获取当前版本
     *
     * @return edition - 当前版本
     */
    public String getEdition() {
        return edition;
    }

    /**
     * 设置当前版本
     *
     * @param edition 当前版本
     */
    public void setEdition(String edition) {
        this.edition = edition == null ? null : edition.trim();
    }

    /**
     * 获取个人id
     *
     * @return personid - 个人id
     */
    public String getPersonid() {
        return personid;
    }

    /**
     * 设置个人id
     *
     * @param personid 个人id
     */
    public void setPersonid(String personid) {
        this.personid = personid == null ? null : personid.trim();
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