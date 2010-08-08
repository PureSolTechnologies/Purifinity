package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractProjectEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenCreationException;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

/**
 * This evaluator checks for simple copy and pasted areas and reports
 * duplications.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CopyAndPasteScanner extends AbstractProjectEvaluator {

	private static final long serialVersionUID = -1786580958941615151L;

	public static final int THRESHOLD = 50;

	private static final Logger logger = Logger
			.getLogger(CopyAndPasteScanner.class);
	private static final Translator translator = Translator
			.getTranslator(CopyAndPasteScanner.class);

	/*
	 * Static information...
	 */
	public static final String NAME = "Copy & Paste Scanner";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator scans for code which was copy and pasted.");
	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
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
	private final Map<File, List<Duplication>> filewiseDuplications = new Hashtable<File, List<Duplication>>();

	public CopyAndPasteScanner(ProjectAnalyzer analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		try {
			clearDuplications();
			getAllCodeRanges();
			checkForDuplications();
		} catch (TokenCreationException e) {
			logger.error(e);
		}
	}

	private void clearDuplications() {
		duplications.clear();
		filewiseDuplications.clear();
	}

	private void getAllCodeRanges() throws TokenCreationException {

		for (File file : getProjectAnalyser().getFiles()) {
			for (CodeRange codeRange : getProjectAnalyser().getAnalyzer(file)
					.getNonFragmentCodeRangesRecursively()) {
				if (!codeRange.getCodeRangeType().isRunnableCodeSegment()) {
					continue;
				}
				ArrayList<Integer> hashStream = new ArrayList<Integer>();
				for (Token token : codeRange.getTokens()) {
					hashStream.add(token.getText().hashCode());
				}
				codeRanges.put(codeRange, hashStream);
			}
		}
	}

	private void checkForDuplications() throws TokenCreationException {
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
					observer.setStatus(((ranges.length - 1) + (ranges.length - left))
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
		for (int leftIndex = 0; leftIndex < leftHashes.size() - THRESHOLD; leftIndex++) {
			for (int rightIndex = 0; rightIndex < rightHashes.size()
					- THRESHOLD; rightIndex++) {
				for (int index = 0; (leftIndex + index < leftHashes.size())
						&& (rightIndex + index < rightHashes.size()); index++) {
					Integer leftHash = leftHashes.get(leftIndex + index);
					Integer rightHash = rightHashes.get(rightIndex + index);
					if (leftHash.compareTo(rightHash) != 0) {
						break;
					}
					if (index >= THRESHOLD - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void check(CodeRange left, CodeRange right) throws TokenCreationException {
		for (int index = 0; (left.getStartId() + index <= left.getStopId())
				&& (right.getStartId() + index <= right.getStopId()); index++) {
			Duplication duplication = checkDetails(left, left.getStartId()
					+ index, right, right.getStartId() + index);
			if (duplication != null) {
				index += duplication.getLeft().getStopId()
						- duplication.getLeft().getStartId();
			}
		}
	}

	private Duplication checkDetails(CodeRange left, int leftIndex,
			CodeRange right, int rightIndex) throws TokenCreationException {
		int counter = 0;
		TokenStream leftTokens = left.getTokenStream();
		TokenStream rightTokens = right.getTokenStream();
		while ((leftIndex + counter < left.getStopId())
				&& (rightIndex + counter < right.getStopId())) {
			Token leftToken = leftTokens.get(leftIndex + counter);
			Token rightToken = rightTokens.get(rightIndex + counter);
			if (!leftToken.getText().equals(rightToken.getText())) {
				break;
			}
			counter++;
		}
		Duplication duplication = null;
		if (counter > THRESHOLD) {
			CodeRange leftDuplication = left.createPartialCodeRange(leftIndex,
					leftIndex + counter - 1);
			CodeRange rightDuplication = right.createPartialCodeRange(
					rightIndex, rightIndex + counter - 1);
			duplication = new Duplication(left, right, leftDuplication,
					rightDuplication);
			addDuplication(duplication);
		}
		return duplication;
	}

	private void addDuplication(Duplication duplication) {
		duplications.add(duplication);
		if (!filewiseDuplications.containsKey(duplication.getLeft().getFile())) {
			filewiseDuplications.put(duplication.getLeft().getFile(),
					new ArrayList<Duplication>());
		}
		if (!filewiseDuplications.containsKey(duplication.getRight().getFile())) {
			filewiseDuplications.put(duplication.getRight().getFile(),
					new ArrayList<Duplication>());
		}
		filewiseDuplications.get(duplication.getLeft().getFile()).add(
				duplication);
		filewiseDuplications.get(duplication.getRight().getFile()).add(
				duplication);
	}

	public List<Duplication> getDuplications() {
		return duplications;
	}

	public List<Duplication> getDuplications(File file) {
		return filewiseDuplications.get(file);
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