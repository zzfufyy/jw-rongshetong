package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.CandidateForCategory;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CandidateForCategoryService;
import com.shopping.wx.service.community_recruitment.CompanyForCategoryService;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CompanyForCategoryServiceImpl
 * @Description 公司意向表实现类
 * @Author zyw
 * @Date 2022/3/31
 **/
@Service
public class CandidateForCategoryServiceImpl extends CrudServiceImpl<CandidateForCategory> implements CandidateForCategoryService {

    @Override
    public List<String> selectCategoryNameListByCandidateOpenid(String candidateOpenid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CandidateForCategory>()
                        // 根据openid获取列表
                        .notEmptyEq(candidateOpenid, CandidateForCategory::getCandidateOpenid)
                        .getSqls()
        );
        List<CandidateForCategory> tempList = selectAllByExample(builder.build());
        List<String> rsList = new ArrayList<>();
        tempList.forEach(r -> rsList.add(r.getCategoryName()));
        return rsList;
    }

    @Override
    public List<CandidateForCategory> listByCandidateOpenid(String candidateOpenid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CandidateForCategory>()
                        // 根据openid获取列表
                        .notEmptyEq(candidateOpenid, CandidateForCategory::getCandidateOpenid)
                        .getSqls()
        );
        return  selectAllByExample(builder.build());
    }


}
