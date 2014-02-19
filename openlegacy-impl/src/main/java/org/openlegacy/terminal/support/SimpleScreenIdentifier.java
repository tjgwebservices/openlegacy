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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.TerminalPositionContainer;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.services.ScreenIdentifier;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * A simple implementation for a screen identifier
 * 
 */
public class SimpleScreenIdentifier implements ScreenIdentifier, TerminalPositionContainer, Serializable {

	private static final long serialVersionUID = 1L;

	private final static Log logger = LogFactory.getLog(SimpleScreenIdentifier.class);

	private TerminalPosition position;
	private String text;

	private boolean supportRightToLeft;

	public SimpleScreenIdentifier(TerminalPosition position, String text, boolean supportRightToLeft) {
		this.position = position;
		this.text = text;
		this.supportRightToLeft = supportRightToLeft;
	}

	public boolean match(TerminalSnapshot terminalSnapshot) {
		String foundText = terminalSnapshot.getText(position, text.length());
		if (foundText.equals(text)) {
			if (logger.isTraceEnabled()) {
				logger.trace(MessageFormat.format("Found text on screen:\''{0}\'' matched to identifier:\''{1}\''", foundText,
						text));
			}
			return true;
		}
		if (supportRightToLeft) {
			foundText = terminalSnapshot.getText(new SimpleTerminalPosition(position.getRow(),
					terminalSnapshot.getSize().getColumns() - (position.getColumn() + text.length() - 2)), text.length());
			foundText = StringUtils.reverse(foundText);
			if (foundText.equals(text)) {
				return true;
			}
		}
		if (logger.isTraceEnabled()) {
			logger.trace(MessageFormat.format("Found text on screen:\''{0}\'' wasnt matched to identifier:\''{1}\''", foundText,
					text));
		}
		return false;
	}

	public TerminalPosition getPosition() {
		return position;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return SnapshotUtils.positionTextToString(position, text);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
