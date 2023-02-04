package com.cqu.hospitalsystem.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Prescription)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:01:48
 */
public class Prescription implements Serializable {
    private static final long serialVersionUID = -43215136342299031L;
    
    private Long prescriptionId;
    
    private Date prescriptionTime;
    
    private Integer prescriptionStatement;


    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Date getPrescriptionTime() {
        return prescriptionTime;
    }

    public void setPrescriptionTime(Date prescriptionTime) {
        this.prescriptionTime = prescriptionTime;
    }

    public Integer getPrescriptionStatement() {
        return prescriptionStatement;
    }

    public void setPrescriptionStatement(Integer prescriptionStatement) {
        this.prescriptionStatement = prescriptionStatement;
    }

}

