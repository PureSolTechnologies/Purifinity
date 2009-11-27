package com.puresol.gui.entities;

import java.util.List;

import javax.swingx.Table;

import com.puresol.appserv.QSysBean;

/**
 * This table is for showing result set in an easy and efficient way. The
 * complete result set is shown. For the representation a ResultSetTableModel
 * was generated to present a standard representation for all SQL types.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EntityTable extends Table {

	private static final long serialVersionUID = 1L;

	/**
	 * This variables keeps the reference to the table model.
	 */
	private EntityTableModel entityTableModel = null;

	/**
	 * This is the standard constructor.
	 */
	public EntityTable() {
		super();
	}

	/**
	 * This is a standard constructor with an additional possibility to set a
	 * initial result set.
	 * 
	 * @param resultSet
	 *            is result set to be shown in the initial view of this table.
	 */
	public EntityTable(QSysBean bean, List<?> entities) {
		this();
		setEntities(entities);
	}

	/**
	 * This method sets a new result set and updates the view of the table.
	 * 
	 * @param resultSet
	 *            is the new ResultSet to be set.
	 */
	public void setEntities(List<?> entities) {
		/*
		 * The following line was written this way, because without setting a
		 * new model, the view was not updated.
		 * SwingUtilities.updateComponentTree(Component) also did not help. A
		 * better solution should be found here in future.
		 */
		setModel(entityTableModel = new EntityTableModel(entities));
		setFillsViewportHeight(true);
		setAutoResizeMode(Table.AUTO_RESIZE_ALL_COLUMNS);
		updateUI();
	}

	/**
	 * This method returns the currently set result set.
	 * 
	 * @return A ResultSet is returned containing the currently set results.
	 */
	public List<?> getEntities() {
		return entityTableModel.getEntities();
	}
}
