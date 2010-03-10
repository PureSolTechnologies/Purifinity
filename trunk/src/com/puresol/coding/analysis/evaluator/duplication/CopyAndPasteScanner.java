package com.puresol.coding.analysis.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;

public class CopyAndPasteScanner implements ProgressObservable {

    public static final int THRESHOLD = 50;

    private static final Logger logger =
	    Logger.getLogger(CopyAndPasteScanner.class);

    private ProgressObserver observer;
    private final ProjectAnalyser analyser;
    private final Hashtable<CodeRange, ArrayList<Integer>> codeRanges =
	    new Hashtable<CodeRange, ArrayList<Integer>>();
    private final ArrayList<Duplication> duplications =
	    new ArrayList<Duplication>();

    public CopyAndPasteScanner(ProjectAnalyser analyser) {
	this.analyser = analyser;
    }

    @Override
    public void setMonitor(ProgressObserver observer) {
	this.observer = observer;
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
    }

    private void getAllCodeRanges() throws TokenException {
	for (File file : analyser.getFiles()) {
	    for (CodeRange codeRange : analyser.getCodeRanges(file)) {
		if (!codeRange.getType().isRunnableCodeSegment()) {
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

    private void checkForDuplications() throws TokenException {
	CodeRange[] ranges = codeRanges.keySet().toArray(new CodeRange[0]);
	if (observer != null) {
	    observer
		    .setRange(0, ((ranges.length + 1) * ranges.length) / 2);
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
		    observer
			    .setStatus(((ranges.length - 1) + (ranges.length - left))
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
	for (int leftIndex = 0; leftIndex < leftHashes.size() - THRESHOLD; leftIndex++) {
	    for (int rightIndex = 0; rightIndex < rightHashes.size()
		    - THRESHOLD; rightIndex++) {
		for (int index = 0; (leftIndex + index < leftHashes.size())
			&& (rightIndex + index < rightHashes.size()); index++) {
		    Integer leftHash = leftHashes.get(leftIndex + index);
		    Integer rightHash =
			    rightHashes.get(rightIndex + index);
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

    private void check(CodeRange left, CodeRange right)
	    throws TokenException {
	for (int leftIndex = left.getStart(); leftIndex <= left.getStop(); leftIndex++) {
	    for (int rightIndex = right.getStart(); rightIndex <= right
		    .getStop(); rightIndex++) {
		Duplication duplication =
			checkDetails(left, leftIndex, right, rightIndex);
		if (duplication != null) {
		    leftIndex = duplication.getLeft().getStop();
		    rightIndex = duplication.getRight().getStop();
		}
	    }

	}
    }

    private Duplication checkDetails(CodeRange left, int leftIndex,
	    CodeRange right, int rightIndex) throws TokenException {
	int counter = 0;
	TokenStream leftTokens = left.getTokenStream();
	TokenStream rightTokens = right.getTokenStream();
	while ((leftIndex + counter < left.getStop())
		&& (rightIndex + counter < right.getStop())) {
	    Token leftToken = leftTokens.get(leftIndex + counter);
	    Token rightToken = rightTokens.get(rightIndex + counter);
	    if (!leftToken.getText().equals(rightToken.getText())) {
		break;
	    }
	    counter++;
	}
	Duplication duplication = null;
	if (counter > THRESHOLD) {
	    CodeRange leftDuplication =
		    new CodeRange(left.getFile(), left.getLanguage(), left
			    .getType(), left.getName(), left
			    .getTokenStream(), leftIndex, leftIndex
			    + counter - 1);
	    CodeRange rightDuplication =
		    new CodeRange(right.getFile(), right.getLanguage(),
			    right.getType(), right.getName(), right
				    .getTokenStream(), rightIndex,
			    rightIndex + counter - 1);
	    duplication =
		    new Duplication(leftDuplication, rightDuplication,
			    counter);
	    addDuplication(duplication);
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
