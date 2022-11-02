package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.InformationCollectSelfBuilding;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.InformationCollectSelfBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName InformationCollectSelfBuilding
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/10
 **/
@RestController
@RequestMapping("/information-collect-self-building")
public class InformationCollectSelfBuildingController extends CrudController<InformationCollectSelfBuilding, String> {

    @Autowired
    InformationCollectSelfBuildingService informationCollectSelfBuildingService;

    @PostMapping("/add")
    public ActionResult<String> add(@RequestBody InformationCollectSelfBuilding informationCollectSelfBuilding) {
        insert(informationCollectSelfBuilding);
        return ActionResult.ok(informationCollectSelfBuilding.getId());
    }


    @PostMapping("/modify")
    public ActionResult<String> modify(@RequestBody InformationCollectSelfBuilding informationCollectSelfBuilding) {
        update(informationCollectSelfBuilding);
        return ActionResult.ok(informationCollectSelfBuilding.getId());
    }



    @Override
    protected CrudService<InformationCollectSelfBuilding> getService() {
        return informationCollectSelfBuildingService;
    }


}
