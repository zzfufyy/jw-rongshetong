package com.shopping.wx.pojo.dto.recruit_company;

import com.shopping.wx.pojo.vo.BasicLocationSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName RecruitCompanyDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitCompanyDTO {
    String companyUuid;

    String companyName;

    String recruiterOpenid;

    String recruiterName;

    Integer recruiterGender;

    String recruiterPortraitPath;

    String phone;

    Double distance;

    Date createTime;
}
