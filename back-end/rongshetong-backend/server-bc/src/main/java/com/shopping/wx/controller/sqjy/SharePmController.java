package com.shopping.wx.controller.sqjy;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.BcUserWx;
import com.shopping.wx.model.Policy;
import com.shopping.wx.model.SharePm;
import com.shopping.wx.model.Userwx;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/share")
public class SharePmController {
    @Autowired
    private DB db;
//小程序分享
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public JSONObject insert(String openid) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(Userwx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid", openid);
        List<Userwx> userwxList = db.selectAllByExample(Userwx.class, example);


        Example example1 = new Example(SharePm.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("openid", openid);
        List<SharePm> sharePmList = db.selectAllByExample(SharePm.class, example1);
        if(sharePmList.size()>0){
            SharePm sharePm = new SharePm();
            sharePm.setId(sharePmList.get(0).getId());
            sharePm.setOpenid(openid);
            sharePm.setSucessSharenum(sharePmList.get(0).getSucessSharenum()+1);
            sharePm.setName(userwxList.get(0).getNickname());
            db.update(sharePm);
        }else {
            SharePm sharePm = new SharePm();
            sharePm.setId(UUID.randomUUID().toString());
            sharePm.setOpenid(openid);
            sharePm.setSucessSharenum(1);
            sharePm.setName(userwxList.get(0).getNickname());
            db.insert(sharePm);
        }
        return json;
    }
//社区分享
    @RequestMapping(value = "/insertbycommunity", method = RequestMethod.GET)
    public JSONObject insertbycommunity(String openid,String communityUuid) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(Userwx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid", openid);
        List<Userwx> userwxList = db.selectAllByExample(Userwx.class, example);


        Example example1 = new Example(SharePm.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("openid", openid);
        List<SharePm> sharePmList = db.selectAllByExample(SharePm.class, example1);
        if(sharePmList.size()>0){
            SharePm sharePm = new SharePm();
            sharePm.setId(sharePmList.get(0).getId());
            sharePm.setOpenid(openid);
            sharePm.setCommunityUuid(communityUuid);
            sharePm.setSucessSharenum(sharePmList.get(0).getSucessSharenum()+1);
            sharePm.setName(userwxList.get(0).getNickname());
            db.update(sharePm);
        }else {
            SharePm sharePm = new SharePm();
            sharePm.setId(UUID.randomUUID().toString());
            sharePm.setOpenid(openid);
            sharePm.setCommunityUuid(communityUuid);
            sharePm.setSucessSharenum(1);
            sharePm.setName(userwxList.get(0).getNickname());
            db.insert(sharePm);
        }
        return json;
    }
    //查询小程序分享总排名
    @RequestMapping(value = "/searchSharePm", method = RequestMethod.GET)
    public JSONObject searchSharePm() throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(SharePm.class);
        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("yl1", "1");
        example.setOrderByClause("sucess_sharenum DESC");
        List<SharePm> sharePmList = db.selectAllByExample(SharePm.class, example);
        json.put("sharePmList", sharePmList);
        return json;
    }
    //查询社区分享排名
    @RequestMapping(value = "/searchSharePmByCommunity", method = RequestMethod.GET)
    public JSONObject searchSharePmByCommunity(String communityUuid) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(SharePm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("communityUuid", communityUuid);
        example.setOrderByClause("sucess_sharenum DESC");
        List<SharePm> sharePmList = db.selectAllByExample(SharePm.class, example);
        json.put("sharePmList", sharePmList);
        return json;
    }

}
