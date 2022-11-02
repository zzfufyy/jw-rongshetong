package com.shopping.wx.pojo.vo.form_subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FormSubjectSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/7/4
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormSubjectSearchCondition implements Serializable {
    String formUuid;
}
