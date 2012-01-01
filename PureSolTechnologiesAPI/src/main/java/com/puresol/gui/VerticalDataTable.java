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

import javax.swing.JTable;

import com.puresol.gui.data.VerticalData;

/**
 * This table is for showing result set in an easy and efficient way. The
 * complete result set is shown. For the representation a ResultSetTableModel
 * was generated to present a standard representation for all SQL types.
 * 
 * @author Rick-Rainer Ludwig
 */
public class VerticalDataTable extends JTable {

	private static final long serialVersionUID = 1L;

	/**
	 * This variables keeps the reference to the table model.
	 */
	private VerticalDataTableModel verticalDataTableModel;

	/**
	 * This is the standard constructor.
	 */
	public VerticalDataTable() {
		super();
		verticalDataTableModel = null;
	}

	/**
	 * This is a standard constructor with an additional possibility to set a
	 * initial result set.
	 * 
	 * @param resultSet
	 *            is result set to be shown in the initial view of this table.
	 */
	public VerticalDataTable(VerticalData verticalData) {
		this();
		setVerticalData(verticalData);
	}

	/**
	 * This method sets a new result set and updates the view of the table.
	 * 
	 * @param resultSet
	 *            is the new ResultSet to be set.
	 */
	public void setVerticalData(VerticalData verticalData) {
		/*
		 * The following line was written this way, because without setting a
		 * new model, the view was not updated.
		 * SwingUtilities.updateComponentTree(Component) also did not help. A
		 * better solution should be found here in future.
		 */
		setModel(verticalDataTableModel = new VerticalDataTableModel(
				verticalData));
		setFillsViewportHeight(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		updateUI();
	}

	/**
	 * This method returns the currently set result set.
	 * 
	 * @return A ResultSet is returned containing the currently set results.
	 */
	public VerticalData getVerticalData() {
		return verticalDataTableModel.getVerticalData();
	}
}
