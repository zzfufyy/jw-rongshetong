package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.DimHouseUsage;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.DimHouseUsageService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName DimHouseUsageServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@Service
public class DimHouseUsageServiceImpl extends CrudServiceImpl<DimHouseUsage> implements DimHouseUsageService {
    @Override
    public List<DimHouseUsage> list() {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.orderByDesc(QueryUtils.getFieldName(DimHouseUsage::getPriority));
        return selectAllByExample(builder.build());
    }
}
