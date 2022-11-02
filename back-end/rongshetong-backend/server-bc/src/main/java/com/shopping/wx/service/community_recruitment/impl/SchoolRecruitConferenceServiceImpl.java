package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.SchoolRecruitConferenceMapper;
import com.shopping.wx.model.SchoolRecruitConference;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.SchoolRecruitConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SchoolRecruitConferenceServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/07/12
 **/
@Service
public class SchoolRecruitConferenceServiceImpl extends CrudServiceImpl<SchoolRecruitConference> implements SchoolRecruitConferenceService {
    @Autowired
    private SchoolRecruitConferenceMapper schoolRecruitConferenceMapper;
}
