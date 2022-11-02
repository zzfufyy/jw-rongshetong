package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.managed_mapper.community_recruitment.IInformationNewsMapper;
import com.shopping.wx.mapper.InformationNewsMapper;
import com.shopping.wx.model.InformationNews;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.InfoNewsService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author ljy
 */
@Service
public class InfoNewsServiceImpl extends CrudServiceImpl<InformationNews> implements InfoNewsService {
    @Autowired
    InformationNewsMapper informationNewsMapper;
    @Autowired
    IInformationNewsMapper iInformationNewsMapper;

    @Override
    public List<InformationNews> page(PagingParam<InfoNewsSearchCondition> pagingParam) {
        InfoNewsSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new InfoNewsSearchCondition());

        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            // Where 子语句
            builder.andWhere(
                    new WhereClauseBuilder<InformationNews>()
                            // 未删除
                            .notDeleted()
                            // 如果标题不为空，模糊查询标题
                            .notEmptyLike(condition.getArticleTitle(), InformationNews::getArticleTitle)
                            // 提供了社区 id，设置相等条件
//                     范围查询时间
//                    .dateRange(condition, InformationNews::getArticleReleaseTime)
                            .getSqls()
            );
            // 默认是 community
            Integer tempCommunityType = Optional.ofNullable(condition.getCommunityType())
                    .orElse(AuditConstant.CommunityType.Community.getValue());
            String tempCommunityUuid = Optional.ofNullable(condition.getCommunityUuid()).orElse("");
            // 仅为社区类型时 放宽communityUuid 匹配条件
            if (tempCommunityType == AuditConstant.CommunityType.Community.getValue()) {
                builder.andWhere(
                        Sqls.custom()
                                .andEqualTo(QueryUtils.getFieldName(InformationNews::getCommunityUuid), tempCommunityUuid)
                                .orEqualTo(QueryUtils.getFieldName(InformationNews::getCommunityUuid), "")
                );
            } else if (tempCommunityType == AuditConstant.CommunityType.School.getValue()) {
                builder.andWhere(
                        Sqls.custom()
                                .andEqualTo(QueryUtils.getFieldName(InformationNews::getCommunityUuid), tempCommunityUuid)
                );
            }
            // 文章类型
            Integer tempArticleType = Optional.ofNullable(condition.getArticleType())
                    .orElse(AuditConstant.InformationNewsArticleType.Normal.getValue());
            builder.andWhere(
                    Sqls.custom()
                            .andEqualTo(QueryUtils.getFieldName(InformationNews::getArticleType), tempArticleType)
            );
            // 根据 发布时间倒序
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getPriority));
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        });
    }

    @Override
    public List<InformationNews> pageByArticleType(PagingParam<InfoNewsSearchCondition> pagingParam) {
        InfoNewsSearchCondition condition = pagingParam.getCondition();
        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            builder.andWhere(
                    Sqls.custom().
                            andEqualTo(QueryUtils.getFieldName(InformationNews::getArticleType), condition.getArticleType())
            );
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getPriority));
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        });
    }

    @Override
    public List<InformationNews> pageAllSocial(PagingParam<InfoNewsSearchCondition> pagingParam) {
        InfoNewsSearchCondition condition = pagingParam.getCondition();
        ArrayList<Integer> values = new ArrayList<>(10);
        for (int i = 21; i <= 30; i++) {
            values.add(i);
        }
        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            builder.andWhere(
                    Sqls.custom().andIn(QueryUtils.getFieldName(InformationNews::getArticleType), values)

            );
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getPriority));
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        });
    }

    @Override
    public List<InformationNews> list() {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<InformationNews>()
                        .notDeleted()
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        return selectAllByExample(builder.build());
    }

    @Override
    public List<InformationNews> listByCommunityUuid(String communityUuid, Integer num) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<InformationNews>()
                        .notEmptyEq(communityUuid, InformationNews::getCommunityUuid)
                        .notDeleted()
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        return (num == null) ?
                selectAllByExample(builder.build()) :
                informationNewsMapper.selectByExampleAndRowBounds(builder.build(), new RowBounds(0, num));

    }

    @Override
    public List<InformationNews> listByPagePath(String communityUuid, String pagePathApply) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(InformationNews::getCommunityUuid), communityUuid)
                        .andEqualTo(QueryUtils.getFieldName(InformationNews::getPagePathApply), pagePathApply)
        );
        builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getPriority));
        return informationNewsMapper.selectByExample(builder.build());
    }

    @Override
    public int increaseCountView(String id) {
        return iInformationNewsMapper.increaseCountView(id);
    }

//
//    @Test
//    public void test(){
//        class User{
//            private String address;
//            public User() {
//            }
//            public User(String address) {
//                this.address = address;
//            }
//
//            public Optional<String> getAddress(){
//                return Optional.ofNullable(address);
//            }
//        }
//        User user = new User();
//        Optional<User> opt = Optional.ofNullable(user);
//
//    }
}
