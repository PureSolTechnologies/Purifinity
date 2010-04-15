package com.puresol.utils;

public class DITestClass {

	@Inject(Integer.class)
	private Integer i = null;

	public Integer getI() {
		return i;
	}
}
