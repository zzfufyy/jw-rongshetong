package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Bankinfo;
import com.shopping.wx.model.Organ;
import com.shopping.wx.model.Qyrz;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/organ")
public class OrganController {
    @Autowired
    private DB db;
    @RequestMapping(value = "/searchorgan", method = RequestMethod.GET)
    public JSONObject searchqy(String yhid) throws IOException {
        Bankinfo bankinfo =db.selectById(yhid, Bankinfo.class);
        Example example = new Example(Organ.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("bankid",yhid);
        List<Organ> organList =db.selectAllByExample(Organ.class,example);
        for (int i = 0; i <organList.size() ; i++) {
            organList.get(i).setYl2(bankinfo.getBankimg());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("organList",organList);
        return  jsonObject;
    }
}
