package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.base.domain.bc.BcUser;
import com.shopping.wx.model.BcUserWx;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserService;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-22 10:22
 */

@Service
@Slf4j
public class UserServiceImpl extends CrudServiceImpl<BcUserWx> implements UserService {

    @Override
    public BcUserWx selectByOpenid(String openid) {

        List<BcUserWx> result = selectAllByExample(Weekend.
                builder(entityClass)
                .where(new WhereClauseBuilder<BcUserWx>()
                       // .notDeleted()
                        .notEmptyEq(openid, BcUserWx::getOpenid)
                        .getSqls()
                ).build()
        );

        if (result.isEmpty()) {
            return null;
        }

        if (result.size() > 1) {
            log.error("There are more than one user with openid [{}]", openid);

        }

        return result.get(0);
    }
}
