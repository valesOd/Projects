package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="appointment_payment")
@Getter
@Setter
public class AppointmentPayment implements ParentEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_appointment_payment")
    private Integer id;
    private String name ="";

    public AppointmentPayment() {
    }

    public AppointmentPayment(int id, String name) {
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return id + " " +name ;
    }

}
