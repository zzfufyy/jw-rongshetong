package com.shopping.wx.pojo.dto.user_candidate;

import com.shopping.wx.model.UserCandidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserCandidatePlus
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCandidatePlus extends UserCandidate implements Serializable {

    String communityName;
}
