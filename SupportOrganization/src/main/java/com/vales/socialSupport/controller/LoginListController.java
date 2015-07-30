package com.vales.socialSupport.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.Login;
import com.vales.socialSupport.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ManagedBean
@ViewScoped
public class LoginListController extends Controller<Login> {


    @Setter
	@ManagedProperty("#{loginService}")
	private LoginService loginService;
    @Setter
    @ManagedProperty("#{personalInfoListController}")
    PersonalInfoListController personalInfoListController;

    private void initializationTypeEntity(){
        Login login = new Login();
        setTypeEntity(login);
    }
    @Override
    protected void initialization() {
        setService(loginService);
        setAuxiliaryController(personalInfoListController);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new Login());
    }

}
