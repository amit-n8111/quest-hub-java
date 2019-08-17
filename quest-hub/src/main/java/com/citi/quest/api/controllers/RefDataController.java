package com.citi.quest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.dtos.StaticDataDTO;
import com.citi.quest.api.repositories.TopicRepository;

import io.swagger.annotations.Api;

@RestController
@Api("API responcible for loading ref data")
@RequestMapping("api/v1/refData")
public class RefDataController {

	@Autowired
	TopicRepository topicRepository;

	@GetMapping(value = "")
	StaticDataDTO getStaticData() {

		List<Topic> topicsList = topicRepository.findAll();
		Topic[] topics = topicsList.toArray(new Topic[topicsList.size()]);
		StaticDataDTO staticData = new StaticDataDTO();
		staticData.setTopic(topics);

		return staticData;

	}

}
