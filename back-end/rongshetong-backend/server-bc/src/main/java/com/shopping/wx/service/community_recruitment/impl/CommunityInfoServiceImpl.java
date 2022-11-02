package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.mapper.CommunityInformationMapper;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CommunityInfoService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.util.StringUtil;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 社区信息服务
 *
 * @author ljy
 * @date 2022-03-14 9:13
 */
@Service
public class CommunityInfoServiceImpl extends CrudServiceImpl<CommunityInformation> implements CommunityInfoService {
    @Autowired
    CommunityInformationMapper communityInformationMapper;

    @Override
    public List<CommunityInformation> page(PagingParam<CommunityInfoSearchCondition> pagingParam) {
        CommunityInfoSearchCondition condition = pagingParam.getCondition();

        // TODO: 实现社区信息分页
        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            if (condition != null) {
                builder.where(new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .notEmptyLike(condition.getName(), CommunityInformation::getCommunityName)
                        .getSqls());

                builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getCreateTime));
            } else {
                builder.where(new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .getSqls());
                builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getCreateTime));
            }
        });
    }

    @Override
    public List<CommunityInformation> list(PagingParam<CommunityInfoSearchCondition> pagingParam) {
        CommunityInfoSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getPriority));
        return selectAllByExample(builder.build());
    }

    @Override
    public List<String> listDistrictNameByCityName(String cityName) {

        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .getSqls()
        );
        if (StringUtil.isNotEmpty(cityName)) {
            builder.andWhere(
                    Sqls.custom().andEqualTo(
                            QueryUtils.getFieldName(CommunityInformation::getCityName), cityName
                    )
            );
        }
        builder.selectDistinct(QueryUtils.getFieldName(CommunityInformation::getDistrictName));
        builder.orderByAsc(QueryUtils.getFieldName(CommunityInformation::getDistrictName));
        List<CommunityInformation> rsList = communityInformationMapper.selectByExample(builder.build());
        return rsList.stream()
                .map(v -> v.getDistrictName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listStreetNameByDistrictName(String districtName) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityInformation>()
                        .notEmptyEq(districtName, CommunityInformation::getDistrictName)
                        .notDeleted()
                        .getSqls()
        );
        builder.selectDistinct(
                QueryUtils.getFieldName(CommunityInformation::getStreetName)
        );
        List<CommunityInformation> rsList = communityInformationMapper.selectByExample(builder.build());
        return rsList.stream()
                .map(v -> v.getStreetName())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommunityInformation> listByStreetName(String streetName) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(Sqls.custom()
                .andEqualTo(QueryUtils.getFieldName(CommunityInformation::getStreetName), streetName)
                .andEqualTo(QueryUtils.getFieldName(CommunityInformation::getCommunityType), AuditConstant.CommunityType.Community.getValue())
                .andEqualTo(QueryUtils.getFieldName(CommunityInformation::getStatus), AuditConstant.RecordStatus.ACTIVE.value())
        );
        builder.orderByAsc(QueryUtils.getFieldName(CommunityInformation::getCommunityName));
        return communityInformationMapper.selectByExample(builder.build());
    }
}
