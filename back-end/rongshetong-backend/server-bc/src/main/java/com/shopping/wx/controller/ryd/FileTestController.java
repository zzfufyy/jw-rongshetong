package com.shopping.wx.controller.ryd;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.pojo.dto.StorageMeta;
import com.shopping.wx.service.basic.ObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ljy
 * @date 2022-03-18 17:41
 */

@RestController("/test/file")
public class FileTestController {

    @Autowired
    ObjectStorageService objectStorageService;

    @PostMapping("/upload")
    ActionResult<String> upload(MultipartFile multipartFile) {
        StorageMeta storageMeta = new StorageMeta();
        try {
            storageMeta.setInputStream(multipartFile.getInputStream());
            storageMeta.setRawFilename(multipartFile.getOriginalFilename());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return ActionResult.error();
        }

        objectStorageService.save(storageMeta);

        return ActionResult.ok();
    }
}
