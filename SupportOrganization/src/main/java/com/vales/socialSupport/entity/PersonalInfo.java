package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personal_info")
public class PersonalInfo implements Serializable {
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
    private PersonalInfo idMemberOfSociety;
    @ManyToOne
    @JoinColumn(name="id_sign_of_kinsman")
    private SignOfKinsman signOfKinsman ;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info")
    private List<RelationLanguagePerson> relationLanguagePersons =new ArrayList<RelationLanguagePerson>();

    public List<RelationLanguagePerson> getRelationLanguagePersons() {
        System.out.println(relationLanguagePersons);
        return relationLanguagePersons;
    }

    public void setRelationLanguagePersons(List<RelationLanguagePerson> relationLanguagePersons) {
        System.out.println("set"+relationLanguagePersons);
        this.relationLanguagePersons = relationLanguagePersons;
    }

    public PersonalInfo() {
    }

    public PersonalInfo(Integer id, String fio, Date birthdate, Character sex, String education, String talents, String info, boolean notComing, String jobPlace, String homeAdress, String district, String mobPhone, String email, String hobby, String profession, String helpSociety, PersonalInfo idMemberOfSociety) {
        this.id = id;
        this.fio = fio;
        this.birthdate = birthdate;
        this.sex = sex;
        this.education = education;
        this.talents = talents;
        this.info = info;
        this.notComing = notComing;
        this.jobPlace = jobPlace;
        this.homeAdress = homeAdress;
        this.district = district;
        this.mobPhone = mobPhone;
        this.email = email;
        this.hobby = hobby;
        this.profession = profession;
        this.helpSociety = helpSociety;
        this.idMemberOfSociety = idMemberOfSociety;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTalents() {
        return talents;
    }

    public void setTalents(String talents) {
        this.talents = talents;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isNotComing() {
        return notComing;
    }

    public void setNotComing(boolean notComing) {
        this.notComing = notComing;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHelpSociety() {
        return helpSociety;
    }

    public void setHelpSociety(String helpSociety) {
        this.helpSociety = helpSociety;
    }

    public PersonalInfo getIdMemberOfSociety() {
        return idMemberOfSociety;
    }

    public void setIdMemberOfSociety(PersonalInfo idMemberOfSociety) {
        this.idMemberOfSociety = idMemberOfSociety;
    }

    public SignOfKinsman getIdSignOfKinsman() {
        return signOfKinsman;
    }

    public void setIdSignOfKinsman(SignOfKinsman signOfKinsman) {
        this.signOfKinsman = signOfKinsman;
    }


}
