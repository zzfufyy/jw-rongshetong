package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Dksq;
import com.shopping.wx.model.Rwjd;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/jdcx")
public class jdcxController {

    @Autowired
    private DB db;

    //查询我的贷款进度
    @RequestMapping(value = "/searchJdcx", method = RequestMethod.GET)
    public JSONObject searchJdcx(String rwid){
        JSONObject json = new JSONObject();
        Example example = new Example(Rwjd.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("rwid",rwid);
        example.setOrderByClause("datetime");
        List<Rwjd> rwjdList = db.selectAllByExample(Rwjd.class,example);

        json.put("rwjdList",rwjdList);
        return json;
    }

}
