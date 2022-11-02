package com.shopping.wx.pojo.dto.user_candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserCandidateDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCandidateDTO implements Serializable {
    String candidateOpenid;
    String realName;
    String candidatePortraitPath;
    Integer gender;
    Date birthday;
    String phone;
    Date createTime;
    Integer expectSalaryMax;
    Integer expectSalaryMin;
    String expectCategoryId;
    String categoryName;
    String expectCommunityId;
    String communityName;
    Double distance;

    String eduSchoolName;
    String eduBackgroundName;
    String eduMajor;
    Date eduBeginTime;
    Date eduEndTime;


}
