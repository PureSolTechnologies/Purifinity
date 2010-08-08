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

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractProjectEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;
import com.puresol.utils.TextUtils;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractProjectEvaluator {

	private static final long serialVersionUID = 5098378023541671490L;

	private static final Translator translator = Translator
			.getTranslator(CoCoMo.class);

	private final CoCoMoValueSet cocomoValues = new CoCoMoValueSet();
	private final Hashtable<CodeRange, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<CodeRange, CoCoMoValueSet>();

	public static final String NAME = "COst COnstruction MOdel";
	public static final String DESCRIPTION = translator
			.i18n("The COst COnstruction MOdel is a simple way "
					+ "to estimate the construction costs of a "
					+ "software project by couting the physical lines of code.");
	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();

	public CoCoMo(ProjectAnalyzer analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProjectAnalyzer projectAnalyser = getProjectAnalyser();
		List<File> files = projectAnalyser.getFiles();
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
		List<CodeRange> codeRanges = getProjectAnalyser().getAnalyzer(file)
				.getNonFragmentCodeRangesRecursively();
		for (CodeRange codeRange : codeRanges) {
			if (codeRange.getCodeRangeType() == CodeRangeType.FILE) {
				int sloc = getSLOC(codeRange);
				addCodeRangeCoCoMo(codeRange, sloc);
				return sloc;
			}
		}
		return 0;
	}

	private int getSLOC(CodeRange codeRange) {
		int sloc = 0;
		int line = 1;
		int oldLine = -1;
		for (Token token : codeRange.getTokens()) {
			if (token.getPublicity() == TokenPublicity.VISIBLE) {
				if (line != oldLine) {
					oldLine = line;
					sloc++;
				}
			}
			line += TextUtils.countLineBreaks(token.getText());
		}
		return sloc;
	}

	private void addCodeRangeCoCoMo(CodeRange codeRange, int sloc) {
		CoCoMoValueSet valueSet = new CoCoMoValueSet();
		valueSet.setSloc(sloc);
		valueSet.setComplexity(cocomoValues.getComplexity());
		valueSet.setAverageSalary(cocomoValues.getAverageSalary(),
				cocomoValues.getCurrency());
		fileCoCoMoValues.put(codeRange, valueSet);
	}

	public void setComplexity(Complexity complexity) {
		cocomoValues.setComplexity(complexity);
		for (CodeRange codeRange : fileCoCoMoValues.keySet()) {
			fileCoCoMoValues.get(codeRange).setComplexity(complexity);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		} else if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		}
		throw new UnsupportedFormatException(format);
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.HTML) {
			return cocomoValues.toString(format);
		} else if (format == ReportingFormat.TEXT) {
			return cocomoValues.toString(format);
		}
		throw new UnsupportedFormatException(format);
	}

	@Override
	public QualityLevel getQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
