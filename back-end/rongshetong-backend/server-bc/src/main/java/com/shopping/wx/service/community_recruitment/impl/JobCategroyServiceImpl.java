package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.conf.Page;
import com.shopping.wx.managed_mapper.community_recruitment.IJobCategoryMapper;
import com.shopping.wx.mapper.JobCategoryMapper;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.pojo.vo.job_category.JobCategorySearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.JobCategoryService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.apache.ibatis.session.RowBounds;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @ClassName JobCategroyServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/14
 **/
@Service
public class JobCategroyServiceImpl extends CrudServiceImpl<JobCategory> implements JobCategoryService {
    @Autowired
    JobCategoryMapper jobCategoryMapper;

    @Autowired
    IJobCategoryMapper iJobCategoryMapper;

    @Override
    public List<JobCategory> page(PagingParam<JobCategorySearchCondition> pagingParam) {
        JobCategorySearchCondition condition = pagingParam.getCondition();
        return pagingByBuilder(pagingParam.getPage(),
                builder -> {
                    if (condition == null) {
                        return;
                    }
                    builder.andWhere(
                            new WhereClauseBuilder<JobCategory>()
                                    .notDeleted()
                                    // 标签名称 模糊
                                    .notEmptyLike(condition.getCategoryName(), JobCategory::getCategoryName)
                                    // 关键词 模糊
                                    .notEmptyLike(condition.getKeyword(), JobCategory::getKeyword)
                                    .getSqls()
                    );
                    // 默认浏览次数排序
                    builder.orderByDesc(QueryUtils.getFieldName(JobCategory::getCountView));
                }
        );
    }

    @Override
    public Boolean incrCountView(String id) {
        return iJobCategoryMapper.incrCountView(id) == 1 ? true : false;
    }

    @Override
    public List<JobCategory> list(String categoryName) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<JobCategory>()
                        .notDeleted()
                        .getSqls()
        );

        // 模糊查询 分类名称
        if (!StringUtil.isEmpty(categoryName)) {
            builder.andWhere(Sqls.custom().andLike("categoryName", "%" + categoryName + "%"));
        }
        builder.orderByDesc(QueryUtils.getFieldName(JobCategory::getPriority));
        builder.orderByDesc(QueryUtils.getFieldName(JobCategory::getCountView));
        Page page = new Page(1, 13);
        return selectPageByExample(builder.build(), page);
    }

    @Override
    public List<JobCategory> listByNumber(Integer number, String categoryName) {
        Example.Builder builder = new Example.Builder(getEntityClass());

        builder.where(
                new WhereClauseBuilder<JobCategory>()
                        .notDeleted()
                        .getSqls());
        builder.andWhere(
                Sqls.custom().
                        andLike(QueryUtils.getFieldName(JobCategory::getCategoryName), QueryUtils.fuzzyQuery(categoryName))
        );

        return jobCategoryMapper.selectByExampleAndRowBounds(builder.build(), new RowBounds(0, number));
    }

    @Override
    public long selectCountByCategoryName(String categoryName) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(new WhereClauseBuilder<JobCategory>()
                .notEmptyEq(categoryName, JobCategory::getCategoryName)
                .getSqls()
        );
        return selectCountByExample(builder.build());
    }

}
