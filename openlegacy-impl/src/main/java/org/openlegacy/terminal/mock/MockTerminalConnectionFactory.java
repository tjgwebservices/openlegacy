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
package org.openlegacy.terminal.mock;

import org.openlegacy.terminal.ConnectionProperties;
import org.openlegacy.terminal.TerminalConnection;

public class MockTerminalConnectionFactory extends AbstractMockTerminalConnectionFactory {

	@Override
	public TerminalConnection getConnection(ConnectionProperties connectionProperties) {
		MockTerminalConnection connection = new MockTerminalConnection(fetchSnapshots());
		connection.setConnected(true);
		return connection;
	}

	@Override
	public void disconnect(TerminalConnection terminalConnection) {
		((MockTerminalConnection)terminalConnection).disconnect();
	}

}
