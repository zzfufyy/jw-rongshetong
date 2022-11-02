package com.shopping.wx.pojo.dto.recruit_company;

import com.shopping.wx.model.RecruitCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RecruitCompanyPlusJobList
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitCompanyJobNameList{
    String companyUuid;
    String companyName;
    String address;
    String addressDetail;
    String jobNameList;
}
