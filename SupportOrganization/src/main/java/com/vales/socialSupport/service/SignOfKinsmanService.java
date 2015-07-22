package com.vales.socialSupport.service;

import com.vales.socialSupport.entity.SignOfKinsman;
import com.vales.socialSupport.repository.SignOfKinsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignOfKinsmanService {

	@Autowired
	private SignOfKinsmanRepository signOfKinsmanRepository;

	public List<SignOfKinsman> findAll() {
        return signOfKinsmanRepository.findAll();
	}

	public void save(SignOfKinsman signOfKinsman) {
		signOfKinsmanRepository.save(signOfKinsman);
	}

	public void remove(SignOfKinsman signOfKinsman) {
		signOfKinsmanRepository.delete(signOfKinsman);
	}

}
