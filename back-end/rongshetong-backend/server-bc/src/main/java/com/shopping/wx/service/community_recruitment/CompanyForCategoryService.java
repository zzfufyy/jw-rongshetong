package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CompanyForCategoryService
 * @Description 公司意向表
 * @Author zyw
 * @Date 2022/3/31
 **/
public interface CompanyForCategoryService extends CrudService<CompanyForCategory> {


    List<String> selectCategoryNameListByCompanyUuid(String companyUuid);
}
