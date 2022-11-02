package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.UserForForm;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_for_form.ChoiceSelectListVo;
import com.shopping.wx.pojo.vo.user_for_form.DetailUserFormVo;
import com.shopping.wx.pojo.vo.user_for_form.SignStatisticVo;
import com.shopping.wx.pojo.vo.user_for_form.UserForFormSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName UserForFormService
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
public interface UserForFormService extends CrudService<UserForForm> {

    /***
     * 批量插入
     *
     * @param listUserForForm
     * @return java.lang.Integer
     */
    Integer insertByList(List<UserForForm> listUserForForm);

    /**
     * 基础分页
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.model.UserForForm>
     */
    List<UserForForm> page(PagingParam<UserForFormSearchCondition> pagingParam);


    List<UserForForm> pageForUploadImg(PagingParam<UserForFormSearchCondition> pagingParam);

    List<SignStatisticVo> pageForSign(PagingParam<UserForFormSearchCondition> pagingParam);

    List<DetailUserFormVo> pageDetailUserForm(PagingParam<UserForFormSearchCondition> pagingParam);

    List<UserForForm> loadListByCondition(UserForFormSearchCondition condition);

    List<ChoiceSelectListVo> loadChoiceSelectListByCondition(UserForFormSearchCondition condition);


    Integer countByCondition(UserForFormSearchCondition condition);
}
