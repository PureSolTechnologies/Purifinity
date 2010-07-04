package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.utils.Property;

public class CodeDepth extends AbstractCodeRangeEvaluator {

	private static final long serialVersionUID = -2151200082569811564L;

	private static final Logger logger = Logger.getLogger(CodeDepth.class);
	private static final Translator translator = Translator
			.getTranslator(CodeDepth.class);

	public static final String NAME = translator.i18n("Code Depth Metric");
	public static final String DESCRIPTION = translator
			.i18n("Analysis the stacked code blocks for a maximum depth.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
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

	private int maxLayer = 0;

	public CodeDepth(CodeRange range) {
		super(range);
	}

	@Override
	public void run() {
		try {
			TokenStream stream = getCodeRange().getTokenStream();
			int layer = 0;
			for (int index = getCodeRange().getStartId(); index <= getCodeRange()
					.getStopId(); index++) {
				Token token = stream.get(index);
				SourceTokenDefinition def = (SourceTokenDefinition) token
						.getDefinitionInstance();
				if (def.changeBlockLayer() != 0) {
					layer += def.changeBlockLayer();
				}
				if (layer > maxLayer) {
					maxLayer = layer;
				}
			}
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	public int getMaxLayer() {
		return maxLayer;
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
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
		report += getMaxLayer();
		return report;
	}

	private String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("CodeDepth") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += "<p>" + translator.i18n("Maximum code depth: ") + "</p>";
		report += getMaxLayer();
		return report;
	}

	@Override
	public QualityLevel getQuality() {
		int maxLayer = getMaxLayer();
		if (maxLayer > 6) {
			return QualityLevel.LOW;
		} else if (maxLayer > 4) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
