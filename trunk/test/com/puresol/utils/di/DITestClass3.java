package com.puresol.utils.di;

public class DITestClass3 extends DITestClass2 {

	@Inject("TestName")
	private Double j2 = null;

	public Double getJ2() {
		return j2;
	}
}
