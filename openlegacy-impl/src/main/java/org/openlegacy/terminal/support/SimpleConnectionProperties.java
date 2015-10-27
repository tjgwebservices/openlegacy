/*******************************************************************************
 * Copyright (c) 2014 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package org.openlegacy.terminal.support;

import org.openlegacy.terminal.ConnectionProperties;

public class SimpleConnectionProperties implements ConnectionProperties {

	private static final long serialVersionUID = 1L;

	private String deviceName;
	private String codePage;

	@Override
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setCodePage(String codePage) {
		this.codePage = codePage;
	}

	@Override
	public String getCodePage() {
		return codePage;
	}

	public static SimpleConnectionProperties clone(ConnectionProperties connectionProeprties) {
		SimpleConnectionProperties result = new SimpleConnectionProperties();
		result.setDeviceName(connectionProeprties.getDeviceName());
		result.setCodePage(connectionProeprties.getCodePage());
		return result;
	}

}
