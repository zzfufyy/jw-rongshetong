package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Ptjs;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tesejr")
public class TesejrController {

    @Autowired
    private DB db;

    //查询特色金融数据
    @RequestMapping(value = "/searchTesejr", method = RequestMethod.GET)
    public JSONObject searchTesejr(){
        JSONObject json = new JSONObject();
        Ptjs ptjs = new Ptjs();
        List<Ptjs> PtjsList = db.select(ptjs);
        json.put("tesejr",PtjsList);
        return json;
    }

  /*  //根据id查询金融数据
    @RequestMapping(value = "/selectTesejrById", method = RequestMethod.GET)
    public JSONObject selectTesejrById(String id){
        JSONObject json = new JSONObject();
        Ptjs ptjs = db.selectById(id,Ptjs.class);
        json.put("ptjs",ptjs);
        return json;
    }*/


    /*   //查询特色金融数据
    @RequestMapping(value = "/searchTesejr", method = RequestMethod.GET)
    public JSONObject searchtesejr() throws IOException {
        JSONObject json = new JSONObject();
        Ptjs ptjs = new Ptjs();
        List<Ptjs> ptjslist = db.select(ptjs);
        json.put("tesejr",ptjslist);
        return json;
    }*/
}
