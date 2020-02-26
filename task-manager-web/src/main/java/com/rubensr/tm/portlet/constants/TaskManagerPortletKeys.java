package com.rubensr.tm.portlet.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author RUBEN
 */
public class TaskManagerPortletKeys {

	public static final String TASKMANAGER = "com_rubensr_tm_portlet_TaskManagerPortlet";
	
	public static final int STATUS_PENDING = 0;
	public static final int STATUS_COMPLETED = 1;
	
	public static final String LABEL_PENDING = "pending";
	public static final String LABEL_COMPLETED = "completed";

	public static String getStatusLabel(int status) {
		if (status == STATUS_PENDING) {
			return LABEL_PENDING;
		}
		else if (status == STATUS_COMPLETED) {
			return LABEL_COMPLETED;
		}
		return StringPool.BLANK;
	}
}