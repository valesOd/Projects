package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.entity.RelationLanguagePerson;
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
public class RelationLanguageListController implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{relationLanguageInfoService}")
    private RelationLanguagePersonService relationLanguagePersonService;
    @ManagedProperty("#{languageService}")
    private LanguageService languageService;
    private Map<String,String> listSignature;
    private List<RelationLanguagePerson> filteredRelationLanguagePersons;
    private RelationLanguagePerson relationLanguagePerson;
    private List<RelationLanguagePerson> relationLanguagePersons;

    public RelationLanguageListController() {
        listSignature = new HashMap();
        for (Method m : RelationLanguagePerson.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadRelationLanguagePersons() {
        relationLanguagePersons = relationLanguagePersonService.findAll();
    }

    public void add() {
        PersonalInfo personalInfo = new PersonalInfo();
        save(relationLanguagePerson);
        loadRelationLanguagePersons();
    }

    public void save(RelationLanguagePerson relationLanguagePerson) {
        relationLanguagePersonService.save(relationLanguagePerson);
    }

    public void remove(RelationLanguagePerson relationLanguagePerson) {
        relationLanguagePersonService.remove(relationLanguagePerson);
        loadRelationLanguagePersons();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "personalInfo removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        RelationLanguagePerson changeableRelationLanguagePerson;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        System.out.println("old " + oldValue + "  new " + newValue);
        if (newValue != null && !newValue.equals(oldValue) && cellSpecifications != null) {
            changeableRelationLanguagePerson = relationLanguagePersons.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeableRelationLanguagePerson,"set" + cellSpecifications[3],newValue);
            save(changeableRelationLanguagePerson);
        }
    }

    private void changeValue(RelationLanguagePerson changeableRelationLanguagePerson, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");

        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeableRelationLanguagePerson.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableRelationLanguagePerson, newValue);
                    break;
                case "Integer":
                    changeableRelationLanguagePerson.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableRelationLanguagePerson, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeableRelationLanguagePerson.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableRelationLanguagePerson, newValue);
                    break;
                case "Date":
                    changeableRelationLanguagePerson.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableRelationLanguagePerson, newValue);
                    break;
                case "Character":
                    changeableRelationLanguagePerson.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableRelationLanguagePerson, newValue);
                    break;
            }
        }
    }

    public void setRelationLanguagePersonService(RelationLanguagePersonService relationLanguagePersonService) {
        this.relationLanguagePersonService = relationLanguagePersonService;
    }
}
