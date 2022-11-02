package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.utils.StringUtils;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyDTO;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyJobNameList;
import com.shopping.wx.pojo.dto.recruit_company.RecruitCompanyPlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;


/**
 * @ClassName RecruitCompanyController
 * @Description 公司相关controller
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("/recruit-company")
public class RecruitCompanyController extends CrudController<RecruitCompany, String> {

    @Autowired
    UploadService uploadService;
    @Autowired
    private RecruitCompanyService recruitCompanyService;
    @Autowired
    private UserRecruiterService userRecruiterService;

    @Autowired
    private DB db;

    @RequestMapping("/page")
    public ActionResult<PageInfo<RecruitCompany>> page(@RequestBody PagingParam<RecruitCompanySearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitCompanyService.page(pagingParam))
        );
    }

    @RequestMapping("/pagedByCondition")
    public ActionResult<PageInfo<RecruitCompanyDTO>> pagedByCondition(
            @RequestBody PagingParam<RecruitCompanySearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitCompanyService.pagedByCondition(pagingParam))
        );
    }

    @RequestMapping("/pageJobNameList")
    public ActionResult<PageInfo<RecruitCompanyJobNameList>> pageJobNameList(
            @RequestBody PagingParam<RecruitCompanySearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitCompanyService.pageJobNameList(pagingParam))
        );
    }

    @GetMapping("/info")
    public ActionResult<RecruitCompany> info(@RequestParam String id) {
        return ActionResult.ok(recruitCompanyService.selectById(id));
    }

    @GetMapping("/infoWithAssociated")
    public ActionResult<RecruitCompanyPlus> infoWithAssociated(@RequestParam String id) {
        return ActionResult.ok(recruitCompanyService.infoWithAssociated(id));
    }

    @GetMapping("/infoByRecruiterPhone")
    public ActionResult<RecruitCompany> infoByRecruiterPhone(@RequestParam String telephone){
        RecruitCompany recruitCompany = new RecruitCompany();
        recruitCompany.setJuridicalPhone(telephone);
        List<RecruitCompany> rsList = recruitCompanyService.select(recruitCompany);
        return ActionResult.ok(rsList.size() == 0 ? null : rsList.get(0));
    }



    /**
     * 添加
     *
     * @param openid
     * @param recruitCompany
     * @return 返回公司id
     */
    @RequestMapping("/add")
    public ActionResult<String> add(@RequestParam String openid, @RequestBody RecruitCompany recruitCompany) {
        //根据电话匹配公司
        Example example = new Example(RecruitCompany.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(QueryUtils.getFieldName(RecruitCompany::getJuridicalPhone), recruitCompany.getJuridicalPhone());
        List<RecruitCompany> recruitCompanyList = recruitCompanyService.selectAllByExample(example);
        if (recruitCompanyList.size() == 0) {
            // 如果 找不到招聘人电话插入
            insert(recruitCompany);
            UserRecruiter  userRecruiter = new UserRecruiter();
            userRecruiter.setId(openid);
            userRecruiter.setTelephone(recruitCompany.getJuridicalPhone());
            if(StringUtil.isNotEmpty(recruitCompany.getJuridicalPerson())){
                userRecruiter.setRealName(recruitCompany.getJuridicalPerson());
            }
            userRecruiter.setCompanyUuid(recruitCompany.getId());
            userRecruiterService.update(userRecruiter);
        } else {
            // 找得到招聘人电话  替换
            System.out.println(111);
            System.out.println(openid);
            RecruitCompany currentRecruitCompany = recruitCompanyList.get(0);
            recruitCompany.setId(currentRecruitCompany.getId());
            recruitCompany.setRecruiterOpenid(openid);
            recruitCompanyService.update(recruitCompany);

            //更新job的招聘人id
            Example example1 = new Example(RecruitJob.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("companyUuid",currentRecruitCompany.getId());
            List<RecruitJob> jobList=db.selectAllByExample(RecruitJob.class,example1);
            for (RecruitJob job:
            jobList) {
                job.setCompanyUuid(currentRecruitCompany.getId());
                job.setRecruiterOpenid(openid);
                db.update(job);
            }

            // 同时更新 招聘人信息
            UserRecruiter  userRecruiter = new UserRecruiter();
            userRecruiter.setId(openid);
            userRecruiter.setTelephone(recruitCompany.getJuridicalPhone());
            if(StringUtil.isNotEmpty(recruitCompany.getJuridicalPerson())){
                userRecruiter.setRealName(recruitCompany.getJuridicalPerson());
            }
            userRecruiter.setCompanyUuid(currentRecruitCompany.getId());
            userRecruiterService.update(userRecruiter);
        }
        return ActionResult.ok(recruitCompany.getId());
    }

    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody RecruitCompany recruitCompany) {
        return ActionResult.ok(update(recruitCompany) == 1);
    }

    @GetMapping("/countByCommunityUuid")
    public ActionResult<Integer> countByCommunityUuid(@RequestParam String communityUuid) {
        return ActionResult.ok(recruitCompanyService.countByCommunityUuid(communityUuid));
    }

    @GetMapping("/identifySuccess")
    public ActionResult<Boolean> identifySuccess(@RequestParam String id) {
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setFlagIdentification(AuditConstant.IdentifyStatus.SUCCESS.getValue());
        return updateVO(recruitCompany) ? ActionResult.ok() : ActionResult.error();

    }

    @RequestMapping("/identifyFail")
    public ActionResult<Boolean> identifyFail(@RequestParam String id) {
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setFlagIdentification(AuditConstant.IdentifyStatus.FAIL.getValue());
        return updateVO(recruitCompany) ? ActionResult.ok() : ActionResult.error();
    }


    @RequestMapping("/uploadPortrait")
    public ActionResult<Boolean> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            RecruitCompany recruitCompany = getEntityWithId(id);
            recruitCompany.setPortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(recruitCompany) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    /**
     * 公司相册上传
     *
     * @param id
     * @param file
     * @return com.shopping.base.foundation.result.ActionResult<java.lang.Boolean>
     */
    @RequestMapping("/uploadPhoto")
    public ActionResult<Boolean> uploadPhoto(@RequestParam String id, MultipartFile file) {
        // 取出原有路径
        String photoPath = recruitCompanyService.selectById(id).getPhotoPath();
        // 遍历files 循环上传
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            photoPath = photoPath + uploadResult.getUploadUriPath() + ";";
        }
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setPhotoPath(photoPath);
        int temp = update(recruitCompany);
        return ActionResult.ok(temp == 1);
    }

    @RequestMapping("/uploadLicensePhoto")
    public ActionResult<Boolean> uploadLicensePhoto(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            RecruitCompany recruitCompany = getEntityWithId(id);
            recruitCompany.setLicensePhotoPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(recruitCompany) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    @RequestMapping("/increaseCountView")
    public ActionResult<Boolean> increaseCountView(@RequestParam String id) {
        return recruitCompanyService.increaseCountView(id) ? ActionResult.ok() : ActionResult.error();
    }


    @Override
    protected CrudService<RecruitCompany> getService() {
        return recruitCompanyService;
    }


    private RecruitCompany getEntityWithId(String uuid) {
        RecruitCompany recruitCompany = new RecruitCompany();
        recruitCompany.setId(uuid);
        return recruitCompany;
    }

    @RequestMapping("/searchbyflag")
    public JSON searchbyflag(int pagess, String communityUuid) {
        Page page = new Page();
        JSON json = new JSON();
        page.setRows(10);
        page.setPage(pagess);
        Example example = new Example(RecruitCompany.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("flagIdentification", 0);
        criteria.andEqualTo("communityUuid", communityUuid);
        criteria.andEqualTo("status", 0);
        example.setOrderByClause(" create_time desc");
        List<RecruitCompany> recruitCompanyList = db.selectPageByExample(RecruitCompany.class, example, page);
        for (int i = 0; i < recruitCompanyList.size(); i++) {
            CommunityInformation communityInformation = db.selectById(recruitCompanyList.get(i).getCommunityUuid(), CommunityInformation.class);
            if (communityInformation != null) {
                recruitCompanyList.get(i).setCommunityUuid(communityInformation.getCommunityName());
            }

        }
        json.setObj(recruitCompanyList);
        return json;
    }


    @RequestMapping("/searchtgrz")
    public JSON searchtgrz(int pagess, String communityUuid) {
        Page page = new Page();
        JSON json = new JSON();
        page.setRows(10);
        page.setPage(pagess);
        Example example = new Example(RecruitCompany.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("communityUuid", communityUuid);
        criteria.andNotEqualTo("flagIdentification", 0);
        example.setOrderByClause(" create_time desc");
        List<RecruitCompany> recruitCompanyList = db.selectPageByExample(RecruitCompany.class, example, page);
        for (int i = 0; i < recruitCompanyList.size(); i++) {
            CommunityInformation communityInformation = db.selectById(recruitCompanyList.get(i).getCommunityUuid(), CommunityInformation.class);
            if (communityInformation != null) {
                recruitCompanyList.get(i).setCommunityUuid(communityInformation.getCommunityName());
            }

        }
        json.setObj(recruitCompanyList);
        return json;
    }

    @RequestMapping("/shtg")
    public JSON shtg(String id) {
        JSON json = new JSON();
        RecruitCompany recruitCompany = db.selectById(id, RecruitCompany.class);
        recruitCompany.setFlagIdentification(1);
        db.update(recruitCompany);
        return json;
    }

    @RequestMapping("/shbtg")
    public JSON shbtg(String id) {
        JSON json = new JSON();
        RecruitCompany recruitCompany = db.selectById(id, RecruitCompany.class);
        recruitCompany.setStatus(-1);
        recruitCompany.setFlagIdentification(-1);
        db.update(recruitCompany);
        return json;
    }
}
