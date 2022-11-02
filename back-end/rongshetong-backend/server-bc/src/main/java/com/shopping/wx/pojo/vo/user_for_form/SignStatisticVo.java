package com.shopping.wx.pojo.vo.user_for_form;

import com.shopping.wx.model.UserForForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName SignStatisticVo
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignStatisticVo extends UserForForm implements Serializable  {
    String userName;
}
