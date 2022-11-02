package com.shopping.wx.controller.sqjy;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.InformationNews;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.InfoNewsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/information-news")
public class InformationNewsController extends CrudController<InformationNews, String> {

    @Autowired
    InfoNewsService infoNewsService;
    final UploadService uploadService;
    @Autowired
    private DB db;

    public InformationNewsController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/searchNewsList", method = RequestMethod.GET)
    public JSONObject searchNewsList(String communityUuid) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(InformationNews.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", "0");
        if (communityUuid != null) {
            criteria.andEqualTo("community_uuid", communityUuid);
        }
        List<InformationNews> informationNewsList = db.selectAllByExample(InformationNews.class, example);
        json.put("informationNewsList", informationNewsList);
        return json;
    }
    @RequestMapping("/uploadPortrait")
    public ActionResult<?> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            InformationNews informationNews = new InformationNews();
            informationNews.setId(id);
            informationNews.setArticlePortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(informationNews) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    @RequestMapping("/uploadPortraitFile")
    public ActionResult<?> uploadPortraitFile(String id, String ext1, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            InformationNews informationNews = new InformationNews();
            informationNews.setId(id);
            informationNews.setExt1(ext1);
            informationNews.setAttachmentPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(informationNews) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    @PostMapping("/page")
    public ActionResult<PageInfo<InformationNews>> page(
            @RequestBody PagingParam<InfoNewsSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(infoNewsService.page(pagingParam))
        );
    }
    @PostMapping("/pageByArticleType")
    public ActionResult<PageInfo<InformationNews>> pageByArticleType(
            @RequestBody PagingParam<InfoNewsSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(infoNewsService.pageByArticleType(pagingParam))
        );
    }

    /***
     * 蓉社通所有新闻
     *
     * @param pagingParam
     * @return com.shopping.base.foundation.result.ActionResult<com.github.pagehelper.PageInfo<com.shopping.wx.model.InformationNews>>
     */
    @PostMapping("/pageAllSocial")
    public ActionResult<PageInfo<InformationNews>> pageAllSocial(
            @RequestBody PagingParam<InfoNewsSearchCondition> pagingParam
    ){
        return ActionResult.ok(
                PageInfo.of(infoNewsService.pageAllSocial(pagingParam))
        );
    }




    @GetMapping("/info")
    public ActionResult<InformationNews> info(@RequestParam String id) {
        return ActionResult.ok(infoNewsService.selectById(id));
    }

    @RequestMapping("/add")
    public ActionResult<Boolean> add(@RequestBody InformationNews informationNews) {
        return ActionResult.ok(insert(informationNews) == 1);
    }

    @RequestMapping("/list")
    ActionResult<List<InformationNews>> list() {
        return ActionResult.ok(infoNewsService.list());
    }

    @RequestMapping("/listByCommunityUuid")
    ActionResult<List<InformationNews>> listByCommunityUuid(
            @RequestParam String communityUuid,
            @RequestParam(required = false) Integer num) {
        return ActionResult.ok(infoNewsService.listByCommunityUuid(communityUuid, num));
    }


    @GetMapping("/increaseCountView")
    ActionResult<Integer> increaseCountView(@RequestParam String id){
        return ActionResult.ok(infoNewsService.increaseCountView(id));
    }

    @GetMapping("listByPagePath")
    ActionResult<List<InformationNews>> listByPagePath(
            @RequestParam String communityUuid,
            @RequestParam String pagePathApply
            ) {
        return ActionResult.ok(infoNewsService.listByPagePath(communityUuid,pagePathApply));
    }


    @Override
    protected CrudService<InformationNews> getService() {
        return infoNewsService;
    }
}
