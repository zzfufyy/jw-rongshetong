package com.shopping.wx.pojo.dto.form_subject_option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ChoiceSelectDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceSelectDTO {
    Integer optionOrder;
    String optionTitle;
    String SubjectUuid;
    String formUuid;

    Integer countOptionNum;
    Integer countUserNum;
}
