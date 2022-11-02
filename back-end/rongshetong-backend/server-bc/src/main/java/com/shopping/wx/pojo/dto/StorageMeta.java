package com.shopping.wx.pojo.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @author ljy
 * @date 2022-03-18 17:00
 */

@Data
public class StorageMeta {
    /**
     * 原始文件名称，包括了拓展名
     */
    String rawFilename;

    /**
     * 存储文件夹
     */
    String directory;

    /**
     * 输入流
     */
    InputStream inputStream;
}
