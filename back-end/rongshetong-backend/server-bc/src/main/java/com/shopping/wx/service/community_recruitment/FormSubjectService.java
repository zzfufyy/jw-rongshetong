package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FormSubject;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_subject.FormSubjectSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FormSubjectService
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
public interface FormSubjectService extends CrudService<FormSubject> {

    /**
     * 插入实体列表
     *
     * @param listFormSubject
     * @return java.lang.Integer
     */
    Integer insertByList(List<FormSubject> listFormSubject);


    List<FormSubject> page(PagingParam<FormSubjectSearchCondition> pagingParam);
}
