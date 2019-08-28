package com.citi.quest.api.service;

import com.citi.quest.api.dtos.DashboardDTO;

public interface DashboardService {

	DashboardDTO getSeekerDashboardData(String user);

	DashboardDTO getPosterDashboardData(String user);

}
