package com.shopping.wx.service.basic.impl;

import com.shopping.wx.config.StorageConfig;
import com.shopping.wx.pojo.dto.StorageMeta;
import com.shopping.wx.service.basic.ObjectStorageService;
import com.shopping.wx.util.StorageUtils;
import com.shopping.wx.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

/**
 * 本地对象存储实现
 *
 * @author ljy
 * @date 2022-03-18 16:07
 */

@Slf4j
@Service
public class LocalObjectStorageServiceImpl implements ObjectStorageService {
    final
    StorageConfig localStorageConfig;

    public LocalObjectStorageServiceImpl(StorageConfig localStorageConfig) {
        this.localStorageConfig = localStorageConfig;
    }

    /**
     * 以流的形式存储对象
     *
     * @param storageMeta 对象元素数
     * @return 唯一标识符
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String save(StorageMeta storageMeta) {
        String uuid = UUIDUtils.timeBaseUUID();
        // TODO: 更新数据库

        String targetPath = getTargetPath(storageMeta, uuid);

        try {
            StorageUtils.createDirOrFile(targetPath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            log.error("Create file with path [{}] failed because of {}", targetPath, ioException);
            return null;
        }

        FileOutputStream outputStream = null;
        InputStream inputStream = storageMeta.getInputStream();
        try {
            outputStream = new FileOutputStream(targetPath);
            IOUtils.copy(storageMeta.getInputStream(), outputStream);
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
            log.error("Target file with path [{}] failed because of {}", targetPath, fne);
            return null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            log.error("Save file with path [{}] failed because of {}", targetPath, ioException);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    log.error("Unexpected ioException when close stream", ioException);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    log.error("Unexpected ioException when close stream", ioException);
                }
            }
        }

        return uuid;
    }

    /**
     * @param key 唯一标识符
     * @return 对象元数据
     */
    @Override
    public StorageMeta load(String key) {
        // TODO: 通过 key 查询数据信息
        StorageMeta storageMeta = new StorageMeta();

        String targetPath = getTargetPath(storageMeta, key);

        try {
            FileInputStream inputStream = new FileInputStream(targetPath);
            storageMeta.setInputStream(inputStream);

            return storageMeta;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Can not find the target file [{}] because of {}", targetPath, e);
            return null;
        }
    }


    private String getTargetPath(StorageMeta storageMeta, String uuid) {
        return StorageUtils.pathJoin(
                localStorageConfig.getBasePath(),
                storageMeta.getDirectory(),
                uuid
        );
    }
}
