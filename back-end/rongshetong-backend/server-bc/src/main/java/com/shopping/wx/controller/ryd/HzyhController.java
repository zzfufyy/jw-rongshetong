package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.About;
import com.shopping.wx.model.Bankinfo;
import com.shopping.wx.model.Dksq;
import com.shopping.wx.model.Organ;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/hzyh")
public class HzyhController {
    @Autowired
    private DB db;

    //合作银行数据
    @RequestMapping(value = "/searchHzyh", method = RequestMethod.GET)
    public JSONObject searchHzyh(){
        JSONObject json = new JSONObject();
        Bankinfo bankinfo = new Bankinfo();
        List<Bankinfo> bankinfoList = db.select(bankinfo);
        json.put("hzyh", bankinfoList);
        return json;
    }

    //根据id查询合作银行
    @RequestMapping(value = "/searchHzyhById", method = RequestMethod.GET)
    public JSONObject searchHzyhById(String orgid) throws ParseException {
        JSONObject json = new JSONObject();
        Organ organ = db.selectById(orgid, Organ.class);
        Bankinfo bankinfo =db.selectById(organ.getBankid(),Bankinfo.class);
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("yhwdid",organ.getId());
        criteria.andBetween("dksj",getFirstDay(new Date()),new Date());
        criteria.andNotEqualTo("status","0");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        Map map = new HashMap();
        map.put("bankinfo",bankinfo);
        map.put("organ",organ);
        map.put("dksqList",dksqList);
        json.put("data", map);
        return json;
    }

    public static String getFirstDay(Date date) throws ParseException {

// 获取截止当前天数

        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        String time = format.format(date);

        String firstDay = time + "-01-01";

        return firstDay;

    }

}
