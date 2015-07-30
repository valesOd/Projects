package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.ParentEntity;
import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.entity.SignOfKinsman;
import com.vales.socialSupport.service.ParentService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vales on 26.07.2015.
 */
@Getter
@Setter
public abstract class Controller<T extends ParentEntity> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ParentService service;
    private Controller auxiliaryController;
    private ParentEntity typeEntity;
    private Map<String, String> listSignature;
    private List<T> filteredEntity;
    private List<T> entitys;
    private T entity;

    @PostConstruct
    protected abstract void initialization();


    protected void initializationListSignature() {

        listSignature = new HashMap();
        for (Method m : typeEntity.getClass().getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(), parameterTypes[0].toString());
            }
        }
    }

    protected void setAuxiliaryController(Controller auxiliaryController) {
        this.auxiliaryController = auxiliaryController;
    }

    public void setTypeEntity(ParentEntity entity) {
        typeEntity = entity;
    }

    protected void loadEntitys() {
        entitys = service.findAll();
    }

    public void add(T entity) {
        entitys.add(entity);
    }

    public void save(T t) {
        service.save(t);
    }

    public void remove(T t) {
        service.remove(t);
        entitys.remove(t);
//        loadEntitys();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, t.getClass().getName().toString() + " removed!", null));
    }

    public void remove() {
        //TODO dublicate body remove method
        service.remove(entity);
        entitys.remove(entity);
        entity = null;
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, entity.getClass().getName().toString() + " removed!", null));

    }

    public T getEntityById(Integer id) {
        for (T entity : getEntitys()) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }

    public void onCellEdit(CellEditEvent event) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        T changeableEntity;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if (newValue != null && !newValue.equals(oldValue) && cellSpecifications != null) {
            changeableEntity = entitys.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeableEntity, "set" + cellSpecifications[3], newValue);
            save(changeableEntity);
        }
    }

    private void changeValue(T changeableEntity, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length > 1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, newValue);
                    break;
                case "Integer":
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, newValue);
                    break;
                case "Date":
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, newValue);
                    break;
                case "Character":
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, newValue);
                    break;
                case "AppointmentPayment":
                    String[] valueAppointmentPayment = newValue.toString().split(" ");
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, new AppointmentPayment(Integer.parseInt(valueAppointmentPayment[0]), valueAppointmentPayment[1]));
                    break;
                case "SignOfKinsman":
                    String[] valueSignOfKinsman = newValue.toString().split(" ");
                    changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, new SignOfKinsman(Integer.parseInt(valueSignOfKinsman[0]), valueSignOfKinsman[1]));
                    break;
                case "PersonalInfo":
                    if (colomName.contains("setPersonalInfo")) {
                        T entity = (T) auxiliaryController.getEntityById(Integer.parseInt(newValue.toString()));
                        System.out.println(entity);
                        changeableEntity.getClass().getMethod("setPersonalInfo", Class.forName(listSignature.get(colomName).toString().substring(6)))
                                .invoke(changeableEntity, entity);
                        break;
                    } else if (colomName.contains("setMemberOfSociety")) {
                        for (T personalInfo : entitys) {
                            if (personalInfo.getId().toString().contains(newValue.toString())) {
                                changeableEntity.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableEntity, personalInfo);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    }

}

