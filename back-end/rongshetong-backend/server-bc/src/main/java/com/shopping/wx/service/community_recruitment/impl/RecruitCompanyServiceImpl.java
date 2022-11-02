package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.managed_mapper.community_recruitment.IRecruitCompanyMapper;
import com.shopping.wx.mapper.RecruitCompanyMapper;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyDTO;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyJobNameList;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyPlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName RecruitCompanyServiceImpl
 * @Description 公司相关接口实现
 * @Author zyw
 * @Date 2022/3/11
 **/
@Service
public class RecruitCompanyServiceImpl extends CrudServiceImpl<RecruitCompany> implements RecruitCompanyService {
    @Autowired
    IRecruitCompanyMapper iRecruitCompanyMapper;
    @Autowired
    RecruitCompanyMapper recruitCompanyMapper;

    @Override
    public List<RecruitCompany> page(PagingParam<RecruitCompanySearchCondition> pagingParam) {

        RecruitCompanySearchCondition condition = pagingParam.getCondition();
        return pagingByBuilder(pagingParam.getPage(),
                builder -> {
                    if (condition == null) {
                        return;
                    }
                    builder.andWhere(

                            new WhereClauseBuilder<RecruitCompany>()
                                    // 未删除
                                    .notDeleted()
                                    //  姓名 模糊
                                    .notEmptyLike(condition.getCompanyName(), RecruitCompany::getCompanyName)
                                    //  是否认证
                                    .notNullEq(condition.getFlagIdentification(), RecruitCompany::getFlagIdentification)

                                    .getSqls()
                    );

                    // 默认时间降序
                    builder.orderByDesc(QueryUtils.getFieldName(RecruitCompany::getCreateTime));


                }
        );
    }

    @Override
    public List<RecruitCompanyDTO> pagedByCondition(PagingParam<RecruitCompanySearchCondition> pagingParam) {
        RecruitCompanySearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new RecruitCompanySearchCondition());
        startPage(pagingParam.getPage());
        // 显示传条件 明确
        return iRecruitCompanyMapper.pagedByCondition(
                Optional.ofNullable(condition.getOrderType()).orElseGet(() -> AuditConstant.OrderType.Distance.getValue()),
                condition.getCommunityUuid(),
                condition.getCompanyName(),
                new Location(condition.getLongitude(), condition.getLatitude()));
    }

    @Override
    public List<RecruitCompanyJobNameList> pageJobNameList(PagingParam<RecruitCompanySearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        RecruitCompanySearchCondition condition = pagingParam.getCondition();
        String buildingUuid = Optional.ofNullable(condition.getBuildingUuid()).orElse("");

        String companyName = Optional.ofNullable(condition.getCompanyName()).orElse("");
        return iRecruitCompanyMapper.pageJobNameList(buildingUuid, companyName);
    }

    @Override
    public Boolean increaseCountView(String id) {
        return iRecruitCompanyMapper.increaseCountView(id) == 1 ? true : false;
    }

    @Override
    public Integer countByCommunityUuid(String communityUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<RecruitCompany>()
                        .notDeleted()
                        .getSqls());
        builder.andWhere(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(RecruitCompany::getCommunityUuid), communityUuid)
                        .andEqualTo(QueryUtils.getFieldName(RecruitCompany::getFlagIdentification), Integer.valueOf(1))
        );

        return recruitCompanyMapper.selectCountByExample(builder.build());
    }

    @Override
    public RecruitCompanyPlus infoWithAssociated(String id) {
        return iRecruitCompanyMapper.infoWithAssociated(id);
    }

}
