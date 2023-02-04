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
 * 处方单检查单列表
 */
public class preCheck {
    private Long regId;

    private Long checklistId;

    private Long checkitemId;

    private String officeName;

    private Date appointmentTime;

    private String checkitemName;

    private String checkPart;

    private Double checkitemPrice;

    private String checkitemNote;

    private Integer checklistStatus;

    private Integer state;  //检查状态在检查单和检查项目关联表中（0：未检查，1：已检查，2：已退费，3：全部检查项目完成）

    private Double totalPrice=0.0;//总价
}
