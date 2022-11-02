package com.shopping.wx.service.basic;

import com.shopping.wx.pojo.dto.StorageMeta;

import java.io.InputStream;

/**
 * 对象存储服务(OSS)
 * 提供了对象存储的接口
 *
 * @author ljy
 * @date 2022-03-18 16:06
 */

public interface ObjectStorageService {
    /**
     * 以流的形式存储对象
     *
     * @param storageMeta 对象元素数
     * @return 唯一标识符。如果返回值为 null，则失败
     */
    String save(StorageMeta storageMeta);

    /**
     * @param key 唯一标识符
     * @return 对象元数据，调用者需要手动关闭数据源
     */
    StorageMeta load(String key);
}
