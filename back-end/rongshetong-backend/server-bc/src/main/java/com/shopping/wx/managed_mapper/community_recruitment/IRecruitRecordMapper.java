package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidateDTO;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidatePlus;
import com.shopping.wx.pojo.dto.recruit_record.RecordForRecruiterDTO;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-25 14:45
 */

@Mapper
public interface IRecruitRecordMapper {
    /**
     * 根据 用户openid 和位置查询列表
     *
     * @param candidateOpenid
     * @param location        位置（经纬度）
     * @return 结果
     */
    List<RecordForCandidateDTO> listRecordPlusByCandidateOpenid(@Param("candidateOpenid") String candidateOpenid, @Param("location") Location location);

    /**
     * 根据公司id 返回查询记录的列表
     *
     * @param companyUuid
     * @return java.util.List<com.shopping.wx.pojo.dto.recruit_record.RecordForCandidateDTO>
     */
    List<RecordForRecruiterDTO> listRecordPlusByCompanyUuid(@Param("companyUuid") String companyUuid);

    /**
     * 返回 记录 for 求职者丰富列表
     *
     * @param candidateOpenid
     * @param flowRecruit
     * @return java.util.List<com.shopping.wx.pojo.dto.recruit_record.RecordForCandidatePlus>
     */
    List<RecordForCandidatePlus> listRecordForCandidatePlus(@Param("candidateOpenid") String  candidateOpenid,@Param("flowRecruit") Integer flowRecruit);
}
