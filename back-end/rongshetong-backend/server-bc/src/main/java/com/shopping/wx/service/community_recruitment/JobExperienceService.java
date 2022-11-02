package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.JobExperience;
import com.shopping.wx.pojo.vo.job_experience.JobExperienceSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName JobExperienceService
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/1
 **/
public interface JobExperienceService extends CrudService<JobExperience> {

    List<JobExperience> listByCondition(JobExperienceSearchCondition jobExperienceSearchCondition);
}
