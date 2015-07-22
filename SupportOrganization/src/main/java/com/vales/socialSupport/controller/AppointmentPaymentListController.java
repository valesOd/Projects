package com.vales.socialSupport.controller;

import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.service.AppointmentPaymentService;
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
public class AppointmentPaymentListController implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{appointmentPaymentService}")
    private AppointmentPaymentService appointmentPaymentService;
    private Map<String,String> listSignature;
    private List<AppointmentPayment> filteredAppointmentPayments;
    private List<AppointmentPayment> appointmentPayments;
    private AppointmentPayment appointmentPayment = new AppointmentPayment();

    public AppointmentPaymentListController() {
        listSignature = new HashMap();
        for (Method m : AppointmentPayment.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadAppointmentPayments() {
        appointmentPayments = appointmentPaymentService.findAll();
    }

    public void add() {
        AppointmentPayment appointmentPayment = new AppointmentPayment();
        save(appointmentPayment);
        loadAppointmentPayments();
    }

    public void save(AppointmentPayment appointmentPayment) {
        appointmentPaymentService.save(appointmentPayment);
    }

    public void remove(AppointmentPayment appointmentPayment) {
        appointmentPaymentService.remove(appointmentPayment);
        loadAppointmentPayments();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "apointmentPayment removed!", null));
    }
    public void remove() {
        appointmentPaymentService.remove(appointmentPayment);
        appointmentPayments.remove(appointmentPayment);
        appointmentPayment = null;
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "apointmentPayment removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        AppointmentPayment changeableAppointmentPayment;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if (newValue != null && !newValue.equals(oldValue) && cellSpecifications != null) {
            changeableAppointmentPayment = appointmentPayments.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeableAppointmentPayment,"set" + cellSpecifications[3],newValue);
            save(changeableAppointmentPayment);
        }

    }
    private void changeValue(AppointmentPayment changeableAppointmentPayment, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeableAppointmentPayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableAppointmentPayment, newValue);
                    break;
                case "Integer":
                    changeableAppointmentPayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableAppointmentPayment, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeableAppointmentPayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableAppointmentPayment, newValue);
                    break;
                case "Date":
                    changeableAppointmentPayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableAppointmentPayment, newValue);
                    break;
                case "Character":
                    changeableAppointmentPayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableAppointmentPayment, newValue);
                    break;
            }
        }
    }

    public void setAppointmentPaymentService(AppointmentPaymentService appointmentPaymentService) {
        this.appointmentPaymentService = appointmentPaymentService;
    }
    public void setAppointmentPayments(List<AppointmentPayment> appointmentPayments) {
        this.appointmentPayments = appointmentPayments;
    }
    public void setAppointmentPayment(AppointmentPayment appointmentPayment) {
        this.appointmentPayment = appointmentPayment;
    }
    public void setFilteredAppointmentPayments(List<AppointmentPayment> filteredAppointmentPayments) {
        this.filteredAppointmentPayments = filteredAppointmentPayments;
    }

    public List<AppointmentPayment> getAppointmentPayments() {
        return appointmentPayments;
    }
    public AppointmentPayment getAppointmentPayment() {
        return appointmentPayment;
    }
    public List<AppointmentPayment> getFilteredAppointmentPayments() {
        return filteredAppointmentPayments;
    }









}
