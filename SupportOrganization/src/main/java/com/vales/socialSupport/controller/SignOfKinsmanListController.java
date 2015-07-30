package com.vales.socialSupport.controller;


import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.SignOfKinsman;
import com.vales.socialSupport.service.ParentService;
import com.vales.socialSupport.service.SignOfKinsmanService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

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
public class SignOfKinsmanListController extends Controller<SignOfKinsman> {

    @Setter
    @ManagedProperty("#{signOfKinsmanService}")
    private SignOfKinsmanService signOfKinsmanService;
    private List<SignOfKinsman> signOfKinsmans;

    private void initializationTypeEntity(){
        SignOfKinsman signOfKinsman = new SignOfKinsman();
        setTypeEntity(signOfKinsman);
    }
    @Override
    protected void initialization() {
        setService(signOfKinsmanService);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new SignOfKinsman());
    }

}
