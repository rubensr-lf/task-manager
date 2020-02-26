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

package com.rubensr.tm.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ruben Sanchez
 * @generated
 */
@ProviderType
public class TaskSoap implements Serializable {

	public static TaskSoap toSoapModel(Task model) {
		TaskSoap soapModel = new TaskSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTaskId(model.getTaskId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setStatus(model.getStatus());
		soapModel.setFinishDate(model.getFinishDate());

		return soapModel;
	}

	public static TaskSoap[] toSoapModels(Task[] models) {
		TaskSoap[] soapModels = new TaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TaskSoap[][] toSoapModels(Task[][] models) {
		TaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TaskSoap[] toSoapModels(List<Task> models) {
		List<TaskSoap> soapModels = new ArrayList<TaskSoap>(models.size());

		for (Task model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TaskSoap[soapModels.size()]);
	}

	public TaskSoap() {
	}

	public long getPrimaryKey() {
		return _taskId;
	}

	public void setPrimaryKey(long pk) {
		setTaskId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTaskId() {
		return _taskId;
	}

	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	private String _uuid;
	private long _taskId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private int _status;
	private Date _finishDate;

}