package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.CommunityBuildingMapper;
import com.shopping.wx.model.CommunityBuilding;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_building.CommunityBuildingSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CommunityBuildingService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.util.StringUtil;

import javax.management.Query;
import java.util.List;

/**
 * @ClassName CommunityBuildingServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/14
 **/
@Service
public class CommunityBuildingServiceImpl extends CrudServiceImpl<CommunityBuilding> implements CommunityBuildingService {
    @Autowired
    private CommunityBuildingMapper communityBuildingMapper;

    @Override
    public List<CommunityBuilding> page(PagingParam<CommunityBuildingSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        CommunityBuildingSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        if(!StringUtils.isEmpty(condition.getCommunityUuid())){
            builder.where(
                    Sqls.custom()
                            .andEqualTo(QueryUtils.getFieldName(CommunityBuilding::getCommunityUuid), condition.getCommunityUuid())
            );
        }
        builder.orderByDesc(QueryUtils.getFieldName(CommunityBuilding::getCreateTime));
        return communityBuildingMapper.selectByExample(builder.build());
    }

    @Override
    public List<CommunityBuilding> pageStreet(PagingParam<CommunityBuildingSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        CommunityBuildingSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        if(!(StringUtils.isEmpty(condition.getDistrictName()) || StringUtils.isEmpty(condition.getStreetName()))){
            builder.where(
                    Sqls.custom()
                            .andEqualTo(QueryUtils.getFieldName(CommunityBuilding::getDistrictName), condition.getDistrictName())
                            .andEqualTo(QueryUtils.getFieldName(CommunityBuilding::getStreetName), condition.getStreetName())
            );
        }
        builder.orderByDesc(QueryUtils.getFieldName(CommunityBuilding::getCreateTime));
        return communityBuildingMapper.selectByExample(builder.build());
    }
}
