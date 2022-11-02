package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.model.FairForCandidate;
import com.shopping.wx.model.FairForCompany;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.fair_for_candidate.FairForCandidateDTO;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyDTO;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyJobDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_candidate.FairForCandidateSearchCondition;
import com.shopping.wx.pojo.vo.fair_for_company.FairForCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FairForCompanyService;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.swing.*;
import java.util.*;

/**
 * @ClassName FairForCompanyController
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@RestController
@RequestMapping("/fair-for-company")
public class FairForCompanyController extends CrudController<FairForCompany, String> {
    @Autowired
    private FairForCompanyService fairForCompanyService;
    @Autowired
    private DB db;

    @Override
    protected CrudService<FairForCompany> getService() {
        return fairForCompanyService;
    }

    @RequestMapping("/add")
    public ActionResult<Integer> add(@RequestBody FairForCompany fairForCompany) {
        // 唯一性保护 - 先删除
        FairForCompany deleteFairForCompany = new FairForCompany();
        deleteFairForCompany.setFairUuid(fairForCompany.getFairUuid());
        deleteFairForCompany.setRecruiterOpenid(fairForCompany.getRecruiterOpenid());
        fairForCompanyService.delete(deleteFairForCompany);
        // 后插入
        return ActionResult.ok(fairForCompanyService.insert(fairForCompany));
    }
    /**
     * 是否 已经报名
     *
     * @param fairUuid
     * @param recruiterOpenid
     * @return com.shopping.base.foundation.result.ActionResult<java.lang.Boolean>
     */
    @GetMapping("/isSign")
    public ActionResult<Boolean> isSign(@RequestParam String fairUuid, @RequestParam String recruiterOpenid) {
        FairForCompany conditionFairForCompany = new FairForCompany();
        conditionFairForCompany.setFairUuid(fairUuid);
        conditionFairForCompany.setRecruiterOpenid(recruiterOpenid);
        long count = fairForCompanyService.selectCount(conditionFairForCompany);
        return ActionResult.ok(count >= 1 ? true : false);
    }

    @RequestMapping("/pageDTO")
    public ActionResult<PageInfo<FairForCompanyDTO>> pageDTO(@RequestBody PagingParam<FairForCompanySearchCondition> pagingParam){

        return ActionResult.ok(
                PageInfo.of(fairForCompanyService.pageDTO(pagingParam))
        );
    }

    @RequestMapping("/pageJobDTO")
    public ActionResult<PageInfo<FairForCompanyJobDTO>> pageJobDTO(@RequestBody PagingParam<FairForCompanySearchCondition> pagingParam){
        return ActionResult.ok(
                PageInfo.of(fairForCompanyService.pageJobDTO(pagingParam))
        );
    }

    @RequestMapping("/insertFairCompany")
    public JSON insertFairCompany(String fairUuid, String candidateOpenid) {
        FairForCompany fairForCompany = new FairForCompany();
        fairForCompany.setId(UuidUtil.getTimeBasedUuid().toString());
        fairForCompany.setFairUuid(fairUuid);
        fairForCompany.setRecruiterOpenid(candidateOpenid);
        UserRecruiter userRecruiter = db.selectById(candidateOpenid, UserRecruiter.class);
        fairForCompany.setCompanyUuid(userRecruiter.getCompanyUuid());
        fairForCompany.setCreateTime(new Date());
        fairForCompany.setStatus(0);
        db.insert(fairForCompany);
        JSON json = new JSON();
        Map map = new HashMap();
//        map.put("jobFair",jobFair);
        json.setObj(map);
        return json;
    }

    @RequestMapping("/searchFairCompany")
    public JSON searchFairCompany(String fairUuid, String candidateOpenid) {
        UserRecruiter userRecruiter = db.selectById(candidateOpenid, UserRecruiter.class);
        Map map = new HashMap();
        List<FairForCompany> fairForCompanies = new ArrayList<>();
        if (userRecruiter != null) {
            Example example = new Example(FairForCompany.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("fairUuid", fairUuid);
            criteria.andEqualTo("recruiterOpenid", userRecruiter.getId());
            criteria.andEqualTo("companyUuid", userRecruiter.getCompanyUuid());
            fairForCompanies = db.selectAllByExample(FairForCompany.class, example);
            map.put("fairForCompanies", fairForCompanies);
        } else {
            map.put("fairForCompanies", fairForCompanies);
        }

        JSON json = new JSON();


        json.setObj(map);
        return json;
    }
}
