package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CandidateForCategory;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CompanyForCategoryService;
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
@RequestMapping("/company-for-category")
public class CompanyForCategoryController extends CrudController<CompanyForCategory, String> {

    @Autowired
    private CompanyForCategoryService companyForCategoryService;



    @GetMapping("/info")
    public ActionResult<CompanyForCategory> info(@RequestParam String id) {
        return ActionResult.ok(companyForCategoryService.selectById(id));
    }

    @RequestMapping("/add")
    public ActionResult<String> add(@RequestParam String companyUuid, @RequestBody List<CompanyForCategory> categoryList) {

        // 删除该openid相关职业记录
        CompanyForCategory companyForCategory = new CompanyForCategory();
        companyForCategory.setCompanyUuid(companyUuid);
        companyForCategoryService.delete(companyForCategory);
        // 插入数据
        categoryList.forEach(r -> insert(r));
        return ActionResult.ok();
    }


    @Override
    protected CrudService<CompanyForCategory> getService() {
        return companyForCategoryService;
    }




}
