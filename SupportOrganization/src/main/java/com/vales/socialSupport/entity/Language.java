package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class Language implements ParentEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language")
    private Integer id;
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
    //TODO if add method "toString", throw next exception " failed to lazily initialize a collection of role: com.vales.socialSupport.entity.Language.relationLanguagePersons, could not initialize proxy - no Session"
//    @Override
//    public String toString() {
//        return "Language{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", relationLanguagePersons=" + relationLanguagePersons +
//                '}';
//    }
}
