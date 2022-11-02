package com.shopping.wx.pojo.vo.user_for_form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserForFormSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/7/5
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForFormSearchCondition implements Serializable {
    String formUuid;
    String subjectUuid;
    String userOpenid;
}
