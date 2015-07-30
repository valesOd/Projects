package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.service.LanguageService;
import com.vales.socialSupport.entity.Language;
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
public class LanguageListController extends Controller<Language> {

    @Setter
	@ManagedProperty("#{languageService}")
	private LanguageService languageService;

    private void initializationTypeEntity(){
        Language language = new Language();
        setTypeEntity(language);
    }
    @Override
    protected void initialization() {
        setService(languageService);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new Language());
    }



}
