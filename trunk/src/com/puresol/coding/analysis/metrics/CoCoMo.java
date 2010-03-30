/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.metrics;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.analysis.evaluator.AbstractEvaluator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractEvaluator {

	private static final Translator translator = Translator
			.getTranslator(CoCoMo.class);

	private double sloc;
	private double ksloc;
	private double personMonth;
	private double personYears;
	private double scheduledMonth;
	private double scheduledYears;
	private double teamSize;
	private double estimatedCosts;
	private double c1; // complexity constant 1
	private double c2; // complexity constant 2
	private double c3; // complexity constant 3
	private Complexity complexity;
	private double averageSalary;
	private String currency;

	public CoCoMo(ProjectAnalyser analyser) {
		super(analyser);
		setComplexity(Complexity.LOW);
		setAverageSalary(56286, "$");
		calculate();
	}

	private void calculate() {
		sloc = getSLOC();
		ksloc = sloc / 1000.0;
		personMonth = c1 * Math.exp(c2 * Math.log(ksloc));
		personYears = personMonth / 12.0;
		scheduledMonth = 2.5 * Math.exp(c3 * Math.log(personMonth));
		scheduledYears = scheduledMonth / 12;
		teamSize = personMonth / scheduledMonth;
		estimatedCosts = personYears * averageSalary * 2.4 / 1000;

	}

	private int getSLOC() {
		ProjectAnalyser projectAnalyser = getProjectAnalyser();
		ArrayList<File> files = projectAnalyser.getFiles();
		int sloc = 0;
		for (File file : files) {
			sloc += getSLOC(projectAnalyser.getAnalyser(file));
		}
		return sloc;
	}

	private int getSLOC(Analyser analyser) {
		int sloc = 0;
		ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
		for (CodeRange codeRange : codeRanges) {
			if (codeRange.getType() == CodeRangeType.FILE) {
				sloc += getSLOC(codeRange);
			}
		}
		return sloc;
	}

	private int getSLOC(CodeRange codeRange) {
		int sloc = 0;
		int oldLine = -1;
		for (Token token : codeRange.getTokens()) {
			if (token.getPublicity() == TokenPublicity.VISIBLE) {
				if (token.getStartLine() != oldLine) {
					oldLine = token.getStartLine();
					sloc++;
				}
			}
		}
		return sloc;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
		if (complexity == Complexity.LOW) {
			c1 = 2.40;
			c2 = 1.05;
			c3 = 0.38;
		} else if (complexity == Complexity.MEDIUM) {
			c1 = 3.00;
			c2 = 1.12;
			c3 = 0.35;
		} else if (complexity == Complexity.HIGH) {
			c1 = 3.60;
			c2 = 1.20;
			c3 = 0.32;
		}
		calculate();
	}

	public void setAverageSalary(double salary, String currency) {
		this.averageSalary = salary;
		this.currency = currency;
		calculate();
	}

	public double getAverageSalary() {
		return averageSalary;
	}

	public double getPersonMonth() {
		return personMonth;
	}

	public double getPersonYears() {
		return personYears;
	}

	public double getScheduledMonth() {
		return scheduledMonth;
	}

	public double getScheduledYears() {
		return scheduledYears;
	}

	public double getTeamSize() {
		return teamSize;
	}

	public double getEstimatedCosts() {
		return estimatedCosts;
	}

	@Override
	public String getName() {
		return "COst COnstruction MOdel";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectEvaluationComment() {
		String text = translator
				.i18n(
						"Total Physical Source Lines of Code (SLOC)                = {0}",
						sloc)
				+ "\n";
		text += translator.i18n("Calculation for a {0} complexity project.",
				complexity.getIdentifier())
				+ "\n";
		text += translator
				.i18n(
						"Development Effort Estimate, Person-Years (Person-Months) = {0} ({1})",
						personYears, personMonth)
				+ "\n";
		text += translator
				.i18n(" (Basic COCOMO model, Person-Months = 2.4 * (KSLOC**1.05))")
				+ "\n";
		text += translator
				.i18n(
						"Schedule Estimate, Years (Months)                         = {0} ({1})",
						scheduledYears, scheduledMonth)
				+ "\n";
		text += translator
				.i18n(" (Basic COCOMO model, Months = 2.5 * (person-months**0.38))")
				+ "\n";
		text += translator
				.i18n(
						"Estimated Average Number of Developers (Effort/Schedule)  = {0}",
						teamSize)
				+ "\n";
		text += translator
				.i18n(
						"Total Estimated Cost to Develop                           = {0}k{1}",
						estimatedCosts, currency)
				+ "\n";
		text += translator.i18n(
				" (average salary = {0}{1}/year, overhead = 2.40)",
				averageSalary, currency)
				+ "\n";
		return text;
	}

	@Override
	public String getFileEvaluationComment(File file) {
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityLevel getQuality(File file) {
		return QualityLevel.HIGH;
	}
}
