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
public class FairForCompanyDTO implements Serializable {
    String companyUuid;
    String companyName;
    Integer countJob;
    String recruiterOpenid;
    String recruiterName;
    String companyPortraitPath;
    Date createTime;

}
