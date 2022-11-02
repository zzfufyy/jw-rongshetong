package com.shopping.wx.controller.bc;

import com.shopping.base.foundation.dao.bc.TemplateDAO;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.repository.bc.BcUserRepository;
import com.shopping.framework.redis.service.RedisService;
import com.shopping.wx.form.bc.BcUserQueryForm;
import com.shopping.wx.service.bc.BcUserService;
import com.shopping.wx.service.bc.BcUserWxService;
import com.shopping.wx.token.authorization.manager.BcLoginUtils;
import com.shopping.wx.util.BaseSMS;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/bc/{appid}/BcDepUser")
@Log4j2
public class BcDepUserInfoController {
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
    @GetMapping("/getUserPageList")
    public ActionResult getUserPageList(@PathVariable String appid,String bmid,int currentPage,int pageSize ,BcUserQueryForm queryForm){
        try{
            Map<Object,Object> map = new HashMap(2);
            queryForm.setAppId(appid);
            queryForm.setDepartmentId(Long.valueOf(bmid));
            List<Map<String,Object>> list =this.bcUserService.getUserPageList(queryForm,currentPage,pageSize);
            BigInteger total = this.bcUserService.getTotalByFields(queryForm);
            map.put("data",list);
            map.put("total",total);
            return ActionResult.ok(map);
        }catch (Exception e){
            log.error("获取员工信息列表异常",e);
        }
        return  ActionResult.error("服务器异常!");
    }

}
