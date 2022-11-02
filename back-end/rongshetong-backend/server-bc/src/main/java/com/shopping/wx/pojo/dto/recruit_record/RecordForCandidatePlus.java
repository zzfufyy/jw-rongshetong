package com.shopping.wx.pojo.dto.recruit_record;

import com.shopping.wx.model.RecruitRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RecordForCandidatePlus
 * @Description 招聘列表 plus FOR 求职者
 * @Author zyw
 * @Date 2022/7/29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordForCandidatePlus extends RecruitRecord implements Serializable {
    String recruitCompanyName;
    String recruitJuridicalPerson;
    String recruitJuridicalPhone;
    String recruitPortraitPath;
    String recruitJobName;
    Integer recruitJobSalaryMin;
    Integer recruitJobSalaryMax;
}
