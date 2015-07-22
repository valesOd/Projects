package com.vales.socialSupport.controller;


import com.vales.socialSupport.entity.SignOfKinsman;
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
public class SignOfKinsmanListController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{signOfKinsmanService}")
    private SignOfKinsmanService signOfKinsmanService;
    private List<SignOfKinsman> signOfKinsmans;
    private SignOfKinsman signOfKinsman;
    private Map listSignature;

    public SignOfKinsmanListController() {
        listSignature = new HashMap();
        for (Method m : SignOfKinsman.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(), parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadSignOfKinsmans() {
        signOfKinsmans = signOfKinsmanService.findAll();
    }

    public void add() {
        SignOfKinsman signOfKinsman = new SignOfKinsman();
        signOfKinsmans.add(signOfKinsman);
        // save(signOfKinsman);
    }

    public void save(SignOfKinsman signOfKinsman) {
        signOfKinsmanService.save(signOfKinsman);
        loadSignOfKinsmans();

    }

    public void remove(SignOfKinsman signOfKinsman) {
        signOfKinsmanService.remove(signOfKinsman);
        signOfKinsmans = signOfKinsmanService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "signOfKinsman removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Object oldValue = event.getComponent();
        Object newValue = event.getNewValue();
        SignOfKinsman changeableSignOfKinsman;

        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if (newValue != null && !newValue.equals(oldValue) && cellSpecifications != null) {
            changeableSignOfKinsman = signOfKinsmans.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            changeValue(changeableSignOfKinsman, "set" + cellSpecifications[3], newValue);

            save(changeableSignOfKinsman);
        }
    }

    private void changeValue(SignOfKinsman changeableSignOfKinsman, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        switch (listSignature.get(colomName).toString().substring(6)) {
            case "java.lang.String":
//                System.out.println("String");
                changeableSignOfKinsman.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableSignOfKinsman, newValue);
                break;
            case "java.lang.Integer":
//                System.out.println("Integer");
                changeableSignOfKinsman.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableSignOfKinsman, newValue);
                break;
            case "boolean":
//                System.out.println("boolean");
                changeableSignOfKinsman.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableSignOfKinsman, newValue);
                break;
            case "java.util.Date":
//                System.out.println("Date");
                changeableSignOfKinsman.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableSignOfKinsman, newValue);
                break;
            case "java.util.Character":
//                System.out.println("Charter");
                changeableSignOfKinsman.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableSignOfKinsman, newValue);
                break;
        }
    }


    public void setSignOfKinsman(SignOfKinsman signOfKinsman) {
        this.signOfKinsman = signOfKinsman;
    }
    public void setSignOfKinsmans(List<SignOfKinsman> signOfKinsmans) {
        this.signOfKinsmans = signOfKinsmans;
    }
    public void setSignOfKinsmanService(SignOfKinsmanService signOfKinsmanService){
        this.signOfKinsmanService = signOfKinsmanService;
    }

    public List<SignOfKinsman> getSignOfKinsmans() {
        return signOfKinsmans;
    }
    public SignOfKinsman getSignOfKinsman() {
        return signOfKinsman;
    }

}
