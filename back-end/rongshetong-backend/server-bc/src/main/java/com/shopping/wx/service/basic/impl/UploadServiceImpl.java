package com.shopping.wx.service.basic.impl;

import com.shopping.wx.service.basic.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ClassName UploadServiceImpl
 * @Description 上传实现类
 * @Author zyw
 * @Date 2022/3/18
 **/
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    protected Environment environment;


    private String getFileSavePath() {
        String fileSavePath = environment.getProperty("spring.resources.static-locations");
        return StringUtil.isNotEmpty(fileSavePath) ?
                Arrays.stream(fileSavePath.trim().split(",")).
                        filter(s -> s.contains("file:")).
                        map(s -> s.replaceAll("file:", "")).
                        collect(Collectors.toList()).get(0) + "/"
                : "";

    }

    private String getFileUriPath() {
        String fileUriPath = environment.getProperty("spring.mvc.static-path-pattern");
        return StringUtil.isNotEmpty(fileUriPath) ? fileUriPath.replaceAll("\\*", "") : "";

    }


    /**
     * TODO
     *
     * @param file
     * @param customDir 自定义dir
     * @return com.shopping.wx.service.basic.UploadService.UploadResult
     */
    @Override
    public UploadResult uploadFile(MultipartFile file, String customDir) {
        // 获取 上传路径
        String fileSavePath = getFileSavePath();
        String fileUriPath = getFileUriPath();

        if (StringUtil.isEmpty(fileSavePath)) {
            return new UploadResult(false, "未设置服务器上传目录", null);
        } else if (ObjectUtils.isEmpty(file) || file.getSize() <= 0) {
            return new UploadResult(false, "无上传文件", null);
        } else if (file.getSize() > FILE_MAX_SIZE) {
            return new UploadResult(false, "上传文件大小不得超过100M", null);
        }
        String fileName = file.getOriginalFilename();
        //  匹配资源映射路径 去除*
        String uri_path = fileUriPath.concat(customDir + "/");
        //  物理映射路径
        String real_path = fileSavePath.concat(customDir + "/");
        String newFileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileName.substring(fileName.lastIndexOf("."));

        File targetFile = new File(real_path, newFileName);
        try {
            // 父级目录判断
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            file.transferTo(targetFile);
        } catch (IOException e) {
            log.error("upload file fail: \n", e);
            return new UploadResult(false, "上传文件失败", real_path + newFileName);
        }
        log.info("upload File Success  ", uri_path + newFileName);
        return new UploadResult(true, "上传文件成功", uri_path + newFileName);
    }

    /**
     * TODO
     *
     * @param file 上传文件
     * @return com.shopping.wx.service.basic.UploadService.UploadResult
     */
    @Override
    public UploadResult uploadFile(MultipartFile file) {
        return uploadFile(file, "");
    }


}
