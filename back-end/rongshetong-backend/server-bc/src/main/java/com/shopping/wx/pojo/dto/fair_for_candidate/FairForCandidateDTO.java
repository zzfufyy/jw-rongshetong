package com.shopping.wx.pojo.dto.fair_for_candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FairForCandidatePlus
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FairForCandidateDTO implements Serializable {
    String candidateOpenid;
    String candidateName;
    String candidatePortraitPath;

    Integer gender;
    Integer workingAge;
    Date birthday;

    Integer expectSalaryMin;
    Integer expectSalaryMax;

    String expectCategoryUuid;
    String expectCategoryName;
    String expectCommunityUuid;
    String expectCommunityName;
    String communityUuid;
    Date createTime;
}
