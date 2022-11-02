package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.user_recruiter.UserRecruiterPlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_recruiter.UserRecruiterSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName UserRecruiterService
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/14
 **/
public interface UserRecruiterService extends CrudService<UserRecruiter> {

    int updateCompanyUuid(String openid, String CompanyUuid);

    /**
     * 分页  where
     *
     * @param paagingParam
     * @return java.util.List<com.shopping.wx.model.UserRecruiter>
     */
    List<UserRecruiter> page(PagingParam<UserRecruiterSearchCondition> paagingParam);

    /**
     * 根据id查找并丰富
     *
     * @param id
     * @return com.shopping.wx.pojo.dto.user_recruiter.UserRecruiterPlus
     */
    UserRecruiterPlus infoPlus(String id);

}
