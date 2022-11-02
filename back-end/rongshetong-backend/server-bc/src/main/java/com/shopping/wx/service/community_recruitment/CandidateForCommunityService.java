package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CandidateForCommunity;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CandidateForComunnityService
 * @Description 求职者社区意向 接口
 * @Author zyw
 * @Date 2022/4/2
 **/
public interface CandidateForCommunityService extends CrudService<CandidateForCommunity> {
    /**
     * 社区意向 entity list表
     *
     * @param candidateOpenid
     * @return java.util.List<com.shopping.wx.model.CandidateForCategory>
     */
    List<CandidateForCommunity> listByCandidateOpenid(String candidateOpenid);

    List<String> selectCommunityNameListByCandidateOpenid(String candidateOpenid);


}
