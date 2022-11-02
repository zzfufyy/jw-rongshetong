package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.BcUserWx;
import com.shopping.wx.service.basic.CrudService;

/**
 * @author ljy
 * @date 2022-03-22 10:22
 */
public interface UserService extends CrudService<BcUserWx> {
    /**
     * 通过 openid 查找唯一的用户
     *
     * @param openid openid
     * @return 用户（可能为 null）
     */
    BcUserWx selectByOpenid(String openid);
}
