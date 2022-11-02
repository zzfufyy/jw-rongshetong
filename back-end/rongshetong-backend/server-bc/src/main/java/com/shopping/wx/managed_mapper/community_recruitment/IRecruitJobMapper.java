package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-25 14:45
 */

@Mapper
public interface IRecruitJobMapper {
    /**
     * 根据位置进行分页查询
     *
     * @param location 位置（经纬度）
     * @return 结果
     */
    List<JobInfoDTO> pagedByDistance(
                                     @Param("communityUuid") String communityUuid,
                                     @Param("orderType") Integer orderType,
                                     @Param("jobName") String jobName,
                                     @Param("salaryCompareState") Integer salaryCompareState,
                                     @Param("jobSalaryMin") Integer jobSalaryMin,
                                     @Param("jobSalaryMax") Integer jobSalaryMax,
                                     @Param("candidateOpenid") String candidateOpenid,
                                     @Param("location") Location location);

    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int increaseCountView(@Param(value = "id") String id);

    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int increaseCountApply(@Param(value = "id") String id);

}
