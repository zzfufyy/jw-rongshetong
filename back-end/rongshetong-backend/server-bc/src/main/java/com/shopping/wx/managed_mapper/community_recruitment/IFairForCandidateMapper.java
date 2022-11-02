package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.fair_for_candidate.FairForCandidateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyw
 * @date 2022-05-25
 */
@Mapper
public interface IFairForCandidateMapper {

    List<FairForCandidateDTO> pageDTO(@Param("fairUuid") String fairUuid);
}
