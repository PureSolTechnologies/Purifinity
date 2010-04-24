package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

/**
 * This evaluator checks for simple copy and pasted areas and reports
 * duplications.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CopyAndPasteScanner extends AbstractEvaluator {

	private static final long serialVersionUID = -1786580958941615151L;

	public static final int THRESHOLD = 50;

	private static final Logger logger = Logger
			.getLogger(CopyAndPasteScanner.class);
	private static final Translator translator = Translator
			.getTranslator(CopyAndPasteScanner.class);

	public static final String NAME = "Copy & Paste Scanner";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator scans for code which was copy and pasted.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private final Hashtable<CodeRange, ArrayList<Integer>> codeRanges = new Hashtable<CodeRange, ArrayList<Integer>>();
	private final ArrayList<Duplication> duplications = new ArrayList<Duplication>();
	private final Hashtable<File, ArrayList<Duplication>> fileDuplications = new Hashtable<File, ArrayList<Duplication>>();

	public CopyAndPasteScanner(ProjectAnalyser analyser) {
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
			for (CodeRange codeRange : getEvaluableCodeRanges(file)) {
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

	private void check(CodeRange left, CodeRange right) throws TokenException {
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
			CodeRange right, int rightIndex) throws TokenException {
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
	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		} else if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		}
		throw new UnsupportedReportingFormatException(format);
	}

	@Override
	public String getFileComment(File file, ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.HTML) {
			return getHTMLFileComment(file);
		} else if (format == ReportingFormat.TEXT) {
			return getTextFileComment(file);
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getHTMLFileComment(File file)
			throws UnsupportedReportingFormatException {
		ArrayList<Duplication> duplications = getDuplications(file);
		if (duplications == null) {
			return "";
		}
		String report = "<p>"
				+ translator
						.i18n(
								"{0} possible duplications were found within file {1}.",
								duplications.size(), file) + "</p>";
		if (duplications != null) {
			for (Duplication duplication : duplications) {
				report += duplication.toString(ReportingFormat.HTML);
			}
		}
		return report;
	}

	private String getTextFileComment(File file)
			throws UnsupportedReportingFormatException {
		ArrayList<Duplication> duplications = getDuplications(file);
		if (duplications == null) {
			return "";
		}
		String report = translator.i18n(
				"{0} possible duplications were found within file {1}.",
				duplications.size(), file);
		report += "\n\n";
		if (duplications != null) {
			int count = 0;
			for (Duplication duplication : duplications) {
				count++;
				report += "<p>" + translator.i18n("Duplication {0}:", count)
						+ "</p>";
				report += duplication.toString(ReportingFormat.TEXT) + "\n\n";
			}
		}
		return report;
	}

	@Override
	public String getProjectComment(ReportingFormat format) {
		return "";
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
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) {
		return "";
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}

	public static void main(String args[]) {
		ProjectAnalyser analyser = new ProjectAnalyser(new File(
				"/home/ludwig/workspace/i18n4java"), "src/javax/i18n4j/*.java");
		analyser.run();
		CopyAndPasteScanner cnpScanner = new CopyAndPasteScanner(analyser);
		cnpScanner.run();
	}

}