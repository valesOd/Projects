package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private int id;
    private Calendar date ;
    private BigDecimal amount=new BigDecimal(0);
    @Column(name = "date_from")
    private Calendar dateFrom ;
    @Column(name = "date_by")
    private Calendar dateBy ;
    @ManyToOne
    @JoinColumn(name="id_appointment_payment")
    private AppointmentPayment appointmentPayment;
    @ManyToOne
    @JoinColumn(name = "id_personal_info")
    private PersonalInfo personalInfo ;

    public AppointmentPayment getAppointmentPayment() {
        return appointmentPayment;
    }

    public void setAppointmentPayment(AppointmentPayment appointmentPayment) {
        this.appointmentPayment = appointmentPayment;
    }

    public Payment(Calendar date, Calendar dateFrom, Calendar dateBy) {
        this.date = date;
        this.dateFrom = dateFrom;
        this.dateBy = dateBy;
    }

    public Payment(){

    }

    public int getId() {
        return id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date.getTime();
    }

    public void setDate(Date date) {
        this.date.setTime(date);
    }

    public Date getDateFrom() {
        return dateFrom.getTime();
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom.setTime(dateFrom);
    }

    public Date getDateBy() {
        return dateBy.getTime();
    }

    public void setDateBy(Date dateBy) {
        this.dateBy.setTime(dateBy);
    }
}