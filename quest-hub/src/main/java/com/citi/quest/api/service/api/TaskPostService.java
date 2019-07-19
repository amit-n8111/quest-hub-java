package com.citi.quest.api.service.api;

import com.citi.quest.api.domain.Task;

public interface TaskPostService {

	public Task postTask(Task task, String user);
}
