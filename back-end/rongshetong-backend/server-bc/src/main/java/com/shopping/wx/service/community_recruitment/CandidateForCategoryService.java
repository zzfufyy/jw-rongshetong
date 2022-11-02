package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CandidateForCategory;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CompanyForCategoryService
 * @Description 公司意向表
 * @Author zyw
 * @Date 2022/3/31
 **/
public interface CandidateForCategoryService extends CrudService<CandidateForCategory> {

    /**
     * 求职者  岗位意向名称 list
     *
     * @param candidateOpenid
     * @return java.util.List<java.lang.String>
     */
    List<String> selectCategoryNameListByCandidateOpenid(String candidateOpenid);

    /**
     * 求职者意向 entity list表
     *
     * @param candidateOpenid
     * @return java.util.List<com.shopping.wx.model.CandidateForCategory>
     */
    List<CandidateForCategory> listByCandidateOpenid(String candidateOpenid);
}
