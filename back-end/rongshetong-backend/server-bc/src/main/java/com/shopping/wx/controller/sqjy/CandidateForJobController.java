package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.CandidateForJob;
import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.model.ViewRecord;
import com.shopping.wx.util.UUIDUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("cand-job")
public class CandidateForJobController {
    @Autowired
    private DB db;
    @RequestMapping("/add")
    public ActionResult<Boolean> add(String candidateOpenid,String jobUuid) {
        System.out.println(candidateOpenid);
        System.out.println(jobUuid);
        Example example = new Example(CandidateForJob.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",candidateOpenid);
        criteria.andEqualTo("jobUuid",jobUuid);
        List<CandidateForJob> candidateForJobList =db.selectAllByExample(CandidateForJob.class,example);
        CandidateForJob candidateForJob = new CandidateForJob();
        if(candidateForJobList.size()==0){
            candidateForJob.setCandidateOpenid(candidateOpenid);
            candidateForJob.setJobUuid(jobUuid);
            candidateForJob.setId(UUIDUtils.randomUUID());
            candidateForJob.setCreateTime(new Date());
            candidateForJob.setFlagCall(1);
            candidateForJob.setStatus(0);
            db.insert(candidateForJob);
        }
        return ActionResult.ok(true);
    }

    @RequestMapping("/list")
    public JSON list(String openid,String jobUuid) {
        JSON json = new JSON();
        Example example = new Example(CandidateForJob.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",openid);
        criteria.andEqualTo("jobUuid",jobUuid);
        List<CandidateForJob> candidateForJobList =db.selectAllByExample(CandidateForJob.class,example);
        json.setObj(candidateForJobList.size());
        return json;
    }


    //面试邀请判断
    @RequestMapping("/searchmsyq")
    public JSON searchmsyq(String candidateOpenid,String jobUuid) {
        JSON json = new JSON();
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",candidateOpenid);
        criteria.andEqualTo("jobUuid",jobUuid);
        criteria.andEqualTo("flagWhoReceive",0);
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);
        json.setObj(recruitRecordList.size());
        return json;
    }

    //查看面试邀请
    @RequestMapping("/ckyq")
    public JSON ckyq(String openid,int pagess) {
        JSON json = new JSON();
        Page page= new Page();
        page.setPage(pagess);
        page.setRows(10);
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",openid);
        example.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);


        Example exampless = new Example(RecruitRecord.class);
        Example.Criteria criteriass =exampless.createCriteria();
        criteriass.andEqualTo("candidateOpenid",openid);
        criteriass.andEqualTo("flagViewReceive",0);
        exampless.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordListss =db.selectAllByExample(RecruitRecord.class,exampless);
        for (int i = 0; i <recruitRecordListss.size() ; i++) {
            recruitRecordListss.get(i).setFlagViewReceive(1);
            db.update(recruitRecordListss.get(i));
        }
        json.setObj(recruitRecordList);
        return json;
    }

//管理简历发送面试邀请
    @RequestMapping("/fsmsyq")
    public JSON fsmsyq(String openid,String id) {
        JSON json = new JSON();
        Page page= new Page();
        page.setRows(10);
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("id",id);
        criteria.andEqualTo("candidateOpenid",openid);
        example.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);
        recruitRecordList.get(0).setFlowRecruit(2);
        db.update( recruitRecordList.get(0));
        json.setObj(recruitRecordList);
        return json;
    }
    //简历管理查询待面试
    @RequestMapping("/ckdms")
    public JSON ckdms(String openid,int pagess) {
        JSON json = new JSON();
        Page page= new Page();
        page.setPage(pagess);
        page.setRows(10);
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",openid);
        criteria.andEqualTo("flowRecruit",2);
        example.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);

        json.setObj(recruitRecordList);
        return json;
    }

    //管理简历通过面试
    @RequestMapping("/tgms")
    public JSON tgms(String openid,String id) {
        JSON json = new JSON();
        Page page= new Page();
        page.setRows(10);
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("id",id);
        criteria.andEqualTo("candidateOpenid",openid);
        example.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);
        recruitRecordList.get(0).setFlowRecruit(3);
        db.update( recruitRecordList.get(0));
        json.setObj(recruitRecordList);
        return json;
    }

    //查询管理简历通过面试
    @RequestMapping("/cktgms")
    public JSON cktgms(String openid,int pagess) {
        JSON json = new JSON();
        Page page= new Page();
        page.setPage(pagess);
        page.setRows(10);
        Example example = new Example(RecruitRecord.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("candidateOpenid",openid);
        criteria.andEqualTo("flowRecruit",3);
        example.setOrderByClause("create_time desc");
        List<RecruitRecord> recruitRecordList =db.selectAllByExample(RecruitRecord.class,example);

        json.setObj(recruitRecordList);
        return json;
    }
}
