package com.shopping.wx.controller.bc;

import com.shopping.base.foundation.dao.bc.TemplateDAO;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.repository.bc.BcUserRepository;
import com.shopping.framework.redis.service.RedisService;
import com.shopping.wx.form.bc.BcUserAddForm;
import com.shopping.wx.service.bc.BcUserService;
import com.shopping.wx.service.bc.BcUserWxService;
import com.shopping.wx.token.authorization.manager.BcLoginUtils;
import com.shopping.wx.util.BaseSMS;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bc/{appid}/BcUnauditingUser")
@Log4j2
public class BcUnauditingUserContoller {

    @Autowired
    BcUserService bcUserService;
    @Autowired
    BaseSMS baseSMS;
    @Autowired
    RedisService redisService;
    @Autowired
    BcUserWxService bcUserWxService;
    @Autowired
    BcLoginUtils bcLoginUtils;
    @Autowired
    BcUserRepository bcUserRepository;

    @Autowired
    TemplateDAO templateDAO;

    /**
     * 报餐用户免审核注册信息保存
     * @param bcUserAddForm
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ActionResult bcUserRegister(@PathVariable String appid, BcUserAddForm bcUserAddForm,String sfsh){
        try {
            return bcUserService.bcUserRegister(appid,bcUserAddForm,sfsh);
        }catch (Exception e){
            log.error("报餐用户注册信息保存异常",e);
        }
        return ActionResult.error("服务器异常");
    }
}
