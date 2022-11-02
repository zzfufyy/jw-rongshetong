package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserForFunctionTag;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserForFunctionTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserForFunctionTagController
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/27
 **/
@RestController
@RequestMapping("/user-for-function-tag")
public class UserForFunctionTagController extends CrudController<UserForFunctionTag, String> {
    @Autowired
    private UserForFunctionTagService userForFunctionTagService;

    @GetMapping("/infoByUserAndPath")
    public ActionResult<UserForFunctionTag> infoByUserOpenid(@RequestParam String userOpenid, @RequestParam String pagePathApply) {
        return ActionResult.ok(
                userForFunctionTagService.infoByUserAndPath(userOpenid, pagePathApply)
        );
    }

    @PostMapping("/add")
    public ActionResult<?> add(@RequestBody UserForFunctionTag userForFunctionTag){
        return ActionResult.ok(
                userForFunctionTagService.insert(userForFunctionTag)
        );
    }

    @PostMapping("/modify")
    public ActionResult<?> modify(@RequestBody UserForFunctionTag userForFunctionTag){
        return ActionResult.ok(
                userForFunctionTagService.update(userForFunctionTag)
        );
    }


    @Override
    protected CrudService<UserForFunctionTag> getService() {
        return userForFunctionTagService;
    }
}
