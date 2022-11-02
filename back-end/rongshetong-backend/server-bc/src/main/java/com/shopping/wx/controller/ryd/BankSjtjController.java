package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Dksq;
import com.shopping.wx.model.Rwjd;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/bksjtj")
public class BankSjtjController {

    @Autowired
    private DB db;


    //小程序端贷款审核流程
    @RequestMapping(value = "/searchdkbyyhid", method = RequestMethod.GET)
    public JSONObject searchdkbyyhid(String yhid) throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andNotEqualTo("status",3);
        criteria.andNotEqualTo("status",-1);
        criteria.andEqualTo("dkbankid",yhid);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList1=db.selectAllByExample(Dksq.class,example);
        Example example1 = new Example(Dksq.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("status","-1");
        criteria1.andNotEqualTo("dkbankid",yhid);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example1);
        dksqList.addAll(dksqList1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }


    //金融办小程序端查询待受理贷款
    @RequestMapping(value = "/searchdk", method = RequestMethod.GET)
    public JSONObject searchdk() throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("status",0);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }

    //金融办小程序端查看数据
    @RequestMapping(value = "/searchdkbystatus", method = RequestMethod.GET)
    public JSONObject searchdkbystatus(String status,String desc) throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        if(!status.equals("")&&status!=null){
            criteria.andEqualTo("status",status);
        }
        if(!desc.equals("")&&desc!=null){
            criteria.andLike("qyname","%"+desc+"%");
        }
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
            Example exampless = new Example(Rwjd.class);
            Example.Criteria criteriass = exampless.createCriteria();
            criteriass.andEqualTo("rwid",dksqList.get(i).getId());
            exampless.setOrderByClause("datetime");
            List<Rwjd> rwjdList = db.selectAllByExample(Rwjd.class,exampless);
            if(rwjdList.size()>0){
                dksqList.get(i).setYxtjid(simpleDateFormat.format(rwjdList.get(0).getDatetime()));
            }
        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }

    @RequestMapping(value = "/searchdkbyyhidandstatus", method = RequestMethod.GET)
    public JSONObject searchdkbyyhidandstatus(String yhid,String status,String desc) throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        if(!status.equals("")&&status!=null){
            criteria.andEqualTo("status",status);
        }
        if(!desc.equals("")&&desc!=null){
            criteria.andLike("qyname","%"+desc+"%");
        }
        criteria.andNotEqualTo("status","0");
        criteria.andEqualTo("dkbankid",yhid);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
            Example exampless = new Example(Rwjd.class);
            Example.Criteria criteriass = exampless.createCriteria();
            criteriass.andEqualTo("rwid",dksqList.get(i).getId());
            exampless.setOrderByClause("datetime");
            List<Rwjd> rwjdList = db.selectAllByExample(Rwjd.class,exampless);
            if(rwjdList.size()>0){
                dksqList.get(i).setYxtjid(simpleDateFormat.format(rwjdList.get(0).getDatetime()));
            }

        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }

//金融办报表
    @RequestMapping(value = "/searchdkbystatusandtime", method = RequestMethod.GET)
    public JSONObject searchdkbystatusandtime(String yhid,String status,String kstime,String jstime) throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        if(!status.equals("")&&status!=null){
            criteria.andEqualTo("status",status);
        }
        if(!kstime.equals("")&&!jstime.equals("")){
            criteria.andBetween("dksj",kstime,jstime);
        }
//        criteria.andEqualTo("dkbankid",yhid);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
            Example exampless = new Example(Rwjd.class);
            Example.Criteria criteriass = exampless.createCriteria();
            criteriass.andEqualTo("rwid",dksqList.get(i).getId());
            exampless.setOrderByClause("datetime");
            List<Rwjd> rwjdList = db.selectAllByExample(Rwjd.class,exampless);
            if(rwjdList.size()>0){
                dksqList.get(i).setYxtjid(simpleDateFormat.format(rwjdList.get(0).getDatetime()));
            }

        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }


    @RequestMapping(value = "/searchdkbyyhidandstatusandtime", method = RequestMethod.GET)
    public JSONObject searchdkbyyhidandstatusandtime(String yhid,String status,String kstime,String jstime) throws ParseException {
        Example example = new Example(Dksq.class);
        Example.Criteria criteria =example.createCriteria();
        if(!status.equals("")&&status!=null){
            criteria.andEqualTo("status",status);
        }
        if(!kstime.equals("")&&!jstime.equals("")){
            criteria.andBetween("dksj",kstime,jstime);
        }
        criteria.andNotEqualTo("status","0");
        criteria.andEqualTo("dkbankid",yhid);
        example.setOrderByClause(" dksj desc");
        List<Dksq> dksqList=db.selectAllByExample(Dksq.class,example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <dksqList.size() ; i++) {
            dksqList.get(i).setYl2(simpleDateFormat.format(dksqList.get(i).getDksj()));
            Example exampless = new Example(Rwjd.class);
            Example.Criteria criteriass = exampless.createCriteria();
            criteriass.andEqualTo("rwid",dksqList.get(i).getId());
            exampless.setOrderByClause("datetime");
            List<Rwjd> rwjdList = db.selectAllByExample(Rwjd.class,exampless);
            if(rwjdList.size()>0){
                dksqList.get(i).setYxtjid(simpleDateFormat.format(rwjdList.get(0).getDatetime()));
            }

        }
        JSONObject json = new JSONObject();
        json.put("dksqList",dksqList);
        return json;
    }

}
