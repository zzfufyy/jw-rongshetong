package com.shopping.wx.service.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName UploadService
 * @Description 上传服务
 * @Author zyw
 * @Date 2022/3/18
 **/
public interface UploadService {

    int FILE_MAX_SIZE = 1024 * 1024 * 100;

    /**
     * 保存上传结果
     */
    @Data
    @AllArgsConstructor
    class UploadResult {
        Boolean success;
        String msg;
        String uploadUriPath;
    }

    /**
     * TODO
     *
     * @param file      上传文件
     * @param customDir 自定义前缀文件路径
     * @return com.shopping.wx.service.basic.UploadService.UploadResult
     */
    UploadResult uploadFile(MultipartFile file, String customDir);


    /**
     * TODO
     *
     * @param file 上传文件
     * @return com.shopping.wx.service.basic.UploadService.UploadResult
     */
    UploadResult uploadFile(MultipartFile file);
}
