package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.ResidentForPerson;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.ResidentForPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ResidentForPersonController
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/27
 **/
@RequestMapping("/resident-for-person")
@RestController
public class ResidentForPersonController extends CrudController<ResidentForPerson, String> {
    @Autowired
    private ResidentForPersonService residentForPersonService;
    @Autowired
    private UploadService uploadService;

    @PostMapping("/add")
    public ActionResult<?> add(@RequestBody ResidentForPerson residentForPerson) {
        // 当前居户 名字重复的删除
        ResidentForPerson deleteResidentPerson = new ResidentForPerson();
        deleteResidentPerson.setResidentUuid(residentForPerson.getResidentUuid());
        deleteResidentPerson.setPersonName(residentForPerson.getPersonName());
        deleteResidentPerson.setPersonType(residentForPerson.getPersonType());
        residentForPersonService.delete(deleteResidentPerson);
        return ActionResult.ok(insert(residentForPerson));
    }

    @RequestMapping("/delete")
    public ActionResult<?> delete(@RequestParam String id) {
        ResidentForPerson deleteResidentForPerson = new ResidentForPerson();
        deleteResidentForPerson.setId(id);
        return residentForPersonService.delete(deleteResidentForPerson) == 1 ? ActionResult.ok() : ActionResult.error();
    }

    @GetMapping("/info")
    public ActionResult<ResidentForPerson> info(@RequestParam String id) {
        return ActionResult.ok(residentForPersonService.selectById(id));
    }

    @PostMapping("/modify")
    public ActionResult<?> modify(@RequestBody ResidentForPerson residentForPerson){
        return ActionResult.ok(residentForPersonService.update(residentForPerson));
    }

    @GetMapping("/listByResidentUuid")
    public ActionResult<List<ResidentForPerson>> listByResidentUuid(
            @RequestParam String residentUuid,
            @RequestParam(required = false) Integer personType) {
        return ActionResult.ok(residentForPersonService.listByResidentUuid(residentUuid, personType));
    }

    @Override
    protected CrudService<ResidentForPerson> getService() {
        return residentForPersonService;
    }
}
