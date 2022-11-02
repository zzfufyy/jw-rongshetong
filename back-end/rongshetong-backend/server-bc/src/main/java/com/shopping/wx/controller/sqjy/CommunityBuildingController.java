package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CommunityBuilding;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_building.CommunityBuildingSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CommunityBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CommunityBuildingController
 * @Description 楼宇就业信息
 * @Author zyw
 * @Date 2022/9/14
 **/
@RestController
@RequestMapping("/community-building")
public class CommunityBuildingController extends CrudController<CommunityBuilding, String> {

    @Autowired
    private CommunityBuildingService communityBuildingService;

    @RequestMapping
    public ActionResult<CommunityBuilding> info(@RequestParam("id") String id){
        return ActionResult.ok(communityBuildingService.selectById(id));
    }

    @RequestMapping("/page")
    public ActionResult<PageInfo> page(@RequestBody PagingParam<CommunityBuildingSearchCondition> pagingParam){
        return ActionResult.ok(
                PageInfo.of(communityBuildingService.page(pagingParam))
        );
    }

    @RequestMapping("/pageStreet")
    public ActionResult<PageInfo> pageStreet(@RequestBody PagingParam<CommunityBuildingSearchCondition> pagingParam){
        return ActionResult.ok(
                PageInfo.of(communityBuildingService.pageStreet(pagingParam))
        );
    }

    @Override
    protected CrudService<CommunityBuilding> getService() {
        return communityBuildingService;
    }
}
