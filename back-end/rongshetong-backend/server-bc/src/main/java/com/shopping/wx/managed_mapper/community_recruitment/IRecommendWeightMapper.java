package com.shopping.wx.managed_mapper.community_recruitment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName IRecommendWeightMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/17
 **/
@Mapper
public interface IRecommendWeightMapper {
    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int incrCountView(@Param(value = "id") String id);
}
