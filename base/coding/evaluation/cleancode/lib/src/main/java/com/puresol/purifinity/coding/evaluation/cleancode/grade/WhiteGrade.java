package com.puresol.purifinity.coding.evaluation.cleancode.grade;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.evaluation.cleancode.practice.Practice;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.Principle;

public class WhiteGrade extends AbstractGrade {

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
