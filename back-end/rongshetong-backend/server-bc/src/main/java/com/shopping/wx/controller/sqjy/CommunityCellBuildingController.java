package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CommunityCellBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CommunityCellController
 * @Description 小区楼栋
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/community-cell-building")
public class CommunityCellBuildingController extends CrudController<CommunityCellBuilding, String> {
    @Autowired
    CommunityCellBuildingService communityCellBuildingService;

    @RequestMapping("/info")
    public ActionResult<CommunityCellBuilding> info(@RequestParam("id") String id) {
        return ActionResult.ok(communityCellBuildingService.selectById(id));
    }

    @RequestMapping("/listByCommunityUuid")
    public ActionResult<List<CommunityCellBuilding>> listByCommunityUuid(@RequestParam String communityUuid) {
        return ActionResult.ok(communityCellBuildingService.listByCommunityUuid(communityUuid));
    }

    @RequestMapping("/listByCellUuid")
    public ActionResult<List<CommunityCellBuilding>> listByCellUuid(@RequestParam String cellUuid) {
        return ActionResult.ok(communityCellBuildingService.listByCellUuid(cellUuid));
    }



    @Override
    protected CrudService<CommunityCellBuilding> getService() {
        return communityCellBuildingService;
    }
}
