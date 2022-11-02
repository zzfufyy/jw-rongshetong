package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.model.DimHouseUsage;
import com.shopping.wx.model.ResidentForHouseUsage;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.DimHouseUsageService;
import com.shopping.wx.service.community_recruitment.ResidentForHouseUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * @ClassName ResidentForHouseUsageController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/resident-for-house-usage")
public class ResidentForHouseUsageController extends CrudController<ResidentForHouseUsage, String> {
    @Autowired
    ResidentForHouseUsageService residentForHouseUsageService;

    @RequestMapping("/addByEntityList")
    public ActionResult<String> addByEntityList(@RequestParam String residentUuid, @RequestBody List<ResidentForHouseUsage> residentForHouseUsageList) {
        // 先删后增
        ResidentForHouseUsage residentForHouseUsage = new ResidentForHouseUsage();
        residentForHouseUsage.setResidentUuid(residentUuid);
        residentForHouseUsageService.delete(residentForHouseUsage);
        residentForHouseUsageList.forEach(r -> insert(r));
        return ActionResult.ok();
    }

    @GetMapping("listByResidentUuid")
    public ActionResult<List<ResidentForHouseUsage>> listByResidentUuid(@RequestParam String residentUuid){
        return ActionResult.ok(residentForHouseUsageService.listByResidentUuid(residentUuid));
    }

    /**
     * TODO
     *
     * 关系表  定时任务 凌晨清一次（数据量大 执行过慢）
     */


    @Override
    protected CrudService<ResidentForHouseUsage> getService() {
        return residentForHouseUsageService;
    }
}
