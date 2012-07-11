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
package org.openlegacy.layout;

import org.openlegacy.definitions.TableDefinition;
import org.openlegacy.definitions.TableDefinition.ColumnDefinition;

import java.util.List;

public interface PagePartDefinition {

	int getColumns();

	List<PagePartRowDefinition> getPartRows();

	int getWidth();

	TableDefinition<ColumnDefinition> getTableDefinition();
}
