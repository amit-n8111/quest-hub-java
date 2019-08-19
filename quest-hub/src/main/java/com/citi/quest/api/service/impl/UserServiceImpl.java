package com.citi.quest.api.service.impl;

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
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;
import com.citi.quest.api.repositories.FavoriteRepository;
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
	public List<UserInfo> getAllUserInfo(SearchUserDTO userSearchDTO) {

		Query query = new Query();

		if (userSearchDTO.getTaskTopicId() != null) {
			query.addCriteria(Criteria.where("taskTopicId").is(userSearchDTO.getTaskTopicId()));
		}
		if (userSearchDTO.getSkillId() != null) {
			query.addCriteria(Criteria.where("skillId").is(userSearchDTO.getSkillId()));
		}
		if (StringUtils.isNotBlank(userSearchDTO.getSearch())) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("soeId").regex(userSearchDTO.getSearch()),
					Criteria.where("managerSoeId").regex(userSearchDTO.getSearch()),
					Criteria.where("name").regex(userSearchDTO.getSearch()),
					Criteria.where("location").regex(userSearchDTO.getSearch()),
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

		return mongoOperations.find(query, UserInfo.class);
	}

}
