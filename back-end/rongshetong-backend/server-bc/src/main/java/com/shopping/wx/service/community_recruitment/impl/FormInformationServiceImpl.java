package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IFormInformationMapper;
import com.shopping.wx.mapper.FormInformationMapper;
import com.shopping.wx.model.FormInformation;
import com.shopping.wx.pojo.dto.form_information.FormInformationPlus;
import com.shopping.wx.pojo.dto.form_information.FormInformationWithStatisticDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_information.FormInformationSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FormInformationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FormInformationServiceImpl
 * @Description 自定义表信息服务 实现类
 * @Author zyw
 * @Date 2022/6/14
 **/
@Service
public class FormInformationServiceImpl extends CrudServiceImpl<FormInformation> implements FormInformationService {
    @Autowired
    FormInformationMapper formInformationMapper;
    @Autowired
    IFormInformationMapper iFormInformationMapper;
    @Override
    public List<FormInformationPlus> pageNormal(PagingParam<FormInformationSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        FormInformationSearchCondition formInformationSearchCondition = pagingParam.getCondition();
        return iFormInformationMapper.listNormalByCondition(formInformationSearchCondition.getUserOpenid(), formInformationSearchCondition.getCommunityUuid());
    }

    @Override
    public List<FormInformationPlus> pageUnpublished(PagingParam<FormInformationSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        FormInformationSearchCondition formInformationSearchCondition = pagingParam.getCondition();
        return iFormInformationMapper.listUnpublishedByCondition(formInformationSearchCondition.getUserOpenid(), formInformationSearchCondition.getCommunityUuid());
    }

    @Override
    public List<FormInformationPlus> pagePublished(PagingParam<FormInformationSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        FormInformationSearchCondition formInformationSearchCondition = pagingParam.getCondition();
        return iFormInformationMapper.listPublishedByCondition(formInformationSearchCondition.getCommunityUuid());
    }

    @Override
    public FormInformationWithStatisticDTO infoWithStatistic(String id) {
        return iFormInformationMapper.infoWithStatistic(id);
    }

}
