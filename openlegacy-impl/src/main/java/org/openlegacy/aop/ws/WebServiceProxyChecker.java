/*******************************************************************************
 * Copyright (c) 2015 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/

package org.openlegacy.aop.ws;

import org.openlegacy.annotations.ws.Service;
import org.openlegacy.aop.BeanProxyChecker;
import org.springframework.core.annotation.AnnotationUtils;

public class WebServiceProxyChecker implements BeanProxyChecker {

	@Override
	public boolean needToProxy(Class<?> clazz) {
		return AnnotationUtils.findAnnotation(clazz, Service.class) != null;
	}

}
