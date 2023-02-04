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
 * 处方单详情
 */
public class prescriptionVo {
    //挂号单ID
    private Long regId;

    private Long prescriptionId; //处方单Id
    //预约时间
    private Date appointmentTime;
    //就诊时间
    private Date prescriptionTime;
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
    //处方状态
    private Integer prescriptionStatement;
}
