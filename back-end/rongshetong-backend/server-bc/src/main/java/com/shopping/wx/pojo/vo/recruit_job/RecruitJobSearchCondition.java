package com.shopping.wx.pojo.vo.recruit_job;

import com.shopping.wx.pojo.vo.BasicLocationSearchCondition;
import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-15 13:53
 */

@Data
public class RecruitJobSearchCondition extends BasicLocationSearchCondition {

    String candidateOpenid;

    String jobName;

    Integer jobSalaryMin;

    Integer jobSalaryMax;

    /**
     * 排序方式
     */
    Integer orderType;

    /**
     * 关联 -  recruit_company
     */
    String communityUuid;

    /**
     * 正常page条件 -  companyUuid
     */
    String companyUuid;

}
