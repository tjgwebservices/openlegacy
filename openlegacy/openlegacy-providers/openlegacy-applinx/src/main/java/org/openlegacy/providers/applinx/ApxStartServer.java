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
package org.openlegacy.providers.applinx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ApxStartServer {

	public static void main(String[] args) throws IOException {
		new ClassPathXmlApplicationContext("classpath*:/test-apx-context.xml");

		System.out.println("Press any key stop");
		System.in.read();
	}
}
