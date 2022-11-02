package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.JobCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName IJobCategoryMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/17
 **/
@Mapper
public interface IJobCategoryMapper extends tk.mybatis.mapper.common.Mapper<JobCategory> {
    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int incrCountView(@Param(value = "id") String id);

}
