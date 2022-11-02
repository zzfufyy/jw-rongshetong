package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_state")
public class UserState {
    /**
     * openid主键
     */
    @Id
    private String id;

    /**
     * 用户角色 0:求职者 1: 招聘者
     */
    @Column(name = "user_role")
    private Integer userRole;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录页面
     */
    @Column(name = "login_page")
    private String loginPage;

    /**
     * 登录页面参数
     */
    @Column(name = "login_page_param")
    private String loginPageParam;

    /**
     * 本条状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 信息录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 获取openid主键
     *
     * @return id - openid主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置openid主键
     *
     * @param id openid主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取用户角色 0:求职者 1: 招聘者
     *
     * @return user_role - 用户角色 0:求职者 1: 招聘者
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置用户角色 0:求职者 1: 招聘者
     *
     * @param userRole 用户角色 0:求职者 1: 招聘者
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录页面
     *
     * @return login_page - 登录页面
     */
    public String getLoginPage() {
        return loginPage;
    }

    /**
     * 设置登录页面
     *
     * @param loginPage 登录页面
     */
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage == null ? null : loginPage.trim();
    }

    /**
     * 获取登录页面参数
     *
     * @return login_page_param - 登录页面参数
     */
    public String getLoginPageParam() {
        return loginPageParam;
    }

    /**
     * 设置登录页面参数
     *
     * @param loginPageParam 登录页面参数
     */
    public void setLoginPageParam(String loginPageParam) {
        this.loginPageParam = loginPageParam == null ? null : loginPageParam.trim();
    }

    /**
     * 获取本条状态（0：正常 -1：失效）
     *
     * @return status - 本条状态（0：正常 -1：失效）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1：失效）
     *
     * @param status 本条状态（0：正常 -1：失效）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取信息录入时间
     *
     * @return create_time - 信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置信息录入时间
     *
     * @param createTime 信息录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取扩展字段1
     *
     * @return ext1 - 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 获取扩展字段2
     *
     * @return ext2 - 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 获取扩展字段3
     *
     * @return ext3 - 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}