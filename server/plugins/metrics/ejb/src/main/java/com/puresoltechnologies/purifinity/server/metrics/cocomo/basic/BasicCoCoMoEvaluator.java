/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
@Remote(Evaluator.class)
public class BasicCoCoMoEvaluator extends AbstractMetricEvaluator {

	public static final String ID = BasicCoCoMoEvaluator.class.getName();

	public static final String NAME = "Basic COst COnstruction MOdel";

	public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

	public static final String DESCRIPTION = "The Basic COst COnstruction MOdel is a simple way "
			+ "to estimate the construction costs of a "
			+ "software project by couting the physical lines of code.";

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<>();

	public static final Set<String> DEPENDENCIES = new HashSet<>();
	static {
		DEPENDENCIES.add(SLOCMetricCalculator.ID);
	}

	private static final Set<ConfigurationParameter<?>> CONFIGURATION_PARAMETERS = new HashSet<>();

	private SoftwareProject complexity = SoftwareProject.LOW;
	private int averageSalary = 56286;
	private String currency = "USD";

	public BasicCoCoMoEvaluator() {
		super(ID, NAME, DESCRIPTION);

	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return CONFIGURATION_PARAMETERS;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return BasicCoCoMoEvaluatorParameter.ALL;
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
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws EvaluationStoreException {
		HashId hashId = analysis.getAnalysisInformation().getHashId();
		EvaluatorStore evaluatorStore = getEvaluatorStore();
		if (evaluatorStore.hasFileResults(hashId, SLOCMetricCalculator.ID)) {
			GenericFileMetrics slocResults = evaluatorStore.readFileResults(
					hashId, SLOCMetricCalculator.ID);
			SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
					hashId).getSourceCodeLocation();
			for (GenericCodeRangeMetrics results : slocResults.getValues()) {
				if (results.getCodeRangeType() == CodeRangeType.FILE) {
					int phyLoc = results.getValue(
							SLOCEvaluatorParameter.PHY_LOC).getValue();
					BasicCoCoMoFileResults fileResults = new BasicCoCoMoFileResults(
							BasicCoCoMoEvaluator.ID, hashId,
							sourceCodeLocation, new Date());
					fileResults.setAverageSalary(averageSalary, currency);
					fileResults.setComplexity(complexity);
					fileResults.setSloc(phyLoc);
					return fileResults;
				}
			}
		}
		return null;
	}

	@Override
	protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		int phyLoc = 0;
		EvaluatorStore evaluatorStore = getEvaluatorStore();
		for (AnalysisFileTree child : directory.getChildren()) {
			HashId hashId = child.getHashId();
			if (child.isFile()) {
				if (evaluatorStore.hasFileResults(hashId, getInformation()
						.getId())) {
					GenericFileMetrics fileResults = evaluatorStore
							.readFileResults(hashId, SLOCMetricCalculator.ID);
					for (GenericCodeRangeMetrics metrics : fileResults
							.getValues()) {
						if (metrics.getCodeRangeType().equals(
								CodeRangeType.FILE)) {
							phyLoc += metrics.getValue(
									SLOCEvaluatorParameter.PHY_LOC).getValue();
							break;
						}
					}
				}
			} else {
				if (evaluatorStore.hasDirectoryResults(hashId, getInformation()
						.getId())) {
					GenericDirectoryMetrics directoryResults = evaluatorStore
							.readDirectoryResults(hashId,
									BasicCoCoMoEvaluator.ID);
					phyLoc += (Integer) directoryResults.getValues()
							.get(SLOCEvaluatorParameter.PHY_LOC.getName())
							.getValue();
				}
			}
		}
		BasicCoCoMoDirectoryResults directoryResults = new BasicCoCoMoDirectoryResults(
				BasicCoCoMoEvaluator.ID, directory.getHashId(), new Date());
		directoryResults.setAverageSalary(averageSalary, currency);
		directoryResults.setComplexity(complexity);
		directoryResults.setSloc(phyLoc);
		return directoryResults;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		AnalysisFileTree directory = analysisRun.getFileTree();
		return processDirectory(analysisRun, directory);
	}
}
