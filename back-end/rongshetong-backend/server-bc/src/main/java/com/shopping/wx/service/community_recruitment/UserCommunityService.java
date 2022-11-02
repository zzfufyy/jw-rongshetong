package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.UserCommunity;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_community.UserCommunitySerachCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * 社区管理人员用户服务
 *
 * @author ljy
 * @date 2022-03-11 16:45
 */
public interface UserCommunityService extends CrudService<UserCommunity> {
    /**
     * 分页搜索用户社区信息
     *
     * @param pagingParam 分页参数
     * @return 分页列表
     */
    List<UserCommunity> page(PagingParam<UserCommunitySerachCondition> pagingParam);


    List<UserCommunity> loadRecorderListByCommunityUuid(String communityUuid);
}
