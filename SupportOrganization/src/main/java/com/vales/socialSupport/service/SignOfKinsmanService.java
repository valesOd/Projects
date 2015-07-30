package com.vales.socialSupport.service;

import com.vales.socialSupport.entity.SignOfKinsman;
import com.vales.socialSupport.repository.SignOfKinsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignOfKinsmanService extends ParentService{

	@Autowired
	private SignOfKinsmanRepository signOfKinsmanRepository;

	@Override
	protected void initializationRepository() {
		setParentService(signOfKinsmanRepository);
	}
}
