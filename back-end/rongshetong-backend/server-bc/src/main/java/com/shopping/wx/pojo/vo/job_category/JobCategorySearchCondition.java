package com.shopping.wx.pojo.vo.job_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName JobCategorySearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCategorySearchCondition implements Serializable {
    String categoryName;

    String keyword;
}
