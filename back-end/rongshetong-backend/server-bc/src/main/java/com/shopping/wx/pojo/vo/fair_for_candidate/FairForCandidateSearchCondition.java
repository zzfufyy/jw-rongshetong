package com.shopping.wx.pojo.vo.fair_for_candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FairForCandidateSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FairForCandidateSearchCondition implements Serializable {
    String fairUuid;
    String candidateName;
}
