/**
 * 
 */
package com.citi.quest.api.event.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.repositories.SkillsRepository;

/**
 * This class is responsible for handling events triggered as a result of
 * <Topic> create/save operations.
 * 
 * @author Ashutosh Srivastav
 *
 */
@RepositoryEventHandler
@Component
public class TopicEventHandler {
	
	@Autowired
	private SkillsRepository skillRepository;
	
	@HandleAfterSave
	public void handleTopicSave(Topic topic) {
		List<Skill> skills = topic.getSkills();
		skillRepository.save(skills);
	}
	
	@HandleAfterCreate
	public void handleTopicCreate(Topic topic) {
		List<Skill> skills = topic.getSkills();
		skillRepository.save(skills);
	}
	
}
