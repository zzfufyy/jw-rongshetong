package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.News;
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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private DB db;
    //查询我的贷款进度
    @RequestMapping(value = "/searchNews", method = RequestMethod.GET)
    public JSONObject searchNews(String name){
        JSONObject json = new JSONObject();
        Example example = new Example(News.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state",1);
        if(!name.equals("")&&name!=null){
            criteria.andLike("xwtitle","%"+name+"%");
        }
        example.setOrderByClause("fbtime desc");
        List<News> newsList = db.selectAllByExample(News.class,example);

        json.put("newsList",newsList);
        return json;
    }
    @RequestMapping(value = "/searchNewById", method = RequestMethod.GET)
    public JSONObject searchNewById(String id){
        JSONObject json = new JSONObject();
        Example example = new Example(News.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state",1);
        criteria.andEqualTo("id",id);
        example.setOrderByClause("fbtime desc");
        List<News> newsList = db.selectAllByExample(News.class,example);
        json.put("newsList",newsList);
        return json;
    }
}
