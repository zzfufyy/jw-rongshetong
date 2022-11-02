package com.shopping.wx.pojo.vo.recruit_candidate;

import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-11 14:26
 */
@Data
public class UserCandidateVO {
    /**
     * 微信 openid
     */
    String id;
    /**
     * 真实姓名
     */
    String realName;
    /**
     * 手机号码
     */
    String telephone;
    /**
     * 性别
     */
    Integer gender;
    /**
     * 家庭住址
     */
    String address;
    /**
     * 简介
     */
    String introduction;
    /**
     * 最低期望薪资
     */
    Integer expectSalaryMin;
    /**
     * 最高期望薪资
     */
    Integer expectSalaryMax;
    /**
     * 期望工作类型
     */
    String expectCategoryId;
    /**
     * 期望工作的社区
     */
    String expectCommunityId;
    /**
     * 所在社区
     */
    String communityId;
    /**
     * 查看数量
     */
    Integer countView;


}
