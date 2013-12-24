/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.impl.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BasicCoCoMoEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = 5098378023541671490L;

	public static final String NAME = "Basic COst COnstruction MOdel";

	public static final String DESCRIPTION = "The Basic COst COnstruction MOdel is a simple way "
			+ "to estimate the construction costs of a "
			+ "software project by couting the physical lines of code.";

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<>();

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;
	private final EvaluatorStore slocStore;
	private SoftwareProject complexity = SoftwareProject.LOW;
	private int averageSalary = 56286;
	private String currency = "USD";

	public BasicCoCoMoEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(NAME, DESCRIPTION, analysisRun, path);
		store = getEvaluatorStore();
		slocStore = EvaluatorStoreFactory.getFactory().createInstance(
				SLOCEvaluator.class);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	public void setComplexity(SoftwareProject complexity) {
		this.complexity = complexity;
	}

	public void setAverageSalary(int averageSalary, String currency) {
		this.averageSalary = averageSalary;
		this.currency = currency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processFile(CodeAnalysis analysis) {
		HashId hashId = analysis.getAnalyzedFile().getHashId();
		if (slocStore.hasFileResults(hashId)) {
			SLOCFileResults slocResults = (SLOCFileResults) slocStore
					.readFileResults(hashId);
			for (SLOCResult results : slocResults.getResults()) {
				if (results.getCodeRangeType() == CodeRangeType.FILE) {
					int phyLoc = results.getSLOCMetric().getPhyLOC();
					BasicCoCoMoFileResults fileResults = new BasicCoCoMoFileResults(
							analysis.getAnalyzedFile().getSourceLocation());
					fileResults.setAverageSalary(averageSalary, currency);
					fileResults.setComplexity(complexity);
					fileResults.setSloc(phyLoc);
					store.storeFileResults(hashId, fileResults);
					break;
				}
			}
		}
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		BasicCoCoMoDirectoryResults directoryResults = createDirectoryResults(directory);
		store.storeDirectoryResults(directory.getHashId(), directoryResults);
	}

	private BasicCoCoMoDirectoryResults createDirectoryResults(
			HashIdFileTree directory) {
		int phyLoc = 0;
		for (HashIdFileTree child : directory.getChildren()) {
			HashId hashId = child.getHashId();
			if (child.isFile()) {
				if (store.hasFileResults(hashId)) {
					BasicCoCoMoResults fileResults = (BasicCoCoMoResults) store
							.readFileResults(hashId);
					phyLoc += fileResults.getPhyLOC();
				}
			} else {
				if (store.hasDirectoryResults(hashId)) {
					BasicCoCoMoDirectoryResults directoryResults = (BasicCoCoMoDirectoryResults) store
							.readDirectoryResults(hashId);
					phyLoc += directoryResults.getPhyLOC();
				}
			}
		}
		BasicCoCoMoDirectoryResults directoryResults = new BasicCoCoMoDirectoryResults(
				new SourceFileLocation(new File(""),
						directory.getPathFile(false)));
		directoryResults.setAverageSalary(averageSalary, currency);
		directoryResults.setComplexity(complexity);
		directoryResults.setSloc(phyLoc);
		return directoryResults;
	}

	@Override
	protected void processProject() throws InterruptedException {
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		BasicCoCoMoDirectoryResults directoryResults = createDirectoryResults(directory);
		store.storeDirectoryResults(directory.getHashId(), directoryResults);
	}

}
