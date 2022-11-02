package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.form_information.FormInformationPlus;
import com.shopping.wx.pojo.dto.form_information.FormInformationWithStatisticDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IFormInformationMapper {

    List<FormInformationPlus> listNormalByCondition(@Param("userOpenid")String userOpenid, @Param("communityUuid")String communityUuid);

    List<FormInformationPlus> listUnpublishedByCondition(@Param("userOpenid")String userOpenid, @Param("communityUuid")String communityUuid);

    List<FormInformationPlus> listPublishedByCondition(@Param("communityUuid") String communityUuid);

    FormInformationWithStatisticDTO infoWithStatistic(@Param("id") String id);

}
