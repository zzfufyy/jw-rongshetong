package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FairForCompany;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyDTO;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyJobDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_company.FairForCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FairForCompanyService
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
public interface FairForCompanyService extends CrudService<FairForCompany> {

    List<FairForCompanyDTO> pageDTO(PagingParam<FairForCompanySearchCondition> pagingParam);

    List<FairForCompanyJobDTO> pageJobDTO(PagingParam<FairForCompanySearchCondition> pagingParam);
}
