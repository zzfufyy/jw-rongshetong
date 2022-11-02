package com.shopping.wx.controller.sqjy;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_recruiter.UserRecruiterSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import com.shopping.wx.util.UUIDUtils;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

/**
 * @ClassName UserRecruiterController
 * @Description 招聘人相关接口
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("user-recruiter")
public class UserRecruiterController extends CrudController<UserRecruiter, String> {
    @Autowired
    private UserRecruiterService userRecruiterService;
    @Autowired
    private RecruitCompanyService recruitCompanyService;
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/page")
    public ActionResult<PageInfo<UserRecruiter>> page(@RequestBody PagingParam<UserRecruiterSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userRecruiterService.page(pagingParam))
        );
    }

    @GetMapping("/info")
    public ActionResult<UserRecruiter> info(@RequestParam String id) {
        return ActionResult.ok(userRecruiterService.selectById(id));
    }
    @GetMapping("/infoPlus")
    public ActionResult<UserRecruiter> infoPlus(@RequestParam String id) {
        return ActionResult.ok(userRecruiterService.infoPlus(id));
    }

    @RequestMapping("/add")
    public ActionResult<?> add(@RequestBody UserRecruiter userRecruiter) {
        // 找不到再插入
        if (ObjectUtils.isEmpty(userRecruiterService.selectById(userRecruiter.getId()))) {
            if (StringUtil.isNotEmpty(userRecruiter.getRealName())) {
                String nickName = EmojiParser.parseToHtmlDecimal(userRecruiter.getRealName());
                userRecruiter.setRealName(nickName);
            }else{
                String nickName = AuditConstant.VISTOR_NAME_PREFIX + UUIDUtils.gen8CharUuid();
                userRecruiter.setRealName(nickName);
            }
            insert(userRecruiter);
        }
        return ActionResult.ok();
    }

    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody UserRecruiter userRecruiter) {

        userRecruiterService.update(userRecruiter);
        // 如果有更新 招聘联系人名字  联动更新公司表  招聘人名字
        if (StringUtil.isNotEmpty(userRecruiter.getRealName())) {
            UserRecruiter queryUserRecruiter = userRecruiterService.selectById(userRecruiter.getId());
            if (StringUtil.isNotEmpty(queryUserRecruiter.getCompanyUuid())) {
                RecruitCompany queryRecruitCompany = recruitCompanyService.selectById(queryUserRecruiter.getCompanyUuid());
                Optional.ofNullable(queryRecruitCompany).ifPresent(v -> {
                    queryRecruitCompany.setJuridicalPerson(userRecruiter.getRealName());
                    recruitCompanyService.update(queryRecruitCompany);
                });
            }


        }

        return ActionResult.ok();
    }

    @RequestMapping("/uploadPortrait")
    public ActionResult<?> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            UserRecruiter userRecruiter = getEntityWithId(id);
            userRecruiter.setPortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(userRecruiter) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    private UserRecruiter getEntityWithId(String openid) {
        UserRecruiter userRecruiter = new UserRecruiter();
        userRecruiter.setId(openid);
        return userRecruiter;
    }

    @Override
    protected CrudService<UserRecruiter> getService() {
        return userRecruiterService;
    }
    /**
     * 保存微信头像
     * @line https://blog.csdn.net/neu_yousei/article/details/22413855
     * @param wechatHeadimgurl 微信头像url
     */
    @RequestMapping("/uploadPortraitbyopenid")
    public JSONObject saveWechatHeadimg(String wechatHeadimgurl, String openid) {
        JSONObject jsonObject = new JSONObject();
        if (wechatHeadimgurl != null) {
            InputStream inputStream = null;
            HttpURLConnection httpURLConnection = null;
            FileOutputStream fileOutputStream = null;
            try {
                URL url = new URL(wechatHeadimgurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                // 设置网络连接超时时间
                httpURLConnection.setConnectTimeout(10000);
                // 设置应用程序要从网络连接读取数据
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    // 从服务器返回一个输入流
                    inputStream = httpURLConnection.getInputStream();
                }
                if (inputStream != null) {
                    // 创建文件夹
                    String filePath = "D://data/file" + "/";
                    String code = UUID.randomUUID().toString();
                    String fileName = code + ".png";

                    byte[] data = new byte[1024];
                    int len;
                        File file = new File(filePath);
                        if (!file.exists()) {
                            file.mkdir();
                        }
                    // 文件写入
                    fileOutputStream = new FileOutputStream(filePath + fileName);
                    while ((len = inputStream.read(data)) != -1) {
                        fileOutputStream.write(data, 0, len);
                    }
                    jsonObject.put("path","/images/"+fileName);
                    UserRecruiter userRecruiter = getEntityWithId(openid);
                    userRecruiter.setPortraitPath("/images/"+fileName);
                    update(userRecruiter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return jsonObject;
    }

}
