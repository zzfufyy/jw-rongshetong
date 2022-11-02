package com.shopping.wx.managed_mapper.community_recruitment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName IResidentForPersonMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/29
 **/
@Mapper
public interface IResidentForPersonMapper {

    int deleteByCellUuid(@Param("cellUuid") String cellUuid);
}
