package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.managed_mapper.community_recruitment.IUserCandidateMapper;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidatePlus;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import com.shopping.wx.util.SalaryCompareUtil;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 求职用户信息服务实现
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Service
public class UserCandidateServiceImpl extends CrudServiceImpl<UserCandidate> implements UserCandidateService {

    @Autowired
    private IUserCandidateMapper iUserCandidateMapper;


    @Override
    public List<UserCandidate> page(PagingParam<UserCandidateSearchCondition> pagingParam) {
        UserCandidateSearchCondition condition = pagingParam.getCondition();
        return pagingByWhereClauseBuilder(pagingParam.getPage(), builder -> builder
                // 未删除
                .notDeleted()
                // 所在社区
                .notNullEq(condition.getCommunityUuid(), UserCandidate::getCommunityUuid)
        );
    }

    @Override
    public List<UserCandidateDTO> pagedByCondition(PagingParam<UserCandidateSearchCondition> pagingParam) {
        UserCandidateSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new UserCandidateSearchCondition());
        startPage(pagingParam.getPage());
        SalaryCompareUtil salaryCompareUtil = new SalaryCompareUtil(condition.getJobSalaryMin(), condition.getJobSalaryMax());
        Integer salaryCompareState = salaryCompareUtil.getCompareMode();
        return iUserCandidateMapper.pagedByDistance(
                condition.getCommunityUuid(),
                Optional.ofNullable(condition.getOrderType()).orElseGet(() -> AuditConstant.OrderType.Distance.getValue()),
                condition.getCategoryName(),
                salaryCompareState,
                condition.getJobSalaryMin(),
                condition.getJobSalaryMax(),
                new Location(condition.getLongitude(), condition.getLatitude())
        );
    }

    @Override
    public void increaseViewCount(String openid) {
        iUserCandidateMapper.increaseViewCount(openid);
    }

    @Override
    public List<UserCandidateDTO> pagedByDistance(String categoyName, Integer jobSalaryMin, Integer jobSalaryMax, PagingParam<Location> positionPagingParam) {
        startPage(positionPagingParam.getPage());
        // 构建薪资比较模式
        SalaryCompareUtil salaryCompareUtil = new SalaryCompareUtil(jobSalaryMin, jobSalaryMax);
        Integer salaryCompareState = salaryCompareUtil.getCompareMode();
        return iUserCandidateMapper.pagedByDistance(null, AuditConstant.OrderType.Distance.getValue(), categoyName, salaryCompareState, jobSalaryMin, jobSalaryMax, positionPagingParam.getCondition());
    }

    @Override
    public UserCandidatePlus infoPlus(String id) {
        return iUserCandidateMapper.infoPlus(id);
    }


}
