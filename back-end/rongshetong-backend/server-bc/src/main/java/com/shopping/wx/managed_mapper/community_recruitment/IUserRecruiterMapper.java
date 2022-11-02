package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.user_recruiter.UserRecruiterPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zyw
 * @date 2022-06-01 11:25
 */
@Mapper
public interface IUserRecruiterMapper {

    UserRecruiterPlus infoPlus(@Param("id") String id);
}
