package com.shopping.wx.pojo.dto.view_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ViewRecordDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewRecordDTO implements Serializable {
    private String id;
    private String candidateOpenid;
    private String companyUuid;
    private String companyName;
    private String portraitPath;
    private String phone;
    private Double distance;
}
