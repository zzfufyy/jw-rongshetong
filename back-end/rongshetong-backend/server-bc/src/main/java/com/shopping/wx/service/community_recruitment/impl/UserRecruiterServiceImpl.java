package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IUserRecruiterMapper;
import com.shopping.wx.mapper.UserRecruiterMapper;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.user_recruiter.UserRecruiterPlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_recruiter.UserRecruiterSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.mapper.weekend.reflection.Reflections;

import java.util.List;

/**
 * @ClassName UserRecruiterServiceImpl
 * @Description 招聘人实现接口
 * @Author zyw
 * @Date 2022/3/14
 **/
@Service
public class UserRecruiterServiceImpl extends CrudServiceImpl<UserRecruiter> implements UserRecruiterService {
    @Autowired
    private UserRecruiterMapper userRecruiterMapper;
    @Autowired
    private IUserRecruiterMapper iUserRecruiterMapper;

    @Override
    public int updateCompanyUuid(String openid, String companyUuid) {
        UserRecruiter userRecruiter = new UserRecruiter();
        userRecruiter.setId(openid);
        userRecruiter.setCompanyUuid(companyUuid);
        return db.update(userRecruiter);
    }

    @Override
    public List<UserRecruiter> page(PagingParam<UserRecruiterSearchCondition> pagingParam) {
        UserRecruiterSearchCondition condition = pagingParam.getCondition();

        return pagingByBuilder(pagingParam.getPage(),
                builder -> {
                    if (condition == null) {
                        return;
                    }
                    builder.andWhere(
                            new WhereClauseBuilder<UserRecruiter>()
                                    // 未删除
                                    .notDeleted()
                                    // 姓名 模糊
                                    .notEmptyLike(condition.getRealName(), UserRecruiter::getRealName)
                                    // 电话号码
                                    .notEmptyEq(condition.getTelephone(), UserRecruiter::getTelephone)
                                    // 公司id
                                    .notEmptyEq(condition.getCompanyUuid(), UserRecruiter::getCompanyUuid)

                                    .getSqls()
                    );
                    // 默认时间降序排列
                    builder.orderByDesc(QueryUtils.getFieldName(UserRecruiter::getCreateTime));
                }
        );
    }

    @Override
    public UserRecruiterPlus infoPlus(String id) {
        return iUserRecruiterMapper.infoPlus(id);
    }


}

