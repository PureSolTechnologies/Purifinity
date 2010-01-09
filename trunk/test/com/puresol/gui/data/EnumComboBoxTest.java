package com.puresol.gui.data;

import org.junit.Test;

import com.puresol.data.PublicityType;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EnumComboBoxTest extends TestCase {

	@Test
	public void testDefaultValue() {
		EnumComboBox enumComboBox = new EnumComboBox(PublicityType.class);
		Assert.assertEquals(0, enumComboBox.getSelectedIndex());
	}

	@Test
	public void testValues() {
		EnumComboBox enumComboBox = new EnumComboBox(PublicityType.class);
		PublicityType[] constants = PublicityType.class.getEnumConstants();
		for (int index = 0; index < constants.length; index++) {
			enumComboBox.setSelectedItem(constants[index]);
			System.out.println(constants[index]);
			System.out.println(enumComboBox.getSelectedItem());
			Assert.assertEquals(constants[index], enumComboBox
					.getSelectedItem());
		}
	}

}
