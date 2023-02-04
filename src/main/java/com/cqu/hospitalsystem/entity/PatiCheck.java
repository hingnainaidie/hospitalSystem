package com.cqu.hospitalsystem.entity;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class PatiCheck implements Serializable{
    private String patientName;
    private String patientIdentify;
    private Date prescriptionTime;
    private Time prescriptionTimeplus;
    private Long prescriptionId;
    private Long clistId;
    private Long citemId;
    private Integer amount;
    private String citemName;
    private String citemPart;
    private Double pricePer;
    private Integer cliststate;
    private Integer citemstate;
    private Long itemListId;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public String getPrescriptionTime() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");//这里可以指定日期的格式
        SimpleDateFormat sdf1 =new SimpleDateFormat("hh:mm:ss");//这里可以指定日期的格式
        //System.out.println(prescriptionTime);
        //System.out.println(prescriptionTimeplus);
        String timeByDate = sdf.format(prescriptionTime)+" "+sdf1.format(prescriptionTimeplus);
        return timeByDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPatientIdentify() {
        return patientIdentify;
    }

    public Double getPricePer() {
        return pricePer;
    }

    public Integer getCliststate() {
        return cliststate;
    }

    public Integer getCitemstate() {
        return citemstate;
    }

    public Long getCitemId() {
        return citemId;
    }

    public Long getClistId() {
        return clistId;
    }

    public Long getItemListId() {
        return itemListId;
    }

    public String getCitemName() {
        return citemName;
    }

    public String getCitemPart() {
        return citemPart;
    }

    public void setCliststate(Integer cliststate) {
        this.cliststate = cliststate;
    }

    public void setCitemstate(Integer citemstate) {
        this.citemstate = citemstate;
    }
}
