package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.model.RecommendWeight;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecommendWeightService;
import org.springframework.stereotype.Service;

/**
 * @ClassName RecommendWeightServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/14
 **/
@Service
public class RecommendWeightServiceImpl extends CrudServiceImpl<RecommendWeight> implements RecommendWeightService {

    @Override
    public ActionResult<Boolean> incrCountView(String candidateOpenid, String categoryUuid) {
        // 查询是否存在
        RecommendWeight recommendWeight = new RecommendWeight();
        recommendWeight.setCandidateOpenid(candidateOpenid);
        recommendWeight.setCategoryUuid(categoryUuid);
        if(selectCount(recommendWeight) == Long.valueOf(1)){

        }else{

        }
        return null;
    }
}
