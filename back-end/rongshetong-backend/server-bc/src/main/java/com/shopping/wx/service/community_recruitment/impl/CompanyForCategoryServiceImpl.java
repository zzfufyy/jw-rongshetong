package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
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
public class CompanyForCategoryServiceImpl extends CrudServiceImpl<CompanyForCategory> implements CompanyForCategoryService {
    @Override
    public List<String> selectCategoryNameListByCompanyUuid(String companyUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CompanyForCategory>()
                        .notEmptyEq(companyUuid, CompanyForCategory::getCompanyUuid)
                        .getSqls()
        );
        List<CompanyForCategory> tempList = selectAllByExample(builder.build());
        List<String> rsList = new ArrayList<>();
        tempList.forEach(r -> rsList.add(r.getCategoryName()));
        return rsList;
    }
}
