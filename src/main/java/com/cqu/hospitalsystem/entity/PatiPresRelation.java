package com.cqu.hospitalsystem.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * (PatiPresRelation)实体类
 *
 * @author nkx
 * @since 2022-01-01
 */
public class PatiPresRelation implements Serializable{

    private String patientIdentify;
    private String patientName;
    private Long medicineId;
    private Integer amount;
    private String medicineName;
    private Double pricePer;
    private Long prescriptionId;
    private Date prescriptionTime;
    private Time prescriptionTimeplus;
    private Integer prestate;
    private Integer state;
    private Long connectId;

    public Double getPricePer() {
        return pricePer;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getPatientIdentify() {
        return patientIdentify;
    }

    /*public String getPrestate() {
        if(prestate==0){
            return "未缴费";
        }
        if(prestate==1){
            return "等待配药";
        }
        if(prestate==2){
            return "正在配药";
        }
        if(prestate==3){
            return "正在发药";
        }
        if(prestate==4){
            return "已取走";
        }
        else{
            return "错误";
        }

    }*/

    public Long getConnectId() {
        return connectId;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getPrestate() {
        return prestate;
    }

    public Integer getState() {
        return state;
    }
    /*public String getState() {
        if(state == 0){
            return "未取走";
        }
        if(state ==1 ){
            return "已取走";
        }
        if(state ==2){
            return "已退费";
        }
        else {
            return "错误";
        }
    }*/

    public String getPrescriptionTime() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");//这里可以指定日期的格式
        SimpleDateFormat sdf1 =new SimpleDateFormat("hh:mm:ss");//这里可以指定日期的格式
        //System.out.println(prescriptionTime);
        //System.out.println(prescriptionTimeplus);
        String timeByDate = sdf.format(prescriptionTime)+" "+sdf1.format(prescriptionTimeplus);
        return timeByDate;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrestate(Integer prestate) {
        this.prestate = prestate;
    }
}
