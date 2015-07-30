package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.entity.RelationLanguagePerson;
import com.vales.socialSupport.entity.SignOfKinsman;
import com.vales.socialSupport.service.LanguageService;
import com.vales.socialSupport.service.RelationLanguagePersonService;
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
import java.util.*;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class RelationLanguageListController extends Controller<RelationLanguagePerson> {

    @Setter
    @ManagedProperty("#{relationLanguageInfoService}")
    private RelationLanguagePersonService relationLanguagePersonService;
    @Setter
    @ManagedProperty("#{languageService}")
    private LanguageService languageService;

    private void initializationTypeEntity(){
        RelationLanguagePerson relationLanguagePerson = new RelationLanguagePerson();
        setTypeEntity(relationLanguagePerson);
    }
    @Override
    protected void initialization() {
        setService(relationLanguagePersonService);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new RelationLanguagePerson());
    }

}

