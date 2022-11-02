package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.About;
import com.shopping.wx.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/gywm")
public class gywmController {

    @Autowired
    private DB db;

    //关于我们数据
    @RequestMapping(value = "/searchAbout1", method = RequestMethod.GET)
    public JSONObject searchAbout(){
        JSONObject json = new JSONObject();
        About about = new About();
        List<About> aboutList = db.select(about);
        json.put("about",aboutList);
        return json;
    }


}
