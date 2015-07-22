package com.vales.socialSupport.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="appointment_payment")
public class AppointmentPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_appointment_payment")
    private int id;
    private String name ="";

    public AppointmentPayment() {
    }

    public AppointmentPayment(int id, String name) {
        this.id=id;
        this.name=name;
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

    @Override
    public String toString() {
        return id + " " +name ;
    }

}
