package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personal_info")
@Getter
@Setter
public class PersonalInfo implements ParentEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal_info")
    private Integer id;
    private String fio = "";
    private Date birthdate = new Date();
    private Character sex = ' ';
    private String education = "";
    private String talents = "";
    private String info = "";
    @Column(name = "is_not_coming")
    private boolean notComing = true;
    @Column(name = "home_adress")
    private String homeAdress = "";
    @Column(name = "job_place")
    private String jobPlace = "";
    private String district = "";
    @Column(name = "mob_phone")
    private String mobPhone = "";
    private String email = "";
    private String hobby = "";
    private String profession = "";
    @Column(name = "help_society")
    private String helpSociety = "";
    @ManyToOne
    @JoinColumn(name = "id_member_of_society")
    private PersonalInfo memberOfSociety;
    @ManyToOne
    @JoinColumn(name="id_sign_of_kinsman")
    private SignOfKinsman signOfKinsman ;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info")
    private List<RelationLanguagePerson> relationLanguagePersons =new ArrayList<RelationLanguagePerson>();


    public PersonalInfo() {
    }


}
