package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.JobCategory;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_category.JobCategorySearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName JobCategoryService
 * @Description 职业类别service
 * @Author zyw
 * @Date 2022/3/14
 **/
public interface JobCategoryService extends CrudService<JobCategory> {
    /**
     * 分页
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.model.UserRecruiter>
     */
    List<JobCategory> page(PagingParam<JobCategorySearchCondition> pagingParam);

    /**
    * list列表
    * @param  categoryName
    * @return java.util.List<com.shopping.wx.model.JobCategory> 
    */
    List<JobCategory> list(String categoryName);


    List<JobCategory> listByNumber(Integer number, String categoryName);

    /**
     * 浏览量自增
     *
     * @param id
     * @return java.lang.Boolean
     */
    Boolean incrCountView(String id);

    /**
     * 根据分类名称查找数量
     *
     * @param categoryName
     * @return long
     */
    long selectCountByCategoryName(String categoryName);


}
