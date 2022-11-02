package com.shopping.wx.pojo.vo.recruit_company;

import com.shopping.wx.pojo.vo.BasicLocationSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RecruitCompanySearchCondition
 * @Description 公司条件查询类
 * @Author zyw
 * @Date 2022/3/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitCompanySearchCondition extends BasicLocationSearchCondition implements Serializable {
    String id;

    Integer orderType;

    String companyName;

    String communityUuid;

    Integer status;

    Integer flagIdentification;

    String buildingUuid;

}
