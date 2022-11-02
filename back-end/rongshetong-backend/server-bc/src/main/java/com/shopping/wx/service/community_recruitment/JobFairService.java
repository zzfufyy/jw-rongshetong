package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.JobFair;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_fair.JobFairSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName JobFairService
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
public interface JobFairService extends CrudService<JobFair> {

    List<JobFair> page(PagingParam<JobFairSearchCondition> pagingParam);
}
