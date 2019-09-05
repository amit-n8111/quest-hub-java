package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.OwnerTaskSuggestionDTO;
import com.citi.quest.api.dtos.OwnerTaskSuggestionResponseDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.dtos.TaskFeedbackDTO;
import com.citi.quest.api.dtos.TaskResponseDTO;

public interface TaskService {

	void postTask(TaskDTO taskDto, String user);

	Task saveTask(String user, Task task, Boolean isSubmitOperation);

	Boolean applyTask(String user, Long taskId, ApplicationDTO applicationDTO);

	List<TaskResponseDTO> searchTasks(SearchTaskDTO searchTaskDTO, String user, Boolean isMyTasks);

	List<Task> getTasks();

	Task updateTaskInfo(TaskDTO taskDto);

	List<TaskResponseDTO> mapToTaskResponseDTO(List<Task> tasks, String user);

	Task approveTask(Long taskId, String applicant);

	boolean setTaskFeedback(TaskFeedbackDTO feedbakDTO, String user);

	OwnerTaskSuggestionResponseDTO getTaskSuggestions(OwnerTaskSuggestionDTO search, String user);

	List<TaskResponseDTO> getRecomendedTasks(String user, int pageNum, int pageSize);

	TaskResponseDTO getTask(Long taskId, String user);

}
