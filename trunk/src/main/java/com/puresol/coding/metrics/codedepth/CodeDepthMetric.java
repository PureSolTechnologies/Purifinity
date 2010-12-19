package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.trees.TreeIterator;
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
public class CodeDepthMetric extends AbstractEvaluator implements
		CodeRangeEvaluator {

	private static final long serialVersionUID = -2151200082569811564L;

	private static final Translator translator = Translator
			.getTranslator(CodeDepthMetric.class);

	public static final String NAME = translator.i18n("Code Depth Metric");

	public static final String DESCRIPTION = translator
			.i18n("Analysis the stacked code blocks for a maximum depth.");

	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(CodeDepthMetric.class, "enabled",
				"Switches calculation of CodeDepth on and off.", Boolean.class,
				"true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
	}

	private final CodeRange codeRange;
	private final LanguageDependedCodeDepthMetric langDepended;
	private int maxDepth = 0;

	public CodeDepthMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super();
		this.codeRange = codeRange;
		langDepended = language
				.getImplementation(LanguageDependedCodeDepthMetric.class);
		if (langDepended == null) {
			throw new RuntimeException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRange getCodeRange() {
		return codeRange;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
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
		String report = Anchor.generate(getName(), "<h3>"
				+ translator.i18n("CodeDepth") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += "<p>" + translator.i18n("Maximum code depth: ") + "</p>";
		report += getMaxDepth();
		return report;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		return CodeDepthQuality.get(codeRange.getType(), maxDepth);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
