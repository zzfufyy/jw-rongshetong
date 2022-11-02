package com.shopping.wx.controller.sqjy;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.BannerForPage;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.Policy;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.BannerForPageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/banner_for_page")
public class BannerForPageController extends CrudController<BannerForPage, String> {
    @Autowired
    private BannerForPageService bannerForPageService;
    @Autowired
    private DB db;

    @RequestMapping(value = "/searchBanner", method = RequestMethod.GET)
    public JSONObject searchBanner() throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(BannerForPage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("communityUuid", "");
        criteria.andEqualTo("status", 0);
        List<BannerForPage> bannerForPageList = db.selectAllByExample(BannerForPage.class, example);
        json.put("bannerForPageList", bannerForPageList);
        return json;
    }

    //查询社区各自banner图
    @RequestMapping(value = "/searchCommunityBanner", method = RequestMethod.GET)
    public JSONObject searchBanner(String communityUuid) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(BannerForPage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("communityUuid", communityUuid);
        criteria.andEqualTo("status", 0);
        List<BannerForPage> bannerForPageList = db.selectAllByExample(BannerForPage.class, example);
        json.put("bannerForPageList", bannerForPageList);
        return json;
    }

    @RequestMapping(value = "/searchCommunity", method = RequestMethod.GET)
    public JSONObject searchCommunity(String ext1) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(CommunityInformation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ext1", ext1);
        criteria.andEqualTo("status", 0);
        List<CommunityInformation> communityInformationList = db.selectAllByExample(CommunityInformation.class, example);
        json.put("communityInformationList", communityInformationList);
        return json;
    }


    @Override
    protected CrudService<BannerForPage> getService() {
        return bannerForPageService;
    }
}
