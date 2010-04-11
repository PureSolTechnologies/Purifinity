package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;

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
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;

public class GotoEvaluator extends AbstractGotoEvaluator {

	private static final Logger logger = Logger.getLogger(GotoEvaluator.class);

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
				String label = token.getText().substring(1, 4);
				label = label.trim();
				if (!label.isEmpty()) {
					try {
						Token next = tokenStream.findNextToken(pos);
						if (!next.getText().toUpperCase().equals("FORMAT")) {
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
		String text = "";
		for (File file : getFiles()) {
			if (getGotoNum(file) == 0) {
				continue;
			}
			text += file + "\n";
			text += getFileComment(file, format);
		}
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
		if (getGotoNum(file) == 0) {
			return "";
		}
		text += file + "\n";
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
		if (getGotoNum(file) == 0) {
			return "";
		}
		text += "<h3>" + file + "</h3>\n";
		text += "<p><b><u>GOTOs:</u></b></p>";
		text += "<ol>\n";
		for (CodeRange codeRange : getCodeRanges(file)) {
			ArrayList<FoundGoto> gotos = getGotos(codeRange);
			if (gotos != null) {
				text += "<li>\n";
				text += codeRange.getTitleString(ReportingFormat.HTML);
				text += "<ul>\n";
				for (FoundGoto foundGoto : gotos) {
					text += "<li>" + foundGoto.toString(ReportingFormat.HTML)
							+ "</li>\n";
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
			if (getGotos(codeRange).size() > 0) {
				return QualityLevel.LOW;
			}
		}
		return QualityLevel.HIGH;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		if (getGotos(codeRange).size() > 0) {
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH;
	}
}
