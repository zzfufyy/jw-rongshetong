package com.shopping.wx.pojo.dto.user_recruiter;

import com.shopping.wx.model.UserRecruiter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserRecruiterPlusWithCompany
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecruiterPlus extends UserRecruiter implements Serializable {
    // 是否认证
    String companyName;
    Integer flagIdentification;

}
