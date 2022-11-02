package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidateDTO;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidatePlus;
import com.shopping.wx.pojo.dto.recruit_record.RecordForRecruiterDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_record.RecruitRecordSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.RecruitRecordService;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * 招聘记录控制器
 * TODO: 建立 VO 模型
 *
 * @author ljy
 * @date 2022-03-16 16:44
 */
@RestController
@RequestMapping("/recruit-record")
public class RecruitRecordController extends CrudController<RecruitRecord, String> {

    @Autowired
    RecruitRecordService recruitRecordService;

    @Autowired
    UserCandidateService userCandidateService;

    public RecruitRecordController(RecruitRecordService recruitRecordService) {
        this.recruitRecordService = recruitRecordService;
    }

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody RecruitRecord recruitRecord) {
        insert(recruitRecord);
        return ActionResult.ok();
    }

    @GetMapping("/info")
    ActionResult<RecruitRecord> info(String recordId) {
        return ActionResult.ok(query(recordId));
    }

    @PostMapping("/modify")
    ActionResult<?> modify(@RequestBody RecruitRecord recruitRecord) {
        update(recruitRecord);
        return ActionResult.ok();
    }

    @GetMapping("/page")
    ActionResult<PageInfo<RecruitRecord>> page(PagingParam<RecruitRecordSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitRecordService.page(pagingParam))
        );
    }

    @GetMapping("/countByCandidateOpenid")
    ActionResult<Long> countByCandidateOpenid(String candidateOpenid) {
        RecruitRecord  recruitRecord = new RecruitRecord();
        recruitRecord.setCandidateOpenid(candidateOpenid);
        return ActionResult.ok(
                recruitRecordService.selectCount(recruitRecord)
        );
    }

    @PostMapping("/countByEntity")
    ActionResult<Long> countByEntity(@RequestBody RecruitRecord recruitRecord) {
        return ActionResult.ok(
                recruitRecordService.selectCount(recruitRecord)
        );
    }


    @RequestMapping("/listRecordPlusByCandidateOpenid")
    ActionResult<List<RecordForCandidateDTO>> listRecordPlusByCandidateOpenid(String candidateOpenid) {
        // 加载位置参数
        UserCandidate userCandidate = userCandidateService.selectById(candidateOpenid);
        Location location = new Location();
        location.setLongitude(userCandidate.getLon());
        location.setLatitude(userCandidate.getLat());
        return ActionResult.ok(
                recruitRecordService.listRecordPlusByCandidateOpenid(candidateOpenid, location)
        );
    }
    @RequestMapping("/listRecordPlusByCompanyUuid")
    ActionResult<List<RecordForRecruiterDTO>> listRecordPlusByCompanyUuid(String companyUuid) {
        return ActionResult.ok(
                recruitRecordService.listRecordPlusByCompanyUuid(companyUuid)
        );
    }

    @PostMapping("/listRecordForCandidatePlus")
    ActionResult<List<RecordForCandidatePlus>> listRecordForCandidatePlus(@RequestBody RecruitRecordSearchCondition recordSearchCondition){
        return ActionResult.ok(recruitRecordService.listRecordForCandidatePlus(recordSearchCondition));
    }

    @GetMapping("/countInterviewResult")
    ActionResult<Integer> countInterviewResult(@RequestParam String candidateOpenid){
        return ActionResult.ok(recruitRecordService.countInterviewResult(candidateOpenid));
    }

    @Override
    protected CrudService<RecruitRecord> getService() {
        return recruitRecordService;
    }
}
