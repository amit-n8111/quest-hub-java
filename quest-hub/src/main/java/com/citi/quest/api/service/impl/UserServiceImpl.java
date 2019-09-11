package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Favorite;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;
import com.citi.quest.api.dtos.SearchUserResponseDTO;
import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.dtos.TagUserSkillDTO;
import com.citi.quest.api.dtos.WorkHistoryAndFeedbackDTO;
import com.citi.quest.api.enums.ExpertiseLevel;
import com.citi.quest.api.repositories.FavoriteRepository;
import com.citi.quest.api.repositories.SkillsRepository;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	FavoriteRepository favRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	SkillsRepository skillsRepository;
	

	@Override
	public Boolean markTaskAsFavorite(long taskId, String user) {
		Favorite favorite = new Favorite();
		favorite.setTaskIds(taskId);
		favorite.setUserId(user);
		favRepository.save(favorite);
		return true;
	}

	@Override
	public UserInfo getUserInfo(String userId) {

		return userInfoRepository.findBySoeId(userId);
	}

	@Override
	public List<SearchUserResponseDTO> searchUserInfo(SearchUserDTO userSearchDTO) {

		Query query = new Query();

		if (userSearchDTO.getTaskTopicId() != null) {
			query.addCriteria(Criteria.where("topicsSubscribed")
					.elemMatch(Criteria.where("id").is(userSearchDTO.getTaskTopicId())));
		}
		if (userSearchDTO.getSkillId() != null) {
			query.addCriteria(
					Criteria.where("skillDetails").elemMatch(Criteria.where("id").is(userSearchDTO.getSkillId())));
		}
		if (StringUtils.isNotBlank(userSearchDTO.getSearch())) {
			query.addCriteria(new Criteria().orOperator(

					Criteria.where("name").regex(userSearchDTO.getSearch()),

					Criteria.where("teamName").regex(userSearchDTO.getSearch())));
		}

		query.with(new PageRequest(userSearchDTO.getPageNumber() - 1, userSearchDTO.getPageSize()));

		if (StringUtils.isNotBlank(userSearchDTO.getSortOrder())) {

			if ("ASC".equalsIgnoreCase(userSearchDTO.getSortOrder())) {
				query.with(new Sort(Sort.Direction.ASC, userSearchDTO.getSortBy()));
			} else {
				query.with(new Sort(Sort.Direction.DESC, userSearchDTO.getSortBy()));
			}

		}
		List<UserInfo> users = mongoOperations.find(query, UserInfo.class);

		List<SearchUserResponseDTO> response = mapToSearchUserResponseDTO(users);
		return response;
	}

	private List<SearchUserResponseDTO> mapToSearchUserResponseDTO(List<UserInfo> users) {
		
		List<SearchUserResponseDTO> response = new ArrayList<SearchUserResponseDTO>();
		for (UserInfo user : users) {
			SearchUserResponseDTO searchUserResponseDTO = new SearchUserResponseDTO();
			searchUserResponseDTO.setSoeId(user.getSoeId());
			searchUserResponseDTO.setName(user.getName());
			searchUserResponseDTO.setLocation(user.getLocation());
			searchUserResponseDTO.setBuName(user.getBuName());
			searchUserResponseDTO.setRating(user.getRating());
			searchUserResponseDTO.setSkillDetails(user.getSkillDetails());
			searchUserResponseDTO.setManagerSoeId(user.getManagerSoeId());
			searchUserResponseDTO.setEmail(user.getEmail());
			searchUserResponseDTO.setTeamName(user.getTeamName());
			searchUserResponseDTO.setUserDescription(user.getUserDescription());
			Integer taskCompletedId = 5;
			List<Task> tasks = taskRepository.findByTaskAssignedToAndTaskStatusId(user.getSoeId(), taskCompletedId);
			WorkHistoryAndFeedbackDTO workHistoryAndFeedbackDTO = new WorkHistoryAndFeedbackDTO();
			for (Task task : tasks) {
				workHistoryAndFeedbackDTO.setTaskName(task.getTaskName());
				workHistoryAndFeedbackDTO.setTaskCreateBy(task.getTaskCreatedBy());
				workHistoryAndFeedbackDTO.setTaskCreateDate(task.getTaskCreateDate());
				workHistoryAndFeedbackDTO.setTaskFeedBack(task.getTaskFeedBack());
				workHistoryAndFeedbackDTO.setRating(task.getRating());
			}

			searchUserResponseDTO.setNumberOfTasksCompleted(tasks.size());
			response.add(searchUserResponseDTO);
		}
		
		return response;
		
	}

	@Override
	public List<SearchUserResponseDTO> getRecomendedUsers(String user,Long taskId , Integer pageNum, Integer pageSize) {
		
		Task task = taskRepository.findByTaskId(taskId);
		List<String> taskSkills = task.getSkills().stream()
				.map(skill -> skill.getName())
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		String searchString = listToString(taskSkills);
		System.out.println("searchString " + searchString);
		
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchString);
		Query query = TextQuery.queryText(criteria).sortByScore()
				.with(new PageRequest(pageNum - 1, pageSize));

		List<UserInfo> recomendedUsers = mongoTemplate.find(query, UserInfo.class);

		System.out.println("\n index after sort");
		recomendedUsers.stream().forEach(u -> System.out.print(u.getSoeId() + ","));
		System.out.println("\n scores after sort");
		recomendedUsers.stream().forEach(u -> System.out.print(u.getScore() + ","));

		for (UserInfo u : recomendedUsers) {
			updateScorefromExperience(u, taskSkills);
		}
		
		recomendedUsers.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));

		System.out.println("\n index after sort");
		recomendedUsers.stream().forEach(u -> System.out.print(u.getSoeId() + ","));
		System.out.println("\n scores after sort");
		recomendedUsers.stream().forEach(u -> System.out.print(u.getScore() + ","));

		return mapToSearchUserResponseDTO(recomendedUsers);
	}
	
	private void updateScorefromExperience(UserInfo u, List<String> taskSkills) {
		float advanceSkillScore = 0;
		List<SkillDetailsDTO> userSkillDTOs = u.getSkillDetails();  //.stream().map(s-> s.getSkill()).collect(Collectors.toList());
		for(String taskSkill : taskSkills) {			
			for(int i=0;i<userSkillDTOs.size();i++) {
				String userSkill = userSkillDTOs.get(i).getSkill().getName();
				if(userSkill.equalsIgnoreCase(taskSkill)) {
					/*if(userSkillDTOs.get(i).getNumOfTaskCompleted() != null)
						advanceSkillScore += userSkillDTOs.get(i).getNumOfTaskCompleted();*/
					if(userSkillDTOs.get(i).getYearsOfExperience() != null)	
						advanceSkillScore += userSkillDTOs.get(i).getYearsOfExperience();
					advanceSkillScore += userSkillDTOs.get(i).getLevel().getId();
				}
				
			}		
		}
		u.setScore(u.getScore() + advanceSkillScore);
		
	}

	private String listToString(List<String> searchWords) {
		StringBuilder sb = new StringBuilder();
		for (String searchWord : searchWords) {
			sb.append(searchWord);
			sb.append(" ");
		}
		return sb.toString();
	}

	@Override
	public Boolean tagSkillsToUserProfile(String user, TagUserSkillDTO tagUserSkills) {
		UserInfo userInfo = userInfoRepository.findBySoeId(user);
		List<SkillDetailsDTO> userSkills = userInfo.getSkillDetails();
		for(String tagUserSkill : tagUserSkills.getSkills()) {
			boolean flag = false;
			for(int i=0;i<userSkills.size();i++) {
				if(tagUserSkill.equalsIgnoreCase(userSkills.get(i).getSkill().getName())) {
					flag=true;
					break;
				}
			}
			if(flag == false) {
				Skill skill = skillsRepository.findSkillsByName(tagUserSkill);
				SkillDetailsDTO skillDTO = new SkillDetailsDTO();
				skillDTO.setSkill(skill);
				skillDTO.setLevel(ExpertiseLevel.BEGINNER);
				skillDTO.setYearsOfExperience(1);
				userSkills.add(skillDTO);
			}
		}
		
		userInfo.setSkillDetails(userSkills);
		userInfoRepository.save(userInfo);		
		return true;
	}

}
