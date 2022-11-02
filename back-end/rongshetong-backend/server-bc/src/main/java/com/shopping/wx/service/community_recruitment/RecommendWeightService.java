package com.shopping.wx.service.community_recruitment;


import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.model.RecommendWeight;
import com.shopping.wx.service.basic.CrudService;

/**
 * @ClassName RecommendWeightService
 * @Description 权重接口
 * @Author zyw
 * @Date 2022/3/14
 **/
public interface RecommendWeightService extends CrudService<RecommendWeight> {


    ActionResult<Boolean> incrCountView(String candidateOpenid, String categoryUuid);
}
