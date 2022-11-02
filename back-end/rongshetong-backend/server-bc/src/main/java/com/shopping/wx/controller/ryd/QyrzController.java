package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Qyrz;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/qyrz")
public class QyrzController {
    @Autowired
    private DB db;

    //查看企业认证绑定状态
    @RequestMapping(value = "/whetherBindingInformation", method = RequestMethod.GET)
    public JSONObject whetherBindingInformation (String openid){
        JSONObject json = new JSONObject();
        Qyrz qyrz = new Qyrz();
        qyrz.setWxopenid(openid);

        List qyrzList = db.select(qyrz);
        if(qyrzList.size() == 0){
            json.put("openidbander",0);
        }else{
            json.put("openidbander",1);
            json.put("qyrz",qyrzList.get(0));
        }
        return json;
    }

    //保存企业认证信息
    @RequestMapping(value = "/saveQyrzMessage", method = RequestMethod.GET)
    public JSONObject saveQyrzMessage(String qyrz){
        JSONObject json = new JSONObject();
        JSONObject qyrzjson = JSONObject.parseObject(qyrz);
        Example example = new Example(Qyrz.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("qyname",qyrzjson.getString("qznam"));
        criteria.andEqualTo("status",1);
        List<Qyrz> qyrzList =db.selectAllByExample(Qyrz.class,example);
            Qyrz qyrz1 = new Qyrz();
            String id = UuidUtil.getTimeBasedUuid().toString();
            qyrz1.setId(id);
            qyrz1.setQyname(qyrzjson.getString("qznam"));
            qyrz1.setFcreatetime(new Date());
            qyrz1.setHyfl(qyrzjson.getString("hzfl"));
            qyrz1.setRygm(qyrzjson.getString("rygm"));
            qyrz1.setZczb(qyrzjson.getString("zczb"));
            qyrz1.setGqxx(qyrzjson.getString("gqxx"));
            qyrz1.setNsdj(qyrzjson.getString("nsdj"));
            qyrz1.setNsxydj(qyrzjson.getString("nsxydj"));
            qyrz1.setYysr(qyrzjson.getString("yysr"));
            qyrz1.setWxopenid(qyrzjson.getString("openid"));
            qyrz1.setStatus("1");
            db.insert(qyrz1);

        return json;
    }

    //修改企业认证信息
    @RequestMapping(value = "/updateQyrzMessage", method = RequestMethod.GET)
    public JSONObject updateQyrzMessage(String qyrz){
        JSONObject json = new JSONObject();
        JSONObject qyrzjson = JSONObject.parseObject(qyrz);

        Qyrz qyrz1 = new Qyrz();
        qyrz1.setId(qyrzjson.getString("id"));
        qyrz1.setQyname(qyrzjson.getString("qznam"));
        qyrz1.setHyfl(qyrzjson.getString("hzfl"));
        qyrz1.setRygm(qyrzjson.getString("rygm"));
        qyrz1.setZczb(qyrzjson.getString("zczb"));
        qyrz1.setGqxx(qyrzjson.getString("gqxx"));
        qyrz1.setNsdj(qyrzjson.getString("nsdj"));
        qyrz1.setNsxydj(qyrzjson.getString("nsxydj"));
        qyrz1.setYysr(qyrzjson.getString("yysr"));
        qyrz1.setWxopenid(qyrzjson.getString("openid"));
        db.update(qyrz1);
        return json;
    }
}
