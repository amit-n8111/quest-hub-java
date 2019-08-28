package com.citi.quest.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.DashboardDTO;
import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.DashboardService;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	UserInfoRepository userInfoRepository;	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public DashboardDTO getPosterDashboardData(String user) {
		
		
		return null;
		
	}

	@Override
	public DashboardDTO getSeekerDashboardData(String user) {

		UserInfo userInfo = userInfoRepository.findBySoeId(user);
		List<SkillDetailsDTO> skills = userInfo.getSkillDetails();
		List<Topic> topics = userInfo.getTopicsSubscribed();
		
		List<String> searchWords = skills.stream().map(skill -> skill.getSkill().getName()).collect(Collectors.toList());
		searchWords.addAll(topics.stream().map(topic -> topic.getTopicName()).collect(Collectors.toList()));
		
		TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
				  .onField("skills", 2F)
				  .onField("taskDescription", 1F)
				  .onField("taskName", 3F)
				  .build();
		mongoTemplate.indexOps(Task.class).ensureIndex(textIndex);
				
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matchingAny(searchWords.toArray(new String[searchWords.size()]));

		Query query = TextQuery.queryText(criteria)
				  .sortByScore()
				  .with(new PageRequest(0, 2));

		List<Task> taskRecomended = mongoTemplate.find(query, Task.class);
		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setMyRecomandedTasks(taskRecomended);
		return dashboardDTO;
	}

}
