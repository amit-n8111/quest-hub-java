package com.citi.quest.api.event.handlers;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.repositories.ApplicationRepository;

@RepositoryEventHandler(Application.class)
@Component
public class ApplicationEventHandler {
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@HandleBeforeSave
	public void handleApplicationSaveWithoutId(Application application) {
		Long maxId = 0L;
		if (application.getId() <= 0 || application.getId() == null) {
			List<Application> applications = applicationRepository.findAll();
			if(CollectionUtils.isNotEmpty(applications)) {
				Application max = applications.stream().max(Comparator.comparing(Application::getId)).get();
				maxId=max.getId();
			}
			application.setId(maxId + 1);
		}
		applicationRepository.save(application);
	}
	
	@HandleBeforeCreate
	public void handleApplicationCreateWithoutId(Application application) {
		Long maxId = 0L;
		if (application.getId() <= 0 || application.getId() == null) {
			List<Application> applications = applicationRepository.findAll();
			if(CollectionUtils.isNotEmpty(applications)) {
				Application max = applications.stream().max(Comparator.comparing(Application::getId)).get();
				maxId=max.getId();
			}
			application.setId(maxId + 1);
		}
		applicationRepository.save(application);
	}

}
