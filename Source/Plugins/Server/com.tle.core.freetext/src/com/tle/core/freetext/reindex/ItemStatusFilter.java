/*
 * Copyright 2017 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.freetext.reindex;

import com.tle.beans.entity.itemdef.ItemDefinition;
import com.tle.beans.item.ItemStatus;

/**
 * @author Nicholas Read
 */
public class ItemStatusFilter extends ReindexFilter
{
	private static final long serialVersionUID = 1L;

	private static final String[] NAMES = {"status", "itemDefinition"};

	private Object[] values;

	public ItemStatusFilter(ItemStatus itemStatus, ItemDefinition itemDefinition)
	{
		values = new Object[]{itemStatus.name(), itemDefinition.getId()};
	}

	@Override
	protected String getWhereClause()
	{
		return "where status = :status and itemDefinition.id = :itemDefinition";
	}

	@Override
	protected String[] getNames()
	{
		return NAMES;
	}

	@Override
	protected Object[] getValues()
	{
		return values;
	}
}