package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BcUserWx;
import com.shopping.wx.pojo.vo.user.UserInfoVO;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljy
 * @date 2022-03-22 10:21
 */

@RestController
@RequestMapping("/user")
public class UserController extends CrudController<BcUserWx, Long> {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    ActionResult<UserInfoVO> info(String openid) {
        return ActionResult.ok(
                castVO(userService.selectByOpenid(openid), UserInfoVO::new)
        );
    }


    /**
     * 子类需要实现此方法以指定服务
     *
     * @return 对应此实体的增删查改服务
     */
    @Override
    protected CrudService<BcUserWx> getService() {
        return userService;
    }
}
