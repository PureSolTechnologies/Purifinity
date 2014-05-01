/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.parsers.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.Result;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.CodeRangeEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCMetricCalculator;

public class NormalizedMaintainabilityIndex extends CodeRangeEvaluator {

	private static final Logger logger = LoggerFactory
			.getLogger(NormalizedMaintainabilityIndex.class);

	private final AnalysisRun analysisRun;
	private final CodeRange codeRange;
	private final SLOCMetricCalculator slocMetric;
	private final McCabeMetric mcCabeMetric;
	private final HalsteadMetric halsteadMetric;
	private NormalizedMaintainabilityIndexResult result;

	public NormalizedMaintainabilityIndex(AnalysisRun analysisRun,
			ProgrammingLanguage language, CodeRange codeRange) {
		super(NormalizedMaintainabilityIndexEvaluator.NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		slocMetric = new SLOCMetricCalculator(analysisRun, language,
				getCodeRange());
		mcCabeMetric = new McCabeMetric(analysisRun, language, getCodeRange());
		halsteadMetric = new HalsteadMetric(analysisRun, language,
				getCodeRange());
	}

	@Override
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public CodeRange getCodeRange() {
		return codeRange;
	}

	private void checkInput() {
		if (getCodeRange() != slocMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (getCodeRange() != mcCabeMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (getCodeRange() != halsteadMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public Boolean call() {
		try {
			fireStarted("Start evaluation.", 4);

			checkInput();

			execute(slocMetric);
			fireUpdateWork("Finished SLOC.", 1);
			execute(mcCabeMetric);
			fireUpdateWork("Finished McCabe metric.", 1);
			execute(halsteadMetric);
			fireUpdateWork("Finished Halstead metric.", 1);

			SLOCMetric sloc = slocMetric.getSLOCResult();
			double MIwoc = 171.0 - 5.2
					* Math.log(halsteadMetric.getHalsteadVolume()) - 0.23
					* mcCabeMetric.getCyclomaticNumber() - 16.2
					* Math.log(sloc.getPhyLOC() * 100.0 / 171.0);
			double MIcw = 50 * Math.sin(Math.sqrt(2.4 * sloc.getComLOC()
					/ sloc.getPhyLOC()));
			result = new NormalizedMaintainabilityIndexResult(MIwoc, MIcw);
			fireDone("Finished evaluation.", true);
			return true;
		} catch (InterruptedException e) {
			logger.warn("Evaluation was interrupted.", e);
			fireDone("Evaluation was interrupted.", false);
			return false;
		} catch (UniversalSyntaxTreeEvaluationException e) {
			logger.warn("Evaluation failed.", e);
			fireDone("Evaluation failed.", false);
			return false;
		}
	}

	public void print() {
		System.out.println("NMIwoc = " + result.getNMIwoc());
		System.out.println("NMIcw = " + result.getNMIcw());
		System.out.println("NMI = " + result.getNMI());
	}

	public double getMIWoc() {
		return result.getNMIwoc();
	}

	public double getMIcw() {
		return result.getNMIcw();
	}

	public double getMI() {
		return result.getNMI();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public SourceCodeQuality getQuality() {
		return NormalizedMaintainabilityQuality.get(getCodeRange().getType(),
				result);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public String getDescription() {
		return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return result.getResults();
	}

	/**
	 * This method is used to start evaluations.
	 * 
	 * @param evaluator
	 * @return
	 * @throws InterruptedException
	 * @throws UniversalSyntaxTreeEvaluationException
	 */
	private <T> T execute(Callable<T> evaluator) throws InterruptedException,
			UniversalSyntaxTreeEvaluationException {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			Future<T> future = executor.submit(evaluator);
			executor.shutdown();
			return future.get(30, TimeUnit.SECONDS);
		} catch (ExecutionException e) {
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		} catch (TimeoutException e) {
			fireDone(e.getMessage(), false);
			throw new UniversalSyntaxTreeEvaluationException(e);
		}
	}

}
