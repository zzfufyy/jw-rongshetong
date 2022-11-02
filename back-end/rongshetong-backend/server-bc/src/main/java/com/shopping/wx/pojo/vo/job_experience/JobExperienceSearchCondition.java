package com.shopping.wx.pojo.vo.job_experience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName JobExperienceSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceSearchCondition implements Serializable {
    String candidateOpenid;
    Integer jobType;
}
