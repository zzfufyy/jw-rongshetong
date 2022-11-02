package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.model.UserCommunity;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_community.UserCommunitySerachCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserCommunityService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区管理人员用户服务实现类
 *
 * @author ljy
 * @date 2022-03-11 16:46
 */

@Service
public class UserCommunityServiceImpl extends CrudServiceImpl<UserCommunity> implements UserCommunityService {

    // TODO: 实现具体的分页逻辑
    @Override
    public List<UserCommunity> page(PagingParam<UserCommunitySerachCondition> pagingParam) {
        return null;
    }

    @Override
    public List<UserCommunity> loadRecorderListByCommunityUuid(String communityUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<UserCommunity>()
                        .notEmptyEq(communityUuid, UserCommunity::getCommunityUuid)
                        .getSqls()
        );
        List<Integer> accessList = new ArrayList<>();
        accessList.add(AuditConstant.UserCommunityLevel.COMMUNITY_ADMIN.getValue());
        accessList.add(AuditConstant.UserCommunityLevel.COMMUNITY_RECORDER.getValue());
        builder.andWhere(
                Sqls.custom()
                        .andIn(
                                QueryUtils.getFieldName(UserCommunity::getAuthorizationLevel),
                                accessList
                        )
        );
        builder.orderByAsc(QueryUtils.getFieldName(UserCommunity::getRealName));
        return selectAllByExample(builder.build());
    }

}
