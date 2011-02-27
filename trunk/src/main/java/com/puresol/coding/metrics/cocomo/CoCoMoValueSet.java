package com.puresol.coding.metrics.cocomo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;

public class CoCoMoValueSet implements Serializable {

	private static final long serialVersionUID = -6007918694918476936L;

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

	private final List<Result> results = new ArrayList<Result>();

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
		calculate();
		recreateResultsList();
	}

	private void calculate() {
		ksloc = (double) sloc / 1000.0;
		personMonth = Math.round(c1 * Math.exp(c2 * Math.log(ksloc)) * 100.0) / 100.0;
		personYears = Math.round(personMonth / 12.0 * 100.0) / 100.0;
		scheduledMonth = Math
				.round(2.5 * Math.exp(c3 * Math.log(personMonth)) * 100.0) / 100.0;
		scheduledYears = Math.round(scheduledMonth / 12 * 100.0) / 100.0;
		teamSize = Math.round(personMonth / scheduledMonth * 100.0) / 100.0;
		estimatedCosts = Math.round(personYears * averageSalary * 2.4 / 1000.0
				* 100.0) / 100.0;
	}

	private void recreateResultsList() {
		results.clear();
		results.add(new Result(translator
				.i18n("Total Physical Source Lines of Code"), translator
				.i18n("kSLOC = SLOC / 1000"), ksloc, translator.i18n("kSLOC")));
		results.add(new Result(
				translator.i18n("Development Effort Estimate"),
				translator
						.i18n("Basic COCOMO model, Person-Months = {0} * (KSLOC**{1}) / {2} complexity",
								c1, c2, complexity.getIdentifier()),
				personMonth, translator.i18n("Person-Months")));
		results.add(new Result(translator.i18n("Development Effort Estimate"),
				translator.i18n("Person-Years = Person-Month / 12"),
				personYears, translator.i18n("Person-Years")));
		results.add(new Result(
				translator.i18n("Schedule Estimate"),
				translator
						.i18n("Basic COCOMO model, Months = 2.5 * (person-months**{0}) / {1} complexity",
								c3, complexity.getIdentifier()),
				scheduledMonth, translator.i18n("Months")));
		results.add(new Result(translator.i18n("Schedule Estimate"), translator
				.i18n("Years = Months / 12"), scheduledYears, translator
				.i18n("Years")));
		results.add(new Result(translator
				.i18n("Estimated Average Number of Developers"), translator
				.i18n("Effort/Schedule"), teamSize, ""));
		results.add(new Result(translator
				.i18n("Total Estimated Cost to Develop"), translator.i18n(
				"average salary = {0}{1}/year, overhead = 2.40", averageSalary,
				currency), estimatedCosts, translator.i18n("k$")));
	}

	@Override
	public String toString() {
		String text = translator
				.i18n("Total Physical Source Lines of Code (SLOC)")
				+ "                = " + sloc + "\n";
		text += translator.i18n("Calculation for a {0} complexity project.",
				complexity.getIdentifier()) + "\n";
		text += translator
				.i18n("Development Effort Estimate, Person-Years (Person-Months)")
				+ " = " + personYears + " (" + personMonth + ")\n";
		text += translator
				.i18n(" (Basic COCOMO model, Person-Months = {0} * (KSLOC**{1})) / {2} complexity",
						c1, c2, complexity.getIdentifier())
				+ "\n";
		text += translator.i18n("Schedule Estimate, Years (Months)")
				+ "                         = " + scheduledYears + " ("
				+ scheduledMonth + ")\n";
		text += translator
				.i18n(" (Basic COCOMO model, Months = 2.5 * (person-months**{0})) / {1} complexity",
						c3, complexity.getIdentifier())
				+ "\n";
		text += translator
				.i18n("Estimated Average Number of Developers (Effort/Schedule)")
				+ "  = " + teamSize + "\n";
		text += translator.i18n("Total Estimated Cost to Develop")
				+ "                           = " + estimatedCosts + "k"
				+ currency + "\n";
		text += translator.i18n(
				" (average salary = {0}{1}/year, overhead = 2.40)",
				averageSalary, currency)
				+ "\n";
		return text;
	}

	public List<Result> getResults() {
		return results;
	}
}
