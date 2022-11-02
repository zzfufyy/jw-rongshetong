package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @author ljy
 */
public interface RecruitJobService extends CrudService<RecruitJob> {

    /**
     * 分页查询
     *
     * @param pagingParam
     * @return
     */
    List<RecruitJob> page(PagingParam<RecruitJobSearchCondition> pagingParam);

    List<JobInfoDTO> pagedByCondition(PagingParam<RecruitJobSearchCondition> pagingParam);

    /**
     * 按照距离升序进行分页查询
     * @param positionPagingParam 分页参数
     * @return 结果
     */
    List<JobInfoDTO> pagedByDistance(String jobName, Integer jobSalaryMin, Integer jobSalaryMax,String candidateOpenid, PagingParam<Location> positionPagingParam);

    /**
     * 根据公司id找发布职位
     *
     * @param companyUuid
     * @return java.util.List<com.shopping.wx.model.RecruitJob>
     */
    List<RecruitJob> listByCompanyUuid(String companyUuid);

    /**
     * 浏览量+1
     *
     * @param id
     * @return int
     */
    Boolean increaseCountView(String id);

    Boolean increaseCountApply(String id);


}
