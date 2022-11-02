package com.shopping.wx.controller.sqjy;

import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.SchoolRecruitConference;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.SchoolRecruitConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SchoolRecruitConferenceController
 * @Description TODO
 * @Author zyw
 * @Date 2022/7/12
 **/
@RestController
@RequestMapping("/school-recruit-conference")
public class SchoolRecruitConferenceController extends CrudController<SchoolRecruitConference, String> {
    @Autowired
    private SchoolRecruitConferenceService schoolRecruitConferenceService;

    @Override
    protected CrudService<SchoolRecruitConference> getService() {
        return schoolRecruitConferenceService;
    }
}
