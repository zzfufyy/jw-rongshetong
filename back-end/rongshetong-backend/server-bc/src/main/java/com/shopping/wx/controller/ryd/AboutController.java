package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.domain.bc.BcUserWx;
import com.shopping.base.foundation.data.expression.In;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/gywm")
public class AboutController {

    @Autowired
    private DB db;

    //关于我们数据
    @RequestMapping(value = "/searchAbout", method = RequestMethod.GET)
    public JSONObject searchAbout(){
        JSONObject json = new JSONObject();
        About about = new About();
        List<About>aboutList = db.select(about);
        json.put("about",aboutList);
        return json;
    }

    @RequestMapping(value = "/searchimgs", method = RequestMethod.GET)
    public JSONObject searchimgs(){
        JSONObject json = new JSONObject();
        Videoinfo videoinfo = new Videoinfo();
        List<Videoinfo> videoinfoList = db.select(videoinfo);
        json.put("videoinfoList",videoinfoList);
        return json;
    }

    @RequestMapping(value = "/getwxuser", method = RequestMethod.GET)
    public JSONObject getwxuser(String openid){
        JSONObject json = new JSONObject();
        Example example = new Example(UserWxInfo.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("openid",openid);
        List<UserWxInfo> bcUserWxList=db.selectAllByExample(UserWxInfo.class,example);
        json.put("bcUserWxList",bcUserWxList);
        return json;
    }


    @RequestMapping(value = "/getStreets", method = RequestMethod.GET)
    public JSONObject getStreets(){
        JSONObject json = new JSONObject();
        Example example = new Example(Streets.class);
        Example.Criteria criteria =example.createCriteria();
        List<Streets> streetsList=db.selectAllByExample(Streets.class,example);
        json.put("streetsList",streetsList);
        return json;
    }


    @RequestMapping(value = "/setWxuserPhone", method = RequestMethod.GET)
    public JSONObject setWxuserPhone(String openid,String phone){
        JSONObject json = new JSONObject();
        Example example = new Example(UserWxInfo.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("openid",openid);
        List<UserWxInfo> bcUserWxList=db.selectAllByExample(UserWxInfo.class,example);
        UserCandidate userCandidate =db.selectById(openid,UserCandidate.class);
        userCandidate.setTelephone(phone);
        bcUserWxList.get(0).setUnionid(phone);
        db.update(bcUserWxList.get(0));
        db.update(userCandidate);
        return json;
    }


    @RequestMapping(value = "/setWxuserandCompPhone", method = RequestMethod.GET)
    public JSONObject setWxuserandCompPhone(String openid,String phone){
        JSONObject json = new JSONObject();
        Example example = new Example(UserWxInfo.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("openid",openid);
        List<UserWxInfo> bcUserWxList=db.selectAllByExample(UserWxInfo.class,example);
        UserRecruiter userRecruiter =db.selectById(openid,UserRecruiter.class);
        userRecruiter.setTelephone(phone);
        bcUserWxList.get(0).setUnionid(phone);
        db.update(bcUserWxList.get(0));
        db.update(userRecruiter);
        return json;
    }


    @RequestMapping(value = "/gettopimg", method = RequestMethod.GET)
    public JSONObject gettopimg(){
        JSONObject json = new JSONObject();
        Example example = new Example(Topimg.class);
        Example.Criteria criteria =example.createCriteria();
        List<Topimg> topimgList=db.selectAllByExample(Topimg.class,example);
        json.put("topimgList",topimgList);
        return json;
    }
}
