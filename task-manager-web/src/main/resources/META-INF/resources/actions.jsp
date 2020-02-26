<%@ include file="init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Task task = (Task)row.getObject();
%>

<liferay-ui:icon-menu direction="left-side"	icon="<%= StringPool.BLANK %>" markupView="lexicon"	message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/edit_task.jsp" />
		<portlet:param name="taskId" value="<%= String.valueOf(task.getTaskId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon message="edit" url="<%= editURL %>"/>

	<%if(task.getStatus() == TaskManagerPortletKeys.STATUS_PENDING){%>
		<portlet:actionURL name="finishTask" var="finishURL">
			<portlet:param name="taskId" value="<%= String.valueOf(task.getTaskId()) %>" />
		</portlet:actionURL>	
		<liferay-ui:icon message="finished" url="<%= finishURL %>"/>	
	<%}%>

	<portlet:actionURL name="deleteTask" var="deleteURL">
		<portlet:param name="taskId" value="<%= String.valueOf(task.getTaskId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>