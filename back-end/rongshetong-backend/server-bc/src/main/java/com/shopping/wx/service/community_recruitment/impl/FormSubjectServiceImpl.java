package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IFormSubjectMapper;
import com.shopping.wx.mapper.FormSubjectMapper;
import com.shopping.wx.model.FormSubject;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_subject.FormSubjectSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FormSubjectService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @ClassName FormSubjectServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@Service
public class FormSubjectServiceImpl extends CrudServiceImpl<FormSubject> implements FormSubjectService {
    @Autowired
    FormSubjectMapper formSubjectMapper;
    @Autowired
    IFormSubjectMapper iFormSubjectMapper;

    @Override
    public Integer insertByList(List<FormSubject> listFormSubject) {
        return iFormSubjectMapper.insertByList(listFormSubject);
    }

    @Override
    public List<FormSubject> page(PagingParam<FormSubjectSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        FormSubjectSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                .andEqualTo(QueryUtils.getFieldName(FormSubject::getFormUuid),condition.getFormUuid())

        );
        builder.orderByAsc(QueryUtils.getFieldName(FormSubject::getSubjectOrder));
        return formSubjectMapper.selectByExample(builder.build());
    }

}
