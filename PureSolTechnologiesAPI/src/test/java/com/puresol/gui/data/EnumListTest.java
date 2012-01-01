package com.puresol.gui.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.data.PublicityType;

public class EnumListTest {

	@Test
	public void testDefaultValue() {
		EnumList enumList = new EnumList(PublicityType.class);
		assertEquals(-1, enumList.getSelectedIndex());
	}

	@Test
	public void testValues() {
		EnumList enumList = new EnumList(PublicityType.class);
		PublicityType[] constants = PublicityType.class.getEnumConstants();
		for (int index = 0; index < constants.length; index++) {
			enumList.setSelectedValue(constants[index], true);
			System.out.println(constants[index]);
			System.out.println(enumList.getSelectedValue());
			assertEquals(constants[index], enumList.getSelectedValue());
		}
	}
}
