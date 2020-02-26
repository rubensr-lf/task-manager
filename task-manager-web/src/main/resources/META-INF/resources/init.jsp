<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="javax.portlet.PortletURL" %>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@page import="com.liferay.taglib.search.ResultRow" %>
<%@page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@page import="com.liferay.petra.string.StringPool" %>
<%@page import="com.rubensr.tm.service.TaskLocalServiceUtil" %>
<%@page import="com.rubensr.tm.model.Task" %>
<%@page import="com.rubensr.tm.portlet.constants.TaskManagerPortletKeys" %>
<%@page import="com.rubensr.tm.portlet.util.TaskManagerUtils" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
long userId = themeDisplay.getUserId();
long companyId = themeDisplay.getCompanyId();
%>