package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.SocialTrainingApply;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.SocialTrainingApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SocialTrainingApplyController
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/18
 **/
@RestController
@RequestMapping("/social-training-apply")
public class SocialTrainingApplyController extends CrudController<SocialTrainingApply, String> {
    @Autowired
    private  SocialTrainingApplyService socialTrainingApplyService;

    @RequestMapping("/add")
    public ActionResult<?> add(@RequestBody SocialTrainingApply socialTrainingApply){
        return ActionResult.ok(
                socialTrainingApplyService.insert(socialTrainingApply)
        );
    }

    @Override
    protected CrudService<SocialTrainingApply> getService() {
        return socialTrainingApplyService;
    }
}
