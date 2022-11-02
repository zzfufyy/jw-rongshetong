package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONArray;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * 融资担保
 */
@Log4j2
@RestController
@RequestMapping("/rzdb")
public class rzdbController {
    @Autowired
    private DB db;


    //查询融资担保产品
    @RequestMapping(value = "/selectRzdb", method = RequestMethod.GET)
    public JSONObject selectDKQX() {
        JSONObject json = new JSONObject();
        Warrant warrant = new Warrant();
        warrant.setSfsx("1");
        List warrantList = db.select(warrant);
        json.put("warrantList", warrantList);
        return json;
    }
    @RequestMapping(value = "/searchfczc", method = RequestMethod.GET)
    public JSONObject httpURLGETCase(String sizeNum ,String pageNum) {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/newLoan/getMoreBankProduct";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl + "?sizeNum="+sizeNum+"&pageNum="+pageNum+"");
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求

                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }


    @RequestMapping(value = "/searchfczc11", method = RequestMethod.GET)
    public JSONObject httpURLGETCase1(String fid) {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/newLoan/getBankProductDetail";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl+ "?fid="+fid+"" );
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求

                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }
    //查询是否进行了企业贷款认证
    @RequestMapping(value = "/selectIsQydkrz", method = RequestMethod.GET)
    public JSONObject selectIsQydkrz(String openid) {
        JSONObject json = new JSONObject();
        Qydkrz qydkrz = new Qydkrz();
        Qyrz qyrz = new Qyrz();
        qyrz.setWxopenid(openid);
        //qyrz.setStatus("1");
        //查询贷款期限
        Dkqxinfo dkqxinfo = new Dkqxinfo();
        dkqxinfo.setSfsx("1");
        List<Dkqxinfo> dkqxinfoList = db.select(dkqxinfo);
        JSONArray dkqxinfojsons = new JSONArray();
        for (int i = 0; i < dkqxinfoList.size(); i++) {
            JSONObject dkqxinfojson = new JSONObject();
            dkqxinfojson.put("id", dkqxinfoList.get(i).getId());
            dkqxinfojson.put("name", dkqxinfoList.get(i).getDkqx());
            dkqxinfojsons.add(dkqxinfojson);
        }
        json.put("dkqxinfojson", dkqxinfojsons);
        //查询贷款利率
        Dkll dkll = new Dkll();
        dkll.setSfsx("1");
        List<Dkll> dkllList = db.select(dkll);
        JSONArray dklljsons = new JSONArray();
        for (int i = 0; i < dkllList.size(); i++) {
            JSONObject dklljson = new JSONObject();
            dklljson.put("id", dkllList.get(i).getId());
            dklljson.put("name", dkllList.get(i).getDkll());
            dklljsons.add(dklljson);
        }
        json.put("dklljson", dklljsons);

        Example example = new Example(Qyrz.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxopenid", openid);
        List<Qyrz> qyrzList = db.selectAllByExample(Qyrz.class, example);

        if(qyrzList.size() > 0){
            json.put("isqyrz",true);
        }else {
            json.put("isqyrz",false);
        }


        List<Qyrz> qyList = db.select(qyrz);
        if (qyList.size() == 0) {
            json.put("isbusness", false);
            return json;
        } else {
            qyrz = qyList.get(0);
            qydkrz.setQyid(qyrz.getId());
            //qydkrz.setStatus("1");
            List<Qydkrz> qydkrzList = db.select(qydkrz);
            if (qydkrzList.size() == 0) {
                json.put("isbusness", false);
                return json;
            } else {
                json.put("qyname", qyrz.getQyname());
                json.put("isbusness", true);
                return json;
            }
        }
    }

    //保存融资担保
    @RequestMapping(value = "/saveRzdb", method = RequestMethod.GET)
    public JSONObject saveRzdb(String rzdbArrays) {
        JSONObject json = new JSONObject();
        JSONObject rzdbjson = JSONObject.parseObject(rzdbArrays);
        Sdbsq sdbsq = new Sdbsq();
        sdbsq.setId(UuidUtil.getTimeBasedUuid().toString());
        sdbsq.setQyname(rzdbjson.getString("qyname"));
        sdbsq.setSqr(rzdbjson.getString("sqr"));
        sdbsq.setLxdh(rzdbjson.getString("lxdh"));
        sdbsq.setDkje(rzdbjson.getString("dkje"));
        sdbsq.setDkqxid(rzdbjson.getString("dkqxid"));
        sdbsq.setDkqx(rzdbjson.getString("dkqx"));
        sdbsq.setDkllid(rzdbjson.getString("dkllid"));
        sdbsq.setDkll(rzdbjson.getString("dkll"));
        sdbsq.setYye(rzdbjson.getString("yye"));
        sdbsq.setNshe(rzdbjson.getString("nse"));
        sdbsq.setXyyhfz(rzdbjson.getString("xyyhfz"));
        sdbsq.setZyyw(rzdbjson.getString("zyyw"));
        sdbsq.setYl1(rzdbjson.getString("openid"));
        sdbsq.setStatus("0");

        db.insert(sdbsq);

        return json;
    }

    //根据id查询融资担保详情
    @RequestMapping(value = "/selectRzdbById", method = RequestMethod.GET)
    public JSONObject selectRzdbById(String id) {
        JSONObject json = new JSONObject();
        Warrant warrant = db.selectById(id, Warrant.class);
        json.put("warrant", warrant);
        return json;
    }

}
