package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.FormSubject;
import com.shopping.wx.model.FormSubjectOption;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FormSubjectOptionService;
import com.shopping.wx.service.community_recruitment.FormSubjectService;
import com.shopping.wx.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName FormSubjectOptionController
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@RestController
@RequestMapping("/form-subject-option")

public class FormSubjectOptionController extends CrudController<FormSubjectOption, String> {
    @Autowired
    private FormSubjectOptionService formSubjectOptionService;

    @PostMapping("/addList")
    public ActionResult<Boolean> addList(@RequestBody List<FormSubjectOption> listFormSubjectOption){

        List<FormSubjectOption> insertList = new ArrayList<>();
        // 删除 对应的formUuid 的option
        if (listFormSubjectOption.size() !=0){
            FormSubjectOption deleteFormSubjectOption = new FormSubjectOption();
            deleteFormSubjectOption.setFormUuid(listFormSubjectOption.get(0).getFormUuid());
            formSubjectOptionService.delete(deleteFormSubjectOption);
        }
        for(FormSubjectOption formSubjectOption : listFormSubjectOption){
            String uuid32 = UUIDUtils.timeBaseUUID32();
            formSubjectOption.setId(uuid32);
            insertList.add(formSubjectOption);
        }
        formSubjectOptionService.insertByList(insertList);
        return ActionResult.ok();
    }

    @PostMapping("/listBySubjectUuidList")
    public ActionResult<List<FormSubjectOption>> listBySubjectUuidList(@RequestBody List<String> listSubjectUuid){
        return ActionResult.ok(formSubjectOptionService.listBySubjectUuidList(listSubjectUuid));
    }


    @GetMapping("/listChoiceSelectBySubjectUuid")
    public ActionResult<?> listChoiceSelectBySubjectUuid(@RequestParam("subjectUuid") String subjectUuid){
        return ActionResult.ok(formSubjectOptionService.listChoiceSelectBySubjectUuid(subjectUuid));
    }


    @Override
    protected CrudService<FormSubjectOption> getService() {
        return formSubjectOptionService;
    }
}
