package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.domain.Favorite;
import com.citi.quest.api.domain.Notification;
import com.citi.quest.api.domain.Question;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.EmailDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.SimillarTaskDTO;
import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.dtos.TaskResponseDTO;
import com.citi.quest.api.enums.RewardType;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.enums.TaskType;
import com.citi.quest.api.notification.EmailNotification;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.repositories.FavoriteRepository;
import com.citi.quest.api.repositories.NotificationRepository;
import com.citi.quest.api.repositories.QuestionsRepository;
import com.citi.quest.api.repositories.SkillsRepository;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.TopicRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.TaskService;
import com.citi.quest.api.util.SequenceGenerator;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private UserInfoRepository userRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private EmailNotification emailNotofication;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private SkillsRepository skillRepository;

	@Autowired
	private QuestionsRepository questionsRepository;

	@Autowired
	private FavoriteRepository favRepository;

	@Autowired
	private NotificationRepository notifRepository;

	private static final String NOTIFICATION_SEQ_KEY = "NOTIFICATION_SEQUENCE";

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Override
	public void postTask(TaskDTO taskDto, String user) {
		// UserInfo userInfo = userRepository.findBySoeId(taskDto.getTaskCreatedBy());

		Task task = updateTaskInfo(taskDto);
		task = taskRepository.save(task);
	}

	@Override
	public Task updateTaskInfo(TaskDTO taskDto) {
		Task task = new Task();

		task.setTaskId(taskDto.getTaskId());
		task.setTaskName(taskDto.getTaskName());
		task.setTaskStatusId(taskDto.getTaskStatusId());
		task.setTaskDescription(taskDto.getTaskDescription());
		task.setTaskTypeName(taskDto.getTaskType());
		task.setTaskDueDate(taskDto.getTaskDueDate());

		task.setScreeningQuestions(taskDto.getScreeningQuestions());
		task.setSkills(taskDto.getSkills());

		task.setTopicId(taskDto.getTaskTopicId());

		task.setTaskCreatedBy(taskDto.getTaskCreatedBy());
		task.setTaskCreateDate(new Date());

		task.setTaskStatusId(1);

		return task;
	}

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public List<TaskResponseDTO> searchTasks(SearchTaskDTO searchTaskDTO, String user) {

		Query query = new Query();
		query.addCriteria(Criteria.where("taskStatusId").is(TaskStatus.PUBLISHED.getId()));
		if (StringUtils.isNotBlank(searchTaskDTO.getCreatedBy())) {
			query.addCriteria(Criteria.where("taskCreatedBy").is(searchTaskDTO.getCreatedBy()));
		}
		if (StringUtils.isNotBlank(searchTaskDTO.getSearch())) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("taskName").regex(searchTaskDTO.getSearch()),
					Criteria.where("taskDescription").regex(searchTaskDTO.getSearch()),
					Criteria.where("skills").elemMatch(Criteria.where("name").regex(searchTaskDTO.getSearch()))));

		}

		query.with(new PageRequest(searchTaskDTO.getPageNumber() - 1, searchTaskDTO.getPageSize()));

		if (StringUtils.isNotBlank(searchTaskDTO.getSortBy())) {

			if ("ASC".equalsIgnoreCase(searchTaskDTO.getSortOrder())) {
				query.with(new Sort(Sort.Direction.ASC, searchTaskDTO.getSortBy()));
			} else {
				query.with(new Sort(Sort.Direction.DESC, searchTaskDTO.getSortBy()));
			}

		}
		List<Task> tasks = mongoOperations.find(query, Task.class);
		return mapToTaskResponseDTO(tasks, user);
	}

	@Override
	public List<TaskResponseDTO> mapToTaskResponseDTO(List<Task> tasks, String user) {
		List<TaskResponseDTO> taskDTOs = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tasks)) {
			for (Task task : tasks) {
				TaskResponseDTO taskDTO = new TaskResponseDTO();
				taskDTO.setTaskCreatedBy(userRepository.findBySoeId(task.getTaskCreatedBy()));
				taskDTO.setManHoursNeeded(task.getManHoursNeeded());
				Favorite fav = favRepository.findOne(user);
				if (null != fav && fav.getTaskIds().contains(task.getTaskId())) {
					taskDTO.setMarkedAsFavorite(true);
				}
				List<Application> applications = applicationRepository.findByTaskTaskId(task.getTaskId());
				taskDTO.setNumberOfApplicationRecieved(applications.size());
				taskDTO.setRewardTypeId(task.getRewardTypeId());
				for (RewardType rewardType : RewardType.values()) {
					if (rewardType.getId() == task.getRewardTypeId()) {
						taskDTO.setRewardType(rewardType.getRewardType());
					}
				}
				taskDTO.setScreeningQuestions(task.getScreeningQuestions());
				taskDTO.setTaskCreatedDate(task.getTaskCreateDate());
				taskDTO.setTaskDescription(task.getTaskDescription());
				taskDTO.setTaskDueDate(task.getTaskDueDate());
				taskDTO.setTaskId(task.getTaskId());
				taskDTO.setTaskName(task.getTaskName());
				taskDTO.setTaskSkills(task.getSkills());
				taskDTO.setTaskStatusId(task.getTaskStatusId());
				for (TaskStatus status : TaskStatus.values()) {
					if (status.getId() == task.getTaskStatusId()) {
						taskDTO.setTaskStatusName(status.getStatus());
					}
				}
				taskDTO.setTaskTopicId(task.getTaskTopicId());
				if (null != topicRepository.findOne(task.getTaskTopicId())) {
					taskDTO.setTaskTopicName(topicRepository.findOne(task.getTaskTopicId()).getTopicName());
				}

				taskDTO.setTotalTasks(100);// TO DO
				if (null != task.getTaskTypeName()) {
					taskDTO.setTaskType(task.getTaskTypeId().toString());
					taskDTO.setTaskTypeName(task.getTaskTypeName());
				}

				taskDTOs.add(taskDTO);

			}
		}

		return taskDTOs;
	}

	@Override
	public Boolean applyTask(String user, Long taskId, ApplicationDTO applicationDTO) {
		Application application = new Application();
		UserInfo userInfo = userRepository.findBySoeId(user);
		application.setUser(userInfo);
		application.setScreeningQuestions(applicationDTO.getScreeningQuestions());
		application.setCommentsOrNotes(applicationDTO.getCommentsOrNotes());
		application.setStartDate(applicationDTO.getStartDate());
		application.setEndDate(applicationDTO.getEndDate());
		Task task = taskRepository.findOne(taskId);
		application.setTask(task);
		Long maxId = 0L;
		if (null == application.getId() || application.getId() <= 0) {
			List<Application> applications = applicationRepository.findAll();
			if (CollectionUtils.isNotEmpty(applications)) {
				Application max = applications.stream().max(Comparator.comparing(Application::getId)).get();
				maxId = max.getId();
			}
			application.setId(maxId + 1);
		}
		applicationRepository.save(application);

		// Send mail to Subscriber
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setMailReciever(userInfo.getEmail());
		emailDTO.setSubject("Task Applied Successfully");
		emailDTO.setMailbody("You have applied for Task :: " + task.getTaskName());
		emailNotofication.sendEmail(emailDTO);

		// Send mail to Poster
		emailDTO = new EmailDTO();
		UserInfo poster = userRepository.findBySoeId(task.getTaskCreatedBy());
		emailDTO.setMailReciever(poster.getEmail());
		emailDTO.setSubject("New Application recieved for Task :" + task.getTaskName());
		emailDTO.setMailbody(userInfo.getName() + " has applied for Task :: " + application.getTask().getTaskName());
		emailNotofication.sendEmail(emailDTO);

		// Save Notification
		Notification notification = new Notification();
		if (notification.getId() == null || notification.getId() <= 0) {
			Long nextSequence = sequenceGenerator.getNextSequence(NOTIFICATION_SEQ_KEY);
			notification.setId(nextSequence);
		}
		notification.setIsViewed(false);
		notification.setNotificationTime(new java.util.Date(System.currentTimeMillis()));
		notification.setTaskId(taskId);
		notification.setTaskName(task.getTaskName());
		notification.setTaskOwner(task.getTaskCreatedBy());
		notification.setUserName(userInfo.getName());
		notification.setUserSoeId(user);
		notification.setAplicationId(application.getId());
		notifRepository.save(notification);

		return true;
	}

	@Override
	public Task saveTask(String user, Task task) {
		if (null == task.getTaskId() || task.getTaskId() < 0) {
			task.setTaskCreatedBy(
					null != userRepository.findBySoeId(user) ? userRepository.findBySoeId(user).getSoeId() : "");
			task.setTaskStatusId(TaskStatus.DRAFT.getId());
			task.setTaskCreateDate(new Date());
			if (task.getTaskTypeId() == 1) {
				task.setTaskTypeName(TaskType.MICRO_TASK.getTypeOfTask());
			} else {
				task.setTaskTypeName(TaskType.MICRO_PROJECT.getTypeOfTask());
			}

			handleScreeningQuestionsSave(task);
			handleSkillsSave(task);
		}
		task = handleTaskSaveWithoutId(task);
		return task;
	}

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

	public void handleScreeningQuestionsSave(Task task) {
		Long maxId = 0L;
		List<Question> existingQues = questionsRepository.findAll();
		if (CollectionUtils.isNotEmpty(existingQues)) {
			Question maxSkill = existingQues.stream().max(Comparator.comparing(Question::getId)).get();
			maxId = maxSkill.getId();
		}
		for (Question question : task.getScreeningQuestions()) {
			if (question.getId() == null || question.getId() <= 0) {
				maxId = maxId + 1;
				question.setId(maxId);
				questionsRepository.save(question);
			}
		}
	}

	public Task handleTaskSaveWithoutId(Task task) {
		Long maxId = 0L;
		if (task.getTaskId() == null || task.getTaskId() <= 0) {
			List<Task> tasks = taskRepository.findAll();
			if (CollectionUtils.isNotEmpty(tasks)) {
				Task max = tasks.stream().max(Comparator.comparing(Task::getTaskId)).get();
				maxId = max.getTaskId();
			}
			task.setTaskId(maxId + 1);
		}
		return taskRepository.save(task);
	}

	public TaskResponseDTO getTask(Long taskId, String user) {
		Task task = taskRepository.findOne(taskId);
		//task are simmilar if they have 
	    //	1.simmilar task name
		//	2.simmilar skills
		//	3.simmilar topic
		List<String> taskSkills = task.getSkills().stream().map(skill -> skill.getName()).collect(Collectors.toList());
		String searchString = listToString(taskSkills);
		searchString = new StringBuilder().append(task.getTaskName()).append(" ").append(searchString).toString();
		System.out.println("searchString "+searchString);
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matching(searchString);
		Query query = TextQuery.queryText(criteria)
				  .sortByScore().addCriteria(Criteria.where("taskStatusId").is(2)).limit(5);

		List<Task> simmilarTasks = mongoTemplate.find(query, Task.class);
		
		System.out.println("\n scores of siillar tasks");
		simmilarTasks.stream().forEach(t -> System.out.print(t.getScore()+","));
		
		System.out.println("simmilarTasks size "+simmilarTasks.size());
		List<SimillarTaskDTO> simillarTask = makeSimillarTaskList(simmilarTasks);
		//************************
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		List<TaskResponseDTO> taskDTOs = mapToTaskResponseDTO(tasks, user);
		taskDTOs.get(0).setSimillarTasks(simillarTask);
		return taskDTOs.get(0);
	}

	

	

	public List<TaskResponseDTO> getRecomendedTasks(String user, int pageNum, int pageSize) {
		
		UserInfo userInfo = userInfoRepository.findBySoeId(user);
		List<SkillDetailsDTO> skills = userInfo.getSkillDetails();
		List<Topic> topics = userInfo.getTopicsSubscribed();
		
		List<String> skillWords = skills.stream().map(skill -> skill.getSkill().getName()).collect(Collectors.toList());
		List<Integer> skillExp = skills.stream().map(skill -> skill.getYearsOfExperience()).collect(Collectors.toList());
		List<String> searchWords = new ArrayList<String>(skillWords);
		searchWords.addAll(topics.stream().map(topic -> topic.getTopicName()).collect(Collectors.toList()));
		String searchString = listToString(searchWords);
		
		/*TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
				  .onField("skills.name", 2F)
				  //.onField("taskDescription", 1F)
				  .onField("taskName", 1F)
				  .build();
		mongoTemplate.indexOps(Task.class).ensureIndex(textIndex);*/	
		
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matching(searchString);
		Query query = TextQuery.queryText(criteria)
				  .sortByScore().addCriteria(Criteria.where("taskStatusId").is(2))
				  .with(new PageRequest(pageNum, pageSize));

		List<Task> recomendedTasks = mongoTemplate.find(query, Task.class);
		
		System.out.println("\n index after sort");
		recomendedTasks.stream().forEach(task -> System.out.print(task.getTaskId()+","));
		System.out.println("\n scores after sort");
		recomendedTasks.stream().forEach(task -> System.out.print(task.getScore()+","));
		
		for(Task t : recomendedTasks) {
			updateScorefromExperience(t,skillWords,skillExp);
		}
		
		//sorting based on experience		
		recomendedTasks.sort((o1,o2) -> o2.getScore().compareTo(o1.getScore()));
		
		System.out.println("\n index after sort");
		recomendedTasks.stream().forEach(task -> System.out.print(task.getTaskId()+","));
		System.out.println("\n scores after sort");
		recomendedTasks.stream().forEach(task -> System.out.print(task.getScore()+","));
		
		return mapToTaskResponseDTO(recomendedTasks, user);
	}

	private void updateScorefromExperience(Task t, List<String> skillWords, List<Integer> skillExp) {
		float releventExperience=0;
		List<Skill> taskSkills = t.getSkills();
		for(int i=0;i<taskSkills.size();i++) {
			System.out.println("\n\nlooping thru taskSkills "+taskSkills.get(i).getName());
			if(skillWords.contains(taskSkills.get(i).getName())) {				
				releventExperience += skillExp.get(i);
				System.out.println("found "+taskSkills.get(i).getName()+", "+ "current releventExperience="+releventExperience);
			}
		}
		System.out.println("final releventExperience "+ releventExperience);
		System.out.println("before score "+t.getScore());
		t.setScore( t.getScore() + releventExperience/100 );
		System.out.println("after score "+t.getScore());
	}

	@Override
	public Task approveTask(Long taskId, String applicant) {
		Task task = taskRepository.findByTaskId(taskId);
		task.setTaskStatusId(2);
		task.setTaskAssignedTo(applicant);
		return task;
	}
	
	
	private String listToString(List<String> searchWords) {
		StringBuilder sb = new StringBuilder();		
		for(String searchWord : searchWords) {			
			sb.append(searchWord);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	private List<SimillarTaskDTO> makeSimillarTaskList(List<Task> similarTasks) {
		List<SimillarTaskDTO> similarTaskDTOs = new ArrayList<SimillarTaskDTO>();
		for(Task similarTask : similarTasks) {
			similarTaskDTOs.add(new SimillarTaskDTO(similarTask.getTaskId(),similarTask.getTaskName()));
		}
		return similarTaskDTOs;
	}

}
