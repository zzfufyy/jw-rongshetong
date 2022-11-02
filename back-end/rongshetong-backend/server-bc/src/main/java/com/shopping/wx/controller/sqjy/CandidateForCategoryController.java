package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CandidateForCategory;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CandidateForCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName RecruitCompanyController
 * @Description 公司相关controller
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("/candidate-for-category")
public class CandidateForCategoryController extends CrudController<CandidateForCategory, String> {

    @Autowired
    private CandidateForCategoryService candidateForCategoryService;


    @GetMapping("/info")
    public ActionResult<CandidateForCategory> info(@RequestParam String id) {
        return ActionResult.ok(candidateForCategoryService.selectById(id));
    }

    /**
     * 添加求职者意向
     *
     * @param categoryList 求职者意向的categorylist
     * @return com.shopping.base.foundation.result.ActionResult<java.lang.String>
     */
    @RequestMapping("/add")
    public ActionResult<String> add(@RequestParam String candidateOpenid, @RequestBody List<CandidateForCategory> categoryList) {

        // 删除该openid相关职业记录
        CandidateForCategory candidateForCategory = new CandidateForCategory();
        candidateForCategory.setCandidateOpenid(candidateOpenid);
        int deleteNum = candidateForCategoryService.delete(candidateForCategory);
        // 插入数据
        categoryList.forEach(
                r -> insert(r)
        );
        return ActionResult.ok();
    }

    /**
     * 根据用户id 返回 求职意向实体list
     *
     * @param candidateOpenid
     * @return com.shopping.base.foundation.result.ActionResult<java.util.List       <       com.shopping.wx.model.CandidateForCategory>>
     */
    @RequestMapping("/listByCandidateOpenid")
    public ActionResult<List<CandidateForCategory>> listByCandidateOpenid(@RequestParam String candidateOpenid) {
        return ActionResult.ok(
                candidateForCategoryService.listByCandidateOpenid(candidateOpenid)
        );
    }


    @Override
    protected CrudService<CandidateForCategory> getService() {
        return candidateForCategoryService;
    }


}
