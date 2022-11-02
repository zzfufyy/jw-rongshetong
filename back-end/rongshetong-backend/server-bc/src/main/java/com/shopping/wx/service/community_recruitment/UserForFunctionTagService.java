package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.UserForFunctionTag;
import com.shopping.wx.service.basic.CrudService;

/**
 * @InterfaceName UserForFunctionTag
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/27
 **/
public interface UserForFunctionTagService extends CrudService<UserForFunctionTag> {

    UserForFunctionTag infoByUserAndPath(String userOpenid, String pagePathApply);
}
