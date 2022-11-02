package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Nsgrb;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/nsgrb")
public class NsgrbController {
    @Autowired
    private DB db;

    //查询纳税光荣榜数据
    @RequestMapping(value = "/searchNsgrb", method = RequestMethod.GET)
    public JSONObject searchNsgrb(){
        JSONObject json = new JSONObject();
        Nsgrb nsgrb = new Nsgrb();
        List<Nsgrb> NsgrbList = db.select(nsgrb);
        json.put("nsgrb",NsgrbList);
        return json;
    }

}
