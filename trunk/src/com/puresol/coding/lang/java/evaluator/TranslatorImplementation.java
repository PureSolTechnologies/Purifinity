package com.puresol.coding.lang.java.evaluator;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class TranslatorImplementation extends AbstractEvaluator {

	private static final Translator translator = Translator
			.getTranslator(TranslatorImplementation.class);

	public static final String NAME = "Translator Implementation for Java";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator checks for a Translator implementation"
					+ "recommended by PureSol-Technologies.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	public TranslatorImplementation(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format) {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		}
		return DESCRIPTION;
	}

	@Override
	public QualityLevel getQuality(File file) {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public String getFileComment(File file, ReportingFormat format) {
		return "";
	}

	@Override
	public String getProjectComment(ReportingFormat format) {
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) {
		return "";
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}

}
