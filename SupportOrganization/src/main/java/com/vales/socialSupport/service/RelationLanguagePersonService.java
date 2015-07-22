package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.RelationLanguagePerson;
import com.vales.socialSupport.repository.RelationLanguagePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationLanguagePersonService {

	@Autowired
	private RelationLanguagePersonRepository relationLanguagePersonRepository;

	public List<RelationLanguagePerson> findAll() {
        return relationLanguagePersonRepository.findAll();
	}

	public void save(RelationLanguagePerson relationLanguagePerson) {
		relationLanguagePersonRepository.save(relationLanguagePerson);
	}

	public void remove(RelationLanguagePerson relationLanguagePerson) {
		relationLanguagePersonRepository.delete(relationLanguagePerson);
	}

}
