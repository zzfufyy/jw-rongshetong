package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.config.StorageConfig;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidatePlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import com.shopping.wx.util.UUIDUtils;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Optional;

/**
 * 应聘者用户控制器
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@RestController()
@RequestMapping("/user-candidate")
public class UserCandidateController extends CrudController<UserCandidate, String> {

    final
    UserCandidateService userCandidateService;
    @Autowired
    private DB db;

    final UploadService uploadService;

    final StorageConfig storageConfig;

    public UserCandidateController(UserCandidateService userCandidateService, UploadService uploadService, StorageConfig localStorageConfig, StorageConfig storageConfig) {
        this.userCandidateService = userCandidateService;
        this.uploadService = uploadService;
        this.storageConfig = storageConfig;
    }

    @RequestMapping("/add")
    ActionResult<?> add(@RequestBody UserCandidate userCandidate) {
        // 找不到再插入

        if (ObjectUtils.isEmpty(userCandidateService.selectById(userCandidate.getId()))) {
            if(StringUtil.isNotEmpty(userCandidate.getRealName())){
                String nickName = EmojiParser.parseToHtmlHexadecimal(userCandidate.getRealName());
                userCandidate.setRealName(nickName);
            }else{
                String nickName = AuditConstant.VISTOR_NAME_PREFIX + UUIDUtils.gen8CharUuid();
                userCandidate.setRealName(nickName);
            }
            insert(userCandidate);
        } else {

        }
        return ActionResult.ok();
    }

    @RequestMapping("/info")
    public ActionResult<UserCandidate> info(String openid) {
        return ActionResult.ok(query(openid));
    }

    @RequestMapping("/infoPlus")
    public ActionResult<UserCandidatePlus> infoPlus(String openid) {
        return ActionResult.ok(userCandidateService.infoPlus(openid));
    }
    @RequestMapping("/uploadPortraitFile")
    public ActionResult<?> uploadPortraitFile(String id, String ext1, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            UserCandidate userCandidate = new UserCandidate();
            userCandidate.setId(id);
            userCandidate.setResume(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(userCandidate) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }
    @RequestMapping("/modify")
    ActionResult<?> modify(@RequestBody UserCandidate userCandidate) {
        Example example = new Example(UserCandidate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("telephone", userCandidate.getTelephone());
        criteria.andEqualTo("ext2", "sqll");
        List<UserCandidate> userCandidateList = db.selectAllByExample(UserCandidate.class, example);
        if (userCandidateList.size() == 0) {
            update(userCandidate);
        } else {
            userCandidate.setRealName(userCandidateList.get(0).getRealName());
            userCandidate.setFlagIdentification(userCandidateList.get(0).getFlagIdentification());
            userCandidate.setIdentityCard(userCandidateList.get(0).getIdentityCard());
            userCandidate.setGender(userCandidateList.get(0).getGender());
            userCandidate.setAddress(userCandidateList.get(0).getAddress());
            userCandidate.setIntroduction(userCandidateList.get(0).getAddress());
            //暂时不考虑把求职意向工资考入
//            userCandidate.setExpectSalaryMin(userCandidateList.get(0).getExpectSalaryMin());
//            userCandidate.setExpectSalaryMax(userCandidateList.get(0).getExpectSalaryMax());
            update(userCandidate);
        }

        return ActionResult.ok();
    }
    @PostMapping("updateByEduInfo")
    public ActionResult<?> updateByEduInfo(@RequestBody UserCandidate userCandidate){
        UserCandidate updateEntity = new UserCandidate();
        updateEntity.setId(userCandidate.getId());
        Optional.ofNullable(userCandidate.getEduSchoolName()).ifPresent(v ->updateEntity.setEduSchoolName(v) );
        Optional.ofNullable(userCandidate.getEduBackgroundName()).ifPresent(v ->updateEntity.setEduBackgroundName(v) );
        Optional.ofNullable(userCandidate.getEduMajor()).ifPresent(v ->updateEntity.setEduMajor(v) );
        Optional.ofNullable(userCandidate.getEduBeginTime()).ifPresent(v->updateEntity.setEduBeginTime(v));
        Optional.ofNullable(userCandidate.getEduEndTime()).ifPresent(v->updateEntity.setEduEndTime(v));
        return ActionResult.ok(userCandidateService.update(updateEntity));
    }



    @PostMapping("/paged-by-distance")
    ActionResult<PageInfo<UserCandidateDTO>> pagedByDistance(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer jobSalaryMin,
            @RequestParam(required = false) Integer jobSalaryMax,
            @RequestBody PagingParam<Location> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userCandidateService.pagedByDistance(categoryName, jobSalaryMin, jobSalaryMax, pagingParam))
        );
    }


    @PostMapping("/pagedByCondition")
    ActionResult<PageInfo<UserCandidateDTO>> pagedByCondition(@RequestBody PagingParam<UserCandidateSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userCandidateService.pagedByCondition(pagingParam))
        );
    }


    /**
     * 查看用户，例如查看简历，需要自增，这里仅做测试用途
     */
    @GetMapping("/check")
    @Deprecated
    public ActionResult<Boolean> check(String openid) {
        userCandidateService.increaseViewCount(openid);
        return ActionResult.ok();
    }

    @RequestMapping("/page")
    public ActionResult<PageInfo<UserCandidate>> page(
            @RequestBody PagingParam<UserCandidateSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(userCandidateService.page(pagingParam))
        );
    }

    @RequestMapping("/uploadPortrait")
    public ActionResult<?> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            UserCandidate userCandidate = getEntityWithId(id);
            userCandidate.setPortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(userCandidate) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    @RequestMapping("/increaseCountView")
    public ActionResult<?> increaseViewCount(@RequestParam String id) {
        userCandidateService.increaseViewCount(id);
        return ActionResult.ok();
    }


    @Override
    protected CrudService<UserCandidate> getService() {
        return userCandidateService;
    }

    private UserCandidate getEntityWithId(String openid) {
        UserCandidate userCandidate = new UserCandidate();
        userCandidate.setId(openid);
        return userCandidate;
    }
}
