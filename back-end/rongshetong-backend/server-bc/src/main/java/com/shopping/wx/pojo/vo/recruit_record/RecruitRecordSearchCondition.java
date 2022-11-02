package com.shopping.wx.pojo.vo.recruit_record;

import com.shopping.wx.pojo.vo.basic.TimeCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ljy
 * @date 2022-03-16 16:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecruitRecordSearchCondition extends TimeCondition {
    /**
     * 求职方 openid
     */
    String candidateOpenid;

    /**
     * 招聘方 openid
     */

    String recruiterOpenid;
    /**
     * 接收方是否查看
     */
    Boolean flagViewReceive;
    /**
     * 是否合适
     */
    Boolean flagSuitable;
    /***
     * 面试进程
     */
    Integer flowRecruit;
}
