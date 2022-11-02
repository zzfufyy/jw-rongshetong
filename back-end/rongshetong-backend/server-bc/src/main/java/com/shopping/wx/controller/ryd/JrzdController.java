package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/jrzd")
public class JrzdController {
    @Autowired
    private DB db;
    //查询公司类型
    @RequestMapping(value = "/searchGslx", method = RequestMethod.GET)
    public JSONObject searchGslx(){
        JSONObject json = new JSONObject();
        Gslx gslx = new Gslx();
        List<Gslx> gslxList = db.select(gslx);
        json.put("gslx",gslxList);
        List<Nsxydj> nsxydjList = db.selectAll(Nsxydj.class);
        json.put("nsxydjList",nsxydjList);
        List<Rygm> rygmList = db.selectAll(Rygm.class);
        json.put("rygmList",rygmList);
        List<Zczb> zczbList = db.selectAll(Zczb.class);
        json.put("zczbList",zczbList);
        List<Yysl> yyslList = db.selectAll(Yysl.class);
        json.put("yyslList",yyslList);
        List<Gqxx> gqxxList = db.selectAll(Gqxx.class);
        json.put("gqxxList",gqxxList);
        return json;
    }

    @RequestMapping(value = "/searchGs", method = RequestMethod.GET)
    public JSONObject searchGs(String openid){
        JSONObject json = new JSONObject();
        Qyrz qyrz = new Qyrz();
        qyrz.setWxopenid(openid);
        List<Qyrz> qyrzList = db.select(qyrz);

        json.put("qyrzList",qyrzList);
        return json;
    }

    @RequestMapping(value = "/searchJrzd", method = RequestMethod.GET)
    public JSONObject searchJrzd(){
        JSONObject json = new JSONObject();
        Example example = new Example(Jrzdfwbmd.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("status",1);
        List<Jrzdfwbmd> jrzdfwbmdList =db.selectAllByExample(Jrzdfwbmd.class,example);
//        for (int i = 0; i < jrzdfwbmdList.size(); i++) {
//            Example example1 = new Example(Gslx.class);
//            Example.Criteria criteria1 =example1.createCriteria();
//            criteria1.andEqualTo("gslx",jrzdfwbmdList.get(i).getQyfl());
//            List<Gslx> gslxList=db.selectAllByExample(Gslx.class,example1);
//            jrzdfwbmdList.get(i).setYl1(gslxList.get(0).getId());
//        }
        json.put("jrzdfwbmdList",jrzdfwbmdList);
        return json;
    }


    @RequestMapping(value = "/savejrzdfwbmd", method = RequestMethod.GET)
    public JSONObject savejrzdfwbmd(String openid,String yyzz,String nszm,String sgcrs,String wblxyjl) throws IOException {
        Jrzdfwbmd jrzdfwbmd = new Jrzdfwbmd();
        jrzdfwbmd.setId(UuidUtil.getTimeBasedUuid().toString());
        Qyrz qyrz = new Qyrz();
        qyrz.setWxopenid(openid);
        Date date = new Date();
        List<Qyrz> qyrzList = db.select(qyrz);
        jrzdfwbmd.setQyname(qyrzList.get(0).getQyname());
        jrzdfwbmd.setQyid(qyrzList.get(0).getId());
        jrzdfwbmd.setQyfl(qyrzList.get(0).getHyfl());
        jrzdfwbmd.setYyzz(yyzz);
        jrzdfwbmd.setYl2(date.toString());
        jrzdfwbmd.setNszm(nszm);
        jrzdfwbmd.setSgcrs(sgcrs);
        jrzdfwbmd.setWblxyjl(wblxyjl);
        jrzdfwbmd.setStatus("0");
        Example example1 = new Example(Gslx.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("gslx",qyrzList.get(0).getHyfl());
        List<Gslx> gslxList=db.selectAllByExample(Gslx.class,example1);
        jrzdfwbmd.setYl1(gslxList.get(0).getId());

        Example example = new Example(Jrzdfwbmd.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("qyid",qyrzList.get(0).getId());
        criteria.andEqualTo("status",1);
        List<Jrzdfwbmd> jrzdfwbmdList =db.selectAllByExample(Jrzdfwbmd.class,example);
        if(jrzdfwbmdList.size()>0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("false", false);
            return jsonObject;
        }else {
            db.insert(jrzdfwbmd);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ture", true);
            return jsonObject;
        }

    }
}
