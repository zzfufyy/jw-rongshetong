package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.base.foundation.data.expression.In;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.mapper.JobExperienceMapper;
import com.shopping.wx.model.JobExperience;
import com.shopping.wx.pojo.vo.job_experience.JobExperienceSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.JobExperienceService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName JobExperienceServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/1
 **/
@Service
public class JobExperienceServiceImpl extends CrudServiceImpl<JobExperience> implements JobExperienceService {
    @Autowired
    JobExperienceMapper jobExperienceMapper;

    @Override
    public List<JobExperience> listByCondition(JobExperienceSearchCondition jobExperienceSearchCondition) {
        Example.Builder builder = new Example.Builder(getEntityClass());

        builder.where(Sqls.custom()
                .andEqualTo(QueryUtils.getFieldName(JobExperience::getCandidateOpenid), jobExperienceSearchCondition.getCandidateOpenid())
        );
        Optional.ofNullable(jobExperienceSearchCondition.getJobType()).ifPresent(v ->
                builder.where(Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(JobExperience::getJobType), v)
                )
        );

        return jobExperienceMapper.selectByExample(builder.build());
    }
}
