package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecommendWeight;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.RecommendWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RecommendWeightController
 * @Description 权重
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("/recommend-weight")
public class RecommendWeightController extends CrudController<RecommendWeight, String> {
    @Autowired
    private RecommendWeightService recommendWeightService;



    @RequestMapping("/info")
    public ActionResult<RecommendWeight> info(@RequestParam String uuid) {
        return ActionResult.ok(infoVO(uuid, RecommendWeight::new));
    }

    @RequestMapping("/add")
    ActionResult<Boolean> add(@RequestBody RecommendWeight recommendWeight){
        return insertVO(recommendWeight) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody RecommendWeight RecommendWeight) {
        return updateVO(RecommendWeight) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/incrCountView")
    public ActionResult<Boolean> incrCountView(@RequestParam String candidateOpenid, @RequestParam String categoryUuid){

        return null;
    }

    @Override
    protected CrudService<RecommendWeight> getService() {
        return recommendWeightService;
    }



}
