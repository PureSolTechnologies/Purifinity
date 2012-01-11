package com.puresol.coding.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;
import com.puresol.math.statistics.Statistics;

public class SLOCResult implements Serializable {

	private static final long serialVersionUID = 1143172056453964480L;

	private static final Translator translator = Translator
			.getTranslator(SLOCResult.class);

	private final List<Result> results = new ArrayList<Result>();
	private final int phyLOC;
	private final int proLOC;
	private final int comLOC;
	private final int blLOC;
	private final Statistics lineStatistics;

	public SLOCResult(int phyLOC, int proLOC, int comLOC, int blLOC,
			Statistics lineStatistics) {
		super();
		this.phyLOC = phyLOC;
		this.proLOC = proLOC;
		this.comLOC = comLOC;
		this.blLOC = blLOC;
		this.lineStatistics = lineStatistics;
		createResultsList();
	}

	private void createResultsList() {
		results.add(new Result("phyLOC", translator
				.i18n("Physical lines of code"), phyLOC, ""));
		results.add(new Result("proLOC", translator
				.i18n("Productive lines of code"), proLOC, ""));
		results.add(new Result("comLOC", translator
				.i18n("Comment lines of code"), comLOC, ""));
		results.add(new Result("blLOC", translator.i18n("Blank lines of code"),
				blLOC, ""));
		results.add(new Result("AvgLL", translator.i18n("Average line length"),
				lineStatistics.getAvg(), ""));
		results.add(new Result("MedLL", translator.i18n("Median line length"),
				lineStatistics.getMedian(), ""));
		results.add(new Result("StdDevLL", translator
				.i18n("Standard deviation line length"), lineStatistics
				.getStdDev(), ""));
		results.add(new Result("MaxLL", translator.i18n("Maximum line length"),
				lineStatistics.getMax(), ""));
		results.add(new Result("MinLL", translator.i18n("Minimum line length"),
				lineStatistics.getMin(), ""));
	}

	public int getPhyLOC() {
		return phyLOC;
	}

	public int getProLOC() {
		return proLOC;
	}

	public int getComLOC() {
		return comLOC;
	}

	public int getBlLOC() {
		return blLOC;
	}

	public Statistics getLineStatistics() {
		return lineStatistics;
	}

	public List<Result> getResults() {
		return results;
	}

}
