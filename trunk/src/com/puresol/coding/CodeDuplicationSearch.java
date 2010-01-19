package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.tokentypes.AbstractSourceTokenDefinition;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class CodeDuplicationSearch {

	private final ProjectAnalyser analyser;
	private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public CodeDuplicationSearch(ProjectAnalyser analyser) {
		this.analyser = analyser;
	}

	public void scan() {
		getAllCodeRanges();
		checkForDuplications();
	}

	private void getAllCodeRanges() {
		for (File file : analyser.getFiles()) {
			codeRanges.addAll(analyser.getCodeRanges(file));
		}
	}

	private void checkForDuplications() {
		CodeRange[] ranges = codeRanges.toArray(new CodeRange[0]);
		for (int left = 0; left < ranges.length - 1; left++) {
			CodeRange leftRange = ranges[left];
			if ((leftRange.getType() == CodeRangeType.FILE)
					|| (leftRange.getType() == CodeRangeType.CLASS)) {
				continue;
			}
			for (int right = left + 1; right < ranges.length; right++) {
				CodeRange rightRange = ranges[right];
				if ((rightRange.getType() == CodeRangeType.FILE)
						|| (rightRange.getType() == CodeRangeType.CLASS)) {
					continue;
				}
				check(leftRange, rightRange);
			}
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
				checkDetails(leftStream, leftIndex, left.getStop(),
						rightStream, rightIndex, right.getStop());
			}

		}
	}

	private int checkDetails(TokenStream leftStream, int leftIndex,
			int leftStop, TokenStream rightStream, int rightIndex, int rightStop) {
		int counter = 0;
		try {
			Token leftToken = leftStream.get(leftIndex);
			Token rightToken = rightStream.get(rightIndex);
			while ((leftIndex <= leftStop) && (rightIndex <= rightStop)) {
				counter++;
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
					continue;
				}
				if (leftDefinition.equals(rightDefinition)) {
					continue;
				}
				break;
			}
		} catch (NoMatchingTokenException e) {
			// moving failure due to end maybe
			// it's not to be tracked here...
		}
		if (counter > 10) {
			System.out.println(counter);
		}
		return counter;
	}
}
