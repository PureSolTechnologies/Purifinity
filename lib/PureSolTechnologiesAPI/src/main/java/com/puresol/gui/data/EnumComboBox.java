package com.puresol.gui.data;

import java.util.Hashtable;

import com.puresol.data.Identifiable;
import com.puresol.gui.AbstractExtendedComboBox;

public class EnumComboBox extends AbstractExtendedComboBox {

    private static final long serialVersionUID = 3207606581424068194L;

    public static EnumComboBox forEnum(Class<? extends Identifiable> clazz) {
	return new EnumComboBox(clazz);
    }

    public EnumComboBox() {
	super();
    }

    public EnumComboBox(Class<? extends Identifiable> clazz) {
	super();
	setEnum(clazz);
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
}