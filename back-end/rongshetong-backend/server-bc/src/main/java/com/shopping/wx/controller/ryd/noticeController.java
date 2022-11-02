package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Notice;
import com.shopping.wx.model.Organ;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/notice")
public class noticeController {

    @Autowired
    private DB db;

    //查询公告数据
    @RequestMapping(value = "/searchNotice", method = RequestMethod.GET)
    public JSONObject searchNotice(String id){
        JSONObject json = new JSONObject();
        List<Notice> noticeList = new ArrayList<>(10);
        Example example = new Example(Organ.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("bankid",id);
        List<Organ> organList =db.selectAllByExample(Organ.class,example);
        for (int i = 0; i < organList.size(); i++) {
            Example example1 = new Example(Notice.class);
            Example.Criteria criteria1 =example1.createCriteria();
            criteria1.andEqualTo("xgyhid",organList.get(i).getId());
            example1.setOrderByClause("fbtime desc");
            List<Notice> noticeList1 =db.selectAllByExample(Notice.class,example1);
            noticeList.addAll(noticeList1);
        }

        json.put("notice",noticeList);
        return json;
    }


    //查询银行数据
    @RequestMapping(value = "/searchOrgan", method = RequestMethod.GET)
    public JSONObject searchOrgan(String id){
        JSONObject json = new JSONObject();
        List<Organ> noticeList = new ArrayList<>();
        Example example = new Example(Organ.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("bankid",id);
        example.setOrderByClause("dkye desc");
        List<Organ> organList =db.selectAllByExample(Organ.class,example);


        json.put("organList",organList);
        return json;
    }

}
