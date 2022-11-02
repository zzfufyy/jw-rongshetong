package com.shopping.wx.pojo.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljy
 * @date 2022-03-25 14:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    /**
     * 经度
     */
    Double longitude;
    /**
     * 纬度
     */
    Double latitude;
}
