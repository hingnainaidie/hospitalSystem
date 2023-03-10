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
public class DocReg {
    private Long regId;
    private Long docId;
    private String docName;
    private Date regTime;
    private Date appointmentTime;
}
