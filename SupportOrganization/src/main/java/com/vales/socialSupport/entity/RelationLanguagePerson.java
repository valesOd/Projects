package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="relation_language_person")
public class RelationLanguagePerson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_people_language")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_language")
    private Language language;

    @ManyToOne
    @JoinColumn(name="personal_info")
    private PersonalInfo personalInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    @Override
    public String toString() {
        return "RelationLanguagePerson{" +
                "id=" + id +
                ", language=" + language +
                ", personalInfo=" + personalInfo +
                '}';
    }

}
