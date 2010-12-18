package com.puresol.ua;

public class TestJAAS extends JAAS {

	public TestJAAS() {
		super(TestJAAS.class.getResource("/config/JAASTest.conf").toString());
	}

}
