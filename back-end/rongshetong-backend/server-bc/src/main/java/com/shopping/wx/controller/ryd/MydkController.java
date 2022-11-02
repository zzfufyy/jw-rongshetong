package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.model.*;
import com.shopping.wx.service.ryd.RydMyLoanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/mydk")
public class MydkController {
    @Autowired
    private DB db;

    @Autowired
    private RydMyLoanService myLoanService;

    //查询我的贷款数据
    @RequestMapping(value = "/searchMydk", method = RequestMethod.GET)
    public JSONObject searchMydk(String openid) {
        JSONObject json = new JSONObject();
        Dksq dksq = new Dksq();
        dksq.setYl1(openid);
        List<Dksq> DksqList = db.select(dksq);

        for (int i = 0; i < DksqList.size(); i++) {
            String type = DksqList.get(i).getStatus();
            if (type.equals("0")) {
                DksqList.get(i).setStatus("待审核");
            } else if (type.equals("1")) {
                DksqList.get(i).setStatus("通过");
            } else {
                DksqList.get(i).setStatus("不通过");
            }
        }

        json.put("mydk", DksqList);
        return json;
    }

    //根据银行id查询我的贷款数据
    @RequestMapping(value = "/searchMydkByBankid", method = RequestMethod.GET)
    public JSONObject searchMydkByBankid(String openid, String yhid) {
        JSONObject json = new JSONObject();
        Dksq dksq = new Dksq();
        dksq.setYl1(openid);
        if (!yhid.equals("0") && !yhid.equals("undefined")) {
            dksq.setDkbankid(yhid);
        }

        List<Dksq> DksqList = db.select(dksq);
        List<Bankinfo> bankinfos = new ArrayList<>();
        for (int i = 0; i < DksqList.size(); i++) {

            Bankinfo bankinfo = db.selectById(DksqList.get(i).getDkbankid(), Bankinfo.class);
            String type = DksqList.get(i).getStatus();
            DksqList.get(i).setDyw(bankinfo.getBankimg3());
            DksqList.get(i).setYl2(bankinfo.getBankimg4());
            if (type.equals("0")) {
                DksqList.get(i).setStatus("已申请");
            } else if (type.equals("1")) {
                DksqList.get(i).setStatus("已通过");
            } else if (type.equals("2")) {
                DksqList.get(i).setStatus("已受理");
            } else if (type.equals("3")) {
                DksqList.get(i).setStatus("已放款");
            } else {
                DksqList.get(i).setStatus("已退回");
            }
        }
        json.put("bankinfos", bankinfos);
        json.put("mydk", DksqList);
        return json;
    }


    @RequestMapping("/myLoanAuthorization")
    public ActionResult<JSONObject> myLoanAuthorization(String openId) {
        List<Bankqx> queryResult = myLoanService.queryAuthorizationByOpenId(openId);
        JSONObject result = new JSONObject();
        // 表中有对应的数据，是管理员
        if (!queryResult.isEmpty()) {
            result.put("data", true);
            return ActionResult.ok(result);
        }
        // 不是管理员
        else {
            result.put("data", false);
            return ActionResult.ok(result);
        }
    }

    @RequestMapping("/allLoans")
    public ActionResult<JSONObject> allLoans(Integer currentPage, Integer pageSize) {
        Page page = new Page(currentPage, pageSize);

        if (!Page.validate(page)) {
            return ActionResult.error("分页参数错误");
        }

        List<Dksq> queryResult = myLoanService.queryAllLoansPaged(page);

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Dksq dksq : queryResult) {
            List<Rwjd> taskProgressList = myLoanService.queryTaskProgress(dksq.getId());
            // 转换格式，便于前端处理
            taskProgressList.forEach((task) -> task.setRwid(dateFormat.format(task.getDatetime())));

            JSONObject extraData = new JSONObject();
            extraData.put("extra",taskProgressList);
            dksq.setYl2(extraData.toJSONString());
        }

        JSONObject result = new JSONObject();
        result.put("data", queryResult);
        result.put("pageInfo", PageInfo.of(queryResult));

        return ActionResult.ok(result);
    }
}
