package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.CandidateForCommunity;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CandidateForCommunityService;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName CandidateForCommunityServiceImpl
 * @Description 社区求职意向 接口实现类
 * @Author zyw
 * @Date 2022/4/2
 **/
@Service
public class CandidateForCommunityServiceImpl extends CrudServiceImpl<CandidateForCommunity> implements CandidateForCommunityService {



    @Override
    public List<CandidateForCommunity> listByCandidateOpenid(String candidateOpenid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CandidateForCommunity>()
                        // 根据openid获取列表
                        .notEmptyEq(candidateOpenid, CandidateForCommunity::getCandidateOpenid)
                        .getSqls()
        );
        return  selectAllByExample(builder.build());
    }

    @Override
    public List<String> selectCommunityNameListByCandidateOpenid(String candidateOpenid) {
        return null;
    }
}
