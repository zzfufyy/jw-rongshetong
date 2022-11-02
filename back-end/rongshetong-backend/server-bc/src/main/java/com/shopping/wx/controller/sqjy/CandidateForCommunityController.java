package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CandidateForCommunity;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CandidateForCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CandidateForCommunityController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/3
 **/
@RestController
@RequestMapping("/candidate-for-community")
public class CandidateForCommunityController extends CrudController<CandidateForCommunity, String> {
    @Autowired
    CandidateForCommunityService candidateForCommunityService;


    @RequestMapping("/add")
    public ActionResult<String> add(@RequestParam String candidateOpenid, @RequestBody List<CandidateForCommunity> categoryList) {

        // 删除该openid相关职业记录
        CandidateForCommunity candidateForCommunity = new CandidateForCommunity();
        candidateForCommunity.setCandidateOpenid(candidateOpenid);
        int deleteNum = candidateForCommunityService.delete(candidateForCommunity);
        // 插入数据
        categoryList.forEach(r -> insert(r));
        return ActionResult.ok();
    }

    /**
     * 根据求职用户id 返回社区意向实体list
     *
     * @param candidateOpenid
     * @return com.shopping.base.foundation.result.ActionResult<java.util.List   <   com.shopping.wx.model.CandidateForCommunity>>
     */
    @RequestMapping("/listByCandidateOpenid")
    public ActionResult<List<CandidateForCommunity>> listByCandidateOpenid(@RequestParam String candidateOpenid) {
        return ActionResult.ok(
                candidateForCommunityService.listByCandidateOpenid(candidateOpenid)
        );
    }

    @Override
    protected CrudService<CandidateForCommunity> getService() {
        return candidateForCommunityService;
    }
}
