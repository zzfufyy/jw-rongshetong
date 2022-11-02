package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.FunctionTag;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FunctionTagService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName FunctionTagController
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/26
 **/
@RestController
@RequestMapping("/function-tag")
public class FunctionTagController extends CrudController<FunctionTag, String> {
    @Autowired
    private FunctionTagService functionTagService;

    @GetMapping("/listByDefaultOrder")
    public ActionResult<List<FunctionTag>> listByDefaultOrder(@RequestParam String pagePathApply, @RequestParam String communityUuid) {
        return ActionResult.ok(
                functionTagService.listByDefaultOrder(pagePathApply, communityUuid)
        );
    }

    @GetMapping("/listAll")
    public ActionResult<List<FunctionTag>> listAll() {
        return ActionResult.ok(
                functionTagService.listAll()
        );
    }

    @Override
    protected CrudService<FunctionTag> getService() {
        return functionTagService;
    }

}
