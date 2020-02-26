create table TM_Task (
	uuid_ VARCHAR(75) null,
	taskId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(500) null,
	description STRING null,
	status INTEGER,
	finishDate DATE null
);