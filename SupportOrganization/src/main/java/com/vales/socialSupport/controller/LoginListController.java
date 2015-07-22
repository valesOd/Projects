package com.vales.socialSupport.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.vales.socialSupport.entity.Login;
import com.vales.socialSupport.service.LoginService;
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
public class LoginListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginService}")
	private LoginService loginService;
    @ManagedProperty("#{personalInfoListController}")
    PersonalInfoListController personalInfoListController;
    private List<Login> logins;
    private Login login = new Login();
    private Map<String,String> listSignature;

    public LoginListController() {
        listSignature = new HashMap();
        for (Method m : Login.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadLogins() {
        logins = loginService.findAll();
    }

    public void add(){
        Login login= new Login();
        logins.add(login);
    }

    public void save(Login login) {
        loginService.save(login);
    }

    public void remove(Login login) {
        loginService.remove(login);
        loadLogins();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "login removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Login changeableLogin;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if(newValue != null && !newValue.equals(oldValue)) {
            changeableLogin = logins.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if(cellSpecifications[3].equals("Password"))
                newValue = Hashing.sha1().hashString(newValue.toString(), Charsets.UTF_8 ).toString();
            changeValue(changeableLogin,"set" + cellSpecifications[3],newValue);
            save(changeableLogin);
        }
    }
    private void changeValue(Login changeableLogin, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeableLogin.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLogin, newValue);
                    break;
                case "Integer":
                    changeableLogin.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLogin, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeableLogin.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLogin, newValue);
                    break;
                case "Date":
                    changeableLogin.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLogin, newValue);
                    break;
                case "Character":
                    changeableLogin.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLogin, newValue);
                    break;
                case "PersonalInfo":
                    changeableLogin.setPersonalInfo(personalInfoListController.getPersonalIngoById(Integer.parseInt(newValue.toString())));
                    break;
            }
        }
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }
    public void setPersonalInfoListController(PersonalInfoListController personalInfoListController) {
        this.personalInfoListController = personalInfoListController;
    }
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public List<Login> getLogins() {
        return logins;
    }
    public Login getLogin(String name){
        for(Login login:logins){
            if(login.getLogin().equals(name))
                return login;
        }
        return null;
    }




}
