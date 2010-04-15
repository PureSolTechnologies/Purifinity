package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class FixedFormEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -254846196228367041L;

	private static final Translator translator = Translator
			.getTranslator(FixedFormEvaluator.class);

	public static final String NAME = "Fixed Form Evaluator";
	public static final String DESCRIPTION = translator
			.i18n("Fortran can be written in fixed or free form."
					+ "This scanner checks the consistent usage of one of these formats within one file and project.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	public FixedFormEvaluator(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setDescription(NAME);
			observer.setRange(0, getFiles().size());
		}
		// TODO Auto-generated method stub

		if (observer != null) {
			observer.finish();
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		} else if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		}
		throw new UnsupportedReportingFormatException(format);
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getFileComment(File file, ReportingFormat format)
			throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getProjectComment(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		// TODO Auto-generated method stub
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public QualityLevel getQuality(File file) {
		// TODO Auto-generated method stub
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		// TODO Auto-generated method stub
		return QualityLevel.UNSPECIFIED;
	}

}
