package com.shopping.wx.pojo.dto.candidate_for_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CandidateForCategoryDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateForCategoryDTO implements Serializable {
    String id;
    String candidateOpenid;
    String categoryUuid;
    String categoryName;

}
