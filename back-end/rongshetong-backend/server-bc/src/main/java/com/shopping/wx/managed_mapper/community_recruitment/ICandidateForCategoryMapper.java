package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.candidate_for_category.CandidateForCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ICandidateForCategoryMapper {
    List<CandidateForCategoryDTO> selectEntityListByCandidateOpenid(@Param("candidateOpenid") String candiddateOpenid);
}
