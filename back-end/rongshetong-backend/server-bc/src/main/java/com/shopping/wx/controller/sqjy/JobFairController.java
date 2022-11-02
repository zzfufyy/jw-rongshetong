package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.model.FairForCompany;
import com.shopping.wx.model.JobFair;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_fair.JobFairSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobFairService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/job-fair")
public class JobFairController extends CrudController<JobFair, String> {
    @Autowired
    private JobFairService jobFairService;
    @Autowired
    private DB db;

    @Override
    protected CrudService<JobFair> getService() {
        return jobFairService;
    }

    @PostMapping("/page")
    public ActionResult<PageInfo<JobFair>> page(@RequestBody PagingParam<JobFairSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(jobFairService.page(pagingParam))
        );
    }
    @GetMapping("/info")
    public ActionResult<JobFair> info(@RequestParam String id){
        return ActionResult.ok(jobFairService.selectById(id));
    }

    @RequestMapping("/fairById")
    public JSON fairById(String id, int pagess) {
        int row = 10;
        Page page = new Page();
        page.setPage(pagess);
        page.setRows(row);
        JobFair jobFair = db.selectById(id, JobFair.class);
        Example example = new Example(FairForCandidate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fairUuid", id);
        List<FairForCandidate> fairForCandidates = db.selectPageByExample(FairForCandidate.class, example, page);

        Example example1 = new Example(FairForCompany.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("fairUuid", id);
        List<FairForCompany> fairForCompanies = db.selectPageByExample(FairForCompany.class, example1, page);
        JSON json = new JSON();
        Map map = new HashMap();
        map.put("jobFair", jobFair);
        map.put("fairForCandidates", fairForCandidates);
        map.put("fairForCompanies", fairForCompanies);
        json.setObj(map);
        return json;
    }


}
