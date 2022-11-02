package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.FormSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IFormSubjectMapper {


    int insertByList(@Param("listFormSubject") List<FormSubject> listFormSubject);

}
