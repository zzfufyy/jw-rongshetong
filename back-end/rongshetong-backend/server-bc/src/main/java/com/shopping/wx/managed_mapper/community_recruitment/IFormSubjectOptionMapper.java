package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.FormSubject;
import com.shopping.wx.model.FormSubjectOption;
import com.shopping.wx.pojo.dto.form_subject_option.ChoiceSelectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IFormSubjectOptionMapper {

    int insertByList(@Param("listFormSubjectOption") List<FormSubjectOption> listFormSubjectOption);

    List<FormSubjectOption> listBySubjectUuidList(@Param("listSubjectUuid") List<String> listSubjectUuid);

    List<ChoiceSelectDTO> listChoiceSelectBySubjectUuid(@Param("subjectUuid") String subjectUuid);

}
