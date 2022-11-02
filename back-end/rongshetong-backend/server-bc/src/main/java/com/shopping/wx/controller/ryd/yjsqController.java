package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/yjsq")
public class yjsqController {

    @Autowired
    private DB db;

    //查询贷款期限
    @RequestMapping(value = "/selectDkqx", method = RequestMethod.GET)
    public JSONObject selectDKQX(){
        JSONObject json = new JSONObject();
        Dkqxinfo dkqxinfo = new Dkqxinfo();
        dkqxinfo.setSfsx("1");
        List<Dkqxinfo> dkqxinfoList = db.select(dkqxinfo);
        json.put("dkqxinfoList",dkqxinfoList);
        return json;
    }

//查询优先条件
    @RequestMapping(value = "/selectYxtj", method = RequestMethod.GET)
    public JSONObject selectYxtj(){
        JSONObject json = new JSONObject();
        Example example = new Example(Yxtj.class);
        example.setOrderByClause(" id desc");
        List<Yxtj> yxtjList = db.selectAllByExample(Yxtj.class,example);
        json.put("yxtjList",yxtjList);
        return json;
    }

    @RequestMapping(value = "/selectBank", method = RequestMethod.GET)
    public JSONObject selectBank(String yxtj){
        JSONObject json = new JSONObject();
        Example example = new Example(Bankinfo.class);
        Example.Criteria criteria =example.createCriteria();
        if(!yxtj.equals("3")){
            criteria.andEqualTo("yxtjid",yxtj);
        }
        List<Bankinfo> bankinfos = db.selectAllByExample(Bankinfo.class,example);
        json.put("bankinfos",bankinfos);
        return json;
    }
    //查询贷款用途
    @RequestMapping(value = "/selectDkyt", method = RequestMethod.GET)
    public JSONObject selectDkyt(){
        JSONObject json = new JSONObject();
        Dkytinfo dkytinfo = new Dkytinfo();
        dkytinfo.setSfsx("1");
        List<Dkytinfo> dkytinfoList = db.select(dkytinfo);
        json.put("dkytinfoList",dkytinfoList);
        return json;
    }
    //查询还款方式
    @RequestMapping(value = "/selectHkfs", method = RequestMethod.GET)
    public JSONObject selectHkfs(){
        JSONObject json = new JSONObject();
        Hkfsinfo hkfsinfo = new Hkfsinfo();
        hkfsinfo.setSfsx("1");
        List<Hkfsinfo> hkfsinfoList = db.select(hkfsinfo);
        json.put("hkfsinfoList",hkfsinfoList);
        return json;
    }
    //查询公司名称
    @RequestMapping(value = "/selectQymc", method = RequestMethod.GET)
    public JSONObject selectGsmc(String openid){
        JSONObject json = new JSONObject();
        Qyrz qyrz = new Qyrz();
        qyrz.setWxopenid(openid);
        List<Qyrz> qyrzList = db.select(qyrz);
        if(qyrzList.size() > 0){
            qyrz = qyrzList.get(0);
            json.put("countQymc",qyrzList.size());
            json.put("qymc",qyrz.getQyname());
        }else {
            json.put("countQymc",qyrzList.size());
        }
        return json;
    }
    //查询贷款银行
    @RequestMapping(value = "/selectDlbank", method = RequestMethod.GET)
    public JSONObject selectDlbank(){
        JSONObject json = new JSONObject();
        Bankinfo bankinfo = new Bankinfo();
        bankinfo.setType("1");
        List<Bankinfo> bankinfoList = db.select(bankinfo);
        json.put("bankinfoList",bankinfoList);
        return json;
    }

    //一键申请保存
    @Transactional
    @RequestMapping(value = "/saveYjsq", method = RequestMethod.GET)
    public JSONObject saveYjsq(String yjsqList,String wdid,String wdname,String productid){
        JSONObject json = new JSONObject();
        JSONObject yjsqOBJ = JSONObject.parseObject(yjsqList);

        //贷款申请
        Dksq dksq = new Dksq();
        dksq.setId(UuidUtil.getTimeBasedUuid().toString());
        dksq.setQyname(yjsqOBJ.getString("qzmc"));
        dksq.setSqr(yjsqOBJ.getString("sqr"));
        dksq.setLxdh(yjsqOBJ.getString("lxdh"));
        dksq.setDkje(yjsqOBJ.getString("dkje"));
        dksq.setDkqxid(yjsqOBJ.getString("dkqxid"));
        dksq.setDkqx(yjsqOBJ.getString("dkqx"));
        dksq.setSfdyw(yjsqOBJ.getString("switch1Checked"));
        dksq.setDkytid(yjsqOBJ.getString("dkytid"));
        dksq.setDkyt(yjsqOBJ.getString("dkyt"));
        dksq.setHkfs(yjsqOBJ.getString("fqhk"));
        dksq.setHkfsid(yjsqOBJ.getString("fqhkid"));
        dksq.setYye(yjsqOBJ.getString("yye"));
        dksq.setDksj(new Date());
        dksq.setNshe(yjsqOBJ.getString("nse"));
        dksq.setYxtjid(yjsqOBJ.getString("yxtjid"));
        dksq.setYxtjname(yjsqOBJ.getString("yxtj"));
        if(productid!=null){
            dksq.setDyw(productid);
        }
        dksq.setDkbankid(yjsqOBJ.getString("dkyhid"));
        if(wdid!=null){
            dksq.setYhwdid(wdid);
        }
        if(wdname!=null){
            dksq.setYhwd(wdname);
        }

        dksq.setDkbankname(yjsqOBJ.getString("dkyh"));
        dksq.setYl1(yjsqOBJ.getString("openid"));
        dksq.setStatus("0");
        db.insert(dksq);
        //任务进度
        Rwjd rwjd = new Rwjd();
        rwjd.setId(UuidUtil.getTimeBasedUuid().toString());
        rwjd.setName("已申请");
        rwjd.setDatetime(new Date());
        rwjd.setOpenid(yjsqOBJ.getString("openid"));
        rwjd.setBh(0);
        rwjd.setIsfinish(0);
        rwjd.setRwid(dksq.getId());
        db.insert(rwjd);

        return json;
    }

    //贷款受理
    @RequestMapping(value = "/dksl", method = RequestMethod.GET)
    public JSONObject dksl(String dksqid,String organid,String oppenid){
        Dksq dksq =db.selectById(dksqid,Dksq.class);
        dksq.setStatus("2");
        Organ organ=db.selectById(organid,Organ.class);
        if(!dksq.getDkbankid().equals(organ.getBankid())){
            Dkzp dkzp = new Dkzp();
            dkzp.setOldbkid(dksq.getDkbankid());
            dkzp.setOldbkname(dksq.getDkbankname());
            dkzp.setOldorganid(dksq.getYhwdid());
            dkzp.setOldorganname(dksq.getYhwd());

            Bankinfo bankinfo =db.selectById(organ.getBankid(),Bankinfo.class);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
            dkzp.setZpsj(simpleDateFormat.format(new Date()));
            dkzp.setNewbkid(bankinfo.getId());
            dkzp.setNewbkname(bankinfo.getBankname());
            dkzp.setNeworganid(organ.getId());
            dkzp.setNeworganname(organ.getOrganname());
            dkzp.setYl1(dksq.getId());
            db.insert(dkzp);

            dksq.setDkbankid(bankinfo.getId());
            dksq.setDkbankname(bankinfo.getBankname());
        }

        dksq.setYhwdid(organid);
        System.out.println(organid);
        dksq.setYhwd(organ.getOrganname());
        db.update(dksq);
        Rwjd rwjd = new Rwjd();
        rwjd.setId(UuidUtil.getTimeBasedUuid().toString());
        rwjd.setName("已受理");
        rwjd.setDatetime(new Date());
        rwjd.setOpenid(dksq.getYl1());
        rwjd.setBh(0);
        rwjd.setIsfinish(0);
        rwjd.setSlroppenid(oppenid);
        if(!oppenid.equals("")){
            Example example = new Example(Bankqx.class);
            Example.Criteria criteria =example.createCriteria();
            criteria.andEqualTo("oppenid",oppenid);
            List<Bankqx> bankqxList =db.selectAllByExample(Bankqx.class,example);
            if(bankqxList.size()>0){
                rwjd.setSlr(bankqxList.get(0).getName());
                rwjd.setSlphone(bankqxList.get(0).getPhone());

            }
        }

        rwjd.setRwid(dksq.getId());
        db.insert(rwjd);
        JSONObject json = new JSONObject();
        json.put("code","已受理");
        return json;
    }


    //贷款退回
    @RequestMapping(value = "/dkth", method = RequestMethod.GET)
    public JSONObject dkth(String dksqid,String thly,String oppenid){
        Dksq dksq =db.selectById(dksqid,Dksq.class);
        dksq.setStatus("-1");
        db.update(dksq);
        Rwjd rwjd = new Rwjd();
        rwjd.setId(UuidUtil.getTimeBasedUuid().toString());
        rwjd.setName("已退回");
        rwjd.setDatetime(new Date());
        rwjd.setOpenid(dksq.getYl1());
        rwjd.setBh(0);
        rwjd.setIsfinish(0);
        rwjd.setThly(thly);
        rwjd.setRwid(dksq.getId());
        if(!oppenid.equals("")){
            Example example = new Example(Bankqx.class);
            Example.Criteria criteria =example.createCriteria();
            criteria.andEqualTo("oppenid",oppenid);
            List<Bankqx> bankqxList =db.selectAllByExample(Bankqx.class,example);
            if(bankqxList.size()>0){
                rwjd.setSlr(bankqxList.get(0).getName());
                rwjd.setSlphone(bankqxList.get(0).getPhone());
                rwjd.setSlroppenid(bankqxList.get(0).getOppenid());
            }
        }
        db.insert(rwjd);
        JSONObject json = new JSONObject();
        json.put("code","已退回");
        return json;
    }

    //贷款通过
    @RequestMapping(value = "/dktg", method = RequestMethod.GET)
    public JSONObject dktg(String dksqid,String oppenid){
        Dksq dksq =db.selectById(dksqid,Dksq.class);
        dksq.setStatus("1");
        db.update(dksq);
        Rwjd rwjd = new Rwjd();
        rwjd.setId(UuidUtil.getTimeBasedUuid().toString());
        rwjd.setName("已通过");
        rwjd.setDatetime(new Date());
        rwjd.setOpenid(dksq.getYl1());
        rwjd.setBh(0);
        rwjd.setIsfinish(0);
        rwjd.setRwid(dksq.getId());
        if(!oppenid.equals("")){
            Example example = new Example(Bankqx.class);
            Example.Criteria criteria =example.createCriteria();
            criteria.andEqualTo("oppenid",oppenid);
            List<Bankqx> bankqxList =db.selectAllByExample(Bankqx.class,example);
            if(bankqxList.size()>0){
                rwjd.setSlr(bankqxList.get(0).getName());
                rwjd.setSlphone(bankqxList.get(0).getPhone());
                rwjd.setSlroppenid(bankqxList.get(0).getOppenid());
            }
        }
        db.insert(rwjd);
        JSONObject json = new JSONObject();
        json.put("code","已通过");
        return json;
    }

    //贷款通过
    @RequestMapping(value = "/dkfk", method = RequestMethod.GET)
    public JSONObject dkfk(String dksqid,String oppenid){
        Dksq dksq =db.selectById(dksqid,Dksq.class);
        dksq.setStatus("3");
        db.update(dksq);
        Rwjd rwjd = new Rwjd();
        rwjd.setId(UuidUtil.getTimeBasedUuid().toString());
        rwjd.setName("已放款");
        rwjd.setDatetime(new Date());
        rwjd.setOpenid(dksq.getYl1());
        rwjd.setBh(0);
        rwjd.setIsfinish(0);
        rwjd.setRwid(dksq.getId());
        if(!oppenid.equals("")){
            Example example = new Example(Bankqx.class);
            Example.Criteria criteria =example.createCriteria();
            criteria.andEqualTo("oppenid",oppenid);
            List<Bankqx> bankqxList =db.selectAllByExample(Bankqx.class,example);
            if(bankqxList.size()>0){
                rwjd.setSlr(bankqxList.get(0).getName());
                rwjd.setSlphone(bankqxList.get(0).getPhone());
                rwjd.setSlroppenid(bankqxList.get(0).getOppenid());
            }
        }
        db.insert(rwjd);
        JSONObject json = new JSONObject();
        json.put("code","已放款");
        return json;
    }

    //判断是否是银行管理员权限
    @RequestMapping(value = "/hqyhglyqx", method = RequestMethod.GET)
    public JSONObject hqyhglyqx(String oppenid){
        //银行权限
        Example example = new Example(Bankqx.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("oppenid",oppenid);
        criteria.andEqualTo("state",1);
        criteria.andEqualTo("type",2);
        List<Bankqx> bankqxList =db.selectAllByExample(Bankqx.class,example);
//金融办权限
        Example example1 = new Example(Bankqx.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("oppenid",oppenid);
        criteria1.andEqualTo("state",1);
        criteria1.andEqualTo("type",1);
        List<Bankqx> jrbbankqxList =db.selectAllByExample(Bankqx.class,example1);
        JSONObject json = new JSONObject();
        Map map = new HashMap();
        map.put("bankqxList",bankqxList);
        map.put("jrbbankqxList",jrbbankqxList);
        json.put("code",map);
        return json;
    }

}
