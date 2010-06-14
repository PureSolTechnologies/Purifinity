package com.puresol.coding.metrics.codedepth;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.utils.Property;

public class CodeDepth extends AbstractEvaluator {

	private static final long serialVersionUID = -2151200082569811564L;

	private static final Logger logger = Logger.getLogger(CodeDepth.class);
	private static final Translator translator = Translator
			.getTranslator(CodeDepth.class);

	public static final String NAME = translator.i18n("Code Depth Metric");
	public static final String DESCRIPTION = translator
			.i18n("Analysis the stacked code blocks for a maximum depth.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(CodeDepth.class, "enabled",
				"Switches calculation of CodeDepth on and off.", Boolean.class,
				"true"));
	}

	private int maxLayer = 0;

	public CodeDepth(ProjectAnalyser range) {
		super(range);
	}

	@Override
	public void run() {
		ProjectAnalyser projectAnalyser = getProjectAnalyser();
		List<File> files = projectAnalyser.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(NAME);
		}
		int count = 0;
		for (File file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			analyseFile(file);
		}
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private void analyseFile(File file) {
		Analyser analyser = getProjectAnalyser().getAnalyser(file);
		analyser.getNamedCodeRanges();
	}
	
	private void calculate() {
		try {
			CodeRange range = getCodeRange();
			TokenStream stream = range.getTokenStream();
			int layer = 0;
			for (int index = range.getStartId(); index <= range.getStopId(); index++) {
				Token token = stream.get(index);
				SourceTokenDefinition def;
				def = (SourceTokenDefinition) token.getDefinitionInstance();
				if (def.changeBlockLayer() != 0) {
					layer += def.changeBlockLayer();
				}
				if (layer > maxLayer) {
					maxLayer = layer;
				}
			}
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	public int getMaxLayer() {
		return maxLayer;
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQualityLevel() {
		int maxLayer = getMaxLayer();
		if (maxLayer > 6) {
			return QualityLevel.LOW;
		} else if (maxLayer > 4) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectComment(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityLevel getProjectQuality() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		// TODO Auto-generated method stub
		return null;
	}
}
