package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "login")
@Getter
@Setter
public class Login implements ParentEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_login")
    private Integer id;
    private String login="";
    private String password="";
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_personal_info")
    private PersonalInfo personalInfo;

    public Login() {
    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

}