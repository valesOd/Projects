package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.Login;
import com.vales.socialSupport.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public List<Login> findAll() {
        return loginRepository.findAll();
	}

	public void save(Login login) {
		loginRepository.save(login);
	}

	public void remove(Login login) {
		loginRepository.delete(login);
	}


}
