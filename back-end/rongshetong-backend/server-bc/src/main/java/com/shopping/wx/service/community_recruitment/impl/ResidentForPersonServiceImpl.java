package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IResidentForPersonMapper;
import com.shopping.wx.mapper.ResidentForPersonMapper;
import com.shopping.wx.model.ResidentForPerson;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.ResidentForPersonService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @ClassName ResidentForPersonServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/27
 **/
@Service
public class ResidentForPersonServiceImpl extends CrudServiceImpl<ResidentForPerson> implements ResidentForPersonService {
    @Autowired
    private ResidentForPersonMapper residentForPersonMapper;
    @Autowired
    private IResidentForPersonMapper iResidentForPersonMapper;
    @Override
    public List<ResidentForPerson> listByResidentUuid(String residentUuid, Integer personType) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<ResidentForPerson>()
                        .notEmptyEq(residentUuid, ResidentForPerson::getResidentUuid)
                        .getSqls()
        );
        if (personType != null) {
            builder.andWhere(
                    Sqls.custom().andEqualTo(
                            QueryUtils.getFieldName(ResidentForPerson::getPersonType),
                            personType
                    ));
        }
        return residentForPersonMapper.selectByExample(builder.build());
    }

    @Override
    public int deleteByCellUuid(String cellUuid) {
        return iResidentForPersonMapper.deleteByCellUuid(cellUuid);
    }
}
