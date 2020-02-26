<%@ include file="init.jsp" %>
<%
long taskId = ParamUtil.getLong(request, "taskId");
Task task = TaskLocalServiceUtil.fetchTask(taskId);
%>
<portlet:actionURL name="updateTask" var="updateTaskURL" />

<aui:form action="<%= updateTaskURL %>" cssClass="container-fluid-1280" method="post" name="fm1">
	<aui:model-context bean="<%= task %>" model="<%= Task.class %>" />
	<aui:input name="mvcPath" type="hidden" value="/edit_task.jsp" />
	<aui:input name="taskId" type="hidden"/>
	<aui:input name="userId" type="hidden"/>
	<aui:input name="status" type="hidden"/>

	<% if(task != null){%>
	<div>
		<p class="article-version-status">
			<b><liferay-ui:message key="status"/>:</b>
			<span class="label <%=(task.getStatus() == TaskManagerPortletKeys.STATUS_PENDING)?"label-warning":"label-success"%> ml-2 text-uppercase">
				<liferay-ui:message key="<%=TaskManagerPortletKeys.getStatusLabel(task.getStatus())%>"/>
			</span>
		</p>
	</div>
	<%}%>
	
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<% if(taskId > 0){%>
				<aui:input label="create-date" name="createDate" disabled="true" autoSize="true"/>
			<%}%>
			<aui:input cssClass="input-task-title" label="title" name="title" autoSize="true">
				<aui:validator name="required" />
			</aui:input>
			<aui:input cssClass="input-task-description" label="description" name="description"></aui:input>	

			<% if(task != null && task.getStatus() == TaskManagerPortletKeys.STATUS_COMPLETED){%>
				<aui:input label="completion-date" name="finishDate" disabled="true" autoSize="true" />
			<%}%>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<portlet:renderURL var="viewURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
		</portlet:renderURL>

		<aui:button onClick="<%= viewURL %>" value="cancel" />
	</aui:button-row>
</aui:form>