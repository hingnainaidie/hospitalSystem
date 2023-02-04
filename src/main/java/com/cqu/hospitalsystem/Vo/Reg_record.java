package com.cqu.hospitalsystem.Vo;


import com.cqu.hospitalsystem.entity.Medicalrecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//以下四条可以省去自定义get和set函数
@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class Reg_record {
    private Long regId;
    private Medicalrecord medicalrecord;

    public Long getRegId() {
        return regId;
    }
    public  void setRegId(Long regId){
        this.regId=regId;
    }
    public void setMedicalrecord(Medicalrecord medicalrecord){
        this.medicalrecord=medicalrecord;
    }
    public Medicalrecord getMedicalrecord() {
        return medicalrecord;
    }
}