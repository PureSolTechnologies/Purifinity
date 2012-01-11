/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
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
 *
 ***************************************************************************/

package com.puresol.gui;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

/**
 * This class is basis for FreeList and EnumList. It's used to provide a basic
 * functionality to connect a output string to a free object for selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractExtendedList extends JList {

	private static final long serialVersionUID = 34435300495300631L;

	private final Map<Object, Object> listData = new Hashtable<Object, Object>();

	public AbstractExtendedList() {
		super();
	}

	public AbstractExtendedList(ListModel listModel) {
		super(listModel);
	}

	protected void setListData(ArrayList<Object> displayItems,
			ArrayList<Object> assignedItems) {
		if (displayItems.size() != assignedItems.size()) {
			throw new IllegalArgumentException(
					"The length of displayItems is unequal to assignedItems!");
		}
		Map<Object, Object> listData = new Hashtable<Object, Object>();
		for (int index = 0; index < displayItems.size(); index++) {
			listData.put(displayItems.get(index), assignedItems.get(index));
		}
		setListData(listData);
	}

	protected void setListData(Map<Object, Object> listData) {
		removeAll();
		if (listData != null) {
			this.listData.putAll(listData);
			setListData(new Vector<Object>(listData.keySet()));
		}
	}

	public void setSelectedValue(Object value, boolean scroll) {
		for (Object key : listData.keySet()) {
			if (listData.get(key).equals(value) || key.equals(value)) {
				super.setSelectedValue(key, scroll);
			}
		}
	}

	public Object getSelectedValue() {
		Object value = super.getSelectedValue();
		if (value != null) {
			value = listData.get(super.getSelectedValue());
		}
		return value;
	}

	public Object[] getSelectedValues() {
		Object[] values = super.getSelectedValues();
		for (int index = 0; index < values.length; index++) {
			values[index] = listData.get(values[index]);
		}
		return values;
	}

	public void removeAll() {
		super.removeAll();
		listData.clear();
	}
}
