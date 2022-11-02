package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.FormSubject;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.UserForForm;
import com.shopping.wx.pojo.dto.user_for_form.UserForFormDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_for_form.ChoiceSelectListVo;
import com.shopping.wx.pojo.vo.user_for_form.UserForFormSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.FormSubjectService;
import com.shopping.wx.service.community_recruitment.UserForFormService;
import com.shopping.wx.util.UUIDUtils;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName UserForFormController
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@RestController
@RequestMapping("/user-for-form")
public class UserForFormController extends CrudController<UserForForm, String> {
    @Autowired
    private UserForFormService userForFormService;
    @Autowired
    private UploadService uploadService;

    @PostMapping("/page")
    public ActionResult<PageInfo> page(@RequestBody PagingParam<UserForFormSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userForFormService.page(pagingParam))
        );
    }

    @PostMapping("/pageForUploadImg")
    public ActionResult<PageInfo> pageForUploadImg(@RequestBody PagingParam<UserForFormSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userForFormService.pageForUploadImg(pagingParam))
        );
    }

    @PostMapping("/pageForSign")
    public ActionResult<PageInfo> pageForSign(@RequestBody PagingParam<UserForFormSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userForFormService.pageForSign(pagingParam))
        );
    }

    @PostMapping("/pageDetailUserForm")
    public ActionResult<PageInfo> pageDetailUserForm(@RequestBody PagingParam<UserForFormSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userForFormService.pageDetailUserForm(pagingParam))
        );
    }

    @PostMapping("/loadListByCondition")
    public ActionResult<List<UserForForm>> loadListByCondition(@RequestBody UserForFormSearchCondition condition) {
        return ActionResult.ok(userForFormService.loadListByCondition(condition));
    }

    @PostMapping("/loadChoiceSelectListByCondition")
    public ActionResult<List<ChoiceSelectListVo>> loadChoiceSelectListByCondition(@RequestBody UserForFormSearchCondition condition) {
        // 丰富optionTitle字段
        return ActionResult.ok(userForFormService.loadChoiceSelectListByCondition(condition));
    }

    @GetMapping("/infoForSelection")
    public ActionResult infoForSelection(@RequestParam String id) {
        // TODO
        return null;
    }

    @PostMapping("/addList")
    public ActionResult<Boolean> addList(@RequestBody List<UserForForm> listUserForForm) {

        userForFormService.insertByList(listUserForForm.stream().map(v -> {
            v.setId(UUIDUtils.timeBaseUUID32());
            return v;
        }).collect(Collectors.toList()));
        return ActionResult.ok();
    }

    @PostMapping(value = "/addUploadImgSubject", produces = "application/json")
    public ActionResult<Boolean> addUploadImgSubject(HttpServletRequest request, MultipartFile file) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String tmpImgPath = request.getParameter(QueryUtils.getFieldName(UserForForm::getUploadImg));
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "/form");
        String realPath = "";
        if (uploadResult.getSuccess()) {
            realPath = uploadResult.getUploadUriPath();
        }
        UserForForm userForForm = new UserForForm();
        userForForm.setComponentType(Integer.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getComponentType))));
        userForForm.setSubjectType(Integer.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getSubjectType))));
        userForForm.setUserOpenid(String.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getUserOpenid))));
        userForForm.setSubjectAnswer(String.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getSubjectAnswer))));
        userForForm.setUploadImg(realPath);
        userForForm.setOptionUuid(String.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getOptionUuid))));
        userForForm.setSubjectUuid(String.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getSubjectUuid))));
        userForForm.setFormUuid(String.valueOf(request.getParameter(QueryUtils.getFieldName(UserForForm::getFormUuid))));
        userForFormService.insert(userForForm);
        return ActionResult.ok();
    }

    @PostMapping("/countByCondition")
    public ActionResult<Integer> countByCondition(@RequestBody UserForFormSearchCondition userForFormSearchCondition) {
        return ActionResult.ok(
                userForFormService.countByCondition(userForFormSearchCondition)
        );
    }

    @Override
    protected CrudService<UserForForm> getService() {
        return userForFormService;
    }
}
