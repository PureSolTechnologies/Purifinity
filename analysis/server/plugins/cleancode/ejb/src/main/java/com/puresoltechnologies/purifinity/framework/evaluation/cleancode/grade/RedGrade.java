package com.puresoltechnologies.purifinity.framework.evaluation.cleancode.grade;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.BoyScoutPractice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.DailyReflectionPractice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.Practice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.RootCauseAnalysisPractice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.SimpleRefactoringPractice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.VCSPractice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.BewareOfOptimizationPrinciple;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.DRYPrinciple;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.FCoIPrinciple;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.KISSPrinciple;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.Principle;

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
