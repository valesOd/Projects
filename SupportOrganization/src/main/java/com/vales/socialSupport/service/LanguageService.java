package com.vales.socialSupport.service;


import com.vales.socialSupport.repository.LanguageRepository;
import com.vales.socialSupport.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	public List<Language> findAll() {
        return languageRepository.findAll();
	}

	public void save(Language language) {
		languageRepository.save(language);
	}

	public void remove(Language language) {
		languageRepository.delete(language);
	}

}
