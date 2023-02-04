package com.cqu.hospitalsystem.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class MedicineAmount {
    private Long medicineId;

    private String medicineName;

    private Integer num;

    private String frequency;

    private String dosagePer;

    private Double pricePer;

    private String specification;

}
