package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;

public class NormalizedMaintainabilityIndexResult {

	private static final Translator translator = Translator
			.getTranslator(NormalizedMaintainabilityIndexResult.class);

	private final List<Result> results = new ArrayList<Result>();

	/**
	 * MaintainabilityIndex without comment.
	 */
	private final double nMIwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private final double nMIcw;
	/**
	 * MaintainabilityIndex
	 */
	private final double nMI;

	public NormalizedMaintainabilityIndexResult(double nMIwoc, double nMIcw) {
		super();
		this.nMIwoc = Math.max(0, nMIwoc / 171.0);
		this.nMIcw = Math.max(0, nMIcw / 50.0);
		this.nMI = Math.max(0, (nMIwoc + nMIcw) / 221.0);
		createResultsList();
	}

	private void createResultsList() {
		results.add(new Result("nMIwoc", translator
				.i18n("Maintainability index without comments"), nMIwoc, ""));
		results.add(new Result("nMIcw", translator
				.i18n("Maintainability index comment weight"), nMIcw, ""));
		results.add(new Result("nMI", translator
				.i18n("Maintainability index without comments"), nMI, ""));
	}

	public double getNMIwoc() {
		return nMIwoc;
	}

	public double getNMIcw() {
		return nMIcw;
	}

	public double getNMI() {
		return nMI;
	}

	public List<Result> getResults() {
		return results;
	}

}
