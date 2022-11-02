package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.UserForFunctionTagMapper;
import com.shopping.wx.model.UserForFunctionTag;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserForFunctionTagService;
import com.shopping.wx.util.UrlUtil;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

/**
 * @ClassName UserForFunctionTagServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/27
 **/
@Service
public class UserForFunctionTagServiceImpl extends CrudServiceImpl<UserForFunctionTag> implements UserForFunctionTagService {
    @Autowired
    private UserForFunctionTagMapper userForFunctionTagMapper;

    @Override
    public UserForFunctionTag infoByUserAndPath(String userOpenid, String pagePathApply) {
        pagePathApply = UrlUtil.appendHeadSlash(pagePathApply);
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                .andEqualTo(QueryUtils.getFieldName(UserForFunctionTag::getUserOpenid), userOpenid)
                .andEqualTo(QueryUtils.getFieldName(UserForFunctionTag::getPagePathApply), pagePathApply)
        );
        return userForFunctionTagMapper.selectOneByExample(builder.build());
    }
}
