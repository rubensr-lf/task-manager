package com.rubensr.tm.portlet;

import com.rubensr.tm.model.Task;
import com.rubensr.tm.portlet.constants.TaskManagerPortletKeys;
import com.rubensr.tm.service.TaskLocalService;

import java.io.IOException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author RUBEN
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Task Manager",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TaskManagerPortlet extends MVCPortlet {
	
	@Reference
	private Portal _portal;
	
	@Reference
	private TaskLocalService _taskLocalService;
	
	private static final Log _log = LogFactoryUtil.getLog(TaskManagerPortlet.class);
	
	public void updateTask(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long taskId = ParamUtil.getLong(actionRequest, "taskId");

		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
		int status = ParamUtil.getInteger(actionRequest, "status");

		try {
			if (taskId <= 0) {
				_taskLocalService.addTask(themeDisplay.getUserId(), title, description);
			} else {
				_taskLocalService.updateTask(themeDisplay.getUserId(), taskId, title, description, status);
			}

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/view.jsp");
			actionResponse.sendRedirect(portletURL.toString());
		}
		catch (Exception e) {
			_log.error(e);
		}
	}
	
	public void finishTask(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long taskId = ParamUtil.getLong(actionRequest, "taskId");

		try {
			Task task = _taskLocalService.getTask(taskId);
			_taskLocalService.updateTask(themeDisplay.getUserId(), taskId, task.getTitle(), task.getDescription(), 1);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}
	
	public void deleteTask(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long taskId = ParamUtil.getLong(actionRequest, "taskId");

		try {
			_taskLocalService.deleteTask(taskId);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}
	
	
	
	// Check if there is a logged-in user in case the portlet is added on a public page
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			if (themeDisplay.isSignedIn()) {
				super.processAction(actionRequest, actionResponse);
			} else {
				String currentURL = PortalUtil.getCurrentURL(actionRequest);
				String redirect = themeDisplay.getURLSignIn().concat("?redirect=").concat(URLCodec.encodeURL(currentURL));
				actionResponse.sendRedirect(redirect);
			}
		} catch (IOException e) {
			_log.error(e);
		}
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			if (themeDisplay.isSignedIn()) {
				super.render(renderRequest, renderResponse);
			} else {
				String currentURL = PortalUtil.getCurrentURL(renderRequest);
				String redirect = themeDisplay.getURLSignIn().concat("?redirect=").concat(URLCodec.encodeURL(currentURL));
				HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(renderResponse);
				httpServletResponse.sendRedirect(redirect);
			}
		} catch (IOException e) {
			_log.error(e);
		}
	}
}