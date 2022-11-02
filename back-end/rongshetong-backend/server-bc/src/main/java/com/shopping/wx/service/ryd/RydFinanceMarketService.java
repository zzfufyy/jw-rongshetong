package com.shopping.wx.service.ryd;

import com.alibaba.fastjson.JSONObject;

public interface RydFinanceMarketService {

    JSONObject queryByConditionPaged(JSONObject condition) throws IllegalArgumentException;

    JSONObject queryProductById(String productId);

    JSONObject queryAllOrganization();

    JSONObject queryAllTargetUserType();
}
