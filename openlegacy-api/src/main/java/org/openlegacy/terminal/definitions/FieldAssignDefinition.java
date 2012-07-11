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
package org.openlegacy.terminal.definitions;

/**
 * Field assign definition defines fields which should be send to the terminal, in the given context (navigation, etc).
 * 
 */
public interface FieldAssignDefinition {

	/**
	 * marker for not sending field content in the given context
	 */
	String NULL = "$XX$";

	String getName();

	String getValue();
}
