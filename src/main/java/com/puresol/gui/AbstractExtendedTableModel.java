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

import javax.swing.table.AbstractTableModel;

abstract public class AbstractExtendedTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractExtendedTableModel() {
		super();
	}

	/**
	 * This method returns the number of columns within the result set.
	 * 
	 * @return A int is returned containing the number of columns.
	 */
	abstract public int getColumnCount();

	/**
	 * This method returns the number of rows within the result set.
	 * 
	 * @return A int is returned containing the number of rows.
	 */
	abstract public int getRowCount();

	/**
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 * 
	 * @param c
	 *            is the column number the class is to be returned.
	 * @return The class for the selected column is returned as Class object.
	 */
	abstract public Class<?> getColumnClass(int c);

	/**
	 * This method declares the column names for the table separately for all
	 * columns.
	 * 
	 * @param col
	 *            is the column number.
	 * 
	 * @return A String is returned containing the name of the column.
	 */
	abstract public String getColumnName(int col);

	/**
	 * This method returns the value for the specified cell.
	 * 
	 * @param row
	 *            is the row number.
	 * @param col
	 *            is the column number.
	 * 
	 * @return An Object class is returned containing the value to be drawn.
	 */
	abstract public Object getValueAt(int row, int col);
}
