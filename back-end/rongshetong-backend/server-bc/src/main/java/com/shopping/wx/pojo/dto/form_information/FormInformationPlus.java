package com.shopping.wx.pojo.dto.form_information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FormInformationPlus
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormInformationPlus implements Serializable {
    String formUuid;
    String formTitle;
    Date beginTime;
    Date endTime;
    Integer completedCount;
    Integer status;
}
