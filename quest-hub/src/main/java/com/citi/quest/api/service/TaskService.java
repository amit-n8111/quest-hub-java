package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.dtos.TaskResponseDTO;

public interface TaskService {

	void postTask(TaskDTO taskDto, String user);

	Task saveTask(String user, Task task);

	Boolean applyTask(String user, Long taskId, ApplicationDTO applicationDTO);

	List<TaskResponseDTO> searchTasks(SearchTaskDTO searchTaskDTO, String user);

	List<Task> getTasks();

	Task updateTaskInfo(TaskDTO taskDto);

	List<TaskResponseDTO> mapToTaskResponseDTO(List<Task> tasks, String user);

}
