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

import javax.i18n4j.Translator;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo {

	private static final Translator translator = Translator
			.getTranslator(CoCoMo.class);

	private int sloc;
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

	public CoCoMo(int sloc) {
		this.sloc = sloc;
		setComplexity(Complexity.LOW);
		setAverageSalary(56286, "$");
		calculate();
	}

	private void calculate() {
		ksloc = sloc / 1000.0;
		personMonth = c1 * Math.exp(c2 * Math.log(ksloc));
		personYears = personMonth / 12.0;
		scheduledMonth = 2.5 * Math.exp(c3 * Math.log(personMonth));
		scheduledYears = scheduledMonth / 12;
		teamSize = personMonth / scheduledMonth;
		estimatedCosts = personYears * averageSalary * 2.4 / 1000;

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

	public String toReport() {
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
}
