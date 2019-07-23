package com.citi.quest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.citi.quest.api.repositories")
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class QuestHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestHubApplication.class, args);
	}
	
	/*
	 * @Bean public CascadeSaveEventListner cascadeSaveEventListner() { return new
	 * CascadeSaveEventListner(); }
	 */
	
}
