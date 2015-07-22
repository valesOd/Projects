package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PersonalInfoService {

	@Autowired
	private PersonalInfoRepository personalInfoRepository;

	public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
	}

	public void save(PersonalInfo personalInfo) {
		personalInfoRepository.save(personalInfo);
	}

	public void remove(PersonalInfo personalInfo) {
		personalInfoRepository.delete(personalInfo);
	}

}
