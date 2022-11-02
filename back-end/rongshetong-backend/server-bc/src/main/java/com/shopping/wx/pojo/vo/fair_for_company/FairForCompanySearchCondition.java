package com.shopping.wx.pojo.vo.fair_for_company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FairForCompanySearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FairForCompanySearchCondition implements Serializable {
    String fairUuid;
    String companyName;
}
