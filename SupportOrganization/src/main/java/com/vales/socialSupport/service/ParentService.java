package com.vales.socialSupport.service;

import com.vales.socialSupport.entity.ParentEntity;
import com.vales.socialSupport.repository.ParentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Vales on 26.07.2015.
 */

public abstract class ParentService<T extends ParentEntity> {

    private JpaRepository repository;

    @PostConstruct
    protected abstract void initializationRepository();

    public List<T> findAll(){
        return repository.findAll();
    }
    public void save(ParentEntity entity){
        repository.save(entity);
    }
    public void remove(ParentEntity entity){
        repository.delete(entity);
    }

    protected void setParentService(JpaRepository repository) {
        this.repository = repository;
    }
}
