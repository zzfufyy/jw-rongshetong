package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.DimHouseUsage;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.DimHouseUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DimHouseUsageController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/dim-house-usage")
public class DimHouseUsageController extends CrudController<DimHouseUsage, String> {
    @Autowired
    DimHouseUsageService dimHouseUsageService;

    @RequestMapping("/list")
    public ActionResult<List<DimHouseUsage>> list(){
        return ActionResult.ok(dimHouseUsageService.list());
    }

    @Override
    protected CrudService<DimHouseUsage> getService() {
        return dimHouseUsageService;
    }
}
