package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.pojo.dto.fair_for_candidate.FairForCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_candidate.FairForCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FairForCandidateService
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
public interface FairForCandidateService extends CrudService<FairForCandidate> {
    List<FairForCandidateDTO> pageDTO(PagingParam<FairForCandidateSearchCondition> pagingParam);
}
