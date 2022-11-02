package com.shopping.wx.pojo.vo.form_information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FormInformationSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormInformationSearchCondition implements Serializable {
    String userOpenid;
    String communityUuid;
    Integer status;
}
