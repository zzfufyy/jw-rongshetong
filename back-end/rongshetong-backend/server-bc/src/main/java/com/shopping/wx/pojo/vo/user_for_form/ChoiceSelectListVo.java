package com.shopping.wx.pojo.vo.user_for_form;

import com.shopping.wx.model.UserForForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ChoiceSelectListVo
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceSelectListVo extends UserForForm implements Serializable {
    String optionTitle;
}
