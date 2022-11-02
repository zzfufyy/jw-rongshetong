package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IFormSubjectOptionMapper;
import com.shopping.wx.mapper.FormSubjectOptionMapper;
import com.shopping.wx.model.FormSubjectOption;
import com.shopping.wx.pojo.dto.form_subject_option.ChoiceSelectDTO;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FormSubjectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FormSubjectOptionServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@Service
public class FormSubjectOptionServiceImpl extends CrudServiceImpl<FormSubjectOption> implements FormSubjectOptionService {
    @Autowired
    FormSubjectOptionMapper formSubjectOptionMapper;
    @Autowired
    IFormSubjectOptionMapper iFormSubjectOptionMapper;

    @Override
    public Integer insertByList(List<FormSubjectOption> listFormSubjectOption) {
        return iFormSubjectOptionMapper.insertByList(listFormSubjectOption);
    }

    @Override
    public List<FormSubjectOption> listBySubjectUuidList(List<String> listSubjectUuid) {
        return iFormSubjectOptionMapper.listBySubjectUuidList(listSubjectUuid);
    }

    @Override
    public List<ChoiceSelectDTO> listChoiceSelectBySubjectUuid(String subjectUuid) {
        return iFormSubjectOptionMapper.listChoiceSelectBySubjectUuid(subjectUuid);
    }
}
