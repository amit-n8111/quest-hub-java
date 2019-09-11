package com.citi.quest.api.event.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.repositories.SkillsRepository;
import com.citi.quest.api.util.SequenceGenerator;

@RepositoryEventHandler(Skill.class)
@Component
public class SkillEventHandler {
	
	/** Unique sequence key for Skill collection. */
	private static final String SKILL_SEQ_KEY = "SKILL_SEQUENCE";

	
	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@HandleBeforeSave
	public void handleApplicationSaveWithoutId(Skill skill) {
		if (skill.getId() == null || skill.getId() <= 0) {
			Long nextSequence = sequenceGenerator.getNextSequence(SKILL_SEQ_KEY);
			skill.setId(nextSequence);
		}
		skillsRepository.save(skill);
	}

	@HandleBeforeCreate
	public void handleApplicationCreateWithoutId(Skill skill) {

		if (null == skill.getId() || skill.getId() <= 0) {
			Long nextSequence = sequenceGenerator.getNextSequence(SKILL_SEQ_KEY);
			skill.setId(nextSequence);
		}

		skillsRepository.save(skill);
	}



}
