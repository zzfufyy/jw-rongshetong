package com.shopping.wx.pojo.vo;

import com.shopping.wx.pojo.vo.common.Location;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BasicLocationDTO
 * @Description 带 位置的 抽象类
 * @Author zyw
 * @Date 2022/5/5
 **/
@Data
public abstract class BasicLocationSearchCondition implements Serializable {

    Double longitude;

    Double latitude;
}
