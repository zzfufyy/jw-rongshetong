package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyDTO;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyJobDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyw
 * @date 2022-05-25
 */
@Mapper
public interface IFairForCompanyMapper {

    List<FairForCompanyDTO> pageDTO(@Param("fairUuid") String fairUuid);

    List<FairForCompanyJobDTO> pageJobDTO(@Param("fairUuid") String fairUuid,@Param("companyName") String companyName);
}
