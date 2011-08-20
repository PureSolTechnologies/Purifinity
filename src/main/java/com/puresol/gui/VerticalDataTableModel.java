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

import com.puresol.gui.data.VerticalData;

/**
 * This table model is designed to show ResultSet objects in a Table. The view
 * is standardized for all SQL types.
 * 
 * @author Rick-Rainer Ludwig
 */

public class VerticalDataTableModel extends AbstractExtendedTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This variable contains the result set to show in the table.
	 */
	private transient VerticalData verticalData;

	/**
	 * This is the standard constructor.
	 */
	public VerticalDataTableModel() {
		super();
		verticalData = null;
	}

	/**
	 * This is a standard constructor with an additional possibility to pass
	 * initial data to the object.
	 * 
	 * @param verticalData
	 *            is a ResultSet containing all data to show in the initial
	 *            view.
	 */
	public VerticalDataTableModel(VerticalData verticalData) {
		this();
		setVerticalData(verticalData);
	}

	/**
	 * This method is used to set new data to be shown.
	 * 
	 * @param verticalData
	 *            is a VerticalData class containing all data to show in the
	 *            initial view.
	 */
	public void setVerticalData(VerticalData verticalData) {
		this.verticalData = verticalData;
	}

	/**
	 * This method returns the currently set result set.
	 * 
	 * @return A ResultSet reference is returned.
	 */
	public VerticalData getVerticalData() {
		return verticalData;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() {
		if (verticalData == null) {
			return 0;
		}
		return verticalData.getColumnNumber();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRowCount() {
		if (verticalData == null) {
			return 0;
		}
		return verticalData.getRowNumber();
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int c) {
		if (verticalData == null) {
			return String.class;
		}
		return verticalData.getType(c).getClassObject();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getColumnName(int col) {
		if (verticalData == null) {
			return "";
		}
		return verticalData.getName(col);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int row, int col) {
		if (verticalData == null) {
			return null;
		}
		return verticalData.getElement(row, col);
	}
}
