package com.puresol.gui.data;

import org.junit.Test;

import com.puresol.data.PublicityType;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EnumListTest extends TestCase {

	@Test
	public void testDefaultValue() {
		EnumList enumList = new EnumList(PublicityType.class);
		Assert.assertEquals(-1, enumList.getSelectedIndex());
	}

	@Test
	public void testValues() {
		EnumList enumList = new EnumList(PublicityType.class);
		PublicityType[] constants = PublicityType.class.getEnumConstants();
		for (int index = 0; index < constants.length; index++) {
			enumList.setSelectedValue(constants[index], true);
			System.out.println(constants[index]);
			System.out.println(enumList.getSelectedValue());
			Assert.assertEquals(constants[index], enumList.getSelectedValue());
		}
	}
}
