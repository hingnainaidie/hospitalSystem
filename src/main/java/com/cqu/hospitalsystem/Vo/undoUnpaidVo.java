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
 * 就诊中的详情
 */
public class undoUnpaidVo {
    private Long regId;    //挂号单ID
    private String docName;    //医生名称
    private Date appointmentTime; //预约时间
    private Date regTime;    //挂号时间
    private String officeName; //就诊科室
    private Integer state;    //挂号单状态
    private Long queueNumber;  //排队号
    private Long recordId;       //病历Id
    private Long prescriptionId; //处方单Id
    private Long checklistId;  //检查单Id
}
