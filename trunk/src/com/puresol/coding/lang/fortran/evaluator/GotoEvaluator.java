package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.gotos.AbstractGotoEvaluator;
import com.puresol.coding.evaluator.gotos.FoundGoto;
import com.puresol.coding.evaluator.gotos.FoundLabel;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;

public class GotoEvaluator extends AbstractGotoEvaluator {

	private static final Logger logger = Logger.getLogger(GotoEvaluator.class);

	private static final Translator translator = Translator
			.getTranslator(GotoEvaluator.class);

	public GotoEvaluator(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public String getLanguage() {
		return "Fortran";
	}

	protected void analyse(CodeRange codeRange) {
		TokenStream tokenStream = codeRange.getTokenStream();
		for (int pos = codeRange.getStartId(); pos <= codeRange.getStopId(); pos++) {
			Token token = tokenStream.get(pos);
			if (token.getDefinition().equals(GotoKeyword.class)) {
				try {
					FoundGoto foundGoto = new FoundGoto(codeRange, pos,
							tokenStream.findNextToken(pos).getText());
					addGoto(codeRange, foundGoto);
				} catch (NoMatchingTokenException e) {
					logger.warn(e.getMessage(), e);
				}
			} else if (token.getDefinition().equals(LineLead.class)) {
				String label = token.getText().substring(1, 5);
				label = label.trim();
				if (!label.isEmpty()) {
					try {
						Token next = tokenStream.findNextToken(pos);
						if ((!next.getText().toUpperCase().equals("FORMAT"))
								&& (!next.getText().toUpperCase().equals(
										"CONTINUE"))) {
							FoundLabel foundLabel = new FoundLabel(codeRange,
									pos, label);
							addLabel(codeRange, foundLabel);
						}
					} catch (NoMatchingTokenException e) {
					}
				}
			}
		}
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
				"{0} files were scanned for usage of GOTO.", getFiles().size())
				+ "\n";
		text += translator.i18n("Quality:") + " "
				+ getProjectQuality().getIdentifier() + "\n";
		text += "\n";
		List<File> files = getFiles();
		Collections.sort(files);
		for (File file : files) {
			text += file + ": " + this.getGotoNum(file) + " GOTOs; "
					+ translator.i18n("Quality:") + " "
					+ getQuality(file).getIdentifier() + "\n";
		}
		return text;
	}

	private String getHTMLProjectComment() {
		String text = "<p>"
				+ translator.i18n("{0} files were scanned for usage of GOTO.",
						getFiles().size()) + "<br/>\n";
		text += translator.i18n("Quality:")
				+ HTMLConverter.convertQualityLevelToHTML(getProjectQuality())
				+ "</p>\n";
		text += "<table>\n";
		text += "<tr><th>" + translator.i18n("File") + "</th><th>"
				+ translator.i18n("Number of GOTOs") + "</th><th>"
				+ translator.i18n("Quality Level") + "</th></tr>\n";
		List<File> files = getFiles();
		Collections.sort(files);
		for (File file : files) {
			text += "<tr>\n";
			text += "<td>" + file + "</td><td>" + this.getGotoNum(file)
					+ "</td><td>"
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
		String text = "";
		text += file + ": " + translator.i18n("Quality:") + " "
				+ getQuality(file).getIdentifier() + "\n";
		for (CodeRange codeRange : getCodeRanges(file)) {
			if (getGotoNum(codeRange) == 0) {
				continue;
			}
			text += codeRange.getTitleString(ReportingFormat.TEXT);
			for (FoundGoto foundGoto : getGotos(codeRange)) {
				text += foundGoto.toString(ReportingFormat.TEXT);
			}
		}
		return text;
	}

	private String getHTMLFileComment(File file)
			throws UnsupportedReportingFormatException {
		String text = "";
		text += "<h3>" + file + "</h3>\n";
		text += "<p>" + translator.i18n("Overall quality:") + " "
				+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
				+ "</p>\n";
		text += "<p><b><u>GOTOs:</u></b></p>";
		text += "<ol>\n";
		for (CodeRange codeRange : getCodeRanges(file)) {
			ArrayList<FoundGoto> gotos = getGotos(codeRange);
			if (gotos != null) {
				text += "<li>\n";
				text += codeRange.getTitleString(ReportingFormat.HTML);
				text += "<ul>\n";
				for (FoundGoto foundGoto : gotos) {
					text += "<li>";
					text += foundGoto.toString(ReportingFormat.HTML);
					String labelName = foundGoto.getLabelName().toUpperCase();
					boolean found = false;
					List<FoundLabel> labels = getLabels(codeRange);
					if (labels != null) {
						for (FoundLabel label : labels) {
							if (label.getLabelName().toUpperCase().equals(
									labelName)) {
								found = true;
								break;
							}
						}
					}
					if (!found) {
						text += "<b>"
								+ translator
										.i18n("There was not an explicit label found! Is it a reusage of a loop label? This might be confusing and should be avoided.")
								+ "</b>";
					}
					text += "</li>\n";
				}
				text += "</ul>\n";
				text += "</li>\n";
			}
		}
		text += "</ol>\n";
		text += "<p><b><u>Labels:</u></b></p>";
		text += "<ol>\n";
		for (CodeRange codeRange : getCodeRanges(file)) {
			ArrayList<FoundLabel> labels = getLabels(codeRange);
			if (labels != null) {
				text += "<li>\n";
				text += codeRange.getTitleString(ReportingFormat.HTML);
				text += "<ul>\n";
				for (FoundLabel foundLabel : labels) {
					text += "<li>" + foundLabel.toString(ReportingFormat.HTML)
							+ "</li>\n";
				}
				text += "</ul>\n";
				text += "</li>\n";
			}
		}
		text += "</ol>\n";
		return text;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		String text = "";
		text += codeRange.getTitleString(format) + "\n";
		if (getGotoNum(codeRange) == 0) {
			return "";
		}
		text += codeRange.getTitleString(format);
		for (FoundGoto foundGoto : getGotos(codeRange)) {
			text += foundGoto.toString(format);
		}
		return text;
	}

	@Override
	public QualityLevel getProjectQuality() {
		if (getGotoNum() > getFiles().size() * 0.01) {
			return QualityLevel.LOW;
		}
		if (getGotoNum() > 0) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public QualityLevel getQuality(File file) {
		for (CodeRange codeRange : getCodeRanges(file)) {
			List<FoundGoto> gotos = getGotos(codeRange);
			if (gotos == null) {
				continue;
			}
			if (gotos.size() > 0) {
				return QualityLevel.LOW;
			}
		}
		return QualityLevel.HIGH;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		List<FoundGoto> gotos = getGotos(codeRange);
		if (gotos == null) {
			return QualityLevel.HIGH;
		}
		if (gotos.size() > 0) {
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH;
	}
}
