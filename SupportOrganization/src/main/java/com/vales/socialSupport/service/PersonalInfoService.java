package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.PersonalInfo;
import com.vales.socialSupport.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PersonalInfoService extends ParentService {

	@Autowired
	private PersonalInfoRepository personalInfoRepository;

	@Override
	protected void initializationRepository() {
		setParentService(personalInfoRepository);
	}
}
