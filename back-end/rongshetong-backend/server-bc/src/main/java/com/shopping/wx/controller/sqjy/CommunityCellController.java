package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.constant.ErrorMsgConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BuildingResident;
import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.model.ResidentForPerson;
import com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO;
import com.shopping.wx.pojo.dto.community_cell.CommunityCellDTO;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.BuildingResidentService;
import com.shopping.wx.service.community_recruitment.CommunityCellBuildingService;
import com.shopping.wx.service.community_recruitment.CommunityCellService;
import com.shopping.wx.service.community_recruitment.ResidentForPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CommunityCellController
 * @Description 小区表
 * @Author zyw
 * @Date 2022/4/11
 **/
@RestController
@RequestMapping("/community-cell")
public class CommunityCellController extends CrudController<CommunityCell, String> {
    @Autowired
    CommunityCellService communityCellService;
    @Autowired
    CommunityCellBuildingService communityCellBuildingService;
    @Autowired
    BuildingResidentService buildingResidentService;
    @Autowired
    ResidentForPersonService residentForPersonService;

    @RequestMapping("/info")
    public ActionResult<CommunityCell> info(@RequestParam("id") String id) {
        return ActionResult.ok(communityCellService.selectById(id));
    }

    @RequestMapping("/infoWithAssociated")
    public ActionResult<CommunityCell> infoWithAssociated(@RequestParam("id") String id) {
        return ActionResult.ok(communityCellService.infoWithAssociated(id));
    }

    /**
     * 级联更新
     *
     * @param communityCell
     * @return com.shopping.base.foundation.result.ActionResult<?>
     */
    @PostMapping("/modifyCascade")
    public ActionResult<?> modifyCascade(@RequestBody CommunityCell communityCell) {
        System.out.println(communityCell.toString());
        String communityUuid = communityCell.getCommunityUuid();
        String cellUuid = communityCell.getId();
        int buildingNameType = communityCell.getBuildingNameType();
        int newNumBuilding = communityCell.getNumBuilding();
        int newNumLayer = communityCell.getNumLayer();
        int newNumLayerFamily = communityCell.getNumLayerFamily();
        CommunityCell oldCommunityCell = communityCellService.selectById(cellUuid);
        int oldNumBuilding = oldCommunityCell.getNumBuilding();
        int oldNumLayer = oldCommunityCell.getNumLayer();
        int oldNumLayerFamily = oldCommunityCell.getNumLayerFamily();
        // 更新建筑
        communityCellBuildingService.updateByCompareNumBuilding( cellUuid, buildingNameType, newNumBuilding, oldNumBuilding);
        // 更新居民楼层 户数
        buildingResidentService.updateByCompareLayerAndLayerFamily(
                communityUuid, cellUuid,
                newNumLayer, oldNumLayer,
                newNumLayerFamily, oldNumLayerFamily);
        // 更新未关联上的
        residentForPersonService.deleteByCellUuid(cellUuid);
        return ActionResult.ok(communityCellService.update(communityCell));
    }
    /**
     * 级联删除
     *
     * @param id
     * @return com.shopping.base.foundation.result.ActionResult<?>
     */
    @GetMapping("/deleteCascade")
    public ActionResult<?> deleteCascade(@RequestParam String id) {
        // 清理小区
        CommunityCell communityCell = new CommunityCell();
        communityCell.setId(id);
        communityCellService.delete(communityCell);
        // 清理 小区楼栋
        CommunityCellBuilding communityCellBuilding = new CommunityCellBuilding();
        communityCellBuilding.setCellUuid(id);
        communityCellBuildingService.delete(communityCellBuilding);
        // 清理 小区居户
        BuildingResident buildingResident = new BuildingResident();
        buildingResident.setCellUuid(id);
        buildingResidentService.delete(buildingResident);
        // 清理 小区居民
        residentForPersonService.deleteByCellUuid(id);
        return ActionResult.ok();
    }

    @RequestMapping("/add")
    ActionResult<?> add(@RequestBody CommunityCell communityCell) {
        //  小区名称不能重复
        CommunityCell communityCellQuery = new CommunityCell();
        communityCellQuery.setCellName(communityCell.getCellName());
        CommunityCell queryResult = communityCellService.selectOne(communityCellQuery);
        if (queryResult == null) {
            // 插入小区
            insert(communityCell);
            // 插入楼栋
            List<CommunityCellBuilding> communityCellBuildingList = communityCellBuildingService.insertListByCommunityCell(communityCell);
            // 插入居民
            buildingResidentService.insertListByCommunityCellBuilding(communityCellBuildingList);
        } else {
            return ActionResult.error(ErrorMsgConstant.RepeatConflictError.CELL_NAME_REPEAT_ERROR);
        }
        return ActionResult.ok();
    }

    @RequestMapping("/listByCommunityUuid")
    public ActionResult<List<CommunityCell>> listByCommunityUuid(@RequestParam String communityUuid) {
        return ActionResult.ok(communityCellService.listByCommunityUuid(communityUuid));
    }

    @RequestMapping("/listDTOByCommunityUuid")
    public ActionResult<List<CommunityCellDTO>> listDTOByCommunityUuid(@RequestParam String communityUuid) {
        return ActionResult.ok(communityCellService.listDTOByCommunityUuid(communityUuid));
    }

    @RequestMapping("/updateByRecorderOpenid")
    public ActionResult<?> updateByRecorderOpenid(
            @RequestParam String recorderOpenid,
            @RequestBody List<String> cellUuidList) {
        return ActionResult.ok(communityCellService.updateByRecorderOpenid(recorderOpenid, cellUuidList));
    }

    @Override
    protected CrudService<CommunityCell> getService() {
        return communityCellService;
    }
}
