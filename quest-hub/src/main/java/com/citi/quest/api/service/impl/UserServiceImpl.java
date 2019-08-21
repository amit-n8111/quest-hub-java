package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Favorite;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;
import com.citi.quest.api.dtos.SearchUserResponseDTO;
import com.citi.quest.api.dtos.WorkHistoryAndFeedbackDTO;
import com.citi.quest.api.repositories.FavoriteRepository;
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
	TaskRepository taskRepository;

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
			query.addCriteria(Criteria.where("taskTopicId").is(userSearchDTO.getTaskTopicId()));
		}
		if (userSearchDTO.getSkillId() != null) {
			query.addCriteria(Criteria.where("skillId").is(userSearchDTO.getSkillId()));
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

		List<SearchUserResponseDTO> response = new ArrayList<SearchUserResponseDTO>();
		for (UserInfo user : users) {
			SearchUserResponseDTO searchUserResponseDTO = new SearchUserResponseDTO();
			searchUserResponseDTO.setSoeId(user.getSoeId());
			searchUserResponseDTO.setName(user.getName());
			searchUserResponseDTO.setLocation(user.getLocation());
			searchUserResponseDTO.setBuName(user.getBuName());
			searchUserResponseDTO.setRating(user.getRating());
			searchUserResponseDTO.setSkillDetails(user.getSkillDetails());
			Integer taskCompletedId = 5;
			List<Task> tasks = taskRepository.findByTaskAssignedToAndTaskStatusId(user.getSoeId(), taskCompletedId);
			WorkHistoryAndFeedbackDTO workHistoryAndFeedbackDTO = new WorkHistoryAndFeedbackDTO();
			Integer taskCompletedByUser = 0;
			for (Task task : tasks) {
				taskCompletedByUser++;
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

}
