package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.tokentypes.AbstractSourceTokenDefinition;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Property;

public class DuplicationScanner extends AbstractEvaluator implements
		ProgressObservable {

	public static final int OPERATOR_THRESHOLD = 50;

	private static final Logger logger = Logger
			.getLogger(DuplicationScanner.class);
	private static final Translator translator = Translator
			.getTranslator(DuplicationScanner.class);

	public static final String NAME = "Code Duplication Scanner";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator scans for simmilar functional code.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private final Hashtable<CodeRange, ArrayList<Integer>> codeRanges = new Hashtable<CodeRange, ArrayList<Integer>>();
	private final ArrayList<Duplication> duplications = new ArrayList<Duplication>();
	private final Hashtable<File, ArrayList<Duplication>> fileDuplications = new Hashtable<File, ArrayList<Duplication>>();

	public DuplicationScanner(ProjectAnalyser analyser) {
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
			addFile(file);
			for (CodeRange codeRange : getProjectAnalyser().getCodeRanges(file)) {
				if (!codeRange.getType().isRunnableCodeSegment()) {
					continue;
				}
				ArrayList<Integer> hashStream = new ArrayList<Integer>();
				for (int index = codeRange.getStart(); index <= codeRange
						.getStop(); index++) {
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
		ArrayList<Integer> leftHashes = codeRanges.get(left);
		ArrayList<Integer> rightHashes = codeRanges.get(right);
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
		for (int leftIndex = left.getStart(); leftIndex <= left.getStop(); leftIndex++) {
			Token leftToken = leftStream.get(leftIndex);
			if (leftToken.getPublicity() != TokenPublicity.VISIBLE) {
				continue;
			}
			if (((AbstractSourceTokenDefinition) leftToken
					.getDefinitionInstance()).getSymbolType() != SymbolType.OPERATOR) {
				continue;
			}
			for (int rightIndex = right.getStart(); rightIndex <= right
					.getStop(); rightIndex++) {
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
					leftIndex = duplication.getLeft().getStop();
					rightIndex = duplication.getRight().getStop();
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
			while ((leftToken.getTokenID() < left.getStop())
					&& (rightToken.getTokenID() < right.getStop())) {
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
			duplication = new Duplication(leftDuplication, rightDuplication,
					operatorCounter + operantCounter);
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

	public ArrayList<Duplication> getDuplications() {
		return duplications;
	}

	public ArrayList<Duplication> getDuplications(File file) {
		return fileDuplications.get(file);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getProjectComment() {
		return "";
	}

	@Override
	public String getFileComment(File file) {
		String report = "";
		ArrayList<Duplication> duplications = getDuplications(file);
		for (Duplication duplication : duplications) {
			report += duplication.toString() + "\n\n";
		}
		return report;
	}

	@Override
	public QualityLevel getProjectQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public QualityLevel getQuality(File file) {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange) {
		return "";
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}
}
