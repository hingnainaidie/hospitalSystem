package com.cqu.hospitalsystem.Vo;


import lombok.*;

import java.util.Date;

@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 检查项目详情
 */
public class checklistVo {
    //挂号单ID
    private Long regId;
    //检查单Id
    private Long checklistId;
    //预约时间
    private Date appointmentTime;
    //开处方单时间
    private Date prescribeTime;
    //就诊医生
    private String docName;
    //医生性别
    private String docGender;
    //医生年龄
    private Integer docAge;
    //医生电话
    private String docPhone;
    //就诊科室
    private String officeName;
    //就诊类型
    private String recordType;
    //诊断结果
    private String recordResult;
    //检查单状态
    private Integer checklistStatus;
}
