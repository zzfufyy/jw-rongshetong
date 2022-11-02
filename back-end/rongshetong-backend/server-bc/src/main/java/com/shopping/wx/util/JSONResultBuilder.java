package com.shopping.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.result.ActionResult;

/**
 * 简化 Controller 层返回值 ActionResult 的使用
 */
public class JSONResultBuilder {

    private final JSONObject data = new JSONObject();

    static final String DATA_KEY = "data";
    static final String PAGE_INFO_KEY = "pageInfo";

    public JSONResultBuilder put(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public JSONResultBuilder data(Object value) {
        return put(DATA_KEY, value);
    }

    public JSONResultBuilder pageInfo(Object value) {
        return put(PAGE_INFO_KEY, value);
    }

    public ActionResult<JSONObject> build() {
        return ActionResult.ok(data);
    }
}
