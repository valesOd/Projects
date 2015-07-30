package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="sign_of_kinsman")
@Setter
@Getter
public class SignOfKinsman  implements ParentEntity,Serializable{
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

       @Override
    public String toString() {
        return id + " " +name ;
    }
}
