package com.shopping.wx.pojo.vo.user_for_form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DetailUserVo
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailUserFormVo implements Serializable {
    String formUuid;
    String userOpenid;
    String userName;
    Date submitTime;
}
