package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.managed_mapper.community_recruitment.IRecruitJobMapper;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitJobService;
import com.shopping.wx.util.SalaryCompareUtil;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ljy
 */
@Service
public class RecruitJobServiceImpl extends CrudServiceImpl<RecruitJob> implements RecruitJobService {

    @Autowired
    IRecruitJobMapper iRecruitJobMapper;


    @Override
    public List<RecruitJob> page(PagingParam<RecruitJobSearchCondition> pagingParam) {
        RecruitJobSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new RecruitJobSearchCondition());
        startPage(pagingParam.getPage());

        return pagingByWhereClauseBuilder(pagingParam.getPage(), builder ->
                builder
                        .notEmptyEq(condition.getCompanyUuid(), RecruitJob::getCompanyUuid)
        );
    }

    @Override
    public List<JobInfoDTO> pagedByCondition(PagingParam<RecruitJobSearchCondition> pagingParam) {
        RecruitJobSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new RecruitJobSearchCondition());
        startPage(pagingParam.getPage());

        SalaryCompareUtil salaryCompareUtil = new SalaryCompareUtil(condition.getJobSalaryMin(), condition.getJobSalaryMax());
        Integer salaryCompareState = salaryCompareUtil.getCompareMode();


        return iRecruitJobMapper.pagedByDistance(
                condition.getCommunityUuid(),
                Optional.ofNullable(condition.getOrderType()).orElseGet(() -> AuditConstant.OrderType.Distance.getValue()),
                condition.getJobName(),
                salaryCompareState,
                condition.getJobSalaryMin(),
                condition.getJobSalaryMax(),
                condition.getCandidateOpenid(),
                new Location(condition.getLongitude(), condition.getLatitude())
        );
    }

    /**
     * 按照距离升序进行分页查询
     *
     * @param positionPagingParam 分页参数
     * @return 结果
     */
    @Override
    public List<JobInfoDTO> pagedByDistance(String jobName, Integer jobSalaryMin, Integer jobSalaryMax, String candidateOpenid, PagingParam<Location> positionPagingParam) {
        startPage(positionPagingParam.getPage());
        // 构建薪资比较模式
        SalaryCompareUtil salaryCompareUtil = new SalaryCompareUtil(jobSalaryMin, jobSalaryMax);
        Integer salaryCompareState = (salaryCompareUtil.getCompareMode() == null) ? -1 : salaryCompareUtil.getCompareMode();
        return iRecruitJobMapper.pagedByDistance(
                null,
                AuditConstant.OrderType.Distance.getValue(),
                jobName,
                salaryCompareState,
                jobSalaryMin,
                jobSalaryMax,
                candidateOpenid,
                positionPagingParam.getCondition());
    }

    @Override
    public List<RecruitJob> listByCompanyUuid(String companyUuid) {
        if(StringUtil.isEmpty(companyUuid)){
            return new ArrayList<>();
        }
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<RecruitJob>()
                        .notEmptyEq(companyUuid, RecruitJob::getCompanyUuid)
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(RecruitJob::getCreateTime));
        return selectAllByExample(builder.build());
    }

    @Override
    public Boolean increaseCountView(String id) {

        return iRecruitJobMapper.increaseCountView(id) == 1 ? true : false;

    }

    @Override
    public Boolean increaseCountApply(String id) {
        return iRecruitJobMapper.increaseCountApply(id) == 1 ? true : false;
    }


}
