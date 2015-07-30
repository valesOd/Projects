package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="relation_language_person")
@Getter
@Setter
public class RelationLanguagePerson implements ParentEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_people_language")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_language")
    private Language language;

    @ManyToOne
    @JoinColumn(name="personal_info")
    private PersonalInfo personalInfo;

    @Override
    public String toString() {
        return "RelationLanguagePerson{" +
                "id=" + id +
                ", language=" + language +
                ", personalInfo=" + personalInfo +
                '}';
    }

}
