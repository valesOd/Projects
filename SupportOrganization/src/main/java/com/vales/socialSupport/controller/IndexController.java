package com.vales.socialSupport.controller;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class IndexController {
    private int curentIndex=0;

    public int getCurentIndex() {
        System.out.println("get"+curentIndex);
        return curentIndex;
    }

    public void setCurentIndex(int curentIndex) {
        System.out.println("set"+curentIndex );
        this.curentIndex = curentIndex;
    }
}
