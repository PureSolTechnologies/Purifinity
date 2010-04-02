package com.puresol.coding.evaluator.metric;

import javax.i18n4j.Translator;

public class CoCoMoValueSet {

	private static final Translator translator = Translator
			.getTranslator(CoCoMoValueSet.class);

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

	public CoCoMoValueSet() {
		setComplexity(Complexity.LOW);
		setAverageSalary(56286, "$");
	}

	/**
	 * @return the sloc
	 */
	public int getSloc() {
		return sloc;
	}

	/**
	 * @param sloc
	 *            the sloc to set
	 */
	public void setSloc(int sloc) {
		this.sloc = sloc;
		refresh();
	}

	/**
	 * @return the ksloc
	 */
	public double getKsloc() {
		return ksloc;
	}

	/**
	 * @return the personMonth
	 */
	public double getPersonMonth() {
		return personMonth;
	}

	/**
	 * @return the personYears
	 */
	public double getPersonYears() {
		return personYears;
	}

	/**
	 * @return the scheduledMonth
	 */
	public double getScheduledMonth() {
		return scheduledMonth;
	}

	/**
	 * @return the scheduledYears
	 */
	public double getScheduledYears() {
		return scheduledYears;
	}

	/**
	 * @return the teamSize
	 */
	public double getTeamSize() {
		return teamSize;
	}

	/**
	 * @return the estimatedCosts
	 */
	public double getEstimatedCosts() {
		return estimatedCosts;
	}

	/**
	 * @return the c1
	 */
	public double getC1() {
		return c1;
	}

	/**
	 * @return the c2
	 */
	public double getC2() {
		return c2;
	}

	/**
	 * @return the c3
	 */
	public double getC3() {
		return c3;
	}

	/**
	 * @return the complexity
	 */
	public Complexity getComplexity() {
		return complexity;
	}

	/**
	 * @param complexity
	 *            the complexity to set
	 */
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
		refresh();
	}

	/**
	 * @return the averageSalary
	 */
	public double getAverageSalary() {
		return averageSalary;
	}

	/**
	 * @param averageSalary
	 *            the averageSalary to set
	 */
	public void setAverageSalary(double averageSalary, String currency) {
		this.averageSalary = averageSalary;
		this.currency = currency;
		refresh();
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	private void refresh() {
		ksloc = (double)sloc / 1000.0;
		personMonth = c1 * Math.exp(c2 * Math.log(ksloc));
		personYears = personMonth / 12.0;
		scheduledMonth = 2.5 * Math.exp(c3 * Math.log(personMonth));
		scheduledYears = scheduledMonth / 12;
		teamSize = personMonth / scheduledMonth;
		estimatedCosts = personYears * averageSalary * 2.4 / 1000;
	}

	public String toString() {
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
