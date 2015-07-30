package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
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
public class PersonalInfoListController extends Controller<PersonalInfo> {

    @Setter
    @ManagedProperty("#{personalInfoService}")

    private PersonalInfoService personalInfoService;

    private void initializationTypeEntity(){
        PersonalInfo personalInfo = new PersonalInfo();
        setTypeEntity(personalInfo);
    }
    @Override
    protected void initialization() {
        setService(personalInfoService);
        initializationTypeEntity();
        loadEntitys();
        initializationListSignature();

    }
    public void add() {
        super.add(new PersonalInfo());
    }



    public PersonalInfo getPersonalInfoById(Integer id){
        for (PersonalInfo personalInfo : getEntitys()) {
            if (personalInfo.getId() == id)
                return personalInfo;
        }
        return null;
    }
    public Set<PersonalInfo> getComingPeople(){
        Set<PersonalInfo> listComingPeople = new HashSet<PersonalInfo>();
        for (PersonalInfo personalInfo : getEntitys()) {
            if (!personalInfo.isNotComing())

                listComingPeople.add(personalInfo);
        }
//        System.out.println(listComingPeople);
        return listComingPeople;
    }
    public Set<PersonalInfo> getMemberOfSociety(){
        Set<PersonalInfo> listMemberOfSociety = new HashSet<PersonalInfo>();
        for (PersonalInfo personalInfo : getEntitys()) {
            if (personalInfo.getMemberOfSociety() != null)
                listMemberOfSociety.add(personalInfo.getMemberOfSociety());
        }
        return listMemberOfSociety;
    }

}
