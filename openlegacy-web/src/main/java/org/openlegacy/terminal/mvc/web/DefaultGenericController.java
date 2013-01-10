/*******************************************************************************
 * Copyright (c) 2012 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package org.openlegacy.terminal.mvc.web;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.json.JsonSerializationUtil;
import org.openlegacy.terminal.layout.ScreenPageBuilder;
import org.openlegacy.terminal.services.ScreenEntitiesRegistry;
import org.openlegacy.terminal.utils.ScreenEntityUtils;
import org.openlegacy.utils.ProxyUtil;
import org.openlegacy.utils.ReflectionUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * OpenLegacy default web controller for a terminal session. Handles GET/POST requests of a web application. Works closely with
 * generic.jspx / composite.jspx. Saves the need for a dedicated controller and page for each screen API, if such doesn't exists.
 * 
 * @author Roi Mor
 * 
 */
@Controller
public class DefaultGenericController {

	private static final String ACTION = "action";

	private final static Log logger = LogFactory.getLog(DefaultGenericController.class);

	private static final String PAGE = "page";

	@Inject
	private TerminalSession terminalSession;

	@Inject
	private ScreenEntitiesRegistry screenEntitiesRegistry;

	@Inject
	private ScreenEntityUtils screenEntityUtils;

	@Inject
	private ScreenPageBuilder pageBuilder;

	@RequestMapping(value = "/{screen}", method = RequestMethod.GET)
	public String getScreenEntity(@PathVariable("screen") String screenEntityName,
			@RequestParam(value = "key", required = false) Object[] keys, Model uiModel) throws IOException {

		ScreenEntity screenEntity = (ScreenEntity)terminalSession.getEntity(screenEntityName, keys);
		uiModel.addAttribute(StringUtils.uncapitalize(screenEntityName), screenEntity);
		ScreenEntityDefinition entityDefinition = screenEntitiesRegistry.get(screenEntityName);
		uiModel.addAttribute(PAGE, pageBuilder.build(entityDefinition));
		return determineViewName(entityDefinition);

	}

	private static String determineViewName(ScreenEntityDefinition entityDefinition) {
		if (entityDefinition.getChildEntitiesDefinitions().size() > 0) {
			return entityDefinition.getEntityName() + MvcConstants.COMPOSITE_SUFFIX;
		}
		return entityDefinition.getEntityName();

	}

	@RequestMapping(value = "/{screen}", method = RequestMethod.GET, params = "partial=1")
	public String getChildScreenEntity(@PathVariable("screen") String screenEntityName, Model uiModel) throws IOException {
		return getScreenEntity(screenEntityName, null, uiModel);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getScreenEntity(HttpServletResponse response, Model uiModel) throws IOException {

		ScreenEntity screenEntity = terminalSession.getEntity();
		String screenEntityName = screenEntityUtils.getEntityName(screenEntity);
		uiModel.addAttribute(screenEntityName, screenEntity);
		return screenEntityName;

	}

	@RequestMapping(value = "/{screen}", method = RequestMethod.POST)
	public String postScreenEntity(@PathVariable("screen") String screenEntityName,
			@RequestParam(defaultValue = "", value = ACTION) String action, HttpServletRequest request,
			HttpServletResponse response, Model uiModel) throws IOException {

		Class<?> entityClass = findAndHandleNotFound(screenEntityName, response);
		if (entityClass == null) {
			return null;
		}

		ScreenEntity screenEntity = (ScreenEntity)terminalSession.getEntity(screenEntityName);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(screenEntity);
		binder.bind(request);

		screenEntityUtils.sendScreenEntity(terminalSession, screenEntity, action);
		screenEntity = terminalSession.getEntity();
		if (request.getParameter("partial") != null) {
			return returnPartialPage(screenEntityName, uiModel);
		} else {
			if (screenEntity == null) {
				return "redirect:emulation";
			}
			String resultEntityName = ProxyUtil.getOriginalClass(screenEntity.getClass()).getSimpleName();
			return "redirect:" + resultEntityName;
		}
	}

	private String returnPartialPage(String screenEntityName, Model uiModel) {
		ScreenEntity resultEntity = (ScreenEntity)terminalSession.getEntity(screenEntityName);
		String resultEntityName = ProxyUtil.getOriginalClass(resultEntity.getClass()).getSimpleName();
		uiModel.addAttribute(resultEntityName, resultEntity);
		ScreenEntityDefinition entityDefinition = screenEntitiesRegistry.get(resultEntityName);
		uiModel.addAttribute(PAGE, pageBuilder.build(entityDefinition));
		return resultEntityName;
	}

	/**
	 * Look for the given screen entity in the registry, and return HTTP 400 (BAD REQUEST) in case it's not found
	 * 
	 * @param screenEntityName
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private Class<?> findAndHandleNotFound(String screenEntityName, HttpServletResponse response) throws IOException {
		Class<?> entityClass = screenEntitiesRegistry.getEntityClass(screenEntityName);
		if (entityClass == null) {
			String message = MessageFormat.format("Screen entity {0} not found", screenEntityName);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
			logger.error(message);
		}
		return entityClass;
	}

	/**
	 * handle Ajax request for auto compete fields
	 * 
	 * @param screenEntityName
	 * @param fieldName
	 * @return JSON content
	 */
	@RequestMapping(value = "/{screen}/{field}", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
	@ResponseBody
	public ResponseEntity<String> autoCompleteJson(@PathVariable("screen") String screenEntityName,
			@PathVariable("field") String fieldName) {
		ScreenEntity screenEntity = (ScreenEntity)terminalSession.getEntity(screenEntityName);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/text; charset=utf-8");
		@SuppressWarnings("unchecked")
		Map<Object, Object> fieldValues = (Map<Object, Object>)ReflectionUtil.invoke(screenEntity,
				MessageFormat.format("get{0}Values", StringUtils.capitalize(fieldName)));

		String result = JsonSerializationUtil.toDojoFormat(fieldValues);
		return new ResponseEntity<String>(result, headers, HttpStatus.OK);
	}

}
