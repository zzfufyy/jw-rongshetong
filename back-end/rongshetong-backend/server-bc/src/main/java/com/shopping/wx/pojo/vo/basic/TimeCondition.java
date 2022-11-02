package com.shopping.wx.pojo.vo.basic;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ljy
 * @date 2022-03-16 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCondition implements Serializable {
    /**
     * 开始时间
     */
    Date beginTime;
    /**
     * 结束时间
     */
    Date endTime;
}
