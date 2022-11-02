package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.ResidentForHouseUsage;
import com.shopping.wx.model.ResidentForSecuritySituation;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.ResidentForHouseUsageService;
import com.shopping.wx.service.community_recruitment.ResidentForSecuritySituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ResidentForSecuritySituationController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/resident-for-security-situation")
public class ResidentForSecuritySituationController extends CrudController<ResidentForSecuritySituation, String> {
    @Autowired
    ResidentForSecuritySituationService residentForSecuritySituationService;

    @RequestMapping("/addByEntityList")
    public ActionResult<String> addByEntityList(@RequestParam String residentUuid, @RequestBody List<ResidentForSecuritySituation> residentForSecuritySituationList) {
        // 先删后增
        ResidentForSecuritySituation residentForSecuritySituation = new ResidentForSecuritySituation();
        residentForSecuritySituation.setResidentUuid(residentUuid);
        residentForSecuritySituationService.delete(residentForSecuritySituation);
        residentForSecuritySituationList.forEach(r -> insert(r));
        return ActionResult.ok();
    }

    @GetMapping("listByResidentUuid")
    public ActionResult<List<ResidentForSecuritySituation>> listByResidentUuid(@RequestParam String residentUuid){
        return ActionResult.ok(residentForSecuritySituationService.listByResidentUuid(residentUuid));
    }

    /**
     * TODO
     *
     * 关系表  定时任务 凌晨清一次（数据量大 执行过慢）
     */

    @Override
    protected CrudService<ResidentForSecuritySituation> getService() {
        return residentForSecuritySituationService;
    }
}
