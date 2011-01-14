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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.document.Chapter;
import com.puresol.document.Document;
import com.puresol.document.Paragraph;
import com.puresol.document.Table;
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

	private static final Logger logger = Logger.getLogger(CoCoMo.class);
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
	private final Hashtable<AnalyzedFile, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<AnalyzedFile, CoCoMoValueSet>();
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
		List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(NAME);
		}
		int sloc = 0;
		int count = 0;
		for (AnalyzedFile file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			try {
				sloc += getFileSLOC(file);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				logger.error("Process with next file...");
			}
		}
		cocomoValues.setSloc(sloc);
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private int getFileSLOC(AnalyzedFile file) throws IOException {
		Analysis analysis = projectAnalyzer.getAnalysis(file);
		ParserTree parserTree = analysis.getParserTree();
		SLOCMetric metric = new SLOCMetric(analysis.getLanguage(),
				new CodeRange("", CodeRangeType.FILE, parserTree));
		metric.run();
		int sloc = metric.getResult().getProLOC();
		addCodeRangeCoCoMo(file, sloc);
		return sloc;
	}

	private void addCodeRangeCoCoMo(AnalyzedFile file, int sloc) {
		CoCoMoValueSet valueSet = new CoCoMoValueSet();
		valueSet.setSloc(sloc);
		valueSet.setComplexity(cocomoValues.getComplexity());
		valueSet.setAverageSalary(cocomoValues.getAverageSalary(),
				cocomoValues.getCurrency());
		fileCoCoMoValues.put(file, valueSet);
	}

	public void setComplexity(Complexity complexity) {
		cocomoValues.setComplexity(complexity);
		for (AnalyzedFile file : fileCoCoMoValues.keySet()) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document getReport() {
		Document document = new Document(getName());
		Chapter descriptionChapter = new Chapter(document,
				translator.i18n("Description"));
		for (String paragraph : getDescription().split("\\n")) {
			new Paragraph(descriptionChapter, paragraph);
		}
		Chapter resultsSummaryChapter = new Chapter(document,
				translator.i18n("Results Summary"));
		Table resultsTable = new Table(resultsSummaryChapter, "Results Table",
				translator.i18n("Symbol"), translator.i18n("Value"),
				translator.i18n("Unit"), translator.i18n("Description"));

		for (Result result : getResults()) {
			resultsTable.addRow(result.getName(), result.getValue(),
					result.getUnit(), result.getDescription());
		}
		return document;
	}
}
