package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.UserForForm;
import com.shopping.wx.pojo.dto.user_recruiter.UserRecruiterPlus;
import com.shopping.wx.pojo.vo.user_for_form.ChoiceSelectListVo;
import com.shopping.wx.pojo.vo.user_for_form.DetailUserFormVo;
import com.shopping.wx.pojo.vo.user_for_form.SignStatisticVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName IUserForFormMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/27
 **/
@Mapper
public interface IUserForFormMapper {
    int insertByList(@Param("listUserForForm") List<UserForForm> listUserForForm);

    List<UserForForm> pageForUploadImg(@Param("subjectUuid") String subjectUuid);

    List<SignStatisticVo> pageForSign(@Param("subjectUuid") String subjectUuid);


    List<DetailUserFormVo> pageDetailUserForm(@Param("formUuid") String formUuid);

    List<ChoiceSelectListVo> loadChoiceSelectListByCondition(@Param("subjectUuid") String subjectUuid,@Param("userOpenid")String userOpenid);
}
