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
package org.openlegacy.definitions.support;

import org.openlegacy.definitions.DateFieldTypeDefinition;

import java.io.Serializable;

public class SimpleDateFieldTypeDefinition implements DateFieldTypeDefinition, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer dayColumn;
	private Integer monthColumn;
	private Integer yearColumn;
	private String pattern;
	private String locale;

	public SimpleDateFieldTypeDefinition() {}

	public SimpleDateFieldTypeDefinition(Integer dayColumn, Integer monthColumn, Integer yearColumn) {
		this.dayColumn = dayColumn;
		this.monthColumn = monthColumn;
		this.yearColumn = yearColumn;
	}

	public SimpleDateFieldTypeDefinition(String pattern, String locale) {
		this.pattern = pattern;
		this.locale = locale;
	}

	public SimpleDateFieldTypeDefinition(Integer dayColumn, Integer monthColumn, Integer yearColumn, String pattern) {
		this.dayColumn = dayColumn;
		this.monthColumn = monthColumn;
		this.yearColumn = yearColumn;
		this.pattern = pattern;
	}

	@Override
	public String getTypeName() {
		return "date";
	}

	@Override
	public Integer getYearColumn() {
		return yearColumn;
	}

	@Override
	public Integer getMonthColumn() {
		return monthColumn;
	}

	@Override
	public Integer getDayColumn() {
		return dayColumn;
	}

	@Override
	public String getPattern() {
		return pattern;
	}

	@Override
	public String getLocale() {
		return locale;
	}

}
