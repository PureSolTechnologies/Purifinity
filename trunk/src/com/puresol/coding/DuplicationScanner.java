package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.tokentypes.AbstractSourceTokenDefinition;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class DuplicationScanner implements ProgressObservable {

	public static final int OPERATOR_THRESHOLD = 25;

	private ProgressObserver observer;
	private final ProjectAnalyser analyser;
	private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();
	private final ArrayList<Duplication> duplications = new ArrayList<Duplication>();

	public DuplicationScanner(ProjectAnalyser analyser) {
		this.analyser = analyser;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		clearDuplications();
		getAllCodeRanges();
		checkForDuplications();
	}

	private void clearDuplications() {
		duplications.clear();
	}

	private void getAllCodeRanges() {
		for (File file : analyser.getFiles()) {
			codeRanges.addAll(analyser.getCodeRanges(file));
		}
	}

	private void checkForDuplications() {
		CodeRange[] ranges = codeRanges.toArray(new CodeRange[0]);
		if (observer != null) {
			observer.setRange(0, (ranges.length - 1) * (ranges.length - 1));
			observer.setStatus(0);
		}
		for (int left = 0; left < ranges.length - 1; left++) {
			if (observer != null) {
				observer.setStatus(left * ranges.length);
			}
			CodeRange leftRange = ranges[left];
			if ((leftRange.getType() == CodeRangeType.FILE)
					|| (leftRange.getType() == CodeRangeType.CLASS)) {
				continue;
			}
			for (int right = left + 1; right < ranges.length; right++) {
				if (observer != null) {
					observer.setStatus(left * ranges.length + right);
				}
				CodeRange rightRange = ranges[right];
				if ((rightRange.getType() == CodeRangeType.FILE)
						|| (rightRange.getType() == CodeRangeType.CLASS)) {
					continue;
				}
				if (observer != null) {
					observer.setText(leftRange.getFile() + " <--> "
							+ rightRange.getFile());
				}
				check(leftRange, rightRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void check(CodeRange left, CodeRange right) {
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
			CodeRange right, int rightIndex) {
		int counter = 0;
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
						counter++;
					}
					continue;
				}
				if (leftDefinition.equals(rightDefinition)) {
					counter++;
					continue;
				}
				break;
			}
		} catch (NoMatchingTokenException e) {
			// moving failure due to end maybe
			// it's not to be tracked here...
		}
		Duplication duplication = null;
		if (counter > OPERATOR_THRESHOLD) {
			CodeRange leftDuplication = new CodeRange(leftStream.getFile(),
					left.getType(), left.getName(), leftStream, leftIndex,
					leftToken.getTokenID());
			CodeRange rightDuplication = new CodeRange(rightStream.getFile(),
					right.getType(), right.getName(), rightStream, rightIndex,
					rightToken.getTokenID());
			duplication = new Duplication(leftDuplication, rightDuplication,
					counter);
			addDuplication(duplication);
			System.out.println(counter);
		}
		return duplication;
	}

	private void addDuplication(Duplication duplication) {
		duplications.add(duplication);
	}

	public ArrayList<Duplication> getDuplications() {
		return duplications;
	}
}
