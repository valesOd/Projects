package com.vales.socialSupport.controller;


import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.entity.Payment;
import com.vales.socialSupport.service.PaymentService;
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
public class PaymentListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{paymentService}")
	private PaymentService paymentService;
    @ManagedProperty("#{personalInfoListController}")
    PersonalInfoListController personalInfoListController;
    private List<Payment> payments;
    private Payment payment = new Payment();
    private Map<String,String> listSignature;

    public PaymentListController() {
        listSignature = new HashMap();
        for (Method m : Payment.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
	public void loadPayments() {
        payments = paymentService.findAll();
	}

    public void add(){

        Calendar date,dateFrom,dateBy;
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        date = Calendar.getInstance(TimeZone.getDefault());
        dateFrom = Calendar.getInstance(TimeZone.getDefault());
        dateBy = Calendar.getInstance(TimeZone.getDefault());
        dateFrom.set(localCalendar.get(Calendar.YEAR),localCalendar.get(Calendar.MONTH)+1,1);
        dateBy.set(localCalendar.get(Calendar.YEAR),localCalendar.get(Calendar.MONTH)+1,dateFrom.getActualMaximum(Calendar.DATE));
        Payment payment = new Payment(date,dateFrom,dateBy);

        save(payment);
        loadPayments();
    }

    public void save(Payment payment) {
        paymentService.save(payment);
    }

    public void remove(Payment payment) {
        paymentService.remove(payment);
        payments = paymentService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "payment removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Payment changeablePayment;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if(newValue != null && !newValue.equals(oldValue)) {
            changeablePayment = payments.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeablePayment,"set" + cellSpecifications[3],newValue);
            save(changeablePayment);
        }
    }

    private void changeValue(Payment changeablePayment, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeablePayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePayment, newValue);
                    break;
                case "Integer":
                    changeablePayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePayment, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeablePayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePayment, newValue);
                    break;
                case "Date":
                    changeablePayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePayment, newValue);
                    break;
                case "Character":
                    changeablePayment.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeablePayment, newValue);
                    break;
                case "PersonalInfo":
                    changeablePayment.setPersonalInfo(personalInfoListController.getPersonalIngoById(Integer.parseInt(newValue.toString())));
                    break;
                case "AppointmentPayment":
                    String[] valueAppointmentPayment = newValue.toString().split(" ");
                    changeablePayment.setAppointmentPayment(new AppointmentPayment(Integer.parseInt(valueAppointmentPayment[0]), valueAppointmentPayment[1]));
                    break;

            }
        }
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void setPersonalInfoListController(PersonalInfoListController personalInfoListController) {
        this.personalInfoListController = personalInfoListController;
    }

    public List<Payment> getPayments() {
        return payments;
    }







}
