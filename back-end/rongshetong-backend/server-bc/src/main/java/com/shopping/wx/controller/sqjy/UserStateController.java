package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserState;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserStateService;
import com.shopping.wx.util.UUIDUtils;
import org.junit.Test;
import org.nutz.mvc.annotation.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author ljy
 * @date 2022-03-11 16:52
 */
@RestController
@RequestMapping("/user-state")
public class UserStateController extends CrudController<UserState, String> {
    @Autowired
    private UserStateService userStateService;

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody UserState userState) {
        UserState queryResult =  userStateService.selectById(userState.getId());
        if(ObjectUtils.isEmpty(queryResult)) {
            // 授权微信用户昵称 插入
            userState.setLoginTime(new Date());
            insert(userState);
        }
        return ActionResult.ok();
    }

    @GetMapping("/info")
    ActionResult<UserState> info(@RequestParam("id") String id) {
        return ActionResult.ok(
                userStateService.selectById(id)
        );
    }

    @PostMapping("/modify")
    ActionResult<?> modify(@RequestBody UserState userState) {
        userState.setLoginTime(new Date());
        userStateService.update(userState);
        return ActionResult.ok();
    }


    @Test
    public void test(){
        System.out.println(UUIDUtils.timeBaseUUID());
    }
    @Override
    protected CrudService<UserState> getService() {
        return userStateService;
    }
}
