package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.Login;
import com.vales.socialSupport.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService extends ParentService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	protected void initializationRepository() {
		setParentService(loginRepository);
	}

}
