package com.puresol.utils.di;

public class DITestClass3 extends DITestClass {

    @Inject
    private Double j = null;

    @Inject("TestName")
    private Double j2 = null;

    public Double getJ() {
	return j;
    }

    public Double getJ2() {
	return j2;
    }
}
