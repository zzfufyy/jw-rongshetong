package com.shopping.wx.pojo.dto.recruit_record;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName RecordForRecruiterDTO
 * @Description  招聘人查看 招聘列表
 * @Author zyw
 * @Date 2022/3/29
 **/
@Data
public class RecordForRecruiterDTO {
    String recordUuid;
    Integer flowRecruit;
    String candidateOpenid;
    String recruiterOpenid;
    String companyUuid;
    String jobUuid;
    String jobName;
    String  companyName;
    Integer jobSalaryMax;
    Integer jobSalaryMin;
    String realName;
    Integer gender;
    Date birthday;
    String telephone;
    Date createTime;
}
