package com.puresol.coding.metrics.maintainability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;

public class MaintainabilityIndexResult implements Serializable {

	private static final long serialVersionUID = 1924196234881066633L;

	private static final Translator translator = Translator
			.getTranslator(MaintainabilityIndexResult.class);

	private final List<Result> results = new ArrayList<Result>();

	/**
	 * MaintainabilityIndex without comment.
	 */
	private final double miwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private final double micw;
	/**
	 * MaintainabilityIndex
	 */
	private final double mi;

	public MaintainabilityIndexResult(double mIwoc, double mIcw) {
		super();
		miwoc = mIwoc;
		micw = mIcw;
		mi = miwoc + micw;
		createResultsList();
	}

	private void createResultsList() {
		results.add(new Result("MIwoc", translator
				.i18n("Maintainability index without comments"), miwoc, ""));
		results.add(new Result("MIcw", translator
				.i18n("Maintainability index comment weight"), micw, ""));
		results.add(new Result("MI", translator
				.i18n("Maintainability index without comments"), mi, ""));
	}

	public double getMIwoc() {
		return miwoc;
	}

	public double getMIcw() {
		return micw;
	}

	public double getMI() {
		return mi;
	}

	public List<Result> getResults() {
		return results;
	}

}
