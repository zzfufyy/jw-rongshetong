package com.shopping.wx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ljy
 * @date 2022-03-18 17:08
 */

@Configuration
public class StorageConfig {


    @Value("${spring.servlet.multipart.location}")
    private String basePath;

    @Value("${storage/image:image}")
    private String imagePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
