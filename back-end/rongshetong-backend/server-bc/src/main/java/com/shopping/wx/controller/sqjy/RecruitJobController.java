package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobCategoryService;
import com.shopping.wx.service.community_recruitment.RecruitJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在招职位控制器
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@RestController()
@RequestMapping("/recruit-job")
public class RecruitJobController extends CrudController<RecruitJob, String> {

    @Autowired
    private RecruitJobService recruitJobService;
    @Autowired
    private JobCategoryService jobCategoryService;

    @PostMapping("/page")
    ActionResult<PageInfo<RecruitJob>> page(
            @RequestBody PagingParam<RecruitJobSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(recruitJobService.page(pagingParam))
        );
    }

    @PostMapping("/pagedByCondition")
    ActionResult<PageInfo<JobInfoDTO>> pagedByCondition(@RequestBody PagingParam<RecruitJobSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(recruitJobService.pagedByCondition(pagingParam))
        );
    }


    @PostMapping("/paged-by-distance")
    ActionResult<PageInfo<JobInfoDTO>> pagedByDistance(
            // 薪资比较参数
            @RequestParam(required = false) String jobName,
            @RequestParam(required = false) Integer jobSalaryMin,
            @RequestParam(required = false) Integer jobSalaryMax,
            // 首页传参  判断是否已投递简历
            @RequestParam(required = false) String candidateOpenid,
            @RequestBody PagingParam<Location> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitJobService.pagedByDistance(jobName,jobSalaryMin, jobSalaryMax,candidateOpenid, pagingParam))
        );
    }

    @GetMapping("/listByCompanyUuid")
    ActionResult<List<RecruitJob>> listByCompanyUuid(@RequestParam String companyUuid) {
        return ActionResult.ok(recruitJobService.listByCompanyUuid(companyUuid));
    }

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody RecruitJob recruitJob) {
        insert(recruitJob);
        // 如果在 job_category表中 没有 则添加记录
        if (jobCategoryService.selectCountByCategoryName(recruitJob.getJobName()) == 0) {
            JobCategory jobCategory = new JobCategory();
            jobCategory.setCategoryName(recruitJob.getJobName());
            jobCategoryService.insert(jobCategory);
        }
        return ActionResult.ok();
    }


    @GetMapping("/info")
    ActionResult<RecruitJob> info(@RequestParam("id") String recruitRecordId) {
        RecruitJob recruitJob = query(recruitRecordId);
        return ActionResult.ok(recruitJob);
    }

    @PostMapping("/modify")
    ActionResult<?> modify(@RequestBody RecruitJob recruitJob) {
        update(recruitJob);
        return ActionResult.ok();
    }

    @RequestMapping("/increaseCountView")
    public ActionResult<Boolean> increaseCountView(@RequestParam String id) {
        return recruitJobService.increaseCountView(id) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/increaseCountApply")
    public ActionResult<Boolean> increaseCountApply(@RequestParam String id) {
        return recruitJobService.increaseCountApply(id) ? ActionResult.ok() : ActionResult.error();
    }

    @GetMapping("/countByCompanyUuid")
    public ActionResult<Long> countByCompanyUuid(@RequestParam String companyUuid){
        RecruitJob recruitJob = new RecruitJob();
        recruitJob.setCompanyUuid(companyUuid);
        return ActionResult.ok(recruitJobService.selectCount(recruitJob));

    }
    @Override
    protected CrudService<RecruitJob> getService() {
        return recruitJobService;
    }
}
