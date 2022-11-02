package com.shopping.wx.pojo.vo.user_recruiter;

import lombok.Data;

/**
 * @ClassName UserRecruiterSearchCondition
 * @Description user_recruiter 条件查询
 * @Author zyw
 * @Date 2022/3/17
 **/
@Data
public class UserRecruiterSearchCondition {
    String id;

    String realName;

    String telephone;

    String companyUuid;

    String status;
}
