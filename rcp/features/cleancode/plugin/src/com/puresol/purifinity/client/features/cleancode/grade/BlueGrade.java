package com.puresol.purifinity.client.features.cleancode.grade;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.client.features.cleancode.practice.Practice;
import com.puresol.purifinity.client.features.cleancode.principle.Principle;

public class BlueGrade extends AbstractGrade {

	private static final List<Principle> principles = new ArrayList<Principle>();
	private static final List<Practice> practices = new ArrayList<Practice>();

	@Override
	public List<Principle> getPrinciples() {
		return principles;
	}

	@Override
	public List<Practice> getPractices() {
		return practices;
	}
}
