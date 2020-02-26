package com.rubensr.tm.portlet.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.rubensr.tm.model.Task;

public class TaskManagerUtils {
	public static OrderByComparator<Task> getOrderByComparator(String orderByCol, String orderByType){
		return OrderByComparatorFactoryUtil.create("TM_Task", orderByCol, "asc".equals(orderByType));
	}
}
