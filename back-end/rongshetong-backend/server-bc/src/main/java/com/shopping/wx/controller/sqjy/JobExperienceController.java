package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BannerForPage;
import com.shopping.wx.model.JobExperience;
import com.shopping.wx.pojo.vo.job_experience.JobExperienceSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobExperienceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/job-experience")
public class JobExperienceController extends CrudController<JobExperience, String> {
    @Autowired
    private JobExperienceService jobExperienceService;

    @GetMapping("/info")
    public ActionResult<JobExperience> info(@RequestParam("id") String id) {
        return ActionResult.ok(jobExperienceService.selectById(id));
    }

    @PostMapping("/add")
    public ActionResult<?> add(@RequestBody JobExperience jobExperience) {
        return ActionResult.ok(jobExperienceService.insert(jobExperience));
    }

    @PostMapping("/modify")
    public ActionResult<?> modify(@RequestBody JobExperience jobExperience) {
        return ActionResult.ok(jobExperienceService.update(jobExperience));
    }

    @PostMapping("/listByCondition")
    public ActionResult<List<JobExperience>> listByCondition(@RequestBody JobExperienceSearchCondition jobExperienceSearchCondition) {
        return ActionResult.ok(jobExperienceService.listByCondition(jobExperienceSearchCondition));
    }

    @GetMapping("/deleteById")
    public ActionResult<?> deleteById(@RequestParam("id") String id) {
        JobExperience jobExperience = new JobExperience();
        jobExperience.setId(id);
        return ActionResult.ok(jobExperienceService.delete(jobExperience));
    }

    @Override
    protected CrudService<JobExperience> getService() {
        return jobExperienceService;
    }
}
