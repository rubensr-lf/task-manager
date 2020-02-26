<%@ include file="init.jsp" %>
<% if (themeDisplay.isSignedIn()) { 
PortletURL portletURL = renderResponse.createRenderURL();

int delta = ParamUtil.getInteger(request, "delta", 5);
String orderByCol = ParamUtil.getString(request, "orderByCol", "createDate");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");
%>
<div class="container-fluid container-fluid-max-xl main-content-body">

	<div class="button-holder">
		<portlet:renderURL var="addTaskURL">
			<portlet:param name="mvcPath" value="/edit_task.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<aui:button href="<%= addTaskURL %>" icon="icon-plus" value="add-task"/>
	</div>

	<div class="w-100">
		<liferay-ui:search-container
			emptyResultsMessage="no-tasks-were-found"
			iteratorURL="<%= portletURL %>"
			total="<%= TaskLocalServiceUtil.getTasksCount(companyId, userId)%>"
			delta="<%=delta%>"
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
		>
			<liferay-ui:search-container-results
				results="<%= TaskLocalServiceUtil.getTasks(companyId, userId, searchContainer.getStart(), searchContainer.getEnd(), TaskManagerUtils.getOrderByComparator(orderByCol, orderByType)) %>"
			/>
		
			<liferay-ui:search-container-row className="com.rubensr.tm.model.Task" escapedModel="<%= true %>" keyProperty="tasksId" modelVar="taskEntry">
		
				<%
					String rowHREF = null;
					PortletURL rowURL = renderResponse.createRenderURL();
					rowURL.setParameter("mvcPath", "/edit_task.jsp");
					rowURL.setParameter("taskId", String.valueOf(taskEntry.getTaskId()));
					rowHREF = rowURL.toString();
				%>
				
				<liferay-ui:search-container-column-text name="title" property="title" orderable="true" href="<%= rowHREF %>"></liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="description" href="<%= rowHREF %>"><%=StringUtil.shorten(taskEntry.getDescription(), 150)%></liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-date name="create-date" property="createDate" orderable="true" orderableProperty="createDate"></liferay-ui:search-container-column-date>
				<liferay-ui:search-container-column-text name="status" orderable="true" cssClass="<%=(taskEntry.getStatus() == TaskManagerPortletKeys.STATUS_PENDING)?"label-warning":"label-success"%>"><liferay-ui:message key="<%=TaskManagerPortletKeys.getStatusLabel(taskEntry.getStatus())%>"/></liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-jsp	path="/actions.jsp"/> 
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</div>

<% } %>