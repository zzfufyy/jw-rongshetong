package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.SocialTrainingApplyMapper;
import com.shopping.wx.model.SocialTrainingApply;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.SocialTrainingApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SocialTrainingApplyServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/18
 **/
@Service
public class SocialTrainingApplyServiceImpl extends CrudServiceImpl<SocialTrainingApply> implements SocialTrainingApplyService {
    @Autowired
    private SocialTrainingApplyMapper socialTrainingApplyMapper;
}
