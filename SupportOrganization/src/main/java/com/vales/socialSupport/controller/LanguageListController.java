package com.vales.socialSupport.controller;

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
public class LanguageListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{languageService}")
	private LanguageService languageService;
    private List<Language> languages;
    private Language language = new Language();
    private Map<String,String> listSignature;

    public LanguageListController() {
        listSignature = new HashMap();
        for (Method m : Language.class.getMethods()) {
            if (m.getName().startsWith("set")) {
                Object parameterTypes[] = m.getParameterTypes();
                listSignature.put(m.getName(),parameterTypes[0].toString());
            }
        }
    }

    @PostConstruct
    public void loadLanguages() {
        languages = languageService.findAll();
    }

    public void add(){
        Language language= new Language();
        languages.add(language);

    }

    public void save(Language language) {
        languageService.save(language);
    }

    public void remove(Language language) {
        languageService.remove(language);
        loadLanguages();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "language removed!", null));
    }

    public void onCellEdit(CellEditEvent event) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Language changeableLanguage;
        String[] cellSpecifications = event.getColumn().getColumnKey().split(":");
        if(newValue != null && !newValue.equals(oldValue)) {
            changeableLanguage = languages.get(Integer.parseInt(cellSpecifications[2]));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            changeValue(changeableLanguage,"set" + cellSpecifications[3],newValue);
            save(changeableLanguage);
        }
    }
    private void changeValue(Language changeableLanguage, String colomName, Object newValue) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] typeVariable = listSignature.get(colomName).toString().split("\\.");
        if (typeVariable.length>1) {
            String type = typeVariable[typeVariable.length - 1];
            switch (type) {
                case "String":
                    changeableLanguage.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLanguage, newValue);
                    break;
                case "Integer":
                    changeableLanguage.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLanguage, Integer.parseInt(newValue.toString()));
                    break;
                case "boolean":
                    changeableLanguage.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLanguage, newValue);
                    break;
                case "Date":
                    changeableLanguage.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLanguage, newValue);
                    break;
                case "Character":
                    changeableLanguage.getClass().getMethod(colomName, Class.forName(listSignature.get(colomName).toString().substring(6))).invoke(changeableLanguage, newValue);
                    break;
            }
        }
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
    public void setLanguageService(LanguageService languageService){
        this.languageService = languageService;
    }

    public List<Language> getLanguages() {
        return languages;
    }


}
