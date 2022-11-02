package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FormSubject;
import com.shopping.wx.model.FormSubjectOption;
import com.shopping.wx.pojo.dto.form_subject_option.ChoiceSelectDTO;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FormSubjectOptionService
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
public interface FormSubjectOptionService extends CrudService<FormSubjectOption> {


    /**
     * 插入实体列表
     *
     * @param listFormSubjectOption
     * @return java.lang.Integer
     */
    Integer insertByList(List<FormSubjectOption> listFormSubjectOption);

    List<FormSubjectOption> listBySubjectUuidList(List<String> listSubjectUuid);


    List<ChoiceSelectDTO> listChoiceSelectBySubjectUuid(String subjectUuid);
}
