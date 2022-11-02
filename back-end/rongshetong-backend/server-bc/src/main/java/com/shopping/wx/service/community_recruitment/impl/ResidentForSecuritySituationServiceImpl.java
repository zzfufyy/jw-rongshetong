package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.ResidentForSecuritySituationMapper;
import com.shopping.wx.model.ResidentForHouseUsage;
import com.shopping.wx.model.ResidentForSecuritySituation;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.ResidentForSecuritySituationService;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName ResidentForSecuritySituationServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@Service
public class ResidentForSecuritySituationServiceImpl extends CrudServiceImpl<ResidentForSecuritySituation> implements ResidentForSecuritySituationService {
    @Autowired
    private ResidentForSecuritySituationMapper residentForSecuritySituationMapper;

    @Override
    public List<ResidentForSecuritySituation> listByResidentUuid(String residentUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<ResidentForSecuritySituation>()
                        .notEmptyEq(residentUuid, ResidentForSecuritySituation::getResidentUuid)
                        .getSqls()
        );
        return residentForSecuritySituationMapper.selectByExample(builder.build());
    }
}
