package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IResidentForHouseUsageMapper;
import com.shopping.wx.mapper.ResidentForHouseUsageMapper;
import com.shopping.wx.model.ResidentForHouseUsage;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.ResidentForHouseUsageService;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName ResidentForHouseUsageServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@Service
public class ResidentForHouseUsageServiceImpl extends CrudServiceImpl<ResidentForHouseUsage> implements ResidentForHouseUsageService {

    @Autowired
    private ResidentForHouseUsageMapper residentForHouseUsageMapper;
    @Autowired
    private IResidentForHouseUsageMapper iResidentForHouseUsageMapper;
    @Override
    public List<ResidentForHouseUsage> listByResidentUuid(String residentUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<ResidentForHouseUsage>()
                        .notEmptyEq(residentUuid, ResidentForHouseUsage::getResidentUuid)
                        .getSqls()
        );
        return residentForHouseUsageMapper.selectByExample(builder.build());
    }

}
