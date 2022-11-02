package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.DimHouseUsage;
import com.shopping.wx.model.DimSecuritySituation;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.DimHouseUsageService;
import com.shopping.wx.service.community_recruitment.DimSecuritySituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DimSecuritySituationController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/dim-security-situation")
public class DimSecuritySituationController extends CrudController<DimSecuritySituation, String> {
    @Autowired
    DimSecuritySituationService dimSecuritySituationService;

    @RequestMapping("/list")
    public ActionResult<List<DimSecuritySituation>> list(){
        return ActionResult.ok(dimSecuritySituationService.list());
    }

    @Override
    protected CrudService<DimSecuritySituation> getService() {
        return dimSecuritySituationService;
    }
}
