package com.puresol.purifinity.client.features.cleancode.grade;

import java.util.List;

import com.puresol.purifinity.client.features.cleancode.practice.Practice;
import com.puresol.purifinity.client.features.cleancode.principle.Principle;

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
