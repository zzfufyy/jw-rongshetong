package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IUserForFormMapper;
import com.shopping.wx.mapper.UserForFormMapper;
import com.shopping.wx.model.UserForForm;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_for_form.ChoiceSelectListVo;
import com.shopping.wx.pojo.vo.user_for_form.DetailUserFormVo;
import com.shopping.wx.pojo.vo.user_for_form.SignStatisticVo;
import com.shopping.wx.pojo.vo.user_for_form.UserForFormSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserForFormService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @ClassName UserForFormServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
@Service
public class UserForFormServiceImpl extends CrudServiceImpl<UserForForm> implements UserForFormService {
    @Autowired
    private UserForFormMapper userForFormMapper;

    @Autowired
    private IUserForFormMapper iUserForFormMapper;


    @Override
    public Integer insertByList(List<UserForForm> listUserForForm) {
        return iUserForFormMapper.insertByList(listUserForForm);
    }

    @Override
    public List<UserForForm> page(PagingParam<UserForFormSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        UserForFormSearchCondition condition = pagingParam.getCondition();

        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(UserForForm::getSubjectUuid), condition.getSubjectUuid())
        );
        builder.orderByDesc(QueryUtils.getFieldName(UserForForm::getCreateTime));
        return userForFormMapper.selectByExample(builder.build());
    }

    @Override
    public List<UserForForm> pageForUploadImg(PagingParam<UserForFormSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        UserForFormSearchCondition condition = pagingParam.getCondition();
        return iUserForFormMapper.pageForUploadImg(condition.getSubjectUuid());
    }

    @Override
    public List<SignStatisticVo> pageForSign(PagingParam<UserForFormSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        UserForFormSearchCondition condition = pagingParam.getCondition();
        return iUserForFormMapper.pageForSign(condition.getSubjectUuid());
    }

    @Override
    public List<DetailUserFormVo> pageDetailUserForm(PagingParam<UserForFormSearchCondition> pagingParam) {
        startPage(pagingParam.getPage());
        UserForFormSearchCondition condition = pagingParam.getCondition();
        return iUserForFormMapper.pageDetailUserForm(condition.getFormUuid());
    }

    @Override
    public List<UserForForm> loadListByCondition(UserForFormSearchCondition condition) {
        String userOpenid = condition.getUserOpenid();
        String subjectUuid = condition.getSubjectUuid();
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                        .andEqualTo(QueryUtils.getFieldName(UserForForm::getUserOpenid), userOpenid)
                        .andEqualTo(QueryUtils.getFieldName(UserForForm::getSubjectUuid), subjectUuid)
        );
        return userForFormMapper.selectByExample(builder.build());
    }

    @Override
    public List<ChoiceSelectListVo> loadChoiceSelectListByCondition(UserForFormSearchCondition condition) {
        return iUserForFormMapper.loadChoiceSelectListByCondition(
                condition.getSubjectUuid(),
                condition.getUserOpenid()
        );
    }

    @Override
    public Integer countByCondition(UserForFormSearchCondition condition) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                Sqls.custom()
                .andEqualTo(QueryUtils.getFieldName(UserForForm::getFormUuid), condition.getFormUuid())
                .andEqualTo(QueryUtils.getFieldName(UserForForm::getUserOpenid), condition.getUserOpenid())
        );
        return userForFormMapper.selectCountByExample(builder.build());
    }


}
