package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (PresMedi)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:01:29
 */
public class PresMedi implements Serializable {
    private static final long serialVersionUID = -16277754459683451L;
    
    private Long connectId;
    
    private Long prescriptionId;
    
    private Long medicineId;
    
    private Integer amount;
    
    private Integer state;


    public Long getConnectId() {
        return connectId;
    }

    public void setConnectId(Long connectId) {
        this.connectId = connectId;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}

