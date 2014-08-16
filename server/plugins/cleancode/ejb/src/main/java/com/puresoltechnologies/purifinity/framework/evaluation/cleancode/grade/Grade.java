package com.puresoltechnologies.purifinity.framework.evaluation.cleancode.grade;

import java.util.List;

import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.practice.Practice;
import com.puresoltechnologies.purifinity.framework.evaluation.cleancode.principle.Principle;

/**
 * This is the interface of a single grade within the clean code development
 * system.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Grade {

	public List<Principle> getPrinciples();

	public List<Practice> getPractices();

}
