package com.shopping.wx.pojo.dto.recruit_record;

import lombok.Data;

/**
 * @ClassName DeliveredJobDTO
 * @Description 已投递岗位
 * @Author zyw
 * @Date 2022/3/29
 **/
@Data
public class RecordForCandidateDTO {
    Integer flowRecruit;
    String candidateOpenid;
    String recruiterOpenid;
    String companyUuid;
    String jobUuid;
    String jobName;
    String companyName;
    String portraitPath;
    Integer jobSalaryMin;
    Integer jobSalaryMax;
    String telephone;
    Double distance;
}
