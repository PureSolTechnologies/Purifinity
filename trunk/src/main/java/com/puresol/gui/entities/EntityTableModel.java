/***************************************************************************
 *
 *   EntityTableModel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.entities;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.puresol.entities.forms.TemplateElement;
import com.puresol.entities.forms.TemplateInformation;
import com.puresol.gui.AbstractExtendedTableModel;

/**
 * This table model is designed to show ResultSet objects in a Table. The view
 * is standardized for all SQL types.
 * 
 * @author Rick-Rainer Ludwig
 */

public class EntityTableModel extends AbstractExtendedTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This variable contains the result set to show in the table.
	 */
	private List<?> entities = null;
	private Vector<TemplateInformation> templates = null;

	/**
	 * This is the standard constructor.
	 */
	public EntityTableModel() {
		super();
	}

	/**
	 * This is a standard constructor with an additional possibility to pass
	 * initial data to the object.
	 * 
	 * @param entities
	 *            is a ResultSet containing all data to show in the initial
	 *            view.
	 */
	public EntityTableModel(List<?> entities) {
		this();
		setEntities(entities);
	}

	/**
	 * This method is used to set new data to be shown.
	 * 
	 * @param entities
	 *            is a VerticalData class containing all data to show in the
	 *            initial view.
	 */
	public void setEntities(List<?> entities) {
		this.entities = entities;
		this.templates = new Vector<TemplateInformation>();
		if (entities != null) {
			for (Object entity : entities) {
				templates.add(TemplateInformation.from(entity));
			}
		}
	}

	/**
	 * This method returns the currently set result set.
	 * 
	 * @return A ResultSet reference is returned.
	 */
	public List<?> getEntities() {
		return entities;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() {
		if (templates == null) {
			return 0;
		}
		if (templates.size() == 0) {
			return 0;
		}
		return templates.get(0).getInputCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRowCount() {
		if (templates == null) {
			return 0;
		}
		return templates.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getColumnName(int col) {
		if (templates == null) {
			return "";
		}
		if (templates.size() == 0) {
			return "";
		}
		return templates.get(0).get(col).getName();
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int col) {
		if (templates == null) {
			return String.class;
		}
		if (templates.size() == 0) {
			return String.class;
		}
		TemplateElement element = templates.get(0).get(col);
		if (Collection.class.isAssignableFrom(element.getType())) {
			return String.class;
		}
		return element.getType();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int row, int col) {
		if (templates == null) {
			return "";
		}
		if (templates.size() == 0) {
			return "";
		}
		TemplateElement element = templates.get(row).get(col);
		if (Collection.class.isAssignableFrom(element.getType())) {
			return element.getValue().toString();
		}
		return element.getValue();
	}
}
