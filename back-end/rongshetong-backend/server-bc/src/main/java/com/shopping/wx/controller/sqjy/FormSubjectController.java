package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.FormInformation;
import com.shopping.wx.model.FormSubject;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_subject.FormSubjectSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FormInformationService;
import com.shopping.wx.service.community_recruitment.FormSubjectService;
import com.shopping.wx.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @ClassName FormSubjectController
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@RestController
@RequestMapping("/form-subject")
public class FormSubjectController extends CrudController<FormSubject, String> {
    @Autowired
    private FormSubjectService formSubjectService;

    @RequestMapping("/addList")
    public ActionResult<Map<Integer, String>> addList(@RequestBody List<FormSubject> listFormSubject) {
        Map<Integer, String> subjectUuidMap = new HashMap<>();
        List<FormSubject> operationList = new ArrayList<>();
        // 删除 此formUuid对应的题目
        if(listFormSubject.size() != 0){
            FormSubject deleteFormSubject = new FormSubject();
            deleteFormSubject.setFormUuid(listFormSubject.get(0).getFormUuid());
            formSubjectService.delete(deleteFormSubject);
        }
        // 解析list
        for (FormSubject formSubject : listFormSubject) {
            String tmpUuid32 = UUIDUtils.timeBaseUUID32();
            formSubject.setId(tmpUuid32);
            subjectUuidMap.put(formSubject.getSubjectOrder(), tmpUuid32);
            operationList.add(formSubject);
        }
        formSubjectService.insertByList(operationList);
        return ActionResult.ok(subjectUuidMap);
    }

    @GetMapping("listByFormUuid")
    public ActionResult<List<FormSubject>> listByFormUuid(@RequestParam("formUuid") String formUuid) {
        FormSubject queryFormSubject = new FormSubject();
        queryFormSubject.setFormUuid(formUuid);
        return ActionResult.ok(formSubjectService.select(queryFormSubject));
    }


    @PostMapping("page")
    public  ActionResult<PageInfo<FormSubject>> page(@RequestBody PagingParam<FormSubjectSearchCondition> pagingParam){
        return ActionResult.ok(PageInfo.of(formSubjectService.page(pagingParam)));
    }




    @Override
    protected CrudService<FormSubject> getService() {
        return formSubjectService;
    }
}
