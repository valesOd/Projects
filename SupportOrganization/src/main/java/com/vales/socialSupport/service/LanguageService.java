package com.vales.socialSupport.service;


import com.vales.socialSupport.repository.LanguageRepository;
import com.vales.socialSupport.entity.Language;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService extends ParentService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	protected void initializationRepository() {
		setParentService(languageRepository);
	}
}
