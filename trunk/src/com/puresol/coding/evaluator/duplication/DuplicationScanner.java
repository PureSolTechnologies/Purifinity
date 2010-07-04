package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractProjectEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.tokentypes.AbstractSourceTokenDefinition;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class DuplicationScanner extends AbstractProjectEvaluator {

	private static final long serialVersionUID = -7419706267204649008L;

	public static final int OPERATOR_THRESHOLD = 50;

	private static final Logger logger = Logger
			.getLogger(DuplicationScanner.class);
	private static final Translator translator = Translator
			.getTranslator(DuplicationScanner.class);

	/*
	 * Static information...
	 */
	public static final String NAME = "Code Duplication Scanner";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator scans for simmilar functional code.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.CHANGEABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	/*
	 * Implementation...
	 */
	private final Map<CodeRange, List<Integer>> codeRanges = new Hashtable<CodeRange, List<Integer>>();
	private final List<Duplication> duplications = new ArrayList<Duplication>();
	private final Map<File, List<Duplication>> fileDuplications = new Hashtable<File, List<Duplication>>();

	public DuplicationScanner(ProjectAnalyzer analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		try {
			clearDuplications();
			getAllCodeRanges();
			checkForDuplications();
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	private void clearDuplications() {
		duplications.clear();
		fileDuplications.clear();
	}

	private void getAllCodeRanges() throws TokenException {
		for (File file : getProjectAnalyser().getFiles()) {
			for (CodeRange codeRange : getProjectAnalyser().getAnalyzer(file)
					.getNonFragmentCodeRangesRecursively()) {
				if (!codeRange.getCodeRangeType().isRunnableCodeSegment()) {
					continue;
				}
				ArrayList<Integer> hashStream = new ArrayList<Integer>();
				for (int index = codeRange.getStartId(); index <= codeRange
						.getStopId(); index++) {
					Token token = codeRange.getTokenStream().get(index);
					if (((SourceTokenDefinition) token.getDefinitionInstance())
							.getSymbolType() == SymbolType.OPERATOR) {
						hashStream.add(token.getText().hashCode());
					}
				}
				codeRanges.put(codeRange, hashStream);
			}
		}
	}

	private void checkForDuplications() throws TokenException {
		CodeRange[] ranges = codeRanges.keySet().toArray(new CodeRange[0]);
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setRange(0, ((ranges.length + 1) * ranges.length) / 2);
			observer.setStatus(0);
			observer.setDescription(NAME);
			observer.showProgressPercent();
		}
		for (int left = 0; left < ranges.length - 1; left++) {
			if (Thread.interrupted()) {
				return;
			}
			CodeRange leftRange = ranges[left];
			for (int right = left + 1; right < ranges.length; right++) {
				if (Thread.interrupted()) {
					return;
				}
				if (observer != null) {
					observer.setStatus(((2 * ranges.length - left - 1))
							* (left + 1) / 2);
				}
				CodeRange rightRange = ranges[right];
				if (hashCheck(leftRange, rightRange)) {
					check(leftRange, rightRange);
				}
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private boolean hashCheck(CodeRange left, CodeRange right) {
		List<Integer> leftHashes = codeRanges.get(left);
		List<Integer> rightHashes = codeRanges.get(right);
		for (int leftIndex = 0; leftIndex < leftHashes.size()
				- OPERATOR_THRESHOLD / 2; leftIndex++) {
			for (int rightIndex = 0; rightIndex < rightHashes.size()
					- OPERATOR_THRESHOLD / 2; rightIndex++) {
				int counter = 0;
				for (int index = 0; (leftIndex + index < leftHashes.size())
						&& (rightIndex + index < rightHashes.size()); index++) {
					if (leftHashes.get(leftIndex + index) != rightHashes
							.get(rightIndex + index)) {
						break;
					}
					counter++;
					if (counter > OPERATOR_THRESHOLD / 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void check(CodeRange left, CodeRange right) throws TokenException {
		TokenStream leftStream = left.getTokenStream();
		TokenStream rightStream = right.getTokenStream();
		for (int leftIndex = left.getStartId(); leftIndex <= left.getStopId(); leftIndex++) {
			Token leftToken = leftStream.get(leftIndex);
			if (leftToken.getPublicity() != TokenPublicity.VISIBLE) {
				continue;
			}
			if (((AbstractSourceTokenDefinition) leftToken
					.getDefinitionInstance()).getSymbolType() != SymbolType.OPERATOR) {
				continue;
			}
			for (int rightIndex = right.getStartId(); rightIndex <= right
					.getStopId(); rightIndex++) {
				Token rightToken = rightStream.get(rightIndex);
				if (rightToken.getPublicity() != TokenPublicity.VISIBLE) {
					continue;
				}
				if (((AbstractSourceTokenDefinition) rightToken
						.getDefinitionInstance()).getSymbolType() != SymbolType.OPERATOR) {
					continue;
				}
				if (!leftToken.getDefinition().equals(
						rightToken.getDefinition())) {
					continue;
				}
				if (!leftToken.getText().equals(rightToken.getText())) {
					continue;
				}
				Duplication duplication = checkDetails(left, leftIndex, right,
						rightIndex);
				if (duplication != null) {
					leftIndex = duplication.getLeft().getStopId();
					rightIndex = duplication.getRight().getStopId();
				}
			}

		}
	}

	private Duplication checkDetails(CodeRange left, int leftIndex,
			CodeRange right, int rightIndex) throws TokenException {
		int operatorCounter = 0;
		int operantCounter = 0;
		TokenStream leftStream = left.getTokenStream();
		TokenStream rightStream = right.getTokenStream();
		Token leftToken = leftStream.get(leftIndex);
		Token rightToken = rightStream.get(rightIndex);
		try {
			while ((leftToken.getTokenID() < left.getStopId())
					&& (rightToken.getTokenID() < right.getStopId())) {
				leftToken = leftStream.findNextToken(leftToken.getTokenID());
				rightToken = rightStream.findNextToken(rightToken.getTokenID());
				SourceTokenDefinition leftDefinition = (SourceTokenDefinition) leftToken
						.getDefinitionInstance();
				SourceTokenDefinition rightDefinition = (SourceTokenDefinition) rightToken
						.getDefinitionInstance();
				if (leftDefinition.getSymbolType() != rightDefinition
						.getSymbolType()) {
					break;
				}
				if (leftDefinition.getSymbolType() == SymbolType.OPERANT) {
					if (leftToken.getText().equals(rightToken.getText())) {
						operantCounter++;
					}
					continue;
				}
				if (leftDefinition.equals(rightDefinition)) {
					operatorCounter++;
					continue;
				}
				break;
			}
		} catch (NoMatchingTokenException e) {
			// moving failure due to end maybe
			// it's not to be tracked here...
		}
		Duplication duplication = null;
		if ((operatorCounter + operantCounter) > OPERATOR_THRESHOLD) {
			CodeRange leftDuplication = left.createPartialCodeRange(leftIndex,
					leftToken.getTokenID());
			CodeRange rightDuplication = right.createPartialCodeRange(
					rightIndex, rightToken.getTokenID());
			duplication = new Duplication(left, right, leftDuplication,
					rightDuplication);
			addDuplication(duplication);
		}
		return duplication;
	}

	private void addDuplication(Duplication duplication) {
		duplications.add(duplication);
		if (!fileDuplications.containsKey(duplication.getLeft().getFile())) {
			fileDuplications.put(duplication.getLeft().getFile(),
					new ArrayList<Duplication>());
		}
		if (!fileDuplications.containsKey(duplication.getRight().getFile())) {
			fileDuplications.put(duplication.getRight().getFile(),
					new ArrayList<Duplication>());
		}
		fileDuplications.get(duplication.getLeft().getFile()).add(duplication);
		fileDuplications.get(duplication.getRight().getFile()).add(duplication);
	}

	public List<Duplication> getDuplications() {
		return duplications;
	}

	public List<Duplication> getDuplications(File file) {
		return fileDuplications.get(file);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		} else if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		}
		throw new UnsupportedFormatException(format);
	}

	@Override
	public String getReport(ReportingFormat format) {
		return "";
	}

	@Override
	public QualityLevel getQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
