package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IFairForCandidateMapper;
import com.shopping.wx.mapper.FairForCandidateMapper;
import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.pojo.dto.fair_for_candidate.FairForCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_candidate.FairForCandidateSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FairForCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName FairForCandidateServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@Service
public class FairForCandidateServiceImpl extends CrudServiceImpl<FairForCandidate> implements FairForCandidateService {
    @Autowired
    private FairForCandidateMapper fairForCandidateMapper;
    @Autowired
    private IFairForCandidateMapper iFairForCandidateMapper;

    @Override
    public List<FairForCandidateDTO> pageDTO(PagingParam<FairForCandidateSearchCondition> pagingParam) {
        FairForCandidateSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new FairForCandidateSearchCondition());
        startPage(pagingParam.getPage());
        return iFairForCandidateMapper.pageDTO(condition.getFairUuid());
    }
}
