package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyDTO;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyJobNameList;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyPlus;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @InterfaceName CustomRecruitmentCompanyMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/16
 **/
@Mapper
public interface IRecruitCompanyMapper extends tk.mybatis.mapper.common.Mapper<RecruitCompany> {
    /**
     * countView ++
     *
     * @param id
     * @return int
     */
    int increaseCountView(@Param(value = "id") String id);

    /**
     * 条件分页
     *
     * @param orderType   排序类型
     * @param companyName 模糊 公司名称
     * @param location    位置参数
     * @return java.util.List<com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyDTO>
     */
    List<RecruitCompanyDTO> pagedByCondition(
            @Param("orderType") Integer orderType,
            @Param("communityUuid") String communityUuid,
            @Param("companyName") String companyName,
            @Param("location") Location location);

    /**
     * 基本信息 关联丰富
     *
     * @param id
     * @return com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyPlus
     */
    RecruitCompanyPlus infoWithAssociated(@Param("id") String id);

    List<RecruitCompanyJobNameList> pageJobNameList(@Param("buildingUuid") String buildingUuid, @Param("companyName")String companyName);

}
