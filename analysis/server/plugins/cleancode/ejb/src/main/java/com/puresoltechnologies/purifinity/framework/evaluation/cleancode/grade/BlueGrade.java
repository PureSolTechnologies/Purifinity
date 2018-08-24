package com.puresoltechnologies.purifinity.framework.evaluation.cleancode.grade;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.Practice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.Principle;

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
