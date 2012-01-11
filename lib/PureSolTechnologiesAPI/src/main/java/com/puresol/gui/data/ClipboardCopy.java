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

package com.puresol.gui.data;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JTable;

public class ClipboardCopy {

	static public void copy(ClipboardOwner sender, JTable table) {
		Clipboard systemClipboard = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		StringBuffer content = new StringBuffer();
		for (int row = 0; row < table.getRowCount(); row++) {
			for (int col = 0; col < table.getColumnCount(); col++) {
				Object value = table.getValueAt(row, col);
				if (String.class.isAssignableFrom(value.getClass())) {
					String str = (String) table.getValueAt(row, col);
					str = str.replaceAll("\"", "''");
					content.append("\"");
					content.append(str);
					content.append("\"\t");
				} else if (Date.class.isAssignableFrom(value.getClass())) {
					DateFormat form = DateFormat.getDateTimeInstance();
					content.append("\"");
					content.append(form.format(value));
					content.append("\"\t");
				} else {
					content.append(value);
					content.append("\t");
				}
			}
			content.append("\n");
		}
		systemClipboard.setContents(new StringSelection(content.toString()),
				sender);
	}
}
