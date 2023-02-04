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
 * 历史预约记录详情
 */
public class historyDetail {
    private Long idRelationId;
    private Long regId;
    private Long recordId;       //病历Id
    private Long prescriptionId; //处方单Id
    private Long checklistId;  //检查单Id
    private String officeName; //就诊科室
    private String docName;    //医生名称
    private Date appointmentTime; //预约时间
    private Date prescriptionTime; //就诊时间
    private Integer prescriptionStatement; //缴费状态
}
