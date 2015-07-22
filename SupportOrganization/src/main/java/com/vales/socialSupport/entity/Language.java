package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language")
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name="id_language")
    private Set<RelationLanguagePerson> relationLanguagePersons;

    public Language() {
    }
    public Language(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
