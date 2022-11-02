package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.*;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.util.BASE64DecodedMultipartFile;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@RestController
@RequestMapping("/fczc")
public class fczcController {

    @Autowired
    private DB db;
    @Autowired
    UploadService uploadService;
    @RequestMapping(value = "/searchShangJia", method = RequestMethod.GET)
    public JSONObject searchShangJia() throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(Policy.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yl1", "1");
        example.setOrderByClause("fbtime DESC");
        List<Policy> policyList = db.selectAllByExample(Policy.class, example);
        json.put("policyList", policyList);
        return json;
    }

    @RequestMapping(value = "/addPageviews", method = RequestMethod.GET)
    public JSONObject addPageviews(String id){
        JSONObject json = new JSONObject();
        Policy policy = db.selectById(id,Policy.class);
        int pageviews = policy.getYl2();
        pageviews = pageviews + 1;
        policy = new Policy();
        policy.setId(id);
        policy.setYl2(pageviews);
        db.update(policy);
        json.put("msg","点击+1");
        return json;
    }

    @RequestMapping(value = "/selectPolicyById", method = RequestMethod.GET)
    public JSONObject selectPolicyById(String id){
        JSONObject json = new JSONObject();
        Policy policy = db.selectById(id,Policy.class);
        json.put("policy",policy);
        return json;
    }

    @RequestMapping(value = "/searchfczc", method = RequestMethod.GET)
    public JSONObject httpURLGETCase() {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/newLoan/getMoreHelpPolicy";
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

    @RequestMapping(value = "/searchfczc11", method = RequestMethod.GET)
    public JSONObject httpURLGETCase1(String fid) {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/companyMainPage/loadOnePolicy";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl + "?fid="+fid+"");
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




    @RequestMapping(value = "/searchtoken", method = RequestMethod.GET)
    public JSONObject httpURLGETCase1(String grant_type ,String appid,String secret) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/token";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        appid="wx7741a14f2a4e3776";
        secret="a6b106075aabd43c2fc9d369dcc9d4d4";
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl + "?grant_type="+grant_type+"&appid="+appid+"&secret="+secret);
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

    @RequestMapping(value = "/base64", method = RequestMethod.GET)
    public JSONObject base64(String access_token ,String path,String width) throws IOException {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
//        path="wx7741a14f2a4e3776";
//        width="a6b106075aabd43c2fc9d369dcc9d4d4";
        StringBuilder result = new StringBuilder();
        Map<String,Object> param = new HashMap<>();
        param.put("access_token", access_token);
        param.put("path", path);
        param.put("width", width);
        String jsonString = JSON.toJSONString(param);
        StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);


//            URL url = new URL(methodUrl + "?access_token="+access_token+"&path="+path+"&width="+width);
//            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(methodUrl);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//            connection.setRequestMethod("POST");// 默认GET请求
//            connection.connect();// 建立TCP连接
            CloseableHttpResponse response = null;
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
////                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
//
//                // 循环读取流
//                while ((line = reader.readLine()) != null) {
//                    result.append(line).append(System.getProperty("line.separator"));// "\n"
//                }
//                System.out.println(result.toString());
//
//            }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }

    public static Map<String, String> getDataMap(String msg){
        Map<String, String> map = new HashMap<>();
        map.put("value", msg);
        return map;
    }

    @RequestMapping(value = "/getnumbers", method = RequestMethod.GET)
    public JSONObject getnumbers(String access_token ,String touser,String template_id,String qyname,String lxr ,String phone ,String dkje ,String yhid,String organid) {
        Example example = new Example(Bankqx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 2);
        criteria.andEqualTo("state", 1);
        criteria.andEqualTo("bankid", yhid);
        if (!organid.equals("undefined")) {
            System.out.println(1111);
            criteria.andEqualTo("orgonid", organid);
        }
        List<Bankqx> bankqxList = db.selectAllByExample(Bankqx.class, example);
        JSONObject result = new JSONObject();
        result.put("bankqxList",bankqxList);
        return result;
    }
    @RequestMapping(value = "/sendinfos", method = RequestMethod.GET)
    public JSONObject sendinfos(String access_token ,String touser,String template_id,String qyname,String lxr ,String phone ,String dkje ,String yhid,String organid) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;

            Map<String,Object> param = new HashMap<>();
            param.put("touser", touser);
            param.put("template_id", template_id);
            param.put("page", "/pages/bankdataStatistics/bankdataStatistics?yhid="+ yhid);
            Map<String,Object> data = new HashMap<>();
            if(!organid.equals("undefined")){
                Organ organ =db.selectById(organid, Organ.class);
                System.out.println(2222);
                data.put("thing1", getDataMap(qyname));
                data.put("thing2", getDataMap(lxr));
                data.put("phone_number3", getDataMap(phone));
                data.put("amount4", getDataMap(dkje+"0000元"));
                data.put("thing5", getDataMap(organ.getOrganname()));
                param.put("data", data);
            }else {
                System.out.println(3333);
                Bankinfo bankinfo = db.selectById(yhid,Bankinfo.class);
                data.put("thing1", getDataMap(qyname));
                data.put("thing2", getDataMap(lxr));
                data.put("phone_number3", getDataMap(phone));
                data.put("amount4", getDataMap(dkje+"0000元"));
                data.put("thing5", getDataMap(bankinfo.getBankname()));
                param.put("data", data);
            }
            //data
            String jsonString = JSON.toJSONString(param);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(methodUrl);
            StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);

            httpPost.setEntity(entity);
            // 使用 JSON 格式传递参数
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            // 响应模型
            CloseableHttpResponse response = null;
            try {
                response = client.execute(httpPost);
                HttpEntity responseEntity = response.getEntity();

                System.out.println("响应状态为:" + response.getStatusLine());
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                }

                JSONObject result = new JSONObject();
                return result;

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (client != null) {
                        client.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }

//求职者发送消息
    @RequestMapping(value = "/sendtdjlinfos", method = RequestMethod.GET)
    public JSONObject sendtdjlinfos(String access_token ,String touser,String template_id,String zhiwei,String openid) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;

        Map<String,Object> param = new HashMap<>();
        param.put("touser", touser);
        param.put("template_id", template_id);
        param.put("page", "pages/manegejl/manegejl");
        Map<String,Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserCandidate userCandidate = db.selectById(openid,UserCandidate.class);
            System.out.println(2222);
            data.put("name1", getDataMap(userCandidate.getRealName()));
            data.put("phrase2", getDataMap(zhiwei));
            data.put("date4", getDataMap(simpleDateFormat.format(new Date())));
            data.put("phone_number6", getDataMap(userCandidate.getTelephone()));
            if(userCandidate.getExpectSalaryMin()==0&&userCandidate.getExpectSalaryMax()==0){
                System.out.println("111");
                data.put("character_string7", getDataMap("0-∞"));
            }else {
                System.out.println("222");
                data.put("character_string7", getDataMap(userCandidate.getExpectSalaryMin()+"-"+userCandidate.getExpectSalaryMax()));
            }

            param.put("data", data);

        //data
        String jsonString = JSON.toJSONString(param);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(methodUrl);
        StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);

        httpPost.setEntity(entity);
        // 使用 JSON 格式传递参数
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }

            JSONObject result = new JSONObject();
            return result;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }
    //招聘者发送消息
    @RequestMapping(value = "/sendmsyqinfos", method = RequestMethod.GET)
    public JSONObject sendmsyqinfos(String access_token ,String touser,String template_id,String zhiwei,String openid,String companyname) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;

        Map<String,Object> param = new HashMap<>();
        param.put("touser", touser);
        param.put("template_id", template_id);
        param.put("page", "pages/msinvite/msinvite");
        Map<String,Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserRecruiter userRecruiter = db.selectById(openid,UserRecruiter.class);
        RecruitJob recruitJob = db.selectById(zhiwei,RecruitJob.class);
        RecruitCompany recruitCompany = db.selectById(companyname,RecruitCompany.class);
        System.out.println(openid);
        data.put("thing5", getDataMap(recruitCompany.getCompanyName()));
        data.put("thing1", getDataMap(recruitJob.getJobName()));
        data.put("time3", getDataMap(simpleDateFormat.format(new Date())));
        data.put("thing6", getDataMap(userRecruiter.getRealName()));
        data.put("time7", getDataMap(simpleDateFormat.format(new Date())));

        param.put("data", data);

        //data
        String jsonString = JSON.toJSONString(param);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(methodUrl);
        StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);

        httpPost.setEntity(entity);
        // 使用 JSON 格式传递参数
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }

            JSONObject result = new JSONObject();
            return result;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }

    //动态生成社区小程序码
    @RequestMapping(value = "/getQRCode", method = RequestMethod.GET)
    public JSONObject getQRCode(String access_token ,String sceneStr) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] reuslt = null;
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token;
            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/sqfw/sqfw");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            log.info("调用生成微信URL接口传参:" + param);
            reuslt=sendPostJson2(url, JSONObject.toJSONString(param));
            log.info(reuslt);
            String code = UUID.randomUUID().toString();
            String qrcodeurl = "D:\\data\\file\\ewm\\"+code+".png";
            String urls =code+".png";
            File miniFile = new File(qrcodeurl);
            if(!miniFile.getParentFile().exists()){
                miniFile.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(miniFile);
            outputStream.write(reuslt);
            jsonObject.put("qrcodeurl",urls);
            return jsonObject;
        } catch (Exception e) {
            log.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonObject;
    }

    //动态生成职位小程序码
    @RequestMapping(value = "/getJobQRCode", method = RequestMethod.GET)
    public JSONObject getJobQRCode(String access_token ,String sceneStr) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] reuslt = null;
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token;

            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/zwxq/zwxq");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            log.info("调用生成微信URL接口传参:" + param);
            reuslt=sendPostJson2(url, JSONObject.toJSONString(param));
            log.info(reuslt);
            String code = UUID.randomUUID().toString();
            String qrcodeurl = "D:\\data\\file\\ewm\\"+code+".png";
            String urls =code+".png";
            File miniFile = new File(qrcodeurl);
            if(!miniFile.getParentFile().exists()){
                miniFile.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(miniFile);
            outputStream.write(reuslt);
            jsonObject.put("qrcodeurl",urls);
            return jsonObject;
        } catch (Exception e) {
            log.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonObject;
    }

    //动态生成投票小程序码
    @RequestMapping(value = "/gettpQRCode", method = RequestMethod.GET)
    public JSONObject gettpQRCode(String access_token ,String sceneStr) {
//        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] reuslt = null;
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token;

            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/zwxq/zwxq");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            log.info("调用生成微信URL接口传参:" + param);
            reuslt=sendPostJson2(url, JSONObject.toJSONString(param));
            log.info(reuslt);
            String code = UUID.randomUUID().toString();
            String qrcodeurl = "D:\\data\\file\\ewm\\"+code+".png";
            String urls =code+".png";
            File miniFile = new File(qrcodeurl);
            if(!miniFile.getParentFile().exists()){
                miniFile.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(miniFile);
            outputStream.write(reuslt);
            jsonObject.put("qrcodeurl",urls);
            return jsonObject;
        } catch (Exception e) {
            log.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonObject;
    }

    public static byte[] sendPostJson2(String url, String json) {
        InputStream inputStream = null;
        byte[] data = null;
        // 创建默认的CloseableHttpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    data = readInputStream(inputStream);
                }
                return data;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    @RequestMapping(value = "/sercahJob", method = RequestMethod.GET)
    public JSONObject sercahJob(String ext1) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(RecruitJob.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("zzid", ext1);
        criteria.andEqualTo("status", 0);
        List<RecruitJob> recruitJobList = db.selectAllByExample(RecruitJob.class, example);
        json.put("recruitJobList", recruitJobList);
        return json;
    }
    /**
     * 将流 保存为数据数组
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
