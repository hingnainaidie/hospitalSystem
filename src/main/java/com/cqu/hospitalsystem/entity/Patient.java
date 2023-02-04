package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Patient)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:01:14
 */
public class Patient implements Serializable {
    private static final long serialVersionUID = -65810196101504572L;
    
    private Long patientId;
    
    private String patientName;
    
    private String patientGender;
    
    private String patientIdentify;
    
    private String patientPhone;
    
    private String patientPassword;
    
    private Integer patientAge;
    
    private String patientAddr;


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientIdentify() {
        return patientIdentify;
    }

    public void setPatientIdentify(String patientIdentify) {
        this.patientIdentify = patientIdentify;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientAddr() {
        return patientAddr;
    }

    public void setPatientAddr(String patientAddr) {
        this.patientAddr = patientAddr;
    }

}

