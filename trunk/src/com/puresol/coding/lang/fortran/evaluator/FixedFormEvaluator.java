package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.lang.fortran.Fortran;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.coding.tokentypes.Comment;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
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
	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private final Hashtable<File, List<FormViolation>> violations = new Hashtable<File, List<FormViolation>>();

	public FixedFormEvaluator(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setDescription(NAME);
			observer.setRange(0, getFiles().size());
			observer.setStatus(0);
		}
		int count = 0;
		for (File file : getProjectAnalyser().getFiles()) {
			if (Thread.interrupted()) {
				return;
			}
			count++;
			if (observer != null) {
				observer.setStatus(count);
			}
			for (CodeRange codeRange : getProjectAnalyser().getCodeRanges(file)) {
				if (Thread.interrupted()) {
					return;
				}
				if (codeRange.getLanguage() != Fortran.getInstance()) {
					continue;
				}
				if (codeRange.getType() != CodeRangeType.FILE) {
					continue;
				}
				if (!getFiles().contains(file)) {
					addFile(file);
				}
				addCodeRange(codeRange);
				analyse(codeRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void analyse(CodeRange codeRange) {
		boolean isFixedForm = codeRange.getTokenStream().get(0).getDefinition()
				.equals(LineLead.class);
		TokenStream tokenStream = codeRange.getTokenStream();
		for (int index = codeRange.getStartId(); index <= codeRange.getStopId(); index++) {
			Token token = tokenStream.get(index);
			if (!token.getText().endsWith("\n")) {
				continue;
			}
			if (index == codeRange.getStopId()) {
				break;
			}
			Token nextToken = tokenStream.get(index + 1);
			if (nextToken.equals(Comment.class)) {
				continue;
			}
			if ((nextToken.getDefinition().equals(LineLead.class) && (!isFixedForm))
					|| ((!nextToken.getDefinition().equals(LineLead.class)) && isFixedForm)) {
				if (!violations.containsKey(codeRange.getFile())) {
					violations.put(codeRange.getFile(),
							new ArrayList<FormViolation>());
				}
				violations.get(codeRange.getFile()).add(
						new FormViolation(codeRange, index + 1));
			}
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
	public String getProjectComment(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextProjectComment();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLProjectComment();
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextProjectComment() {
		String text = translator.i18n("The overall project quality is: {0}",
				HTMLConverter.convertQualityLevelToHTML(getProjectQuality()))
				+ "\n\n";
		List<File> files = new ArrayList<File>(violations.keySet());
		Collections.sort(files);
		for (File file : files) {
			text += file + ": "
					+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
					+ "\n";
		}
		return text;
	}

	private String getHTMLProjectComment() {
		String text = "<p>"
				+ translator
						.i18n(
								"The overall project quality is: {0}",
								HTMLConverter
										.convertQualityLevelToHTML(getProjectQuality()))
				+ "</p>\n";
		text += "<ul>\n";
		List<File> files = new ArrayList<File>(violations.keySet());
		Collections.sort(files);
		for (File file : files) {
			text += "<li>" + file + ": "
					+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
					+ "</li>\n";
		}
		text += "</ul>\n";
		return text;
	}

	@Override
	public String getFileComment(File file, ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextFileComment(file);
		} else if (format == ReportingFormat.HTML) {
			return getHTMLFileComment(file);
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextFileComment(File file)
			throws UnsupportedReportingFormatException {
		String text = translator.i18n("Violations:") + "\n\n";
		for (FormViolation violation : violations.get(file)) {
			text += violation.toString(ReportingFormat.TEXT);
		}
		return text;
	}

	private String getHTMLFileComment(File file)
			throws UnsupportedReportingFormatException {
		String text = "<p>" + translator.i18n("Violations:") + "</p>\n";
		text += "<ol>\n";
		for (FormViolation violation : violations.get(file)) {
			text += "<li>" + violation.toString(ReportingFormat.TEXT)
					+ "</li>\n";
		}
		text += "</ol>\n";
		return text;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		int violations = 0;
		List<File> files = getProjectAnalyser().getFiles();
		for (File file : files) {
			if (getQuality(file) != QualityLevel.HIGH) {
				violations++;
			}
		}
		double ratio = (double) violations / (double) files.size();
		if (ratio > 0.1) {
			return QualityLevel.LOW;
		}
		if (ratio > 0.0) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public QualityLevel getQuality(File file) {
		if (violations.size() > 0) {
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}

}
