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

import javax.swing.JComboBox;

/**
 * This class is basis for FreeList and EnumList. It's used to provide a basic
 * functionality to connect a output string to a free object for selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractExtendedComboBox extends JComboBox {

	private static final long serialVersionUID = 34435300495300631L;

	private Hashtable<Object, Object> listData = new Hashtable<Object, Object>();

	public AbstractExtendedComboBox() {
		super();
	}

	protected void setItems(ArrayList<Object> displayItems,
			ArrayList<Object> assignedItems) {
		removeAll();
		if (displayItems.size() != assignedItems.size()) {
			throw new IllegalArgumentException(
					"The length of displayItems is unequal to assignedItems!");
		}
		for (int index = 0; index < displayItems.size(); index++) {
			listData.put(displayItems.get(index), assignedItems.get(index));
			addItem(displayItems.get(index));
		}
	}

	protected void setItems(Hashtable<Object, Object> listData) {
		removeAll();
		this.listData = listData;
		if (listData == null) {
			return;
		}
		for (Object displayString : listData.keySet()) {
			addItem((String) displayString);
		}
	}

	public void setSelectedItem(Object item) {
		System.out.println(item.toString());
		for (Object key : listData.keySet()) {
			if (listData.get(key).equals(item) || (key.equals(item))) {
				super.setSelectedItem(key);
			}
		}
	}

	public Object getSelectedItem() {
		Object value = super.getSelectedItem();
		if (value != null) {
			value = listData.get(value);
		}
		return value;
	}
}
