/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.EvaluatorManager;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractEvaluator {

	private static final Logger logger = Logger.getLogger(CoCoMo.class);
	private static final Translator translator = Translator
			.getTranslator(CoCoMo.class);

	static {
		logger.info("Initial static initialization...");
		EvaluatorManager.getInstance().setName(CoCoMo.class, "CoCoMo");
	}

	private final CoCoMoValueSet cocomoValues = new CoCoMoValueSet();
	private final Hashtable<File, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<File, CoCoMoValueSet>();

	public CoCoMo(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProjectAnalyser projectAnalyser = getProjectAnalyser();
		ArrayList<File> files = projectAnalyser.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(
					"CoCoMo - Cost Construction Model calculation");
		}
		int sloc = 0;
		int count = 0;
		for (File file : files) {
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			int fileSLOC = getSLOC(projectAnalyser.getAnalyser(file));
			sloc += fileSLOC;
			addFile(file);
		}
		cocomoValues.setSloc(sloc);
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private int getSLOC(Analyser analyser) {
		int sloc = getFileSLOC(analyser);
		addFileCoCoMo(analyser, sloc);
		return sloc;
	}

	private int getFileSLOC(Analyser analyser) {
		int sloc = 0;
		ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
		for (CodeRange codeRange : codeRanges) {
			if (codeRange.getType() == CodeRangeType.FILE) {
				sloc += getSLOC(codeRange);
			}
		}
		return sloc;
	}

	private int getSLOC(CodeRange codeRange) {
		int sloc = 0;
		int oldLine = -1;
		for (Token token : codeRange.getTokens()) {
			if (token.getPublicity() == TokenPublicity.VISIBLE) {
				if (token.getStartLine() != oldLine) {
					oldLine = token.getStartLine();
					sloc++;
				}
			}
		}
		return sloc;
	}

	private void addFileCoCoMo(Analyser analyser, int sloc) {
		CoCoMoValueSet valueSet = new CoCoMoValueSet();
		valueSet.setSloc(sloc);
		valueSet.setComplexity(cocomoValues.getComplexity());
		valueSet.setAverageSalary(cocomoValues.getAverageSalary(), cocomoValues
				.getCurrency());
		fileCoCoMoValues.put(analyser.getFile(), valueSet);

	}

	public void setComplexity(Complexity complexity) {
		cocomoValues.setComplexity(complexity);
		for (File file : fileCoCoMoValues.keySet()) {
			fileCoCoMoValues.get(file).setComplexity(complexity);
		}
	}

	@Override
	public String getName() {
		return "COst COnstruction MOdel";
	}

	@Override
	public String getDescription() {
		return translator.i18n("The COst COnstruction MOdel is a simple way "
				+ "to estimate the construction costs of a "
				+ "software project by couting the physical lines of code.");
	}

	@Override
	public String getProjectComment() {
		return translator.i18n("Project Result:\n\n") + cocomoValues.toString();
	}

	@Override
	public String getFileComment(File file) {
		return fileCoCoMoValues.get(file).toString();
	}

	@Override
	public QualityLevel getProjectQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public QualityLevel getQuality(File file) {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange) {
		return "";
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}
}
