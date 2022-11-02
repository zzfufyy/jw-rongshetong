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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/qydkrz")
public class QydkrzController {
    @Autowired
    private DB db;
    @RequestMapping(value = "/searchqy", method = RequestMethod.GET)
    public JSONObject searchqy(String openid) throws IOException {
        Example example = new Example(Qyrz.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("wxopenid",openid);
        List<Qyrz> qyrzList =db.selectAllByExample(Qyrz.class,example);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("qyrzList",qyrzList);
        return  jsonObject;
    }


    @RequestMapping(value = "/searchyh", method = RequestMethod.GET)
    public JSONObject searchyh(String yhid,String orgid) throws IOException {
        Example example = new Example(Bankinfo.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("id",yhid);
        List<Bankinfo> bankinfos =db.selectAllByExample(Bankinfo.class,example);


        Example example1 = new Example(Organ.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("id",orgid);
        List<Organ> organList =db.selectAllByExample(Organ.class,example1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bankinfos",bankinfos);
        jsonObject.put("organList",organList);
        return  jsonObject;
    }
    @RequestMapping(value = "/searchyhpm", method = RequestMethod.GET)
    public JSONObject searchyhpm(String status) throws IOException {
        Example example = new Example(Yhpm.class);
        Example.Criteria criteria =example.createCriteria();
        if(status.equals("1")){
            example.setOrderByClause("pricecount desc");
        }else if(status.equals("2")){
            example.setOrderByClause("sxe desc");
        }else {
            example.setOrderByClause("fordercount desc");
        }

        List<Yhpm> yhpms =db.selectAllByExample(Yhpm.class,example);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("yhpms",yhpms);

        return  jsonObject;
    }

    @RequestMapping(value = "/searchfczc", method = RequestMethod.GET)
    public JSONObject httpURLGETCase() {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/newLoan/loadLoanMainPage";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl );
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




    @RequestMapping(value = "/saveqydkrz", method = RequestMethod.GET)
    public JSONObject saveqydkrz(String qyid,String qyname,String name,String sfz,String zcszd,String zmsc,String bmsc) throws IOException {
        Qydkrz qydkrz = new Qydkrz();
        qydkrz.setId(UuidUtil.getTimeBasedUuid().toString());
        qydkrz.setQyname(qyname);
        qydkrz.setQyid(qyid);
        qydkrz.setSqr(name);
        qydkrz.setSfzh(sfz);
        qydkrz.setQyzcszd(zcszd);
        qydkrz.setSfzzimg(zmsc);
        qydkrz.setSfzfimg(bmsc);
        qydkrz.setStatus("0");
        db.insert(qydkrz);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ture", true);
        return jsonObject;
    }



    @RequestMapping(value = "/searchdkpj", method = RequestMethod.GET)
    public JSONObject searchdkpj(String dkid,String gsname) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Example example = new Example(Dkpj.class);
        Example.Criteria criteria =example.createCriteria();
        if(!dkid.equals("")){
            criteria.andEqualTo("dkid",dkid);
        }
        List<Dkpj> dkpjList =db.selectAllByExample(Dkpj.class,example);
        List<Dksq> dksqList= new ArrayList<>();
        for (int i = 0; i <dkpjList.size() ; i++) {
            Dksq dksq =db.selectById(dkpjList.get(i).getDkid(),Dksq.class);
            dksqList.add(dksq);
        }

        jsonObject.put("dksqList",dksqList);
        jsonObject.put("dkpjList",dkpjList);
        return  jsonObject;
    }
    @RequestMapping(value = "/searchpj", method = RequestMethod.GET)
    public JSONObject searchpj(String qyname,String status) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(status.equals("1")){
            Example example = new Example(DkpjView.class);
            Example.Criteria criteria =example.createCriteria();
            if(!qyname.equals("")){
                criteria.andLike("qyname","%"+qyname+"%");
            }
            List<DkpjView> dkpjList =db.selectAllByExample(DkpjView.class,example);
            jsonObject.put("dkpjList",dkpjList);


        }
       if(status.equals("2")){
           Example example1 = new Example(DktsView.class);
           Example.Criteria criteria1 =example1.createCriteria();
           if(!qyname.equals("")){
               criteria1.andLike("qyname","%"+qyname+"%");
           }
           List<DktsView> dktsList =db.selectAllByExample(DktsView.class,example1);
           jsonObject.put("dktsList",dktsList);

       }
        if(status.equals("")){
            Example example1 = new Example(DktsView.class);
            Example.Criteria criteria1 =example1.createCriteria();
            if(!qyname.equals("")){
                criteria1.andLike("qyname","%"+qyname+"%");
            }
            List<DktsView> dktsList =db.selectAllByExample(DktsView.class,example1);
            jsonObject.put("dktsList",dktsList);

            Example example = new Example(DkpjView.class);
            Example.Criteria criteria =example.createCriteria();
            if(!qyname.equals("")){
                criteria.andLike("qyname","%"+qyname+"%");
            }
            List<DkpjView> dkpjList =db.selectAllByExample(DkpjView.class,example);
            jsonObject.put("dkpjList",dkpjList);
        }

        return  jsonObject;
    }

    @RequestMapping(value = "/savepjsl", method = RequestMethod.GET)
    public JSONObject savepjsl(String id,String status) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Dkpj dkpj =db.selectById(id,Dkpj.class);
        dkpj.setYl1(status);
        if(status.equals("1")){
            dkpj.setSltime(new Date());
        }else if(status.equals("2")){
            dkpj.setWctime(new Date());
        }
        db.update(dkpj);
        Example example = new Example(DkpjView.class);
        Example.Criteria criteria =example.createCriteria();
        List<DkpjView> dkpjList =db.selectAllByExample(DkpjView.class,example);


        jsonObject.put("dkpjList",dkpjList);

        return  jsonObject;
    }

    @RequestMapping(value = "/savetssl", method = RequestMethod.GET)
    public JSONObject savetssl(String id,String status) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Dkts dkts =db.selectById(id,Dkts.class);
        dkts.setYl1(status);
        if(status.equals("1")){
            dkts.setSltime(new Date());
        }else if(status.equals("2")){
            dkts.setWctime(new Date());
        }
        db.update(dkts);
        Example example = new Example(DktsView.class);
        Example.Criteria criteria =example.createCriteria();
        List<DktsView> dktsList =db.selectAllByExample(DktsView.class,example);


        jsonObject.put("dktsList",dktsList);

        return  jsonObject;
    }

    @RequestMapping(value = "/searchdkts", method = RequestMethod.GET)
    public JSONObject searchdkts(String dkid) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Example example = new Example(Dkts.class);
        Example.Criteria criteria =example.createCriteria();
        if(!dkid.equals("")){
            criteria.andEqualTo("dkid",dkid);
        }
        List<Dkts> dktsList =db.selectAllByExample(Dkts.class,example);



        jsonObject.put("dktsList",dktsList);
        return  jsonObject;
    }
}
