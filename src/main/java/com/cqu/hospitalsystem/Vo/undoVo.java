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
 * 未完成预约记录列表
 */
public class undoVo {
    private Long regId;    //挂号单ID
    private String docName;    //医生名称
    private Date appointmentTime; //预约时间
    private Date regTime;    //挂号时间
    private String officeName; //就诊科室
    private Integer state;    //挂号单状态
    private Long queueNumber;  //排队号
}
