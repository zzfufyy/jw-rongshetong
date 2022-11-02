package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidateDTO;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidatePlus;
import com.shopping.wx.pojo.dto.recruit_record.RecordForRecruiterDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_record.RecruitRecordSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-16 15:56
 * <p>
 * 招聘记录服务
 */
public interface RecruitRecordService extends CrudService<RecruitRecord> {


    List<RecruitRecord> page(PagingParam<RecruitRecordSearchCondition> pagingParam);

    List<RecordForCandidateDTO> listRecordPlusByCandidateOpenid(String candidateOpenid, Location location);

    List<RecordForRecruiterDTO> listRecordPlusByCompanyUuid(String companyUuid);


    List<RecordForCandidatePlus> listRecordForCandidatePlus(RecruitRecordSearchCondition recordSearchCondition);

    Integer countInterviewResult(String candidateOpenid);
}
