package com.shopping.wx.pojo.vo.job_fair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName JobFairSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobFairSearchCondition implements Serializable {
    String fairTitle;
}
