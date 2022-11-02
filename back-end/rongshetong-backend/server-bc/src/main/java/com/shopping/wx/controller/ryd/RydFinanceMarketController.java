package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.service.ryd.RydFinanceMarketService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 金融超市控制器
 *
 * @author csjing
 */
@Log4j
@RestController
@RequestMapping("/ryd/FinanceMarket")
public class RydFinanceMarketController {

    @Autowired
    RydFinanceMarketService financeMarketService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ActionResult<JSONObject> list(String paramString) {
        try {
            JSONObject condition = JSONObject.parseObject(paramString);
            return ActionResult.ok(financeMarketService.queryByConditionPaged(condition));
        } catch (Exception exception) {
            log.error(exception);
            exception.printStackTrace();
            return ActionResult.error();
        }
    }

    @RequestMapping("/allOrganization")
    public ActionResult<JSONObject> allOrganization() {
        return ActionResult.ok(financeMarketService.queryAllOrganization());
    }

    @RequestMapping("/allTargetUserType")
    public ActionResult<JSONObject> allTargetUserType() {
        return ActionResult.ok(financeMarketService.queryAllTargetUserType());
    }

    @RequestMapping("/info")
    public ActionResult<JSONObject> info(String productId) {
        return ActionResult.ok(financeMarketService.queryProductById(productId));
    }
}
