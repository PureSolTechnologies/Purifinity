package com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate;

import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.COSTS;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.KSLOC;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_MONTH;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.PERSON_YEARS;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_MONTH;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.SCHEDULED_YEARS;
import static com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluatorParameter.TEAM_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.puresoltechnologies.commons.math.money.Money;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.api.MetricValue;

public abstract class IntermediateCoCoMoResults extends AbstractEvaluatorResult {

	private static final long serialVersionUID = 9107629880981197874L;

	private int phyLOC;
	private double ksloc;
	private double personMonth;
	private double personYears;
	private double scheduledMonth;
	private double scheduledYears;
	private double teamSize;
	private double estimatedCosts;
	private SoftwareProject project;
	private double averageSalary;
	private String currency;

	private final List<MetricValue> results = new ArrayList<MetricValue>();

	private final Map<IntermediateCOCOMOAttribute, Rating> attributes = new HashMap<>();

	public IntermediateCoCoMoResults() {
		setProject(SoftwareProject.SEMI_DETACHED);
		setAverageSalary(56286, "$");
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
	 * @return the complexity
	 */
	public SoftwareProject getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the complexity to set
	 */
	public void setProject(SoftwareProject project) {
		this.project = project;
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
		ksloc = phyLOC / 1000.0;
		personMonth = Math.round(project.getAi()
				* Math.exp(project.getBi() * Math.log(ksloc)) * getEAF()
				* 100.0) / 100.0;
		personYears = Math.round(personMonth / 12.0 * 100.0) / 100.0;
		scheduledMonth = Math.round(project.getCi()
				* Math.exp(project.getDi() * Math.log(personMonth)) * 100.0) / 100.0;
		scheduledYears = Math.round(scheduledMonth / 12 * 100.0) / 100.0;
		teamSize = Math.round(personMonth / scheduledMonth * 100.0) / 100.0;
		estimatedCosts = Math.round(personYears * averageSalary * 2.4 / 1000.0
				* 100.0) / 100.0;
	}

	private void recreateResultsList() {
		results.clear();
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
		text += "Calculation for a " + project.name()
				+ " complexity project.\n";
		text += "Development Effort Estimate, Person-Years (Person-Months) = "
				+ personYears + " (" + personMonth + ")\n";
		text += " (Intermediate COCOMO model, Person-Months = "
				+ project.getAi() + " * (KSLOC^" + project.getBi() + ") * "
				+ getEAF() + ") / " + project.name() + " complexity\n";
		text += "Schedule Estimate, Years (Months)                         = "
				+ scheduledYears + " (" + scheduledMonth + ")\n";
		text += " (Intermediate COCOMO model, Months = " + project.getCi()
				+ " * (person-months^" + project.getDi() + ")) / "
				+ project.name() + " complexity\n";
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

	/**
	 * This method calculates the EAF factor. It reads out the
	 * {@link #attributes} field and multiplies each values. If an attribute is
	 * not set, it is treated as {@link Rating#NOMINAL} (factor 1.0).
	 * 
	 * @return
	 */
	public double getEAF() {
		double eaf = 1.0;
		for (Entry<IntermediateCOCOMOAttribute, Rating> attribute : attributes
				.entrySet()) {
			eaf *= attribute.getKey().getFactor(attribute.getValue());
		}
		return eaf;
	}

	public void setAttribute(IntermediateCOCOMOAttribute attribute,
			Rating rating) {
		attributes.put(attribute, rating);
		refresh();
	}
}
