package com.puresol.purifinity.coding.metrics.cocomo;

import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.COSTS;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.KSLOC;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.ArrayList;
import java.util.List;

import com.puresol.commons.utils.math.Money;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.MetricValue;

public abstract class CoCoMoResults extends AbstractEvaluatorResult {

	private static final long serialVersionUID = 9107629880981197874L;

	private int phyLOC;
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

	private final List<MetricValue> results = new ArrayList<MetricValue>();

	public CoCoMoResults() {
		setComplexity(Complexity.LOW);
		setAverageSalary(56286, "$");
		refreshParameters();
	}

	/**
	 * @return the sloc
	 */
	public int getPhyLOC() {
		return phyLOC;
	}

	/**
	 * @param sloc
	 *            the sloc to set
	 */
	public void setSloc(int sloc) {
		this.phyLOC = sloc;
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
		refreshParameters();
		recreateResultsList();
	}

	private void calculate() {
		ksloc = phyLOC / 1000.0;
		personMonth = Math.round(c1 * Math.exp(c2 * Math.log(ksloc)) * 100.0) / 100.0;
		personYears = Math.round(personMonth / 12.0 * 100.0) / 100.0;
		scheduledMonth = Math
				.round(2.5 * Math.exp(c3 * Math.log(personMonth)) * 100.0) / 100.0;
		scheduledYears = Math.round(scheduledMonth / 12 * 100.0) / 100.0;
		teamSize = Math.round(personMonth / scheduledMonth * 100.0) / 100.0;
		estimatedCosts = Math.round(personYears * averageSalary * 2.4 / 1000.0
				* 100.0) / 100.0;
	}

	private void refreshParameters() {
	}

	private void recreateResultsList() {
		results.clear();
		results.add(new MetricValue(ksloc, KSLOC));
		results.add(new MetricValue(ksloc, KSLOC));
		results.add(new MetricValue(personMonth, PERSON_MONTH));
		results.add(new MetricValue(personYears, PERSON_YEARS));
		results.add(new MetricValue(scheduledMonth, SCHEDULED_MONTH));
		results.add(new MetricValue(scheduledYears, SCHEDULED_YEARS));
		results.add(new MetricValue(teamSize, TEAM_SIZE));
		results.add(new MetricValue(estimatedCosts, COSTS));
	}

	@Override
	public String toString() {
		String text = "Total Physical Source Lines of Code (SLOC)"
				+ "                = " + phyLOC + "\n";
		text += "Calculation for a " + complexity.name()
				+ " complexity project.\n";
		text += "Development Effort Estimate, Person-Years (Person-Months) = "
				+ personYears + " (" + personMonth + ")\n";
		text += " (Basic COCOMO model, Person-Months = " + c1 + " * (KSLOC^"
				+ c2 + ")) / " + complexity.name() + " complexity\n";
		text += "Schedule Estimate, Years (Months)                         = "
				+ scheduledYears + " (" + scheduledMonth + ")\n";
		text += " (Basic COCOMO model, Months = 2.5 * (person-months^" + c3
				+ ")) / " + complexity.name() + " complexity\n";
		text += "Estimated Average Number of Developers (Effort/Schedule)  = "
				+ teamSize + "\n";
		text += "Total Estimated Cost to Develop                           = "
				+ estimatedCosts + "k" + currency + "\n";
		text += " (average salary = " + averageSalary + currency
				+ "/year, overhead = 2.40)\n";
		return text;
	}

	public Money getMoney() {
		return new Money(currency, 100, Math.round(averageSalary * 100));
	}
}
