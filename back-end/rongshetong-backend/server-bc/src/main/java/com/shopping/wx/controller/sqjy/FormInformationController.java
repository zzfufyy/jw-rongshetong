package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.FormInformation;
import com.shopping.wx.pojo.dto.form_information.FormInformationWithStatisticDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_information.FormInformationSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.FormInformationService;
import com.shopping.wx.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName FormInformationController
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@RestController
@RequestMapping("/form-information")
public class FormInformationController extends CrudController<FormInformation, String> {
    @Autowired
     FormInformationService formInformationService;

    @PostMapping("/add")
    public ActionResult<String> add(@RequestBody FormInformation formInformation){
        String id;
        // 先删除 后增加  使得新增 更新公用此方法
        if(StringUtils.isEmpty(formInformation.getId())){
            id = UUIDUtils.timeBaseUUID32();
            formInformation.setId(id);
        }else{
            id = formInformation.getId();
            FormInformation deleteFormInformation = new FormInformation();
            deleteFormInformation.setId(id);
            formInformationService.delete(deleteFormInformation);
        }
        formInformation.setBeginTime(new Date());
        formInformationService.insert(formInformation);
        return ActionResult.ok(id);
    }

    @GetMapping("/info")
    public ActionResult<FormInformation> info(@RequestParam("id") String id){
        return ActionResult.ok(formInformationService.selectById(id));
    }
    @GetMapping("/delete")
    public ActionResult<Boolean> delete(@RequestParam("id")String id){
        return ActionResult.ok(formInformationService.deleteById(id) == 1);
    }

    @PostMapping("/pageNormal")
    public ActionResult<PageInfo> pageNormal(@RequestBody PagingParam<FormInformationSearchCondition> pagingParam){

        return ActionResult.ok(
                PageInfo.of(formInformationService.pageNormal(pagingParam))
        );
    }

    @PostMapping("/pageUnpublished")
    public ActionResult<PageInfo> pageUnpublished(@RequestBody PagingParam<FormInformationSearchCondition> pagingParam){

        return ActionResult.ok(
                PageInfo.of(formInformationService.pageUnpublished(pagingParam))
        );
    }

    @PostMapping("/pagePublished")
    public ActionResult<PageInfo> pagePublished(@RequestBody PagingParam<FormInformationSearchCondition> pagingParam){
        return ActionResult.ok(
                PageInfo.of(formInformationService.pagePublished(pagingParam))
        );
    }

    @GetMapping("/publish")
    public ActionResult<Boolean> publish(@RequestParam String id){
        FormInformation formInformation  = new FormInformation();
        formInformation.setId(id);
        formInformation.setStatus(1);
        formInformationService.update(formInformation);
        return ActionResult.ok(formInformationService.update(formInformation) == 1);
    }

    @GetMapping("/unpublish")
    public ActionResult<Boolean> unpublish(@RequestParam String id){
        FormInformation formInformation  = new FormInformation();
        formInformation.setId(id);
        formInformation.setStatus(-1);
        formInformationService.update(formInformation);
        return ActionResult.ok(formInformationService.update(formInformation) == 1);
    }

    @GetMapping("/infoWithFilled")
    public ActionResult<FormInformationWithStatisticDTO> infoWithFilled(@RequestParam String id){
        return ActionResult.ok(formInformationService.infoWithStatistic(id));
    }


    @Override
    protected CrudService<FormInformation> getService() {
        return formInformationService;
    }

}
