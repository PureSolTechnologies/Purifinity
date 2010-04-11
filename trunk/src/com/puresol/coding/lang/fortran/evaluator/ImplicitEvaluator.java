package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.lang.fortran.source.keywords.ImplicitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NoneKeyword;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class ImplicitEvaluator extends AbstractEvaluator {

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
			observer.setRange(0, getFiles().size());
		}
		int count = 0;
		for (File file : getProjectAnalyser().getFiles()) {
			for (CodeRange codeRange : getProjectAnalyser().getCodeRanges(file)) {
				count++;
				if (!codeRange.getLanguage().equals("Fortran")) {
					continue;
				}
				if (observer != null) {
					observer.setStatus(count);
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
		boolean foundImplicitNone = false;
		boolean foundImplicit = false;
		TokenStream tokenStream = codeRange.getTokenStream();
		for (Token token : codeRange.getTokens()) {
			if (!token.getDefinition().equals(ImplicitKeyword.class)) {
				continue;
			}
			Token nextToken = tokenStream.get(token.getTokenID() + 1);
			if (nextToken.getDefinition().equals(NoneKeyword.class)) {
				foundImplicitNone = true;
			} else {
				foundImplicitNone = true;
			}
			int pos = token.getTokenID();
			Token currentToken = tokenStream.get(pos);
			String text = currentToken.getText();
			pos++;
			do {
				currentToken = tokenStream.get(pos);
				text += currentToken.getText();
			} while (!currentToken.getText().contains("\n"));
			addImplicit(codeRange, new FoundImplicit(codeRange, token
					.getTokenID(), text));
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
