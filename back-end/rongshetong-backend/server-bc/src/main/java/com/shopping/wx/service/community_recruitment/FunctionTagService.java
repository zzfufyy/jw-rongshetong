package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FunctionTag;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FunctionTagService
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/26
 **/
public interface FunctionTagService extends CrudService<FunctionTag> {

    /**
     * 默认功能列表排序
     *
     * @param pagePathApply
     * @param communityUuid
     * @return java.util.List<com.shopping.wx.model.FunctionTag>
     */
    List<FunctionTag> listByDefaultOrder(String pagePathApply, String communityUuid);

    /**
     * 列表
     *
     * @param
     * @return java.util.List<com.shopping.wx.model.FunctionTag>
     */
    List<FunctionTag> listAll();
}
