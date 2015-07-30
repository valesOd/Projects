package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.service.AppointmentPaymentService;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.LinkedList;


@ManagedBean
@ViewScoped
public class AppointmentPaymentListController extends Controller<AppointmentPayment> {

    @Setter
    @ManagedProperty("#{appointmentPaymentService}")
    private AppointmentPaymentService appointmentPaymentService;

    private void initializationTypeEntity(){
        AppointmentPayment appointmentPayment = new AppointmentPayment();
        setTypeEntity(appointmentPayment);
    }
    @Override
    protected void initialization() {
        setService(appointmentPaymentService);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new AppointmentPayment());
    }


}
