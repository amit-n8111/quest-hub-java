package com.citi.quest.api.event.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.citi.quest.api.domain.Question;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.repositories.QuestionsRepository;
import com.citi.quest.api.repositories.SkillsRepository;

@RepositoryEventHandler(Task.class)
@Component
public class TaskEventHandler {
	
	@Autowired
	private SkillsRepository skillRepository;
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	@HandleAfterSave
	public void handleSkillsSave(Task task) {
		List<Skill> skills = task.getSkills();
		skillRepository.save(skills);
	}
	
	@HandleAfterCreate
	public void handleSkillsCreate(Task task) {
		List<Skill> skills = task.getSkills();
		skillRepository.save(skills);
	}
	
	@HandleAfterSave
	public void handleScreeningQuestionsSave(Task task) {
		List<Question> questions = task.getScreeningQuestions();
		questionsRepository.save(questions);
	}
	
	@HandleAfterCreate
	public void handleScreeningQuestionsCreate(Task task) {
		List<Question> questions = task.getScreeningQuestions();
		questionsRepository.save(questions);
	}


}
