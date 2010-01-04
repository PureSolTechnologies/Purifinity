package com.puresol.gui.data;

import java.util.Hashtable;

import javax.swingx.AbstractExtendedComboBox;
import javax.swingx.Dialog;

import com.puresol.data.Identifiable;
import com.puresol.data.PublicityType;

public class EnumComboBox extends AbstractExtendedComboBox {

	private static final long serialVersionUID = 3207606581424068194L;

	public EnumComboBox() {
		super();
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
		setItems(listData);
	}

	public static void main(String[] args) {
		Dialog dialog = new Dialog("Test", true);
		EnumComboBox ec = new EnumComboBox();
		ec.setEnum(PublicityType.class);
		dialog.add(ec);
		dialog.pack();
		dialog.run();
	}

}
