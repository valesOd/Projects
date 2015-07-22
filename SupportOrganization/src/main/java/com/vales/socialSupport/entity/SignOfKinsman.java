package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sign_of_kinsman")
public class SignOfKinsman  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sign_of_kinsman")
    private Integer id;
    @Column(name="name_sign_of_kinsman")
    private String name;

    @OneToMany
    @JoinColumn(name="id_sign_of_kinsman")
    private Set<PersonalInfo> personalInfos;

    public SignOfKinsman() {
    }

    public SignOfKinsman(Integer id,String name) {
        this.id=id;
        this.name = name;
    }

    public Integer getId() {
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

    @Override
    public String toString() {
        return id + " " +name ;
    }
}
