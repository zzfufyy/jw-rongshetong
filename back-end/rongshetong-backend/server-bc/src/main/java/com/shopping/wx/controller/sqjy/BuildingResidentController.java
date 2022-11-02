package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BuildingResident;
import com.shopping.wx.model.ResidentForPerson;
import com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.BuildingResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BuildingResidentController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
@RestController
@RequestMapping("/building-resident")
public class BuildingResidentController extends CrudController<BuildingResident, String> {
    @Autowired
    private BuildingResidentService buildingResidentService;

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/add")
    public ActionResult<?> add(@RequestBody BuildingResident buildingResident) {
        return ActionResult.ok(insert(buildingResident));
    }

    @RequestMapping("/modify")
    public ActionResult<?> modify(@RequestBody BuildingResident buildingResident) {
        buildingResident.setFlagRegister(1);
        buildingResident.setRegisterTime(new Date());
        return ActionResult.ok(buildingResidentService.updateByEntity(buildingResident));
    }

    @RequestMapping("/info")
    public ActionResult<BuildingResident> info(@RequestParam("id") String id) {
        return ActionResult.ok(buildingResidentService.selectById(id));
    }

    @RequestMapping("/infoWithAssociated")
    public ActionResult<BuildingResidentDTO> infoWithAssociated(@RequestParam("id") String id) {
        return ActionResult.ok(buildingResidentService.infoWithAssociated(id));
    }


    @RequestMapping("/loadListByBuildingUuid")
    public ActionResult<List<BuildingResident>> loadListByBuildingUuid(@RequestParam("buildingUuid") String buildingUuid) {
        return ActionResult.ok(buildingResidentService.loadListByBuildingUuid(buildingUuid));
    }

    @RequestMapping("/uploadPortrait")
    public ActionResult<?> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "/residentSign");
        if (uploadResult.getSuccess()) {
            BuildingResident buildingResident = new BuildingResident();
            buildingResident.setId(id);
            buildingResident.setSignImgPath(uploadResult.getUploadUriPath());
            buildingResidentService.updateByEntity(buildingResident);
            return ActionResult.ok(uploadResult.getUploadUriPath());
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }

    }

    @Override
    protected CrudService<BuildingResident> getService() {
        return buildingResidentService;
    }
}
