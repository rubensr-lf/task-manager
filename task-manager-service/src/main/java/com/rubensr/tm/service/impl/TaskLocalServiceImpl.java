/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rubensr.tm.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.rubensr.tm.model.Task;
import com.rubensr.tm.service.base.TaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.rubensr.tm.service.TaskLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Ruben Sanchez
 * @see TaskLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.rubensr.tm.model.Task",
	service = AopService.class
)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.rubensr.tm.service.TaskLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.rubensr.tm.service.TaskLocalServiceUtil</code>.
	 */
	
	public List<Task> getTasks (long companyId, long userId, int start, int end){
		return taskPersistence.findByC_U(companyId, userId, start, end);
	}
	
	public List<Task> getTasks (long companyId, long userId, int start, int end, OrderByComparator<Task> orderByComparator){
		return taskPersistence.findByC_U(companyId, userId, start, end, orderByComparator);
	}
	
	public int getTasksCount (long companyId, long userId) {
		return taskPersistence.countByC_U(companyId, userId);
	}
	
	public Task addTask (long userId, String title, String description) throws PortalException {
		User user = userLocalService.getUserById(userId);
		Date now = new Date();
		long taskId = counterLocalService.increment();
		Task task = taskPersistence.create(taskId);
		task.setCompanyId(user.getCompanyId());
		task.setCreateDate(now);
		task.setDescription(description);
		task.setModifiedDate(now);
		task.setStatus(0);
		task.setTitle(title);
		task.setUserId(userId);
		task.setUserName(user.getFullName());
		taskPersistence.update(task);
		return task;
	}
	
	public Task updateTask (long userId, long taskId, String title, String description, int status) throws PortalException {
		User user = userLocalService.getUserById(userId);
		Date now = new Date();
		Task task = taskPersistence.findByPrimaryKey(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setModifiedDate(now);
		task.setStatus(status);
		if(status == 1) {
			task.setFinishDate(now);
		}
		task.setUserId(userId);
		task.setUserName(user.getFullName());
		taskPersistence.update(task);
		return task;
	}
}