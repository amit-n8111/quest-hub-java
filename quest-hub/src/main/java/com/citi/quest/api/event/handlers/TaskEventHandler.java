package com.citi.quest.api.event.handlers;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Question;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.repositories.QuestionsRepository;
import com.citi.quest.api.repositories.SkillsRepository;
import com.citi.quest.api.repositories.TaskRepository;

@RepositoryEventHandler(Task.class)
@Component
public class TaskEventHandler {
	
	@Autowired
	private SkillsRepository skillRepository;
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@HandleAfterSave
	public void handleSkillsSave(Task task) {
		Long maxId = 0L;
		List<Skill> existingSkills = skillRepository.findAll();
		if (CollectionUtils.isNotEmpty(existingSkills)) {
			Skill maxSkill = existingSkills.stream().max(Comparator.comparing(Skill::getId)).get();
			maxId = maxSkill.getId();
		}
		for (Skill skill : task.getSkills()) {
			if (skill.getId() == null || skill.getId() <= 0) {
				maxId = maxId + 1;
				skill.setId(maxId);
				skillRepository.save(skill);
			}
		}
	}
	
	@HandleAfterCreate
	public void handleSkillsCreate(Task task) {
		Long maxId = 0L;
		List<Skill> existingSkills = skillRepository.findAll();
		if (CollectionUtils.isNotEmpty(existingSkills)) {
			Skill maxSkill = existingSkills.stream().max(Comparator.comparing(Skill::getId)).get();
			maxId = maxSkill.getId();
		}
		if(task.getSkills()!=null) {
			for (Skill skill : task.getSkills()) {
				if (skill.getId() == null || skill.getId() <= 0) {
					maxId = maxId + 1;
					skill.setId(maxId);
					skillRepository.save(skill);
				}
			}
		}
	}
	
	@HandleAfterSave
	public void handleScreeningQuestionsSave(Task task) {
		Long maxId = 0L;
		List<Question> existingQues = questionsRepository.findAll();
		if(CollectionUtils.isNotEmpty(existingQues)) {
			Question maxSkill = existingQues.stream().max(Comparator.comparing(Question::getId)).get();
			maxId = maxSkill.getId();
		}
		for(Question question : task.getScreeningQuestions()) {
			if(question.getId() == null || question.getId()<=0) {
				maxId = maxId +1;
				question.setId(maxId);
				questionsRepository.save(question);
			}
		}
	}
	
	@HandleAfterCreate
	public void handleScreeningQuestionsCreate(Task task) {
		Long maxId = 0L;
		List<Question> existingQues = questionsRepository.findAll();
		if(CollectionUtils.isNotEmpty(existingQues)) {
			Question maxSkill = existingQues.stream().max(Comparator.comparing(Question::getId)).get();
			maxId = maxSkill.getId();
		}
		for(Question question : task.getScreeningQuestions()) {
			if(question.getId() == null || question.getId()<=0) {
				maxId = maxId +1;
				question.setId(maxId);
				questionsRepository.save(question);
			}
		}
	}
	
	@HandleBeforeSave
	public void handleTaskSaveWithoutId(Task task) {
		Long maxId = 0L;
		if ( task.getTaskId() == null || task.getTaskId() <= 0) {
			List<Task> tasks = taskRepository.findAll();
			if (CollectionUtils.isNotEmpty(tasks)) {
				Task max = tasks.stream().max(Comparator.comparing(Task::getTaskId)).get();
				maxId = max.getTaskId();
			}
			task.setTaskId(maxId + 1);
		}
		taskRepository.save(task);
	}
	
	@HandleBeforeCreate
	public void handleTaskCreateWithoutId(Task task) {
		Long maxId = 0L;
		if (task.getTaskId() == null || task.getTaskId() <= 0) {
			List<Task> tasks = taskRepository.findAll();
			if (CollectionUtils.isNotEmpty(tasks)) {
				Task max = tasks.stream().max(Comparator.comparing(Task::getTaskId)).get();
				maxId = max.getTaskId();
			}
			task.setTaskId(maxId + 1);
		}
		taskRepository.save(task);
	}


}
