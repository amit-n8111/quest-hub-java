package com.citi.quest.api.event.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.util.SequenceGenerator;

@RepositoryEventHandler(Application.class)
@Component
public class ApplicationEventHandler {
	/** Unique sequence key for Application collection. */
	private static final String APPLICATION_SEQ_KEY = "APPLICATION_SEQUENCE";

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@HandleBeforeSave
	public void handleApplicationSaveWithoutId(Application application) {
		if (application.getId() == null || application.getId() <= 0) {
			Long nextSequence = sequenceGenerator.getNextSequence(APPLICATION_SEQ_KEY);
			application.setId(nextSequence);
		}
		applicationRepository.save(application);
	}

	@HandleBeforeCreate
	public void handleApplicationCreateWithoutId(Application application) {

		if (null == application.getId() || application.getId() <= 0) {
			Long nextSequence = sequenceGenerator.getNextSequence(APPLICATION_SEQ_KEY);
			application.setId(nextSequence);
		}

		applicationRepository.save(application);
	}

}
