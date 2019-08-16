/**
 * 
 */
package com.citi.quest.api.event.handlers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.repositories.SkillsRepository;
import com.citi.quest.api.repositories.TopicRepository;

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
	
	@Autowired
	private TopicRepository topicRepository;
	
	@HandleAfterSave
	public void handleTopicSave(Topic topic) {
		List<Skill> existingSkills = skillRepository.findAll();
		Skill maxSkill = existingSkills.stream().max(Comparator.comparing(Skill::getId)).get();
		Long maxId = maxSkill.getId();
		for(Skill skill : topic.getSkills()) {
			if(skill.getId()<=0 || skill.getId() == null) {
				maxId = maxId +1;
				skill.setId(maxId+1);
				skillRepository.save(skill);
			}
		}
	}
	
	@HandleAfterCreate
	public void handleTopicCreate(Topic topic) {
		List<Skill> existingSkills = skillRepository.findAll();
		Skill maxSkill = existingSkills.stream().max(Comparator.comparing(Skill::getId)).get();
		Long maxId = maxSkill.getId();
		for(Skill skill : topic.getSkills()) {
			if(skill.getId()<=0 || skill.getId() == null) {
				maxId = maxId +1;
				skill.setId(maxId+1);
				skillRepository.save(skill);
			}
		}
	}
	
	@HandleBeforeSave
	public void handleTopicSaveWithoutId(Topic topic) {
		if (topic.getId() <= 0 || topic.getId() == null) {
			List<Topic> topics = topicRepository.findAll();
			Topic max = topics.stream().max(Comparator.comparing(Topic::getId)).get();
			topic.setId(max.getId() + 1);
		}
		topicRepository.save(topic);
	}
	
	@HandleBeforeCreate
	public void handleTopicCreateWithoutId(Topic topic) {
		if(topic.getId()<=0 || topic.getId()==null) {
			List<Topic> topics = topicRepository.findAll();
			Topic max = topics.stream().max(Comparator.comparing(Topic::getId)).get();
			topic.setId(max.getId()+1);
		}
		topicRepository.save(topic);
	}
	
}
