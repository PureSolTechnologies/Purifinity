package com.puresol.document.convert.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puresol.document.Table;
import com.puresol.gui.VerticalDataTable;
import com.puresol.gui.data.VerticalData;

public class GUITable {

	public static JPanel convert(Table table) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		VerticalData data = new VerticalData();
		for (String colName : table.getColumnNames()) {
			data.addColumn(colName, String.class);
		}
		for (List<Object> row : table.getRows()) {
			String array[] = new String[row.size()];
			for (int id = 0; id < row.size(); id++) {
				array[id] = row.get(id).toString();
			}
			data.addRow((Object[]) array);
		}
		VerticalDataTable dataTable = new VerticalDataTable(data);
		panel.add(dataTable, BorderLayout.CENTER);
		if ((table.getCaption() != null) && (!table.getCaption().isEmpty())) {
			panel.add(new JLabel(table.getCaption(), JLabel.CENTER),
					BorderLayout.SOUTH);
		}
		return panel;
	}
}
