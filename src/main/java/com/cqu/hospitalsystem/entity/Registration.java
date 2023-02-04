package com.cqu.hospitalsystem.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Registration)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:02:19
 */
public class Registration implements Serializable {
    private static final long serialVersionUID = -38474558279215524L;
    
    private Long regId;
    
    private Long docId;
    
    private Long patientId;
    
    private Date regTime;
    
    private Date appointmentTime;
    
    private Long queueNumber;
    
    private Integer state;


    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Long getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Long queueNumber) {
        this.queueNumber = queueNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}

