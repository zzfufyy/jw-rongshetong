package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.DimSecuritySituation;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.DimSecuritySituationService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName DimSecuritySituationServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@Service
public class DimSecuritySituationServiceImpl extends CrudServiceImpl<DimSecuritySituation> implements DimSecuritySituationService {
    @Override
    public List<DimSecuritySituation> list() {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.orderByDesc(QueryUtils.getFieldName(DimSecuritySituation::getPriority));
        return selectAllByExample(builder.build());
    }
}
