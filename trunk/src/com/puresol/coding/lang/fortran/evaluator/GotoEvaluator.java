package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.lang.fortran.Fortran;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

public class GotoEvaluator extends AbstractGotoEvaluator {

	private static final long serialVersionUID = 5675509692839948804L;

	private static final Logger logger = Logger.getLogger(GotoEvaluator.class);

	private static final Translator translator = Translator
			.getTranslator(GotoEvaluator.class);

	public GotoEvaluator(ProjectAnalyzer analyser) {
		super(analyser);
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return Fortran.getInstance();
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
								&& (!next.getText().toUpperCase()
										.equals("CONTINUE"))) {
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
			throws UnsupportedFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextProjectComment();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLProjectComment();
		}
		throw new UnsupportedFormatException(format);
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
			for (CodeRange codeRange : getCodeRanges(file)) {
				text += file + ": " + this.getGotoNum(file) + " GOTOs; "
						+ translator.i18n("Quality:") + " "
						+ getQuality(codeRange).getIdentifier() + "\n";
			}
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
			for (CodeRange codeRange : getCodeRanges(file)) {
				text += "<tr>\n";
				text += "<td>"
						+ file
						+ "</td><td>"
						+ this.getGotoNum(file)
						+ "</td><td>"
						+ HTMLConverter
								.convertQualityLevelToHTML(getQuality(codeRange))
						+ "</td>\n";
				text += "<td>\n";
			}
		}
		text += "</table>\n";
		return text;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedFormatException {
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
