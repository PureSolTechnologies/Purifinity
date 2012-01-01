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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This class is for simplifing the Excel file creation and Excel file reading
 * trough the POI-API (http://poi.apache.org).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExcelFile {

	static public void writeTableToExcel(File file, String sheetName,
			JTable table) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet = wb.createSheet(sheetName);
			for (int rowID = 0; rowID < table.getRowCount(); rowID++) {
				HSSFRow row = sheet.createRow(rowID);
				for (int colID = 0; colID < table.getColumnCount(); colID++) {
					HSSFCell cell = row.createCell(colID);
					Object value = table.getValueAt(rowID, colID);
					if (value.getClass().equals(Integer.class)
							|| value.getClass().equals(int.class)) {
						cell.setCellValue((Integer) value);
					} else if (value.getClass().equals(Double.class)
							|| value.getClass().equals(double.class)) {
						cell.setCellValue((Double) value);
					} else if (value.getClass().equals(Float.class)
							|| value.getClass().equals(float.class)) {
						cell.setCellValue((Float) value);
					} else if (value.getClass().equals(Date.class)) {
						cell.setCellValue((Date) value);
					} else {
						cell.setCellValue(new HSSFRichTextString(value
								.toString()));
					}
				}
			}
			OutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
