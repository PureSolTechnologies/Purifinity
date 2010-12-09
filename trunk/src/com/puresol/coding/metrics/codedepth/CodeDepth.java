package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.trees.TreeIterator;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.lexer.Token;
import com.puresol.utils.Property;

/**
 * This metric looks for cascaded code blocks and finds the maximum. The code
 * depth is a measure for complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepth extends AbstractCodeRangeEvaluator implements
		TreeVisitor<ParserTree> {

	private static final long serialVersionUID = -2151200082569811564L;

	private static final Translator translator = Translator
			.getTranslator(CodeDepth.class);

	public static final String NAME = translator.i18n("Code Depth Metric");

	public static final String DESCRIPTION = translator
			.i18n("Analysis the stacked code blocks for a maximum depth.");

	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(CodeDepth.class, "enabled",
				"Switches calculation of CodeDepth on and off.", Boolean.class,
				"true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
	}

	private final LanguageDependedCodeDepthMetric langDepended;
	private int maxDepth = 0;

	public CodeDepth(ProgrammingLanguage language, CodeRange codeRange) {
		super(codeRange);
		langDepended = language
				.getImplementation(LanguageDependedCodeDepthMetric.class);
		if (langDepended == null) {
			throw new RuntimeException();
		}
	}

	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().setRange(0, 1);
			getMonitor().setDescription(NAME);
		}
		maxDepth = 0;
		TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
				getCodeRange().getParserTree());
		do {
			ParserTree node = iterator.getCurrentNode();
			Token token = node.getToken();
			if (token != null) {
				ParserTree parent = node;
				int depth = 0;
				do {
					if (langDepended.cascadingNode(parent)) {
						depth++;
					}
					parent = parent.getParent();
				} while ((parent != null)
						&& (parent != getCodeRange().getParserTree()));
				if (depth > maxDepth) {
					maxDepth = depth;
				}
			}
		} while (iterator.goForward());
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextReport();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		}
		throw new UnsupportedFormatException(format);
	}

	private String getTextReport() {
		String report = translator.i18n("CodeDepth") + "\n\n";
		report += translator.i18n("Quality: ") + getQuality().getIdentifier();
		report += "\n";
		report += translator.i18n("Maximum code depth: ");
		report += getMaxDepth();
		return report;
	}

	private String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("CodeDepth") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += "<p>" + translator.i18n("Maximum code depth: ") + "</p>";
		report += getMaxDepth();
		return report;
	}

	@Override
	public QualityLevel getQuality() {
		int maxDepth = getMaxDepth();
		if (maxDepth > 6) {
			return QualityLevel.LOW;
		} else if (maxDepth > 4) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public WalkingAction visit(ParserTree tree) {
		// TODO Auto-generated method stub
		return null;
	}
}
