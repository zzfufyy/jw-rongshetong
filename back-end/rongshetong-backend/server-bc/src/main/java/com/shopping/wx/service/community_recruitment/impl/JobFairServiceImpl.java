package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.base.utils.StringUtils;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.mapper.JobFairMapper;
import com.shopping.wx.model.JobFair;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_fair.JobFairSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.JobFairService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName JobFairServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@Service
public class JobFairServiceImpl extends CrudServiceImpl<JobFair> implements JobFairService {
    @Autowired
    private JobFairMapper jobFairMapper;

    @Override
    public List<JobFair> page(PagingParam<JobFairSearchCondition> pagingParam) {
        JobFairSearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new JobFairSearchCondition());
        // 开始分页
        startPage(pagingParam.getPage());
        Example.Builder builder = new Example.Builder(getEntityClass());

        builder.where(
                Sqls.custom()
                        .andNotEqualTo(QueryUtils.getFieldName(JobFair::getStatus), AuditConstant.RecordStatus.DELETED.value())
        );
        // 模糊查询 title
        if(StringUtil.isNotEmpty(condition.getFairTitle())){
            builder.andWhere(Sqls.custom().andLike(QueryUtils.getFieldName(JobFair::getFairTitle), QueryUtils.fuzzyQuery(condition.getFairTitle())));
        }
        builder.orderByDesc(QueryUtils.getFieldName(JobFair::getCreateTime));
        return jobFairMapper.selectByExample(builder.build());
    }
}
