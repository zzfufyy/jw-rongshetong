package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.InformationNews;
import com.shopping.wx.model.JobCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName IInformationNewsMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/19
 **/
@Mapper
public interface IInformationNewsMapper extends tk.mybatis.mapper.common.Mapper<InformationNews> {
    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int increaseCountView(@Param(value = "id") String id);

}
