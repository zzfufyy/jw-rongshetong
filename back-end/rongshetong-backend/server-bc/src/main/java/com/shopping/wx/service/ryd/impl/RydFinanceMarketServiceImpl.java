package com.shopping.wx.service.ryd.impl;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.result.PaginationResult;
import com.shopping.base.foundation.result.QueryResult;

import com.shopping.wx.managed_mapper.ryd.RydBankInfoDAO;
import com.shopping.wx.managed_mapper.ryd.RydOrganDAO;
import com.shopping.wx.managed_mapper.ryd.RydProductDAO;
import com.shopping.wx.model.Bankinfo;
import com.shopping.wx.model.Organ;
import com.shopping.wx.model.Product;
import com.shopping.wx.service.ryd.RydFinanceMarketService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service("rydFinanceMarketService")
public class RydFinanceMarketServiceImpl implements RydFinanceMarketService {

    @Autowired
    RydProductDAO productDAO;

    @Autowired
    RydBankInfoDAO bankInfoDAO;

    @Autowired
    RydOrganDAO organDAO;

    @Override
    public JSONObject queryByConditionPaged(JSONObject condition) throws IllegalArgumentException {

        log.info("=========================START QUERY===============================");
        PaginationResult<Product> result = productDAO.queryByCondition(condition);
        log.info("=========================END QUERY=================================");
        JSONObject ret = new JSONObject();

        List<JSONObject> data = result.getData()
                .stream()
                .map((datum) -> {
                    JSONObject item = new JSONObject();
                    JSONObject extra = new JSONObject();
                    item.put("raw", datum);

                    QueryResult<Organ> queryOrgan = organDAO.queryOrganVOByBankId(datum.getBankid());
                    if (!queryOrgan.getData().isEmpty()) {
                        Organ organVO = queryOrgan.getData().get(0);
                        extra.put("bankName", organVO.getOrganname());
                        Bankinfo bankinfo = bankInfoDAO.findById(organVO.getBankid());
                        if (bankinfo != null) {
                            extra.put("bankImg", bankinfo.getBankimg());
                            extra.put("bankImgs", bankinfo.getSbtime());
                            extra.put("bankId", bankinfo.getId());
                        }
                    }

                    item.put("extraData", extra);
                    return item;
                }).collect(Collectors.toList());

        JSONObject pageInfo = new JSONObject();
        {
            // 总数量
            pageInfo.put("total", result.getTotal());
            // 页面数量
            pageInfo.put("pages", result.getPages());
            // 当前页面索引
            pageInfo.put("currentPage", result.getCurrentPage());
        }

        ret.put("data", data);
        ret.put("pageInfo", pageInfo);

        return ret;
    }

    @Override
    public JSONObject queryProductById(String productId) {
        JSONObject ret = new JSONObject();
        ret.put("data", productDAO.findById(productId));
        return ret;
    }

    @Override
    public JSONObject queryAllOrganization() {
        JSONObject result = new JSONObject();
        result.put("data", organDAO.queryAllOrganVO().getData());
        return result;
    }

    @Override
    public JSONObject queryAllTargetUserType() {
        JSONObject result = new JSONObject();

        result.put("data", productDAO.queryAllTargetUserType().getData());
        return result;
    }
}
