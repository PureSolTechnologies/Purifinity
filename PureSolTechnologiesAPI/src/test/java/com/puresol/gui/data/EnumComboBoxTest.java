package com.puresol.gui.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.data.PublicityType;

public class EnumComboBoxTest {

	@Test
	public void testDefaultValue() {
		EnumComboBox enumComboBox = new EnumComboBox(PublicityType.class);
		assertEquals(0, enumComboBox.getSelectedIndex());
	}

	@Test
	public void testValues() {
		EnumComboBox enumComboBox = new EnumComboBox(PublicityType.class);
		PublicityType[] constants = PublicityType.class.getEnumConstants();
		for (int index = 0; index < constants.length; index++) {
			enumComboBox.setSelectedItem(constants[index]);
			System.out.println(constants[index]);
			System.out.println(enumComboBox.getSelectedItem());
			assertEquals(constants[index], enumComboBox.getSelectedItem());
		}
	}

}
