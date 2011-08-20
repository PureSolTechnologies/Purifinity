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
import java.util.Map;

import javax.swing.ListModel;

public class FreeList extends AbstractExtendedList {

    private static final long serialVersionUID = 210162776409051253L;

    public FreeList() {
	super();
    }

    public FreeList(ListModel listModel) {
	super(listModel);
    }

    public void setListData(ArrayList<Object> displayItems,
	    ArrayList<Object> assignedItems) {
	super.setListData(displayItems, assignedItems);
    }

    public void setListData(Map<Object, Object> listData) {
	super.setListData(listData);
    }

    public Object getSelectedValue() {
	return super.getSelectedValue();
    }

    public Object[] getSelectedValues() {
	return super.getSelectedValues();
    }
}
