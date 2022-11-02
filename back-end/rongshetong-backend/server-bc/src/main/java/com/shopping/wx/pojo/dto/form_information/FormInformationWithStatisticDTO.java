package com.shopping.wx.pojo.dto.form_information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FormInformationWithStatisticDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/7/1
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormInformationWithStatisticDTO {
    String formUuid;
    String formTitle;
    Integer formFillThisDay;
    Integer formFillThisMonth;
    Integer formFillTotal;
}
