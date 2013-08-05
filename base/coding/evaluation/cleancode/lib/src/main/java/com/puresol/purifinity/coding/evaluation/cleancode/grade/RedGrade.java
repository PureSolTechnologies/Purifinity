package com.puresol.purifinity.coding.evaluation.cleancode.grade;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.evaluation.cleancode.practice.BoyScoutPractice;
import com.puresol.purifinity.coding.evaluation.cleancode.practice.DailyReflectionPractice;
import com.puresol.purifinity.coding.evaluation.cleancode.practice.Practice;
import com.puresol.purifinity.coding.evaluation.cleancode.practice.RootCauseAnalysisPractice;
import com.puresol.purifinity.coding.evaluation.cleancode.practice.SimpleRefactoringPractice;
import com.puresol.purifinity.coding.evaluation.cleancode.practice.VCSPractice;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.BewareOfOptimizationPrinciple;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.DRYPrinciple;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.FCoIPrinciple;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.KISSPrinciple;
import com.puresol.purifinity.coding.evaluation.cleancode.principle.Principle;

/**
 * 
 * @author ludwig
 * 
 */
public class RedGrade extends AbstractGrade {

	private static final List<Principle> principles = new ArrayList<Principle>();
	static {
		principles.add(new DRYPrinciple());
		principles.add(new KISSPrinciple());
		principles.add(new BewareOfOptimizationPrinciple());
		principles.add(new FCoIPrinciple());
	}

	private static final List<Practice> practices = new ArrayList<Practice>();
	static {
		practices.add(new BoyScoutPractice());
		practices.add(new RootCauseAnalysisPractice());
		practices.add(new VCSPractice());
		practices.add(new SimpleRefactoringPractice());
		practices.add(new DailyReflectionPractice());
	}

	@Override
	public List<Principle> getPrinciples() {
		return principles;
	}

	@Override
	public List<Practice> getPractices() {
		return practices;
	}
}
