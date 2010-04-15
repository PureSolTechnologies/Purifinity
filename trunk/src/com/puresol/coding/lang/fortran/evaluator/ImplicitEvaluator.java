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
import com.puresol.coding.lang.fortran.source.keywords.ImplicitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NoneKeyword;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class ImplicitEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -1777491557023428085L;

	private static final Translator translator = Translator
			.getTranslator(ImplicitEvaluator.class);

	public static final String NAME = "Implicit Evaluator";
	public static final String DESCRIPTION = translator
			.i18n("Fortran supports the implicit declaration of variables which might lead to confusion. "
					+ "It's not a clean development method, too. "
					+ "Explicit declarations are obvious and should be used as the only method if possible.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private final Hashtable<CodeRange, ArrayList<FoundImplicit>> implicits = new Hashtable<CodeRange, ArrayList<FoundImplicit>>();

	public ImplicitEvaluator(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setDescription(NAME);
			observer.setRange(0, getProjectAnalyser().getFiles().size());
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
				if (!codeRange.getLanguage().equals("Fortran")) {
					continue;
				}
				if (!codeRange.getType().isRunnableCodeSegment()) {
					continue;
				}
				addFile(file);
				addCodeRange(codeRange);
				analyse(codeRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void analyse(CodeRange codeRange) {
		TokenStream tokenStream = codeRange.getTokenStream();
		for (Token token : codeRange.getTokens()) {
			if (!token.getDefinition().equals(ImplicitKeyword.class)) {
				continue;
			}
			boolean isImplicitNone = false;
			Token nextToken = tokenStream.get(token.getTokenID() + 1);
			if (nextToken.getDefinition().equals(NoneKeyword.class)) {
				isImplicitNone = true;
			}
			int pos = token.getTokenID();
			Token currentToken = tokenStream.get(pos);
			String text = currentToken.getText();
			do {
				pos++;
				currentToken = tokenStream.get(pos);
				text += currentToken.getText();
			} while (!currentToken.getText().contains("\n"));
			addImplicit(codeRange, new FoundImplicit(codeRange, token
					.getTokenID(), text, isImplicitNone));
		}
	}

	private void addImplicit(CodeRange codeRange, FoundImplicit foundImplicit) {
		if (!implicits.containsKey(codeRange)) {
			implicits.put(codeRange, new ArrayList<FoundImplicit>());
		}
		implicits.get(codeRange).add(foundImplicit);
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
		String text = translator.i18n(
				"{0} files were scanned for usage of IMPLICIT.", getFiles()
						.size())
				+ "\n";
		text += translator.i18n("Quality:") + " "
				+ getProjectQuality().getIdentifier() + "\n";
		text += "\n";
		List<File> files = getFiles();
		Collections.sort(files);
		for (File file : files) {
			text += file + ": " + translator.i18n("Quality:") + " "
					+ getQuality(file).getIdentifier() + "\n";
		}
		return text;
	}

	private String getHTMLProjectComment() {
		String text = "<p>"
				+ translator.i18n(
						"{0} files were scanned for usage of IMPLICIT.",
						getFiles().size()) + "<br/>\n";
		text += translator.i18n("Quality:")
				+ HTMLConverter.convertQualityLevelToHTML(getProjectQuality())
				+ "</p>\n";
		text += "<table>\n";
		text += "<tr><th>" + translator.i18n("File") + "</th><th>"
				+ translator.i18n("Quality Level") + "</th></tr>\n";
		List<File> files = getFiles();
		Collections.sort(files);
		for (File file : files) {
			text += "<tr>\n";
			text += "<td>" + file + "</td><td>"
					+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
					+ "</td>\n";
			text += "<td>\n";
		}
		text += "</table>\n";
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
		List<CodeRange> codeRanges = getProjectAnalyser().getCodeRanges(file);
		if (codeRanges == null) {
			return "";
		}
		String text = "";
		text += file + ": " + translator.i18n("Quality:") + " "
				+ getQuality(file).getIdentifier() + "\n";
		for (CodeRange codeRange : codeRanges) {
			text += getTextCodeRangeComment(codeRange) + "\n\n";
		}
		return text;
	}

	private String getHTMLFileComment(File file)
			throws UnsupportedReportingFormatException {
		List<CodeRange> codeRanges = getProjectAnalyser().getCodeRanges(file);
		if (codeRanges == null) {
			return "";
		}
		String text = "";
		text += "<h3>" + file + "</h3>\n";
		text += "<p>" + translator.i18n("Overall quality:") + " "
				+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
				+ "</p>\n";
		for (CodeRange codeRange : codeRanges) {
			text += getHTMLCodeRangeComment(codeRange) + "\n\n";
		}
		return text;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextCodeRangeComment(codeRange);
		} else if (format == ReportingFormat.HTML) {
			return getHTMLCodeRangeComment(codeRange);
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextCodeRangeComment(CodeRange codeRange)
			throws UnsupportedReportingFormatException {
		if (codeRange.getType() == CodeRangeType.FILE) {
			return "";
		}
		String text = codeRange.getTitleString(ReportingFormat.TEXT) + "\n\n";
		List<FoundImplicit> foundImplicits = implicits.get(codeRange);
		if (foundImplicits == null) {
			text += translator
					.i18n("No IMPLICIT statement was found! IMPLICIT NONE should be placed at the beginning of each code range.")
					+ "\n";
		} else if (foundImplicits.size() == 0) {
			text += translator
					.i18n("No IMPLICIT statement was found! IMPLICIT NONE should be placed at the beginning of each code range.")
					+ "\n";
		} else {
			boolean none = false;
			boolean nonNone = false;
			for (FoundImplicit implicit : foundImplicits) {
				if (implicit.isImplicitNone()) {
					none = true;
				} else {
					nonNone = true;
				}
			}
			if (foundImplicits.size() > 1) {
				text += translator
						.i18n("Multiple IMPLICIT statements were found. This should be avoided to express a clear intention.")
						+ "\n";
			}
			if (none && nonNone) {
				text += translator
						.i18n("IMPLICIT XXX and IMPLICIT NONE are present! Only IMPLICIT NONE should be used.")
						+ "\n";
			} else if (nonNone) {
				text += translator
						.i18n("IMPLICIT XXX is present! Only IMPLICIT NONE should be used.")
						+ "\n";
			}
		}
		return text;
	}

	private String getHTMLCodeRangeComment(CodeRange codeRange)
			throws UnsupportedReportingFormatException {
		if (codeRange.getType() == CodeRangeType.FILE) {
			return "";
		}
		String text = codeRange.getTitleString(ReportingFormat.HTML) + "\n";
		text += "<p>\n";
		List<FoundImplicit> foundImplicits = implicits.get(codeRange);
		if (foundImplicits == null) {
			text += translator
					.i18n("No IMPLICIT statement was found! IMPLICIT NONE should be placed at the beginning of each code range.")
					+ "\n";
		} else if (foundImplicits.size() == 0) {
			text += translator
					.i18n("No IMPLICIT statement was found! IMPLICIT NONE should be placed at the beginning of each code range.")
					+ "\n";
		} else {
			boolean none = false;
			boolean nonNone = false;
			for (FoundImplicit implicit : foundImplicits) {
				if (implicit.isImplicitNone()) {
					none = true;
				} else {
					nonNone = true;
				}
			}
			if (foundImplicits.size() > 1) {
				text += translator
						.i18n("Multiple IMPLICIT statements were found. This should be avoided to express a clear intention.")
						+ "<br/>\n";
			}
			if (none && nonNone) {
				text += translator
						.i18n("IMPLICIT XXX and IMPLICIT NONE are present! Only IMPLICIT NONE should be used.")
						+ "\n";
			} else if (nonNone) {
				text += translator
						.i18n("IMPLICIT XXX is present! Only IMPLICIT NONE should be used.")
						+ "\n";
			}
		}
		text += "</p>\n";
		return text;
	}

	@Override
	public QualityLevel getProjectQuality() {
		List<File> files = getProjectAnalyser().getFiles();
		if (files == null) {
			return QualityLevel.HIGH;
		}
		QualityLevel level = QualityLevel.HIGH;
		for (File file : files) {
			level = QualityLevel.getMinLevel(level, getQuality(file));
		}
		return level;
	}

	@Override
	public QualityLevel getQuality(File file) {
		List<CodeRange> codeRanges = getProjectAnalyser().getCodeRanges(file);
		if (codeRanges == null) {
			return QualityLevel.HIGH;
		}
		QualityLevel level = QualityLevel.HIGH;
		for (CodeRange codeRange : codeRanges) {
			level = QualityLevel.getMinLevel(level, getQuality(codeRange));
		}
		return level;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		List<FoundImplicit> foundImplicits = implicits.get(codeRange);
		if (foundImplicits == null) {
			return QualityLevel.LOW;
		}
		for (FoundImplicit implicit : foundImplicits) {
			if (!implicit.isImplicitNone()) {
				return QualityLevel.LOW;
			}
		}
		if (foundImplicits.size() > 1) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.LOW;
	}

}
