package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.RelationLanguagePerson;
import com.vales.socialSupport.repository.RelationLanguagePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationLanguagePersonService extends ParentService {

	@Autowired
	private RelationLanguagePersonRepository relationLanguagePersonRepository;

	@Override
	protected void initializationRepository() {
		setParentService(relationLanguagePersonRepository);
	}
}
