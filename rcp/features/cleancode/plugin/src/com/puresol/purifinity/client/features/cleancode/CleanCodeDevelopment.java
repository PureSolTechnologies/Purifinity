package com.puresol.purifinity.client.features.cleancode;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.client.features.cleancode.grade.BlackGrade;
import com.puresol.purifinity.client.features.cleancode.grade.BlueGrade;
import com.puresol.purifinity.client.features.cleancode.grade.Grade;
import com.puresol.purifinity.client.features.cleancode.grade.GreenGrade;
import com.puresol.purifinity.client.features.cleancode.grade.OrangeGrade;
import com.puresol.purifinity.client.features.cleancode.grade.RedGrade;
import com.puresol.purifinity.client.features.cleancode.grade.WhiteGrade;
import com.puresol.purifinity.client.features.cleancode.grade.YellowGrade;

/**
 * This is the central base class for clean code development information.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CleanCodeDevelopment {

	private static final List<Grade> grades = new ArrayList<Grade>();
	static {
		grades.add(new BlackGrade());
		grades.add(new RedGrade());
		grades.add(new OrangeGrade());
		grades.add(new YellowGrade());
		grades.add(new GreenGrade());
		grades.add(new BlueGrade());
		grades.add(new WhiteGrade());
	}

	/**
	 * This method returns a list of all clean code developer grades.
	 * 
	 * @return A {@link List} of {@link Grade}s is returned in order of the rank
	 *         of development.
	 */
	public static List<Grade> getGrades() {
		return grades;
	}

}
