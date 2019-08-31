package com.citi.quest.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.dtos.DashboardDTO;
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

		return null;
	}

}
