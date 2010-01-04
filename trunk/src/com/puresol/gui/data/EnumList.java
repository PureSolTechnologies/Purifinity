package com.puresol.gui.data;

import java.util.Hashtable;

import javax.swing.ListModel;
import javax.swingx.AbstractExtendedList;
import javax.swingx.Dialog;

import com.puresol.data.Identifiable;
import com.puresol.data.PublicityType;

public class EnumList extends AbstractExtendedList {

	private static final long serialVersionUID = 3551998275859748280L;

	public EnumList() {
		super();
	}

	public EnumList(ListModel listModel) {
		super(listModel);
	}

	public void setEnum(Class<? extends Identifiable> enumeration) {
		this.removeAll();
		if (enumeration == null) {
			return;
		}
		Identifiable[] consts = enumeration.getEnumConstants();
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		for (Identifiable constant : consts) {
			listData.put(constant.getIdentifier(), constant);
		}
		setListData(listData);
	}
	
	public static void main(String[] args) {
		Dialog dialog = new Dialog("Test", true);
		EnumList el = new EnumList();
		el.setEnum(PublicityType.class);
		dialog.add(el);
		dialog.pack();
		dialog.run();
	}
}
