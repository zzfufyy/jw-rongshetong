package com.shopping.wx.pojo.dto.recruit_company;

import com.shopping.wx.model.RecruitCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RecruitCompanyPlus
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitCompanyPlus extends RecruitCompany {
    String communityName;
}
