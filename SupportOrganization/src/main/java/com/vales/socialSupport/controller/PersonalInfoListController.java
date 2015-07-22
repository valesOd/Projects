package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.service.PersonalInfoService;
import com.vales.socialSupport.entity.SignOfKinsman;
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


@ManagedBean
@ViewScoped
public class PersonalInfoListController implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{personalInfoService}")
    private PersonalInfoService personalInfoService;
    private Map<String,String> listSignature;
    private List<PersonalInfo> filteredPersonalInfos;
    private PersonalInfo personalInfo;
    private List<PersonalInfo> personalInfos;

    public PersonalInfoListController() {
        listSignature = new HashMap();
        for (Method m : PersonalInfo.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadPersonalInfos() {
        personalInfos = personalInfoService.findAll();
    }

    public void add() {
        PersonalInfo personalInfo = new PersonalInfo();
        save(personalInfo);
        loadPersonalInfos();
    }

    public void save(PersonalInfo personalInfo) {
          personalInfoService.save(personalInfo);
    }

    public void remove(PersonalInfo personalInfo) {
        personalInfoService.remove(personalInfo);
        loadPersonalInfos();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "personalInfo removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        PersonalInfo changeablePersonalInfo;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        System.out.println("old " + oldValue + "  new " + newValue);
        if (newValue != null && !newValue.equals(oldValue) && cellSpecifications != null) {
            changeablePersonalInfo = personalInfos.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeablePersonalInfo,"set" + cellSpecifications[3],newValue);
            save(changeablePersonalInfo);
        }
    }
    private void changeValue(PersonalInfo changeablePersonalInfo, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeablePersonalInfo.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePersonalInfo, newValue);
                    break;
                case "Integer":
                    changeablePersonalInfo.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePersonalInfo, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeablePersonalInfo.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePersonalInfo, newValue);
                    break;
                case "Date":
                    changeablePersonalInfo.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePersonalInfo, newValue);
                    break;
                case "Character":
                    changeablePersonalInfo.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePersonalInfo, newValue);
                    break;
                case "SignOfKinsman":
                    String[] valueSignOfKinsman = newValue.toString().split(" ");
                    changeablePersonalInfo.setIdSignOfKinsman(new SignOfKinsman(Integer.parseInt(valueSignOfKinsman[0]), valueSignOfKinsman[1]));
                    break;
                case "PersonalInfo":
                    for(PersonalInfo personalInfo : personalInfos){
                        if(personalInfo.getId().toString().contains(newValue.toString())){
                            changeablePersonalInfo.setIdMemberOfSociety(personalInfo);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public void setFilteredPersonalInfos(List<PersonalInfo> filteredPersonalInfos) {
        this.filteredPersonalInfos = filteredPersonalInfos;
    }
    public void setPersonalInfoService(PersonalInfoService personalInfoService){
        this.personalInfoService = personalInfoService;
    }
    public void setPersonalInfos(List<PersonalInfo> personalInfos) {
        this.personalInfos = personalInfos;
    }

    public PersonalInfo getPersonalIngoById(Integer id){
        for (PersonalInfo personalInfo : personalInfos) {
            if (personalInfo.getId() == id)
                return personalInfo;
        }
        return null;
    }
    public Set<PersonalInfo> getComingPeople(){
        Set<PersonalInfo> listComingPeople = new HashSet<PersonalInfo>();
        for (PersonalInfo personalInfo : personalInfos) {
            if (!personalInfo.isNotComing())

                listComingPeople.add(personalInfo);
        }
        System.out.println(listComingPeople);
        return listComingPeople;
    }
    public Set<PersonalInfo> getMemberOfSociety(){
        Set<PersonalInfo> listMemberOfSociety = new HashSet<PersonalInfo>();
        for (PersonalInfo personalInfo : personalInfos) {
            if (personalInfo.getIdMemberOfSociety() != null)
                listMemberOfSociety.add(personalInfo.getIdMemberOfSociety());
        }
        return listMemberOfSociety;
    }
    public List<PersonalInfo> getFilteredPersonalInfos() {
        return filteredPersonalInfos;
    }
    public List<PersonalInfo> getPersonalInfos() {
        return personalInfos;
    }
}
