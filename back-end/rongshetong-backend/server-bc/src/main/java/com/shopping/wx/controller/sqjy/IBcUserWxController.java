package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BcUserWx;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.IBcUserWxService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName BcUserWxController
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/31
 **/

@RestController
@RequestMapping("/bc-user-wx")
public class IBcUserWxController extends CrudController<BcUserWx, Long> {
    @Autowired
    private IBcUserWxService iBcUserWxService;

    @PostMapping("/add")
    public ActionResult<?> add(@RequestBody BcUserWx bcUserWx){
        bcUserWx.setAddtime(new Date());
        bcUserWx.setCreateTime(new Date());
        insert(bcUserWx);
        return ActionResult.ok();
    }

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());
    }
    @Override
    protected CrudService<BcUserWx> getService() {
        return iBcUserWxService;
    }
}
