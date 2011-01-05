/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.cocomo;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.Property;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractEvaluator implements ProjectEvaluator {

	private static final long serialVersionUID = 5098378023541671490L;

	private static final Translator translator = Translator
			.getTranslator(CoCoMo.class);

	public static final String NAME = "COst COnstruction MOdel";

	public static final String DESCRIPTION = translator
			.i18n("The COst COnstruction MOdel is a simple way "
					+ "to estimate the construction costs of a "
					+ "software project by couting the physical lines of code.");

	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();

	private final CoCoMoValueSet cocomoValues = new CoCoMoValueSet();
	private final Hashtable<File, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<File, CoCoMoValueSet>();
	private final ProjectAnalyzer projectAnalyzer;

	public CoCoMo(ProjectAnalyzer projectAnalyzer) {
		super();
		this.projectAnalyzer = projectAnalyzer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProjectAnalyzer getProjectAnalyzer() {
		return projectAnalyzer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		List<File> files = projectAnalyzer.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(NAME);
		}
		int sloc = 0;
		int count = 0;
		for (File file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			int fileSLOC = getFileSLOC(file);
			sloc += fileSLOC;
		}
		cocomoValues.setSloc(sloc);
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private int getFileSLOC(File file) {
		ParserTree parserTree = projectAnalyzer.getAnalyzer(file)
				.getParserTree();
		int sloc = getSLOC(parserTree);
		addCodeRangeCoCoMo(file, sloc);
		return sloc;
	}

	private int getSLOC(ParserTree parserTree) {
		return parserTree.getMetaData().getLineNum();
	}

	private void addCodeRangeCoCoMo(File file, int sloc) {
		CoCoMoValueSet valueSet = new CoCoMoValueSet();
		valueSet.setSloc(sloc);
		valueSet.setComplexity(cocomoValues.getComplexity());
		valueSet.setAverageSalary(cocomoValues.getAverageSalary(),
				cocomoValues.getCurrency());
		fileCoCoMoValues.put(file, valueSet);
	}

	public void setComplexity(Complexity complexity) {
		cocomoValues.setComplexity(complexity);
		for (File file : fileCoCoMoValues.keySet()) {
			fileCoCoMoValues.get(file).setComplexity(complexity);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		return SourceCodeQuality.UNSPECIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return cocomoValues.getResults();
	}

}
