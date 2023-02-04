package com.cqu.hospitalsystem.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class RegRelation {
    private Long reg_id;
    private int reg_state;
    private String doc_name;
    private String office_name;
    private Date appointment_time;
}
