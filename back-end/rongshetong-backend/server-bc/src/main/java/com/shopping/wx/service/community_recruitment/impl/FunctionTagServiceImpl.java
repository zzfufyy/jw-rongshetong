package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.FunctionTagMapper;
import com.shopping.wx.model.FunctionTag;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FunctionTagService;
import com.shopping.wx.util.UrlUtil;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @ClassName FunctionTagServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/26
 **/
@Service
public class FunctionTagServiceImpl extends CrudServiceImpl<FunctionTag> implements FunctionTagService {
    @Autowired
    private FunctionTagMapper functionTagMapper;

    @Override
    public List<FunctionTag> listByDefaultOrder(String pagePathApply, String communityUuid) {
        pagePathApply = UrlUtil.appendHeadSlash(pagePathApply);
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(FunctionTag::getPagePathApply), pagePathApply)
                        .andEqualTo(QueryUtils.getFieldName(FunctionTag::getIsCustom), 0)
        );
        builder.orWhere(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(FunctionTag::getPagePathApply), pagePathApply)
                        .andEqualTo(QueryUtils.getFieldName(FunctionTag::getIsCustom), 1)
                        .andLike(QueryUtils.getFieldName(FunctionTag::getEffectCommunityUuid), "%" + communityUuid + "%")
        );
        builder.orderByDesc(QueryUtils.getFieldName(FunctionTag::getPriority));

        return functionTagMapper.selectByExample(builder.build());
    }

    @Override
    public List<FunctionTag> listAll() {
        return functionTagMapper.selectAll();
    }

}
