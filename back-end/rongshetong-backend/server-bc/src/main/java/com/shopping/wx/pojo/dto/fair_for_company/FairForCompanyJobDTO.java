package com.shopping.wx.pojo.dto.fair_for_company;

/**
 * @ClassName FairForCompanyDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/25
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FairForCompanyJobDTO implements Serializable {
    String companyUuid;
    String companyName;
    String jobUuid;
    String jobName;
    Integer jobSalaryMin;
    Integer jobSalaryMax;
    String recruiterOpenid;
    String recruiterName;
    Integer recruiterGender;
    String recruiterPortraitPath;
    String recruitingNumber;
    Date createTime;
}
