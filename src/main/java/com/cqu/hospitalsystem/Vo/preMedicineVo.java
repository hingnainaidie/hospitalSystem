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
 * 处方单药品列表
 */
public class preMedicineVo {

    private Long regId;

    private Long prescriptionId;

    private Long medicineId;

    private Date appointmentTime;

    private String medicineName;

    private String specification;

    private Integer amount;

    private String frequency;

    private String dosagePer;

    private Double pricePer;

    private int state;    //状态（0：未使用，1：已使用，2：已退费）

    //(0:未缴费，1：等待配药 2：配药中 3：配药完成 4：已取走)
    private Integer prescriptionStatement;

    //总价
    private Double totalPrice=0.0;
}
