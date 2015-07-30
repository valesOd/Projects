package com.vales.socialSupport.controller;


import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.Payment;
import com.vales.socialSupport.service.PaymentService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@ManagedBean
@ViewScoped
public class PaymentListController extends Controller<Payment> {

	@Setter
	@ManagedProperty("#{paymentService}")
	private PaymentService paymentService;

    @Setter

    @ManagedProperty("#{personalInfoListController}")
    private PersonalInfoListController personalInfoListController;

    private void initializationTypeEntity(){
        Payment payment = new Payment();
        setTypeEntity(payment);
    }
    @Override
    protected void initialization() {
        setService(paymentService);
        initializationTypeEntity();
        setAuxiliaryController(personalInfoListController);
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new Payment());
    }

}
