package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.model.JobFair;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.dto.fair_for_candidate.FairForCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_candidate.FairForCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FairForCandidateService;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FairForCandidateController
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@RestController
@RequestMapping("/fair-for-candidate")
public class FairForCandidateController extends CrudController<FairForCandidate, String> {
    @Autowired
    private FairForCandidateService fairForCandidateService;

    @Autowired
    private DB db;

    @Override
    protected CrudService<FairForCandidate> getService() {
        return fairForCandidateService;
    }


    @RequestMapping("/pageDTO")
    public ActionResult<PageInfo<FairForCandidateDTO>> pageDTO(@RequestBody PagingParam<FairForCandidateSearchCondition> pagingParam){

        return ActionResult.ok(
                PageInfo.of(fairForCandidateService.pageDTO(pagingParam))
        );
    }

    @RequestMapping("/add")
    public ActionResult<Integer> add(@RequestBody FairForCandidate fairForCandidate) {
        // 唯一性保护 - 先删除
        FairForCandidate deleteFairForCandidate = new FairForCandidate();
        deleteFairForCandidate.setFairUuid(fairForCandidate.getFairUuid());
        deleteFairForCandidate.setCandidateOpenid(fairForCandidate.getCandidateOpenid());
        fairForCandidateService.delete(deleteFairForCandidate);
        // 后插入
        return ActionResult.ok(fairForCandidateService.insert(fairForCandidate));
    }
    /***
     * 是否 已经报名
     *
     * @param fairUuid
     * @param candidateOpenid
     * @return com.shopping.base.foundation.result.ActionResult<java.lang.Boolean>
     */
    @GetMapping("isSign")
    public ActionResult<Boolean> isSign(@RequestParam String fairUuid, @RequestParam String candidateOpenid) {
        FairForCandidate conditionFairForCandidate = new FairForCandidate();
        conditionFairForCandidate.setFairUuid(fairUuid);
        conditionFairForCandidate.setCandidateOpenid(candidateOpenid);
        long count = fairForCandidateService.selectCount(conditionFairForCandidate);
        return ActionResult.ok(count >= 1 ? true : false);
    }




    @RequestMapping("/insertFairCandidate")
    public JSON insertFairCandidate(String fairUuid, String candidateOpenid) {
        FairForCandidate fairForCandidate = new FairForCandidate();
        fairForCandidate.setId(UuidUtil.getTimeBasedUuid().toString());
        fairForCandidate.setFairUuid(fairUuid);
        fairForCandidate.setCandidateOpenid(candidateOpenid);
        fairForCandidate.setCreateTime(new Date());
        fairForCandidate.setStatus(0);
        db.insert(fairForCandidate);
        JSON json = new JSON();
        Map map = new HashMap();
//        map.put("jobFair",jobFair);
        json.setObj(map);
        return json;
    }

    @RequestMapping("/searchFairCandidate")
    public JSON searchFairCandidate(String fairUuid, String candidateOpenid) {
        UserCandidate userCandidate = db.selectById(candidateOpenid, UserCandidate.class);
        Example example = new Example(FairForCandidate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fairUuid", fairUuid);
        criteria.andEqualTo("candidateOpenid", candidateOpenid);
        List<FairForCandidate> fairForCandidates = db.selectAllByExample(FairForCandidate.class, example);
        JSON json = new JSON();
        Map map = new HashMap();
        map.put("fairForCandidates", fairForCandidates);
        json.setObj(map);
        return json;
    }
}