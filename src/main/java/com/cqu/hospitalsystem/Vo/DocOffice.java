package com.cqu.hospitalsystem.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class DocOffice {
    private Long doc_id;
    private String doc_name;
    private String office_name;
    private String doc_gender;
    private int doc_age;
    private String doc_level;
    private int doc_price;
    private String doc_phone;
}
