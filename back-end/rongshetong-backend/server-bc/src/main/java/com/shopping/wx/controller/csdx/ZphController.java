package com.shopping.wx.controller.csdx;

import com.shopping.wx.entity.JSON;
import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.model.*;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyJobDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_company.FairForCompanySearchCondition;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.community_recruitment.FairForCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/zph")
public class ZphController {
    @Autowired
    private DB db;
    @Autowired
    private FairForCompanyService fairForCompanyService;
    //双选会列表
    @RequestMapping("/page")
    public JSON page(@RequestParam(name = "pages", defaultValue = "") int pages,  @RequestParam(name = "name", defaultValue = "")String name) throws ParseException {
        Page page = new Page();
        page.setRows(10);
        page.setPage(pages);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Example example = new Example(JobFair.class);
        Example.Criteria criteria =example.createCriteria();
        if(!name.equals("")){
            criteria.andLike("fairTitle","%"+name+"%");
        }
        criteria.andEqualTo("status",0);
        example.setOrderByClause("create_time desc ");
        List<JobFair> jobFairList =db.selectPageByExample(JobFair.class,example,page);
        List<JobFair> jobFairListlength =db.selectAllByExample(JobFair.class,example);
        for (int i = 0; i <jobFairList.size() ; i++) {
            jobFairList.get(i).setExt3(simpleDateFormat.format(jobFairList.get(i).getFairBeginTime()));
            jobFairList.get(i).setExt2(simpleDateFormat.format(jobFairList.get(i).getFairEndTime()));
            Example example1 = new Example(FairForCompany.class);
            Example.Criteria criteria1 =example1.createCriteria();
            criteria1.andEqualTo("fairUuid",jobFairList.get(i).getId());
            List<FairForCompany> fairForCompanies =db.selectAllByExample(FairForCompany.class,example1);
            jobFairList.get(i).setExt1(String.valueOf(fairForCompanies.size()));
        }
        JSON json= new JSON();
        json.setObj(jobFairList);
        json.setFlag(true);
        json.setMsg(String.valueOf(jobFairListlength.size()));
        return json;
    }
    //查询单个招聘双选会信息
    @RequestMapping("/sxhbyid")
    public JSON sxhbyid(@RequestParam(name = "id", defaultValue = "") String id) throws ParseException {
        Page page = new Page();
        page.setRows(10);
//        page.setPage(pages);
        JobFair jobFair =db.selectById(id,JobFair.class);
        jobFair.setCountView(jobFair.getCountView()+1);
        db.update(jobFair);
//        Example example = new Example(FairForCompany.class);
//        Example.Criteria criteria =example.createCriteria();
//        criteria.andEqualTo("fairUuid",id);
//        List<FairForCompany> fairForCompanyList =db.selectPageByExample(FairForCompany.class,example,page);
        JSON json = new JSON();
        json.setObj(jobFair);
        json.setFlag(true);
        json.setMsg("");
        return json;
    }
    @RequestMapping("/pageJobDTO")
    public ActionResult<PageInfo<FairForCompanyJobDTO>> pageJobDTO(@RequestParam(name = "id", defaultValue = "") String id,
                                                                   @RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "pages", defaultValue = "") int pages){
        Page page = new Page();
        page.setPage(pages);
        page.setRows(10);
        PagingParam<FairForCompanySearchCondition> pagingParam = new PagingParam();
        pagingParam.setPage(page);
        FairForCompanySearchCondition fairForCompanySearchCondition = new FairForCompanySearchCondition();
        fairForCompanySearchCondition.setFairUuid(id);
        fairForCompanySearchCondition.setCompanyName(name);
        pagingParam.setCondition(fairForCompanySearchCondition);
        return ActionResult.ok(
                PageInfo.of(fairForCompanyService.pageJobDTO(pagingParam))
        );
    }
    //在线招聘
    @RequestMapping("/onlineZp")
    public JSON onlineZp(@RequestParam(name = "pages", defaultValue = "") int pages,  @RequestParam(name = "name", defaultValue = "")String name,
                         @RequestParam(name = "zy", defaultValue = "")String zy,
                         @RequestParam(name = "gzdz", defaultValue = "")String gzdz) throws ParseException {
        Page page = new Page();
        page.setRows(10);
        page.setPage(pages);
        JSON json = new JSON();
        Example example = new Example(RecruitCompany.class);
        Example.Criteria criteria =example.createCriteria();
        if(!name.equals("")){
            criteria.andLike("companyName","%"+name+"%");
        }
        if(!zy.equals("")){
            criteria.andLike("introduction","%"+zy+"%");
        }
        if(!gzdz.equals("")){
            criteria.andLike("address","%"+gzdz+"%");
        }
        criteria.andLike("ext1","%长沙学院%");
        criteria.andEqualTo("status",0);
        List<RecruitCompany> recruitCompanyList =db.selectPageByExample(RecruitCompany.class,example,page);
//        for (int i = 0; i <recruitCompanyList.size() ; i++) {
//            Example example1 = new Example(RecruitJob.class);
//            Example.Criteria criteria1 =example1.createCriteria();
//            criteria1.andEqualTo("companyUuid",recruitCompanyList.get(i).getId());
//            criteria1.andEqualTo("status",0);
//            List<RecruitJob> recruitJobList =db.selectAllByExample(RecruitJob.class,example1);
//            String jobname ="";
//            for (int j = 0; j <recruitJobList.size() ; j++) {
//                jobname+=recruitJobList.get(j).getJobName()+",";
//            }
//            recruitCompanyList.get(i).setExt3(jobname);
//        }
        for (RecruitCompany recruitCompany:
        recruitCompanyList) {
            Example example1 = new Example(RecruitJob.class);
            Example.Criteria criteria1 =example1.createCriteria();
            criteria1.andEqualTo("companyUuid",recruitCompany.getId());
            criteria1.andEqualTo("status",0);
            List<RecruitJob> recruitJobList =db.selectAllByExample(RecruitJob.class,example1);
            String jobname ="";
//            for (int j = 0; j <recruitJobList.size() ; j++) {
//                jobname+=recruitJobList.get(j).getJobName()+",";
//            }
            for (RecruitJob recruitJob:
            recruitJobList) {
                jobname+=recruitJob.getJobName()+",";
            }
            recruitCompany.setExt3(jobname);
        }
        List<RecruitCompany> recruitCompanyListlength =db.selectAllByExample(RecruitCompany.class,example);
        json.setObj(recruitCompanyList);
        json.setFlag(true);
        json.setMsg(String.valueOf(recruitCompanyListlength.size()));
        return json;
    }

    @RequestMapping("/qzjob")
    public JSON qzJob(@RequestParam(name = "pages", defaultValue = "") int pages,  @RequestParam(name = "name", defaultValue = "")String name
                        ) throws ParseException {
        Page page = new Page();
        page.setRows(10);
        page.setPage(pages);
        JSON json = new JSON();
        Example example = new Example(RecruitJob.class);
        Example.Criteria criteria =example.createCriteria();
        Example.Criteria criteria1 =example.createCriteria();
        if(!name.equals("")){
            criteria.andLike("companyName","%"+name+"%");
            criteria1.orLike("jobName","%"+name+"%");
            example.and(criteria1);
        }
        criteria.andLike("ext1","%长沙学院%");
        criteria.andEqualTo("status",0);
        List<RecruitJob> recruitJobList =db.selectPageByExample(RecruitJob.class,example,page);
        List<RecruitJob> recruitJobListlength =db.selectAllByExample(RecruitJob.class,example);
        json.setObj(recruitJobList);
        json.setFlag(true);
        json.setMsg(String.valueOf(recruitJobListlength.size()));
        return json;
    }


    //校内宣讲会
    @RequestMapping("/xnxjh")
    public JSON xnxjh(@RequestParam(name = "pages", defaultValue = "") int pages,  @RequestParam(name = "name", defaultValue = "")String name,
                         @RequestParam(name = "zy", defaultValue = "")String zy,
                         @RequestParam(name = "gzdz", defaultValue = "")String gzdz) throws ParseException {
        Page page = new Page();
        page.setRows(10);
        page.setPage(pages);
        JSON json = new JSON();
        Example example = new Example(SchoolRecruitConference.class);
        Example.Criteria criteria =example.createCriteria();
        if(!name.equals("")){
            criteria.andLike("companyName","%"+name+"%");
        }
        if(!zy.equals("")){
            criteria.andLike("recruitMajor","%"+zy+"%");
        }
        if(!gzdz.equals("")){
            criteria.andLike("companyCity","%"+gzdz+"%");
        }
        criteria.andEqualTo("conferenceType",0);
        List<SchoolRecruitConference> schoolRecruitConferenceList=db.selectPageByExample(SchoolRecruitConference.class,example,page);
        List<SchoolRecruitConference> schoolRecruitConferenceListlength = db.selectAllByExample(SchoolRecruitConference.class,example);
        json.setObj(schoolRecruitConferenceList);
        json.setFlag(true);
        json.setMsg(String.valueOf(schoolRecruitConferenceListlength.size()));
        return json;
    }

    //校外宣讲会
    @RequestMapping("/xwxjh")
    public JSON xwxjh(@RequestParam(name = "pages", defaultValue = "") int pages,  @RequestParam(name = "name", defaultValue = "")String name,
                    @RequestParam(name = "zy", defaultValue = "")String zy,
                    @RequestParam(name = "gzdz", defaultValue = "")String gzdz) throws ParseException {
        Page page = new Page();
        page.setRows(10);
        page.setPage(pages);
        JSON json = new JSON();
        Example example = new Example(SchoolRecruitConference.class);
        Example.Criteria criteria =example.createCriteria();
        if(!name.equals("")){
            criteria.andLike("companyName","%"+name+"%");
        }
        if(!zy.equals("")){
            criteria.andLike("recruitMajor","%"+zy+"%");
        }
        if(!gzdz.equals("")){
            criteria.andLike("companyCity","%"+gzdz+"%");
        }
        criteria.andEqualTo("conferenceType",1);
        List<SchoolRecruitConference> schoolRecruitConferenceList=db.selectPageByExample(SchoolRecruitConference.class,example,page);
        List<SchoolRecruitConference> schoolRecruitConferenceListlength = db.selectAllByExample(SchoolRecruitConference.class,example);
        json.setObj(schoolRecruitConferenceList);
        json.setFlag(true);
        json.setMsg(String.valueOf(schoolRecruitConferenceListlength.size()));
        return json;
    }
    @RequestMapping("/addCountView")
    public JSON addCountView(@RequestParam(name = "id", defaultValue = "")String id) throws ParseException {
        SchoolRecruitConference schoolRecruitConference =db.selectById(id,SchoolRecruitConference.class);
        schoolRecruitConference.setCountView(schoolRecruitConference.getCountView()+1);
        db.update(schoolRecruitConference);
        JSON json = new JSON();
        json.setObj(schoolRecruitConference);
        json.setFlag(true);
        return  json;
    }


    //职位详情
    @RequestMapping("getJobDetails")
    public JSON getJobDetails(@RequestParam(name = "jobid", defaultValue = "") String jobid) throws ParseException {
        JSON json = new JSON();
        RecruitJob recruitJob = db.selectById(jobid,RecruitJob.class);
        recruitJob.setCountView(recruitJob.getCountView()+1);
        db.update(recruitJob);
        RecruitCompany recruitCompany =db.selectById(recruitJob.getCompanyUuid(),RecruitCompany.class);
        Map map = new HashMap();
        recruitJob.setExt3(recruitCompany.getIntroduction());
        map.put("recruitJob",recruitJob);
        map.put("recruitCompany",recruitCompany);

        json.setObj(map);
        json.setFlag(true);
        json.setMsg("");
        return json;
    }

    //公司详情
    @RequestMapping("getCompanyDetails")
    public JSON getCompanyDetails(@RequestParam(name = "jobid", defaultValue = "") String jobid) throws ParseException {
        JSON json = new JSON();
        RecruitJob recruitJob = db.selectById(jobid,RecruitJob.class);
        if(recruitJob!=null){
            RecruitCompany recruitCompany =db.selectById(recruitJob.getCompanyUuid(),RecruitCompany.class);
            Map map = new HashMap();
            Example example = new Example(RecruitJob.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("companyUuid",recruitJob.getCompanyUuid());
            criteria.andEqualTo("status",0);
            List<RecruitJob> recruitJobList =db.selectAllByExample(RecruitJob.class,example);
//        recruitJob.setExt3(recruitCompany.getIntroduction());
            map.put("recruitJob",recruitJob);
            map.put("recruitCompany",recruitCompany);
            map.put("recruitJobList",recruitJobList);
            json.setObj(map);
            json.setFlag(true);
            json.setMsg("");
        }else {
            RecruitCompany recruitCompany =db.selectById(jobid,RecruitCompany.class);
            Map map = new HashMap();
            Example example = new Example(RecruitJob.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("companyUuid",jobid);
            criteria.andEqualTo("status",0);
            List<RecruitJob> recruitJobList =db.selectAllByExample(RecruitJob.class,example);
//        recruitJob.setExt3(recruitCompany.getIntroduction());
            map.put("recruitJob",recruitJob);
            map.put("recruitCompany",recruitCompany);
            map.put("recruitJobList",recruitJobList);
            json.setObj(map);
            json.setFlag(true);
            json.setMsg("");
        }

        return json;
    }
}
