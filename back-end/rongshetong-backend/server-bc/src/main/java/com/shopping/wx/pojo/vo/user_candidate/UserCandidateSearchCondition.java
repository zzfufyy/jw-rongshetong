package com.shopping.wx.pojo.vo.user_candidate;

import com.shopping.wx.pojo.vo.BasicLocationSearchCondition;
import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-16 13:58
 * <p>
 * 用户搜索条件
 */
@Data
public class UserCandidateSearchCondition extends BasicLocationSearchCondition {

    String categoryName;

    Integer jobSalaryMin;

    Integer jobSalaryMax;

    Integer orderType;
    /**
     * 社区id
     */
    String communityUuid;
}
